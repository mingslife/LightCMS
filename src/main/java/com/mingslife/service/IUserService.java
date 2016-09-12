package com.mingslife.service;

import java.util.List;

import com.mingslife.model.User;

public interface IUserService {
	void save(User user);
	void update(User user);
	void delete(User user);
	void delete(Integer id);
	User find(Integer id);
	User find(Integer id, String[] parameters);
	List<User> load();
	List<User> load(String[] parameters);
	List<User> load(String condition, Object[] values);
	List<User> load(String[] parameters, String condition, Object[] values);
	List<User> load(int curPage, int limit);
	List<User> load(String[] parameters, int curPage, int limit);
	List<User> load(String condition, Object[] values, int curPage, int limit);
	List<User> load(String[] parameters, String condition, Object[] values, int curPage, int limit);
	List<User> load(String condition, Object[] values, String order, String sort);
	List<User> load(String[] parameters, String condition, Object[] values, String order, String sort);
	List<User> load(String order, String sort, int curPage, int limit);
	List<User> load(String[] parameters, String order, String sort, int curPage, int limit);
	List<User> load(String condition, Object[] values, String order, String sort, int curPage, int limit);
	List<User> load(String[] parameters, String condition, Object[] values, String order, String sort, int curPage, int limit);
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
