package com.benson.graduate.company.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.common.enumeration.EnumerationType;
import com.benson.graduate.company.model.RecruitmentInfo;
import com.benson.graduate.company.model.RecruitmentUnit;
import com.benson.graduate.company.service.RecruitmentInfoService;
import com.benson.graduate.company.service.RecruitmentUnitService;
import com.benson.graduate.sys.model.EnumerationValue;
import com.benson.graduate.sys.service.EnumerationValueService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 添加招聘信息Action
 * @author benson
 *
 */
@Controller("addRecruitmentInfoAction")
@Scope("prototype")
public class RecruitmentInfo_AddRecruitmentInfoAction extends BaseAction implements ModelDriven<RecruitmentInfo>,Preparable{

	private static final long serialVersionUID = 5157317235195346581L;
	
	private RecruitmentInfoService recruitmentInfoService;
	@Resource(name="recruitmentInfoService")
	public void setRecruitmentInfoService(
			RecruitmentInfoService recruitmentInfoService) {
		this.recruitmentInfoService = recruitmentInfoService;
	}
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
	
	//接收从addRecruitmentInfo.jsp传来过的公司id
	private Integer unitId;
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	
	//ModelDriven拦截器
	private RecruitmentInfo model=new RecruitmentInfo();
	
	/**
	 * 进入添加招聘信息之前需要准备数据
	 */
	public void prepareToAddRecruitmentInfoPage(){
		//工资
		List<EnumerationValue> monthlySalarys=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.MONTHLY_SALARY);
		request.setAttribute("monthlySalarys", monthlySalarys);
		//工作性质
		List<EnumerationValue> workTypes=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.WORK_TYPE);
		request.setAttribute("workTypes", workTypes);
		//行业领域
		List<EnumerationValue> industryTypes=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.INDUSTRY_TYPE);
		request.setAttribute("industryTypes", industryTypes);
		//学历要求
		List<EnumerationValue> educationTypes=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.EDUCATION_TYPE);
		request.setAttribute("educationTypes", educationTypes);
		//公司
		List<RecruitmentUnit> recruitmentUnits=recruitmentUnitService.findAllRecruitmentUnits();
		request.setAttribute("recruitmentUnits", recruitmentUnits);
	}
	
	/**
	 * 进入添加招聘信息页面
	 * @return
	 */
	public String toAddRecruitmentInfoPage(){
		return "addRecruitmentInfoPage";
	}

	/**
	 * 添加招聘信息
	 */
	public void addRecruitmentInfo(){
		Json json=new Json();
		System.out.println("公司id为：  "+unitId);
		//根据招聘公司id查找公司
		model.setRecruitmentUnit(recruitmentUnitService.findRecruitmentUnitById(unitId));
		//添加实体
		boolean result=recruitmentInfoService.addRecruitmentInfo(model);
		json.setSuccess(result);
		if(result==false){
			json.setMsg("添加失败");
		}
		super.writeJson(json);
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public RecruitmentInfo getModel() {
		// TODO Auto-generated method stub
		return model;
	}

}
