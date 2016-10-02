var articleScope;
app.service("articleService", function(service) {
	this.showRecords = function() {
		return service.ajax("GET", service.basePath + "articles.do", null);
	};
	this.showRecord = function(id) {
		return service.ajax("GET", service.basePath + "articles/{id}.do".replace("{id}", id), null);
	};
	this.createRecord = function(record) {
		return service.ajax("POST", service.basePath + "articles.do", record);
	};
	this.updateRecord = function(id, record) {
		return service.ajax("PUT", service.basePath + "articles/{id}.do".replace("{id}", id), record);
	};
	this.deleteRecord = function(id) {
		return service.ajax("DELETE", service.basePath + "articles/{id}.do".replace("{id}", id), null);
	};
});
app.controller("articleController", function($scope, $routeParams, articleService) {
	console.info($routeParams);
	var articleContentEditor = new SimpleMDE({
		autoDownloadFontAwesome: false,
		element: document.getElementById("article-content"),
//		hideIcons: ["guide"],
//		showIcons: ["code", "table"],
		spellChecker: false,
		/*toolbar: [
			"bold", "italic", "heading", "|",
			"code", "quote", "unordered-list", "ordered-list", "clean-block", "|",
			"link", "image", "table", "|",
			"preview", "side-by-side", "fullscreen", "guide", "|",
			{
				name: "bold",
				action: function(editor) {},
				className: "glyphicon glyphicon-bold",
				title: "加粗"
			}, {
				name: "italic",
				action: function(editor) {},
				className: "glyphicon glyphicon-italic",
				title: "斜体"
			}, {
				name: "heading",
				action: function(editor) {},
				className: "glyphicon glyphicon-header",
				title: "标题"
			}, {
				name: "upload",
				action: function(editor) {
//					alert("Upload image!");
					bootbox.dialog({
						title: "上传图片",
						message: '<input type="file" class="form-control" id="article-content-upload" />',
						buttons: {
							upload: {
								label: "确定",
								className: "btn-primary"
							},
							cancel: {
								label: "取消",
								className: "btn-default"
							}
						}
					});
					$("#article-content-upload").fileupload({
						url: "../upload/image.do",
						dataType: "json",
						add: function(event, data) {
							$("button.btn[data-bb-handler='upload']").click(function() {
								data.submit();
							});
						},
						done: function(event, data) {
							console.info(data);
							articleContentEditor.value(articleContentEditor.value() + "![" + data.result.url + "](" + data.result.url + ")");
						}
					});
				},
				className: "glyphicon glyphicon-picture",
				title: "上传图片"
			}
		]*/
		toolbar: global.toolbar
	});
	var recordId = $routeParams.id;
	if (recordId) {
		$scope.baseSelectDatas = [{
			value: true,
			name: "是"
		}, {
			value: false,
			name: "否"
		}];
		articleService.showRecord(recordId).then(function(data) {
			console.info(data);
			$scope.article = data;
			articleContentEditor.value(data.markdown);
		});
	}
	$("#article-table").bootstrapTable({
		url: "../articles.do",
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
				curPage: params.offset / params.limit + 1,
				limit: params.limit
			};
			return _params;
		},
//		toolbar: "#addCategory",
		columns: [{
			field: "title",
			title: "标题"
		}, {
			field: "authorId",
			title: "作者"
		}, {
			field: "categoryId",
			title: "分类"
		}, {
			field: "publishDate",
			title: "发布时间"
		}, {
			field: "readNumber",
			title: "阅读数"
		}, {
			field: "commentNumber",
			title: "评论数"
		}, {
			field: "isVisible",
			title: "是否显示"
		}, {
			field: "canComment",
			title: "开启评论"
		}, {
			field: "hasPassword",
			title: "是否加密"
		}, {
			field: "onTop",
			title: "是否置顶"
		}, {
			field: "id",
			title: "操作",
			align: "center",
			formatter: function(value, row, index) {
				return '<div class="btn-group">' +
						'<button type="button" class="btn btn-xs btn-primary"><span class="glyphicon glyphicon-edit"></span> 编辑</button>' +
						'<button type="button" class="btn btn-xs btn-danger" onclick="articleScope.deleteRecord(\'' + value + '\')"><span class="glyphicon glyphicon-trash"></span> 删除</button>' +
						'</div>';
			}
		}]
	});
	
	$scope.showRecord = function(id) {
		articleService.showRecord(id).then(function(data) {
			console.info(data);
		});
	};
	$scope.updateRecord = function(id) {
		articleService.updateRecord(id, record).then(function(data) {
			console.info(data);
		});
	};
	$scope.deleteRecord = function(id) {
		swal({
			title: "",
			text: "确认删除？",
			type: "warning",
			showCancelButton: true,
			showLoaderOnConfirm: true
		}, function() {
			articleService.deleteRecord(id).then(function(data) {
				swal("", "删除成功", "success");
			});
		});
	};
	$scope.formatRecord = function(record) {
		var markdown = articleContentEditor.value();
		return {
			title: record.title,
			authorId: record.authorId,
			categoryId: record.categoryId,
			isVisible: record.isVisible,
			canComment: record.canComment,
			password: record.password,
			cover: record.cover,
			keywords: record.keywords,
			description: record.description,
			content: articleContentEditor.markdown(markdown),
			markdown: markdown,
			onTop: record.onTop
		};
	};
	$scope.save = function() {
		console.info($scope.article);
		var record = $scope.formatRecord($scope.article);
		if (recordId) {
			articleService.updateRecord(recordId, record).then(function(data) {
				console.info(data);
			});
		} else {
			articleService.saveRecord(record).then(function(data) {
				console.info(data);
			});
		}
	};
	$scope.cancel = function() {};
	
	articleScope = $scope;
});
