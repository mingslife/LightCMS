<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/xml"%><?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<rss version="2.0" xmlns:content="http://purl.org/rss/1.0/modules/content/">
	<channel>
		<title>${application.brand}</title>
		<link><%= basePath %></link>
		<description>${application.description}</description>
		<lastBuildDate>${lastBuildDate}</lastBuildDate>
		<language>${application.language}</language>
<c:forEach var="article" items="${articles}" varStatus="status">
		<item>
			<title>${article.title}</title>
			<link><%= basePath %>article/${article.uuid}</link>
			<author>${article.authorName}</author>
			<category>${article.categoryName}</category>
			<pubDate>${article.publishDate}</pubDate>
			<description><![CDATA[${article.summary}]]></description>
			<content:encoded><![CDATA[${article.content}]]></content:encoded>
		</item>
</c:forEach>
	</channel>
</rss>