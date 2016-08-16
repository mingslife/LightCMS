package com.mingslife.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class TestDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty
//	@Email
	private String email;
	@NotEmpty
	private String subject;
	@NotEmpty
	private String content;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
