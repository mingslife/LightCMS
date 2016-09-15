package com.mingslife.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingslife.model.Article;
import com.mingslife.pojo.ArticleForArticlePOJO;
import com.mingslife.pojo.ArticleForBlogPOJO;

public interface ArticleMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Article record);

	int insertSelective(Article record);

	Article selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Article record);

	int updateByPrimaryKey(Article record);

	/**
	 * 查询
	 * 
	 * @param parameters 查询字段
	 * @param condition 条件
	 * @param order 排序字段
	 * @param sort 排序方式
	 * @param offset 起始位置
	 * @param limit 查询条数
	 * @return 实体列表
	 */
	List<Article> select(@Param("parameters") String parameters, @Param("condition") String condition, @Param("order") String order, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit);

	/**
	 * 计数
	 * 
	 * @param parameters 统计字段
	 * @param condition 条件
	 * @param isDistinct 是否去重
	 * @return 计数结果
	 */
	long count(@Param("parameters") String parameters, @Param("condition") String condition, @Param("isDistinct") boolean isDistinct);

	/**
	 * 求和
	 * 
	 * @param parameter 统计字段
	 * @param condition 条件
	 * @param order 排序字段
	 * @param sort 排序方式
	 * @param offset 起始位置
	 * @param limit 查询条数
	 * @param isDistinct 是否去重
	 * @return 求和结果
	 */
	double sum(@Param("parameter") String parameter, @Param("condition") String condition, @Param("order") String order, @Param("sort") String sort, @Param("offset") int offset, @Param("limit") int limit, @Param("isDistinct") boolean isDistinct);

	Article find(@Param("id") Integer id, @Param("parameters") String parameters);

	List<ArticleForBlogPOJO> loadForBlog(@Param("offset") int offset, @Param("limit") int limit);
	long countForBlog();
	ArticleForArticlePOJO findByUuidForArticle(@Param("uuid") String uuid);
	List<Article> loadForMenu(@Param("limit") int limit);
}
