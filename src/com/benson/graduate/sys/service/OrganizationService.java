package com.benson.graduate.sys.service;

import java.util.List;

import com.benson.graduate.base.pagemodel.TreeNode;
import com.benson.graduate.base.service.BaseService;
import com.benson.graduate.sys.model.Organization;
import com.benson.graduate.sys.pagemodel.PageOrganization;

public interface OrganizationService extends BaseService {
	/**
	 * 获取所有组织部门
	 */
	public List<Organization> findAllOrganization();
	
	/**
	 * 根据部门id查找组织部门（确定有的）
	 */
	public Organization findOrganizationById(Integer id);
	
	/**
	 * 查询指定id的所有子部门
	 */
	public List<Organization> findChildrensByOrganizationId(Integer id);
	
	/**
	 * 获取组织部门树的Grid
	 */
	public List<PageOrganization> treeGrid();
	
	/**
	 * 获取下拉树数据或者是树数据(combotree/tree)
	 * @return
	 */
	public List<TreeNode> combotree();
	
	/**
	 * 添加组织部门实体
	 */
	public boolean addOrganization(Organization o);
	
	/**
	 * 更新组织部门实体
	 */
	public boolean updateOrganization(Organization o);
	
	/**
	 * 查找该组织部门所有的子部门
	 * @param id
	 * @return
	 */
	public List<Organization> findChildOrganizationsById(Integer id);
	
	/**
	 * 删除组织部门
	 */
	public boolean deleteOrganization(Organization o);
}
