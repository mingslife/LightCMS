package com.mingslife.service;

import java.util.List;

import com.mingslife.model.Category;
import com.mingslife.pojo.CategorySimplePOJO;

public interface ICategoryService {
	void save(Category category);
	void update(Category category);
	void delete(Category category);
	void delete(Integer id);
	Category find(Integer id);
	Category find(Integer id, String[] parameters);
	List<Category> load();
	List<Category> load(String[] parameters);
	List<Category> load(String condition, Object[] values);
	List<Category> load(String[] parameters, String condition, Object[] values);
	List<Category> load(int curPage, int limit);
	List<Category> load(String[] parameters, int curPage, int limit);
	List<Category> load(String condition, Object[] values, int curPage, int limit);
	List<Category> load(String[] parameters, String condition, Object[] values, int curPage, int limit);
	List<Category> load(String condition, Object[] values, String order, String sort);
	List<Category> load(String[] parameters, String condition, Object[] values, String order, String sort);
	List<Category> load(String order, String sort, int curPage, int limit);
	List<Category> load(String[] parameters, String order, String sort, int curPage, int limit);
	List<Category> load(String condition, Object[] values, String order, String sort, int curPage, int limit);
	List<Category> load(String[] parameters, String condition, Object[] values, String order, String sort, int curPage, int limit);
	long count();
	long count(String condition, Object[] values);
	long count(String[] parameters, boolean isDistinct);
	long count(String[] parameters, String condition, Object[] values, boolean isDistinct);
	double sum(String parameter);
	double sum(String parameter, String condition, Object[] values);
	double sum(String parameter, String order, String sort, int curPage, int limit);
	double sum(String parameter, String condition, Object[] values, int curPage, int limit);
	double sum(String parameter, String condition, Object[] values, String order, String sort, int curPage, int limit);
	double sum(String parameter, String condition, Object[] values, String order, String sort, int curPage, int limit, boolean isDistinct);

	List<Category> loadForMenu(int limit);
	List<CategorySimplePOJO> loadAll();
}
