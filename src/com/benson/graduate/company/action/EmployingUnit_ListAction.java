package com.benson.graduate.company.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.company.service.RecruitmentUnitService;

/**
 * 显示用人单位信息列表Action
 * @author benson
 *
 */
@Controller("employingUnitListAction")
@Scope("prototype")
public class EmployingUnit_ListAction extends BaseAction{

	private static final long serialVersionUID = 2876915221803261437L;
	
	private RecruitmentUnitService recruitmentUnitService;
	@Resource(name="recruitmentUnitService")
	public void setRecruitmentUnitService(
			RecruitmentUnitService recruitmentUnitService) {
		this.recruitmentUnitService = recruitmentUnitService;
	}

	//获取前台easy-ui传过来的参数
	private String rows;//每页显示的记录数    
    private String page;//当前第几页
    private String sort; //根据哪个字段排序
    private String order;	//desc,asc
    //查询条件
	private String unitCode; 
	private String unitName;
	private String unitTy;
	private String recruitmentTy;
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
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getUnitTy() {
		return unitTy;
	}
	public void setUnitTy(String unitTy) {
		this.unitTy = unitTy;
	}
	public String getRecruitmentTy() {
		return recruitmentTy;
	}
	public void setRecruitmentTy(String recruitmentTy) {
		this.recruitmentTy = recruitmentTy;
	}

	/**
     * 进入用人单位信息列表页面
     * @return
     */
	public String toEmpUnitListPage(){
		System.out.println("toEmpUnitListPage()");
		return "empUnitListPage";
	}
	
	/**
	 * 获取所有用人单位信息
	 */
	public void getEmpUnitList(){
		//第一次datagrid自动向url发一次请求，都是为null,需要初始化
		int pageNow=Integer.parseInt((page==null||page=="0")?"1":page);
		int pageRows=Integer.parseInt((rows==null||rows=="0")?"10":rows);
		super.writeJson(recruitmentUnitService.getDataGrid(unitCode,unitName,unitTy,recruitmentTy,sort,order,pageNow,pageRows));
	}
	
	
}	
