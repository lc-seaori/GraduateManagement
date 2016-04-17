package com.benson.graduate.sys.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.benson.graduate.base.dao.impl.BaseDaoImpl;
import com.benson.graduate.sys.dao.RoleDao;
import com.benson.graduate.sys.model.Role;

/**
 * 对数据库角色数据的操作
 * @author benson
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {
	
	@Override
	public Long getRolesCount(String hql) {
		Query query=getSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	@Override
	public void deleteRole(List<Integer> ids) {
		// TODO Auto-generated method stub
		for(int i=0;i<ids.size();i++){
			String hql="delete from Role r where r.id=?";
			Query query=getSession().createQuery(hql);
			query.setParameter(0, ids.get(i));
			query.executeUpdate();
		}
	}
	
}
