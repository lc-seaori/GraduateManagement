package com.benson.graduate.sys.service;

import java.util.List;

import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.service.BaseService;
import com.benson.graduate.sys.model.User;

public interface UserService extends BaseService {
	/**
	 * 根据用户帐号查找用户
	 */
	public List<User> findUserByLoginName(String loginname);
	
	/**
	 * 根据用户id查找用户
	 */
	public User findUserByUserId(Integer userId);
	
	/**
	 * 根据用户实体来更新
	 */
	public boolean updateUser(User user);
	
	/**
	 * 获取用户信息的datagird，整合easy-ui
	 * @param(第几页，每页多少行，排序，排序方式  还有其他的是模糊查询的参数)
	 */
	public DataGrid getDataGrid(String loginname,String name,Integer organization,String sort,String order,int pageNow,int pageRows);
	
	/**
	 * 插入用户
	 */
	public boolean addUser(User user);
	
	/**
	 * 批量删除选中的用户
	 */
	public boolean deleteSelectedUser(String idList);
	
}
