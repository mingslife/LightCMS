package com.mingslife.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mingslife.model.Article;
import com.mingslife.service.IArticleService;
import com.mingslife.web.controller.BaseController;

@Controller
@RequestMapping(value = "/articles")
public class ArticleController extends BaseController {
	@Autowired
	private IArticleService articleService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(@RequestParam(value = "page", required = false) Integer curPage, Model model) {
		curPage = curPage == null ? 1 : curPage;
		List<Article> articles = articleService.load(new String[] {"id", "uuid", "title"}, "id", "asc", curPage, PAGE_LIMIT);
		System.out.println(articles);
		model.addAttribute("articles", articles);
		return "articles/index";
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<String> show(@RequestParam(value = "page", required = false) Integer curPage) {
		curPage = curPage == null ? 1 : curPage;
		List<Article> articles = articleService.load(new String[] {"id", "uuid", "title"}, "id", "asc", curPage, PAGE_LIMIT);
		System.out.println(gson.toJson(articles));
		return new ResponseEntity<String>(gson.toJson(articles), HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Article show(@PathVariable("id") int id) {
		Article article = articleService.find(id);
//		return gson.toJson(article);
		return article;
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
