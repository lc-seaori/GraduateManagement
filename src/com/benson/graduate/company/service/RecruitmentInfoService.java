package com.benson.graduate.company.service;

import java.util.List;
import java.util.Map;

import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.pagemodel.Pager;
import com.benson.graduate.base.service.BaseService;
import com.benson.graduate.company.model.RecruitmentInfo;
import com.benson.graduate.company.pagemodel.PageRecruitmentInfo;

public interface RecruitmentInfoService extends BaseService {
	
	/**
	 * 根据招聘单位id查找所有的招聘信息
	 */
	public List<RecruitmentInfo> getAllRecruitmentInfosByUnitId(Integer id);
	
	/**
	 * 转页面模型
	 */
	public List<PageRecruitmentInfo> changeToPageModel(List<RecruitmentInfo> infos);
	
	/**
	 * 获取招聘信息列表的datagird，整合easy-ui
	 * @param(第几页，每页多少行，排序，排序方式  还有其他的是模糊查询的参数)
	 */
	public DataGrid getDataGrid(String position,String unitName,String workTy,String industryTy,String educationTy,String sort,String order,int pageNow,int pageRows);

	
	/**
	 * 添加招聘信息
	 */
	public boolean addRecruitmentInfo(RecruitmentInfo info);
	
	/**
	 * 根据id查找招聘信息
	 */
	public RecruitmentInfo findRecruitmentInfoById(Integer id);
	
	/**
	 * 更新招聘信息
	 */
	public boolean updateRecruitmentInfo(RecruitmentInfo info);
	
	/**
	 * 根据id列表删除招聘信息
	 */
	public boolean deleteSelectedRecruitmentInfo(String idList);
	
	/**
	 * 根据招聘单位id和发布时间查找所有的招聘信息
	 */
	public List<RecruitmentInfo> getAllRecruitmentInfos(Integer recUnitId,Integer timeId);
	
	/**
	 * 前台招聘信息分页
	 * @param pageNumber
	 * @param pageSize
	 * @param fieldMap
	 * @return
	 */
	Pager findInfoPager(int pageNumber, int pageSize, Map<String, Object> fieldMap);
	
	/**
	 * 查询总数
	 * @param sql
	 * @param object
	 * @return
	 */
	long selectCount(String sql, Object...object);
	
	/**
	 * 查招聘信息页面实体
	 * @param id
	 * @return
	 */
	PageRecruitmentInfo findPageRecruInfoById(Integer id);
	
}
