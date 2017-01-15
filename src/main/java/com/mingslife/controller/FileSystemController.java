package com.mingslife.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
