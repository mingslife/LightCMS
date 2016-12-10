var Patch = {
	SimpleMDE: {
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
	    ]
	}
};