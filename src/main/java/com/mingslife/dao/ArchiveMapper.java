package com.mingslife.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingslife.model.Archive;

public interface ArchiveMapper {
	int deleteByPrimaryKey(Integer id);
	int insert(Archive record);
	int insertSelective(Archive record);
	Archive selectByPrimaryKey(Integer id);
	int updateByPrimaryKeySelective(Archive record);
	int updateByPrimaryKey(Archive record);
	List<Archive> select(@Param("parameters") String parameters, @Param("condition") String condition, @Param("order") String order, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit);
	long count(@Param("parameters") String parameters, @Param("condition") String condition, @Param("isDistinct") boolean isDistinct);
	double sum(@Param("parameter") String parameter, @Param("condition") String condition, @Param("order") String order, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit, @Param("isDistinct") boolean isDistinct);
	Archive find(@Param("id") Integer id, @Param("parameters") String parameters);
}
