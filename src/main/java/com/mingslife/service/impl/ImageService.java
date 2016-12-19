package com.mingslife.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingslife.dao.ImageMapper;
import com.mingslife.model.Image;
import com.mingslife.service.IImageService;
import com.mingslife.web.util.SQLUtil;

@Service
public class ImageService implements IImageService {
	@Autowired
	private ImageMapper imageMapper;

	@Override
	public void save(Image image) {
		imageMapper.insert(image);
	}

	@Override
	public void update(Image image) {
		imageMapper.updateByPrimaryKeySelective(image);
	}

	@Override
	public void delete(Image image) {
		imageMapper.deleteByPrimaryKey(image.getId());
	}

	@Override
	public void delete(Integer id) {
		imageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Image find(Integer id) {
		return imageMapper.selectByPrimaryKey(id);
	}

	@Override
	public Image find(Integer id, String[] parameters) {
		return imageMapper.find(id, SQLUtil.formatParameters(parameters));
	}

	@Override
	public List<Image> load() {
		return imageMapper.select(null, null, null, null, -1, -1);
	}

	@Override
	public List<Image> load(String[] parameters) {
		return imageMapper.select(SQLUtil.formatParameters(parameters), null, null, null, -1, -1);
	}

	@Override
	public List<Image> load(String condition, Object[] values) {
		return imageMapper.select(null, SQLUtil.fillCondition(condition, values), null, null, -1, -1);
	}

	@Override
	public List<Image> load(String[] parameters, String condition, Object[] values) {
		return imageMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), null, null, -1, -1);
	}

	@Override
	public List<Image> load(int curPage, int limit) {
		return imageMapper.select(null, null, null, null, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Image> load(String[] parameters, int curPage, int limit) {
		return imageMapper.select(SQLUtil.formatParameters(parameters), null, null, null, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Image> load(String condition, Object[] values, int curPage, int limit) {
		return imageMapper.select(null, SQLUtil.fillCondition(condition, values), null, null, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Image> load(String[] parameters, String condition, Object[] values, int curPage, int limit) {
		return imageMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), null, null, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Image> load(String condition, Object[] values, String order, String sort) {
		return imageMapper.select(null, SQLUtil.fillCondition(condition, values), order, sort, -1, -1);
	}

	@Override
	public List<Image> load(String[] parameters, String condition, Object[] values, String order, String sort) {
		return imageMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), order, sort, -1, -1);
	}

	@Override
	public List<Image> load(String order, String sort, int curPage, int limit) {
		return imageMapper.select(null, null, order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Image> load(String[] parameters, String order, String sort, int curPage, int limit) {
		return imageMapper.select(SQLUtil.formatParameters(parameters), null, order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Image> load(String condition, Object[] values, String order, String sort, int curPage, int limit) {
		return imageMapper.select(null, SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public List<Image> load(String[] parameters, String condition, Object[] values, String order, String sort, int curPage, int limit) {
		return imageMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public long count() {
		return imageMapper.count(null, null, false);
	}

	@Override
	public long count(String condition, Object[] values) {
		return imageMapper.count(null, SQLUtil.fillCondition(condition, values), false);
	}

	@Override
	public long count(String[] parameters, boolean isDistinct) {
		return imageMapper.count(SQLUtil.formatParameters(parameters), null, isDistinct);
	}

	@Override
	public long count(String[] parameters, String condition, Object[] values, boolean isDistinct) {
		return imageMapper.count(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), isDistinct);
	}

	@Override
	public double sum(String parameter) {
		return imageMapper.sum(parameter, null, null, null, -1, -1, false);
	}

	@Override
	public double sum(String parameter, String condition, Object[] values) {
		return imageMapper.sum(parameter, SQLUtil.fillCondition(condition, values), null, null, -1, -1, false);
	}

	@Override
	public double sum(String parameter, String order, String sort, int curPage, int limit) {
		return imageMapper.sum(parameter, null, order, sort, SQLUtil.getOffset(curPage, limit), limit, false);
	}

	@Override
	public double sum(String parameter, String condition, Object[] values, int curPage, int limit) {
		return imageMapper.sum(parameter, SQLUtil.fillCondition(condition, values), null, null, SQLUtil.getOffset(curPage, limit), limit, false);
	}

	@Override
	public double sum(String parameter, String condition, Object[] values, String order, String sort, int curPage, int limit) {
		return imageMapper.sum(parameter, SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit, false);
	}

	@Override
	public double sum(String parameter, String condition, Object[] values, String order, String sort, int curPage, int limit, boolean isDistinct) {
		return imageMapper.sum(parameter, SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit, isDistinct);
	}

	@Override
	public Image findByMd5(String md5) {
		return imageMapper.findByMd5(md5);
	}

	@Override
	public Image findBySourceMd5(String sourceMd5) {
		return imageMapper.findBySourceMd5(sourceMd5);
	}
}
