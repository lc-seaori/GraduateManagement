package com.benson.graduate.stu.pagemodel;

public class PageGraduateInfo {
	
	//原model
	private Integer id;		//主键
	private String name;	//学生名称
	private String unitName;	//就业单位名称
	private String unitLocation;	//就业单位所在地
	private String unitIndustry;		//就业单位行业
	private String unitContantPerson;	//单位联系人
	private String unitContantPhone;	//单位联系电话
	private String unitAddress;			//就业单位详细地址（放最后）
	private String graduatePhone;		//毕业生联系方式
	private String unitPostEncoding;	//单位邮政编码
	
	//需要转换
	private String departmentGAduit;	//学院审核（默认未审核） /审核中/通过/不通过
	private String schoolGAduit;	//学校审核（默认未审核） /审核中/通过/不通过
	private String unitTy;			//单位性质
	private String applyTy;			//应聘方式
	
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
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getUnitLocation() {
		return unitLocation;
	}
	public void setUnitLocation(String unitLocation) {
		this.unitLocation = unitLocation;
	}
	public String getUnitIndustry() {
		return unitIndustry;
	}
	public void setUnitIndustry(String unitIndustry) {
		this.unitIndustry = unitIndustry;
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
	public String getUnitAddress() {
		return unitAddress;
	}
	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}
	public String getGraduatePhone() {
		return graduatePhone;
	}
	public void setGraduatePhone(String graduatePhone) {
		this.graduatePhone = graduatePhone;
	}
	public String getUnitPostEncoding() {
		return unitPostEncoding;
	}
	public void setUnitPostEncoding(String unitPostEncoding) {
		this.unitPostEncoding = unitPostEncoding;
	}
	
	
	public String getDepartmentGAduit() {
		return departmentGAduit;
	}
	public void setDepartmentGAduit(String departmentGAduit) {
		this.departmentGAduit = departmentGAduit;
	}
	public String getSchoolGAduit() {
		return schoolGAduit;
	}
	public void setSchoolGAduit(String schoolGAduit) {
		this.schoolGAduit = schoolGAduit;
	}
	public String getUnitTy() {
		return unitTy;
	}
	public void setUnitTy(String unitTy) {
		this.unitTy = unitTy;
	}
	public String getApplyTy() {
		return applyTy;
	}
	public void setApplyTy(String applyTy) {
		this.applyTy = applyTy;
	}
	
}
