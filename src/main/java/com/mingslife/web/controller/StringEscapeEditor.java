package com.mingslife.web.controller;

import java.beans.PropertyEditorSupport;

public class StringEscapeEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		System.out.println(text);
		super.setAsText(text);
	}
}
