package com.mingslife.dto;

import java.io.Serializable;

import com.mingslife.model.Menu;

public class MenuDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Menu toModel() {
		Menu model = new Menu();
		model.setId(id);
		return model;
	}
}
