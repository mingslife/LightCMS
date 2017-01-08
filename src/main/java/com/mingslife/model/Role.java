package com.mingslife.model;

import java.io.Serializable;
import java.util.Date;

import com.mingslife.web.annotation.CreateOperator;
import com.mingslife.web.annotation.CreateDate;
import com.mingslife.web.annotation.UpdateOperator;
import com.mingslife.web.annotation.UpdateDate;

public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String roleName;
	private Boolean isActive;
	@CreateDate
	private Date createDate;
	@UpdateDate
	private Date updateDate;
	@CreateOperator
	private Integer createOperator;
	@UpdateOperator
	private Integer updateOperator;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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
