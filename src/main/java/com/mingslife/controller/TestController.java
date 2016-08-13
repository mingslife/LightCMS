package com.mingslife.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mingslife.web.controller.BaseController;
import com.mingslife.web.event.EmailEvent;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController {
	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public ResponseEntity<String> email() {
		System.out.println(System.currentTimeMillis());
		applicationContext.publishEvent(new EmailEvent("642203604@qq.com", "测试邮件", "这里是邮件内容。"));
		System.out.println(System.currentTimeMillis());
		return new ResponseEntity<String>("{}", HttpStatus.OK);
	}
}
