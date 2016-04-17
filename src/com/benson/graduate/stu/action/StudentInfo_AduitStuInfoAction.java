package com.benson.graduate.stu.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.stu.service.StudentInfoService;

@Controller("studentInfoAduitStuInfoAction")
@Scope("prototype")
public class StudentInfo_AduitStuInfoAction extends BaseAction{

	private static final long serialVersionUID = 8531577396143579731L;
	
	private StudentInfoService studentInfoService;
	@Resource(name="studentInfoService")
	public void setStudentInfoService(StudentInfoService studentInfoService) {
		this.studentInfoService = studentInfoService;
	}

	private String idList;
	public String getIdList() {
		return idList;
	}
	public void setIdList(String idList) {
		this.idList = idList;
	}
	
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


	
	/**
	 * 更改学院审核的申请状态（改为申请中）
	 */
	public void toDoApplyDepartmentAduit(){
		System.out.println("要申请审核的id如下：   "+idList);
		boolean result=studentInfoService.updateDoStuDepartmentAduit(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}

	/**
	 * 更改学院审核的申请状态（改为未申请）
	 */
	public void toDoCacelApplyDepartmentAduit(){
		System.out.println("要取消申请审核的id如下：   "+idList);
		boolean result=studentInfoService.updateCancelStuDepartmentAduit(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 学院进行审核操作（更改学院审核的状态，改为通过）
	 */
	public void toDoDepartmentAduit(){
		System.out.println("审核的id如下：   "+idList);
		boolean result=studentInfoService.updateDepartmentAduit(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 *  学院进行审核操作（更改学院审核的状态，改为不通过）
	 */
	public void toDoCancelDepartmentAduit(){
		System.out.println("审核的id如下：   "+idList);
		boolean result=studentInfoService.updateDepartmentInverseAduit(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 更新学校审核的申请状态（该为审核中）
	 */
	public void toDoApplySchoolAduit(){
		System.out.println("要申请审核的id如下：   "+idList);
		boolean result=studentInfoService.updateDoStuSchoolAduit(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 更改学校审核的申请状态（改为未审核）
	 */
	public void toDoCacelApplySchoolAduit(){
		System.out.println("要取消申请审核的id如下：   "+idList);
		boolean result=studentInfoService.updateCancelStuSchoolAduit(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 学校进行审核操作（更改学校审核的状态，改为通过）
	 */
	public String toDoSchoolAduit(){
		System.out.println("学校审核的id：   "+id);
		//更新源信息
		//studentInfoService.updateSchoolAduit(id);
		
		//重定向studentInfoAddNewInfoAction
		return "success";
	}
	
	/**
	 *  学校进行审核操作（更改学校审核的状态，改为不通过）
	 */
	public void toDoCancelSchoolAduit(){
		System.out.println("审核的id如下：   "+idList);
		boolean result=studentInfoService.updateSchoolInverseAduit(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
}
