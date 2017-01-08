package com.mingslife.model;

import java.io.Serializable;
import java.util.Date;

import com.mingslife.web.annotation.CreateOperator;
import com.mingslife.web.annotation.CreateDate;
import com.mingslife.web.annotation.UpdateOperator;
import com.mingslife.web.annotation.UpdateDate;

public class Guest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String email;
	private String nickname;
	private String sessionId;
	private String currentIp;
	private Date firstVisitDate;
	private Date lastVisitDate;
	private Long visitCount;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getCurrentIp() {
		return currentIp;
	}

	public void setCurrentIp(String currentIp) {
		this.currentIp = currentIp;
	}

	public Date getFirstVisitDate() {
		return firstVisitDate;
	}

	public void setFirstVisitDate(Date firstVisitDate) {
		this.firstVisitDate = firstVisitDate;
	}

	public Date getLastVisitDate() {
		return lastVisitDate;
	}

	public void setLastVisitDate(Date lastVisitDate) {
		this.lastVisitDate = lastVisitDate;
	}

	public Long getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Long visitCount) {
		this.visitCount = visitCount;
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
