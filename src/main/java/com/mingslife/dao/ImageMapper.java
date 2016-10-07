package com.mingslife.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingslife.model.Image;

public interface ImageMapper {
	int deleteByPrimaryKey(Integer id);
	int insert(Image record);
	int insertSelective(Image record);
	Image selectByPrimaryKey(Integer id);
	int updateByPrimaryKeySelective(Image record);
	int updateByPrimaryKey(Image record);
	List<Image> select(@Param("parameters") String parameters, @Param("condition") String condition, @Param("order") String order, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit);
	long count(@Param("parameters") String parameters, @Param("condition") String condition, @Param("isDistinct") boolean isDistinct);
	double sum(@Param("parameter") String parameter, @Param("condition") String condition, @Param("order") String order, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit, @Param("isDistinct") boolean isDistinct);
	Image find(@Param("id") Integer id, @Param("parameters") String parameters);

	Image findByMd5(@Param("md5") String md5);
}
