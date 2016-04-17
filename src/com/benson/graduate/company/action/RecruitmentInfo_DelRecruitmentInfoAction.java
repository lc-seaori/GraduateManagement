package com.benson.graduate.company.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.company.service.RecruitmentInfoService;

/**
 * 删除招聘信息Action
 * @author benson
 *
 */
@Controller("delRecruitmentInfoAction")
@Scope("prototype")
public class RecruitmentInfo_DelRecruitmentInfoAction extends BaseAction {

	private static final long serialVersionUID = 3761398534019018454L;
	
	private RecruitmentInfoService recruitmentInfoService;
	@Resource(name="recruitmentInfoService")
	public void setRecruitmentInfoService(
			RecruitmentInfoService recruitmentInfoService) {
		this.recruitmentInfoService = recruitmentInfoService;
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
	 * 删除选中的记录
	 */
	public void toDelRecruitmentInfo(){
		boolean result = recruitmentInfoService.deleteSelectedRecruitmentInfo(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
}
