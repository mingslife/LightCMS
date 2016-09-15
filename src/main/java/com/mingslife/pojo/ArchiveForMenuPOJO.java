package com.mingslife.pojo;

import java.io.Serializable;

public class ArchiveForMenuPOJO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer month;
	private String monthF;

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

	public String getMonthF() {
		if (monthF == null) {
			monthF = (month / 100) + "年" + (month % 100) + "月";
		}
		return monthF;
	}
}
