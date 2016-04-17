package com.benson.graduate.stu.model;

/**
 *  班级实体 
 */
public class Clazz {
	
	private Integer id;   //主键
	private String name;  //班级名称
	private String classQqNumber;  //班级q群
	//单向n对1关联   与年级之间的关系
	private Grade grade;
	//单向n对1关联  与专业之间的关系
	private MajorField majorField;
	
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
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public MajorField getMajorField() {
		return majorField;
	}
	public void setMajorField(MajorField majorField) {
		this.majorField = majorField;
	}
	
	
}	
