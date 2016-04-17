package com.benson.graduate.stu.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.stu.service.GraduateInfoService;

@Controller("aduitGraduateInfoAction")
@Scope("prototype")
public class GraduateInfo_AduitGraduateInfoAction extends BaseAction {

	private static final long serialVersionUID = 5091845992523918642L;
	
	private GraduateInfoService graduateInfoService;
	@Resource(name="graduateInfoService")
	public void setGraduateInfoService(GraduateInfoService graduateInfoService) {
		this.graduateInfoService = graduateInfoService;
	}

	//接收从graduateInfoList.jsp传来的参数
	private String idList;
	public String getIdList() {
		return idList;
	}
	public void setIdList(String idList) {
		this.idList = idList;
	}
	
	/**
	 * 更改学院审核的申请状态（改为审核中）
	 */
	public void toDoApplyDepartmentGAduit(){
		System.out.println("要申请审核的id如下：   "+idList);
		boolean result=graduateInfoService.updateGraDepartmentAduit(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 更改学院审核的申请状态（改为未申请）
	 */
	public void toDoCancelApplyDepartmentGAduit(){
		System.out.println("要取消申请审核的id如下：   "+idList);
		boolean result=graduateInfoService.updateCancelGraDepartmentAduit(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 学院进行审核操作（更改学院审核的状态，改为通过）
	 */
	public void toDoDepartmentGAduit(){
		System.out.println("审核的id如下：   "+idList);
		boolean result=graduateInfoService.updateDepartmentGAduit(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 *  学院进行审核操作（更改学院审核的状态，改为不通过）
	 */
	public void toDoCancelDepartmentGAduit(){
		System.out.println("审核的id如下：   "+idList);
		boolean result=graduateInfoService.updateDepartmentInverseGAduit(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 更新学校审核的申请状态（该为审核中）
	 */
	public void toDoApplySchoolGAduit(){
		System.out.println("要申请审核的id如下：   "+idList);
		boolean result=graduateInfoService.updateDoGraSchoolGAduit(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 更改学校审核的申请状态（改为未审核）
	 */
	public void toDoCancelApplySchoolGAduit(){
		System.out.println("要取消申请审核的id如下：   "+idList);
		boolean result=graduateInfoService.updateCancelGraSchoolGAduit(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 学校进行审核操作（更改学校审核的状态，改为通过）
	 */
	public void toDoSchoolGAduit(){
		boolean result=graduateInfoService.updateSchoolGAduit(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 *  学校进行审核操作（更改学校审核的状态，改为不通过）
	 */
	public void toDoCancelSchoolGAduit(){
		System.out.println("审核的id如下：   "+idList);
		boolean result=graduateInfoService.updateSchoolInverseGAduit(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
}
