package com.benson.graduate.stu.service;

import java.util.List;

import com.benson.graduate.base.service.BaseService;
import com.benson.graduate.stu.model.Grade;

public interface GradeService extends BaseService {
	
	/**
	 * 查找所有年级
	 * @return
	 */
	public List<Grade> findAllGrades();
	
	/**
	 * 根据年级id查找年级
	 * @param id
	 * @return
	 */
	public Grade findGradeById(Integer id);
}
