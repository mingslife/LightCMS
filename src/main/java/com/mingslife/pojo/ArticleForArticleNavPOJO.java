package com.mingslife.pojo;

import java.io.Serializable;

public class ArticleForArticleNavPOJO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String uuid;
	private String title;

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
}
