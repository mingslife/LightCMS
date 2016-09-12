package com.mingslife.dto;

import java.io.Serializable;

import com.mingslife.model.User;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User toModel() {
		User model = new User();
		model.setId(id);
		return model;
	}
}
