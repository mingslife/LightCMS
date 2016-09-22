<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/xml"%><?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<rss version="2.0">
	<channel>
		<title>${application.brand}</title>
		<link><%= basePath %></link>
		<description>RSS Feed</description>
<c:forEach var="article" items="${articles}" varStatus="status">
		<item>
			<title>${article.title}</title>
			<link><%= basePath %>article/${article.uuid}</link>
			<author>${article.authorName}</author>
			<category>${article.categoryName}</category>
			<pubDate>${article.publishDate}</pubDate>
			<description>${article.summary}</description>
		</item>
</c:forEach>
	</channel>
</rss>