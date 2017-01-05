package com.mingslife.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingslife.dao.MenuMapper;
import com.mingslife.model.Menu;
import com.mingslife.service.IMenuService;
import com.mingslife.web.util.SQLUtil;

@Service
public class MenuService implements IMenuService {
	@Autowired
	private MenuMapper menuMapper;

	@Override
	public void save(Menu menu) {
		menuMapper.insert(menu);
	}

	@Override
	public void update(Menu menu) {
		menuMapper.updateByPrimaryKeySelective(menu);
	}

	@Override
	public void delete(Menu menu) {
		menuMapper.deleteByPrimaryKey(menu.getId());
	}

	@Override
	public void delete(Integer id) {
		menuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Menu find(Integer id) {
		return menuMapper.selectByPrimaryKey(id);
	}

	@Override
	public Menu find(Integer id, String[] parameters) {
		return menuMapper.find(id, SQLUtil.formatParameters(parameters));
	}

	@Override
	public List<Menu> load() {
		return menuMapper.select(null, null, null, null, -1, -1);
	}

	@Override
	public List<Menu> load(String[] parameters) {
		return menuMapper.select(SQLUtil.formatParameters(parameters), null, null, null, -1, -1);
	}

	@Override
	public List<Menu> load(String condition, Object[] values) {
		return menuMapper.select(null, SQLUtil.fillCondition(condition, values), null, null, -1, -1);
	}

	@Override
	public List<Menu> load(String[] parameters, String condition, Object[] values) {
		return menuMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), null, null, -1, -1);
	}

	@Override
	public List<Menu> load(int curPage, int limit) {
		return menuMapper.select(null, null, null, null, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Menu> load(String[] parameters, int curPage, int limit) {
		return menuMapper.select(SQLUtil.formatParameters(parameters), null, null, null, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Menu> load(String condition, Object[] values, int curPage, int limit) {
		return menuMapper.select(null, SQLUtil.fillCondition(condition, values), null, null, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Menu> load(String[] parameters, String condition, Object[] values, int curPage, int limit) {
		return menuMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), null, null, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Menu> load(String condition, Object[] values, String order, String sort) {
		return menuMapper.select(null, SQLUtil.fillCondition(condition, values), order, sort, -1, -1);
	}

	@Override
	public List<Menu> load(String[] parameters, String condition, Object[] values, String order, String sort) {
		return menuMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), order, sort, -1, -1);
	}

	@Override
	public List<Menu> load(String order, String sort, int curPage, int limit) {
		return menuMapper.select(null, null, order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Menu> load(String[] parameters, String order, String sort, int curPage, int limit) {
		return menuMapper.select(SQLUtil.formatParameters(parameters), null, order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Menu> load(String condition, Object[] values, String order, String sort, int curPage, int limit) {
		return menuMapper.select(null, SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Menu> load(String[] parameters, String condition, Object[] values, String order, String sort, int curPage, int limit) {
		return menuMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public long count() {
		return menuMapper.count(null, null, false);
	}

	@Override
	public long count(String condition, Object[] values) {
		return menuMapper.count(null, SQLUtil.fillCondition(condition, values), false);
	}

	@Override
	public long count(String[] parameters, boolean isDistinct) {
		return menuMapper.count(SQLUtil.formatParameters(parameters), null, isDistinct);
	}

	@Override
	public long count(String[] parameters, String condition, Object[] values, boolean isDistinct) {
		return menuMapper.count(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), isDistinct);
	}

	@Override
	public double sum(String parameter) {
		return menuMapper.sum(parameter, null, null, null, -1, -1, false);
	}

	@Override
	public double sum(String parameter, String condition, Object[] values) {
		return menuMapper.sum(parameter, SQLUtil.fillCondition(condition, values), null, null, -1, -1, false);
	}

	@Override
	public double sum(String parameter, String order, String sort, int curPage, int limit) {
		return menuMapper.sum(parameter, null, order, sort, SQLUtil.getOffset(curPage, limit), limit, false);
	}

	@Override
	public double sum(String parameter, String condition, Object[] values, int curPage, int limit) {
		return menuMapper.sum(parameter, SQLUtil.fillCondition(condition, values), null, null, SQLUtil.getOffset(curPage, limit), limit, false);
	}

	@Override
	public double sum(String parameter, String condition, Object[] values, String order, String sort, int curPage, int limit) {
		return menuMapper.sum(parameter, SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit, false);
	}

	@Override
	public double sum(String parameter, String condition, Object[] values, String order, String sort, int curPage, int limit, boolean isDistinct) {
		return menuMapper.sum(parameter, SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit, isDistinct);
	}
}
