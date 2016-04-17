package com.benson.graduate.stu.pagemodel;

import java.io.Serializable;

public class PageStudent implements Serializable {

	private static final long serialVersionUID = 8406171435974827381L;
	
	private Integer id;    //主键
	private String name;	//学生名称
	private String code;	//学号
	
	//需要转换的字段
	private String schoolStatus;  //学籍状态
	private String departmentName ;	//学院名字
	private String clazzName;	//班别
	private String graduationTime ; //毕业时间
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSchoolStatus() {
		return schoolStatus;
	}
	public void setSchoolStatus(String schoolStatus) {
		this.schoolStatus = schoolStatus;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getClazzName() {
		return clazzName;
	}
	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}
	public String getGraduationTime() {
		return graduationTime;
	}
	public void setGraduationTime(String graduationTime) {
		this.graduationTime = graduationTime;
	}
	
	

}
