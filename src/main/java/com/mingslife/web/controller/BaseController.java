package com.mingslife.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class BaseController {
	@Autowired
	protected ApplicationContext applicationContext;
}
