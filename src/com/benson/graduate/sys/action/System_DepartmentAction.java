package com.benson.graduate.sys.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.stu.model.Department;
import com.benson.graduate.stu.service.DepartmentService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 学院Action
 * @author benson
 */
@Controller("departmentAction")
@Scope("prototype")
public class System_DepartmentAction extends BaseAction implements Preparable,ModelDriven<Department>{
	private static final long serialVersionUID = -844240981010582591L;
	
	private DepartmentService departmentService;
	@Resource(name="departmentService")
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	//获取前台easy-ui传过来的参数
	private String rows;//每页显示的记录数    
    private String page;//当前第几页
    private String sort; //根据哪个字段排序
    private String order;	//desc,asc
    private String name;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//接收从departmentList.jsp页面传送过来的学院id
	private Integer depId;
	public Integer getDepId() {
		return depId;
	}
	public void setDepId(Integer depId) {
		this.depId = depId;
	}
	private String idList;
	public String getIdList() {
		return idList;
	}
	public void setIdList(String idList) {
		this.idList = idList;
	}

	private Department department=new Department();
	@Override
	public Department getModel() {
		// TODO Auto-generated method stub
		return department;
	}
	
	/**
	 * 学院列表页面
	 * @return
	 */
	public String toDepListPage(){
		return "depListPage";
	}
	
	/**
	 * 获取学院列表Grid
	 */
	public void getDepList(){
		//第一次datagrid自动向url发一次请求，都是为null,需要初始化
		int pageNow=Integer.parseInt((page==null||page=="0")?"1":page);
		int pageRows=Integer.parseInt((rows==null||rows=="0")?"10":rows);
		super.writeJson(departmentService.getDataGrid(name,sort,order,pageNow,pageRows));
	}
	
	/**
	 * 添加学院页面
	 */
	public String addDepPage(){
		return "addDepPage";
	}
	
	public void prepareEditDepPage(){
		System.out.println("prepareEditDepPage()");
		department=departmentService.findDepartmentById(depId);
	}
	
	/**
	 * 编辑学院页面
	 */
	public String editDepPage(){
		return "editDepPage";
	}
	
	/**
	 * 添加学院实体
	 */
	public void addDepartment(){
		boolean result=departmentService.addDepartment(department);
		Json json =new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 更新学院实体
	 */
	public void editDepartment(){
		boolean result=departmentService.updateDepartment(department);
		Json json =new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 批量删除学院实体
	 */
	public void toDeleteDepartment(){
		boolean result=departmentService.deleteSelectedDeps(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
