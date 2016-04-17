package com.benson.graduate.company.pagemodel;

/**
 * 用于页面传输的招聘单位信息model
 * @author benson
 *
 */
public class PageRecruitmentUnit {
	
	//原字段
	private Integer id;			//主键
	private String unitCode;	//招聘单位编号
	private String unitName;	//招聘单位名称
	private String unitAddress;		//招聘单位详细地址
	private String unitContantPerson;	//单位联系人
	private String unitContantPhone;	//单位联系电话
	private String unitDescription;		//单位描述private Integer id;
	
	//转换字段
	private String unit;	//招聘单位性质
	private String recruitment;	//招聘方式（现场招聘会、网络招聘等）
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getRecruitment() {
		return recruitment;
	}
	public void setRecruitment(String recruitment) {
		this.recruitment = recruitment;
	}
	
	
	
}
