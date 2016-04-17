package com.benson.graduate.stu.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.stu.service.GraduateInfoService;

//这里注意，单例问题，导致模糊查询的时候：查询完一次，下次点击列表的时候，上次一次的查询条件依然保存(因为不是单例)
@Controller("graduateInfoListAction")
@Scope("prototype")
public class GraduateInfo_ListAction extends BaseAction {

	private static final long serialVersionUID = -8695140975776473341L;
	
	private GraduateInfoService graduateInfoService;
	@Resource(name="graduateInfoService")
	public void setGraduateInfoService(GraduateInfoService graduateInfoService) {
		this.graduateInfoService = graduateInfoService;
	}
	
	//获取前台easy-ui传过来的参数
	private String rows;//每页显示的记录数    
    private String page;//当前第几页
    private String sort; //根据哪个字段排序
    private String order;	//desc,asc
    private String code;	//学号
    private String name;	//名字
    private String idCard;  //身份证
    private String departmentName;	//学院
    private String graduateTime; 	//毕业时间
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getGraduateTime() {
		return graduateTime;
	}
	public void setGraduateTime(String graduateTime) {
		this.graduateTime = graduateTime;
	}
	
	/**
	 * 进入就业信息列表页面
	 * @return
	 */
	public String toGraduateInfoListPage(){
		return "graduateInfoListPage";
	}
	
	/**
	 * 获得所有学生的就业信息
	 */
	public void getGraduateInfoList(){
		System.out.println("记录数： "+rows +"  第几页：  "+page+"  排序字段 ："+sort +"  排序方式：  "+order);
		System.out.println(code+"   "+name+"   "+idCard+"   "+departmentName+"   "+graduateTime);
		//第一次datagrid自动向url发一次请求，都是为null,需要初始化
		int pageNow=Integer.parseInt((page==null||page=="0")?"1":page);
		int pageRows=Integer.parseInt((rows==null||rows=="0")?"10":rows);
		super.writeJson(graduateInfoService.getDataGrid(code,name,idCard,departmentName,graduateTime,sort,order,pageNow,pageRows));
	}
	
	/**
	 * 进入学院审核学生就业信息页面
	 */
	public String toDepartmentAduitPage(){
		System.out.println("toDepartmentAduitPage()");
		return "departmentAduitListPage";
	}
	
	/**
	 * 获取需要院系审核的所有学生就业信息
	 */
	public void getDepartmentAduitList(){
//		System.out.println("   Test   Page  : "+page+ "   "+rows);
//		System.out.println("   Test   Query : "+examNum+"   "+name+"   "+idCard+"   "+departmentName+"  "+graduateTime);
		int pageNow=Integer.parseInt((page==null||page=="0")?"1":page);
		int pageRows=Integer.parseInt((rows==null||rows=="0")?"10":rows);
		super.writeJson(graduateInfoService.getDepartmentAduitDataGrid(code,name,idCard,departmentName,graduateTime,sort,order,pageNow,pageRows));
	}
	
	/**
	 * 进入学校审核学生就业信息页面
	 */
	public String toSchoolAduitPage(){
		System.out.println("toSchoolAduitPage()");
		return "schoolAduitListPage";
	}
	
	/**
	 * 获取所有学生的学校审核源信息
	 */
	public void getSchoolAduitList(){
//		System.out.println("   Test   Page  : "+page+ "   "+rows);
//		System.out.println("   Test   Query : "+examNum+"   "+name+"   "+idCard+"   "+departmentName+"  "+graduateTime);
		int pageNow=Integer.parseInt((page==null||page=="0")?"1":page);
		int pageRows=Integer.parseInt((rows==null||rows=="0")?"10":rows);
		super.writeJson(graduateInfoService.getSchoolAduitDataGrid(code,name,idCard,departmentName,graduateTime,sort,order,pageNow,pageRows));
	}
}
