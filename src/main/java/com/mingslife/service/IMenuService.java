package com.mingslife.service;

import java.util.List;

import com.mingslife.model.Menu;

public interface IMenuService {
	void save(Menu menu);
	void update(Menu menu);
	void delete(Menu menu);
	void delete(Integer id);
	Menu find(Integer id);
	Menu find(Integer id, String[] parameters);
	List<Menu> load();
	List<Menu> load(String[] parameters);
	List<Menu> load(String condition, Object[] values);
	List<Menu> load(String[] parameters, String condition, Object[] values);
	List<Menu> load(int curPage, int limit);
	List<Menu> load(String[] parameters, int curPage, int limit);
	List<Menu> load(String condition, Object[] values, int curPage, int limit);
	List<Menu> load(String[] parameters, String condition, Object[] values, int curPage, int limit);
	List<Menu> load(String condition, Object[] values, String order, String sort);
	List<Menu> load(String[] parameters, String condition, Object[] values, String order, String sort);
	List<Menu> load(String order, String sort, int curPage, int limit);
	List<Menu> load(String[] parameters, String order, String sort, int curPage, int limit);
	List<Menu> load(String condition, Object[] values, String order, String sort, int curPage, int limit);
	List<Menu> load(String[] parameters, String condition, Object[] values, String order, String sort, int curPage, int limit);
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
}
