package com.mingslife.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mingslife.pojo.ArticleForRssPOJO;
import com.mingslife.service.IArticleService;

@Controller
public class RSSController {
	private static final int RSS_ARTICLES_LIMIT = 30;

	@Autowired
	private IArticleService articleService;

	@RequestMapping(value = "/rss", method = RequestMethod.GET)
	public String rss(Model model) {
		List<ArticleForRssPOJO> articles = articleService.loadForRss(RSS_ARTICLES_LIMIT);
		model.addAttribute("articles", articles);
		return "rss";
	}
}
