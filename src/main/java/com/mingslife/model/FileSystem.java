package com.mingslife.model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FileSystem implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private Integer id;
	private String fileName;
	private Long fileSize;
	private String contentType;
	private Boolean isDirectory;
	private Date lastModifiedDate;
	private String path;
	@JsonIgnore
	private File file;
	@JsonIgnore
	private File root;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Boolean getIsDirectory() {
		return isDirectory;
	}

	public void setIsDirectory(Boolean isDirectory) {
		this.isDirectory = isDirectory;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public File getRoot() {
		return root;
	}

	public void setRoot(File root) {
		this.root = root;
	}

	public FileSystem() {
	}

	@Deprecated
	public FileSystem(File file) {
		this.fileName = file.getName();
		try {
			this.contentType = Files.probeContentType(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			this.contentType = "application/octet-stream";
		}
		this.path = file.getPath();
		boolean isDirectory = file.isDirectory();
		this.fileSize = isDirectory ? null : file.length();
		this.isDirectory = isDirectory;
		this.lastModifiedDate = new Date(file.lastModified());
		this.file = file;
		this.root = null;
	}

	public FileSystem(File root, File file) {
		this.fileName = file.getName();
		try {
			this.contentType = Files.probeContentType(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			this.contentType = "application/octet-stream";
		}
		String rootPath = root.getAbsolutePath();
		String path = file.getAbsolutePath();
		this.path = path.substring(rootPath.length());
		boolean isDirectory = file.isDirectory();
		this.fileSize = isDirectory ? null : file.length();
		this.isDirectory = isDirectory;
		this.lastModifiedDate = new Date(file.lastModified());
		this.file = file;
		this.root = root;
	}
}
