package com.benson.graduate.stu.dao;

import java.util.List;

import com.benson.graduate.base.dao.BaseDao;
import com.benson.graduate.stu.model.Student;

public interface StudentDao extends BaseDao<Student>{
	/**
	 * 获取学生信息总数
	 */
	public Long getStudentsCount(String hql);
	
	/**
	 * 根据id列表删除学生信息
	 */
	public void deleteStudent(Integer id);
	
	/**
	 * 根据id查找学生名字
	 */
	public String getStudentNameById(Integer id);
	
	/**
	 * 批量添加学生信息
	 * @param stus
	 * @return
	 */
	public boolean addStudensByList(List<Student> stus);
	
}
