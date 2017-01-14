package com.mingslife.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
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

@Controller
@RequestMapping("/file_systems")
public class FileSystemController extends BaseController {
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Map<String, Object> list(@RequestParam(value = "page", defaultValue = "1") int page) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<FileSystem> fileSystems = new ArrayList<FileSystem>();
		
		File file = new File("C:/");
		File[] files = file.listFiles();
		for (File each : files) {
			System.out.println(each.getName() + " " + each.isDirectory());
			FileSystem fileSystem = FileUtil.toFileSystem(each);
			fileSystems.add(fileSystem);
		}
		
		jsonMap.put("rows", fileSystems);
		
		return jsonMap;
	}
}
