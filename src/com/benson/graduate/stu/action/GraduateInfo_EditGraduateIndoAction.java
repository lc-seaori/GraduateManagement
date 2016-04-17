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

@Controller("editGraduateInfoAction")
@Scope("prototype")
public class GraduateInfo_EditGraduateIndoAction extends BaseAction implements ModelDriven<GraduateInfo>,Preparable{

	private static final long serialVersionUID = -5261612059282030228L;
	
	private GraduateInfoService graduateInfoService;
	@Resource(name="graduateInfoService")
	public void setGraduateInfoService(GraduateInfoService graduateInfoService) {
		this.graduateInfoService = graduateInfoService;
	}
	private EnumerationValueService enumerationValueService;
	@Resource(name="enumerationValueService")
	public void setEnumerationValueService(
			EnumerationValueService enumerationValueService) {
		this.enumerationValueService = enumerationValueService;
	}
	private StudentService studentService;
	@Resource(name="studentService")
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	//ModelDriven 拦截器
	private GraduateInfo model=new GraduateInfo();

	//接收从graduateInfoList.jsp传过来的参数
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	//接收从editGraduateInfo.jsp页面传过来的参数
	private Integer stuId;
	public Integer getStuId() {
		return stuId;
	}
	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}


	@Override
	public GraduateInfo getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	
	public void prepareToEditGraduateInfoPage(){
		//数据准备
		//查找所有的应聘方式和单位性质
		System.out.println("graduateInfoList.jsp传来的id:  "+id);
		List<EnumerationValue> unitTypes=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.UNIT_TYPE);
		List<EnumerationValue> applyTypes=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.APPLY_RECRUITMENT_TYPE);
		model=graduateInfoService.findGraduateInfoById(id);
		Integer stuId=model.getStudent().getId();
		request.setAttribute("stuId", stuId);
		request.setAttribute("unitTypes", unitTypes);
		request.setAttribute("applyTypes", applyTypes);
	}
	
	/**
	 * 进入毕业生信息编辑页面
	 * @return
	 */
	public String toEditGraduateInfoPage(){
		return "editGraduateInfoPage";
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 更新学生毕业信息
	 */
	public void editGraduateInfo(){
		//更新model的Student实体
		ValueStack vs = ActionContext.getContext().getValueStack();  
		System.out.println("从添加页面传过来的学生id，用于保存就业信息：   "+vs.findValue("stuId").toString());
		//根据学生id查找学生
		Student student=studentService.findStudentById(Integer.parseInt(vs.findValue("stuId").toString()));
		model.setStudent(student);
		boolean result=graduateInfoService.updateGraduateInfo(model);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
}
