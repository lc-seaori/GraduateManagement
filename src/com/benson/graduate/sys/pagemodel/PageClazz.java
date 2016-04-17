package com.benson.graduate.sys.pagemodel;

import java.io.Serializable;

public class PageClazz implements Serializable {

	private static final long serialVersionUID = 4497258876309948850L;
	
	//原model属性
	private Integer id;   //主键
	private String name;  //班级名称
	private String classQqNumber;  //班级q群
	
	//需要换转的属性
	private String majorFieldName;   //所属专业名称
	private String gradeName;		//所属年级

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
	public String getClassQqNumber() {
		return classQqNumber;
	}
	public void setClassQqNumber(String classQqNumber) {
		this.classQqNumber = classQqNumber;
	}
	public String getMajorFieldName() {
		return majorFieldName;
	}

	public void setMajorFieldName(String majorFieldName) {
		this.majorFieldName = majorFieldName;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	
}
