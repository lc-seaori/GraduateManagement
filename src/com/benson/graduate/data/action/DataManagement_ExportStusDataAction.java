package com.benson.graduate.data.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.data.service.DataManagementService;
import com.benson.graduate.stu.model.Student;
import com.benson.graduate.stu.service.DepartmentService;
import com.benson.graduate.stu.service.StudentService;

/**
 * 导出学生信息Action
 * @author benson
 *
 */
@Controller("exportStusAction")
@Scope("prototype")
public class DataManagement_ExportStusDataAction extends BaseAction{
	
	private static final long serialVersionUID = -2100646487040387044L;
	
	private DepartmentService departmentService;
	@Resource(name="departmentService")
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	private StudentService studentService;
	@Resource(name="studentService")
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	private DataManagementService dataManagementService;
	@Resource(name="dataManagementService")
	public void setDataManagementService(DataManagementService dataManagementService) {
		this.dataManagementService = dataManagementService;
	}
	
	//接收从exportStuInfos.jsp传来的参数
	private String departmentSelected;
	private String majorFieldSelected;
	private String clazzSelected;
	public String getDepartmentSelected() {
		return departmentSelected;
	}
	public void setDepartmentSelected(String departmentSelected) {
		this.departmentSelected = departmentSelected;
	}
	public String getMajorFieldSelected() {
		return majorFieldSelected;
	}
	public void setMajorFieldSelected(String majorFieldSelected) {
		this.majorFieldSelected = majorFieldSelected;
	}
	public String getClazzSelected() {
		return clazzSelected;
	}
	public void setClazzSelected(String clazzSelected) {
		this.clazzSelected = clazzSelected;
	}
	
	//下载文件需要的
	private String fileName;   //初始通过param指定的文件名属性
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}	
	private InputStream is;
	
	/**
	 * 进入导出学生信息页面
	 * @return
	 */
	public String toExportStusPage(){
		//查询所有学院
		request.setAttribute("department", departmentService.findAllDepartments());
		return "exportStusPage";
	}
	
	public InputStream getExcelStream() throws Exception{
		int departmentId,majorFieldId,clazzId;
		//根据三个查询条件来判断excell的生成格式
		int type=0;   //默认根据学院来创建excell
		if(!clazzSelected.equals("---请选择班别---")){
			type=1;  //根据指定班别来创建excell
			clazzId=Integer.parseInt(clazzSelected);
			majorFieldId=Integer.parseInt(majorFieldSelected);
			departmentId=Integer.parseInt(departmentSelected);
		}else if(!majorFieldSelected.equals("---请选择专业---")){
			type=2;  //根据指定专业来创建excell
			clazzId=0;
			majorFieldId=Integer.parseInt(majorFieldSelected);
			departmentId=Integer.parseInt(departmentSelected);
		}else if(!departmentSelected.equals("---请选择学院---")){
			type=3;  //根据指定学院来创建excell
			clazzId=0;
			majorFieldId=0;
			departmentId=Integer.parseInt(departmentSelected);
		}else{
			clazzId=0;
			majorFieldId=0;
			departmentId=0;
		}
		List<Student> students= studentService.findStudentsForExport(departmentId, majorFieldId, clazzId);
		HSSFWorkbook book=dataManagementService.createHSSFWorkbookByStusList2(students,type);
		ByteArrayOutputStream os = new ByteArrayOutputStream();  
		try {
			//book.write(new FileOutputStream("d:/学生源信息数据.xls"));
			book.write(os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        byte[] fileContent = os.toByteArray();  
        is = new ByteArrayInputStream(fileContent);
        os.flush();
        os.close();
        return is;
	}
	
	/**
	 * 开始导出学生信息
	 * @throws IOException 
	 */
	public String exportStus() throws IOException{
		return "success";
	}
	
	//提供转换编码后的供下载用的文件名  
	public String getDownloadFileName(){
		String downFileName=fileName;
		//解决浏览器下载文件时出现的乱码问题
		String Agent = request.getHeader("User-Agent"); 
		if(Agent!=null){
			Agent = Agent.toLowerCase();
			try {
				if (Agent.indexOf("firefox") != -1){
					//火狐
					downFileName=new String(downFileName.getBytes(),"ISO8859-1");
				}else if(Agent.indexOf("msie") != -1){
					//ie
					downFileName = java.net.URLEncoder.encode(downFileName,"UTF-8");
				}else{
					//其他（谷歌，苹果等）
					downFileName = java.net.URLEncoder.encode(downFileName,"UTF-8");
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return downFileName;
	}

}
