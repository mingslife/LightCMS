package com.mingslife.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingslife.model.Menu;

public interface MenuMapper {
	int deleteByPrimaryKey(Integer id);
	int insert(Menu record);
	int insertSelective(Menu record);
	Menu selectByPrimaryKey(Integer id);
	int updateByPrimaryKeySelective(Menu record);
	int updateByPrimaryKey(Menu record);
	List<Menu> select(@Param("parameters") String parameters, @Param("condition") String condition, @Param("order") String order, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit);
	long count(@Param("parameters") String parameters, @Param("condition") String condition, @Param("isDistinct") boolean isDistinct);
	double sum(@Param("parameter") String parameter, @Param("condition") String condition, @Param("order") String order, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit, @Param("isDistinct") boolean isDistinct);
	Menu find(@Param("id") Integer id, @Param("parameters") String parameters);

	List<Menu> loadParentsByRoleId(@Param("roleId") Integer roleId);
	List<Menu> loadChildrenByParentIdAndRoleId(@Param("parentId") Integer parentId, @Param("roleId") Integer roleId);
}
