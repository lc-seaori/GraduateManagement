package com.benson.graduate.stu.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.benson.graduate.base.dao.impl.BaseDaoImpl;
import com.benson.graduate.stu.dao.GraduateInfoDao;
import com.benson.graduate.stu.model.GraduateInfo;

@Repository("graduateInfoDao")
public class GraduateInfoDaoImpl extends BaseDaoImpl<GraduateInfo> implements
		GraduateInfoDao {
	@Override
	public Long getGraduateInfosCount(String hql) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}
	
	@Override
	public void deleteGraduateInfo(List<Integer> ids) {
		// TODO Auto-generated method stub
		for(int i=0;i<ids.size();i++){
			String hql="delete from GraduateInfo s where s.id=?";
			Query query=getSession().createQuery(hql);
			query.setParameter(0, ids.get(i));
			query.executeUpdate();
		}
	}
	
	@Override
	public boolean addGraduateInfosByList(List<GraduateInfo> graduateInfo){
		try {
			for(GraduateInfo g:graduateInfo){
				getSession().save(g);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
