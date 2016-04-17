package com.benson.graduate.stu.service;

import java.util.List;

import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.service.BaseService;
import com.benson.graduate.stu.model.StudentInfo;

/**
 * 学生源信息业务逻辑组件接口
 * @author benson
 *
 */
public interface StudentInfoService extends BaseService {
	
	/**
	 *  分页查询查找所有的学生生源信息
	 */
	public List<StudentInfo> getAllStudentInfosByPage(String hql,int pageNow,int pageRows);

	/**
	 *  查找所有的学生生源信息
	 */
	public List<StudentInfo> getAllStudentInfos();
	
	/**
	 * 获取学生源信息的datagird，整合easy-ui
	 * @param(第几页，每页多少行)
	 */
	public DataGrid getDataGrid(String examNum,String name,String idCard,String graduateTime,String sort,String order,int pageNow,int pageRows);
	
	/**
	 * 获取学院审核的学生源信息的datagird，整合easy-ui
	 * @param(第几页，每页多少行)
	 */
	public DataGrid getDepartmentAduitDataGrid(String examNum,String name,String idCard,String departmentName,String graduateTime,String sort,String order,int pageNow,int pageRows);
	
	/**
	 * 获取学校审核的学生源信息的datagird，整合easy-ui
	 * @param(第几页，每页多少行)
	 */
	public DataGrid getSchoolAduitDataGrid(String examNum,String name,String idCard,String departmentName,String graduateTime,String sort,String order,int pageNow,int pageRows);

	
	/**
	 * 保存学生源信息，并返回操作码
	 */
	public boolean addStudentInfo(StudentInfo studentInfo);
	
	/**
	 * 根据id查找学生源信息
	 */
	public StudentInfo findStudentInfoById(int id);
	
	/**
	 * 更新学生信息
	 */
	public boolean updateStudentInfo(StudentInfo studentInfo);
	
	/**
	 * 删除选中的学生信息
	 */
	public boolean deleteSelectedStudentInfo(String idList);
	
	/**
	 * 根据准考证号判断用户是否存在
	 */
	public boolean findStudentInfoByExamNum(String examNum);
	
	/**
	 * 根据身份证号判断用户是否存在
	 */
	public boolean findStudentInfoByIdCard(String idCard);
	
	/**
	 * 选中的学生信息提交院系审核
	 */
	public boolean updateDoStuDepartmentAduit(String idList);
	
	/**
	 * 选中的学生信息取消院系审核申请
	 */
	public boolean updateCancelStuDepartmentAduit(String idList);
	
	/**
	 * 选中的学生信息进行学院审核（通过）
	 */
	public boolean updateDepartmentAduit(String idList);
	
	/**
	 * 选中的学生信息进行学院审核（不通过）
	 */
	public boolean updateDepartmentInverseAduit(String idList);
	
	
	/**
	 * 选中的学生信息提交学校审核
	 */
	public boolean updateDoStuSchoolAduit(String idList);
	
	/**
	 * 选中的学生信息取消学校审核申请
	 */
	public boolean updateCancelStuSchoolAduit(String idList);

	/**
	 * 选中的学生信息进行学校审核(单个学生信息，学生状态改为通过，然后添加到学生表中)
	 */
	public void updateSchoolAduit(Integer id);
	
	/**
	 * 选中的学生信息进行学校审核（不通过）
	 */
	public boolean updateSchoolInverseAduit(String idList);
	
}
