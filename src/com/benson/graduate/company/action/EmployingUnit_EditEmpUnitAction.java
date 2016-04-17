package com.benson.graduate.company.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.common.enumeration.EnumerationType;
import com.benson.graduate.company.model.RecruitmentUnit;
import com.benson.graduate.company.service.RecruitmentUnitService;
import com.benson.graduate.sys.model.EnumerationValue;
import com.benson.graduate.sys.service.EnumerationValueService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;


@Controller("editEmpUnitAction")
@Scope("prototype")
public class EmployingUnit_EditEmpUnitAction extends BaseAction implements Preparable,ModelDriven<RecruitmentUnit>{

	private static final long serialVersionUID = -8530431615998829703L;
	
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
	
	//接收从empUnitList.jsp传过来的参数
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	//从editEmpUnit.jsp页面接收的参数
	private String valiUnitCode;
	public String getValiUnitCode() {
		return valiUnitCode;
	}
	public void setValiUnitCode(String valiUnitCode) {
		this.valiUnitCode = valiUnitCode;
	}
	
	//ajax验证字段需要字段
	private InputStream inputStream;
	public InputStream getInputStream(){
		return inputStream;
	}
	
	private RecruitmentUnit model=new RecruitmentUnit();
	
	public void prepareToEditEmpUnitPage(){
		//招聘方式方式与公司性质
		List<EnumerationValue> unitTypes=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.UNIT_TYPE);
		request.setAttribute("unitTypes", unitTypes);
		List<EnumerationValue> recruitmentTypes=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.APPLY_RECRUITMENT_TYPE);
		request.setAttribute("recruitmentTypes", recruitmentTypes);
		//根据id获取招聘单位信息
		model=recruitmentUnitService.findRecruitmentUnitById(id);
	}
	
	public String toEditEmpUnitPage(){
		return "editEmpUnitPage";
	}
	
	/**
	 * 检验单位编号是否存在
	 * @throws UnsupportedEncodingException 
	 */
	public String validateUnitCode() throws UnsupportedEncodingException{
		System.out.println("vali         = "+valiUnitCode);
		if(!recruitmentUnitService.findRecruitmentUnitByUnitCoide(valiUnitCode)){
			inputStream=new ByteArrayInputStream("1".getBytes("UTF-8"));
		}else{
			inputStream=new ByteArrayInputStream("0".getBytes("UTF-8"));
		}
		return "ajaxSuccess";
	}
	
	/**
	 * 添加实体
	 */
	public void editEmpUnit(){
		
		Json json=new Json();
		//先判断单位编号是否存在
		if(recruitmentUnitService.findRecruitmentUnitByUnitCoide(model.getUnitCode())){
			json.setSuccess(false);
			json.setMsg("单位编号已存在，请检查后重新输入");
			super.writeJson(json);
		}
		//更新实体
		boolean result=recruitmentUnitService.updateRecruitmentUnit(model);
		json.setSuccess(result);
		if(result==false){
			json.setMsg("更新失败");
		}
		super.writeJson(json);
	}

	@Override
	public RecruitmentUnit getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
