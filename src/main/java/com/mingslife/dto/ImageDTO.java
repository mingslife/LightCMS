package com.mingslife.dto;

import java.io.Serializable;

import com.mingslife.model.Image;

public class ImageDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Image toModel() {
		Image model = new Image();
		model.setId(id);
		return model;
	}
}
