var app = angular.module("app", []);
app.controller("loginController", function($scope) {
	$scope.lock = false;
	$scope.login = function() {
		$scope.lock = true;
		alert($scope.username);
		alert(md5($scope.password));
	};
});