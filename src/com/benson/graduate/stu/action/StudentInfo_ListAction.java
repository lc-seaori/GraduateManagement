package com.benson.graduate.stu.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.stu.service.StudentInfoService;

/**
 * 生源信息列表Action
 * @author benson
 *
 */
@Controller("studentInfoListAction")
@Scope("prototype")
public class StudentInfo_ListAction extends BaseAction {

	private static final long serialVersionUID = -8866705888720370284L;
	
	//需要使用到的业务逻辑组件
	private StudentInfoService studentInfoService;
	@Resource(name="studentInfoService")
	public void setStudentInfoService(StudentInfoService studentInfoService) {
		this.studentInfoService = studentInfoService;
	}
	
	
	//获取前台easy-ui传过来的参数
	private String rows;//每页显示的记录数    
    private String page;//当前第几页
    private String sort; //根据哪个字段排序
    private String order;	//desc,asc
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

	//获取easy-ui点击查询时候传过来的参数
	private String examNum;
	private String name;
	private String idCard;
	private String departmentName;
	private String graduateTime;
	public void setExamNum(String examNum) {
		this.examNum = examNum;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public void setGraduateTime(String graduateTime) {
		this.graduateTime = graduateTime;
	}
	
	/**
	 * 进入所有学生的生源信息页面
	 */
	public String toStudentInfoListPage(){
		System.out.println("toStudentListPage()");
		return "listPage";
	}
	
	/**
	 * 获取所有学生的生源信息
	 */
	public void getStudentList(){
//		System.out.println("   Test   Page  : "+page+ "   "+rows);
//		System.out.println("   Test   Query : "+examNum+"   "+name+"   "+idCard);
		int pageNow=Integer.parseInt((page==null||page=="0")?"1":page);
		int pageRows=Integer.parseInt((rows==null||rows=="0")?"10":rows);
		super.writeJson(studentInfoService.getDataGrid(examNum,name,idCard,graduateTime,sort,order,pageNow,pageRows));
	}
	
	/**
	 * 进入学院审核学生信息页面
	 */
	public String toDepartmentAduitPage(){
		System.out.println("toDepartmentAduitPage()");
		return "departmentAduitListPage";
	}
	
	/**
	 * 获取所有学生的院系审核源信息
	 */
	public void getDepartmentAduitList(){
//		System.out.println("   Test   Page  : "+page+ "   "+rows);
//		System.out.println("   Test   Query : "+examNum+"   "+name+"   "+idCard+"   "+departmentName+"  "+graduateTime);
		int pageNow=Integer.parseInt((page==null||page=="0")?"1":page);
		int pageRows=Integer.parseInt((rows==null||rows=="0")?"10":rows);
		super.writeJson(studentInfoService.getDepartmentAduitDataGrid(examNum,name,idCard,departmentName,graduateTime,sort,order,pageNow,pageRows));
	}
	
	/**
	 * 进入学校审核学生信息页面
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
		super.writeJson(studentInfoService.getSchoolAduitDataGrid(examNum,name,idCard,departmentName,graduateTime,sort,order,pageNow,pageRows));
	}
	
	
	
}
