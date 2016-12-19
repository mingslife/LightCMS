var Patch = {
	SimpleMDE: {
		toolbar: function(editor) {
			return [
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
		    				message: '<span class="btn btn-primary btn-block fileinput-button" id="simplemde-image-upload-button">' +
		    							'<span id="simplemde-image-upload-text">选择图片</span>' +
		    							'<input type="file" id="simplemde-image-upload-file" name="file" accept="image/*" title="选择图片" />' +
		    						'</span>' +
		    						'<div class="progress" id="simplemde-image-upload-progress" style="display:none;">' +
		    							'<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">' +
		    								'<span class="sr-only">完成0%</span>' +
		    							'</div>' +
		    						'</div>' +
		    						'<div class="form-group checkbox">' +
		    							'<label>' +
		    								'<input type="checkbox" id="simplemde-image-upload-is-source" /> 原图' +
		    							'</label>' +
		    						'</div>',
		    				buttons: {
		    					cancel: {
		    						label: "取消",
		    						className: "btn-default"
		    					},
		    					upload: {
		    						label: "上传",
		    						className: "btn-primary disabled",
		    						callback: function() {
		    							return false;
		    						}
		    					}
		    				},
		    				callback: function(result) {
		    					console.info(result);
		    				}
		    			});
		    			uploadDialog.on("hidden.bs.modal", function(e) {
		    				$("body").addClass("modal-open"); // 修复关闭上传模态框之后，上一级模态框无法滚动的bug
		    			});
		    			setTimeout(function() {
		    				$("#simplemde-image-upload-file").fileupload({
			    				url: "../upload/image.do",
			    				dataType: "json",
			    				autoUpload: false,
			    				add: function(e, data) {
			    					var file = data.files[0];
			    					if (file.type.indexOf("image/") !== 0) {
			    						bootbox.alert({
			    							className: "modal-danger",
			    							title: '<span class="fa fa-remove"></span> 错误',
			    							message: "不支持的图片格式！"
			    						});
			    						return;
			    					} else if (file.size > 1024 * 1024 * 10) {
			    						bootbox.alert({
			    							className: "modal-danger",
			    							title: '<span class="fa fa-remove"></span> 错误',
			    							message: "图片大小大于10M！"
			    						});
			    						return;
			    					}
			    					
			    					var text = file.name + " (" + Util.bytesToSize(file.size) + ")";
			    					$("#simplemde-image-upload-text").text(text);
			    					console.info(data);
			    					$("button.btn[data-bb-handler='upload']").removeClass("disabled").unbind("click").click(function() {
			    						$(this).addClass("disabled");
			    						$("#simplemde-image-upload-button").hide();
			    						$("#simplemde-image-upload-progress").show();
			    						data.submit();
			    					});
			    				},
			    				done: function(e, data) {
			    					editor.value(editor.value() + "![" + data.result.name + "](" + data.result.url + ")");
			    					uploadDialog.modal("hide");
			    				},
			    				fail: function(e, data) {
			    					setTimeout(function() {
			    						var xhr = data.jqXHR;
			    						var message = xhr.responseJSON && xhr.responseJSON.error ? xhr.responseJSON.error : "保存失败";
			    						if ($.notify) {
			    							$.notify(message, {type: "danger"});
			    						} else {
			    							alert(message);
			    						}
			    					}, 0);
			    				},
			    				progressall: function(e, data) {
			    					var progress = parseInt(data.loaded / data.total * 100, 10);
				    				$("#simplemde-image-upload-progress .progress-bar").attr("aria-valuenow", progress).css("width", progress + "%").find("span.sr-only").text("完成" + progress + "%");
			    				}
			    			}).bind("fileuploadsubmit", function(e, data) {
			    				data.formData = {
			    					compress: !$("#simplemde-image-upload-is-source").is(":checked")
			    				};
			    			});
		    			}, 0);
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
		    ];
		}
	}
};