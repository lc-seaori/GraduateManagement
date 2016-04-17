package com.benson.graduate.stu.service;

import java.util.List;

import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.service.BaseService;
import com.benson.graduate.stu.model.Clazz;

public interface ClazzService extends BaseService{
	/**
	 * 根据专业id查找所有班级
	 */
	public List<Clazz> findAllClazzByMajorId(Integer majorId);
	
	/**
	 * 根据班的id找班级
	 */
	public Clazz findClazzById(Integer clazzId);
	
	/**
	 * 得到所有班级（属于毕业年级的班级）
	 */
	public List<Clazz> findAllGraClazzes(Integer isboolean);
	
	/**
	 * 获取班级列表Grid
	 * @param name
	 * @param majorId
	 * @param sort
	 * @param order
	 * @param pageNow
	 * @param pageRows
	 * @return
	 */
	public DataGrid getDataGrid(String name, String majorId, String sort,
			String order, int pageNow, int pageRows) ;
	
	/**
	 * 添加班级
	 * @param clazz
	 * @return
	 */
	public boolean addClazz(Clazz clazz);
	
	/**
	 * 更新班级信息
	 * @param clazz
	 * @return
	 */
	public boolean updateClazz(Clazz clazz);
	
	public boolean deleteSelectedClazzs(String idList);
	
}
