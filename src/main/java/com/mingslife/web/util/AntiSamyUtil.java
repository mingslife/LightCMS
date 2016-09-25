package com.mingslife.web.util;

import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;

public class AntiSamyUtil {
	public static String getCleanHTML(String data) {
		AntiSamy antiSamy = new AntiSamy();
		try {
			Policy policy = Policy.getInstance(AntiSamy.class.getResource("/antisamy-myspace.xml").openStream());
			CleanResults cleanResults = antiSamy.scan(data, policy);
			return cleanResults.getCleanHTML();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		String data = "<a href=\"https://www.baidu.com/\" onclick=\"alert('xss');\">baidu</a>\n"
				+ "<img src=# onerror=\"alert('xss');\" />\n"
				+ "<img src=# ononerrorerror=\"alert('xss');\" />\n"
				+ "<a href=\"javascript:;\">test</a>";
		System.out.println("Before:\n" + data);
		System.out.println();
		System.out.println("After:\n" + getCleanHTML(data));
	}
}
