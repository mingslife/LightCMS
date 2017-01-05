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

import com.mingslife.dto.MenuDTO;
import com.mingslife.model.Menu;
import com.mingslife.service.IMenuService;
import com.mingslife.web.controller.BaseController;

@Controller
@RequestMapping("/menus")
public class MenuController extends BaseController {
	@Autowired
	private IMenuService menuService;

	public String index(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		List<Menu> menus = menuService.load(new String[] {"id"}, "id", "asc", page, LIMIT);
		model.addAttribute("menus", menus);
		return "menus/index";
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Map<String, Object> list(@RequestParam(value = "page", defaultValue = "1") int page) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<Menu> menus = menuService.load(new String[] {"id"}, "id", "asc", page, LIMIT);
		long count = menuService.count();
		
		jsonMap.put("rows", menus);
		jsonMap.put("total", count);
		
		return jsonMap;
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Menu show(@PathVariable("id") Integer id) {
		Menu menu = menuService.find(id, new String[] {"id"});
		return menu;
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void create(@Valid @ModelAttribute MenuDTO menuDTO) {
		menuService.save(menuDTO.toModel());
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable("id") Integer id, @Valid @ModelAttribute MenuDTO menuDTO, Model model) {
		menuService.update(menuDTO.toModel());
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void destory(@PathVariable("id") Integer id) {
		menuService.delete(id);
	}

	@ResponseBody
	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public void deletes(@RequestParam("ids[]") List<Integer> ids) {
		for (Integer id : ids) {
			menuService.delete(id);
		}
	}
}
