package com.mingslife.dto;

import java.io.Serializable;

import com.mingslife.model.RoleMenu;

public class RoleMenuDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleMenu toModel() {
		RoleMenu model = new RoleMenu();
		model.setId(id);
		return model;
	}
}
