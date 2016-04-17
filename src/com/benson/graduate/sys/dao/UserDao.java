package com.benson.graduate.sys.dao;

import java.util.List;

import com.benson.graduate.base.dao.BaseDao;
import com.benson.graduate.sys.model.User;

public interface UserDao extends BaseDao<User> {
	
	/**
	 * 根据hql计算记录数
	 */
	public Long getUsersCount(String hql);
	
	
	/**
	 * 根据多个用户id删除多个用户
	 */
	public void deleteUser(List<Integer> ids);
}
