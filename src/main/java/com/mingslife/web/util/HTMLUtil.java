package com.mingslife.web.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.TextExtractingVisitor;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;

public class HTMLUtil {
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
	
	public static String removeHTMLTags(String str, String tags) {
		if (str == null || tags == null)
			return null;
		
		String regex = "(</?)(" + tags + ")([^>]*>)";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE + Pattern.MULTILINE);
		Matcher matcher;
		// 此处需要循环匹配，防止恶意构造的字符串
		while ((matcher = pattern.matcher(str)).find())
			str = matcher.replaceAll("");
		
		return str;
	}
	
	public static String removeEvents(String content) {
		if (content == null)
			return null;
		
		String regex = "(<[^<]*)(on\\w*\\x20*=|javascript:)";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE + Pattern.MULTILINE);
		Matcher matcher;
		while ((matcher = pattern.matcher(content)).find())
			content = matcher.replaceAll("$1");
		
		return content;
	}
	
	public static String makeSafe(String content) {
		return removeEvents(removeHTMLTags(content, "html|body|head|title|style|canvas|script|iframe|frameset|meta"));
	}
	
	public static String makeSafe(String content, String tags) {
		return removeEvents(removeHTMLTags(content, tags));
	}
	
	public static String getPlainText(String data) {
		try {
			Parser parser = new Parser(data);
			TextExtractingVisitor visitor = new TextExtractingVisitor();
			parser.visitAllNodesWith(visitor);
			return visitor.getExtractedText();
		} catch (ParserException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getSummary(String content, int length) {
		String plainText = getPlainText(content);
		return plainText.length() > length ? plainText.substring(0, length) : plainText;
	}

	public static void main(String[] args) {
		String data = "<a href=\"https://www.baidu.com/\" onclick=\"alert('xss');\">baidu</a>\n"
				+ "<img src=# onerror=\"alert('xss');\" />\n"
				+ "<img src=# ononerrorerror=\"alert('xss');\" />\n"
				+ "<a href=\"javascript:;\">test</a>"
				+ "<script type=\"text/javascript\">alert(1);</script>";
		System.out.println("Before:\n" + data);
		System.out.println();
		System.out.println(System.currentTimeMillis());
		System.out.println("AntiSamy:\n" + getCleanHTML(data));
		System.out.println(System.currentTimeMillis());
		System.out.println("Pattern:\n" + makeSafe(data));
		System.out.println(System.currentTimeMillis());
		System.out.println("Plain Text:\n" + getPlainText(data));
	}
}
