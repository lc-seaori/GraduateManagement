package com.benson.graduate.sys.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.benson.graduate.base.pagemodel.TreeNode;
import com.benson.graduate.base.service.impl.BaseServiceImpl;
import com.benson.graduate.sys.dao.AuthDao;
import com.benson.graduate.sys.model.Auth;
import com.benson.graduate.sys.pagemodel.PageAuth;
import com.benson.graduate.sys.service.AuthService;

@Service("authService")
public class AuthServiceImpl extends BaseServiceImpl implements AuthService {

	private AuthDao authDao;
	@Resource(name="authDao")
	public void setAuthDao(AuthDao authDao) {
		this.authDao = authDao;
	}

	@Override
	public List<PageAuth> treeGrid() {
		// TODO Auto-generated method stub
		String hql="from Auth a";
		List<Auth> auths=authDao.findEntityByHQL(hql);
		if(auths!=null&&auths.size()>0){
			return changeToPageModel(auths);
		}else{
			return new ArrayList<PageAuth>();
		}
	}
	
	/**
	 * auth转成页面模型pageAuth
	 */
	public List<PageAuth> changeToPageModel(List<Auth> auths){
		List<PageAuth> pageAuths=new ArrayList<PageAuth>();
		//List<PageAuth> tempAuths=new ArrayList<PageAuth>();
		if(auths!=null&&auths.size()>0){
			for(Auth auth :auths){
				//是否有父权限
				PageAuth pageAuth=this.modelRecursion(auth);
				Auth temp=authDao.getEntity(pageAuth.getId());
				if(temp.getAuth()==null){
					pageAuths.add(pageAuth);
				}
			}
		}
		return pageAuths;
	}

	/**
	 * 递归
	 */
	public PageAuth modelRecursion(Auth auth){
		
		PageAuth pageAuth=new PageAuth();
		BeanUtils.copyProperties(auth, pageAuth);
		//权限图标
		pageAuth.setIconCls(auth.getIcon());
		//转换创建时间
		Date date=auth.getCreateTime();
		if(date!=null){
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			String createTime="";
			try {
				createTime=dateFormat.format(date);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pageAuth.setCreateTimePage(createTime);
		}
		
		//查询以该权限为父权限的所有子权限
		String hql="from Auth a where a.auth.id=?";
		List<Auth> auths=authDao.findEntityByHQL(hql, auth.getId());
		if(auths!=null&&auths.size()>0){
			//有孩子的都是关闭的
			pageAuth.setState("closed");
			List<PageAuth> childs=new ArrayList<PageAuth>();
			for(Auth a:auths){
				PageAuth p=modelRecursion(a);
				childs.add(p);
			}
			pageAuth.setChildren(childs);
			//pageAuth.setState("open");
		}
		return pageAuth;
	}
	
	@Override
	public List<TreeNode> combotree(){
		List<TreeNode> trees=new ArrayList<TreeNode>();
		String hql="from Auth a";
		List<Auth> auths=authDao.findEntityByHQL(hql);
		if(auths!=null&&auths.size()>0){
			for(Auth a:auths){
				TreeNode treeNode=this.comboRecursion(a);
				Auth temp=authDao.getEntity(Integer.parseInt(treeNode.getId()));
				if(temp.getAuth()==null){
					trees.add(treeNode);
				}
			}
		}
		return trees;
	}
	
	public TreeNode comboRecursion(Auth auth){
		TreeNode treeNode=new TreeNode();
		treeNode.setId(auth.getId()+"");
		treeNode.setText(auth.getName());
		treeNode.setIconCls(auth.getIcon());
		//查找所有子权限
		String hql="from Auth a where a.auth.id=?";
		List<Auth> auths=authDao.findEntityByHQL(hql, auth.getId());
		if(auths!=null&&auths.size()>0){
			//有孩子的都是关闭的
			//treeNode.setState("closed");
			List<TreeNode> childs=new ArrayList<TreeNode>();
			for(Auth a:auths){
				TreeNode t=comboRecursion(a);
				childs.add(t);
			}
			treeNode.setChildren(childs);
			//pageAuth.setState("open");
		}
		return treeNode;
	}
	
	@Override
	public Auth findAuthById(Integer id) {
		// TODO Auto-generated method stub
		return authDao.getEntity(id);
	}


	@Override
	public boolean addAuth(Auth auth) {
		// TODO Auto-generated method stub
		try {
			authDao.saveEntity(auth);
		} catch (Exception e) {
			// TODO: handle exception
			//添加失败
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public boolean updateAuth(Auth auth) {
		// TODO Auto-generated method stub
		try {
			authDao.updateEntity(auth);
		} catch (Exception e) {
			// TODO: handle exception
			//更新失败
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<Auth> findChildAuthsById(Integer id) {
		// TODO Auto-generated method stub
		String hql="from Auth a where a.auth.id=?";
		return authDao.findEntityByHQL(hql, id);
	}

	@Override
	public boolean deleteAuth(Auth auth) {
		// TODO Auto-generated method stub
		try {
			authDao.deleteEntity(auth);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<Auth> findAllAuthsByRoleId(Integer id) {
		// TODO Auto-generated method stub
		String sql="select * from sys_role_auth ra where ra.ROLE_ID=?";
		List<Object[]> objects=authDao.findEntityBySql(sql, id);
		List<Auth> auths=new ArrayList<Auth>();
		
		if(objects!=null){
			for(Object[] object:objects){
				//根据权限id查找权限
				auths.add(authDao.getEntity((Integer)object[0]));
			}
		}
		return auths;
	}
	
	@Override
	public Auth findAuthsByUrl(String url){
		String hql="from Auth a where a.url=?";
		return authDao.findEntityByHQL(hql, url).get(0);
	}
}
