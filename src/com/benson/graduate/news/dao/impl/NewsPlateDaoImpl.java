package com.benson.graduate.news.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import com.benson.graduate.base.dao.impl.BaseDaoImpl;
import com.benson.graduate.news.dao.NewsPlateDao;
import com.benson.graduate.news.model.NewsPlate;

@Repository("newsPlateDao")
public class NewsPlateDaoImpl extends BaseDaoImpl<NewsPlate> implements
		NewsPlateDao {

	@Override
	public Long getNewsPlateCount(String hql, Object... objects) {
		Query query=getSession().createQuery(hql);
		for(int i=0;i<objects.length;i++){
			query.setParameter(i, objects[i]);
		}
		return (Long) query.uniqueResult();
	}
	
	@Override
	public int getPlateCount(String sql, Object... objects) {
		SQLQuery query = this.getSession().createSQLQuery(sql);
		if(objects!=null){
			for(int i=0;i<objects.length;i++){
				query.setParameter(i, objects[i]);
			}
		}
			return Integer.parseInt(query.list().get(0).toString());
	}

	@Override
	public boolean updateNewsPlate(String sql, Object... objects) {
		SQLQuery query = this.getSession().createSQLQuery(sql);
		if(objects!=null){
			for(int i=0;i<objects.length;i++){
				query.setParameter(i, objects[i]);
			}
		}
		int result = query.executeUpdate();
		if(result == 0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public void deleteNewsPlates(List<Integer> ids) {
		for(int i=0;i<ids.size();i++){
			String hql="delete from NewsPlate n where n.id=?";
			Query query=getSession().createQuery(hql);
			query.setParameter(0, ids.get(i));
			query.executeUpdate();
		}
	}

}
