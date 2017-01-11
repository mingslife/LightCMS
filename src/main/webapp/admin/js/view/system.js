app.service("systemService", function(service) {});
app.controller("systemController", function($scope, $location, systemService) {
	switch ($location.path()) {
	case "/developer": Util.setTitle("开发者模式"); break;
	}
});