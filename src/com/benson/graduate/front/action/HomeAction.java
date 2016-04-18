package com.benson.graduate.front.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.SessionInfo;
import com.benson.graduate.company.service.RecruitmentInfoService;
import com.benson.graduate.company.service.RecruitmentUnitService;
import com.benson.graduate.news.service.NewsPlateService;

@Controller("homeAction")
@Scope("prototype")
public class HomeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private RecruitmentInfoService recruitmentInfoService;
	@Resource(name="recruitmentInfoService")
	public void setRecruitmentInfoService(RecruitmentInfoService recruitmentInfoService) {
		this.recruitmentInfoService = recruitmentInfoService;
	}
	private RecruitmentUnitService recruitmentUnitService;
	@Resource(name="recruitmentUnitService")
	public void setRecruitmentUnitService(
			RecruitmentUnitService recruitmentUnitService) {
		this.recruitmentUnitService = recruitmentUnitService;
	}
	private NewsPlateService newsPlateService;
	@Resource(name="newsPlateService")
	public void setNewsPlateService(NewsPlateService newsPlateService) {
		this.newsPlateService = newsPlateService;
	}

	//前台首页
	public String toHomeIndex(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//进入前台首页
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		if(sessionInfo != null){
			request.setAttribute("sessionInfo", sessionInfo);
		}
		System.out.println("asdasdasd");
		request.setAttribute("newsPlateList", newsPlateService.findAllRootNewsPlate());
		request.setAttribute("infosList", recruitmentInfoService.getAllRecruitmentInfos(0, 4));
		request.setAttribute("unitsList", recruitmentUnitService.findAllRecruitmentUnits());
		return "homeIndex";
	}
	
	//登录界面
	public String doNotNeedSession_toLogin(){
		return "login";
	}
	
	public String no_nnIndex(){
		
		return "nnIndex";
	}
	
	//后台首页
	public String toBackstage(){
		return "backstage";
	}
	
}
