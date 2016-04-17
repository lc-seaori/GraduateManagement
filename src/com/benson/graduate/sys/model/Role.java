package com.benson.graduate.sys.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色实体
 * @author benson
 */
public class Role {
	private Integer id;		//id主键
	private String  name;	//角色名字
	private Integer isdefault;	//是否默认
	private String description;	//描述
	private Set<Auth> auths =new HashSet<Auth>();  //角色所有权限
	private Set<User> users=new HashSet<User>();	//与用户的多对多关联关系
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Auth> getAuths() {
		return auths;
	}
	public void setAuths(Set<Auth> auths) {
		this.auths = auths;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
}
