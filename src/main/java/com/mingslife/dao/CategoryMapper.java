package com.mingslife.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingslife.model.Category;

public interface CategoryMapper {
	int deleteByPrimaryKey(Integer id);
	int insert(Category record);
	int insertSelective(Category record);
	Category selectByPrimaryKey(Integer id);
	int updateByPrimaryKeySelective(Category record);
	int updateByPrimaryKey(Category record);
	List<Category> select(@Param("parameters") String parameters, @Param("condition") String condition, @Param("order") String order, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit);
	long count(@Param("parameters") String parameters, @Param("condition") String condition, @Param("isDistinct") boolean isDistinct);
	double sum(@Param("parameter") String parameter, @Param("condition") String condition, @Param("order") String order, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit, @Param("isDistinct") boolean isDistinct);
	Category find(@Param("id") Integer id, @Param("parameters") String parameters);

	List<Category> loadForMenu(@Param("limit") int limit);
}
