package com.benson.graduate.stu.dao;

import java.util.List;

import com.benson.graduate.base.dao.BaseDao;
import com.benson.graduate.stu.model.Clazz;

public interface ClazzDao extends BaseDao<Clazz> {
	
	/**
	 * 根据hql计算班级记录总数
	 * @param hql
	 * @return
	 */
	public Long getClazzsCount(String hql);
	
	/**
	 * 批量删除班级信息
	 * @param ids
	 */
	public void deleteClazz(List<Integer> ids);
	
}
