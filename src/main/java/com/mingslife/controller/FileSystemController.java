package com.mingslife.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mingslife.model.FileSystem;
import com.mingslife.service.IFileSystemService;
import com.mingslife.util.FileUtil;
import com.mingslife.web.controller.BaseController;
import com.mingslife.web.exception.WebException;

@Controller
@RequestMapping("/file_systems")
public class FileSystemController extends BaseController {
	@Autowired
	private IFileSystemService fileSystemService;

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<FileSystem> list(@RequestParam(value = "path", defaultValue = "") String path) {
		@SuppressWarnings("unchecked")
		Map<String, String> applicationMap = (Map<String, String>) application.getAttribute("application");
		String uploadPath = applicationMap.get("uploadPath");
		String uploadRoot = applicationMap.get("uploadRoot");
		try {
			uploadPath = uploadPath.equals("") ? application.getResource(uploadRoot).getPath() : uploadPath;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new WebException("上传配置错误！");
		}
		
		List<FileSystem> fileSystems = fileSystemService.loadList(uploadPath, path);
		
		return fileSystems;
	}
	
	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Map<String, Object> upload(@RequestParam("path") String path, HttpServletRequest request) throws IOException {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
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
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");
		File uploadFile = new File(parentFile, file.getName());
		try {
			file.transferTo(uploadFile);
			FileSystem fileSystem = FileUtil.toFileSystem(uploadFile);
			jsonMap.put("data", fileSystem);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new WebException("上传失败！");
		}
		
		return jsonMap;
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
	
	@ResponseBody
	@RequestMapping(value = "/image", method = RequestMethod.GET)
	public List<Map<String, Object>> images() {
		List<Map<String, Object>> images = new ArrayList<Map<String, Object>>();
		
		return images;
	}
}
