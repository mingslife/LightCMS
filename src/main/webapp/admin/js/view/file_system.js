app.service("fileSystemService", function(service) {
	this.loadListUrl = service.basePath + "file_systems.do";
});
app.controller("fileSystemController", function($scope, $routeParams, fileSystemService) {
	Util.setTitle("文件管理");
	
	$scope.defaults = {
			IS_VISIBLE: [{
				key: "是",
				value: true
			}, {
				key: "否",
				value: false
			}]
		};
		$scope.lock = false;
		$scope.backToParent = function() {
			$scope.category = {};
			window.location.hash = "#/category";
		};
		$scope.newRecord = function() {
			if (app.configurations && app.configurations.singlePage) {
				window.location.hash = "#/category/create";
			} else {
				$("#category-modal").modal("show");
			}
		};
		$scope.saveRecord = function() {
			$scope.lock = true;
			var category = {
				categoryName: $.trim($scope.category.categoryName),
				position: $scope.category.position,
				isVisible: $scope.category.isVisible
			};
			var saveFunction = $scope.category.id == null ? categoryService.saveCategory(category) : categoryService.updateCategory($scope.category.id, category);
			saveFunction.then(function(data) {
				$scope.lock = false;
				$.notify("保存成功！", {type: "success"});
				if ($("#category-table").length > 0) {
					$("#category-modal").modal("hide");
					$("#category-table").bootstrapTable("refresh");
				} else {
					$scope.backToParent();
				}
			}, function(data) {
				$scope.lock = false;
			});
		};
		$scope.showRecord = function(id) {
			categoryService.showCategory(id).then(function(data) {
				$scope.category = data;
				if (app.configurations && app.configurations.singlePage) {
					window.location.hash = "#/category/" + id;
				} else {
					$("#category-modal").modal("show");
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
		
		if ($routeParams["id"] == null) {
			$("#file-system-modal").on("hidden.bs.modal", function(e) {
				$scope.fileSystem = {};
				$scope.$apply();
			});
			$("#file-system-table").bootstrapTable({
				url: fileSystemService.loadListUrl,
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
				toolbar: "#file-system-toolbar",
				columns: [{
					checkbox: true
				}, {
					field: "fileName",
					title: "文件名称"
				}, {
					field: "fileSize",
					title: "大小",
					formatter: function(value, row, index) {
						return value == null ? null : Util.bytesToSize(value);
					}
				}, {
					field: "lastModifiedDate",
					title: "修改日期",
					formatter: function(value, row, index) {
						return moment(value).format("YYYY-MM-DD HH:mm:ss");
					}
				}, {
					field: "id",
					title: "操作",
					align: "center",
					formatter: function(value, row, index) {
						return '<div class="buttons">' +
								'<a href="javascript:;" title="编辑" onclick="app.scopes.fileSystemScope.showRecord(\'' + value + '\')"><span class="fa fa-pencil"></span></a>' +
								'<a href="javascript:;" title="删除" onclick="app.scopes.fileSystemScope.deleteRecord(\'' + value + '\')"><span class="fa fa-trash"></span></a>' +
								'</div>';
					}
				}]
			});
		} else if ($routeParams["id"] !== "create") {
			var recordId = $routeParams["id"];
			$scope.showRecord(recordId);
		}
		
		app.scopes.fileSystemScope = {
			newRecord: $scope.newRecord,
			showRecord: $scope.showRecord,
			deleteRecord: $scope.deleteRecord,
			deleteRecords: $scope.deleteRecords
		};
});