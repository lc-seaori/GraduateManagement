package com.benson.graduate.stu.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.stu.service.GraduateInfoService;

@Controller("delGraduateInfoAction")
@Scope("prototype")
public class GraduateInfo_DelGraduateInfoAction extends BaseAction{

	private static final long serialVersionUID = 8133955009809743357L;
	
	private GraduateInfoService graduateInfoService;
	@Resource(name="graduateInfoService")
	public void setGraduateInfoService(GraduateInfoService graduateInfoService) {
		this.graduateInfoService = graduateInfoService;
	}

	//接收从graduateInfoList.jsp传过来的参数
	private String idList;
	public String getIdList() {
		return idList;
	}
	public void setIdList(String idList) {
		this.idList = idList;
	}

	/**
	 * 删除学生就业信息
	 */
	public void toDelGraduateInfo(){
		System.out.println("开始删除");
		boolean result=graduateInfoService.deleteSelectedGraduateInfo(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
}
