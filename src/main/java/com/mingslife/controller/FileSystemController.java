package com.mingslife.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mingslife.model.FileSystem;
import com.mingslife.util.FileUtil;
import com.mingslife.web.controller.BaseController;

@Controller
@RequestMapping("/file_systems")
public class FileSystemController extends BaseController {
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<FileSystem> list(@RequestParam(value = "page", defaultValue = "1") int page) {
		List<FileSystem> fileSystems = new ArrayList<FileSystem>();
		
		List<FileSystem> directoryFileSystems = new ArrayList<FileSystem>();
		List<FileSystem> fileFileSystems = new ArrayList<FileSystem>();
		
		File rootFile = new File("C:/");
		File[] files = rootFile.listFiles();
		for (File file : files) {
			System.out.println(file.getName() + " " + file.isDirectory());
			FileSystem fileSystem = FileUtil.toFileSystem(file);
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
