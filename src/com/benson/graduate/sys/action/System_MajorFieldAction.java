package com.benson.graduate.sys.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.stu.model.MajorField;
import com.benson.graduate.stu.service.DepartmentService;
import com.benson.graduate.stu.service.MajorFieldService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 专业设置Action
 * @author benson
 */
@Controller("majorAction")
@Scope("prototype")
public class System_MajorFieldAction extends BaseAction implements Preparable,ModelDriven<MajorField>{

	private static final long serialVersionUID = -860026095581814802L;
	
	private DepartmentService departmentService;
	@Resource(name="departmentService")
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	private MajorFieldService majorFieldService;
	@Resource(name="majorFieldService")
	public void setMajorFieldService(MajorFieldService majorFieldService) {
		this.majorFieldService = majorFieldService;
	}
	
	//获取前台easy-ui传过来的参数
	private String rows;//每页显示的记录数    
    private String page;//当前第几页
    private String sort; //根据哪个字段排序
    private String order;	//desc,asc
    private String name;
    private String departmentSelected;
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
	public String getDepartmentSelected() {
		return departmentSelected;
	}
	public void setDepartmentSelected(String departmentSelected) {
		this.departmentSelected = departmentSelected;
	}
	
	//接收从majorList.jsp页面传来的参数
	private Integer majorId;
	public Integer getMajorId() {
		return majorId;
	}
	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}
	private String idList;
	public String getIdList() {
		return idList;
	}
	public void setIdList(String idList) {
		this.idList = idList;
	}
	
	private MajorField majorField=new MajorField();
	@Override
	public MajorField getModel() {
		// TODO Auto-generated method stub
		return majorField;
	}

	/**
	 * 专业列表页面
	 * @return
	 */
	public String toMajorListPage(){
		//查找所有学院
		request.setAttribute("departments", departmentService.findAllDepartments());
		return "majorListPage";
	}
	
	/**
	 * 获取专业方向列表Grid
	 */
	public void getMajorList(){
		System.out.println("测试：  "+name+"   "+departmentSelected);
		System.out.println(sort+"   "+order+"  "+page+"  "+rows);
		//第一次datagrid自动向url发一次请求，都是为null,需要初始化
		int pageNow=Integer.parseInt((page==null||page=="0")?"1":page);
		int pageRows=Integer.parseInt((rows==null||rows=="0")?"10":rows);
		super.writeJson(majorFieldService.getDataGrid(name,departmentSelected,sort,order,pageNow,pageRows));
	}
	
	/**
	 * 添加专业页面
	 */
	public String addMajorPage(){
		request.setAttribute("departments", departmentService.findAllDepartments());
		return "addMajorPage";
	}
	
	/**
	 * 添加专业信息
	 */
	public void addMajor(){
		//根据学院id查找学院
		majorField.setDepartment(departmentService.findDepartmentById(Integer.parseInt(departmentSelected)));
		boolean result=majorFieldService.addMajorField(majorField);
		Json json =new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	public void prepareEditMajorPage(){
		//根据id查找专业信息
		majorField=majorFieldService.findMajorById(majorId);
		request.setAttribute("departments", departmentService.findAllDepartments());
	}
	/**
	 * 编辑专业页面
	 */
	public String editMajorPage(){
		return "editMajorPage";
	}
	
	/**
	 * 更新专业
	 */
	public void editMajor(){
		boolean result=majorFieldService.updateMajor(majorField);
		Json json =new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 批量删除专业信息
	 */
	public void toDeleteMajor(){
		boolean result=majorFieldService.deleteSelectedMajors(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
