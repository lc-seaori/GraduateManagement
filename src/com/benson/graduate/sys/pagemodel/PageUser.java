package com.benson.graduate.sys.pagemodel;

import java.io.Serializable;

/**
 * 用户列表页面模型，用户与页面显示的数据进行交互
 * @author benson
 */
public class PageUser implements Serializable{
	
	private static final long serialVersionUID = 8025625247350992339L;
	
	//原来属性
	private Integer id;		//id自增
	private String loginname;   //登陆名
	private String password;	//密码
	private String name;	//姓名
	
	//需要转换的数据
	private String isDefaultPage;	//是否默认
	private String statePage;		//状态
	private String createTimePage;	//创建时间
	private String modifyTimePage;	//最后更新时间
	private Integer organizationId;	//所属组织部门id
	private String organizationName;	//所述组织部门名称
	
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
	public String getIsDefaultPage() {
		return isDefaultPage;
	}
	public void setIsDefaultPage(String isDefaultPage) {
		this.isDefaultPage = isDefaultPage;
	}
	public String getStatePage() {
		return statePage;
	}
	public void setStatePage(String statePage) {
		this.statePage = statePage;
	}
	public String getCreateTimePage() {
		return createTimePage;
	}
	public void setCreateTimePage(String createTimePage) {
		this.createTimePage = createTimePage;
	}
	public String getModifyTimePage() {
		return modifyTimePage;
	}
	public void setModifyTimePage(String modifyTimePage) {
		this.modifyTimePage = modifyTimePage;
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	
	
}
