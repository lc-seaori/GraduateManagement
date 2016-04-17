package com.benson.graduate.stu.model;

/**
 *	学生实体（已入学） 
 */
public class Student {
	
	private Integer id;    //主键
	private String name;	//学生名称
	private String code;	//学号
	private Integer schoolStatus;   //学籍状态(正常、休学、辍学、退学、毕业)
	//学生与学院之间的单向n-1关系
	private Department department;
	//学生与班级之间的单向n-1关系
	private Clazz clazz;
	//学生与学生基本信息之间的1-1关系（双向好还是单向好？）
	private StudentInfo studentInfo;
	
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
	public Integer getSchoolStatus() {
		return schoolStatus;
	}
	public void setSchoolStatus(Integer schoolStatus) {
		this.schoolStatus = schoolStatus;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Clazz getClazz() {
		return clazz;
	}
	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}
	public StudentInfo getStudentInfo() {
		return studentInfo;
	}
	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}
	
	
}
