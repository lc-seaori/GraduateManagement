package com.benson.graduate.company.pagemodel;

public class PageRecruitmentInfo {

	// 原model
	private Integer id; // 主键
	private String position; // 招聘职位
	private int hireCount; // 招聘人数
	private String positionDescription; // 职业描述

	// 需要转换
	private String monthlySalary; // 月薪（范围值）
	private String workType; // 工作性质
	private String industryType; // 行业领域
	private String educationType; // 学历（本科、大专）
	private String releaseTime; // 发布时间
	private String endTime; // 截止时间
	
	//拓展
	private String unitName;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getHireCount() {
		return hireCount;
	}
	public void setHireCount(int hireCount) {
		this.hireCount = hireCount;
	}
	public String getPositionDescription() {
		return positionDescription;
	}
	public void setPositionDescription(String positionDescription) {
		this.positionDescription = positionDescription;
	}
	public String getMonthlySalary() {
		return monthlySalary;
	}
	public void setMonthlySalary(String monthlySalary) {
		this.monthlySalary = monthlySalary;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public String getIndustryType() {
		return industryType;
	}
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	public String getEducationType() {
		return educationType;
	}
	public void setEducationType(String educationType) {
		this.educationType = educationType;
	}
	public String getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	

}