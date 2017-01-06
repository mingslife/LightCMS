package com.mingslife.dto;

import java.io.Serializable;

import com.mingslife.model.Menu;

public class MenuDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String url;
	private Integer position;
	private Boolean isVisible;
	private String icon;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Menu toModel() {
		Menu model = new Menu();
		model.setId(id);
		model.setUrl(url);
		model.setPosition(position);
		model.setIsVisible(isVisible);
		model.setIcon(icon);
		return model;
	}
}
