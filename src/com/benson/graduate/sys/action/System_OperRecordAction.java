package com.benson.graduate.sys.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.sys.service.OperationRecordService;

/**
 * 用户操作Action
 * @author benson
 */
@Controller("operRecordAction")
@Scope("prototype")
public class System_OperRecordAction extends BaseAction {
	
	private static final long serialVersionUID = 3208988549779287735L;
	
	private OperationRecordService operationRecordService;
	@Resource(name="operationRecordService")
	public void setOperationRecordService(
			OperationRecordService operationRecordService) {
		this.operationRecordService = operationRecordService;
	}

	//获取前台easy-ui传过来的参数
	private String rows;//每页显示的记录数    
    private String page;//当前第几页
    private String sort; //根据哪个字段排序
    private String order;	//desc,asc
    
    private String operationPerson;
    private String operationName;
    private String time;
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getOperationPerson() {
		return operationPerson;
	}
	public void setOperationPerson(String operationPerson) {
		this.operationPerson = operationPerson;
	}
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	//接收从operRecord.jsp页面传送过来的参数
	private String idList;
	public String getIdList() {
		return idList;
	}
	public void setIdList(String idList) {
		this.idList = idList;
	}
	
	/**
	 * 进入用户操作页面
	 * @return
	 */
	public String toOperRecordListPage(){
		return "operRecordListPage";
	}
	
	/**
	 * 获取操作记录列表
	 */
	public void getOperRecordList(){
		//第一次datagrid自动向url发一次请求，都是为null,需要初始化
		int pageNow=Integer.parseInt((page==null||page=="0")?"1":page);
		int pageRows=Integer.parseInt((rows==null||rows=="0")?"10":rows);
		//int operTime=Integer.parseInt((time==null||time=="0")?"0":time);
		super.writeJson(operationRecordService.getDataGrid(operationPerson,operationName,time,sort,order,pageNow,pageRows));
	}
	
	/**
	 * 删除操作记录
	 */
	public void deleteRecord(){
		boolean result=operationRecordService.deleteSelectedRecord(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}

}
