package com.mingslife.dto;

import java.io.Serializable;

import com.mingslife.model.Category;

public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Category toModel() {
		Category model = new Category();
		model.setId(id);
		return model;
	}
}
