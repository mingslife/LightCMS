<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%= basePath %>">

		<title>${application.brand}</title>

		<!-- meta -->
		<meta charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1">

	    <!-- css -->
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/ionicons.min.css">
		<link rel="stylesheet" href="css/pace.css">
	    <link rel="stylesheet" href="css/github-markdown.min.css">
	    <link rel="stylesheet" href="css/github.min.css">
	    <link rel="stylesheet" href="css/custom.css">

	    <!-- js -->
	    <script src="js/jquery-2.1.3.min.js"></script>
	    <script src="js/bootstrap.min.js"></script>
	    <script src="js/pace.min.js"></script>
	    <script src="js/modernizr.custom.js"></script>
	    <script src="js/highlight.min.js"></script>
	</head>

	<body id="single">
		<div class="container">	
<jsp:include page="../jsp/header.jsp" />
		</div>

		<div class="content-body">
			<div class="container">
				<div class="row">
					<main class="col-md-8">
						<article class="post post-1">
							<header class="entry-header">
								<h1 class="entry-title">${article.title}</h1>
								<div class="entry-meta">
									<span class="post-category"><a href="#">${article.categoryName}</a></span>
			
									<span class="post-date"><a href="#"><time class="entry-date" datetime="${article.publishDateF2}">${article.publishDateF}</time></a></span>
			
									<span class="post-author"><a href="#">${article.authorName}</a></span>
			
									<span class="comments-link"><a href="#">${article.commentNumber}条评论</a></span>
								</div>
							</header>
							<div class="entry-content clearfix markdown-body">
<c:if test="${article.password == ''}">${article.content}</c:if>
<c:if test="${article.password != ''}">
								<div class="col-md-12 lock-article">
									<p>该文章已加密，请输入密码：</p>
									<form class="lock-article-form" onsubmit="return false">
										<input type="password" id="lock-article-password" maxlength="6" required />
										<button onclick="loadLockArticle()">→</button>
									</form>
								</div>
</c:if>
							</div>
							<div class="article-info text-muted">
								<span>2016-9-25 00:42</span>
								<span>阅读(98)</span>
								<span>评论(0)</span>
							</div>
						</article>
						<div class="agree">
							<a href="javascript:agreeArticle();"><span class="ion-heart"></span> <span id="agree-number">0</span></a>
						</div>
						<div id="reply-list">
						</div>
						<form class="reply-form" onsubmit="return false">
							<input type="text" id="reply-name" placeholder="称呼，可不填" />
							<input type="email" id="reply-email" placeholder="邮箱，可不填" />
							<textarea id="reply-content" placeholder="评论内容" required></textarea>
							<button class="btn-send btn-5 btn-5b ion-checkmark btn-block" onclick="commentArticle()"><span>提交</span></button>
						</form>
					</main>
<jsp:include page="../jsp/aside.jsp" />
				</div>
			</div>
		</div>
<jsp:include page="../jsp/footer.jsp" />

<jsp:include page="../jsp/menu.jsp" />

		<script src="js/script.js"></script>

	</body>
</html>
