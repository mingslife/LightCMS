package com.mingslife.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mingslife.pojo.ArticleForArticlePOJO;
import com.mingslife.pojo.ArticleForBlogPOJO;
import com.mingslife.service.IArticleService;
import com.mingslife.web.controller.BaseController;

@Controller
public class MainController extends BaseController {
	private static final int INDEX_ARTICLES_LIMIT = 10;
	private static final int BLOG_ARTICLES_LIMIT = 10;

	@Autowired
	private IArticleService articleService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		List<ArticleForBlogPOJO> articles = articleService.loadForBlog(1, INDEX_ARTICLES_LIMIT);
		model.addAttribute("articles", articles);
		return "index";
	}

	@RequestMapping(value = "/blog", method = RequestMethod.GET)
	public String blog(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		List<ArticleForBlogPOJO> articles = articleService.loadForBlog(page, BLOG_ARTICLES_LIMIT);
		model.addAttribute("articles", articles);
		return "blog";
	}

	@RequestMapping(value = "/article/{uuid}", method = RequestMethod.GET)
	public String article(@PathVariable("uuid") String uuid, Model model) {
		ArticleForArticlePOJO article = articleService.findByUUidForArticle(uuid);
		model.addAttribute("article", article);
		return "article";
	}
}
