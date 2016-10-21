package com.mingslife.dto;

import java.io.Serializable;

import com.mingslife.model.Category;

public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String categoryName;
	private Integer position;
	private Boolean isVisible;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Category toModel() {
		Category model = new Category();
		model.setId(id);
		model.setCategoryName(categoryName);
		model.setPosition(position);
		model.setIsVisible(isVisible);
		return model;
	}
}
