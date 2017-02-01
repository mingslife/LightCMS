package com.mingslife.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mingslife.dto.ArchiveDTO;
import com.mingslife.model.Archive;
import com.mingslife.service.IArchiveService;
import com.mingslife.web.controller.BaseController;

@Controller
@RequestMapping("/archives")
public class ArchiveController extends BaseController {
	@Autowired
	private IArchiveService archiveService;

	public String index(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "10") int limit, Model model) {
		List<Archive> archives = archiveService.load(new String[] {"id"}, "id", "asc", page, limit);
		model.addAttribute("archives", archives);
		return "archives/index";
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Map<String, Object> list(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "10") int limit) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<Archive> archives = archiveService.load(new String[] {"id"}, "id", "asc", page, limit);
		long count = archiveService.count();
		
		jsonMap.put("rows", archives);
		jsonMap.put("total", count);
		
		return jsonMap;
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Archive show(@PathVariable("id") Integer id) {
		Archive archive = archiveService.find(id, new String[] {"id"});
		return archive;
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void create(@Valid @ModelAttribute ArchiveDTO archiveDTO) {
		archiveService.save(archiveDTO.toModel());
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable("id") Integer id, @Valid @ModelAttribute ArchiveDTO archiveDTO, Model model) {
		archiveService.update(archiveDTO.toModel());
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void destory(@PathVariable("id") Integer id) {
		archiveService.delete(id);
	}

	@ResponseBody
	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public void deletes(@RequestParam("ids[]") List<Integer> ids) {
		for (Integer id : ids) {
			archiveService.delete(id);
		}
	}
}
