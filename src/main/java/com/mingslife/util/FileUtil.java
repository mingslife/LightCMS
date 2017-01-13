package com.mingslife.util;

import java.io.File;

import com.mingslife.model.FileSystem;

public class FileUtil {
	public static FileSystem toFileSystem(File file) {
		FileSystem fileSystem = new FileSystem();
		fileSystem.setFileName(file.getName());
		fileSystem.setFileSize(file.length());
		fileSystem.setIsDirectory(file.isDirectory());
		return fileSystem;
	}
}
