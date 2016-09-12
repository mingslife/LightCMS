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

import com.mingslife.dto.UserDTO;
import com.mingslife.model.User;
import com.mingslife.service.IUserService;
import com.mingslife.web.controller.BaseController;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {
	@Autowired
	private IUserService userService;

	public String index(@RequestParam(value = "page", required = false) Integer page, Model model) {
		page = page == null ? 1 : page;
		List<User> users = userService.load(new String[] {"id"}, "id", "asc", page, LIMIT);
		model.addAttribute("users", users);
		return "users/index";
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		page = page == null ? 1 : page;
		List<User> users = userService.load(new String[] {"id"}, "id", "asc", page, LIMIT);
		long count = userService.count();
		
		jsonMap.put("rows", users);
		jsonMap.put("total", count);
		
		return jsonMap;
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User show(@PathVariable("id") Integer id) {
		User user = userService.find(id, new String[] {"id"});
		return user;
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void create(@Valid @ModelAttribute UserDTO userDTO) {
		userService.save(userDTO.toModel());
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable("id") Integer id, @Valid @ModelAttribute UserDTO userDTO, Model model) {
		userService.update(userDTO.toModel());
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void destory(@PathVariable("id") Integer id) {
		userService.delete(id);
	}

	@ResponseBody
	@RequestMapping(value = "/deletes", method = RequestMethod.DELETE)
	public void deletes(@RequestParam("ids[]") List<Integer> ids) {
		for (Integer id : ids) {
			userService.delete(id);
		}
	}
}
