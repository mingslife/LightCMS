package com.mingslife.dto;

import java.io.Serializable;

import com.mingslife.model.Article;
import com.mingslife.web.util.HTMLUtil;

public class ArticleDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String title;
	private Integer authorId;
	private Integer categoryId;
	private Boolean isVisible;
	private Boolean canComment;
	private String password;
	private String cover;
	private String keywords;
	private String description;
	private String content;
	private String markdown;
	private Boolean onTop;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Boolean getCanComment() {
		return canComment;
	}

	public void setCanComment(Boolean canComment) {
		this.canComment = canComment;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = HTMLUtil.getCleanHTML(content);
	}

	public String getMarkdown() {
		return markdown;
	}

	public void setMarkdown(String markdown) {
		this.markdown = markdown;
	}

	public Boolean getOnTop() {
		return onTop;
	}

	public void setOnTop(Boolean onTop) {
		this.onTop = onTop;
	}

	public Article toModel() {
		Article model = new Article();
		model.setTitle(title);
		model.setAuthorId(authorId);
		model.setCategoryId(categoryId);
		model.setIsVisible(isVisible);
		model.setCanComment(canComment);
		model.setPassword(password);
		model.setCover(cover);
		model.setKeywords(keywords);
		model.setDescription(description);
		model.setContent(content);
		model.setMarkdown(markdown);
		model.setOnTop(onTop);
		model.setSummary(password.equals("") ? HTMLUtil.getSummary(content, Article.SUMMARY_LENGTH) : "<p class=\"text-muted\"><span class=\"ion-locked\"></span> 文章已加密</p>");
		return model;
	}
}
