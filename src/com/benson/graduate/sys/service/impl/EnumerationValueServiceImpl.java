package com.benson.graduate.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.benson.graduate.base.service.impl.BaseServiceImpl;
import com.benson.graduate.sys.dao.EnumerationValueDao;
import com.benson.graduate.sys.model.EnumerationValue;
import com.benson.graduate.sys.service.EnumerationValueService;

@Service("enumerationValueService")
public class EnumerationValueServiceImpl extends BaseServiceImpl implements
		EnumerationValueService {
	
	private EnumerationValueDao enumerationValueDao;
	@Resource(name="enumerationValueDao")
	public void setEnumerationValueDao(EnumerationValueDao enumerationValueDao) {
		this.enumerationValueDao = enumerationValueDao;
	}
	
	
	public List<EnumerationValue> findAllEnumerationValuesByName(
			int enumerationType) {	
		return enumerationValueDao.getEnumerationValueByEnumerationName(enumerationType);
	}


	@Override
	public EnumerationValue findEnumerationValueById(int id) {
		// TODO Auto-generated method stub
		return enumerationValueDao.getEntity(id);
	}
	

}
