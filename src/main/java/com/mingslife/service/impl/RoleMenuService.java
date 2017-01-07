package com.mingslife.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingslife.dao.RoleMenuMapper;
import com.mingslife.model.RoleMenu;
import com.mingslife.service.IRoleMenuService;
import com.mingslife.web.util.SQLUtil;

@Service
public class RoleMenuService implements IRoleMenuService {
	@Autowired
	private RoleMenuMapper roleMenuMapper;

	@Override
	public void save(RoleMenu roleMenu) {
		roleMenuMapper.insert(roleMenu);
	}

	@Override
	public void update(RoleMenu roleMenu) {
		roleMenuMapper.updateByPrimaryKeySelective(roleMenu);
	}

	@Override
	public void delete(RoleMenu roleMenu) {
		roleMenuMapper.deleteByPrimaryKey(roleMenu.getId());
	}

	@Override
	public void delete(Integer id) {
		roleMenuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public RoleMenu find(Integer id) {
		return roleMenuMapper.selectByPrimaryKey(id);
	}

	@Override
	public RoleMenu find(Integer id, String[] parameters) {
		return roleMenuMapper.find(id, SQLUtil.formatParameters(parameters));
	}

	@Override
	public List<RoleMenu> load() {
		return roleMenuMapper.select(null, null, null, null, -1, -1);
	}

	@Override
	public List<RoleMenu> load(String[] parameters) {
		return roleMenuMapper.select(SQLUtil.formatParameters(parameters), null, null, null, -1, -1);
	}

	@Override
	public List<RoleMenu> load(String condition, Object[] values) {
		return roleMenuMapper.select(null, SQLUtil.fillCondition(condition, values), null, null, -1, -1);
	}

	@Override
	public List<RoleMenu> load(String[] parameters, String condition, Object[] values) {
		return roleMenuMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), null, null, -1, -1);
	}

	@Override
	public List<RoleMenu> load(int curPage, int limit) {
		return roleMenuMapper.select(null, null, null, null, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<RoleMenu> load(String[] parameters, int curPage, int limit) {
		return roleMenuMapper.select(SQLUtil.formatParameters(parameters), null, null, null, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<RoleMenu> load(String condition, Object[] values, int curPage, int limit) {
		return roleMenuMapper.select(null, SQLUtil.fillCondition(condition, values), null, null, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<RoleMenu> load(String[] parameters, String condition, Object[] values, int curPage, int limit) {
		return roleMenuMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), null, null, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<RoleMenu> load(String condition, Object[] values, String order, String sort) {
		return roleMenuMapper.select(null, SQLUtil.fillCondition(condition, values), order, sort, -1, -1);
	}

	@Override
	public List<RoleMenu> load(String[] parameters, String condition, Object[] values, String order, String sort) {
		return roleMenuMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), order, sort, -1, -1);
	}

	@Override
	public List<RoleMenu> load(String order, String sort, int curPage, int limit) {
		return roleMenuMapper.select(null, null, order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<RoleMenu> load(String[] parameters, String order, String sort, int curPage, int limit) {
		return roleMenuMapper.select(SQLUtil.formatParameters(parameters), null, order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<RoleMenu> load(String condition, Object[] values, String order, String sort, int curPage, int limit) {
		return roleMenuMapper.select(null, SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<RoleMenu> load(String[] parameters, String condition, Object[] values, String order, String sort, int curPage, int limit) {
		return roleMenuMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public long count() {
		return roleMenuMapper.count(null, null, false);
	}

	@Override
	public long count(String condition, Object[] values) {
		return roleMenuMapper.count(null, SQLUtil.fillCondition(condition, values), false);
	}

	@Override
	public long count(String[] parameters, boolean isDistinct) {
		return roleMenuMapper.count(SQLUtil.formatParameters(parameters), null, isDistinct);
	}

	@Override
	public long count(String[] parameters, String condition, Object[] values, boolean isDistinct) {
		return roleMenuMapper.count(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), isDistinct);
	}

	@Override
	public double sum(String parameter) {
		return roleMenuMapper.sum(parameter, null, null, null, -1, -1, false);
	}

	@Override
	public double sum(String parameter, String condition, Object[] values) {
		return roleMenuMapper.sum(parameter, SQLUtil.fillCondition(condition, values), null, null, -1, -1, false);
	}

	@Override
	public double sum(String parameter, String order, String sort, int curPage, int limit) {
		return roleMenuMapper.sum(parameter, null, order, sort, SQLUtil.getOffset(curPage, limit), limit, false);
	}

	@Override
	public double sum(String parameter, String condition, Object[] values, int curPage, int limit) {
		return roleMenuMapper.sum(parameter, SQLUtil.fillCondition(condition, values), null, null, SQLUtil.getOffset(curPage, limit), limit, false);
	}

	@Override
	public double sum(String parameter, String condition, Object[] values, String order, String sort, int curPage, int limit) {
		return roleMenuMapper.sum(parameter, SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit, false);
	}

	@Override
	public double sum(String parameter, String condition, Object[] values, String order, String sort, int curPage, int limit, boolean isDistinct) {
		return roleMenuMapper.sum(parameter, SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit, isDistinct);
	}
}
