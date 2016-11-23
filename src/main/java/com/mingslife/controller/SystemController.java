package com.mingslife.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mingslife.dto.SystemLoginDTO;
import com.mingslife.web.controller.BaseController;

@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, Object> login(@Valid @ModelAttribute SystemLoginDTO systemLoginDTO) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		return jsonMap;
	}
}
