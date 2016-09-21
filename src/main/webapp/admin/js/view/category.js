var categoryScope;
app.service("categoryService", function(service) {
	this.showRecords = function() {
		return service.ajax("GET", service.basePath + "categories.do", null);
	};
	this.showRecord = function(id) {
		return service.ajax("GET", service.basePath + "categories/{id}.do".replace("{id}", id), null);
	};
	this.createRecord = function(record) {
		return service.ajax("POST", service.basePath + "categories.do", record);
	};
	this.updateRecord = function(id, record) {
		return service.ajax("PUT", service.basePath + "categories/{id}.do".replace("{id}", id), record);
	};
	this.deleteRecord = function(id) {
		return service.ajax("DELETE", service.basePath + "categories/{id}.do".replace("{id}", id), null);
	};
});
app.controller("categoryController", function($scope, $routeParams, categoryService) {
	var recordId = $routeParams.id;
	if (recordId) {
		$scope.baseSelectDatas = [{
			value: true,
			name: "是"
		}, {
			value: false,
			name: "否"
		}];
		categoryService.showRecord(recordId).then(function(data) {
			console.info(data);
			$scope.category = data;
		});
	} else {
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
					curPage: params.offset / params.limit + 1,
					limit: params.limit
				};
				return _params;
			},
//			toolbar: "#addCategory",
			columns: [{
				field: "categoryName",
				title: "名称"
			}, {
				field: "position",
				title: "排序"
			}, {
				field: "isVisible",
				title: "是否显示"
			}, {
				field: "id",
				title: "操作",
				align: "center",
				formatter: function(value, row, index) {
					return '<div class="btn-group">' +
							'<button type="button" class="btn btn-xs btn-primary"><span class="glyphicon glyphicon-edit"></span> 编辑</button>' +
							'<button type="button" class="btn btn-xs btn-danger" onclick="categoryScope.deleteRecord(\'' + value + '\')"><span class="glyphicon glyphicon-trash"></span> 删除</button>' +
							'</div>';
				}
			}]
		});
	}
});