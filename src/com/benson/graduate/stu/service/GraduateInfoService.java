package com.benson.graduate.stu.service;

import java.util.List;

import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.service.BaseService;
import com.benson.graduate.stu.model.GraduateInfo;

public interface GraduateInfoService extends BaseService{
	
	/**
	 * 获取学生信息的datagird，整合easy-ui
	 * @param(第几页，每页多少行，排序，排序方式  还有其他的是模糊查询的参数)
	 */
	public DataGrid getDataGrid(String code,String name,String idCard,String departmentName,String graduateTime,String sort,String order,int pageNow,int pageRows);
	
	/**
	 * 获取学院审核的学生就业信息的datagird，整合easy-ui
	 */
	public DataGrid getDepartmentAduitDataGrid(String code,String name,String idCard,String departmentName,String graduateTime,String sort,String order,int pageNow,int pageRows);
	
	/**
	 * 获取学校审核的学生源信息的datagird，整合easy-ui
	 * @param(第几页，每页多少行)
	 */
	public DataGrid getSchoolAduitDataGrid(String code,String name,String idCard,String departmentName,String graduateTime,String sort,String order,int pageNow,int pageRows);
	
	/**
	 * 根据id查找学生就业信息
	 */
	public GraduateInfo findGraduateInfoById(Integer id);
	
	/**
	 * 更新学生就业信息
	 */
	public boolean updateGraduateInfo(GraduateInfo graduateInfo);
	
	/**
	 * 删除选中的学生就业信息
	 */
	public boolean deleteSelectedGraduateInfo(String idList);
	
	/**
	 * 添加学生就业信息
	 */
	public boolean addGraduateInfo(GraduateInfo graduateInfo);
	
	/**
	 * 选中的学生就业信息提交院系审核（审核中）
	 */
	public boolean updateGraDepartmentAduit(String idList);
	
	/**
	 * 选中的学生就业信息取消院系审核申请（未申请）
	 */
	public boolean updateCancelGraDepartmentAduit(String idList);
	
	/**
	 * 选中的学生就业信息进行学院审核（通过）
	 */
	public boolean updateDepartmentGAduit(String idList);
	
	/**
	 * 选中的学生就业信息进行学院审核（不通过）
	 */
	public boolean updateDepartmentInverseGAduit(String idList);
	
	/**
	 * 选中的学生就业信息提交学校审核
	 */
	public boolean updateDoGraSchoolGAduit(String idList);
	
	/**
	 * 选中的学生就业信息取消学校审核申请
	 */
	public boolean updateCancelGraSchoolGAduit(String idList);
	
	/**
	 * 选中的学生就业信息进行学校审核(通过)
	 */
	public boolean updateSchoolGAduit(String idList);
	
	/**
	 * 选中的学生就业信息进行学校审核（不通过）
	 */
	public boolean updateSchoolInverseGAduit(String idList);
	
	/**
	 * 通过学院、专业和班别查找符合条件的并且已经审核的学生就业信息
	 */
	public List<GraduateInfo> findGraduateInfosForExport(int departmentId,int majorId,int clazzId);
	
	/**
	 * 根据学生id查找是否有学生就业信息（而且是通过学校审核的）
	 */
	public boolean findGraduateInfoByStuId(Integer stuId);
}
