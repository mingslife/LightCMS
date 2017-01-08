package com.mingslife.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mingslife.web.annotation.CreateOperator;
import com.mingslife.web.annotation.CreateDate;
import com.mingslife.web.annotation.UpdateOperator;
import com.mingslife.web.annotation.UpdateDate;

public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String categoryName;
	private Integer position;
	private Boolean isVisible;
	@JsonIgnore
	@CreateDate
	private Date createDate;
	@JsonIgnore
	@UpdateDate
	private Date updateDate;
	@JsonIgnore
	@CreateOperator
	private Integer createOperator;
	@JsonIgnore
	@UpdateOperator
	private Integer updateOperator;

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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getCreateOperator() {
		return createOperator;
	}

	public void setCreateOperator(Integer createOperator) {
		this.createOperator = createOperator;
	}

	public Integer getUpdateOperator() {
		return updateOperator;
	}

	public void setUpdateOperator(Integer updateOperator) {
		this.updateOperator = updateOperator;
	}
}
