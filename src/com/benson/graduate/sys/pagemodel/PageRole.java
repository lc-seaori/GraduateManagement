package com.benson.graduate.sys.pagemodel;

import java.io.Serializable;

public class PageRole implements Serializable{

	private static final long serialVersionUID = 8551234691948247211L;
	
	//原Model
	private Integer id;		//id主键
	private String  name;	//角色名字
	private String description;	  //角色描述
	
	//需要转换
	private String isdefaultPage;	 //是否默认

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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIsdefaultPage() {
		return isdefaultPage;
	}
	public void setIsdefaultPage(String isdefaultPage) {
		this.isdefaultPage = isdefaultPage;
	}
	
}
