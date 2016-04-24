package com.benson.graduate.news.service;

import java.util.List;
import java.util.Map;

import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.pagemodel.Pager;
import com.benson.graduate.base.service.BaseService;
import com.benson.graduate.news.model.NewsNn;

/**
 * 新闻业务逻辑组件接口
 * @author benson
 */
public interface NewsNnService extends BaseService {
	
	/**
	 * 获取新闻的datagird，整合easy-ui
	 * @param(第几页，每页多少行)
	 */
	public DataGrid getDataGrid(Map<String, Object> fieldMap, String sort, String order, int pageNow, int pageRows);
	
	/**
	 * 主键查询
	 * @param id
	 * @return
	 */
	NewsNn findById(int id);
	
	/**
	 * 根据新闻类型id获取所有该类新闻
	 * @param enVal
	 * @return
	 */
	List<NewsNn> findByNewsPlateId(int enVal);
	
	/**
	 * 添加实体  hql
	 * @param newsNn
	 * @return
	 */
	boolean insertNewsNn(NewsNn newsNn);
	
	/**
	 * 删除 hql
	 * @param idList
	 * @return
	 */
	boolean deleteNewsNns(String idList);
	
	/**
	 * 更新实体
	 * @param newsNn
	 * @return
	 */
	boolean updateNewsNn(NewsNn newsNn);
	
	/**
	 * 文件删除
	 * @param path
	 * @return
	 */
	boolean deleteFile(String path);
	
	/**
	 * 前台新闻分页
	 * @param pageNumber
	 * @param pageSize
	 * @param fieldMap
	 * @return
	 */
	Pager findNewsNnPager(int pageNumber, int pageSize, Map<String, Object> fieldMap);
	
	/**
	 * 查询总数
	 * @param sql
	 * @param object
	 * @return
	 */
	long selectCount(String sql, Object...object);
}
