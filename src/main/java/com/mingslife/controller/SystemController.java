package com.mingslife.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mingslife.dto.SystemLoginDTO;
import com.mingslife.model.User;
import com.mingslife.service.IUserService;
import com.mingslife.web.annotation.Permission;
import com.mingslife.web.component.UserManager;
import com.mingslife.web.controller.BaseController;
import com.mingslife.web.exception.WebException;
import com.mingslife.web.util.DataSecurityUtil;

@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private IUserService userService;
	
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, Object> login(@Valid @ModelAttribute SystemLoginDTO systemLoginDTO) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		String account = systemLoginDTO.getUsername();
		String password = systemLoginDTO.getPassword();
		
		User user = userService.findByAccount(account);
		if (user != null && DataSecurityUtil.matchBCrypt(user.getPassword(), password)) {
			Integer userId = user.getId();
			userManager.login(userId);
			
			jsonMap.put("url", "main.html");
		} else {
			throw new WebException("账号或密码错误！");
		}
		
		return jsonMap;
	}
	
	@Permission
	@ResponseBody
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public void logout() {
		userManager.logout();
	}
	
	@Permission
	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public Map<String, Object> test() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		jsonMap.put("url", "main.html");
		
		return jsonMap;
	}
}
