package com.benson.graduate.sys.dao;

import java.util.List;

import com.benson.graduate.base.dao.BaseDao;
import com.benson.graduate.sys.model.OperationRecord;

public interface OperationRecordDao extends BaseDao<OperationRecord> {
	
	/**
	 * 根据hql来计算操作记录条数
	 * @param hql
	 * @return
	 */
	public Long getOperationRecordsCount(String hql);

	/**
	 * 删除操作记录
	 * @param ids
	 */
	public void deleteRecord(List<Integer> ids);
}
