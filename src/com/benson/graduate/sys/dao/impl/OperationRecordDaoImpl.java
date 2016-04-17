package com.benson.graduate.sys.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.benson.graduate.base.dao.impl.BaseDaoImpl;
import com.benson.graduate.sys.dao.OperationRecordDao;
import com.benson.graduate.sys.model.OperationRecord;

@Repository("operationRecordDao")
public class OperationRecordDaoImpl extends BaseDaoImpl<OperationRecord>
		implements OperationRecordDao {

	@Override
	public Long getOperationRecordsCount(String hql) {
		// TODO Auto-generated method stub
		SQLQuery query = this.getSession().createSQLQuery(hql);
		return Long.valueOf(query.uniqueResult().toString());
	}

	@Override
	public void deleteRecord(List<Integer> ids) {
		// TODO Auto-generated method stub
		for(int i=0;i<ids.size();i++){
			String hql="delete from OperationRecord r where r.id=?";
			Query query=getSession().createQuery(hql);
			query.setParameter(0, ids.get(i));
			query.executeUpdate();
		}
	}

	
}
