package com.benson.graduate.stu.model;

import java.util.Date;

/**
 *  学生源信息实体（或者招生信息实体）
 */
public class StudentInfo {
	
	private Integer id;    //主键
	private Integer departmentAduitStatus=10001;	//学院审核（默认未审核） /审核中/通过/不通过
	private Integer schoolAduitStatus=10001;	//学校审核（默认未审核） /审核中/通过/不通过
	private String examNum; //准考证
	private String name;   //学生名字
	private String graduationSchool; //毕业院校
	private Integer sexType;   //性别类型
	//单向n对1关系，与专业之间的关系
	private MajorField majorField;
	private Date birthday;	//生日
	private String idCard;  //身份证号码
	private Integer nationType;   //民族类型
	private Integer politicalFeatureType;    //政治面貌
	private String nativePlace;		//籍贯
	private Integer householdType;  //户籍类型
	private String householdPlace;  //户口所在地
	private Integer schoolLengthType;  //学制类型（3年制/4年制/5年制）
	private Integer educationType;   //学历（高中/中专）
	private String phone; 		//联系方式
	private Date enterCollegeTime;   //入学年份
	private Date graduateTime;		//毕业时间
	private String postEncoding;	//邮政编码
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDepartmentAduitStatus() {
		return departmentAduitStatus;
	}
	public void setDepartmentAduitStatus(Integer departmentAduitStatus) {
		this.departmentAduitStatus = departmentAduitStatus;
	}
	public Integer getSchoolAduitStatus() {
		return schoolAduitStatus;
	}
	public void setSchoolAduitStatus(Integer schoolAduitStatus) {
		this.schoolAduitStatus = schoolAduitStatus;
	}
	public String getExamNum() {
		return examNum;
	}
	public void setExamNum(String examNum) {
		this.examNum = examNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGraduationSchool() {
		return graduationSchool;
	}
	public void setGraduationSchool(String graduationSchool) {
		this.graduationSchool = graduationSchool;
	}
	public Integer getSexType() {
		return sexType;
	}
	public void setSexType(Integer sexType) {
		this.sexType = sexType;
	}
	public MajorField getMajorField() {
		return majorField;
	}
	public void setMajorField(MajorField majorField) {
		this.majorField = majorField;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Integer getNationType() {
		return nationType;
	}
	public void setNationType(Integer nationType) {
		this.nationType = nationType;
	}
	public Integer getPoliticalFeatureType() {
		return politicalFeatureType;
	}
	public void setPoliticalFeatureType(Integer politicalFeatureType) {
		this.politicalFeatureType = politicalFeatureType;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public Integer getHouseholdType() {
		return householdType;
	}
	public void setHouseholdType(Integer householdType) {
		this.householdType = householdType;
	}
	public String getHouseholdPlace() {
		return householdPlace;
	}
	public void setHouseholdPlace(String householdPlace) {
		this.householdPlace = householdPlace;
	}
	public Integer getSchoolLengthType() {
		return schoolLengthType;
	}
	public void setSchoolLengthType(Integer schoolLengthType) {
		this.schoolLengthType = schoolLengthType;
	}
	public Integer getEducationType() {
		return educationType;
	}
	public void setEducationType(Integer educationType) {
		this.educationType = educationType;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getEnterCollegeTime() {
		return enterCollegeTime;
	}
	public void setEnterCollegeTime(Date enterCollegeTime) {
		this.enterCollegeTime = enterCollegeTime;
	}
	public Date getGraduateTime() {
		return graduateTime;
	}
	public void setGraduateTime(Date graduateTime) {
		this.graduateTime = graduateTime;
	}
	public String getPostEncoding() {
		return postEncoding;
	}
	public void setPostEncoding(String postEncoding) {
		this.postEncoding = postEncoding;
	}
	
	
	
}
