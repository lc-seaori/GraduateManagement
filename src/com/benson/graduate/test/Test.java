package com.benson.graduate.test;

import java.util.List;

import junit.framework.TestCase;

import com.benson.graduate.stu.model.StudentInfo;
import com.benson.graduate.stu.service.StudentInfoService;
import com.benson.graduate.stu.service.impl.StudentInfoServiceImpl;


public class Test extends TestCase{
	
	@org.junit.Test
	public void testadd(){
		System.out.println("来");
		StudentInfoService studentInfoService=new StudentInfoServiceImpl();
		List<StudentInfo> studentInfos=studentInfoService.getAllStudentInfos();
		for(StudentInfo stu:studentInfos){
			System.out.println(stu.getName()+" "+stu.getExamNum()+" "+stu.getGraduationSchool());
		}
	}
}
