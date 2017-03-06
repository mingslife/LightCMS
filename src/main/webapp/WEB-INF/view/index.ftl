<#escape x as x?html>
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

		<link rel="alternate" type="application/rss+xml" href="rss.xml" title="Ming's Life">

	    <!-- js -->
	    <script src="js/jquery-2.1.3.min.js"></script>
	    <script src="js/bootstrap.min.js"></script>
	    <script src="js/pace.min.js"></script>
	    <script src="js/modernizr.custom.js"></script>
	</head>

	<body>
		<div class="container">	
<#include "header.ftl">
		</div>

		<div class="content-body">
			<div class="container">
				<div class="row">
					<main class="col-md-8">
<#list articles as article>
						<article class="post post-${article_index}">
							<header class="entry-header">
								<h1 class="entry-title">
									<a href="article/${article.uuid}">${article.title}</a>
								</h1>
								<div class="entry-meta">
									<span class="post-category"><a href="#">${article.categoryName}</a></span>
									<span class="post-date"><a href="#"><time class="entry-date" datetime="${article.publishDateF2}">${article.publishDateF}</time></a></span>
									<span class="post-author"><a href="#">${article.authorName}</a></span>
									<span class="comments-link"><a href="#">${article.commentNumber}条评论</a></span>
								</div>
							</header>
							<div class="entry-content clearfix">
								<p><#noescape>${article.summary}</#noescape></p>
								<div class="read-more cl-effect-14">
									<a href="article/${article.uuid}" class="more-link">阅读全文 <span class="meta-nav">→</span></a>
								</div>
							</div>
						</article>
</#list>
					</main>
<#include "aside.ftl">
				</div>
			</div>
		</div>
<#include "footer.ftl">

<#include "menu.ftl">

		<script src="js/script.js"></script>

	</body>
</html>
</#escape>