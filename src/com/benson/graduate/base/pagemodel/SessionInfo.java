package com.benson.graduate.base.pagemodel;

import java.util.List;

/**
 * sessionInfo模型
 * 
 * @author Benson
 * 
 */
public class SessionInfo implements java.io.Serializable {

	private static final long serialVersionUID = -1614612552705574508L;

	private Integer id;// 用户ID
	private String loginname;// 用户登录名称
	private String name;	// 姓名
	private List<String> authList;// 用户可以访问的权限地址列表
	
	private List<String> authAllList;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getAuthList() {
		return authList;
	}
	public void setAuthList(List<String> authList) {
		this.authList = authList;
	}
	public List<String> getAuthAllList() {
		return authAllList;
	}
	public void setAuthAllList(List<String> authAllList) {
		this.authAllList = authAllList;
	}
	
	@Override
	public String toString() {
		return loginname;
	}
}
