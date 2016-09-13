<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
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
	    <link rel="stylesheet" href="css/custom.css">

	    <!-- js -->
	    <script src="js/jquery-2.1.3.min.js"></script>
	    <script src="js/bootstrap.min.js"></script>
	    <script src="js/pace.min.js"></script>
	    <script src="js/modernizr.custom.js"></script>
	</head>

	<body>
		<div class="container">	
			<header id="site-header">
				<div class="row">
					<div class="col-md-4 col-sm-5 col-xs-8">
						<div class="logo">
							<h1><a href="index.html">${application.brand}</a></h1>
						</div>
					</div><!-- col-md-4 -->
					<div class="col-md-8 col-sm-7 col-xs-4">
						<nav class="main-nav" role="navigation">
							<div class="navbar-header">
  								<button type="button" id="trigger-overlay" class="navbar-toggle">
    								<span class="ion-navicon"></span>
  								</button>
							</div>

							<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
  								<ul class="nav navbar-nav navbar-right">
    								<li class="cl-effect-11"><a href="index.html">首页</a></li>
    								<li class="cl-effect-11"><a href="full-width.html">博客</a></li>
    								<li class="cl-effect-11"><a href="about.html">关于</a></li>
    								<li class="cl-effect-11"><a href="contact.html">联络</a></li>
  								</ul>
							</div><!-- /.navbar-collapse -->
						</nav>
						<div id="header-search-box">
							<a id="search-menu" href="#"><span id="search-icon" class="ion-ios-search-strong"></span></a>
							<div id="search-form" class="search-form">
								<form role="search" method="get" id="searchform" action="#">
									<input type="search" placeholder="Search" required>
									<button type="submit"><span class="ion-ios-search-strong"></span></button>
								</form>				
							</div>
						</div>
					</div><!-- col-md-8 -->
				</div>
			</header>
		</div>

		<div class="content-body">
			<div class="container">
				<div class="row">
					<main class="col-md-12">
<c:forEach var="article" items="${articles}" varStatus="status">
						<article class="post post-${status.index}">
							<header class="entry-header">
								<h1 class="entry-title">
									<a href="single.html">${article.title}</a>
								</h1>
								<div class="entry-meta">
									<span class="post-category"><a href="#">${article.categoryName}</a></span>
			
									<span class="post-date"><a href="#"><time class="entry-date" datetime="${article.publishDateF2}">${article.publishDateF}</time></a></span>
			
									<span class="post-author"><a href="#">${article.authorName}</a></span>
			
									<span class="comments-link"><a href="#">${article.commentNumber}条评论</a></span>
								</div>
							</header>
							<div class="entry-content clearfix">
								<p>${article.summary}</p>
								<div class="read-more cl-effect-14">
									<a href="#" class="more-link">阅读全文 <span class="meta-nav">→</span></a>
								</div>
							</div>
						</article>
</c:forEach>
					</main>
				</div>
<c:if test="${totalPage > 1}">
				<div class="text-center pager">
					<form name="pagerForm" method="get" action="blog.do">
						<a href="blog.do?category=&month=&search=&keyword=&page=1">&laquo;</a>
						<input name="page" type="text" />
<c:forEach var="curPage" begin="${startPage}" end="${endPage}">
						<c:if test="${curPage == page}"><a class="active">${curPage}</a></c:if>
						<c:if test="${curPage != page}"><a href="blog.do?category=&month=&search=&keyword=&page=${curPage}">${curPage}</a></c:if>
</c:forEach>
						<a href="blog.do?category=&month=&search=&keyword=&page=${totalPage}">&raquo;</a>
<c:if test="${totalPage > 3}">
						<a class="more">&hellip;</a>
						<div class="more-blank"></div>
</c:if>
					</form>
				</div>
</c:if>
			</div>
		</div>
		<footer id="site-footer">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<p class="copyright">${application.copyright}</p>
					</div>
				</div>
			</div>
		</footer>

		<!-- Mobile Menu -->
		<div class="ming-overlay">
			<button type="button" class="overlay-close"><span class="ion-ios-close-empty"></span></button>
			<nav>
				<ul>
					<li><a href="index.html">首页</a></li>
					<li><a href="blog.html">博客</a></li>
					<li><a href="about.html">关于</a></li>
					<li><a href="contact.html">联络</a></li>
				</ul>
			</nav>
		</div>

		<script src="js/script.js"></script>

	</body>
</html>
