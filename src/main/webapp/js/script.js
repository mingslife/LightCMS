var searchvisible = 0;

$("#search-menu").click(function(e){ 
    //This stops the page scrolling to the top on a # link.
    e.preventDefault();

    var val = $('#search-icon');
    if(val.hasClass('ion-ios-search-strong')){
        val.addClass('ion-ios-close-empty');
        val.removeClass('ion-ios-search-strong');
    }
    else{
         val.removeClass('ion-ios-close-empty');
        val.addClass('ion-ios-search-strong');
    }
    
    
    if (searchvisible ===0) {
        //Search is currently hidden. Slide down and show it.
        $("#search-form").slideDown(200);
        $("#s").focus(); //Set focus on the search input field.
        searchvisible = 1; //Set search visible flag to visible.
    } 

    else {
        //Search is currently showing. Slide it back up and hide it.
        $("#search-form").slideUp(200);
        searchvisible = 0;
    }
});

// Ming Custom Script
(function() {
	// Ming Mobile Menu
	function toggleMingOverlay() {
		if ($('div.ming-overlay').hasClass('open')) {
			$('div.ming-overlay').fadeOut().removeClass('open');
			$('body').removeClass('no-scroll');
		} else {
			$('body').addClass('no-scroll');
			$('div.ming-overlay').addClass('open').fadeIn();
		}
	}
	$('#trigger-overlay').click(toggleMingOverlay);
	$('button.overlay-close').click(toggleMingOverlay);
	
	// Ming Pager
	$(".pager .more").click(function() {
		var thisElement = $(this);
		var inputElement = thisElement.parent().find("input[type='text']");
		var blankElement = thisElement.parent().find(".more-blank");
		if (thisElement.attr("status") === "on") {
			if ($.trim(inputElement.val())) {
				$(this).parent().submit();
			} else {
				inputElement.css({opacity: "0"});
				setTimeout(function() {
					inputElement.css("display", "none").val("");
				}, 500);
				blankElement.css("width", "36px");
				thisElement.text("…").css("margin-left", "0").attr("status", "off");
			}
		} else {
//			var offset = thisElement.offset();
//			var left = offset.left;
//			var top = offset.top;
//			inputElement.css({left: (left - 120) + "px", top: top + "px"}).show();
			inputElement.css({display: "inline-block", opacity: "1"});
			blankElement.css("width", "0");
//			thisElement.text("→").css("margin-left", "-80px").attr("status", "on");
			thisElement.text("×").css("margin-left", "-80px").attr("status", "on");
		}
	});
	$(".pager input[type='text']").on("keyup", function() {
		if ($.trim($(this).val())) {
			$(".pager .more").text("→");
		} else {
			$(".pager .more").text("×");
		}
	});
	
	// Twitter Emoji
	//twemoji.parse(document.body, {size: 72});
	
	// SyntaxHighlighter
//	SyntaxHighlighter.all();
})();

// Ming Contact
function contact() {
	$.ajax({
		url: "contact.action",
		type: "POST",
		dataType: "json",
		data: {
			name: $.trim($("#contact-name").val()),
			email: $.trim($("#contact-email").val()),
			subject: $.trim($("#contact-subject").val()),
			message: $.trim($("#contact-message").val())
		},
		beforeSend: function() {},
		complete: function() {},
		success: function(data) {
			if (data.result === "success") {
				alert("留言成功");
				window.location.reload();
			} else {
				alert(data.result);
			}
		},
		error: function() {
			alert("ERROR");
		}
	});
}

// Ming Lock
var articlePassword = null;
function loadLockArticle() {
	$.ajax({
		url: "loadLockArticle.action",
		type: "POST",
		dataType: "json",
		data: {
			id: articleId,
			password: $("#lock-article-password").val()
		},
		beforeSend: function() {},
		complete: function() {},
		success: function(data) {
			if (data.content != null) {
				articlePassword = $("#lock-article-password").val();
				$(".entry-content").html(data.content);
				$(".reply-form").show();
				SyntaxHighlighter.highlight(); // 解决ajax加载文档，代码无法高亮显示
				$(window).resize(); // 修复代码过长显示问题
				scrollTo(0, 0);
				loadLockArticleComments();
			} else {
				alert(data.result);
			}
		},
		error: function() {
			alert("ERROR");
		}
	});
}
function loadLockArticleComments() {
	$.ajax({
		url: "loadLockArticleComments.action",
		type: "POST",
		dataType: "json",
		data: {
			id: articleId,
			password: articlePassword
		},
		beforeSend: function() {},
		complete: function() {},
		success: function(data) {
			var html = "";
			for (var i = 0, length = data.length; i < length; i++) {
				var comment = data[i];
				html += '<section class="reply"><div class="reply-info"><span class="reply-name">' + comment.name + '</span><span class="reply-time">' + formatCustomDate(comment.createTime) + '</span></div><div class="reply-content">' + comment.content + '</div></section>';
			}
			$("#reply-list").html(html);
		},
		error: function() {
			alert("ERROR");
		}
	});
}
function formatCustomDate(date) {
	try {
		var str = String(date);
		return str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8) + " " + str.substring(8, 10) + ":" + str.substring(10, 12);
	} catch (e) {
		return "未知时间";
	}
}

// Ming Agree
function agreeArticle() {
	$.ajax({
		url: "agreeArticle.action",
		type: "POST",
		dataType: "json",
		data: {
			id: articleId,
			password: articlePassword
		},
		beforeSend: function() {},
		complete: function() {},
		success: function(data) {
			if (data.count != null) {
				$("#agree-number").html(data.count);
			} else {
				alert(data.result);
			}
		},
		error: function() {
			alert("ERROR");
		}
	});
}

// Ming Comment
function commentArticle() {
	$.ajax({
		url: "commentArticle.action",
		type: "POST",
		dataType: "json",
		data: {
			id: articleId,
			name: $.trim($("#reply-name").val()),
			email: $.trim($("#reply-email").val()),
			content: $.trim($("#reply-content").val()),
			password: articlePassword
		},
		beforeSend: function() {},
		complete: function() {},
		success: function(data) {
			if (data.result === "success") {
				alert("评论成功");
				window.location.reload();
			} else {
				alert(data.result);
			}
		},
		error: function() {
			alert("ERROR");
		}
	});
}

if (window.hljs) {
	$(document).ready(function() {
		$("pre code").each(function(i, block) {
			hljs.highlightBlock(block);
		});
	});
}
