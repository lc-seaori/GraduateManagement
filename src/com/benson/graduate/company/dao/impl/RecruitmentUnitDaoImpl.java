package com.benson.graduate.company.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.benson.graduate.base.dao.impl.BaseDaoImpl;
import com.benson.graduate.company.dao.RecruitmentUnitDao;
import com.benson.graduate.company.model.RecruitmentUnit;

@Repository("recruitmentUnitDao")
public class RecruitmentUnitDaoImpl extends BaseDaoImpl<RecruitmentUnit>
		implements RecruitmentUnitDao {

	@Override
	public Long getRecruitmentUnitsCount(String hql) {
		// TODO Auto-generated method stub
		Query query =getSession().createQuery(hql);
		return (Long)query.uniqueResult();
	}

	@Override
	public void deleteRecruitmentUnits(List<Integer> ids) {
		// TODO Auto-generated method stub
		for(int i=0;i<ids.size();i++){
			String hql="delete from RecruitmentUnit r where r.id=?";
			Query query=getSession().createQuery(hql);
			query.setParameter(0, ids.get(i));
			query.executeUpdate();
		}
	}

}
