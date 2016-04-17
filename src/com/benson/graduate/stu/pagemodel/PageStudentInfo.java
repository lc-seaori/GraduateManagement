package com.benson.graduate.stu.pagemodel;

import java.io.Serializable;

/**
 *  学生生源信息页面实体，用于与页面进行交互
 */
public class PageStudentInfo implements Serializable{
	
	private static final long serialVersionUID = -3716294075050605552L;
	
	//原model
	private Integer id;    //主键
	private String examNum; //准考证
	private String name;   //学生名字
	private String graduationSchool; //毕业院校
	private String idCard;  //身份证号码
	private String nativePlace;		//籍贯
	private String householdPlace;  //户口所在地
	private String phone; 		//联系方式
	private String postEncoding;	//邮政编码
	
	//需要转换
	private String sex;   //性别
	private String departmentAduit;			//学院审核（默认未审核） /审核中/通过/不通过
	private String schoolAduit;  //学校审核（默认未审核） /审核中/通过/不通过
	private String majorFieldName;  //所报专业名称
	private String birthday;	//生日(转换成字符串)
	private String nation;   //民族
	private String politicalFeature;    //政治面貌
	private String household;  //户籍
	private String schoolLength;  //学制（3年制/4年制/5年制）
	private String education;   //学历（高中/中专）
	private String enterCollegeTime;   //入学年份(转换成xxxx年xx月xx日的格式)
	private String graduateTime;	//毕业年份
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getHouseholdPlace() {
		return householdPlace;
	}
	public void setHouseholdPlace(String householdPlace) {
		this.householdPlace = householdPlace;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPostEncoding() {
		return postEncoding;
	}
	public void setPostEncoding(String postEncoding) {
		this.postEncoding = postEncoding;
	}
	
	
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDepartmentAduit() {
		return departmentAduit;
	}
	public void setDepartmentAduit(String departmentAduit) {
		this.departmentAduit = departmentAduit;
	}
	public String getSchoolAduit() {
		return schoolAduit;
	}
	public void setSchoolAduit(String schoolAduit) {
		this.schoolAduit = schoolAduit;
	}
	public String getMajorFieldName() {
		return majorFieldName;
	}
	public void setMajorFieldName(String majorFieldName) {
		this.majorFieldName = majorFieldName;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getPoliticalFeature() {
		return politicalFeature;
	}
	public void setPoliticalFeature(String politicalFeature) {
		this.politicalFeature = politicalFeature;
	}
	public String getHousehold() {
		return household;
	}
	public void setHousehold(String household) {
		this.household = household;
	}
	public String getSchoolLength() {
		return schoolLength;
	}
	public void setSchoolLength(String schoolLength) {
		this.schoolLength = schoolLength;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getEnterCollegeTime() {
		return enterCollegeTime;
	}
	public void setEnterCollegeTime(String enterCollegeTime) {
		this.enterCollegeTime = enterCollegeTime;
	}
	public String getGraduateTime() {
		return graduateTime;
	}
	public void setGraduateTime(String graduateTime) {
		this.graduateTime = graduateTime;
	}
	
	
}
