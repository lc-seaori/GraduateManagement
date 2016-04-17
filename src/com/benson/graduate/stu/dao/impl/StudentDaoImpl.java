package com.benson.graduate.stu.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.benson.graduate.base.dao.impl.BaseDaoImpl;
import com.benson.graduate.stu.dao.StudentDao;
import com.benson.graduate.stu.model.Student;

@Repository("studentDao")
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao {

	@Override
	public Long getStudentsCount(String hql) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	@Override
	public void deleteStudent(Integer id) {
		// TODO Auto-generated method stub
		//更新学生源信息的校审状态（改为未审核）
		String hql="delete from Student s where s.id=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public String getStudentNameById(Integer id) {
		// TODO Auto-generated method stub
		String hql="select s.name from Student s where s.id=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0, id);
		return (String) query.list().get(0);
	}

	@Override
	public boolean addStudensByList(List<Student> stus) {
		// TODO Auto-generated method stub
		try {
			for(Student s:stus){
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
