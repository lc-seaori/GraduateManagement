package com.benson.graduate.sys.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;







import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Combobox;
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
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

@Controller("editUserAction")
@Scope("prototype")
public class System_EditUserAction extends BaseAction implements ModelDriven<User>,Preparable{
	
	private static final long serialVersionUID = -5235438129657056064L;
	
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
	
	//接收从userList.jsp页面传送过来的用户id
	private Integer userId;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	private Integer id;
	private String loginname;
	private String password;
	private String createTime;
	private String name;
	private Integer isdefault;
	private Integer state;
	private Integer organization;
	private String newPassword;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	//接收从editUser.jsp传送过来的数据
	private Integer[] roles;
	public Integer[] getRoles() {
		return roles;
	}
	public void setRoles(Integer[] roles) {
		this.roles = roles;
	}
	
	private User model;
	
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		System.out.println("getModel()");
		return model;
	}
	
	public void prepareToEditUserPage(){
		//根据用户id查找用户
		System.out.println("prepareToEditUserPage()");
		//为了下拉多选框拿到json数据
		ActionContext.getContext().getSession().put("selectedUserId", userId);
		model=userService.findUserByUserId(userId);
	}
	
	public String toEditUserPage(){
		//准备添加页面需要的数据
		//是否默认
		List<EnumerationValue> values=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.BOOLEAN_TYPE);
		request.setAttribute("isdefault", values);
		//用户状态
		values=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.USER_STATE);
		request.setAttribute("status", values);
		//所有组织部门
		request.setAttribute("organizations", organizationService.findAllOrganization());
		return "editUserPage";
	}
	
	/**
	 * 获取多选框数据
	 */
	public void getCombobox(){
		List<Combobox> comboxes=new ArrayList<Combobox>();
		User user=userService.findUserByUserId((Integer)ActionContext.getContext().getSession().get("selectedUserId"));
		ActionContext.getContext().getSession().remove("selectedUserId");
		Set<Role> haveRole=user.getRoles();
		List<Role> allRole=roleService.findAllRoles();
		for(Role role:allRole){
			Combobox box=new Combobox();
			box.setId(role.getId());
			box.setText(role.getName());
			if(haveRole!=null&&haveRole.size()>0){
				Iterator<Role> iterator=haveRole.iterator();
				while(iterator.hasNext()){
					Role r=iterator.next();
					if(r.getId()==role.getId()){
						box.setSelected(true);
					}
				}
			}
			comboxes.add(box);
		}
		super.writeJson(comboxes);
	}
	
	/**
	 * 修改数据
	 */
	public void doEditUser(){
		System.out.println("doEditUser()");
//		System.out.println("测试数据: "+model.getId()+
//				"  "+model.getLoginname()+
//				"  "+model.getPassword()+
//				"  "+model.getName()+
//				"  "+model.getIsdefault()+
//				"  "+model.getState()+
//				"   "+model.getCreateTime()+
//				"  "+model.getModifyTime()+
//				"   "+model.getOrganization().getName());
		System.out.println("测试数据：   "+id+"   "+loginname+" "+password+"   "+newPassword+"  "+name+"  "+isdefault+"  "+state+"  "+organization+"  "+createTime);
		User user=userService.findUserByUserId(id);
		//登录名、不修改
		user.setName(name);
		//密码逻辑
		if(!newPassword.trim().equals("")){
			//不为空就是修改
			user.setPassword(MD5Util.md5(newPassword));
		}
		user.setIsdefault(isdefault);
		user.setState(state);
		user.setOrganization(organizationService.findOrganizationById(organization));
		//更新时间
		user.setModifyTime(new Date());
		Set<Role> roleSet=new HashSet<Role>();
		if(roles!=null&&roles.length>0){
			for(Integer id:roles){
				System.out.println("id  :"+id);
				Role role=roleService.findRoleById(id);
				roleSet.add(role);
			}
		}
		user.setRoles(roleSet);
		boolean result=userService.updateUser(user);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
