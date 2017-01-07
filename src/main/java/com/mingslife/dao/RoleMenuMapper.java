package com.mingslife.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingslife.model.RoleMenu;

public interface RoleMenuMapper {
	int deleteByPrimaryKey(Integer id);
	int insert(RoleMenu record);
	int insertSelective(RoleMenu record);
	RoleMenu selectByPrimaryKey(Integer id);
	int updateByPrimaryKeySelective(RoleMenu record);
	int updateByPrimaryKey(RoleMenu record);
	List<RoleMenu> select(@Param("parameters") String parameters, @Param("condition") String condition, @Param("order") String order, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit);
	long count(@Param("parameters") String parameters, @Param("condition") String condition, @Param("isDistinct") boolean isDistinct);
	double sum(@Param("parameter") String parameter, @Param("condition") String condition, @Param("order") String order, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit, @Param("isDistinct") boolean isDistinct);
	RoleMenu find(@Param("id") Integer id, @Param("parameters") String parameters);
}
