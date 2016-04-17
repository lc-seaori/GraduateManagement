package com.benson.graduate.sys.service;

import java.util.List;

import com.benson.graduate.base.pagemodel.TreeNode;
import com.benson.graduate.base.service.BaseService;
import com.benson.graduate.sys.model.Auth;
import com.benson.graduate.sys.pagemodel.PageAuth;

/**
 * 关于权限的逻辑操作
 * @author benson
 *
 */
public interface AuthService extends BaseService {
	
	/**
	 * 获取权限treegrid
	 */
	public List<PageAuth> treeGrid();
	
	/**
	 * 下拉树comboTree
	 */
	public List<TreeNode> combotree();
	
	/**
	 * 根据id获取权限
	 */
	public Auth findAuthById(Integer id);
	
	/**
	 * 添加权限实体
	 */
	public boolean addAuth(Auth auth);
	
	/**
	 * 更新权限实体
	 */
	public boolean updateAuth(Auth auth);
	
	/**
	 * 根据权限id查找所有子权限
	 */
	public List<Auth> findChildAuthsById(Integer id);
	
	/**
	 * 删除权限信息
	 */
	public boolean deleteAuth(Auth auth);
	
	/**
	 * 根据角色id查该角色拥有的所有权限
	 */
	public List<Auth> findAllAuthsByRoleId(Integer id);
	
	/**
	 * 根据权限url查找权限(权限url可重复)
	 */
	public Auth findAuthsByUrl(String url);
} 
