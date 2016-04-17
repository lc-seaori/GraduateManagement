package com.benson.graduate.sys.service;

import java.util.List;

import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.service.BaseService;
import com.benson.graduate.sys.model.Role;

public interface RoleService extends BaseService {
	
	/**
	 * 查找所有的角色
	 * @return
	 */
	public List<Role> findAllRoles();
	
	/**
	 * 根据id查找角色
	 */
	public Role findRoleById(Integer id);
	
	/**
	 * 根据用户id查找该用户的所有角色
	 */
	public List<Role> findAllRolesByUserId(Integer id);
	
	/**
	 * 获取角色信息的datagird，整合easy-ui
	 * @param(第几页，每页多少行，排序，排序方式  还有其他的是模糊查询的参数)
	 */
	public DataGrid getDataGrid(String name,String sort,String order,int pageNow,int pageRows);
	
	/**
	 * 添加角色实体
	 */
	public boolean addRole(Role role);
	
	/**
	 * 更新角色信息
	 */
	public boolean updateRole(Role role);
	
	/**
	 * 批量删除角色信息
	 */
	public boolean deleteSelectedRoles(String idList);
 	
}
