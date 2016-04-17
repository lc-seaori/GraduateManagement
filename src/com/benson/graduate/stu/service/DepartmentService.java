package com.benson.graduate.stu.service;

import java.util.List;

import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.service.BaseService;
import com.benson.graduate.stu.model.Department;

/**
 * 学院业务逻辑组件接口
 * @author benson
 *
 */
public interface DepartmentService extends BaseService{
	
	/**
	 * 查找所有学院 
	 * @return
	 */
	public List<Department> findAllDepartments();
	
	/**
	 * 根据学院id查找学院
	 */
	public Department findDepartmentById(Integer id);
	
	/**
	 * 获取学院列表Grid
	 */
	public DataGrid getDataGrid(String name,String sort, String order, int pageNow, int pageRows) ;
	
	/**
	 * 保存实体
	 */
	public boolean addDepartment(Department d);
	
	/**
	 * 更新学院实体
	 */
	public boolean updateDepartment(Department d);
	
	/**
	 * 批量删除学院
	 */
	public boolean deleteSelectedDeps(String idList);
	
}
