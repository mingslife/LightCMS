package com.mingslife.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArticleForBlogPOJO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String uuid;
	private String title;
	private Integer authorId;
	private String authorName;
	private Integer categoryId;
	private String categoryName;
	private Date publishDate;
	private Long readNumber;
	private Long commentNumber;
	private String summary;
	private String publishDateF;
	private String publishDateF2;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

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

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Long getReadNumber() {
		return readNumber;
	}

	public void setReadNumber(Long readNumber) {
		this.readNumber = readNumber;
	}

	public Long getCommentNumber() {
		return commentNumber;
	}

	public void setCommentNumber(Long commentNumber) {
		this.commentNumber = commentNumber;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPublishDateF() {
		if (publishDateF == null) {
			publishDateF = new SimpleDateFormat("yyyy年M月d日").format(publishDate);
		}
		return publishDateF;
	}

	public String getPublishDateF2() {
		if (publishDateF2 == null) {
			publishDateF2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(publishDate);
		}
		return publishDateF2;
	}
}
