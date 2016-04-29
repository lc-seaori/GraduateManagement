package com.benson.graduate.company.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.benson.graduate.base.dao.impl.BaseDaoImpl;
import com.benson.graduate.company.dao.RecruitmentInfoDao;
import com.benson.graduate.company.model.RecruitmentInfo;

@Repository("recruitmentInfoDao")
public class RecruitmentInfoDaoImpl extends BaseDaoImpl<RecruitmentInfo>
		implements RecruitmentInfoDao {
	@Override
	public Long getRecruitmentInfosCount(String hql) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}
	
	@Override
	public void deleteRecruitmentInfos(List<Integer> ids) {
		// TODO Auto-generated method stub
		for(int i=0;i<ids.size();i++){
			String hql="delete from RecruitmentInfo r where r.id=?";
			Query query=getSession().createQuery(hql);
			query.setParameter(0, ids.get(i));
			query.executeUpdate();
		}
	}
	
	@Override
	public long getInfoCount(String hql, Object... objects) {
		Query query=getSession().createQuery(hql);
		for(int i=0;i<objects.length;i++){
			query.setParameter(i, objects[i]);
		}
		return (long) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RecruitmentInfo> findEntityListBySql(String sql, Object... objects) {
		SQLQuery query = this.getSession().createSQLQuery(sql).addEntity(RecruitmentInfo.class);
		if(objects!=null){
			for(int i=0;i<objects.length;i++){
				query.setParameter(i, objects[i]);
			}
		}
		return query.list();
	}
}
