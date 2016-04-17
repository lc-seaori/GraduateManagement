package com.benson.graduate.sys.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.service.impl.BaseServiceImpl;
import com.benson.graduate.sys.dao.EnumerationValueDao;
import com.benson.graduate.sys.dao.UserDao;
import com.benson.graduate.sys.model.EnumerationValue;
import com.benson.graduate.sys.model.User;
import com.benson.graduate.sys.pagemodel.PageUser;
import com.benson.graduate.sys.service.UserService;

/**
 * 用户操作的业务逻辑
 * @author benson
 *
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	
	private UserDao userDao;
	@Resource(name="userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	private EnumerationValueDao enumerationValueDao;
	@Resource(name="enumerationValueDao")
	public void setEnumerationValueDao(EnumerationValueDao enumerationValueDao) {
		this.enumerationValueDao = enumerationValueDao;
	}

	
	public List<User> findUserByLoginName(String loginname){
		String hql="from User u where u.loginname=?";
		return userDao.findEntityByHQL(hql, loginname);
	}


	@Override
	public User findUserByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return userDao.getEntity(userId);
	}


	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		try {
			userDao.updateEntity(user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public DataGrid getDataGrid(String loginname, String name, Integer organizationId,String sort,
			String order, int pageNow, int pageRows) {
		// TODO Auto-generated method stub
		String hql="";
		List<User> users=null;
		//第一次datagrid自动发送请求时，三个字段为null
		//一个为null就不会继续判断
		if(loginname==null&&name==null){
			hql="from User u";
			if(organizationId!=null&&organizationId!=0){
				hql+=" where u.organization.id="+organizationId;
			}
		}else{
			hql="from User u";
			if(!loginname.equals("")){
				hql+=" where u.loginname like \'%"+loginname+"%\'";
				if(!name.equals("")){
					hql+=" and u.name like \'%"+name+"%\'";
					if(organizationId!=null&&organizationId!=0){
						hql+=" and u.organization.id="+organizationId;
					}
				}else{
					if(organizationId!=null&&organizationId!=0){
						hql+=" and u.organization.id="+organizationId;
					}
				}
			}else{
				if(!name.equals("")){
					hql+=" where u.name like \'%"+name+"%\'";
					if(organizationId!=null&&organizationId!=0){
						hql+=" and u.organization.id="+organizationId;
					}
				}else{
					if(organizationId!=null&&organizationId!=0){
						hql+=" where u.organization.id="+organizationId;
					}
				}
			}
		}
		if(sort!=null&&order!=null){
			hql+=" order by "+sort+" "+order;
		}
		System.out.println("最后查询的hql为--------------------------------------：   "+hql);
		users=getAllUsersByPage(hql,pageNow,pageRows);
		
		if(users!=null && users.size()>0){
			List<PageUser> pageUsers=modelChangeToPageModel(users);
			DataGrid data=new DataGrid();
			data.setTotal(getTotals(hql));
			data.setRows(pageUsers);
			return data;
		}else{
			DataGrid data=new DataGrid();
			data.setRows(new ArrayList<PageUser>());
			data.setTotal(0L);
			return data;
		}
	}

	/**
	 * 分页获取所有用户
	 * @param hql
	 * @param pageNow
	 * @param pageRows
	 * @return
	 */
	public List<User> getAllUsersByPage(String hql,int pageNow,int pageRows) {
		return userDao.findEntityByHQL(hql, pageNow, pageRows);
	}
	
	/**
	 * 转为用户页面模型
	 * @param graduateInfo
	 * @return
	 */
	public List<PageUser> modelChangeToPageModel(List<User> users){
		List<PageUser> pageUsers=new ArrayList<PageUser>();
		for(User user:users){
			PageUser pageUser=new PageUser();
			BeanUtils.copyProperties(user,pageUser);
			
			//转换是否默认
			EnumerationValue enumerationValue=enumerationValueDao.getEntity(user.getIsdefault());
			if(enumerationValue!=null){
				pageUser.setIsDefaultPage(enumerationValue.getName());
			}
			//转换用户状态
			enumerationValue=enumerationValueDao.getEntity(user.getState());
			if(enumerationValue!=null){
				pageUser.setStatePage(enumerationValue.getName());
			}
			//转换创建时间和最后更新时间格式
			Date date1=user.getCreateTime();
			Date date2=user.getModifyTime();
			if(date1!=null&&date2!=null){
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
				String createTime="";
				String modifyTime="";
				try {
					createTime=dateFormat.format(date1);
					modifyTime=dateFormat.format(date2);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pageUser.setCreateTimePage(createTime);
				pageUser.setModifyTimePage(modifyTime);
			}
			
			//转换组织部门名称
			if(user.getOrganization()!=null){
				pageUser.setOrganizationId(user.getOrganization().getId());
				pageUser.setOrganizationName(user.getOrganization().getName());
			}else{
				//查不到的话
				pageUser.setOrganizationName("");
				pageUser.setOrganizationId(0);
			}
			
			pageUsers.add(pageUser);
		}
		return pageUsers;
	}
	
	public Long getTotals(String hql){
		hql="select count(*) "+hql;
		return userDao.getUsersCount(hql);
	}


	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		try {
			userDao.saveEntity(user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteSelectedUser(String idList) {
		// TODO Auto-generated method stub
		try {
			 userDao.deleteUser(stringToInt(idList));
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
