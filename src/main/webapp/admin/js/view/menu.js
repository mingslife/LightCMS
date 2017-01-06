app.service("menuService", function(service) {
	this.loadListUrl = service.basePath + "menus.do";
	this.showMenu = function(id) {
		return service.ajax("GET", "menus/{id}.do".replace("{id}", id), null);
	};
	this.saveMenu = function(menu) {
		return service.ajax("POST", "menus.do", $.param(menu));
	};
	this.updateMenu = function(id, menu) {
		return service.ajax("PUT", "menus/{id}.do".replace("{id}", id), $.param(menu));
	};
	this.deleteMenu = function(id) {
		return service.ajax("DELETE", "menus/{id}.do".replace("{id}", id), null);
	};
	this.deleteMenus = function(ids) {
		return service.ajax("POST", "menus/deletes.do", Util.arrayParam("ids[]", ids));
	};
	this.loadAll = function() {
		return service.ajax("GET", "menus/all.do", null);
	};
});
app.controller("menuController", function($scope, $routeParams, menuService) {
	Util.setTitle("菜单管理");
	
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
		window.location.hash = "#/menu";
	};
	$scope.newRecord = function() {
		if (app.configurations && app.configurations.singlePage) {
			window.location.hash = "#/menu/create";
		} else {
			$("#menu-modal").modal("show");
		}
	};
	$scope.saveRecord = function() {
		$scope.lock = true;
		var menu = {
			menu: $.trim($scope.menu.name),
			url: $.trim($scope.menu.url),
			isVisible: $scope.menu.isVisible,
			position: $scope.menu.position,
			icon: $.trim($scope.menu.icon),
			parentId: null // TODO
		};
		var saveFunction = $scope.menu.id == null ? menuService.saveMenu(menu) : menuService.updateMenu($scope.menu.id, menu);
		saveFunction.then(function(data) {
			$scope.lock = false;
			$.notify("保存成功！", {type: "success"});
			if ($("#menu-table").length > 0) {
				$("#menu-modal").modal("hide");
				$("#menu-table").bootstrapTable("refresh");
			} else {
				$scope.backToParent();
			}
		}, function(data) {
			$scope.lock = false;
		});
	};
	$scope.showRecord = function(id) {
		menuService.showMenu(id).then(function(data) {
			$scope.menu = data;
			if (app.configurations && app.configurations.singlePage) {
				window.location.hash = "#/menu/" + id;
			} else {
				$("#menu-modal").modal("show");
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
					menuService.deleteMenu(id).then(function(data) {
						$.notify("删除成功！", {type: "success"});
						$("#menu-table").bootstrapTable("refresh");
					});
				}
			}
		});
	};
	$scope.deleteRecords = function() {
		var records = $("#menu-table").bootstrapTable("getSelections");
		if (records.length > 0) {
			var ids = records.map(function(item) {
				return item.id;
			});
			bootbox.confirm({
				className: "modal-warning",
				title: '<span class="fa fa-warning"></span> 警告',
				message: "确认删除这" + ids.length + "条数据吗？",
				callback: function(result) {
					menuService.deleteMenus(ids).then(function(data) {
						$.notify("删除成功！", {type: "success"});
						$("#menu-table").bootstrapTable("refresh");
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
		$("#menu-modal").on("hidden.bs.modal", function(e) {
			$scope.menu = {};
			$scope.$apply();
		});
		$("#menu-table").bootstrapTable({
			url: menuService.loadListUrl,
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
			toolbar: "#menu-toolbar",
			columns: [{
				checkbox: true
			}, {
				field: "name",
				title: "名称"
			}, {
				field: "url",
				title: "链接"
			}, {
				field: "isVisible",
				title: "是否可见",
				formatter: function(value, row, index) {
					return Util.enumFormatter($scope.defaults.IS_VISIBLE, value);
				}
			}, {
				field: "position",
				title: "位置"
			}, {
				field: "icon",
				title: "图标",
				formatter: function(value, row, index) {
					return '<span class="fa fa-' + value + '"></span>';
				}
			}, {
				field: "id",
				title: "操作",
				align: "center",
				formatter: function(value, row, index) {
					return '<div class="buttons">' +
							'<a href="javascript:;" title="编辑" onclick="app.scopes.menuScope.showRecord(\'' + value + '\')"><span class="fa fa-pencil"></span></a>' +
							'<a href="javascript:;" title="删除" onclick="app.scopes.menuScope.deleteRecord(\'' + value + '\')"><span class="fa fa-trash"></span></a>' +
							'</div>';
				}
			}]
		});
	} else if ($routeParams["id"] !== "create") {
		var recordId = $routeParams["id"];
		$scope.showRecord(recordId);
	}
	
	app.scopes.menuScope = {
		newRecord: $scope.newRecord,
		showRecord: $scope.showRecord,
		deleteRecord: $scope.deleteRecord,
		deleteRecords: $scope.deleteRecords
	};
});