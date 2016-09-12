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
	private static final int BLOG_ARTICLES_LIMIT = 2;

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
		long totalPage = (long) Math.ceil(articleService.countForBlog() * 1.0 / BLOG_ARTICLES_LIMIT);
		long startPage = 1;
		long endPage = totalPage;
		if (totalPage > 3) {
			if (page <= 2) {
				startPage = 1;
				endPage = 3;
			} else if (page > totalPage - 2) {
				startPage = totalPage - 2;
			} else {
				startPage = page - 1;
				endPage = page + 1;
			}
		}
		model.addAttribute("articles", articles);
		model.addAttribute("page", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "blog";
	}

	@RequestMapping(value = "/article/{uuid}", method = RequestMethod.GET)
	public String article(@PathVariable("uuid") String uuid, Model model) {
		ArticleForArticlePOJO article = articleService.findByUUidForArticle(uuid);
		model.addAttribute("article", article);
		return "article";
	}
}
