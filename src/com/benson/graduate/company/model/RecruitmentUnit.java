package com.benson.graduate.company.model;

/**
 *  招聘单位实体 
 */
public class RecruitmentUnit {

	private Integer id;			//主键
	private Integer recruitmentType;	//招聘方式（现场招聘会、网络招聘等）
	private String unitCode;	//招聘单位编号
	private String unitName;	//招聘单位名称
	private String unitAddress;		//招聘单位详细地址
	private Integer unitType;	//招聘单位性质
	private String unitContantPerson;	//单位联系人
	private String unitContantPhone;	//单位联系电话
	private String unitDescription;		//单位描述
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRecruitmentType() {
		return recruitmentType;
	}
	public void setRecruitmentType(Integer recruitmentType) {
		this.recruitmentType = recruitmentType;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getUnitAddress() {
		return unitAddress;
	}
	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}
	public Integer getUnitType() {
		return unitType;
	}
	public void setUnitType(Integer unitType) {
		this.unitType = unitType;
	}
	public String getUnitContantPerson() {
		return unitContantPerson;
	}
	public void setUnitContantPerson(String unitContantPerson) {
		this.unitContantPerson = unitContantPerson;
	}
	public String getUnitContantPhone() {
		return unitContantPhone;
	}
	public void setUnitContantPhone(String unitContantPhone) {
		this.unitContantPhone = unitContantPhone;
	}
	public String getUnitDescription() {
		return unitDescription;
	}
	public void setUnitDescription(String unitDescription) {
		this.unitDescription = unitDescription;
	}
	
	
}
