package com.benson.graduate.stu.dao;

import java.util.List;

import com.benson.graduate.base.dao.BaseDao;
import com.benson.graduate.stu.model.MajorField;

public interface MajorFieldDao extends BaseDao<MajorField> {
	
	/**
	 * 根据Hql计算总记录数
	 * @param hql
	 * @return
	 */
	public Long getMajorsCount(String hql);
	
	/**
	 * 批量删除专业信息
	 * @param ids
	 */
	public void deleteMajor(List<Integer> ids);
}
