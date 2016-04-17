package com.benson.graduate.stu.dao.impl;

import org.springframework.stereotype.Repository;

import com.benson.graduate.base.dao.impl.BaseDaoImpl;
import com.benson.graduate.stu.dao.GradeDao;
import com.benson.graduate.stu.model.Grade;

/**
 * 对数据库关于年级的数据的处理
 * @author benson
 */
@Repository("gradeDao")
public class GradeDaoImpl extends BaseDaoImpl<Grade> implements GradeDao {
	
}
