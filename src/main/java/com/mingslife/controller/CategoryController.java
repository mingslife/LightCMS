package com.mingslife.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mingslife.dto.CategoryDTO;
import com.mingslife.model.Category;
import com.mingslife.pojo.CategorySimplePOJO;
import com.mingslife.service.ICategoryService;
import com.mingslife.web.controller.BaseController;

@Controller
@RequestMapping("/categories")
public class CategoryController extends BaseController {
	@Autowired
	private ICategoryService categoryService;

	public String index(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "10") int limit, Model model) {
		List<Category> categories = categoryService.load(new String[] {"id"}, "id", "asc", page, limit);
		model.addAttribute("categories", categories);
		return "categories/index";
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Map<String, Object> list(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "limit", defaultValue = "10") int limit) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		List<Category> categories = categoryService.load(new String[] {"id", "category_name", "position", "is_visible"}, "id", "asc", page, limit);
		long count = categoryService.count();
		
		jsonMap.put("rows", categories);
		jsonMap.put("total", count);
		
		return jsonMap;
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Category show(@PathVariable("id") Integer id) {
		Category category = categoryService.find(id, new String[] {"id", "category_name", "position", "is_visible"});
		return category;
	}

	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void create(@ModelAttribute CategoryDTO categoryDTO) {
		categoryService.save(categoryDTO.toModel());
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable("id") Integer id, @ModelAttribute CategoryDTO categoryDTO, Model model) {
		Category category = categoryDTO.toModel();
		category.setId(id);
		categoryService.update(category);
	}

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void destory(@PathVariable("id") Integer id) {
		categoryService.delete(id);
	}

	@ResponseBody
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public void deletes(@RequestParam("ids[]") List<Integer> ids) {
		for (Integer id : ids) {
			categoryService.delete(id);
		}
	}

	@ResponseBody
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<CategorySimplePOJO> all() {
		List<CategorySimplePOJO> categories = categoryService.loadAll();
		return categories;
	}
}
