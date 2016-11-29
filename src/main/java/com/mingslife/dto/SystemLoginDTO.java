package com.mingslife.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class SystemLoginDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String username;
	@NotEmpty
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}
}
