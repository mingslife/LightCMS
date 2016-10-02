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
		name: "strikethrough",
		action: SimpleMDE.toggleStrikethrough,
		className: "fa fa-strikethrough",
		title: "Strikethrough"
	}, {
		name: "heading",
		action: SimpleMDE.toggleHeadingSmaller,
		className: "fa fa-header",
		title: "Heading",
		default: true
	}, {
		name: "heading-smaller",
		action: SimpleMDE.toggleHeadingSmaller,
		className: "fa fa-header fa-header-x fa-header-smaller",
		title: "Smaller Heading"
	}, {
		name: "heading-bigger",
		action: SimpleMDE.toggleHeadingBigger,
		className: "fa fa-header fa-header-x fa-header-bigger",
		title: "Bigger Heading"
	}, {
		name: "heading-1",
		action: SimpleMDE.toggleHeading1,
		className: "fa fa-header fa-header-x fa-header-1",
		title: "Big Heading"
	}, {
		name: "heading-2",
		action: SimpleMDE.toggleHeading2,
		className: "fa fa-header fa-header-x fa-header-2",
		title: "Medium Heading"
	}, {
		name: "heading-3",
		action: SimpleMDE.toggleHeading3,
		className: "fa fa-header fa-header-x fa-header-3",
		title: "Small Heading"
	}, {
//		name: "separator-1"
//	}, {
		name: "code",
		action: SimpleMDE.toggleCodeBlock,
		className: "fa fa-code",
		title: "Code"
	}, {
		name: "quote",
		action: SimpleMDE.toggleBlockquote,
		className: "fa fa-quote-left",
		title: "Quote",
		default: true
	}, {
		name: "unordered-list",
		action: SimpleMDE.toggleUnorderedList,
		className: "fa fa-list-ul",
		title: "Generic List",
		default: true
	}, {
		name: "ordered-list",
		action: SimpleMDE.toggleOrderedList,
		className: "fa fa-list-ol",
		title: "Numbered List",
		default: true
	}, {
		name: "clean-block",
		action: SimpleMDE.cleanBlock,
		className: "fa fa-eraser fa-clean-block",
		title: "Clean block"
	}, {
//		name: "separator-2"
//	}, {
		name: "link",
		action: SimpleMDE.drawLink,
		className: "fa fa-link",
		title: "Create Link",
		default: true
	}, {
		name: "image",
		action: SimpleMDE.drawImage,
		className: "fa fa-picture-o",
		title: "Insert Image",
		default: true
	}, {
		name: "table",
		action: SimpleMDE.drawTable,
		className: "fa fa-table",
		title: "Insert Table"
	}, {
		name: "horizontal-rule",
		action: SimpleMDE.drawHorizontalRule,
		className: "fa fa-minus",
		title: "Insert Horizontal Line"
	}, {
//		name: "separator-3"
//	}, {
		name: "preview",
		action: SimpleMDE.togglePreview,
		className: "fa fa-eye no-disable",
		title: "Toggle Preview",
		default: true
	}, {
		name: "side-by-side",
		action: SimpleMDE.toggleSideBySide,
		className: "fa fa-columns no-disable no-mobile",
		title: "Toggle Side by Side",
		default: true
	}, {
		name: "fullscreen",
		action: SimpleMDE.toggleFullScreen,
		className: "fa fa-arrows-alt no-disable no-mobile",
		title: "Toggle Fullscreen",
		default: true
	}, {
//		name: "separator-4"
//	}, {
		name: "guide",
		action: "https://simplemde.com/markdown-guide",
		className: "fa fa-question-circle",
		title: "Markdown Guide",
		default: true
	}, {
//		name: "separator-5"
//	}, {
		name: "undo",
		action: SimpleMDE.undo,
		className: "fa fa-undo no-disable",
		title: "Undo"
	}, {
		name: "redo",
		action: SimpleMDE.redo,
		className: "fa fa-repeat no-disable",
		title: "Redo"
	}
];