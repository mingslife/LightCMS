package com.mingslife.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mingslife.model.Article;
import com.mingslife.service.IArticleService;
import com.mingslife.web.controller.BaseController;

@Controller
@RequestMapping(value = "/article")
public class ArticleController extends BaseController {
	@Autowired
	private IArticleService articleService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "";
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<String> show(@RequestParam("id") int id) {
		Article article = articleService.find(id);
		return new ResponseEntity<String>("{}", HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> create() {
		return new ResponseEntity<String>("{}", HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<String> update() {
		return new ResponseEntity<String>("{}", HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete() {
		return new ResponseEntity<String>("{}", HttpStatus.OK);
	}
}
