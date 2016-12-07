var app = angular.module("app", ["ngRoute"]);
app.scopes = {};
app.config(function($routeProvider) {
	$routeProvider
		.when("/", {
			templateUrl: "view/home.html",
			controller: "homeController"
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
	this.basePath = "../";
	this.ajax = function(method, url, data) {
		var defer = $q.defer();
		$http({
			method: method,
			url: url,
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
app.controller("controller", function($scope) {});

!function ($) {
    $(document).on("click","ul.nav li.parent > a > span.icon", function(){          
        $(this).find("em:first").toggleClass("glyphicon-minus");      
    }); 
    $(".sidebar span.icon").find("em:first").addClass("glyphicon-plus");
}(window.jQuery);

$(window).on("resize", function () {
  if ($(window).width() > 768) $("#sidebar-collapse").collapse("show");
});
$(window).on("resize", function () {
  if ($(window).width() <= 767) $("#sidebar-collapse").collapse("hide");
});