package com.mingslife.model;

import java.io.Serializable;
import java.util.Date;

import com.mingslife.web.annotation.CreationOperator;
import com.mingslife.web.annotation.CreationTimestamp;
import com.mingslife.web.annotation.UpdateOperator;
import com.mingslife.web.annotation.UpdateTimestamp;

public class Archive implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer month;
	@CreationTimestamp
	private Date createDate;
	@UpdateTimestamp
	private Date updateDate;
	@CreationOperator
	private Integer createOperator;
	@UpdateOperator
	private Integer updateOperator;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
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
