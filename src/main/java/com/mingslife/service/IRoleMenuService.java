package com.mingslife.service;

import java.util.List;

import com.mingslife.model.RoleMenu;

public interface IRoleMenuService {
	void save(RoleMenu roleMenu);
	void update(RoleMenu roleMenu);
	void delete(RoleMenu roleMenu);
	void delete(Integer id);
	RoleMenu find(Integer id);
	RoleMenu find(Integer id, String[] parameters);
	List<RoleMenu> load();
	List<RoleMenu> load(String[] parameters);
	List<RoleMenu> load(String condition, Object[] values);
	List<RoleMenu> load(String[] parameters, String condition, Object[] values);
	List<RoleMenu> load(int curPage, int limit);
	List<RoleMenu> load(String[] parameters, int curPage, int limit);
	List<RoleMenu> load(String condition, Object[] values, int curPage, int limit);
	List<RoleMenu> load(String[] parameters, String condition, Object[] values, int curPage, int limit);
	List<RoleMenu> load(String condition, Object[] values, String order, String sort);
	List<RoleMenu> load(String[] parameters, String condition, Object[] values, String order, String sort);
	List<RoleMenu> load(String order, String sort, int curPage, int limit);
	List<RoleMenu> load(String[] parameters, String order, String sort, int curPage, int limit);
	List<RoleMenu> load(String condition, Object[] values, String order, String sort, int curPage, int limit);
	List<RoleMenu> load(String[] parameters, String condition, Object[] values, String order, String sort, int curPage, int limit);
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
