package com.benson.graduate.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.benson.graduate.base.pagemodel.TreeNode;
import com.benson.graduate.base.service.impl.BaseServiceImpl;
import com.benson.graduate.sys.dao.OrganizationDao;
import com.benson.graduate.sys.model.Organization;
import com.benson.graduate.sys.pagemodel.PageOrganization;
import com.benson.graduate.sys.service.OrganizationService;

@Service("organizationService")
public class OrganizationServiceImpl extends BaseServiceImpl implements
		OrganizationService {
	
	private OrganizationDao organizationDao;
	@Resource(name="organizationDao")
	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}


	@Override
	public List<Organization> findAllOrganization() {
		// TODO Auto-generated method stub
		String hql="from Organization o ";
		return organizationDao.findEntityByHQL(hql);
	}


	@Override
	public Organization findOrganizationById(Integer id) {
		// TODO Auto-generated method stub
		return organizationDao.getEntity(id);
	}


	@Override
	public List<Organization> findChildrensByOrganizationId(Integer id) {
		// TODO Auto-generated method stub
		String hql="from Organization o where o.organization.id=?";
		return organizationDao.findEntityByHQL(hql, id);
	}


	@Override
	public List<PageOrganization> treeGrid() {
		// TODO Auto-generated method stub
		String hql="from Organization o";
		List<Organization> organizations=organizationDao.findEntityByHQL(hql);
		if(organizations!=null&&organizations.size()>0){
			return changeToPageModel(organizations);
		}else{
			return new ArrayList<PageOrganization>();
		}
	}
	
	/**
	 * Organization转成页面模型pageOrganization
	 */
	public List<PageOrganization> changeToPageModel(List<Organization> organizations){
		List<PageOrganization> pageOrganizations=new ArrayList<PageOrganization>();
		for(Organization organization :organizations){
			//是否有父部门
			PageOrganization pageOrganization=this.modelRecursion(organization);
			Organization temp=organizationDao.getEntity(pageOrganization.getId());
			if(temp.getOrganization()==null){
				pageOrganizations.add(pageOrganization);
			}
		}
		return pageOrganizations;
	}

	/**
	 * 递归
	 */
	public PageOrganization modelRecursion(Organization organization){
		PageOrganization pageOrganization=new PageOrganization();
		BeanUtils.copyProperties(organization, pageOrganization);
		//组织部门图标
		pageOrganization.setIconCls(organization.getIcon());
		
		//查询以该组织部门为父组织的所有子组织部门
		String hql="from Organization o where o.organization.id=?";
		List<Organization> organizations=organizationDao.findEntityByHQL(hql, organization.getId());
		if(organizations!=null&&organizations.size()>0){
			//有孩子的都是关闭的
			pageOrganization.setState("closed");
			List<PageOrganization> childs=new ArrayList<PageOrganization>();
			for(Organization o:organizations){
				PageOrganization p=modelRecursion(o);
				childs.add(p);
			}
			pageOrganization.setChildren(childs);
		}
		return pageOrganization;
	}
	
	@Override
	public List<TreeNode> combotree(){
		List<TreeNode> trees=new ArrayList<TreeNode>();
		String hql="from Organization o";
		List<Organization> organizations=organizationDao.findEntityByHQL(hql);
		if(organizations!=null&&organizations.size()>0){
			for(Organization o:organizations){
				TreeNode treeNode=this.comboRecursion(o);
				Organization temp=organizationDao.getEntity(Integer.parseInt(treeNode.getId()));
				if(temp.getOrganization()==null){
					trees.add(treeNode);
				}
			}
		}
		return trees;
	}
	
	public TreeNode comboRecursion(Organization organization){
		TreeNode treeNode=new TreeNode();
		treeNode.setId(organization.getId()+"");
		treeNode.setText(organization.getName());
		treeNode.setIconCls(organization.getIcon());
		//查找所有子权限
		String hql="from Organization o where o.organization.id=?";
		List<Organization> organizations=organizationDao.findEntityByHQL(hql, organization.getId());
		if(organizations!=null&&organizations.size()>0){
			//有孩子的都是关闭的
			//treeNode.setState("closed");
			List<TreeNode> childs=new ArrayList<TreeNode>();
			for(Organization o:organizations){
				TreeNode t=comboRecursion(o);
				childs.add(t);
			}
			treeNode.setChildren(childs);
			//pageAuth.setState("open");
		}
		return treeNode;
	}


	@Override
	public boolean addOrganization(Organization o) {
		// TODO Auto-generated method stub
		try {
			organizationDao.saveEntity(o);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public boolean updateOrganization(Organization o) {
		// TODO Auto-generated method stub
		try {
			organizationDao.updateEntity(o);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public List<Organization> findChildOrganizationsById(Integer id) {
		// TODO Auto-generated method stub
		String hql="from Organization o where o.organization.id=?";
		return organizationDao.findEntityByHQL(hql, id);
	}


	@Override
	public boolean deleteOrganization(Organization o) {
		// TODO Auto-generated method stub
		try {
			organizationDao.deleteEntity(o);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
}
