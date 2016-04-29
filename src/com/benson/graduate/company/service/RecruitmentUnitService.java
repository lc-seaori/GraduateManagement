package com.benson.graduate.company.service;

import java.util.List;
import java.util.Map;

import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.pagemodel.Pager;
import com.benson.graduate.base.service.BaseService;
import com.benson.graduate.company.model.RecruitmentUnit;
import com.benson.graduate.company.pagemodel.PageRecruitmentUnit;

public interface RecruitmentUnitService extends BaseService {
	/**
	 * 获取招聘单位信息的datagird，整合easy-ui
	 * @param(第几页，每页多少行，排序，排序方式  还有其他的是模糊查询的参数)
	 */
	public DataGrid getDataGrid(String unitCode,String unitName,String unitTy,String recruitmentTy,String sort,String order,int pageNow,int pageRows);

	/**
	 * 根据单位编号查找实体
	 */
	public boolean findRecruitmentUnitByUnitCoide(String unitCode);
	
	/**
	 * 添加招聘单位信息
	 */
	public boolean addRecruitmentUnit(RecruitmentUnit unit);
	
	/**
	 * 根据id查找招聘单位信息
	 */
	public RecruitmentUnit findRecruitmentUnitById(Integer id);
	
	/**
	 * 跟新招聘单位信息
	 */
	public boolean updateRecruitmentUnit(RecruitmentUnit unit);
	
	/**
	 * 批量删除招聘单位信息
	 */
	public boolean deleteSelectedRecruitmentUnit(String idList);
	
	/**
	 * 查询所有招聘公司单位
	 */
	public List<RecruitmentUnit> findAllRecruitmentUnits();
	
	/**
	 * 前台招聘公司信息分页
	 * @param pageNumber
	 * @param pageSize
	 * @param fieldMap
	 * @return
	 */
	Pager findUnitPager(int pageNumber, int pageSize, Map<String, Object> fieldMap);
	
	/**
	 * 查询总数
	 * @param sql
	 * @param object
	 * @return
	 */
	long selectCount(String sql, Object...object);
	
	/**
	 * 查询页面模型
	 * @param id
	 * @return
	 */
	PageRecruitmentUnit findPageRecruUnitById(Integer id);
	
}
