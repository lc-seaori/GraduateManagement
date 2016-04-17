package com.benson.graduate.stu.dao;

import java.util.List;

import com.benson.graduate.base.dao.BaseDao;
import com.benson.graduate.stu.model.StudentInfo;

public interface StudentInfoDao extends BaseDao<StudentInfo> {
	/**
	 * 新生总人数
	 */
	public Long getStudentInfosCount(String hql);
	
	/**
	 * 根据id列表删除学生源信息
	 */
	public void deleteStudentInfo(List<Integer> ids);
	
	/**
	 * 批量添加学生
	 */
	public boolean addStudenInfosByList(List<StudentInfo> stuInfos);

}
