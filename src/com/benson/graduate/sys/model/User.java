package com.benson.graduate.sys.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体
 * @author benson
 */
public class User {
	private Integer id;		//id自增
	private String loginname;   //登陆名
	private String password;	//密码
	private String name;	//姓名
	private Integer isdefault;  //是否默认
	private Integer state ;		//用户状态
	private Organization organization;	//所属组织部门
	private Date createTime;	//创建时间
	private Date modifyTime;	//最后更新时间
	private Set<Role> roles=new HashSet<Role>();  //用户的所有角色
	
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
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	
}
