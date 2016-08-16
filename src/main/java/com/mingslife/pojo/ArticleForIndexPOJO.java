package com.mingslife.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ArticleForIndexPOJO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String uuid;
	private String title;
	private Integer authorId;
	private Integer categoryId;
	private Date publishDate;
	private Long readNumber;
	private Long commentNumber;
	private String summary;
	private String categoryName;
	private String publishDateF;

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

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getPublishDateF() {
		if (publishDateF == null) {
			publishDateF = new SimpleDateFormat("yyyy年M月d日").format(publishDate);
		}
		return publishDateF;
	}
}
