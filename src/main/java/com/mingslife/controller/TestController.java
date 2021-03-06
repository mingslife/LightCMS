package com.mingslife.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mingslife.dto.TestDTO;
import com.mingslife.model.Article;
import com.mingslife.service.IArticleService;
import com.mingslife.web.annotation.Permission;
import com.mingslife.web.controller.BaseController;
import com.mingslife.web.event.EmailEvent;
import com.mingslife.web.exception.WebException;
import com.mingslife.web.patch.StringEscapeObjectMapper;
import com.mingslife.web.util.JcsegUtil;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController {
	@Autowired
	private IArticleService articleService;

	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public ResponseEntity<String> email() {
		System.out.println(System.currentTimeMillis());
		applicationContext.publishEvent(new EmailEvent("642203604@qq.com", "测试邮件", "这里是邮件内容。"));
		System.out.println(System.currentTimeMillis());
		return new ResponseEntity<String>("{}", HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/xss", method = RequestMethod.POST)
	public Map<String, Object> xss(@RequestParam("content") String content) {
		ObjectMapper objectMapper = new StringEscapeObjectMapper();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("content", content);
		System.out.println(content);
		try {
			System.out.println(objectMapper.writeValueAsString(jsonMap));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonMap;
	}

	@ResponseBody
	@RequestMapping(value = "/xss/article", method = RequestMethod.GET)
	public List<Article> TestArticleXss(@RequestParam("content") String content) {
		List<Article> articles = new ArrayList<Article>(2);
		
		Article article = new Article();
		article.setId(1);
		article.setUuid(UUID.randomUUID().toString());
		article.setTitle("<p>测试<p>");
		article.setAuthorId(1);
		article.setCategoryId(1);
		article.setPublishDate(new Date());
		article.setMonth(201608);
		article.setReadNumber(0L);
		article.setCommentNumber(0L);
		article.setIsVisible(true);
		article.setCanComment(true);
		article.setPassword(null);
		article.setHasAttachment(false);
		article.setHasImage(false);
		article.setHasVideo(false);
		article.setCover(null);
		article.setSummary("测试");
		article.setContent("<p>测试</p>");
		article.setMarkdown("<p></p>");
		article.setOnTop(false);
		Article article2 = new Article();
		article2.setId(2);
		article2.setUuid(UUID.randomUUID().toString());
		article2.setTitle("<p>测试<p>");
		article2.setAuthorId(1);
		article2.setCategoryId(1);
		article2.setPublishDate(new Date());
		article2.setMonth(201608);
		article2.setReadNumber(0L);
		article2.setCommentNumber(0L);
		article2.setIsVisible(true);
		article2.setCanComment(true);
		article2.setPassword(null);
		article2.setHasAttachment(false);
		article2.setHasImage(false);
		article2.setHasVideo(false);
		article2.setCover(null);
		article2.setSummary("测试");
		article2.setContent("<p>测试</p>");
		article2.setMarkdown("<p></p>");
		article2.setOnTop(false);
		
		articles.add(article);
		articles.add(article2);
		
		return articles;
	}

	@RequestMapping(value = "/jcseg", method = RequestMethod.GET)
	public ResponseEntity<String> jcseg() {
//		System.out.println(System.currentTimeMillis());
//		Set<String> words = JcsegUtil.getWords("测试中文分词。");
//		System.out.println(words);
		System.out.println(System.currentTimeMillis());
		/*Set<String> keywords = JcsegUtil.getKeywords("Redis运行在内存中但是可以持久化到磁盘，所以在对不同数据集进行高速读写时需要权衡内存，应为数据量不能大于硬件内存。"
				+ "在内存数据库方面的另一个优点是，相比在磁盘上相同的复杂的数据结构，在内存中操作起来非常简单，这样Redis可以做很多内部复杂性很强的事情。"
				+ "同时，在磁盘格式方面他们是紧凑的以追加的方式产生的，因为他们并不需要进行随机访问。");*/
		Set<String> keywords = JcsegUtil.getKeywords("该阶段主要是培训SSM框架和进行部分需求设计和数据库设计。由于我们实验室之前一直是用的SSH框架，本次项目要求是要用SSM框架，"
				+ "所以感觉上，还是比较新鲜的。SSM框架是由Spring、SpringMVC和MyBatis三个后端框架组成。Spring是一个基于IOC和AOP的构架多层J2EE系统的开源框架，"
				+ "Spring是于2003年兴起的一个轻量级的Java开发框架，由Rod Johnson创建。简单来说，Spring是一个分层的JavaSE/EE一站式轻量级开源框架。SpringMVC属于Spring Framework的后续产品，"
				+ "已经融合在Spring Web Flow里面。Spring框架提供了构建Web应用程序的全功能MVC模块。使用Spring可插入的MVC架构，从而在使用Spring进行Web开发时，"
				+ "可以选择使用Spring的SpringMVC框架或集成其他MVC开发框架，如Struts1、Struts2等。MyBatis本是apache的开源项目iBatis，2010年这个项目由apache software foundation迁移到了google code，"
				+ "并且改名为MyBatis。2013年11月迁移到GitHub。MyBatis是支持普通SQL查询，存储过程和高级映射的优秀持久层框架。MyBatis消除了几乎所有的JDBC代码和参数的手工设置以及结果集的检索。"
				+ "MyBatis使用简单的XML或注解用于配置和原始映射，将接口和Java的POJO映射成数据库中的记录。Spring在以往项目基本都有使用，所以本次对于这个框架的理解并没有太大的提升。"
				+ "而在SpringMVC和MyBatis框架的学习中，我收获颇丰：原来我在学习Struts2和DWR框架的时候，请求类型基本都是全匹配的，而以往项目开发过程中，我们都是一味地使用post请求，"
				+ "而不使用get请求的原因，竟然是“get请求的参数直接放在url的query部分，觉得这样暴露出来了，这样非常地不安全”，这个想法在脑中禁锢了很久，以致于后边一段时期都是用的post请求进行前后端通讯，"
				+ "直至后来了解到了抓包技术可以抓取请求和返回的报文，包括request body和response body里面的内容，那么这样一来，请求参数是放在query里面还是request body里面就没有太大区别了，"
				+ "并且这样没有正确理解请求类型的语义。后来，我在重庆奇点新宇技术有限公司担任实习研发工程师的时候，使用过Ruby on Rails开发过两个项目，在Rails里面，请求类型都是分开的，并且生成的CRUD方法，"
				+ "都遵循了RESTful的软件架构风格：请求类型都是分开的，而请求的url却是一样的；查询对应get请求，增加对应post请求，修改对应put请求，删除对应delete请求。而用Struts2或者DWR进行开发，"
				+ "一般情况下都是不区分请求类型的，这就不利于构建RESTful应用，而SpringMVC可以轻易地构建RESTful应用，只需要在方法注解参数加上RequestMethod就行。接下来是MyBatis持久化框架，在这以前，"
				+ "我跟大部分实验室的同学一样，项目开发都是用的Hibernate进行持久化，当时进行数据库操作的时候，除了业务逻辑比较复杂的情况，一般都是用的动态sql（hql），"
				+ "sql可以根据使用的数据库类型进行动态生成，可以很好地处理数据库方言，并且有利于数据库的类型迁移。但是这样也会有问题，由于是动态sql，在速度上会比直接拼的sql慢一些，"
				+ "而MyBatis一般是直接写sql，除了速度上可以更快以外，还可以手动去优化sql语句，一般情况来说，使用哪种数据库进行开发是项目一开始就确定的，这样的话还可以针对该数据库进行优化，"
				+ "虽然Hibernate也可以写sql，但是无法像MyBatis那样直接把sql写在配置文件里，从而去实现真正的分离，另外就是Hibernate的开销要比MyBatis大。从各个方面考虑，"
				+ "Hibernate虽然有其独特的跨数据库优势，但是，它仍然在慢慢地退出历史舞台。");
		System.out.println(keywords);
		System.out.println(System.currentTimeMillis());
		return new ResponseEntity<String>("{}", HttpStatus.OK);
	}

	@RequestMapping(value = "/article", method = RequestMethod.GET)
	public ResponseEntity<String> article() {
		Article article = new Article();
		article.setUuid(UUID.randomUUID().toString());
		article.setTitle("测试");
		article.setAuthorId(1);
		article.setCategoryId(1);
		article.setPublishDate(new Date());
		article.setMonth(201608);
		article.setReadNumber(0L);
		article.setCommentNumber(0L);
		article.setIsVisible(true);
		article.setCanComment(true);
		article.setPassword(null);
		article.setHasAttachment(false);
		article.setHasImage(false);
		article.setHasVideo(false);
		article.setCover(null);
		article.setSummary("测试");
		article.setContent("测试");
		article.setOnTop(false);
		articleService.save(article);
		return new ResponseEntity<String>("{}", HttpStatus.OK);
	}
	
	@Permission(1)
	@RequestMapping(value = "/permission", method = RequestMethod.GET)
	public ResponseEntity<String> permission() {
		return new ResponseEntity<String>("{}", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/exception", method = RequestMethod.GET)
	public ResponseEntity<String> throwException() {
		throw new WebException("测试抛出异常！");
//		return new ResponseEntity<String>("{}", HttpStatus.OK);
	}

	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public ResponseEntity<String> testGet(@Valid @ModelAttribute TestDTO testDTO, @RequestParam("type") int type) {
		if (type == 1) return new ResponseEntity<String>("{\"error\":\"error!!\",\"type\":\"GET\"}", HttpStatus.BAD_REQUEST);
		applicationContext.publishEvent(new EmailEvent(testDTO.getEmail(), testDTO.getSubject(), testDTO.getContent()));
		return new ResponseEntity<String>("{\"msg\":\"Sent to " + testDTO.getEmail() + " successfully.\",\"type\":\"GET\"}", HttpStatus.OK);
	}

	@RequestMapping(value = "/api", method = RequestMethod.POST)
	public ResponseEntity<String> testPost(@Valid @ModelAttribute TestDTO testDTO, @RequestParam("type") int type) {
		if (type == 1) return new ResponseEntity<String>("{\"error\":\"error!!\",\"type\":\"POST\"}", HttpStatus.BAD_REQUEST);
		applicationContext.publishEvent(new EmailEvent(testDTO.getEmail(), testDTO.getSubject(), testDTO.getContent()));
		return new ResponseEntity<String>("{\"msg\":\"Sent to " + testDTO.getEmail() + " successfully.\",\"type\":\"POST\"}", HttpStatus.OK);
	}

	@RequestMapping(value = "/api", method = RequestMethod.PUT)
	public ResponseEntity<String> testPut(@Valid @ModelAttribute TestDTO testDTO, @RequestParam("type") int type) {
		if (type == 1) return new ResponseEntity<String>("{\"error\":\"error!!\",\"type\":\"PUT\"}", HttpStatus.BAD_REQUEST);
		applicationContext.publishEvent(new EmailEvent(testDTO.getEmail(), testDTO.getSubject(), testDTO.getContent()));
		return new ResponseEntity<String>("{\"msg\":\"Sent to " + testDTO.getEmail() + " successfully.\",\"type\":\"PUT\"}", HttpStatus.OK);
	}

	@RequestMapping(value = "/api", method = RequestMethod.DELETE)
	public ResponseEntity<String> testDelete(@Valid @ModelAttribute TestDTO testDTO, @RequestParam("type") int type) {
		if (type == 1) return new ResponseEntity<String>("{\"error\":\"error!!\",\"type\":\"DELETE\"}", HttpStatus.BAD_REQUEST);
		applicationContext.publishEvent(new EmailEvent(testDTO.getEmail(), testDTO.getSubject(), testDTO.getContent()));
		return new ResponseEntity<String>("{\"msg\":\"Sent to " + testDTO.getEmail() + " successfully.\",\"type\":\"DELETE\"}", HttpStatus.OK);
	}
}
