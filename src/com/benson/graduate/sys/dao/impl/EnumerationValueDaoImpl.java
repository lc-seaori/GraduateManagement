package com.benson.graduate.sys.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.benson.graduate.base.dao.impl.BaseDaoImpl;
import com.benson.graduate.sys.dao.EnumerationValueDao;
import com.benson.graduate.sys.model.EnumerationValue;

@SuppressWarnings("unchecked")
@Repository("enumerationValueDao")
public class EnumerationValueDaoImpl extends BaseDaoImpl<EnumerationValue>
		implements EnumerationValueDao {
	
	public List<EnumerationValue> getEnumerationValueByEnumerationName(
			int enumerationType) {
		String hql="from EnumerationValue ev where ev.enumeration.id=?";
		Query query=this.getSession().createQuery(hql);
		query.setParameter(0, enumerationType);
		return query.list();
	}
	
}
