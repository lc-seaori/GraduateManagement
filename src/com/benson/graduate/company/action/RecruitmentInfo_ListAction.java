package com.benson.graduate.company.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.company.service.RecruitmentInfoService;

/**
 * 显示招聘单位信息的Action
 * @author benson
 *
 */
@Controller("recruitmentInfoListAction")
@Scope("prototype")
public class RecruitmentInfo_ListAction extends BaseAction {

	private static final long serialVersionUID = 2456536525460163267L;
	
	private RecruitmentInfoService recruitmentInfoService;
	@Resource(name="recruitmentInfoService")
	public void setRecruitmentInfoService(
			RecruitmentInfoService recruitmentInfoService) {
		this.recruitmentInfoService = recruitmentInfoService;
	}

	//接收从recruitmentInfoList.jsp传过来的参数
	//获取前台easy-ui传过来的参数
	private String rows;//每页显示的记录数    
    private String page;//当前第几页
    private String sort; //根据哪个字段排序
    private String order;	//desc,asc
    //查询条件
    private String position;
    private String unitName;
    private String workTy;
    private String industryTy;
    private String educationTy;
	
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
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getWorkTy() {
		return workTy;
	}
	public void setWorkTy(String workTy) {
		this.workTy = workTy;
	}
	public String getIndustryTy() {
		return industryTy;
	}
	public void setIndustryTy(String industryTy) {
		this.industryTy = industryTy;
	}
	public String getEducationTy() {
		return educationTy;
	}
	public void setEducationTy(String educationTy) {
		this.educationTy = educationTy;
	}


	/**
	 * 进入招聘信息列表页面
	 * @return
	 */
	public String toRecruitmentInfoListPage(){
		return "recruitmentInfoListPage";
	}
	
	/**
	 * 得到招聘信息列表
	 */
	public void getRecruitmentInfoList(){
		//第一次datagrid自动向url发一次请求，都是为null,需要初始化
		int pageNow=Integer.parseInt((page==null||page=="0")?"1":page);
		int pageRows=Integer.parseInt((rows==null||rows=="0")?"10":rows);
		super.writeJson(recruitmentInfoService.getDataGrid(position,unitName,workTy,industryTy,educationTy,sort,order,pageNow,pageRows));
	}
}
