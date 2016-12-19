package com.mingslife.service;

import java.util.List;

import com.mingslife.model.Image;

public interface IImageService {
	void save(Image image);
	void update(Image image);
	void delete(Image image);
	void delete(Integer id);
	Image find(Integer id);
	Image find(Integer id, String[] parameters);
	List<Image> load();
	List<Image> load(String[] parameters);
	List<Image> load(String condition, Object[] values);
	List<Image> load(String[] parameters, String condition, Object[] values);
	List<Image> load(int curPage, int limit);
	List<Image> load(String[] parameters, int curPage, int limit);
	List<Image> load(String condition, Object[] values, int curPage, int limit);
	List<Image> load(String[] parameters, String condition, Object[] values, int curPage, int limit);
	List<Image> load(String condition, Object[] values, String order, String sort);
	List<Image> load(String[] parameters, String condition, Object[] values, String order, String sort);
	List<Image> load(String order, String sort, int curPage, int limit);
	List<Image> load(String[] parameters, String order, String sort, int curPage, int limit);
	List<Image> load(String condition, Object[] values, String order, String sort, int curPage, int limit);
	List<Image> load(String[] parameters, String condition, Object[] values, String order, String sort, int curPage, int limit);
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

	Image findByMd5(String md5);
	Image findBySourceMd5(String sourceMd5);
}
