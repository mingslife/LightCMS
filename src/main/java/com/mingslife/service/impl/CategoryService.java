package com.mingslife.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingslife.dao.CategoryMapper;
import com.mingslife.model.Category;
import com.mingslife.service.ICategoryService;
import com.mingslife.web.util.SQLUtil;

@Service
public class CategoryService implements ICategoryService {
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public void save(Category category) {
		categoryMapper.insert(category);
	}

	@Override
	public void update(Category category) {
		categoryMapper.updateByPrimaryKeySelective(category);
	}

	@Override
	public void delete(Category category) {
		categoryMapper.deleteByPrimaryKey(category.getId());
	}

	@Override
	public void delete(Integer id) {
		categoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Category find(Integer id) {
		return categoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public Category find(Integer id, String[] parameters) {
		return categoryMapper.find(id, SQLUtil.formatParameters(parameters));
	}

	@Override
	public List<Category> load() {
		return categoryMapper.select(null, null, null, null, -1, -1);
	}

	@Override
	public List<Category> load(String[] parameters) {
		return categoryMapper.select(SQLUtil.formatParameters(parameters), null, null, null, -1, -1);
	}

	@Override
	public List<Category> load(String condition, Object[] values) {
		return categoryMapper.select(null, SQLUtil.fillCondition(condition, values), null, null, -1, -1);
	}

	@Override
	public List<Category> load(String[] parameters, String condition, Object[] values) {
		return categoryMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), null, null, -1, -1);
	}

	@Override
	public List<Category> load(int curPage, int limit) {
		return categoryMapper.select(null, null, null, null, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Category> load(String[] parameters, int curPage, int limit) {
		return categoryMapper.select(SQLUtil.formatParameters(parameters), null, null, null, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Category> load(String condition, Object[] values, int curPage, int limit) {
		return categoryMapper.select(null, SQLUtil.fillCondition(condition, values), null, null, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Category> load(String[] parameters, String condition, Object[] values, int curPage, int limit) {
		return categoryMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), null, null, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Category> load(String condition, Object[] values, String order, String sort) {
		return categoryMapper.select(null, SQLUtil.fillCondition(condition, values), order, sort, -1, -1);
	}

	@Override
	public List<Category> load(String[] parameters, String condition, Object[] values, String order, String sort) {
		return categoryMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), order, sort, -1, -1);
	}

	@Override
	public List<Category> load(String order, String sort, int curPage, int limit) {
		return categoryMapper.select(null, null, order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Category> load(String[] parameters, String order, String sort, int curPage, int limit) {
		return categoryMapper.select(SQLUtil.formatParameters(parameters), null, order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Category> load(String condition, Object[] values, String order, String sort, int curPage, int limit) {
		return categoryMapper.select(null, SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Category> load(String[] parameters, String condition, Object[] values, String order, String sort, int curPage, int limit) {
		return categoryMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public long count() {
		return categoryMapper.count(null, null, false);
	}

	@Override
	public long count(String condition, Object[] values) {
		return categoryMapper.count(null, SQLUtil.fillCondition(condition, values), false);
	}

	@Override
	public long count(String[] parameters, boolean isDistinct) {
		return categoryMapper.count(SQLUtil.formatParameters(parameters), null, isDistinct);
	}

	@Override
	public long count(String[] parameters, String condition, Object[] values, boolean isDistinct) {
		return categoryMapper.count(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), isDistinct);
	}

	@Override
	public double sum(String parameter) {
		return categoryMapper.sum(parameter, null, null, null, -1, -1, false);
	}

	@Override
	public double sum(String parameter, String condition, Object[] values) {
		return categoryMapper.sum(parameter, SQLUtil.fillCondition(condition, values), null, null, -1, -1, false);
	}

	@Override
	public double sum(String parameter, String order, String sort, int curPage, int limit) {
		return categoryMapper.sum(parameter, null, order, sort, SQLUtil.getOffset(curPage, limit), limit, false);
	}

	@Override
	public double sum(String parameter, String condition, Object[] values, int curPage, int limit) {
		return categoryMapper.sum(parameter, SQLUtil.fillCondition(condition, values), null, null, SQLUtil.getOffset(curPage, limit), limit, false);
	}

	@Override
	public double sum(String parameter, String condition, Object[] values, String order, String sort, int curPage, int limit) {
		return categoryMapper.sum(parameter, SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit, false);
	}

	@Override
	public double sum(String parameter, String condition, Object[] values, String order, String sort, int curPage, int limit, boolean isDistinct) {
		return categoryMapper.sum(parameter, SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit, isDistinct);
	}
}
