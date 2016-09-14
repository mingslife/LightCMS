package com.mingslife.service;

import java.util.List;

import com.mingslife.model.Archive;

public interface IArchiveService {
	void save(Archive archive);
	void update(Archive archive);
	void delete(Archive archive);
	void delete(Integer id);
	Archive find(Integer id);
	Archive find(Integer id, String[] parameters);
	List<Archive> load();
	List<Archive> load(String[] parameters);
	List<Archive> load(String condition, Object[] values);
	List<Archive> load(String[] parameters, String condition, Object[] values);
	List<Archive> load(int curPage, int limit);
	List<Archive> load(String[] parameters, int curPage, int limit);
	List<Archive> load(String condition, Object[] values, int curPage, int limit);
	List<Archive> load(String[] parameters, String condition, Object[] values, int curPage, int limit);
	List<Archive> load(String condition, Object[] values, String order, String sort);
	List<Archive> load(String[] parameters, String condition, Object[] values, String order, String sort);
	List<Archive> load(String order, String sort, int curPage, int limit);
	List<Archive> load(String[] parameters, String order, String sort, int curPage, int limit);
	List<Archive> load(String condition, Object[] values, String order, String sort, int curPage, int limit);
	List<Archive> load(String[] parameters, String condition, Object[] values, String order, String sort, int curPage, int limit);
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
