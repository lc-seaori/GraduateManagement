package com.benson.graduate.sys.dao;

import java.util.List;

import com.benson.graduate.base.dao.BaseDao;
import com.benson.graduate.sys.model.EnumerationValue;

public interface EnumerationValueDao extends BaseDao<EnumerationValue> {
	
	/**
	 * 根据枚举名称查找所有枚举值实体
	 */
	public List<EnumerationValue> getEnumerationValueByEnumerationName(int enumerationType);
}
