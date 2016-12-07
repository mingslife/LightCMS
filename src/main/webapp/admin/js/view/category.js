app.service("categoryService", function(service) {
	this.showCategory = function(id) {
		return service.ajax("GET", "../categories/{id}.do".replace("{id}", id), null);
	};
	this.saveCategory = function(category) {
		return service.ajax("POST", "../categories.do", category);
	};
	this.updateCategory = function(category) {
		return service.ajax("PUT", "../categories/{id}.do".replace("{id}", category.id), category);
	};
	this.deleteCategory = function(id) {
		return service.ajax("DELETE", "../categories/{id}.do".replace("{id}", id), null);
	};
});
app.controller("categoryController", function($scope, $routeParams, categoryService) {
	$scope.defaults = {
		IS_VISIBLE: [{
			key: "是",
			value: true
		}, {
			key: "否",
			value: false
		}]
	};
	$scope.showRecord = function(id) {
		categoryService.showCategory(id).then(function(data) {
			$scope.category = data;
			$("#category-modal").modal("show");
		});
	};
	
	if (isNaN($routeParams["id"])) {
		$("#category-modal").on("hidden.bs.modal", function(e) {
			$scope.category = {};
			$scope.$apply();
		});
		$("#category-table").bootstrapTable({
			url: "../categories.do",
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
			toolbar: "#category-toolbar",
			columns: [{
				field: "categoryName",
				title: "分类名称"
			}, {
				field: "position",
				title: "排序"
			}, {
				field: "isVisible",
				title: "是否可见"
			}, {
				field: "id",
				title: "操作",
				align: "center",
				formatter: function(value, row, index) {
					return '<div class="btn-group">' +
							'<button type="button" class="btn btn-xs btn-primary" onclick="app.scopes.categoryScope.showRecord(\'' + value + '\')"><span class="glyphicon glyphicon-edit"></span> 编辑</button>' +
							'<button type="button" class="btn btn-xs btn-danger" onclick="articleScope.deleteRecord(\'' + value + '\')"><span class="glyphicon glyphicon-trash"></span> 删除</button>' +
							'</div>';
				}
			}]
		});
	} else {}
	
	app.scopes.categoryScope = {
		showRecord: $scope.showRecord
	};
});