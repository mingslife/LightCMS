package com.mingslife.dto;

import java.io.Serializable;

import com.mingslife.model.Archive;

public class ArchiveDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Archive toModel() {
		Archive model = new Archive();
		model.setId(id);
		return model;
	}
}
