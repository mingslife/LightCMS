package com.mingslife.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mingslife.dao.ArticleMapper;
import com.mingslife.model.Article;
import com.mingslife.pojo.ArticleForArticlePOJO;
import com.mingslife.pojo.ArticleForBlogPOJO;
import com.mingslife.service.IArticleService;
import com.mingslife.web.util.SQLUtil;

@Service
public class ArticleService implements IArticleService {
	@Autowired
	private ArticleMapper articleMapper;
	
	@Override
	public void save(Article article) {
		articleMapper.insert(article);
	}
	
	@Override
	public void update(Article article) {
		articleMapper.updateByPrimaryKeySelective(article);
	}
	
	@Override
	public void delete(Article article) {
		articleMapper.deleteByPrimaryKey(article.getId());
	}
	
	@Override
	public void delete(Integer id) {
		articleMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public Article find(Integer id) {
		return articleMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public Article find(Integer id, String[] parameters) {
		return articleMapper.find(id, SQLUtil.formatParameters(parameters));
	}
	
	@Override
	public List<Article> load() {
		return articleMapper.select(null, null, null, null, -1, -1);
	}
	
	@Override
	public List<Article> load(String[] parameters) {
		return articleMapper.select(SQLUtil.formatParameters(parameters), null, null, null, -1, -1);
	}
	
	@Override
	public List<Article> load(String condition, Object[] values) {
		return articleMapper.select(null, SQLUtil.fillCondition(condition, values), null, null, -1, -1);
	}
	
	@Override
	public List<Article> load(String[] parameters, String condition, Object[] values) {
		return articleMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), null, null, -1, -1);
	}
	
	@Override
	public List<Article> load(int curPage, int limit) {
		return articleMapper.select(null, null, null, null, SQLUtil.getOffset(curPage, limit), limit);
	}
	
	@Override
	public List<Article> load(String[] parameters, int curPage, int limit) {
		return articleMapper.select(SQLUtil.formatParameters(parameters), null, null, null, SQLUtil.getOffset(curPage, limit), limit);
	}
	
	@Override
	public List<Article> load(String condition, Object[] values, int curPage, int limit) {
		return articleMapper.select(null, SQLUtil.fillCondition(condition, values), null, null, SQLUtil.getOffset(curPage, limit), limit);
	}
	
	@Override
	public List<Article> load(String[] parameters, String condition, Object[] values, int curPage, int limit) {
		return articleMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), null, null, SQLUtil.getOffset(curPage, limit), limit);
	}
	
	@Override
	public List<Article> load(String condition, Object[] values, String order, String sort) {
		return articleMapper.select(null, SQLUtil.fillCondition(condition, values), order, sort, -1, -1);
	}
	
	@Override
	public List<Article> load(String[] parameters, String condition, Object[] values, String order, String sort) {
		return articleMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), order, sort, -1, -1);
	}
	
	@Override
	public List<Article> load(String order, String sort, int curPage, int limit) {
		return articleMapper.select(null, null, order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}
	
	@Override
	public List<Article> load(String[] parameters, String order, String sort, int curPage, int limit) {
		return articleMapper.select(SQLUtil.formatParameters(parameters), null, order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}
	
	@Override
	public List<Article> load(String condition, Object[] values, String order, String sort, int curPage, int limit) {
		return articleMapper.select(null, SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}
	
	@Override
	public List<Article> load(String[] parameters, String condition, Object[] values, String order, String sort, int curPage, int limit) {
		return articleMapper.select(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit);
	}
	
	@Override
	public long count() {
		return articleMapper.count(null, null, false);
	}
	
	@Override
	public long count(String condition, Object[] values) {
		return articleMapper.count(null, SQLUtil.fillCondition(condition, values), false);
	}
	
	@Override
	public long count(String[] parameters, boolean isDistinct) {
		return articleMapper.count(SQLUtil.formatParameters(parameters), null, isDistinct);
	}
	
	@Override
	public long count(String[] parameters, String condition, Object[] values, boolean isDistinct) {
		return articleMapper.count(SQLUtil.formatParameters(parameters), SQLUtil.fillCondition(condition, values), isDistinct);
	}
	
	@Override
	public double sum(String parameter) {
		return articleMapper.sum(parameter, null, null, null, -1, -1, false);
	}
	
	@Override
	public double sum(String parameter, String condition, Object[] values) {
		return articleMapper.sum(parameter, SQLUtil.fillCondition(condition, values), null, null, -1, -1, false);
	}
	
	@Override
	public double sum(String parameter, String order, String sort, int curPage, int limit) {
		return articleMapper.sum(parameter, null, order, sort, SQLUtil.getOffset(curPage, limit), limit, false);
	}
	
	@Override
	public double sum(String parameter, String condition, Object[] values, int curPage, int limit) {
		return articleMapper.sum(parameter, SQLUtil.fillCondition(condition, values), null, null, SQLUtil.getOffset(curPage, limit), limit, false);
	}
	
	@Override
	public double sum(String parameter, String condition, Object[] values, String order, String sort, int curPage, int limit) {
		return articleMapper.sum(parameter, SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit, false);
	}
	
	@Override
	public double sum(String parameter, String condition, Object[] values, String order, String sort, int curPage, int limit, boolean isDistinct) {
		return articleMapper.sum(parameter, SQLUtil.fillCondition(condition, values), order, sort, SQLUtil.getOffset(curPage, limit), limit, isDistinct);
	}

	@Override
	public List<ArticleForBlogPOJO> loadForBlog(int curPage, int limit) {
		return articleMapper.loadForBlog(SQLUtil.getOffset(curPage, limit), limit);
	}

	@Override
	public long countForBlog() {
		return articleMapper.countForBlog();
	}

	@Override
	public ArticleForArticlePOJO findByUUidForArticle(String uuid) {
		return articleMapper.findByUuidForArticle(uuid);
	}
}
