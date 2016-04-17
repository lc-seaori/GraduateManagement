package com.benson.graduate.news.dao;

import java.util.List;

import com.benson.graduate.base.dao.BaseDao;
import com.benson.graduate.news.model.NewsPlate;

public interface NewsPlateDao extends BaseDao<NewsPlate> {
	/**
	 * 获取新闻板块总数
	 * @param hql
	 * @return
	 */
	public Long getNewsPlateCount(String hql, Object...objects);
	
	/**
	 * 获取新闻板块总数 sql
	 * @param sql
	 * @param objects
	 * @return
	 */
	public int getPlateCount(String sql, Object...objects);
	
	/**
	 * 更新
	 * @param sql
	 * @param objects
	 * @return
	 */
	boolean updateNewsPlate(String sql, Object...objects);
	
	/**
	 * 批量删除  hql
	 * @param ids
	 */
	void deleteNewsPlates(List<Integer> ids) ;
}
