package com.mingslife.dto;

import java.io.Serializable;

import com.mingslife.model.Article;

public class ArticleDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Article toModel() {
		Article model = new Article();
		model.setId(id);
		return model;
	}
}
