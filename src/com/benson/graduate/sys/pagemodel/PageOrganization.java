package com.benson.graduate.sys.pagemodel;

import java.io.Serializable;
import java.util.List;

public class PageOrganization implements Serializable {

	private static final long serialVersionUID = 1764073874062124079L;
	private Integer id;		//id自增
	private String name;     //组织部门名字
	private String icon;	//部门图标
	private String description;		//组织描述
	
	private String iconCls;     //权限图标
	private List<PageOrganization> children;	//孩子组织部门
	private String state="open";   //默认是关闭
	private String pid;		//上级权限id
	private String pname;
	
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public List<PageOrganization> getChildren() {
		return children;
	}
	public void setChildren(List<PageOrganization> children) {
		this.children = children;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	
}
