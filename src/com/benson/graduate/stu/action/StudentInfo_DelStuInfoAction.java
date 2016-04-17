package com.benson.graduate.stu.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.stu.service.StudentInfoService;

@Controller("studentInfoDelStuInfoAction")
@Scope("prototype")
public class StudentInfo_DelStuInfoAction extends BaseAction{

	private static final long serialVersionUID = 8531577396143579731L;
	
	private String idList;
	public String getIdList() {
		return idList;
	}
	public void setIdList(String idList) {
		this.idList = idList;
	}
	
	private StudentInfoService studentInfoService;
	@Resource(name="studentInfoService")
	public void setStudentInfoService(StudentInfoService studentInfoService) {
		this.studentInfoService = studentInfoService;
	}
	/**
	 * 删除所有选中学生源信息信息
	 */
	public void toDelStuInfo(){
		boolean result=studentInfoService.deleteSelectedStudentInfo(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
}
