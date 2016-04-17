package com.benson.graduate.stu.model;

/**
 * 学院实体 
 */
public class Department {
	
	private Integer id;    //主键
	private String name;   //学院名称
	private String remark;  //学院描述
	
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
	
	
}
