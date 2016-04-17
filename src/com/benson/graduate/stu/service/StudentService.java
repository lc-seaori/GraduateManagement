package com.benson.graduate.stu.service;


import java.util.List;

import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.service.BaseService;
import com.benson.graduate.stu.model.Student;

public interface StudentService extends BaseService {
	
	/**
	 * 根据学号查找学生信息
	 */
	public boolean findStudentByCode(String code);
	
	/**
	 * 保存学生实体
	 */
	public boolean saveStudent(Student student);
	
	/**
	 * 获取学生信息的datagird，整合easy-ui
	 * @param(第几页，每页多少行，排序，排序方式  还有其他的是模糊查询的参数)
	 */
	public DataGrid getDataGrid(String code,String name,String departmentName,String clazzName,String graduateTime,String sort,String order,int pageNow,int pageRows,boolean isNot);
	
	/**
	 * 获取所有学生信息的datagird，提供添加就业信息时使用
	 * @return
	 */
	public DataGrid getDataGrid();
	
	/**
	 * 根据id查找学会信息
	 */
	public Student findStudentById(Integer id);
	
	/**
	 * 更新学生信息
	 */
	public boolean updateStudent(Student stu); 
	
	/**
	 * 根据id列表删除学生记录
	 */
	public boolean deleteSelectedStudent(String idList);
	
	/**
	 * 根据id查找学生名字
	 */
	public String findStudentNameById(Integer id);
	
	/**
	 * 通过学院、专业和班别查找符合条件的并且已经审核的学生源信息
	 */
	public List<Student> findStudentsForExport(int departmentId,int majorId,int clazzId);
	
	/**
	 * 根据班名和毕业年份查找该毕业班所有指定在该年份毕业的所有学生
	 */
	public List<Student> findStudentsByClazzNameAndTime(String clazzName,String graTime);
	
	/**
	 * 根据学院id和毕业时间查找所有的学生
	 */
	public List<Student> findStudentsByDeparmentId(Integer departmentId,String graTime);
	
	/**
	 * 根据专业id和毕业时间查找所有学生
	 */
	public List<Student> findStudentsByMajorId(Integer majorId,String graTime);
	
	/**
	 * 根据班别id和毕业时间查找所有学生
	 */
	public List<Student> findStudentsByClazzId(Integer clazzId,String graTime);
	
}
