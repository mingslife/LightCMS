var app = angular.module("app", ["ngRoute"]);
app.config(function($routeProvider) {
	$routeProvider
		.when("/", {
			templateUrl: "view/home.html",
			controller: "homeController"
		})
//		.when("/article", {
//			templateUrl: "view/article.html",
//			controller: "articleController"
//		})
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
//			headers: {
//				"Content-Type": "application/x-www-form-urlencoded"
//			},
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

$(document).ready(function() {
	$(".modal").on("show.bs.modal", function(event) {
		$(".navbar").addClass("modal-mask-blur");
		$(".sidebar").addClass("modal-mask-blur");
		$(".main").addClass("modal-mask-blur");
	});
	$(".modal").on("hidden.bs.modal", function(event) {
		$(".navbar").removeClass("modal-mask-blur");
		$(".sidebar").removeClass("modal-mask-blur");
		$(".main").removeClass("modal-mask-blur");
	});
});