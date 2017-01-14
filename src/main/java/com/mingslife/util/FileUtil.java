package com.mingslife.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

import com.mingslife.model.FileSystem;

public class FileUtil {
	public static FileSystem toFileSystem(File file) {
		FileSystem fileSystem = new FileSystem();
		fileSystem.setFileName(file.getName());
		fileSystem.setLastModifiedDate(new Date(file.lastModified()));
		fileSystem.setPath(file.getPath());
		boolean isDirectory = file.isDirectory();
		fileSystem.setIsDirectory(isDirectory);
		if (!isDirectory) {
			fileSystem.setFileSize(file.length());
		}
		try {
			fileSystem.setContentType(Files.probeContentType(file.toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileSystem;
	}
}
