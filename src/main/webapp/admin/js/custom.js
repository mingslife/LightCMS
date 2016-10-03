$('#calendar').datepicker({
		});

!function ($) {
    $(document).on("click","ul.nav li.parent > a > span.icon", function(){          
        $(this).find('em:first').toggleClass("glyphicon-minus");      
    }); 
    $(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
}

(window.jQuery);
	$(window).on('resize', function () {
  if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
})
$(window).on('resize', function () {
  if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
})

var global = {};
global.toolbar = [
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
//		name: "strikethrough",
//		action: SimpleMDE.toggleStrikethrough,
//		className: "fa fa-strikethrough",
//		title: "删除线"
//	}, {
		name: "heading",
		action: SimpleMDE.toggleHeadingSmaller,
		className: "fa fa-header",
		title: "标题",
		default: true
	}, "|", {
//		name: "heading-smaller",
//		action: SimpleMDE.toggleHeadingSmaller,
//		className: "fa fa-header fa-header-x fa-header-smaller",
//		title: "Smaller Heading"
//	}, {
//		name: "heading-bigger",
//		action: SimpleMDE.toggleHeadingBigger,
//		className: "fa fa-header fa-header-x fa-header-bigger",
//		title: "Bigger Heading"
//	}, {
//		name: "heading-1",
//		action: SimpleMDE.toggleHeading1,
//		className: "fa fa-header fa-header-x fa-header-1",
//		title: "Big Heading"
//	}, {
//		name: "heading-2",
//		action: SimpleMDE.toggleHeading2,
//		className: "fa fa-header fa-header-x fa-header-2",
//		title: "Medium Heading"
//	}, {
//		name: "heading-3",
//		action: SimpleMDE.toggleHeading3,
//		className: "fa fa-header fa-header-x fa-header-3",
//		title: "Small Heading"
//	}, {
//		name: "separator-1"
//	}, {
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
//		name: "clean-block",
//		action: SimpleMDE.cleanBlock,
//		className: "fa fa-eraser fa-clean-block",
//		title: "清除样式"
//	}, {
//		name: "separator-2"
//	}, {
		name: "link",
		action: SimpleMDE.drawLink,
		className: "fa fa-link",
		title: "链接",
		default: true
	}, {
		name: "image",
		action: function(editor) {
			bootbox.dialog({
				title: "上传图片",
				message: '<input type="file" class="form-control" id="article-content-upload" />',
				buttons: {
					upload: {
						label: "确定",
						className: "btn-primary"
					},
					cancel: {
						label: "取消",
						className: "btn-default"
					}
				}
			});
			$("#article-content-upload").fileupload({
				url: "../upload/image.do",
				dataType: "json",
				add: function(event, data) {
					$("button.btn[data-bb-handler='upload']").click(function() {
						data.submit();
					});
				},
				done: function(event, data) {
					articleContentEditor.value(articleContentEditor.value() + "![" + data.result.url + "](" + data.result.url + ")");
				}
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
//		name: "horizontal-rule",
//		action: SimpleMDE.drawHorizontalRule,
//		className: "fa fa-minus",
//		title: "分隔线"
//	}, {
//		name: "separator-3"
//	}, {
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
//	}, {
//		name: "separator-4"
//	}, {
//		name: "guide",
//		action: "https://simplemde.com/markdown-guide",
//		className: "fa fa-question-circle",
//		title: "帮助",
//		default: true
	}, "|", {
//		name: "separator-5"
//	}, {
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