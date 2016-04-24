package com.benson.graduate.news.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.benson.graduate.base.dao.impl.BaseDaoImpl;
import com.benson.graduate.news.dao.NewsNnDao;
import com.benson.graduate.news.model.NewsNn;

@Repository("newsNnDao")
public class NewsNnDaoImpl extends BaseDaoImpl<NewsNn> implements
		NewsNnDao {

	@Override
	public long getNewsNnCount(String hql, Object... objects) {
		Query query=getSession().createQuery(hql);
		for(int i=0;i<objects.length;i++){
			query.setParameter(i, objects[i]);
		}
		return (long) query.uniqueResult();
	}
	
	@Override
	public void deleteNewsNns(List<Integer> ids) {
		for(int i=0;i<ids.size();i++){
			String hql="delete from NewsNn n where n.id=?";
			Query query=getSession().createQuery(hql);
			query.setParameter(0, ids.get(i));
			query.executeUpdate();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NewsNn> findEntityListBySql(String sql, Object... objects) {
		SQLQuery query = this.getSession().createSQLQuery(sql).addEntity(NewsNn.class);
		if(objects!=null){
			for(int i=0;i<objects.length;i++){
				query.setParameter(i, objects[i]);
			}
		}
		return query.list();
	}

}
