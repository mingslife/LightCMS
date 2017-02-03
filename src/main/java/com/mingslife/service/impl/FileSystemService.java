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
		File root = new File(uploadPath);
		File parentFile = new File(uploadPath, path);
		if (!parentFile.exists() || !parentFile.isDirectory()) {
			throw new WebException("无效的路径！");
		}
		
		File[] files = parentFile.listFiles();
		List<FileSystem> fileSystems = new ArrayList<FileSystem>(files.length + 1); // 预留parentFileSystem所占用的空间
		List<FileSystem> fileSystemFiles = new ArrayList<FileSystem>(files.length);
		if (!root.equals(parentFile)) {
			FileSystem parentFileSystem = new FileSystem(root, parentFile.getParentFile());
			parentFileSystem.setFileName("..");
			fileSystems.add(parentFileSystem);
		}
		for (File file : files) {
			FileSystem fileSystem = new FileSystem(root, file);
			// 区分文件和文件夹，达到文件夹能在列表排到前面的目的
			if (fileSystem.getIsDirectory()) {
				fileSystems.add(fileSystem);
			} else {
				fileSystemFiles.add(fileSystem);
			}
		}
		fileSystems.addAll(fileSystemFiles);
		return fileSystems;
	}
}
