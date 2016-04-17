package com.benson.graduate.sys.pagemodel;

import java.io.Serializable;

public class PageMajorField implements Serializable {
	
	private static final long serialVersionUID = 2845498775548413943L;
	
	//原model属性
	private Integer id;  //主键
	private String name; //专业方向名称
	private String remark; //专业描述
	
	//需要转换的属性
	private String departmentName;

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	
	
}	
