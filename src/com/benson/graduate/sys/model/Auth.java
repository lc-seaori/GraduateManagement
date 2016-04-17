package com.benson.graduate.sys.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 权限实体
 * @author benson
 */
public class Auth {
	
	private Integer id;		//id自增
	private String name;	//权限名字
	private String url;		//权限路径
	private String icon;	//权限图标
	private Date createTime;	//权限创建时间
	private Auth auth;		//上一级权限
	private String description;	//权限描述
	private Set<Role> roles=new HashSet<Role>();	//与角色多对多的关系
	private Set<Auth> auths=new HashSet<Auth>();	//下级（子级）权限
	
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Auth getAuth() {
		return auth;
	}
	public void setAuth(Auth auth) {
		this.auth = auth;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Set<Auth> getAuths() {
		return auths;
	}
	public void setAuths(Set<Auth> auths) {
		this.auths = auths;
	}
	
}
