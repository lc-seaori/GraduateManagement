package com.benson.graduate.data.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;

/**
 * 显示数据管理页面的Action
 * @author benson
 *
 */
@Controller("showDataManagement")
@Scope("prototype")
public class DataManagement_ShowAction extends BaseAction {

	private static final long serialVersionUID = 3989066396759209057L;
	
	/**
	 * 显示数据管理页面
	 * @return
	 */
	public String toDataManagementPage(){
		System.out.println("toDataManagementPage()");
		return "dataManagementPage";
	}
}
