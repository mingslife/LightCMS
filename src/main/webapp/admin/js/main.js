var app = angular.module("app", ["ngRoute"]);
app.scopes = {};
app.configurations = {
	singlePage: false
};
app.config(function($routeProvider) {
	$routeProvider
		.when("/", {
			templateUrl: "view/home.html",
			controller: "homeController"
		})
		.when("/menu", {
			templateUrl: "view/menu/list.html",
			controller: "menuController"
		})
		.when("/menu/:id", {
			templateUrl: "view/menu/form.html",
			controller: "menuController"
		})
		.when("/category", {
			templateUrl: "view/category/list.html",
			controller: "categoryController"
		})
		.when("/category/:id", {
			templateUrl: "view/category/form.html",
			controller: "categoryController"
		})
		.when("/article", {
			templateUrl: "view/article/list.html",
			controller: "articleController"
		})
		.when("/article/:id", {
			templateUrl: "view/article/form.html",
			controller: "articleController"
		});
});
app.service("service", function($http, $q) {
	var basePath = "../";
	this.basePath = basePath;
	this.ajax = function(method, url, data) {
		var defer = $q.defer();
		$http({
			method: method,
			url: (url.indexOf("http://") !== -1 || url.indexOf("https://") !== -1) ? url : basePath + url,
			data: data,
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			},
			cache: false
		}).success(function(data) {
			if (angular.isDefined(data.error)) {
				alert(data.error);
				defer.reject(data.error);
			} else {
				defer.resolve(data);
			}
		}).error(function(data) {
			defer.reject("请求错误，请检查网络后重试");
		});
		return defer.promise;
	};
});
app.service("mainService", function(service) {
	this.loadMenu = function() {
		return service.ajax("GET", "system/menu.do", null);
	};
});
app.controller("controller", function($scope, $location, mainService) {
	$scope.loadMenu = function() {
		mainService.loadMenu().then(function(data) {
			$scope.menus = data.menus;
		});
	};
	$scope.isCurrentMenu = function(url) {
		return url === "#" + $location.path();
	};
	console.info($location.path());
	
	$scope.loadMenu();
});

$(function() {
	if (window.bootbox) {
		bootbox.addLocale("zh_CN", {OK: "好", CONFIRM: "确认", CANCEL: "取消"});
		bootbox.setLocale("zh_CN");
	}
	if ($ && $.notify) {
		$.notifyDefaults({
			delay: 3000,
			z_index: 1080,
			mouse_over: "pause"
		});
	}
	
	if (window.Util) {
		Util.systemName = "轻博客";
	}
	
	$(document).on("click","ul.nav li.parent > a > span.icon", function() {
		$(this).find("em:first").toggleClass("glyphicon-minus");
	});
	$(".sidebar span.icon").find("em:first").addClass("glyphicon-plus");
	$(window).on("resize", function () {
		if ($(window).width() > 768) $("#sidebar-collapse").collapse("show");
	});
	$(window).on("resize", function () {
		if ($(window).width() <= 767) $("#sidebar-collapse").collapse("hide");
	});
});