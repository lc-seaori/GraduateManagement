package com.benson.graduate.stu.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.common.enumeration.EnumerationType;
import com.benson.graduate.stu.model.MajorField;
import com.benson.graduate.stu.model.StudentInfo;
import com.benson.graduate.stu.pagemodel.StudentInfoDto;
import com.benson.graduate.stu.service.DepartmentService;
import com.benson.graduate.stu.service.MajorFieldService;
import com.benson.graduate.stu.service.StudentInfoService;
import com.benson.graduate.sys.model.EnumerationValue;
import com.benson.graduate.sys.service.EnumerationValueService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 生源信息编辑Action
 * @author benson
 *
 */

@Controller("studentInfoEditStuInfoAction")
@Scope("prototype")
public class StudentInfo_EditStuInfoAction extends BaseAction implements ModelDriven<StudentInfoDto>,Preparable{
	
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
	
	//获取editStudentInfo.jsp传来的参数
	private int departmentId;
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	
	//接收从studentInfo传输过来的id
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	//传给editStudentInfo.jsp页面所需要的参数
	private String birthdays;
	private String enterCollegeTimes;
	private String graduateTimes;
	public String getBirthdays() {
		return birthdays;
	}
	public void setBirthdays(String birthdays) {
		this.birthdays = birthdays;
	}
	public String getEnterCollegeTimes() {
		return enterCollegeTimes;
	}
	public void setEnterCollegeTimes(String enterCollegeTimes) {
		this.enterCollegeTimes = enterCollegeTimes;
	}
	public String getGraduateTimes() {
		return graduateTimes;
	}
	public void setGraduateTimes(String graduateTimes) {
		this.graduateTimes = graduateTimes;
	}
	
	//struts2传参数给editStudentInfo.jsp页面
	private List<EnumerationValue> sex;
	private List<EnumerationValue> politicalFeature;
	private List<EnumerationValue> household;
	private List<EnumerationValue> schoolLength;
	private List<EnumerationValue> education;
	private List<EnumerationValue> nation;
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
	
	private StudentInfoDto model =new StudentInfoDto();
	/**
	 * 重写得到模型的方法
	 */
	@Override
	public StudentInfoDto getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	public void prepareToEditStudentInfoPage(){
		//为ModelDriven拦截器做准备
		//System.out.println("preparetoEditStudentInfoPage()");
		//System.out.println("需要编辑的学生源信息Id    ="   +id);
		StudentInfo studentInfo=studentInfoService.findStudentInfoById(id);
		BeanUtils.copyProperties(studentInfo, model);
		//日期格式转换：
		Date date1=model.getBirthday();
		Date date2=model.getEnterCollegeTime();
		Date date3=model.getGraduateTime();
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try {
			model.setBirthdayTrans(dateFormat.format(date1));
			model.setEnterCollegeTimeTrans(dateFormat.format(date2));
			model.setGraduateTimeTrans(dateFormat.format(date3));
			System.out.println(model.getBirthdayTrans()+ "    " +model.getEnterCollegeTimeTrans() +"   "+model.getGraduateTimeTrans());
			//System.out.println(dateShow);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 进入新生信息编辑页面
	 */
	public String toEditStudentInfoPage(){
		
		//根据id获取该学生源信息
		System.out.println("该学生的专业id为：  "+model.getMajorField().getId());
		
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
		//专业
		List<MajorField> majors=majorFieldService.findAllMajorsByDepartmentId(model.getMajorField().getDepartment().getId());
		request.setAttribute("majors", majors);
		//学院
		request.setAttribute("departments", departmentService.findAllDepartments());
		request.setAttribute("rebirthday", model.getBirthdayTrans());
		request.setAttribute("reenterCollegeTime", model.getEnterCollegeTimeTrans());
		request.setAttribute("regraduateTime", model.getGraduateTimeTrans());
		return "editStudentInfoPage";
	}
	
	/**
	 * 点击学院下拉框选中学院之后，根据id查询所有的专业
	 */
	public void toGetMajorField(){
		List<MajorField> majors=majorFieldService.findAllMajorsByDepartmentId(departmentId);
//		for(MajorField major : majors){
//			System.out.println(major.getId()+"   "+major.getName()+"  "+major.getRemark()+"  "+major.getDepartment().getName());
//		}
		super.writeJson(majors);
	}
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("prepare()");
	}
	
	/**
	 * 编辑学生源信息
	 */
	public void editStudentInfo(){
//		System.out.println(model.getExamNum()+" houseplace " +model.getHouseholdPlace()+"school "+model.getGraduationSchool()+" idcard "+model.getIdCard()+
//				" name "+model.getName()+"  nativeplace"+model.getNativePlace()+" phone "+model.getPhone()+" post  "+model.getPostEncoding()+" educationtype  "+model.getEducationType()
//				+"  "+model.getHouseholdType()+"  "+model.getNationType()+"   "+model.getPoliticalFeatureType()+"  "+model.getSchoolLengthType()+
//				" sex  "+model.getSexType()+" birthday "+model.getBirthday()+" collegetime  "+model.getEnterCollegeTime()+"   ");
//		System.out.println("学生id         "+id+" modelId:  "+model.getId()+"   "+birthdays+"   "+enterCollegeTimes);
		//转回StudentInfo
		StudentInfo studentInfo=new StudentInfo();
		BeanUtils.copyProperties(model, studentInfo);
		//设置缺的属性
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date date1=null;
		Date date2=null;
		Date date3=null;
		try {
			date1=dateFormat.parse(birthdays);
			date2=dateFormat.parse(enterCollegeTimes);
			date3=dateFormat.parse(graduateTimes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		studentInfo.setBirthday(date1);
		studentInfo.setEnterCollegeTime(date2);
		studentInfo.setGraduateTime(date3);
//		System.out.println(studentInfo.getExamNum()+" houseplace " +studentInfo.getHouseholdPlace()+"school "+studentInfo.getGraduationSchool()+" idcard "+studentInfo.getIdCard()+
//				" name "+studentInfo.getName()+"  nativeplace"+studentInfo.getNativePlace()+" phone "+studentInfo.getPhone()+" post  "+studentInfo.getPostEncoding()+" educationtype  "+studentInfo.getEducationType()
//				+"  "+studentInfo.getHouseholdType()+"  "+studentInfo.getNationType()+"   "+studentInfo.getPoliticalFeatureType()+"  "+studentInfo.getSchoolLengthType()+
//				" sex  "+studentInfo.getSexType()+" birthday "+studentInfo.getBirthday()+" collegetime  "+studentInfo.getEnterCollegeTime()+"   ");
		
		boolean result=studentInfoService.updateStudentInfo(studentInfo);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}

}
