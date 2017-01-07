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

import com.mingslife.dto.RoleMenuDTO;
import com.mingslife.model.RoleMenu;
import com.mingslife.service.IRoleMenuService;
import com.mingslife.web.controller.BaseController;

@Controller
@RequestMapping("/role_menus")
public class RoleMenuController extends BaseController {
	@Autowired
	private IRoleMenuService roleMenuService;

	public String index(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		List<RoleMenu> role_menus = roleMenuService.load(new String[] {"id"}, "id", "asc", page, LIMIT);
		model.addAttribute("role_menus", role_menus);
		return "role_menus/index";
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Map<String, Object> list(@RequestParam(value = "page", defaultValue = "1") int page) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<RoleMenu> role_menus = roleMenuService.load(new String[] {"id"}, "id", "asc", page, LIMIT);
		long count = roleMenuService.count();
		
		jsonMap.put("rows", role_menus);
		jsonMap.put("total", count);
		
		return jsonMap;
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public RoleMenu show(@PathVariable("id") Integer id) {
		RoleMenu roleMenu = roleMenuService.find(id, new String[] {"id"});
		return roleMenu;
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void create(@Valid @ModelAttribute RoleMenuDTO roleMenuDTO) {
		roleMenuService.save(roleMenuDTO.toModel());
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable("id") Integer id, @Valid @ModelAttribute RoleMenuDTO roleMenuDTO, Model model) {
		roleMenuService.update(roleMenuDTO.toModel());
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void destory(@PathVariable("id") Integer id) {
		roleMenuService.delete(id);
	}

	@ResponseBody
	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public void deletes(@RequestParam("ids[]") List<Integer> ids) {
		for (Integer id : ids) {
			roleMenuService.delete(id);
		}
	}
}
