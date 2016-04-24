package com.benson.graduate.base.dao;

import java.util.List;

public interface BaseDao<T>{
	
	//写操作
	public void saveEntity(T t);
	public void saveOrUpdateEntity(T t);
	public void updateEntity(T t);
	public void deleteEntity(T t);
	//批量处理
	public void batchEntitiesByHQL(String hql,Object...objects);
	
	//读操作
	public T loadEntity(Integer id);
	public T getEntity(Integer id);
	public List<T> findEntityByHQL(String hql,Object...objects);
	public List<T> findEntityByHQL(String hql, int page, int rows);
	public List<T> findEntityByHQL(String hql, int page, int rows,Object...objects);
	public List<Object[]> findEntityBySql(String sql,Object...objects);
	public List<Object[]> findEntityBySql(String sql, int page, int rows);
	public List<Object[]> findEntityBySql(String sql, int page, int rows,Object...objects);
	
	
	public int executeSql(String sql,Object...objects);
	
}
