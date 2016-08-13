package com.mingslife.web.event;

import org.springframework.context.ApplicationEvent;

public class EmailEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;

	private String address;
	private String subject;
	private String content;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public EmailEvent(Object source) {
		super(source);
	}
	
	public EmailEvent(String address, String subject, String content) {
		super(address);
		this.address = address;
		this.subject = subject;
		this.content = content;
	}
}
