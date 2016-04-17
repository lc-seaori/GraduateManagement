package com.benson.graduate.stu.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.benson.graduate.base.dao.impl.BaseDaoImpl;
import com.benson.graduate.stu.dao.ClazzDao;
import com.benson.graduate.stu.model.Clazz;

@Repository("clazzDao")
public class ClazzDaoImpl extends BaseDaoImpl<Clazz> implements ClazzDao {

	@Override
	public Long getClazzsCount(String hql) {
		Query query=getSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	@Override
	public void deleteClazz(List<Integer> ids) {
		for(int i=0;i<ids.size();i++){
			String hql="delete from Clazz c where c.id=?";
			Query query=getSession().createQuery(hql);
			query.setParameter(0, ids.get(i));
			query.executeUpdate();
		}
	}
}
