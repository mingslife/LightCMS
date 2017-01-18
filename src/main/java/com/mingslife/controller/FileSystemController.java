package com.mingslife.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.mingslife.model.FileSystem;
import com.mingslife.util.FileUtil;
import com.mingslife.web.controller.BaseController;
import com.mingslife.web.exception.WebException;

@Controller
@RequestMapping("/file_systems")
public class FileSystemController extends BaseController {
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<FileSystem> list(@RequestParam(value = "path", defaultValue = "") String path) {
		// 防止跳到非法目录
		if (path.indexOf("..") != -1) {
			throw new WebException("非法路径！");
		}
		
		@SuppressWarnings({"unchecked"})
		Map<String, String> applicationMap = (Map<String, String>) application.getAttribute("application");
		
		List<FileSystem> fileSystems = new ArrayList<FileSystem>();
		
		List<FileSystem> directoryFileSystems = new ArrayList<FileSystem>();
		List<FileSystem> fileFileSystems = new ArrayList<FileSystem>();
		
		String uploadPath = applicationMap.get("uploadPath");
		File root = new File(uploadPath);
		File parentFile = new File(root, path);
		if (!parentFile.exists()) {
			throw new WebException("找不到该路径！");
		}
		if (!parentFile.equals(root)) {
			FileSystem fileSystem = FileUtil.toFileSystem(root, parentFile.getParentFile());
			fileSystem.setFileName("..");
			fileSystems.add(fileSystem);
		}
		File[] files = parentFile.listFiles();
		for (File file : files) {
			System.out.println(file.getName() + " " + file.isDirectory() + " " + file.getAbsolutePath());
			FileSystem fileSystem = FileUtil.toFileSystem(root, file);
			if (fileSystem.getIsDirectory()) {
				directoryFileSystems.add(fileSystem);
			} else {
				fileFileSystems.add(fileSystem);
			}
		}
		
		fileSystems.addAll(directoryFileSystems);
		fileSystems.addAll(fileFileSystems);
		
		return fileSystems;
	}
	
	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Map<String, Object> upload(@RequestParam("path") String path, HttpServletRequest request) {
		// 防止跳到非法目录
		if (path.indexOf("..") != -1) {
			throw new WebException("非法路径！");
		}
		
		@SuppressWarnings("unchecked")
		Map<String, String> applicationMap = (Map<String, String>) application.getAttribute("application");
		
		String uploadPath = applicationMap.get("uploadPath");
		File root = new File(uploadPath);
		File parentFile = new File(uploadPath, path);
		if (!parentFile.exists() || !parentFile.isDirectory()) {
			throw new WebException("无效的路径！");
		}
		
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(application);
		// TODO 今天先到这里，有空继续
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(@RequestParam("path") String path) throws IOException {
		// 防止跳到非法目录
		if (path.indexOf("..") != -1) {
			throw new WebException("非法路径！");
		}
		
		@SuppressWarnings({"unchecked"})
		Map<String, String> applicationMap = (Map<String, String>) application.getAttribute("application");
		
		String uploadPath = applicationMap.get("uploadPath");
		File root = new File(uploadPath);
		File downloadFile = new File(root, path);
		if (!downloadFile.exists() || downloadFile.isDirectory()) {
			throw new WebException("找不到该文件！");
		}
		
		String fileName = downloadFile.getName();
		String contentType = Files.probeContentType(downloadFile.toPath());
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(FileUtil.getMediaType(contentType));
		httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\""); // 解决IE和Edge下载失败的问题
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(downloadFile), httpHeaders, HttpStatus.CREATED);
	}
}
