package com.benson.graduate.data.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.common.enumeration.EnumerationType;
import com.benson.graduate.data.model.GraduateRate;
import com.benson.graduate.data.model.WorkRate;
import com.benson.graduate.stu.model.Clazz;
import com.benson.graduate.stu.model.Student;
import com.benson.graduate.stu.service.ClazzService;
import com.benson.graduate.stu.service.GraduateInfoService;
import com.benson.graduate.stu.service.StudentService;
import com.benson.graduate.sys.model.EnumerationValue;
import com.benson.graduate.sys.service.EnumerationValueService;
import com.google.gson.Gson;

/**
 * 查询毕业率和就业率的action
 * @author benson
 *
 */
@Controller("workAndGraduateListAction")
@Scope("prototype")
public class DataManagement_GetWorkAndGraduateListAction extends BaseAction {

	private static final long serialVersionUID = -6039010170967397516L;
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
	private EnumerationValueService enumerationValueService;
	@Resource(name="enumerationValueService")
	public void setEnumerationValueService(
			EnumerationValueService enumerationValueService) {
		this.enumerationValueService = enumerationValueService;
	}
	private GraduateInfoService graduateInfoService;
	@Resource(name="graduateInfoService")
	public void setGraduateInfoService(GraduateInfoService graduateInfoService) {
		this.graduateInfoService = graduateInfoService;
	}
	
	//接收从chooseTime.jsp页面传过来的参数
	private String graTime;
	private String workTime;
	public void setGraTime(String graTime) {
		this.graTime = graTime;
	}
	public String getGraTime() {
		return graTime;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	
	/**
	 * 选择时间
	 * @return
	 */
	public String toChooseTimePage(){
		return "chooseTimePage";
	}


	/**
	 * 前往毕业率和就业率页面
	 * @return
	 */
	public String toWorkAndGraduateListPage(){
		System.out.println("toWorkAndGraduateListPage()");
		request.setAttribute("graTime", graTime);
		return "workAndGraduateListPage";
	}
	
	/**
	 * 计算未毕业人数、毕业人数以及毕业率
	 */
	public void getGraduateRate(){
		//处理时间(年月)
		graTime=graTime.substring(0,graTime.indexOf("-"));
		System.out.println("毕业时间为： "+graTime);
		
		//workAndGraduateList.jsp页面需要的页面数据模型
		List<GraduateRate> graduateRates=new ArrayList<GraduateRate>();
		
		//获取是"是"的枚举值的id
		List<EnumerationValue> values=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.BOOLEAN_TYPE);
		Integer isboolean=0;
		if(values!=null&&values.size()>0){
			for(EnumerationValue val:values){
				if(val.getName().equals("是")){
					isboolean=val.getId();
				}
			}
		}
		//查找毕业班级
		List<Clazz> clazzs=clazzService.findAllGraClazzes(isboolean);
		if(clazzs!=null&&clazzs.size()>0){
			for(Clazz clazz:clazzs){
				//得到班级名字
				String clazzName=clazz.getName();
				System.out.println("班级名字为：   "+clazzName);
				//根据班级名字和毕业时间查找所有的学生
				List<Student> stus=studentService.findStudentsByClazzNameAndTime(clazzName,graTime);
				if(stus!=null&&stus.size()>0){
					
					//毕业人数和未毕业人数设定：  毕业班级的总学生数
					//里面可能包含退学、辍学、参军等。
					//所以只计算学籍状态不是已毕业和未注册的学生为未毕业、已毕业的为毕业
					int notGraCount=0,graCount=0;
					
					//不是null，证明是在指定年份毕业的毕业班的学生
					GraduateRate rate=new GraduateRate();
					//System.out.println("测试：  "+stus.get(0).getName()+"   "+stus.get(0).getId()+"   "+stus.get(0).getDepartment().getName());
					rate.setDepartment(stus.get(0).getDepartment().getName());
					rate.setMajorField(clazz.getMajorField().getName());
					rate.setClazz(clazz.getName());
					for(Student stu:stus){
						if(stu.getSchoolStatus()!=5001&&stu.getSchoolStatus()!=5007){
							notGraCount++;
						}else if(stu.getSchoolStatus()==5007){
							graCount++;
						}
					}
					rate.setRate("毕业率");
					rate.set未毕业人数(notGraCount);
					rate.set已毕业人数(graCount);
					
					graduateRates.add(rate);
					
				}else{
					//不是在指定年份毕业的
					//System.out.println("无学生");
				}
			}
			if(graduateRates.size()==0){
				GraduateRate rate=new GraduateRate();
				rate.setRate("无任何班级在该毕业年份毕业");
				rate.setDepartment("学院");
				rate.setMajorField("专业");
				rate.setClazz("班别");
				graduateRates.add(rate);
			}
			//写回给页面
			try {
				Gson gson = new Gson();
				System.out.println(gson.toJson(graduateRates));
				ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
				ServletActionContext.getResponse().getWriter().write(gson.toJson(graduateRates));
				ServletActionContext.getResponse().getWriter().flush();
				ServletActionContext.getResponse().getWriter().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void getWorkRate(){
		//处理时间(年月)
		workTime=workTime.substring(0,workTime.indexOf("-"));
		System.out.println("就业时间为：  "+workTime);
		
		//获取是"是"的枚举值的id
		List<EnumerationValue> values=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.BOOLEAN_TYPE);
		Integer isboolean=0;
		if(values!=null&&values.size()>0){
			for(EnumerationValue val:values){
				if(val.getName().equals("是")){
					isboolean=val.getId();
				}
			}
		}
		//查找毕业班级
		List<Clazz> clazzs=clazzService.findAllGraClazzes(isboolean);
		
		//workAndGraduateList.jsp页面需要的页面数据模型
		List<WorkRate> workRates=new ArrayList<WorkRate>();
		if(clazzs!=null&&clazzs.size()>0){
			for(Clazz clazz:clazzs){
				//得到班级名字
				String clazzName=clazz.getName();
//				System.out.println("班级名字为：   "+clazzName);
				//根据班级名字和毕业时间查找所有的学生
				List<Student> stus=studentService.findStudentsByClazzNameAndTime(clazzName,workTime);
				
				if(stus!=null&&stus.size()>0){
					WorkRate rate =new WorkRate();
					//毕业生就业人数与未就业人数
					int notWorkCount=0;
					int workCount=0;
					//int sumCount=0;
					rate.setDepartment(stus.get(0).getDepartment().getName());
					rate.setMajorField(clazz.getMajorField().getName());
					rate.setClazz(clazz.getName());
					for(Student stu:stus){
						if(stu.getSchoolStatus()==5007){
							//sumCount++;
							//从就业信息中是否找到该学生的就业信息，而且前提是已经通过学校审核
							if(graduateInfoService.findGraduateInfoByStuId(stu.getId())){
								//找到学生的就业信息
								workCount++;
							}else{
								notWorkCount++;
							}
						}
					}
					rate.setRate("就业率");
					rate.set未就业人数(notWorkCount);
					rate.set已就业人数(workCount);
					
					workRates.add(rate);
				}
			}
			
		}
		if(workRates.size()==0){
			WorkRate rate=new WorkRate();
			rate.setRate("无任何班级的就业信息");
			rate.setDepartment("学院");
			rate.setMajorField("专业");
			rate.setClazz("班别");
			workRates.add(rate);
		}
		//写回给页面
		try {
			Gson gson = new Gson();
			System.out.println(gson.toJson(workRates));
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(gson.toJson(workRates));
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
