var app = angular.module("app", []);
app.service("service", function($http, $q) {
	this.ajax = function(method, url, data) {
		if (data != null && (method.toUpperCase() === "GET" || method.toUpperCase() === "DELETE")) {
			url = url + "?" + data;
			data = null;
		}
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
				if ($.notify) {
					$.notify(data.error, {
						type: "danger"
					});
				} else {
					alert(data.error);
				}
				defer.reject(data.error);
			} else {
				defer.resolve(data);
			}
		}).error(function(data) {
			console.log(data);
			var errorMessage = "请求错误，请检查网络后重试！";
			if (angular.isDefined(data.error)) {
				errorMessage = data.error;
			}
			if ($.notify) {
				$.notify(errorMessage, {
					type: "danger"
				});
			} else {
				alert(data.error);
			}
			defer.reject(errorMessage);
		});
		return defer.promise;
	};
});
app.service("loginService", function(service) {
	this.login = function(username, password) {
		return service.ajax("POST", "../system/login.do", "username=" + username + "&password=" + md5(password));
	};
	this.test = function() {
		return service.ajax("POST", "../system/test.do", null);
	};
});
app.controller("loginController", function($scope, $location, loginService) {
	console.info($location.search());
	$scope.lock = false;
	$scope.login = function() {
		$scope.lock = true;
		loginService.login($scope.username, $scope.password).then(function(data) {
			console.info(data);
			$scope.lock = false;
		}, function() {
			$scope.lock = false;
		});
	};
	$scope.test = function() {
		loginService.test().then(function(data) {
			console.info(data);
		});
	};
	
	$scope.test();
});