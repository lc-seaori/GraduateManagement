package com.benson.graduate.sys.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.benson.graduate.base.dao.impl.BaseDaoImpl;
import com.benson.graduate.sys.dao.UserDao;
import com.benson.graduate.sys.model.User;

/**
 * 对数据库关于用户的增删改查等操作
 * @author benson
 *
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public Long getUsersCount(String hql) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	@Override
	public void deleteUser(List<Integer> ids) {
		// TODO Auto-generated method stub
		for(int i=0;i<ids.size();i++){
			String hql="delete from User s where s.id=?";
			Query query=getSession().createQuery(hql);
			query.setParameter(0, ids.get(i));
			query.executeUpdate();
		}
	}
}
