package com.mingslife.web.component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StartupManager implements InitializingBean {
	@Autowired
	private ServletContext application;

	@Override
	public void afterPropertiesSet() throws Exception {
		Properties properties = new Properties();
		try {
			properties.load(application.getResourceAsStream("WEB-INF/classes/application.properties"));
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Map<String, String> applicationMap = new HashMap<String, String>((Map) properties);
			application.setAttribute("application", applicationMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Startup Manager has initialized completed.");
	}
}
