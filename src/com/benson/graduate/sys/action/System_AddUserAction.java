package com.benson.graduate.sys.action;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.common.enumeration.EnumerationType;
import com.benson.graduate.sys.model.EnumerationValue;
import com.benson.graduate.sys.model.Role;
import com.benson.graduate.sys.model.User;
import com.benson.graduate.sys.service.EnumerationValueService;
import com.benson.graduate.sys.service.OrganizationService;
import com.benson.graduate.sys.service.RoleService;
import com.benson.graduate.sys.service.UserService;
import com.benson.graduate.utils.MD5Util;
import com.opensymphony.xwork2.Preparable;

/**
 * 添加用户使用的Action
 * @author benson
 */
@Controller("addUserAction")
@Scope("prototype")
public class System_AddUserAction extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 745771940861495539L;
	private EnumerationValueService enumerationValueService;
	@Resource(name="enumerationValueService")
	public void setEnumerationValueService(
			EnumerationValueService enumerationValueService) {
		this.enumerationValueService = enumerationValueService;
	}
	private OrganizationService organizationService;
	@Resource(name="organizationService")
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
	private RoleService roleService;
	@Resource(name="roleService")
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	private UserService userService;
	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	//接收从addUser.jsp页面传来的参数
	private String loginname;
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	//表单参数
	private Integer isdefault;
	private Integer state;
	private Integer organization;
	private Integer[] role;
	private String password;
	private String name;
	public Integer getIsdefault() {
		return isdefault;
	}
	public void setIsdefault(Integer isdefault) {
		this.isdefault = isdefault;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getOrganization() {
		return organization;
	}
	public void setOrganization(Integer organization) {
		this.organization = organization;
	}
	public Integer[] getRole() {
		return role;
	}
	public void setRole(Integer[] role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/*private User model =new User();
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		System.out.println("getModel()");
		return model;
	}*/
	
	
	/**
	 * 进入添加页面时需要准备数据
	 */
	public void prepareToAddUserPage(){
		System.out.println("prepareToAddUserPage()");
		//准备添加页面需要的数据
		//是否默认
		List<EnumerationValue> values=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.BOOLEAN_TYPE);
		request.setAttribute("isdefault", values);
		//用户状态
		values=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.USER_STATE);
		request.setAttribute("status", values);
		//所有组织部门
		request.setAttribute("organizations", organizationService.findAllOrganization());
		//所属角色
		request.setAttribute("roles", roleService.findAllRoles());
	}
	
	/**
	 * 进入添加用户页面
	 */
	public String toAddUserPage(){
		System.out.println("toAddUserPage()");
		return "addUserPage";
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("prepare()");
	}
	
	//异步验证用户名是否正确
	public void validateLoginname(){
		System.out.println("validateLoginname()");
		System.out.println("用户名为：    "+loginname);
		//根据用户名查找用户是否存在
		List<User> user=userService.findUserByLoginName(loginname);
		Json json =new Json();
		if(user!=null&&user.size()>0){
			//查找到用户,用户名 不可用
			json.setSuccess(false);
		}else{
			json.setSuccess(true);
		}
		super.writeJson(json);
	}
	
	/**
	 * 添加用户
	 */
	public void doAddUser(){
//		System.out.println("addUser()");
//		System.out.println("单个测试role    ");
//		for(Integer id:role){
//			System.out.println("-------------------->  "+id);
//		}
		//System.out.println("测试：  "+ model.getLoginname()+"  "+model.getName()+"  "+model.getPassword()+"  ");
//		System.out.println(loginname+"   "+password+"  "+name);
//		System.out.println(isdefault+"  "+state+"  "+organization+"   "+role);
		User user=new User();
		user.setLoginname(loginname.trim());
		user.setPassword(MD5Util.md5(password));
		user.setName(name.trim());
		user.setIsdefault(isdefault);
		user.setState(state);
		//根据id查找组织部门
		user.setOrganization(organizationService.findOrganizationById(organization));
		user.setCreateTime(new Date());
		user.setModifyTime(new Date());
		Set<Role> roles=new HashSet<Role>();
		
		for(Integer id:role){
			if(id!=0){
				//如果是0就是空白的
				//根据id查找角色
				Role role = roleService.findRoleById(id);
				roles.add(role);
			}
		}
		user.setRoles(roles);
		boolean result=userService.addUser(user);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
}
