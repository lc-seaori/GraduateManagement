package com.benson.graduate.stu.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.benson.graduate.base.dao.impl.BaseDaoImpl;
import com.benson.graduate.stu.dao.StudentInfoDao;
import com.benson.graduate.stu.model.StudentInfo;

@Repository("studentInfoDao")
public class StudentInfoDaoImpl extends BaseDaoImpl<StudentInfo> implements StudentInfoDao{

	@Override
	public Long getStudentInfosCount(String hql) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	@Override
	public void deleteStudentInfo(List<Integer> ids) {
		// TODO Auto-generated method stub
		for(int i=0;i<ids.size();i++){
			String hql="delete from StudentInfo s where s.id=?";
			Query query=getSession().createQuery(hql);
			query.setParameter(0, ids.get(i));
			query.executeUpdate();
		}
	}
	
	@Override
	public boolean addStudenInfosByList(List<StudentInfo> stuInfos){
		try {
			for(StudentInfo s:stuInfos){
				getSession().save(s);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
}
