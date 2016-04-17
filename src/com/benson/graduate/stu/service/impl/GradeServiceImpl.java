package com.benson.graduate.stu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.benson.graduate.base.service.impl.BaseServiceImpl;
import com.benson.graduate.stu.dao.GradeDao;
import com.benson.graduate.stu.model.Grade;
import com.benson.graduate.stu.service.GradeService;

/**
 * 对年级的逻辑操作
 * @author benson
 */
@Service("gradeService")
public class GradeServiceImpl extends BaseServiceImpl implements GradeService {

	private GradeDao gradeDao;
	@Resource(name="gradeDao")
	public void setGradeDao(GradeDao gradeDao) {
		this.gradeDao = gradeDao;
	}


	@Override
	public List<Grade> findAllGrades() {
		// TODO Auto-generated method stub
		String hql="from Grade";
		return gradeDao.findEntityByHQL(hql);
	}


	@Override
	public Grade findGradeById(Integer id) {
		// TODO Auto-generated method stub
		String hql="from Grade g where g.id=?";
		return gradeDao.findEntityByHQL(hql, id).get(0);
	}

}
