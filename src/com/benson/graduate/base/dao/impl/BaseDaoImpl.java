package com.benson.graduate.base.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.benson.graduate.base.dao.BaseDao;

@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T> {

	//注入SessionFactory，可以使用@Resource注解，或者提供setter方法
	private SessionFactory sessionFactory;
	@Resource(name="sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
//		System.out.println("setSessionFactory");
		this.sessionFactory = sessionFactory;
	}
	//获取线程安全的Session
	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	private Class<T> clazz;
	//构造方法：
	public BaseDaoImpl(){
		//这里的this是BaseDaoImpl的子类，因为BaseDaoImpl是一个抽象类
		//得到泛型化超类
		ParameterizedType type= (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz=(Class<T>) type.getActualTypeArguments()[0];
	}
	
	@Override
	public void saveEntity(T t) {
		// TODO Auto-generated method stub
		this.getSession().save(t);	
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		// TODO Auto-generated method stub
		this.getSession().saveOrUpdate(t);
	}

	@Override
	public void updateEntity(T t) {
		// TODO Auto-generated method stub
		this.getSession().update(t);
	}

	@Override
	public void deleteEntity(T t) {
		// TODO Auto-generated method stub
		this.getSession().delete(t);
	}

	/**
	 * 根据hql语句批量更新实体
	 */
	@Override
	public void batchEntitiesByHQL(String hql, Object... objects) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery(hql);
		for(int i=0;i<objects.length;i++){
			query.setParameter(i, objects[i]);
		}
		query.executeUpdate();
	}

	@Override
	public T loadEntity(Integer id) {
		// TODO Auto-generated method stub
		return (T) getSession().load(clazz, id);
	}

	@Override
	public T getEntity(Integer id) {
		// TODO Auto-generated method stub
		return (T)getSession().get(clazz, id);
	}

	@Override
	public List<T> findEntityByHQL(String hql, Object... objects) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery(hql);
		for(int i=0;i<objects.length;i++){
			query.setParameter(i, objects[i]);
		}
		return query.list();
	}
	@Override
	public List<T> findEntityByHQL(String hql, int page, int rows) {
		// TODO Auto-generated method stub
		Query query=this.getSession().createQuery(hql);
		return query.setFirstResult((page-1)*rows).setMaxResults(rows).list();
	}
	
	@Override
	public List<T> findEntityByHQL(String hql, int page, int rows,
			Object... objects) {
		// TODO Auto-generated method stub
		Query query=getSession().createQuery(hql);
		for(int i=0;i<objects.length;i++){
			query.setParameter(i, objects[i]);
		}
		return query.setFirstResult((page-1)*rows).setMaxResults(rows).list();
	}
	
	@Override
	public List<Object[]> findEntityBySql(String sql, Object... objects) {
		// TODO Auto-generated method stub
		SQLQuery query = this.getSession().createSQLQuery(sql);
		if(objects!=null){
			for(int i=0;i<objects.length;i++){
				query.setParameter(i, objects[i]);
			}
		}
		return query.list();
	}
	
	@Override
	public List<Object[]> findEntityBySql(String sql, int page, int rows,
			Object... objects) {
		// TODO Auto-generated method stub
		SQLQuery query = this.getSession().createSQLQuery(sql);
		if(objects!=null){
			for(int i=0;i<objects.length;i++){
				query.setParameter(i, objects[i]);
			}
		}
		return query.setFirstResult((page-1)*rows).setMaxResults(rows).list();
	}
	
	@Override
	public List<Object[]> findEntityBySql(String sql, int page, int rows) {
		// TODO Auto-generated method stub
		SQLQuery query = this.getSession().createSQLQuery(sql);
		return query.setFirstResult((page-1)*rows).setMaxResults(rows).list();
	}
	
	@Override
	public int executeSql(String sql, Object... objects) {
		// TODO Auto-generated method stub
		SQLQuery q = this.getSession().createSQLQuery(sql);
		return q.executeUpdate();
	}

}
