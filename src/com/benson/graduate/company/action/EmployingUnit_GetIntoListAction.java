package com.benson.graduate.company.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.company.model.RecruitmentInfo;
import com.benson.graduate.company.model.RecruitmentUnit;
import com.benson.graduate.company.pagemodel.PageRecruitmentInfo;
import com.benson.graduate.company.service.RecruitmentInfoService;
import com.benson.graduate.company.service.RecruitmentUnitService;
import com.benson.graduate.sys.service.EnumerationValueService;
import com.opensymphony.xwork2.Preparable;

/**
 * 通过招聘单位来得到其所有的招聘信息
 * @author benson
 *
 */
@Controller("getRecruitmentInfoListAction")
@Scope("prototype")
public class EmployingUnit_GetIntoListAction extends BaseAction implements Preparable{

	private static final long serialVersionUID = -7953287421959314860L;
	private RecruitmentUnitService recruitmentUnitService;
	@Resource(name="recruitmentUnitService")
	public void setRecruitmentUnitService(
			RecruitmentUnitService recruitmentUnitService) {
		this.recruitmentUnitService = recruitmentUnitService;
	}
	private EnumerationValueService enumerationValueService;
	@Resource(name="enumerationValueService")
	public void setEnumerationValueService(
			EnumerationValueService enumerationValueService) {
		this.enumerationValueService = enumerationValueService;
	}
	private RecruitmentInfoService recruitmentInfoService;
	@Resource(name="recruitmentInfoService")
	public void setRecruitmentInfoService(
			RecruitmentInfoService recruitmentInfoService) {
		this.recruitmentInfoService = recruitmentInfoService;
	}
	
	//接收从empUnitList.jsp传来的id
 	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 进入招聘信息页面之前进行数据准备
	 */
	public void prepareToRecruitmentInfosPage(){
		//根据id查找公司的基本信息
		RecruitmentUnit unit=recruitmentUnitService.findRecruitmentUnitById(id);
		if(unit!=null){
			System.out.println(unit.getUnitName()+"   "+unit.getUnitDescription());
		}
		request.setAttribute("unit", unit);
		request.setAttribute("recruitmentTypes", enumerationValueService.findEnumerationValueById(unit.getRecruitmentType()).getName());
		request.setAttribute("unitTypes", enumerationValueService.findEnumerationValueById(unit.getUnitType()).getName());
		//根据招聘单位id查找其所有的招聘信息
		List<RecruitmentInfo> infos=recruitmentInfoService.getAllRecruitmentInfosByUnitId(id);

		//转换模型
		List<PageRecruitmentInfo> pageInfos=recruitmentInfoService.changeToPageModel(infos);
		for(PageRecruitmentInfo page:pageInfos){
			System.out.println(page.getMonthlySalary()+"  "+page.getReleaseTime()+"  "+page.getEndTime()+"  "+page.getIndustryType());
		}
		
		request.setAttribute("pageInfos", pageInfos);
	}


	/**
	 * 进入招聘信息页面
	 */
	public String toRecruitmentInfosPage(){
		
		return "recruitmentInfosPage";
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
