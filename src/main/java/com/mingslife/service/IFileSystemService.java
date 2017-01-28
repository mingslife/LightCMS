package com.mingslife.service;

import java.util.List;

import com.mingslife.model.FileSystem;

public interface IFileSystemService {
	List<FileSystem> loadList(String path);
	List<FileSystem> loadList(String uploadPath, String path);
}
