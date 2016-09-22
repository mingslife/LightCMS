package com.mingslife.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mingslife.pojo.ArticleForRssPOJO;
import com.mingslife.service.IArticleService;

@Controller
public class RssController {
	private static final int RSS_ARTICLES_LIMIT = 10;

	@Autowired
	private IArticleService articleService;

	@RequestMapping(value = "/rss", method = RequestMethod.GET)
	public String rss(Model model) {
		List<ArticleForRssPOJO> articles = articleService.loadForRss(RSS_ARTICLES_LIMIT);
		Date lastBuildDate = articles.size() != 0 ? articles.get(0).getPublishDate() : new Date();
		model.addAttribute("articles", articles);
		model.addAttribute("lastBuildDate", lastBuildDate);
		return "rss";
	}
}
