package com.benson.graduate.stu.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.common.enumeration.EnumerationType;
import com.benson.graduate.stu.model.Clazz;
import com.benson.graduate.stu.model.Department;
import com.benson.graduate.stu.model.MajorField;
import com.benson.graduate.stu.model.Student;
import com.benson.graduate.stu.model.StudentInfo;
import com.benson.graduate.stu.service.ClazzService;
import com.benson.graduate.stu.service.DepartmentService;
import com.benson.graduate.stu.service.MajorFieldService;
import com.benson.graduate.stu.service.StudentInfoService;
import com.benson.graduate.stu.service.StudentService;
import com.benson.graduate.sys.model.EnumerationValue;
import com.benson.graduate.sys.service.EnumerationValueService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 生源信息添加Action
 * @author benson
 *
 */

@Controller("studentInfoAddNewInfoAction")
@Scope("prototype")
public class StudentInfo_AddNewInfoAction extends BaseAction implements ModelDriven<StudentInfo>{
	
	private static final long serialVersionUID = 1L;

	//需要使用到的业务逻辑组件
	private StudentInfoService studentInfoService;
	@Resource(name="studentInfoService")
	public void setStudentInfoService(StudentInfoService studentInfoService) {
		this.studentInfoService = studentInfoService;
	}
	private EnumerationValueService enumerationValueService;
	@Resource(name="enumerationValueService")
	public void setEnumerationValueService(
			EnumerationValueService enumerationValueService) {
		this.enumerationValueService = enumerationValueService;
	}
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
	private ClazzService clazzService;
	@Resource(name="clazzService")
	public void setClazzService(ClazzService clazzService) {
		this.clazzService = clazzService;
	}
	private StudentService studentService;
	@Resource(name="studentService")
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	//接收从studentInfoAduitStuInfoAction传送过来的学生信息id
	private Integer id;
	public void setId(Integer id) {
		this.id = id;
	}
	
	//获取addStuInfo.jsp传来的参数
	private int departmentId;
	private int majorFieldId;
	private String valiExamNum;
	private String valiIdCard;
	
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public int getMajorFieldId() {
		return majorFieldId;
	}
	public void setMajorFieldId(int majorFieldId) {
		this.majorFieldId = majorFieldId;
	}
	public String getValiExamNum() {
		return valiExamNum;
	}
	public void setValiExamNum(String valiExamNum) {
		this.valiExamNum = valiExamNum;
	}
	public String getValiIdCard() {
		return valiIdCard;
	}
	public void setValiIdCard(String valiIdCard) {
		this.valiIdCard = valiIdCard;
	}
	
	//接收从exportStuInfos.jsp传过来的参数
	private int majorId;
	public int getMajorId() {
		return majorId;
	}
	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}
	
	//传给addStudent.jsp页面的参数
	private String stuName;
	private String departmentName;
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	//获取addStudent.jsp页面传来的参数
	private String code;
	private Integer clazz;
	private Integer schoolStatus;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getClazz() {
		return clazz;
	}
	public void setClazz(Integer clazz) {
		this.clazz = clazz;
	}
	public Integer getSchoolStatus() {
		return schoolStatus;
	}
	public void setSchoolStatus(Integer schoolStatus) {
		this.schoolStatus = schoolStatus;
	}
	
	//struts2传参数给edutStudentInfo.jsp页面
	private List<Department> departments;
	private List<EnumerationValue> sex;
	private List<EnumerationValue> politicalFeature;
	private List<EnumerationValue> household;
	private List<EnumerationValue> schoolLength;
	private List<EnumerationValue> education;
	private List<EnumerationValue> nation;
	public List<Department> getDepartments() {
		return departments;
	}
	public List<EnumerationValue> getSex() {
		return sex;
	}
	public StudentInfoService getStudentInfoService() {
		return studentInfoService;
	}
	public List<EnumerationValue> getPoliticalFeature() {
		return politicalFeature;
	}
	public List<EnumerationValue> getHousehold() {
		return household;
	}
	public List<EnumerationValue> getSchoolLength() {
		return schoolLength;
	}
	public List<EnumerationValue> getEducation() {
		return education;
	}
	public List<EnumerationValue> getNation() {
		return nation;
	}
	
	//ajax验证字段需要字段
	private InputStream inputStream;
	public InputStream getInputStream(){
		return inputStream;
	}
	
	private StudentInfo model =new StudentInfo();
	/**
	 * 重写得到模型的方法
	 */
	@Override
	public StudentInfo getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	/**
	 * 进入新生信息添加页面
	 */
	public String toAddStudentInfoPage(){
		//获取页面所需要的枚举键值
		//性别
		sex=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.SEX_TYPE);
		if(sex!=null&&sex.size()>0){
			for(EnumerationValue value:sex){
				System.out.println(value.getId()+"   "+value.getName()+"  "+value.getRemark());
			}
		}
		//政治面貌
		politicalFeature=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.POLITICAL_FEATURE_TYPE);
		if(politicalFeature!=null&&politicalFeature.size()>0){
			for(EnumerationValue value:politicalFeature){
				System.out.println(value.getId()+"   "+value.getName()+"  "+value.getRemark());
			}
		}
		//户籍类型
		household=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.HOUSEHOLD_TYPE);
		if(household!=null&&household.size()>0){
			for(EnumerationValue value:household){
				System.out.println(value.getId()+"   "+value.getName()+"  "+value.getRemark());
			}
		}
		//学制类型
		schoolLength=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.SCHOOL_LENGTH_TYPE);
		if(schoolLength!=null&&schoolLength.size()>0){
			for(EnumerationValue value:schoolLength){
				System.out.println(value.getId()+"   "+value.getName()+"  "+value.getRemark());
			}
		}
		//学历类型
		education=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.EDUCATION_TYPE);
		if(education!=null&&education.size()>0){
			for(EnumerationValue value:education){
				System.out.println(value.getId()+"   "+value.getName()+"  "+value.getRemark());
			}
		}
		//民族类型
		nation=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.NATION_TYPE);
		if(nation!=null&&nation.size()>0){
			for(EnumerationValue value:nation){
				System.out.println(value.getId()+"   "+value.getName()+"  "+value.getRemark());
			}
		}
		//学院、专业
		departments=departmentService.findAllDepartments();
		if(departments!=null&&departments.size()>0){
			for(Department department:departments){
				System.out.println(department.getId()+"   "+department.getName()+"  "+department.getRemark());
			}
		}
		return "addStudentInfoPage";
	}
	
	/**
	 * 点击学院下拉框选中学院之后，根据id查询所有的专业
	 */
	public void toGetMajorField(){
		List<MajorField> majors=majorFieldService.findAllMajorsByDepartmentId(departmentId);
		super.writeJson(majors);
	}
	
	/**
	 * 点击专业下拉框选中专业之后，根据id查询所有班级
	 */
	public void toGetClazz(){
		System.out.println("专业id为：   "+majorId);
		List<Clazz> clazzes=clazzService.findAllClazzByMajorId(majorId);
		for(Clazz clazz :clazzes){
			System.out.println(clazz.getId()+"   "+clazz.getName());
		}
		super.writeJson(clazzes);
	}
	
	/**
	 * 添加学生源信息
	 */
	public void addStudentInfo(){
		
		Json json=new Json();
		//防止用户在错误提示之后继续添加相同信息，再次判断
		if(studentInfoService.findStudentInfoByExamNum(model.getExamNum())){
			json.setSuccess(false);
			json.setMsg("输入的准考证号已存在，请确认后再重新输入！");
			super.writeJson(json);
			return ;
		}else if(studentInfoService.findStudentInfoByIdCard(model.getIdCard())){
			json.setSuccess(false);
			json.setMsg("输入的身份证号已存在，请确认后再重新输入！");
			super.writeJson(json);
			return ;
		}
		
		//成功....根据专业id查找专业
		MajorField major=majorFieldService.findMajorById(majorFieldId);
		model.setMajorField(major);
		
		boolean result=studentInfoService.addStudentInfo(model);
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 添加学生源信息时，对准考证号进行验证
	 * @throws UnsupportedEncodingException 
	 */
	public String validateExamNum() throws UnsupportedEncodingException{
		System.out.println("vali         = "+valiExamNum);
		if(!studentInfoService.findStudentInfoByExamNum(valiExamNum)){
			inputStream=new ByteArrayInputStream("1".getBytes("UTF-8"));
		}else{
			inputStream=new ByteArrayInputStream("0".getBytes("UTF-8"));
		}
		return "ajaxSuccess";
	}
	
	/**
	 * 添加学生源信息时，对身份证进行验证
	 */
	public String validateIdCard() throws UnsupportedEncodingException{
		System.out.println("vali         = "+valiIdCard);
		if(!studentInfoService.findStudentInfoByIdCard(valiIdCard)){
			inputStream=new ByteArrayInputStream("1".getBytes("UTF-8"));
		}else{
			inputStream=new ByteArrayInputStream("0".getBytes("UTF-8"));
		}
		return "ajaxSuccess";
	}
	
	/**
	 * 添加学生信息的时候，对学号进行验证
	 */
	public String validateCode() throws UnsupportedEncodingException{
		System.out.println("vali         = "+code);
		if(!studentService.findStudentByCode(code)){
			inputStream=new ByteArrayInputStream("1".getBytes("UTF-8"));
		}else{
			inputStream=new ByteArrayInputStream("0".getBytes("UTF-8"));
		}
		return "ajaxSuccess";
	}
	
	/**
	 * 进入添加学生信息页面
	 */
	public String toAddStudentPage(){
		System.out.println("从studentInfoAduitStuInfoAction传送过来的学生信息id    : "+id);
		//根据id获取学生源信息
		StudentInfo studentInfo=studentInfoService.findStudentInfoById(id);
		System.out.println(studentInfo.getExamNum()+"   "+studentInfo.getName());
		//request.setAttribute("name", studentInfo.getName());
		stuName=studentInfo.getName();
		//根据专业id找所有班级
		List<Clazz> classes=clazzService.findAllClazzByMajorId(studentInfo.getMajorField().getId());
		//学籍状态
		List<EnumerationValue> values=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.STU_STATUS);
		request.setAttribute("classes", classes);
		departmentName=studentInfo.getMajorField().getDepartment().getName();
		//request.setAttribute("departmentName", studentInfo.getMajorField().getDepartment().getName());
		request.setAttribute("schoolStatus", values);
		return "addStudentPage";
	}
	
	/**
	 * 添加学生信息
	 */
	public void addStudent(){
		//System.out.println("addStudentPage传来的   ："+code+"   "+clazz+"   "+schoolStatus+"   "+departmentName+"   "+stuName);
		//System.out.println("测试id是否已经变成空   +  "+id);
		Json json=new Json();
		//再次判断学号是否有重复
		if(studentService.findStudentByCode(code)){
			json.setSuccess(false);
			json.setMsg("输入的学号已存在，请确认后再重新输入！");
			super.writeJson(json);
			return ;
		}
		//添加到学生表中
		Student student =new Student();
		student.setCode(code);
		student.setName(stuName);
		//更新studentInfo的学校审核状态
		studentInfoService.updateSchoolAduit(id);
		//根据id获取学生源信息
		StudentInfo studentInfo=studentInfoService.findStudentInfoById(id);
		student.setDepartment(studentInfo.getMajorField().getDepartment());
		Clazz cla=clazzService.findClazzById(clazz);
		student.setClazz(cla);
		student.setSchoolStatus(schoolStatus);
		student.setStudentInfo(studentInfo);
		if(studentService.saveStudent(student)){
			json.setSuccess(true);
			super.writeJson(json);
		}else{
			json.setSuccess(false);
			json.setMsg("审核失败");
			super.writeJson(json);
		}
	}

}
