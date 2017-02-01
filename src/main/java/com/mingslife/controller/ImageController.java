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

import com.mingslife.dto.ImageDTO;
import com.mingslife.model.Image;
import com.mingslife.service.IImageService;
import com.mingslife.web.controller.BaseController;

@Controller
@RequestMapping("/images")
public class ImageController extends BaseController {
	@Autowired
	private IImageService imageService;

	public String index(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "10") int limit, Model model) {
		List<Image> images = imageService.load(new String[] {"id"}, "id", "asc", page, limit);
		model.addAttribute("images", images);
		return "images/index";
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Map<String, Object> list(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "10") int limit) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<Image> images = imageService.load(new String[] {"id"}, "id", "asc", page, limit);
		long count = imageService.count();
		
		jsonMap.put("rows", images);
		jsonMap.put("total", count);
		
		return jsonMap;
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Image show(@PathVariable("id") Integer id) {
		Image image = imageService.find(id, new String[] {"id"});
		return image;
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void create(@Valid @ModelAttribute ImageDTO imageDTO) {
		imageService.save(imageDTO.toModel());
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable("id") Integer id, @Valid @ModelAttribute ImageDTO imageDTO, Model model) {
		imageService.update(imageDTO.toModel());
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void destory(@PathVariable("id") Integer id) {
		imageService.delete(id);
	}

	@ResponseBody
	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public void deletes(@RequestParam("ids[]") List<Integer> ids) {
		for (Integer id : ids) {
			imageService.delete(id);
		}
	}
}
