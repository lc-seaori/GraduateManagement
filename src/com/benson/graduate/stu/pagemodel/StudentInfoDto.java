package com.benson.graduate.stu.pagemodel;

import com.benson.graduate.stu.model.StudentInfo;

/**
 * 在编辑学生源信息时需要使用这个类来传递数据
 * @author benson
 *
 */
public class StudentInfoDto extends StudentInfo {
	private String birthdayTrans;
	private String enterCollegeTimeTrans;
	private String graduateTimeTrans;
	public String getBirthdayTrans() {
		return birthdayTrans;
	}
	public void setBirthdayTrans(String birthdayTrans) {
		this.birthdayTrans = birthdayTrans;
	}
	public String getEnterCollegeTimeTrans() {
		return enterCollegeTimeTrans;
	}
	public void setEnterCollegeTimeTrans(String enterCollegeTimeTrans) {
		this.enterCollegeTimeTrans = enterCollegeTimeTrans;
	}
	public String getGraduateTimeTrans() {
		return graduateTimeTrans;
	}
	public void setGraduateTimeTrans(String graduateTimeTrans) {
		this.graduateTimeTrans = graduateTimeTrans;
	}
	
	
	
	
}
