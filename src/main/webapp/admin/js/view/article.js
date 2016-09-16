var articleScope;
app.service("articleService", function(service) {
	this.showRecords = function() {
		return service.ajax("GET", "../articles.do", "");
	};
	this.showRecord = function(id) {
		return service.ajax("GET", "../articles/{id}.do".replace("{id}", id), "");
	};
	this.createRecord = function(record) {
		return service.ajax("POST", "../articles.do", JSON.stringify(record));
	};
	this.updateRecord = function(id, record) {
		return service.ajax("PUT", "../articles/{id}.do".replace("{id}", id), JSON.stringify(record));
	};
	this.deleteRecord = function(id) {
		return service.ajax("DELETE", "../articles/{id}.do".replace("{id}", id), "id=" + id);
	};
});
app.controller("articleController", function($scope, $routeParams, articleService) {
	console.info($routeParams);
	var recordId = $routeParams.id;
	if (recordId) {
		$scope.isVisibleDatas = [{
			value: true,
			name: "是"
		}, {
			value: false,
			name: "否"
		}];
		articleService.showRecord(recordId).then(function(data) {
			console.info(data);
			$scope.article = data;
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
	
	articleScope = $scope;
});
