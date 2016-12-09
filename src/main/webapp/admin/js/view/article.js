app.service("articleService", function(service) {
	this.loadListUrl = service.basePath + "articles.do";
	this.showArticle = function(id) {
		return service.ajax("GET", "articles/{id}.do".replace("{id}", id), null);
	};
	this.saveArticle = function(article) {
		return service.ajax("POST", "articles.do", $.param(article));
	};
	this.updateArticle = function(id, article) {
		return service.ajax("PUT", "articles/{id}.do".replace("{id}", id), $.param(article));
	};
	this.deleteArticle = function(id) {
		return service.ajax("DELETE", "articles/{id}.do".replace("{id}", id), null);
	};
	this.deleteArticles = function(ids) {
		return service.ajax("POST", "articles/deletes.do", Util.arrayParam("ids[]", ids));
	};
});
app.controller("articleController", function($scope, $routeParams, articleService) {
	Util.setTitle("文章管理");
	
	$scope.defaults = {
		IS_VISIBLE: [{
			key: "是",
			value: true
		}, {
			key: "否",
			value: false
		}],
		CAN_COMMENT: [{
			key: "是",
			value: true
		}, {
			key: "否",
			value: false
		}],
		HAS_PASSWORD: [{
			key: "是",
			value: true
		}, {
			key: "否",
			value: false
		}],
		ON_TOP: [{
			key: "是",
			value: true
		}, {
			key: "否",
			value: false
		}]
	};
	$scope.lock = false;
	$scope.backToParent = function() {
		$scope.article = {};
		window.location.hash = "#/article";
	};
	$scope.newRecord = function() {
		if (app.configurations && app.configurations.singlePage) {
			window.location.hash = "#/article/create";
		} else {
			$("#article-modal").modal("show");
		}
	};
	$scope.saveRecord = function() {
		$scope.lock = true;
		var article = {
			// TODO
			categoryName: $.trim($scope.category.categoryName),
			position: $scope.category.position,
			isVisible: $scope.category.isVisible
		};
		var saveFunction = $scope.article.id == null ? articleService.saveArticle(article) : articleService.updateArticle($scope.article.id, article);
		saveFunction.then(function(data) {
			$scope.lock = false;
			$.notify("保存成功！", {type: "success"});
			if ($("#article-table").length > 0) {
				$("#article-modal").modal("hide");
				$("#article-table").bootstrapTable("refresh");
			} else {
				$scope.backToParent();
			}
		}, function(data) {
			$scope.lock = false;
		});
	};
	$scope.showRecord = function(id) {
		articleService.showArticle(id).then(function(data) {
			$scope.article = data;
			if (app.configurations && app.configurations.singlePage) {
				window.location.hash = "#/article/" + id;
			} else {
				$("#article-modal").modal("show");
			}
		});
	};
	$scope.deleteRecord = function(id) {
		bootbox.confirm({
			className: "modal-warning",
			title: '<span class="fa fa-warning"></span> 警告',
			message: "确认删除吗？",
			callback: function(result) {
				if (result) {
					articleService.deleteArticle(id).then(function(data) {
						$.notify("删除成功！", {type: "success"});
						$("#article-table").bootstrapTable("refresh");
					});
				}
			}
		});
	};
	$scope.deleteRecords = function() {
		var records = $("#article-table").bootstrapTable("getSelections");
		if (records.length > 0) {
			var ids = records.map(function(item) {
				return item.id;
			});
			bootbox.confirm({
				className: "modal-warning",
				title: '<span class="fa fa-warning"></span> 警告',
				message: "确认删除这" + ids.length + "条数据吗？",
				callback: function(result) {
					articleService.deleteArticles(ids).then(function(data) {
						$.notify("删除成功！", {type: "success"});
						$("#article-table").bootstrapTable("refresh");
					});
				}
			});
		} else {
			bootbox.alert({
				className: "modal-danger",
				title: '<span class="fa fa-remove"></span> 错误',
				message: "请先选择！"
			});
		}
	};
	
	if ($routeParams["id"] == null) {
		$("#article-modal").on("hidden.bs.modal", function(e) {
			$scope.article = {};
			$scope.$apply();
		});
		$("#article-table").bootstrapTable({
			url: articleService.loadListUrl,
			cache: false,
			striped: false,
			pagination: true,
			pageSize: 10,
			pageList: [10, 20, 30],
			sidePagination: "server",
			showColumns: true,
			showRefresh: true,
			showToggle: true,
			queryParams: function(params) {
				var _params = {
					page: params.offset / params.limit + 1,
					limit: params.limit
				};
				return _params;
			},
			toolbar: "#article-toolbar",
			columns: [{
				checkbox: true
			}, {
				field: "title",
				title: "标题"
			}, {
				field: "publishDate",
				title: "发布时间",
				formatter: function(value, row, index) {
					return moment(value).format("YYYY-MM-DD HH:mm:ss");
				}
			}, {
				field: "isVisible",
				title: "是否可见",
				formatter: function(value, row, index) {
					return Util.enumFormatter($scope.defaults.IS_VISIBLE, value);
				}
			}, {
				field: "canComment",
				title: "是否可评论",
				formatter: function(value, row, index) {
					return Util.enumFormatter($scope.defaults.CAN_COMMENT, value);
				}
			}, {
				field: "hasPassword",
				title: "是否加密",
				formatter: function(value, row, index) {
					return Util.enumFormatter($scope.defaults.HAS_PASSWORD, value);
				}
			}, {
				field: "onTop",
				title: "是否置顶",
				formatter: function(value, row, index) {
					return Util.enumFormatter($scope.defaults.ON_TOP, value);
				}
			}, {
				field: "id",
				title: "操作",
				align: "center",
				formatter: function(value, row, index) {
					return '<div class="buttons">' +
							'<a href="javascript:;" title="编辑" onclick="app.scopes.articleScope.showRecord(\'' + value + '\')"><span class="fa fa-pencil"></span></a>' +
							'<a href="javascript:;" title="删除" onclick="app.scopes.articleScope.deleteRecord(\'' + value + '\')"><span class="fa fa-trash"></span></a>' +
							'</div>';
				}
			}]
		});
	} else if ($routeParams["id"] !== "create") {
		var recordId = $routeParams["id"];
		$scope.showRecord(recordId);
	}
	
	app.scopes.articleScope = {
		newRecord: $scope.newRecord,
		showRecord: $scope.showRecord,
		deleteRecord: $scope.deleteRecord,
		deleteRecords: $scope.deleteRecords
	};
});