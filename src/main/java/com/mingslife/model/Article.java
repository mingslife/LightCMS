package com.mingslife.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mingslife.web.annotation.CreateOperator;
import com.mingslife.web.annotation.CreateDate;
import com.mingslife.web.annotation.UpdateOperator;
import com.mingslife.web.annotation.UpdateDate;

public class Article implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final int SUMMARY_LENGTH = 120;

	private Integer id;
	private String uuid;
	private String title;
	private Integer authorId;
	private Integer categoryId;
	private Date publishDate;
	private Integer month;
	private Long readNumber;
	private Long commentNumber;
	private Boolean isVisible;
	private Boolean canComment;
	private String password;
	private Boolean hasAttachment;
	private Boolean hasImage;
	private Boolean hasVideo;
	private String cover;
	private String keywords;
	private String description;
	private String summary;
	private String content;
	private String markdown;
	private Boolean onTop;
	@JsonIgnore
	@CreateDate
	private Date createDate;
	@JsonIgnore
	@UpdateDate
	private Date updateDate;
	@JsonIgnore
	@CreateOperator
	private Integer createOperator;
	@JsonIgnore
	@UpdateOperator
	private Integer updateOperator;

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

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
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

	public Boolean getHasAttachment() {
		return hasAttachment;
	}

	public void setHasAttachment(Boolean hasAttachment) {
		this.hasAttachment = hasAttachment;
	}

	public Boolean getHasImage() {
		return hasImage;
	}

	public void setHasImage(Boolean hasImage) {
		this.hasImage = hasImage;
	}

	public Boolean getHasVideo() {
		return hasVideo;
	}

	public void setHasVideo(Boolean hasVideo) {
		this.hasVideo = hasVideo;
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getCreateOperator() {
		return createOperator;
	}

	public void setCreateOperator(Integer createOperator) {
		this.createOperator = createOperator;
	}

	public Integer getUpdateOperator() {
		return updateOperator;
	}

	public void setUpdateOperator(Integer updateOperator) {
		this.updateOperator = updateOperator;
	}
}
