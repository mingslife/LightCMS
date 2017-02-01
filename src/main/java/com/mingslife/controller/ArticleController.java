package com.mingslife.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public String index(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "10") int limit, Model model) {
		List<Article> articles = articleService.load(new String[] {"id", "uuid", "title"}, "id", "asc", page, limit);
		model.addAttribute("articles", articles);
		return "articles/index";
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Map<String, Object> list(@RequestParam(value = "curPage", defaultValue = "1") int curPage, @RequestParam(value = "limit", defaultValue = "10") int limit) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<Article> articles = articleService.load(new String[] {"id", "title", "publish_date", "is_visible", "can_comment", "on_top"}, "id", "desc", curPage, limit);
		long count = articleService.count();
		
		jsonMap.put("rows", articles);
		jsonMap.put("total", count);
		
		return jsonMap;
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Article show(@PathVariable("id") int id) {
		Article article = articleService.find(id, new String[] {"id", "uuid", "title", "author_id", "category_id", "publish_date", "read_number", "comment_number", "is_visible", "can_comment", "password", "cover", "keywords", "description", "content", "markdown", "on_top"});
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
