package com.mingslife.pojo;

import java.io.Serializable;

public class CategorySimplePOJO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String categoryName;

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
}
