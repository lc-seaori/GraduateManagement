package com.benson.graduate.sys.action;


import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.stu.model.Clazz;
import com.benson.graduate.stu.service.ClazzService;
import com.benson.graduate.stu.service.DepartmentService;
import com.benson.graduate.stu.service.GradeService;
import com.benson.graduate.stu.service.MajorFieldService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 班级Action
 * @author benson
 */
@Controller("clazzAction")
@Scope("prototype")
public class System_ClazzAction extends BaseAction implements Preparable, ModelDriven<Clazz>{

	private static final long serialVersionUID = -7137483928305206167L;
	
	private MajorFieldService majorFieldService;
	@Resource(name="majorFieldService")
	public void setMajorFieldService(MajorFieldService majorFieldService) {
		this.majorFieldService = majorFieldService;
	}
	private ClazzService clazzService;
	@Resource(name="clazzService")
	public void setClazzService(ClazzService clazzService) {
		this.clazzService = clazzService;
	}
	private DepartmentService departmentService;
	@Resource(name="departmentService")
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	private GradeService gradeService;
	@Resource(name="gradeService")
	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}
	
	//获取前台easy-ui传过来的参数
	private String rows;//每页显示的记录数    
    private String page;//当前第几页
    private String sort; //根据哪个字段排序
    private String order;	//desc,asc
    private String name;
    private String majorFieldSelected;
    private String gradeSelected;
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
	public String getMajorFieldSelected() {
		return majorFieldSelected;
	}
	public void setMajorFieldSelected(String majorFieldSelected) {
		this.majorFieldSelected = majorFieldSelected;
	}
	public String getGradeSelected() {
		return gradeSelected;
	}
	public void setGradeSelected(String gradeSelected) {
		this.gradeSelected = gradeSelected;
	}
	
	//接收从clazzList.jsp传过来的班级id
	public Integer clazzId;
	public Integer getClazzId() {
		return clazzId;
	}
	public void setClazzId(Integer clazzId) {
		this.clazzId = clazzId;
	}
	private String idList;
	public String getIdList() {
		return idList;
	}
	public void setIdList(String idList) {
		this.idList = idList;
	}
	
	private Clazz clazz=new Clazz();
	@Override
	public Clazz getModel() {
		// TODO Auto-generated method stub
		return clazz;
	}
	
	/**
	 * 班级列表页面
	 * @return
	 */
	public String toClazzListPage(){
		//查找是所有专业
		request.setAttribute("majors", majorFieldService.findAllMajorField());
		return "clazzListPage";
	}
	
	/**
	 * 获取班级列表Grid
	 */
	public void getClazzList(){
		System.out.println("测试：  "+name+"   "+majorFieldSelected);
		System.out.println(sort+"   "+order+"  "+page+"  "+rows);
		//第一次datagrid自动向url发一次请求，都是为null,需要初始化
		int pageNow=Integer.parseInt((page==null||page=="0")?"1":page);
		int pageRows=Integer.parseInt((rows==null||rows=="0")?"10":rows);
		super.writeJson(clazzService.getDataGrid(name,majorFieldSelected,sort,order,pageNow,pageRows));
	}
	
	/**
	 * 添加班级页面
	 * @return
	 */
	public String addClazzPage(){
		request.setAttribute("departments", departmentService.findAllDepartments());
		request.setAttribute("grades", gradeService.findAllGrades());
		return "addClazzPage";
	}
	
	/**
	 * 添加班级
	 */
	public void addClazz(){
		//根据id查找专业和查找年级
		clazz.setGrade(gradeService.findGradeById(Integer.parseInt(gradeSelected)));
		clazz.setMajorField(majorFieldService.findMajorById(Integer.parseInt(majorFieldSelected)));
		boolean result=clazzService.addClazz(clazz);
		Json json =new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	public void prepareEditClazzPage(){
		clazz=clazzService.findClazzById(clazzId);
	}
	
	/**
	 * 编辑班级信息页面
	 * @return
	 */
	public String editClazzPage(){
		request.setAttribute("departments", departmentService.findAllDepartments());
		request.setAttribute("grades", gradeService.findAllGrades());
		request.setAttribute("majors", majorFieldService.findAllMajorsByDepartmentId(clazz.getMajorField().getDepartment().getId()));
		//request.setAttribute("departmentId", clazz.getMajorField().getDepartment().getId());
		//request.setAttribute("depName", clazz.getMajorField().getDepartment().getName());
		return "editClazzPage";
	}
	
	/**
	 * 编辑班级信息
	 */
	public void editClazz(){
		boolean result=clazzService.updateClazz(clazz);
		Json json =new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 批量删除班级信息
	 */
	public void toDeleteClazz(){
		boolean result=clazzService.deleteSelectedClazzs(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
