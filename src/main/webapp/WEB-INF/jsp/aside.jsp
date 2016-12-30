<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
					<aside class="col-md-4">
						<div class="widget widget-recent-posts">
							<h3 class="widget-title">最近发布</h3>
							<ul>
<c:forEach var="article" items="${articlesForMenu}" varStatus="status">
								<li><a href="article/${article.uuid}">${article.title}</a></li>
</c:forEach>
							</ul>
						</div>

						<div class="widget widget-archives">
							<h3 class="widget-title">归档</h3>
							<ul>
<c:forEach var="archive" items="${archivesForMenu}" varStatus="status">
								<li><a href="#">${archive.monthF}</a></li>
</c:forEach>
							</ul>
						</div>

						<div class="widget widget-category">
							<h3 class="widget-title">分类</h3>
							<ul>
<c:forEach var="category" items="${categoriesForMenu}" varStatus="status">
								<li><a href="#">${category.categoryName}</a></li>
</c:forEach>
							</ul>
						</div>
					</aside>