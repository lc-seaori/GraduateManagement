package com.benson.graduate.news.service;

import java.util.List;
import java.util.Map;
import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.service.BaseService;
import com.benson.graduate.news.model.NewsPlate;

/**
 * 新闻板块业务逻辑组件接口
 * @author benson
 */
public interface NewsPlateService extends BaseService {
	
	/**
	 * 获取新闻板块的datagird，整合easy-ui
	 * @param(第几页，每页多少行)
	 */
	public DataGrid getDataGrid(Map<String, Object> fieldMap, String sort, String order, int pageNow, int pageRows);
	
	/**
	 * 获取所有一级新闻板块
	 * @return
	 */
	List<NewsPlate> findAllRootNewsPlate();
	
	/**
	 * 获取所有二级版块
	 * @param platePid
	 * @return
	 */
	List<NewsPlate> findChildNewsPlate(int platePid);
	
	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	NewsPlate findById(int id);
	
	/**
	 * 添加实体  hql
	 * @param newsPlate
	 * @return
	 */
	boolean insertNewsPlate(NewsPlate newsPlate);
	
	/**
	 * 更新  sql
	 * @param newsPlateId
	 * @param fieldMap
	 * @return
	 */
	boolean updateNewsPlate(int newsPlateId, Map<String, Object> fieldMap);
	
	/**
	 * 删除 hql
	 * @param idList
	 * @return
	 */
	boolean deleteNewsPlates(String idList);
	
	boolean isContext(String idList);
}
