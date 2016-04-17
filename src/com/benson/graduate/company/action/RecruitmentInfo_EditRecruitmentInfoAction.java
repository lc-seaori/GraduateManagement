package com.benson.graduate.company.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.common.enumeration.EnumerationType;
import com.benson.graduate.company.model.RecruitmentInfo;
import com.benson.graduate.company.service.RecruitmentInfoService;
import com.benson.graduate.company.service.RecruitmentUnitService;
import com.benson.graduate.sys.model.EnumerationValue;
import com.benson.graduate.sys.service.EnumerationValueService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 编辑招聘信息Action
 * @author benson
 *
 */
@Controller("editRecruitmentInfoAction")
@Scope("prototype")
public class RecruitmentInfo_EditRecruitmentInfoAction extends BaseAction implements Preparable,ModelDriven<RecruitmentInfo>{

	private static final long serialVersionUID = 2779019119153330698L;
	private RecruitmentInfoService recruitmentInfoService;
	@Resource(name="recruitmentInfoService")
	public void setRecruitmentInfoService(
			RecruitmentInfoService recruitmentInfoService) {
		this.recruitmentInfoService = recruitmentInfoService;
	}
	private EnumerationValueService enumerationValueService;
	@Resource(name="enumerationValueService")
	public void setEnumerationValueService(
			EnumerationValueService enumerationValueService) {
		this.enumerationValueService = enumerationValueService;
	}
	private RecruitmentUnitService recruitmentUnitService;
	@Resource(name="recruitmentUnitService")
	public void setRecruitmentUnitService(
			RecruitmentUnitService recruitmentUnitService) {
		this.recruitmentUnitService = recruitmentUnitService;
	}
	//接收从recruitmentInfoList.jsp页面传来的编辑id
	private Integer recrId;
	public Integer getRecrId() {
		return recrId;
	}
	public void setRecrId(Integer recrId) {
		this.recrId = recrId;
	}
	
	//接收从editRecruitmentInfo.jsp页面传来的
	private String releaseTimes;
	private String endTimes;
	private Integer unitId;
	public String getReleaseTimes() {
		return releaseTimes;
	}
	public void setReleaseTimes(String releaseTimes) {
		this.releaseTimes = releaseTimes;
	}
	public String getEndTimes() {
		return endTimes;
	}
	public void setEndTimes(String endTimes) {
		this.endTimes = endTimes;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	
	//ModelDriven
	private RecruitmentInfo model=new RecruitmentInfo();

	/**
	 * 进入编辑页面之前准备数据
	 */
	public void prepareToEditRecruitmentInfoPage(){
		//根据id查询响应的招聘信息
		model=recruitmentInfoService.findRecruitmentInfoById(recrId);
		if(model!=null){
			request.setAttribute("unitName", model.getRecruitmentUnit().getUnitName());
		}
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
		//转换发布时间和截止时间
		Date date1=model.getReleaseTime();
		Date date2=model.getEndTime();
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String startTime="";
		String endTime="";
		try {
			startTime=dateFormat.format(date1);
			endTime=dateFormat.format(date2);
			//System.out.println(dateShow);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		System.out.println("id+   :  "+model.getRecruitmentUnit().getId());
		request.setAttribute("unitId", model.getRecruitmentUnit().getId());
	}
	
	/**
	 * 进入编辑页面
	 * @return
	 */
	public String toEditRecruitmentInfoPage(){
		return "editRecruitmentInfoPage";
	}
	
	/**
	 * 更新
	 */
	public void editRecruitmentInfo(){
		System.out.println("editRecruitmentInfo()");
		System.out.println("测试：   "+releaseTimes+"   "+endTimes);
		
		//设置缺的属性
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date date1=null;
		Date date2=null;
		try {
			date1=dateFormat.parse(releaseTimes);
			date2=dateFormat.parse(endTimes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setReleaseTime(date1);
		model.setEndTime(date2);
		//根据UnitId找单位信息
		model.setRecruitmentUnit(recruitmentUnitService.findRecruitmentUnitById(unitId));
		//更新
		boolean result=recruitmentInfoService.updateRecruitmentInfo(model);
		
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	@Override
	public RecruitmentInfo getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
