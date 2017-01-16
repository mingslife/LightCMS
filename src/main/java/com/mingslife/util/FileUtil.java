package com.mingslife.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

import org.springframework.http.MediaType;

import com.mingslife.model.FileSystem;

public class FileUtil {
	public static FileSystem toFileSystem(File file) {
		FileSystem fileSystem = new FileSystem();
		fileSystem.setFileName(file.getName());
		fileSystem.setLastModifiedDate(new Date(file.lastModified()));
		String path = file.getAbsolutePath();
		fileSystem.setPath(path);
		boolean isDirectory = file.isDirectory();
		fileSystem.setIsDirectory(isDirectory);
		if (!isDirectory) {
			fileSystem.setFileSize(file.length());
			try {
				fileSystem.setContentType(Files.probeContentType(file.toPath()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileSystem;
	}
	
	public static FileSystem toFileSystem(File root, File file) {
		FileSystem fileSystem = new FileSystem();
		fileSystem.setFileName(file.getName());
		fileSystem.setLastModifiedDate(new Date(file.lastModified()));
		String rootPath = root.getAbsolutePath();
		String path = file.getAbsolutePath();
		fileSystem.setPath(path.substring(rootPath.length()));
		boolean isDirectory = file.isDirectory();
		fileSystem.setIsDirectory(isDirectory);
		if (!isDirectory) {
			fileSystem.setFileSize(file.length());
			try {
				fileSystem.setContentType(Files.probeContentType(file.toPath()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileSystem;
	}
	
	public static MediaType getMediaType(String contentType) {
		if (contentType == null || contentType.length() == 0) {
			return MediaType.APPLICATION_OCTET_STREAM;
		} else {
			return MediaType.parseMediaType(contentType);
		}
	}
}
