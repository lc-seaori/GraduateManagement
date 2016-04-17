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
	
	//前台首页
	public String toHomeIndex(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//进入前台首页
//		SessionInfo sessionInfo = new SessionInfo();
//		sessionInfo.setId(1);
//		sessionInfo.setLoginname("admin");
//		sessionInfo.setName("Benson");
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		if(sessionInfo != null){
			//request.getSession().setAttribute("sessionInfo", sessionInfo);
			request.setAttribute("sessionInfo", sessionInfo);
		}
		//System.out.println("size: "+recruitmentInfoService.getAllRecruitmentInfos(0, 4).size()+","+recruitmentUnitService.findAllRecruitmentUnits().size());
		request.setAttribute("infosList", recruitmentInfoService.getAllRecruitmentInfos(0, 4));
		request.setAttribute("unitsList", recruitmentUnitService.findAllRecruitmentUnits());
		return "homeIndex";
	}
	
	//登录界面
	public String doNotNeedSession_toLogin(){
		System.out.println("登陆");
		return "login";
	}
	
	//后台首页
	public String toBackstage(){
		return "backstage";
	}
	
}
