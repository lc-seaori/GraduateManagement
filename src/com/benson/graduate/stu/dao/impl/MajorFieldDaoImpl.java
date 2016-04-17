package com.benson.graduate.stu.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.benson.graduate.base.dao.impl.BaseDaoImpl;
import com.benson.graduate.stu.dao.MajorFieldDao;
import com.benson.graduate.stu.model.MajorField;

@Repository("majorFieldDao")
public class MajorFieldDaoImpl extends BaseDaoImpl<MajorField> implements
		MajorFieldDao {

	@Override
	public Long getMajorsCount(String hql) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	@Override
	public void deleteMajor(List<Integer> ids) {
		// TODO Auto-generated method stub
		for(int i=0;i<ids.size();i++){
			String hql="delete from MajorField m where m.id=?";
			Query query=getSession().createQuery(hql);
			query.setParameter(0, ids.get(i));
			query.executeUpdate();
		}
	}
	
}
