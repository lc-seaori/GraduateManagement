package com.benson.graduate.stu.dao;

import java.util.List;

import com.benson.graduate.base.dao.BaseDao;
import com.benson.graduate.stu.model.GraduateInfo;

public interface GraduateInfoDao extends BaseDao<GraduateInfo> {
	
	/**
	 * 获取学生信息总数
	 */
	public Long getGraduateInfosCount(String hql);
	
	/**
	 * 根据id列表删除学生就业信息
	 */
	public void deleteGraduateInfo(List<Integer> ids);
	
	/**
	 * 批量添加学生就业信息（导入excel）
	 * @param graduateInfo
	 * @return
	 */
	public boolean addGraduateInfosByList(List<GraduateInfo> graduateInfo);

}
