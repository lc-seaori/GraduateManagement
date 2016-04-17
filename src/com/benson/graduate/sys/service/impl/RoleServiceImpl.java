package com.benson.graduate.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.service.impl.BaseServiceImpl;
import com.benson.graduate.sys.dao.EnumerationValueDao;
import com.benson.graduate.sys.dao.RoleDao;
import com.benson.graduate.sys.model.EnumerationValue;
import com.benson.graduate.sys.model.Role;
import com.benson.graduate.sys.pagemodel.PageRole;
import com.benson.graduate.sys.service.RoleService;
/**
 * 角色业务逻辑组件实现类
 * @author benson
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {

	private RoleDao roleDao;
	@Resource(name="roleDao")
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	private EnumerationValueDao enumerationValueDao;
	@Resource(name="enumerationValueDao")
	public void setEnumerationValueDao(EnumerationValueDao enumerationValueDao) {
		this.enumerationValueDao = enumerationValueDao;
	}

	@Override
	public List<Role> findAllRoles() {
		// TODO Auto-generated method stub
		String hql="from Role r";
		return roleDao.findEntityByHQL(hql);
	}

	@Override
	public Role findRoleById(Integer id) {
		// TODO Auto-generated method stub
		return roleDao.getEntity(id);
	}

	@Override
	public List<Role> findAllRolesByUserId(Integer id) {
		// TODO Auto-generated method stub
		String sql="select * from sys_user_role ur where ur.USER_ID=?";
		List<Object[]> objects=roleDao.findEntityBySql(sql, id);
		List<Role> roles=new ArrayList<Role>();
		if(objects!=null){
			for(Object[] object:objects){
				//根据角色id查询角色
				roles.add(roleDao.getEntity((Integer)object[1]));
			}
		}
		return roles;
	}

	@Override
	public DataGrid getDataGrid(String name, String sort, String order,
			int pageNow, int pageRows) {
		// TODO Auto-generated method stub
		String hql="from Role r";
		List<Role> roles=null;
		//一个为null就不会继续判断
		if(name==null){
			hql="from Role r";
		}else{
			hql+=" where name like \'%"+name+"%\'";
		}
		if(sort!=null&&order!=null){
			hql+=" order by "+sort+" "+order;
		}
		System.out.println("最后查询的hql为：   "+hql);
		roles=this.getAllRolesByPage(hql,pageNow,pageRows);
		if(roles!=null && roles.size()>0){
			List<PageRole> pageRoles=modelChangeToPageModel(roles);
			DataGrid data=new DataGrid();
			data.setTotal(getTotals(hql));
			data.setRows(pageRoles);
			return data;
		}else{
			DataGrid data=new DataGrid();
			data.setRows(new ArrayList<PageRole>());
			data.setTotal(0L);
			return data;
		}
	}
	
	//分页查询所有角色
	public List<Role> getAllRolesByPage(String hql,int pageNow,int pageRows) {
		return roleDao.findEntityByHQL(hql, pageNow, pageRows);
	}
	
	//RoleModel转换成页面PageModel模型
	public List<PageRole> modelChangeToPageModel(List<Role> roles){
		List<PageRole> pageRoles =new ArrayList<PageRole>();
		for(Role role:roles){
			PageRole pageRole=new PageRole();
			//拷贝相同属性
			BeanUtils.copyProperties(role, pageRole);
			
			//转换是否默认字段
			EnumerationValue enumerationValue=enumerationValueDao.getEntity(role.getIsdefault());
			if(enumerationValue!=null){
				pageRole.setIsdefaultPage(enumerationValue.getName());
			}
			
			pageRoles.add(pageRole);
		}
		return pageRoles;
	}
	
	public Long getTotals(String hql){
		hql="select count(*) "+hql;
		return roleDao.getRolesCount(hql);
	}

	@Override
	public boolean addRole(Role role) {
		// TODO Auto-generated method stub
		try {
			roleDao.saveEntity(role);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateRole(Role role) {
		// TODO Auto-generated method stub
		try {
			roleDao.updateEntity(role);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteSelectedRoles(String idList) {
		// TODO Auto-generated method stub
		try {
			 roleDao.deleteRole(stringToInt(idList));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//将逗号隔开的id列表转化为Integer数组
	public List<Integer> stringToInt(String idList){
		String []str_id=idList.split(",");
		List<Integer> ids=new ArrayList<Integer>();
		for(int i=0;i<str_id.length;i++){
			ids.add(Integer.parseInt(str_id[i]));
		}
		return ids;
	}

}
