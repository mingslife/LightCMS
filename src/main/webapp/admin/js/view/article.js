app.service("articleService", function(service) {
	this.showArticles = function() {
		return service.ajax("GET", "../articles.do", "");
	};
	this.showArticle = function(id) {
		return service.ajax("GET", "../articles/{id}.do".replace("{id}", id), "");
	};
	this.createArticle = function(record) {
		return service.ajax("POST", "../articles.do", JSON.stringify(record));
	};
	this.updateArticle = function(id, record) {
		return service.ajax("PUT", "../articles/{id}.do".replace("{id}", id), JSON.stringify(record));
	};
	this.deleteArticle = function(id) {
		return service.ajax("DELETE", "../articles/{id}.do".replace("{id}", id), "id=" + id);
	};
});
app.controller("articleController", function($scope, articleService) {
	/*articleService.showArticles().then(function(data) {
		console.info(data);
	});
	articleService.showArticle(1).then(function(data) {
		console.info(data);
	});*/
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
			title: "操作",
			align: "center",
			formatter: function(value, row, index) {
				return '<div class="btn-group">' +
						'<button type="button" class="btn btn-xs btn-primary"><span class="glyphicon glyphicon-edit"></span> 编辑</button>' +
						'<button type="button" class="btn btn-xs btn-danger"><span class="glyphicon glyphicon-trash"></span> 删除</button>' +
						'</div>';
			}
		}]
	});
});