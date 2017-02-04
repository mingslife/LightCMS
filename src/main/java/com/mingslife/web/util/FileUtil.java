package com.mingslife.web.util;

import org.springframework.http.MediaType;

public class FileUtil {
	public static MediaType getMediaType(String contentType) {
		if (contentType == null || contentType.length() == 0) {
			return MediaType.APPLICATION_OCTET_STREAM;
		} else {
			return MediaType.parseMediaType(contentType);
		}
	}
}
