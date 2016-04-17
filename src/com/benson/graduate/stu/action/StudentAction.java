package com.benson.graduate.stu.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.common.enumeration.EnumerationType;
import com.benson.graduate.stu.model.Clazz;
import com.benson.graduate.stu.model.Student;
import com.benson.graduate.stu.service.ClazzService;
import com.benson.graduate.stu.service.StudentService;
import com.benson.graduate.sys.model.EnumerationValue;
import com.benson.graduate.sys.service.EnumerationValueService;
import com.opensymphony.xwork2.Preparable;

@Controller("studentAction")
@Scope("prototype")
public class StudentAction extends BaseAction implements Preparable{

	private static final long serialVersionUID = -1610547386069721068L;
	
	private StudentService studentService;
	@Resource(name="studentService")
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	private ClazzService clazzService;
	@Resource(name="clazzService")
	public void setClazzService(ClazzService clazzService) {
		this.clazzService = clazzService;
	}
	private EnumerationValueService enumerationValueService;
	@Resource(name="enumerationValueService")
	public void setEnumerationValueService(
			EnumerationValueService enumerationValueService) {
		this.enumerationValueService = enumerationValueService;
	}
	
	//获取前台easy-ui传过来的参数
	private String rows;//每页显示的记录数    
    private String page;//当前第几页
    private String sort; //根据哪个字段排序
    private String order;	//desc,asc
    private String code;	//学号
    private String name;	//名字
    private String departmentName;	//学院
    private String clazzName;
    private Integer clazzVal;	//班别
    private Integer stuStatusVal;
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
	public Integer getClazzVal() {
		return clazzVal;
	}
	public void setClazzVal(Integer clazzVal) {
		this.clazzVal = clazzVal;
	}
	public Integer getStuStatusVal() {
		return stuStatusVal;
	}
	public void setStuStatusVal(Integer stuStatusVal) {
		this.stuStatusVal = stuStatusVal;
	}
	public String getClazzName() {
		return clazzName;
	}
	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}
	
	//获取从studentList.jsp页面传来的参数
	private Integer id;
	private String idList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIdList() {
		return idList;
	}
	public void setIdList(String idList) {
		this.idList = idList;
	}
	
	//传给studentList.jsp页面的参数
	private List<EnumerationValue> schoolStatus;
	public List<EnumerationValue> getSchoolStatus() {
		return schoolStatus;
	}
	public void setSchoolStatus(List<EnumerationValue> schoolStatus) {
		this.schoolStatus = schoolStatus;
	}
	/**
	 * 进入学生列表页面
	 * @return
	 */
	public String toStudentListPage(){
		return "studentListPage";
	}
	
	/**
	 * 得到所有学生信息(分页)
	 */
	public void getStudentList(){
		System.out.println("记录数： "+rows +"  第几页：  "+page+"  排序字段 ："+sort +"  排序方式：  "+order);
		//第一次datagrid自动向url发一次请求，都是为null,需要初始化
		int pageNow=Integer.parseInt((page==null||page=="0")?"1":page);
		int pageRows=Integer.parseInt((rows==null||rows=="0")?"10":rows);
		super.writeJson(studentService.getDataGrid(code,name,departmentName, clazzName,graduateTime,sort,order,pageNow,pageRows,false));
	}
	
	/**
	 * otherStuList.jsp得到没有添加就业信息的学生列表
	 */
	public void getStudentsNotInGra(){
		//第一次datagrid自动向url发一次请求，都是为null,需要初始化
		int pageNow=Integer.parseInt((page==null||page=="0")?"1":page);
		int pageRows=Integer.parseInt((rows==null||rows=="0")?"10":rows);
		super.writeJson(studentService.getDataGrid(code,name,departmentName, clazzName,graduateTime,sort,order,pageNow,pageRows,true));
	}
	
	/**
	 * 得到所有的学生信息，提供给添加就业信息的时候使用
	 */
	public void getAllStudents(){
		super.writeJson(studentService.getDataGrid());
	}
	
	
	/**
	 * 为进入编辑页面做准备
	 */
	public void prepareToEditStudentPage(){
		//数据准备
		System.out.println("id    ="+id);
		Student student=studentService.findStudentById(id);
		System.out.println(student.getCode()+" "+student.getName());
		code=student.getCode();
		name=student.getName();
		departmentName=student.getDepartment().getName();
		Date date=student.getStudentInfo().getGraduateTime();
		if(date!=null){
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			try {
				graduateTime=dateFormat.format(date);
				//System.out.println(dateShow);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//clazzName=student.getClazz().getName();
		//根据专业id查找所有班级
		List<Clazz> classes=clazzService.findAllClazzByMajorId(student.getStudentInfo().getMajorField().getId());
		request.setAttribute("classes", classes);
		request.setAttribute("classVal", student.getClazz().getId());
		request.setAttribute("stuStatus", student.getSchoolStatus());
		schoolStatus=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.STU_STATUS);
		if(schoolStatus!=null&&schoolStatus.size()>0){
			for(EnumerationValue value:schoolStatus){
				System.out.println(value.getId()+"   "+value.getName()+"  "+value.getRemark());
			}
		}
	}
	
	/**
	 * 进入编辑页面
	 */
	public String toEditStudentPage(){
		return "editStudentPage";
	}
	
	/**
	 * 更新学生信息
	 */
	public void editStudent(){
		System.out.println(id+"   "+code+"   "+clazzVal+"   "+stuStatusVal);
		Student stu=studentService.findStudentById(id);
		stu.setCode(code);
		stu.setSchoolStatus(stuStatusVal);
		Clazz clazz=clazzService.findClazzById(clazzVal);
		stu.setClazz(clazz);
		//更新
		boolean result=studentService.updateStudent(stu);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 删除学生信息
	 */
	public void toDeleteStudent(){
		boolean result=studentService.deleteSelectedStudent(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
	}
	
}
