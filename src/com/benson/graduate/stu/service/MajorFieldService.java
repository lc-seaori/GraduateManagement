package com.benson.graduate.stu.service;

import java.util.List;

import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.service.BaseService;
import com.benson.graduate.stu.model.MajorField;

/**
 * 专业方向业务逻辑组件接口
 * @author benson
 */
public interface MajorFieldService extends BaseService {
	
	/**
	 * 根据学院id查找该学院的所有专业
	 */
	public List<MajorField> findAllMajorsByDepartmentId(int id);
	
	/**
	 * 根据专业id查找专业
	 */
	public MajorField findMajorById(int id);
	
	/**
	 * 查找所有专业
	 */
	public List<MajorField> findAllMajorField();
	
	/**
	 * 获取专业列表Grid
	 */
	public DataGrid getDataGrid(String name, String departmentId ,String sort, String order,
			int pageNow, int pageRows);
	
	/**
	 * 添加专业
	 */
	public boolean addMajorField(MajorField majorField);
	
	/**
	 * 更新专业
	 */
	public boolean updateMajor(MajorField majorField);

	/**
	 * 根据id列表批量删除专业信息
	 */
	public boolean deleteSelectedMajors(String idList);

}
