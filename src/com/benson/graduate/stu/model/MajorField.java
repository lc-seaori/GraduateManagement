package com.benson.graduate.stu.model;

/**
 * 专业实体 
 */
public class MajorField {
	private Integer id;  //主键
	private String name; //专业方向名称
	private String remark; //专业描述
	//单向n对1关联
	private Department department;

	
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
}
