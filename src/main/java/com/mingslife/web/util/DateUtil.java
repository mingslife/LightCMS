package com.mingslife.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

	public static String format(Date date) {
		simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(date);
	}

	public static String format(Date date, String pattern) {
		simpleDateFormat.applyPattern(pattern);
		return simpleDateFormat.format(date);
	}
}
