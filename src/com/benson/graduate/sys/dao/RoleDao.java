package com.benson.graduate.sys.dao;

import java.util.List;

import com.benson.graduate.base.dao.BaseDao;
import com.benson.graduate.sys.model.Role;

public interface RoleDao extends BaseDao<Role> {

	/**
	 * 获取角色总数(根据hql)
	 */
	public Long getRolesCount(String hql);
	
	/**
	 * 批量删除角色
	 */
	public void deleteRole(List<Integer> ids);
}
