app.service("articleService", function(service) {
	this.showArticles = function() {
		return service.ajax("GET", "../articles.do", "");
	};
	this.showArticle = function(id) {
		return service.ajax("GET", "../articles/{id}.do".replace("{id}", id), "");
	};
	this.createArticle = function(record) {
		return service.ajax("POST", "../articles.do", JSON.stringify(record));
	};
	this.updateArticle = function(id, record) {
		return service.ajax("PUT", "../articles/{id}.do".replace("{id}", id), JSON.stringify(record));
	};
	this.deleteArticle = function(id) {
		return service.ajax("DELETE", "../articles/{id}.do".replace("{id}", id), "id=" + id);
	};
});
app.controller("articleController", function($scope, articleService) {
	articleService.showArticles().then(function(data) {
		console.info(data);
	});
	articleService.showArticle(1).then(function(data) {
		console.info(data);
	});
});