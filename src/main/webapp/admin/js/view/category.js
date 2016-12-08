app.service("categoryService", function(service) {
	this.loadListUrl = service.basePath + "categories.do";
	this.showCategory = function(id) {
		return service.ajax("GET", "categories/{id}.do".replace("{id}", id), null);
	};
	this.saveCategory = function(category) {
		return service.ajax("POST", "categories.do", $.param(category));
	};
	this.updateCategory = function(id, category) {
		return service.ajax("PUT", "categories/{id}.do".replace("{id}", id), $.param(category));
	};
	this.deleteCategory = function(id) {
		return service.ajax("DELETE", "categories/{id}.do".replace("{id}", id), null);
	};
	this.deleteCategories = function(ids) {
		return service.ajax("POST", "categories/deletes.do", Util.arrayParam("ids[]", ids));
	};
});
app.controller("categoryController", function($scope, $routeParams, categoryService) {
	Util.setTitle("分类");
	
	$scope.defaults = {
		IS_VISIBLE: [{
			key: "是",
			value: true
		}, {
			key: "否",
			value: false
		}]
	};
	$scope.saveRecord = function() {
		var category = {
			categoryName: $.trim($scope.category.categoryName),
			position: $scope.category.position,
			isVisible: $scope.category.isVisible
		};
		var saveFunction = $scope.category.id == null ? categoryService.saveCategory(category) : categoryService.updateCategory($scope.category.id, category);
		saveFunction.then(function(data) {
			$("#category-modal").modal("hide");
			$.notify("保存成功！", {type: "success"});
			$("#category-table").bootstrapTable("refresh");
		});
	};
	$scope.showRecord = function(id) {
		categoryService.showCategory(id).then(function(data) {
			$scope.category = data;
			$("#category-modal").modal("show");
		});
	};
	$scope.deleteRecord = function(id) {
		bootbox.confirm({
			className: "modal-warning",
			title: '<span class="fa fa-warning"></span> 警告',
			message: "确认删除吗？",
			callback: function(result) {
				if (result) {
					categoryService.deleteCategory(id).then(function(data) {
						$.notify("删除成功！", {type: "success"});
						$("#category-table").bootstrapTable("refresh");
					});
				}
			}
		});
	};
	$scope.deleteRecords = function() {
		var records = $("#category-table").bootstrapTable("getSelections");
		if (records.length > 0) {
			var ids = records.map(function(item) {
				return item.id;
			});
			bootbox.confirm({
				className: "modal-warning",
				title: '<span class="fa fa-warning"></span> 警告',
				message: "确认删除这" + ids.length + "条数据吗？",
				callback: function(result) {
					categoryService.deleteCategories(ids).then(function(data) {
						$.notify("删除成功！", {type: "success"});
						$("#category-table").bootstrapTable("refresh");
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
	
	if (isNaN($routeParams["id"])) {
		$("#category-modal").on("hidden.bs.modal", function(e) {
			$scope.category = {};
			$scope.$apply();
		});
		$("#category-table").bootstrapTable({
			url: categoryService.loadListUrl,
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
				checkbox: true
			}, {
				field: "categoryName",
				title: "分类名称"
			}, {
				field: "position",
				title: "排序"
			}, {
				field: "isVisible",
				title: "是否可见",
				formatter: function(value, row, index) {
					return Util.enumFormatter($scope.defaults.IS_VISIBLE, value);
				}
			}, {
				field: "id",
				title: "操作",
				align: "center",
				formatter: function(value, row, index) {
					return '<div class="buttons">' +
							'<a href="javascript:;" onclick="app.scopes.categoryScope.showRecord(\'' + value + '\')"><span class="fa fa-pencil"></span></a>' +
							'<a href="javascript:;" onclick="app.scopes.categoryScope.deleteRecord(\'' + value + '\')"><span class="fa fa-trash"></span></a>' +
							'</div>';
				}
			}]
		});
	} else {}
	
	app.scopes.categoryScope = {
		showRecord: $scope.showRecord,
		deleteRecord: $scope.deleteRecord,
		deleteRecords: $scope.deleteRecords
	};
});