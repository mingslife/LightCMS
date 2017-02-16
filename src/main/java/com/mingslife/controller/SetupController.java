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
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "../system/setup";
	}
	
	@RequestMapping(value = "/model", method = RequestMethod.GET)
	public String model() {
		return "../system/template/model";
	}
	
	@RequestMapping(value = "/mapper", method = RequestMethod.GET)
	public String mapper() {
		return "../system/template/mapper";
	}
	
	@RequestMapping(value = "/mapper_impl", method = RequestMethod.GET)
	public String mapperImpl() {
		return "../system/template/mapper_impl";
	}
	
	@RequestMapping(value = "/service", method = RequestMethod.GET)
	public String service() {
		return "../system/template/service";
	}
	
	@RequestMapping(value = "/service_impl", method = RequestMethod.GET)
	public String serviceImpl() {
		return "../system/template/service_impl";
	}
	
	@RequestMapping(value = "/dto", method = RequestMethod.GET)
	public String dto() {
		return "../system/template/dto";
	}
	
	@RequestMapping(value = "/controller", method = RequestMethod.GET)
	public String controller() {
		return "../system/template/controller";
	}
	
	@ResponseBody
	@RequestMapping(value = "/version", method = RequestMethod.GET)
	public Map<String, Object> version() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		jsonMap.put("version", "0.1");
		
		return jsonMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public Map<String, Object> test() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		return jsonMap;
	}
}
