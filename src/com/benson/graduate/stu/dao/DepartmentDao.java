package com.benson.graduate.stu.dao;

import java.util.List;

import com.benson.graduate.base.dao.BaseDao;
import com.benson.graduate.stu.model.Department;

/**
 * 学院Dao接口，对数据库的响应操作
 */
public interface DepartmentDao extends BaseDao<Department> {
	
	/**
	 * 根据hql计算数据数目
	 */
	public Long getDepartmentsCount(String hql);
	
	/**
	 * 批量删除学院
	 */
	public void deleteDepartment(List<Integer> ids);
	
}
