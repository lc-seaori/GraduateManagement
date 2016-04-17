package com.benson.graduate.company.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.company.service.RecruitmentUnitService;

@Controller("delEmpUnitAction")
@Scope("prototype")
public class EmployingUnit_DelEmpUnitAction extends BaseAction {

	private static final long serialVersionUID = -3291396579826086734L;

	private RecruitmentUnitService recruitmentUnitService;
	@Resource(name="recruitmentUnitService")
	public void setRecruitmentUnitService(
			RecruitmentUnitService recruitmentUnitService) {
		this.recruitmentUnitService = recruitmentUnitService;
	}
	
	//接收从empUnitList.jsp传来的id字符串
	private String idList;
	public String getIdList() {
		return idList;
	}
	public void setIdList(String idList) {
		this.idList = idList;
	}
	
	/**
	 * 删除招聘单位信息
	 */
	public void toDelEmpUnit(){
		boolean result=recruitmentUnitService.deleteSelectedRecruitmentUnit(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
}
