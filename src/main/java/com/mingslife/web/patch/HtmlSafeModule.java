package com.mingslife.web.patch;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class HtmlSafeModule extends SimpleModule {
	private static final long serialVersionUID = 1L;

	public HtmlSafeModule() {
		addSerializer(String.class, new HtmlSafeSerializer());
	}
}
