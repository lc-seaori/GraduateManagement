package com.benson.graduate.stu.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.common.enumeration.EnumerationType;
import com.benson.graduate.stu.model.GraduateInfo;
import com.benson.graduate.stu.model.Student;
import com.benson.graduate.stu.service.GraduateInfoService;
import com.benson.graduate.stu.service.StudentService;
import com.benson.graduate.sys.model.EnumerationValue;
import com.benson.graduate.sys.service.EnumerationValueService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.util.ValueStack;

@Controller("addGraduateInfoAction")
@Scope("prototype")
public class GraduateInfo_AddGraduateInfoAction extends BaseAction implements Preparable,ModelDriven<GraduateInfo>{

	private static final long serialVersionUID = 8972035583820223887L;
	
	private GraduateInfoService graduateInfoService;
	@Resource(name="graduateInfoService")
	public void setGraduateInfoService(GraduateInfoService graduateInfoService) {
		this.graduateInfoService = graduateInfoService;
	}
	private StudentService studentService;
	@Resource(name="studentService")
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	private EnumerationValueService enumerationValueService;
	@Resource(name="enumerationValueService")
	public void setEnumerationValueService(
			EnumerationValueService enumerationValueService) {
		this.enumerationValueService = enumerationValueService;
	}
	
	//接收从graduateInfoList.jsp页面传过来的参数
	private Integer stuId;
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}
	
	//modelDriven拦截器
	private GraduateInfo model=new GraduateInfo();

	/**
	 * 进入添加就业信息之前准备数据
	 */
	public void prepareToAddGraduateInfoPage(){
		System.out.println("prepareToAddGraduateInfoPage()");
		//根据id查找学生名字
		String name=studentService.findStudentNameById(stuId);
		System.out.println("name为：  "+name);
		request.setAttribute("stuName", name);
		//应聘方式与公司性质
		List<EnumerationValue> unitTypes=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.UNIT_TYPE);
		request.setAttribute("unitTypes", unitTypes);
		List<EnumerationValue> applyTypes=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.APPLY_RECRUITMENT_TYPE);
		request.setAttribute("applyTypes", applyTypes);
	}

	/**
	 * 进入添加就业信息页面
	 */
	public String toAddGraduateInfoPage(){
		System.out.println("toAddGraduateInfoPage()+  stuId :  "+stuId);
		return "addGraduateInfoPage";
		//return "addGraduateInfoPage";
	}
	
	/**
	 * 添加就业信息
	 */
	public void addGraudateInfo(){
		ValueStack vs = ActionContext.getContext().getValueStack();  
		System.out.println("从添加页面传过来的学生id，用于保存就业信息：   "+vs.findValue("stuId").toString());
		//System.out.println("测试数据   "+model.getName()+"  "+model.getUnitType()+"  "+model.getApplyType()+"  "+model.getUnitAddress());
		//根据学生id查找学生
		Student student=studentService.findStudentById(Integer.parseInt(vs.findValue("stuId").toString()));
		model.setStudent(student);
		boolean result=graduateInfoService.addGraduateInfo(model);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 进入学生列表页面
	 * @return
	 */
	public String toStudentListPage(){
		return "studentListPage";
	}
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public GraduateInfo getModel() {
		// TODO Auto-generated method stub
		return model;
	}
}
