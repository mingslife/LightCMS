package com.mingslife.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingslife.dao.ArchiveMapper;
import com.mingslife.model.Archive;
import com.mingslife.service.IArchiveService;
import com.mingslife.web.util.SQLUtil;

@Service
public class ArchiveService implements IArchiveService {
	@Autowired
	private ArchiveMapper archiveMapper;

	@Override
	public void save(Archive archive) {
		archiveMapper.insert(archive);
	}

	@Override
	public void update(Archive archive) {
		archiveMapper.updateByPrimaryKeySelective(archive);
	}

	@Override
	public void delete(Archive archive) {
		archiveMapper.deleteByPrimaryKey(archive.getId());
	}

	@Override
	public void delete(Integer id) {
		archiveMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Archive find(Integer id) {
		return archiveMapper.selectByPrimaryKey(id);
	}

	@Override
	public Archive find(Integer id, String[] parameters) {
		return archiveMapper.find(id, SQLUtil.formatParameters(parameters));
	}

	@Override
	public List<Archive> load() {
		return archiveMapper.select(null, null, null, null, -1, -1);
	}

	@Override
	public List<Archive> load(String[] parameters) {
		return archiveMapper.select(SQLUtil.formatParameters(parameters), null, null, null, -1, -1);
	}

	@Override
	public List<Archive> load(String condition, Object[] values) {
		return archiveMapper.select(null, SQLUtil.fillCondition(condition, values), null, null, -1, -1);
	}

	@Override
	public List<Archive> load(String[] parameters, String condition, Object[] values) {
		return archiveMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), null, null, -1, -1);
	}

	@Override
	public List<Archive> load(int curPage, int limit) {
		return archiveMapper.select(null, null, null, null, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Archive> load(String[] parameters, int curPage, int limit) {
		return archiveMapper.select(SQLUtil.formatParameters(parameters), null, null, null, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Archive> load(String condition, Object[] values, int curPage, int limit) {
		return archiveMapper.select(null, SQLUtil.fillCondition(condition, values), null, null, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Archive> load(String[] parameters, String condition, Object[] values, int curPage, int limit) {
		return archiveMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), null, null, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Archive> load(String condition, Object[] values, String order, String sort) {
		return archiveMapper.select(null, SQLUtil.fillCondition(condition, values), order, sort, -1, -1);
	}

	@Override
	public List<Archive> load(String[] parameters, String condition, Object[] values, String order, String sort) {
		return archiveMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), order, sort, -1, -1);
	}

	@Override
	public List<Archive> load(String order, String sort, int curPage, int limit) {
		return archiveMapper.select(null, null, order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Archive> load(String[] parameters, String order, String sort, int curPage, int limit) {
		return archiveMapper.select(SQLUtil.formatParameters(parameters), null, order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Archive> load(String condition, Object[] values, String order, String sort, int curPage, int limit) {
		return archiveMapper.select(null, SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Archive> load(String[] parameters, String condition, Object[] values, String order, String sort, int curPage, int limit) {
		return archiveMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public long count() {
		return archiveMapper.count(null, null, false);
	}

	@Override
	public long count(String condition, Object[] values) {
		return archiveMapper.count(null, SQLUtil.fillCondition(condition, values), false);
	}

	@Override
	public long count(String[] parameters, boolean isDistinct) {
		return archiveMapper.count(SQLUtil.formatParameters(parameters), null, isDistinct);
	}

	@Override
	public long count(String[] parameters, String condition, Object[] values, boolean isDistinct) {
		return archiveMapper.count(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), isDistinct);
	}

	@Override
	public double sum(String parameter) {
		return archiveMapper.sum(parameter, null, null, null, -1, -1, false);
	}

	@Override
	public double sum(String parameter, String condition, Object[] values) {
		return archiveMapper.sum(parameter, SQLUtil.fillCondition(condition, values), null, null, -1, -1, false);
	}

	@Override
	public double sum(String parameter, String order, String sort, int curPage, int limit) {
		return archiveMapper.sum(parameter, null, order, sort, SQLUtil.getOffset(curPage, limit), limit, false);
	}

	@Override
	public double sum(String parameter, String condition, Object[] values, int curPage, int limit) {
		return archiveMapper.sum(parameter, SQLUtil.fillCondition(condition, values), null, null, SQLUtil.getOffset(curPage, limit), limit, false);
	}

	@Override
	public double sum(String parameter, String condition, Object[] values, String order, String sort, int curPage, int limit) {
		return archiveMapper.sum(parameter, SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit, false);
	}

	@Override
	public double sum(String parameter, String condition, Object[] values, String order, String sort, int curPage, int limit, boolean isDistinct) {
		return archiveMapper.sum(parameter, SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit, isDistinct);
	}
}
