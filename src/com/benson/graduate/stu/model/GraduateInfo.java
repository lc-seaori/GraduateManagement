package com.benson.graduate.stu.model;

/**
 *	毕业生就业信息实体
 */
public class GraduateInfo {

	private Integer id;		//主键
	private Integer departmentGAduitStatus=10001;	//学院审核（默认未审核） /审核中/通过/不通过
	private Integer schoolGAduitStatus=10001;	//学校审核（默认未审核） /审核中/通过/不通过
	private String name;	//学生名称
	private String unitName;	//就业单位名称
	private String unitLocation;	//就业单位所在地
	private Integer unitType;	//单位性质（过期、事业单位、国家行政机关、政府）
//	private String graudationSchool;	//毕业院校    （默认是：五邑大学）
//	private Integer educationType;		//学历（默认是：本科）
	private String unitIndustry;		//就业单位行业
	private String unitContantPerson;	//单位联系人
	private String unitContantPhone;	//单位联系电话
	private String unitAddress;			//就业单位详细地址（放最后）
	private String graduatePhone;		//毕业生联系方式
	private Integer applyType;			//应聘方式（校园招聘等）
	private String unitPostEncoding;	//单位邮政编码
	//毕业生也学生信息之间1-1的关系
	private Student student;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDepartmentGAduitStatus() {
		return departmentGAduitStatus;
	}
	public void setDepartmentGAduitStatus(Integer departmentGAduitStatus) {
		this.departmentGAduitStatus = departmentGAduitStatus;
	}
	public Integer getSchoolGAduitStatus() {
		return schoolGAduitStatus;
	}
	public void setSchoolGAduitStatus(Integer schoolGAduitStatus) {
		this.schoolGAduitStatus = schoolGAduitStatus;
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
	public Integer getUnitType() {
		return unitType;
	}
	public void setUnitType(Integer unitType) {
		this.unitType = unitType;
	}
//	public String getGraudationSchool() {
//		return graudationSchool;
//	}
//	public void setGraudationSchool(String graudationSchool) {
//		this.graudationSchool = graudationSchool;
//	}
//	public Integer getEducationType() {
//		return educationType;
//	}
//	public void setEducationType(Integer educationType) {
//		this.educationType = educationType;
//	}
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
	public Integer getApplyType() {
		return applyType;
	}
	public void setApplyType(Integer applyType) {
		this.applyType = applyType;
	}
	public String getUnitPostEncoding() {
		return unitPostEncoding;
	}
	public void setUnitPostEncoding(String unitPostEncoding) {
		this.unitPostEncoding = unitPostEncoding;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
}
