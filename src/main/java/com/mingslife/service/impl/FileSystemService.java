package com.mingslife.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mingslife.model.FileSystem;
import com.mingslife.service.IFileSystemService;
import com.mingslife.web.exception.WebException;

@Service
public class FileSystemService implements IFileSystemService {
	@Deprecated
	@Override
	public List<FileSystem> loadList(String path) {
		if (path == null || path.indexOf("..") != -1) {
			throw new WebException("非法路径！");
		}
		File parentFile = new File(path);
		File[] files = parentFile.listFiles();
		List<FileSystem> fileSystems = new ArrayList<FileSystem>(files.length);
		for (File file : files) {
			FileSystem fileSystem = new FileSystem(file);
			fileSystems.add(fileSystem);
		}
		return fileSystems;
	}

	@Override
	public List<FileSystem> loadList(String uploadPath, String path) {
		if (path == null || path.indexOf("..") != -1) {
			throw new WebException("非法路径！");
		}
		File root = new File(uploadPath);
		File parentFile = new File(uploadPath, path);
		if (!parentFile.exists() || !parentFile.isDirectory()) {
			throw new WebException("无效的路径！");
		}
		File[] files = parentFile.listFiles();
		List<FileSystem> fileSystems = new ArrayList<FileSystem>(files.length);
		for (File file : files) {
			FileSystem fileSystem = new FileSystem(root, file);
			fileSystems.add(fileSystem);
		}
		return fileSystems;
	}
}
