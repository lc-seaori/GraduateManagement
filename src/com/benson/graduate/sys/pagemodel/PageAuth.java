package com.benson.graduate.sys.pagemodel;

import java.io.Serializable;
import java.util.List;

public class PageAuth implements Serializable {

	private static final long serialVersionUID = 6982911537932956712L;

	private Integer id; // id自增
	private String name; // 权限名字
	private String url; // 权限路径
	private String icon; // 权限图标
	private String description; // 权限描述
	
	private String iconCls;     //权限图标
	private String createTimePage; // 权限创建时间
	private List<PageAuth> children;
	private boolean isParent;
	private String state="open";   //默认是关闭
	
	//private Integer pid; // 上级权限id
	private String pid;
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

	public String getCreateTimePage() {
		return createTimePage;
	}

	public void setCreateTimePage(String createTimePage) {
		this.createTimePage = createTimePage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public List<PageAuth> getChildren() {
		return children;
	}
	public void setChildren(List<PageAuth> children) {
		this.children = children;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
//	public Integer getPid() {
//		return pid;
//	}
//	public void setPid(Integer pid) {
//		this.pid = pid;
//	}
	

}
