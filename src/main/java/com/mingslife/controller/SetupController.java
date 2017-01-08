package com.mingslife.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mingslife.web.controller.BaseController;

@Controller
@RequestMapping("/setup")
public class SetupController extends BaseController {
	@ResponseBody
	@RequestMapping(value = "/version", method = RequestMethod.GET)
	public Map<String, Object> version() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		jsonMap.put("version", "0.1");
		
		return jsonMap;
	}
}
