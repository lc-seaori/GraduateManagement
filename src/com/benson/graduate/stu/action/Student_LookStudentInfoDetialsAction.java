package com.benson.graduate.stu.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.stu.model.StudentInfo;
import com.benson.graduate.stu.pagemodel.PageStudentInfo;
import com.benson.graduate.stu.service.StudentService;
import com.benson.graduate.sys.model.EnumerationValue;
import com.benson.graduate.sys.service.EnumerationValueService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

@Controller("studentInfoDetialsAction")
@Scope("prototype")
public class Student_LookStudentInfoDetialsAction extends BaseAction implements ModelDriven<PageStudentInfo>,Preparable{

	private static final long serialVersionUID = 503118744450272589L;
	
	private StudentService studentService;
	@Resource(name="studentService")
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	private EnumerationValueService enumerationValueService;
	@Resource(name="enumerationValueService")
	public void setEnumerationValueService(
			EnumerationValueService enumerationValueService) {
		this.enumerationValueService = enumerationValueService;
	}
	
	//接收studentList.jsp传来的参数
	private Integer studentId;
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	//struts2 model拦截器
	private PageStudentInfo model=new PageStudentInfo();

	@Override
	public PageStudentInfo getModel() {
		// TODO Auto-generated method stub
		System.out.println("getModel()");
		return model;
	}
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
	}
	
	/**
	 * 为学生列表准备数据
	 */
	public void prepareToStudentInfoDetials(){
		System.out.println("prepareToStudentInfoDetials()");
		StudentInfo studentInfo=studentService.findStudentById(studentId).getStudentInfo();
		if(studentInfo!=null){
			System.out.println(studentInfo.getExamNum()+"  "+studentInfo.getName());
		}
		BeanUtils.copyProperties(studentInfo, model);
		//转换性别
		EnumerationValue value=enumerationValueService.findEnumerationValueById(studentInfo.getSexType());
		model.setSex(value.getName());
		//专业名
		model.setMajorFieldName(studentInfo.getMajorField().getName());
		//民族
		value=enumerationValueService.findEnumerationValueById(studentInfo.getNationType());
		model.setNation(value.getName());
		//政治面貌
		value=enumerationValueService.findEnumerationValueById(studentInfo.getPoliticalFeatureType());
		model.setPoliticalFeature(value.getName());
		//户籍
		value=enumerationValueService.findEnumerationValueById(studentInfo.getHouseholdType());
		model.setHousehold(value.getName());
		//学制
		value=enumerationValueService.findEnumerationValueById(studentInfo.getSchoolLengthType());
		model.setSchoolLength(value.getName());
		//学历
		value=enumerationValueService.findEnumerationValueById(studentInfo.getEducationType());
		model.setEducation(value.getName());
		//生日、入学时间、毕业时间
		Date date1=studentInfo.getBirthday();
		Date date2=studentInfo.getEnterCollegeTime();
		Date date3=studentInfo.getGraduateTime();
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try {
			model.setBirthday(dateFormat.format(date1));
			model.setEnterCollegeTime(dateFormat.format(date2));
			model.setGraduateTime(dateFormat.format(date3));
			//System.out.println(dateShow);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 查看学生源信息
	 */
	public String toStudentInfoDetials(){
		System.out.println("toStudentInfoDetials()");
		return "studentInfoDetialsPage";
	}
	
}
