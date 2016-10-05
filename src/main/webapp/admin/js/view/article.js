var articleScope;
app.service("articleService", function(service) {
	this.showRecords = function() {
		return service.ajax("GET", service.basePath + "articles.do", null);
	};
	this.showRecord = function(id) {
		return service.ajax("GET", service.basePath + "articles/{id}.do".replace("{id}", id), null);
	};
	this.createRecord = function(record) {
		return service.ajax("POST", service.basePath + "articles.do", record);
	};
	this.updateRecord = function(id, record) {
		return service.ajax("PUT", service.basePath + "articles/{id}.do".replace("{id}", id), record);
	};
	this.deleteRecord = function(id) {
		return service.ajax("DELETE", service.basePath + "articles/{id}.do".replace("{id}", id), null);
	};
});
app.controller("articleController", function($scope, $routeParams, articleService) {
	console.info($routeParams);
	var articleContentEditor = new SimpleMDE({
		autoDownloadFontAwesome: false,
		element: document.getElementById("article-content"),
//		hideIcons: ["guide"],
//		showIcons: ["code", "table"],
		spellChecker: false,
		toolbar: [
	      	{
	    		name: "bold",
	    		action: SimpleMDE.toggleBold,
	    		className: "fa fa-bold",
	    		title: "加粗",
	    		default: true
	    	}, {
	    		name: "italic",
	    		action: SimpleMDE.toggleItalic,
	    		className: "fa fa-italic",
	    		title: "倾斜",
	    		default: true
	    	}, {
	    		name: "heading",
	    		action: SimpleMDE.toggleHeadingSmaller,
	    		className: "fa fa-header",
	    		title: "标题",
	    		default: true
	    	}, "|", {
	    		name: "code",
	    		action: SimpleMDE.toggleCodeBlock,
	    		className: "fa fa-code",
	    		title: "代码"
	    	}, {
	    		name: "quote",
	    		action: SimpleMDE.toggleBlockquote,
	    		className: "fa fa-quote-left",
	    		title: "引用",
	    		default: true
	    	}, {
	    		name: "unordered-list",
	    		action: SimpleMDE.toggleUnorderedList,
	    		className: "fa fa-list-ul",
	    		title: "无序列表",
	    		default: true
	    	}, {
	    		name: "ordered-list",
	    		action: SimpleMDE.toggleOrderedList,
	    		className: "fa fa-list-ol",
	    		title: "有序列表",
	    		default: true
	    	}, "|", {
	    		name: "link",
	    		action: SimpleMDE.drawLink,
	    		className: "fa fa-link",
	    		title: "链接",
	    		default: true
	    	}, {
	    		name: "image",
	    		action: function(editor) {
	    			var uploadDialog = bootbox.dialog({
	    				title: "上传图片",
	    				message: '<span class="btn btn-primary btn-block fileinput-button" id="fileinput-button">' +
	    							'<span>选择图片</span>' +
	    							'<input type="file" id="article-content-upload" name="file" accept="image/*" title="选择图片" />' +
	    						'</span>' +
	    						'<div class="progress" id="upload-progress" style="display:none;">' +
	    							'<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">' +
	    								'<span class="sr-only">完成0%</span>' +
	    							'</div>' +
	    						'</div>',
	    				buttons: {
	    					upload: {
	    						label: "确定",
	    						className: "btn-primary disabled",
	    						callback: function() {
	    							return false;
	    						}
	    					},
	    					cancel: {
	    						label: "取消",
	    						className: "btn-default"
	    					}
	    				},
	    				callback: function(result) {
	    					console.info(result);
	    				}
	    			});
	    			$("#article-content-upload").fileupload({
	    				url: "../upload/image.do",
	    				dataType: "json",
	    				autoUpload: false,
//	    				acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
//	    				maxFileSize: 81920,
//	    				disableImageResize: /Android(?!.*Chrome)|Opera/.test(window.navigator.userAgent),
//	    				previewMaxWidth: 100,
//	    				previewMaxHeight: 100,
//	    				previewCrop: true,
	    				add: function(e, data) {
	    					console.info(data);
	    					$("button.btn[data-bb-handler='upload']").removeClass("disabled").unbind("click").click(function() {
	    						$(this).addClass("disabled");
	    						$("#fileinput-button").hide();
	    						$("#upload-progress").show();
	    						data.submit();
	    					});
	    				},
	    				done: function(e, data) {
	    					articleContentEditor.value(articleContentEditor.value() + "![" + data.result.name + "](" + data.result.url + ")");
	    					uploadDialog.modal("hide");
	    				},
	    				progressall: function(e, data) {
	    					var progress = parseInt(data.loaded / data.total * 100, 10);
		    				$("#upload-progress .progress-bar").attr("aria-valuenow", progress).css("width", progress + "%").find("span.sr-only").text("完成" + progress + "%");
	    				}
//	    			}).on("fileuploadprocessalways", function(e, data) {
//	    				console.info(data);
//	    				var file = data.files[0];
//	    				console.info(file.preview);
//	    				$(".fileinput-button").after(file.preview);
//	    				uploadInput.parent().hide();
//	    			}).on("fileuploadprogressall", function(e, data) {
//	    				var progress = parseInt(data.loaded / data.total * 100, 10);
//	    				$("#upload-progress .progress-bar").attr("aria-valuenow", progress).css("width", progress + "%").find("span.sr-only").text("完成" + progress + "%");
	    			});
	    		},
	    		className: "fa fa-picture-o",
	    		title: "图片",
	    		default: true
	    	}, {
	    		name: "table",
	    		action: SimpleMDE.drawTable,
	    		className: "fa fa-table",
	    		title: "表格"
	    	}, "|", {
	    		name: "preview",
	    		action: SimpleMDE.togglePreview,
	    		className: "fa fa-eye no-disable",
	    		title: "预览",
	    		default: true
	    	}, {
	    		name: "side-by-side",
	    		action: SimpleMDE.toggleSideBySide,
	    		className: "fa fa-columns no-disable no-mobile",
	    		title: "实时预览",
	    		default: true
	    	}, {
	    		name: "fullscreen",
	    		action: SimpleMDE.toggleFullScreen,
	    		className: "fa fa-arrows-alt no-disable no-mobile",
	    		title: "全屏",
	    		default: true
	    	}, "|", {
	    		name: "undo",
	    		action: SimpleMDE.undo,
	    		className: "fa fa-undo no-disable",
	    		title: "撤销"
	    	}, {
	    		name: "redo",
	    		action: SimpleMDE.redo,
	    		className: "fa fa-repeat no-disable",
	    		title: "重做"
	    	}
	    ],
	    renderingConfig: {
	    	singleLineBreaks: false,
	    	codeSyntaxHighlighting: true,
	    },
//		toolbar: global.toolbar
	});
	var recordId = $routeParams.id;
	if (recordId) {
		$scope.baseSelectDatas = [{
			value: true,
			name: "是"
		}, {
			value: false,
			name: "否"
		}];
		articleService.showRecord(recordId).then(function(data) {
			console.info(data);
			$scope.article = data;
			articleContentEditor.value(data.markdown);
		});
	}
	$("#article-table").bootstrapTable({
		url: "../articles.do",
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
				curPage: params.offset / params.limit + 1,
				limit: params.limit
			};
			return _params;
		},
//		toolbar: "#addCategory",
		columns: [{
			field: "title",
			title: "标题"
		}, {
			field: "authorId",
			title: "作者"
		}, {
			field: "categoryId",
			title: "分类"
		}, {
			field: "publishDate",
			title: "发布时间"
		}, {
			field: "readNumber",
			title: "阅读数"
		}, {
			field: "commentNumber",
			title: "评论数"
		}, {
			field: "isVisible",
			title: "是否显示"
		}, {
			field: "canComment",
			title: "开启评论"
		}, {
			field: "hasPassword",
			title: "是否加密"
		}, {
			field: "onTop",
			title: "是否置顶"
		}, {
			field: "id",
			title: "操作",
			align: "center",
			formatter: function(value, row, index) {
				return '<div class="btn-group">' +
						'<button type="button" class="btn btn-xs btn-primary"><span class="glyphicon glyphicon-edit"></span> 编辑</button>' +
						'<button type="button" class="btn btn-xs btn-danger" onclick="articleScope.deleteRecord(\'' + value + '\')"><span class="glyphicon glyphicon-trash"></span> 删除</button>' +
						'</div>';
			}
		}]
	});
	
	$scope.showRecord = function(id) {
		articleService.showRecord(id).then(function(data) {
			console.info(data);
		});
	};
	$scope.updateRecord = function(id) {
		articleService.updateRecord(id, record).then(function(data) {
			console.info(data);
		});
	};
	$scope.deleteRecord = function(id) {
		swal({
			title: "",
			text: "确认删除？",
			type: "warning",
			showCancelButton: true,
			showLoaderOnConfirm: true
		}, function() {
			articleService.deleteRecord(id).then(function(data) {
				swal("", "删除成功", "success");
			});
		});
	};
	$scope.formatRecord = function(record) {
		var markdown = articleContentEditor.value();
		return {
			title: record.title,
			authorId: record.authorId,
			categoryId: record.categoryId,
			isVisible: record.isVisible,
			canComment: record.canComment,
			password: record.password,
			cover: record.cover,
			keywords: record.keywords,
			description: record.description,
			content: articleContentEditor.markdown(markdown),
			markdown: markdown,
			onTop: record.onTop
		};
	};
	$scope.save = function() {
		console.info($scope.article);
		var record = $scope.formatRecord($scope.article);
		if (recordId) {
			articleService.updateRecord(recordId, record).then(function(data) {
				console.info(data);
			});
		} else {
			articleService.saveRecord(record).then(function(data) {
				console.info(data);
			});
		}
	};
	$scope.cancel = function() {};
	
	articleScope = $scope;
});
