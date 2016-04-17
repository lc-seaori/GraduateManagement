package com.benson.graduate.sys.service;

import java.util.List;

import com.benson.graduate.base.service.BaseService;
import com.benson.graduate.sys.model.EnumerationValue;

public interface EnumerationValueService extends BaseService {
	/**
	 * 根据枚举名称查找所有枚举值实体
	 */
	public List<EnumerationValue> findAllEnumerationValuesByName(int enumerationType);
	
	/**
	 * 根据枚举值的id查找枚举值
	 */
	public  EnumerationValue findEnumerationValueById(int id);
}
