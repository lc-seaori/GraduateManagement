package com.benson.graduate.company.model;

import java.util.Date;

/**
 *	招聘信息实体 
 */
public class RecruitmentInfo {
	
	private Integer id;		//主键
	private String position;	//招聘职位
	private Integer monthlySalary;	//月薪（范围值）
	private Integer workType;      //工作性质
	private Integer industryType; 	//行业领域
	private Integer educationType;	//学历（本科、大专）
	private int hireCount;		//招聘人数
	private Date releaseTime;		//发布时间
	private Date endTime;		//截止时间
	private String positionDescription;		//职业描述
	//招聘信息与招聘单位之间的单向n-1的关系
	private RecruitmentUnit recruitmentUnit;
	
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
	public Integer getMonthlySalary() {
		return monthlySalary;
	}
	public void setMonthlySalary(Integer monthlySalary) {
		this.monthlySalary = monthlySalary;
	}
	public Integer getWorkType() {
		return workType;
	}
	public void setWorkType(Integer workType) {
		this.workType = workType;
	}
	public Integer getIndustryType() {
		return industryType;
	}
	public void setIndustryType(Integer industryType) {
		this.industryType = industryType;
	}
	public Integer getEducationType() {
		return educationType;
	}
	public void setEducationType(Integer educationType) {
		this.educationType = educationType;
	}
	public int getHireCount() {
		return hireCount;
	}
	public void setHireCount(int hireCount) {
		this.hireCount = hireCount;
	}
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getPositionDescription() {
		return positionDescription;
	}
	public void setPositionDescription(String positionDescription) {
		this.positionDescription = positionDescription;
	}
	public RecruitmentUnit getRecruitmentUnit() {
		return recruitmentUnit;
	}
	public void setRecruitmentUnit(RecruitmentUnit recruitmentUnit) {
		this.recruitmentUnit = recruitmentUnit;
	}
}
