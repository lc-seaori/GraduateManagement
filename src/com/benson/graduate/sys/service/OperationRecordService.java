package com.benson.graduate.sys.service;

import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.service.BaseService;
import com.benson.graduate.sys.model.OperationRecord;

public interface OperationRecordService extends BaseService {
	
	/**
	 * 在权限认证成功时，添加用户操作记录
	 * @param record
	 * @return
	 */
	public boolean addOperationRecord(OperationRecord record);
	
	/**
	 * 获取用户操作记录的datagird
	 * @param operationPerson
	 * @param operationName
	 * @param time
	 * @param sort
	 * @param order
	 * @param pageNow
	 * @param pageRows
	 * @return
	 */
	public DataGrid getDataGrid(String operationPerson, String operationName, String time,String sort,
			String order, int pageNow, int pageRows) ;
	
	/**
	 * 批量删除记录
	 * @param idList
	 * @return
	 */
	public boolean deleteSelectedRecord(String idList);
}
