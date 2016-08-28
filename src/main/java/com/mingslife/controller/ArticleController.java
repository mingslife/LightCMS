package com.mingslife.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mingslife.dto.ArticleDTO;
import com.mingslife.model.Article;
import com.mingslife.service.IArticleService;
import com.mingslife.web.controller.BaseController;

@Controller
@RequestMapping(value = "/articles")
public class ArticleController extends BaseController {
	@Autowired
	private IArticleService articleService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(@RequestParam(value = "page", required = false) Integer page, Model model) {
		page = page == null ? 1 : page;
		List<Article> articles = articleService.load(new String[] {"id", "uuid", "title"}, "id", "asc", page, LIMIT);
		model.addAttribute("articles", articles);
		return "articles/index";
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Article> show(@RequestParam(value = "page", required = false) Integer page) {
		page = page == null ? 1 : page;
		List<Article> articles = articleService.load(new String[] {"id", "uuid", "title"}, "id", "asc", page, LIMIT);
		return articles;
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Article show(@PathVariable("id") int id) {
		Article article = articleService.find(id);
		return article;
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void create(@Valid @ModelAttribute ArticleDTO articleDTO) {
		Article article = articleDTO.toModel();
		articleService.save(article);
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable("id") Integer id, @Valid @ModelAttribute ArticleDTO articleDTO) {
		Article article = articleDTO.toModel();
		article.setId(id);
		articleService.update(article);
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Integer id) {
		articleService.delete(id);
	}
}
