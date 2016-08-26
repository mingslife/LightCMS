package com.mingslife.web.util;

import java.nio.charset.Charset;

import org.springframework.http.converter.StringHttpMessageConverter;

public class StringHttpMessageConverterUTF8 extends StringHttpMessageConverter {
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
}
