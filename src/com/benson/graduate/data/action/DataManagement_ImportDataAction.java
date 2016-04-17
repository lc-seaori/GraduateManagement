package com.benson.graduate.data.action;

import java.io.File;
import java.io.FileInputStream;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.data.service.DataManagementService;

/**
 * 导入学生源信息Action
 * @author benson
 *
 */
@Controller("importDataAction")
@Scope("prototype")
public class DataManagement_ImportDataAction extends BaseAction {

	private static final long serialVersionUID = -6147184015752180073L;
	
	private DataManagementService dataManagementService;
	@Resource(name="dataManagementService")
	public void setDataManagementService(DataManagementService dataManagementService) {
		this.dataManagementService = dataManagementService;
	}

	// 封装上传文件域的属性
	private File upload;
	// 封装上传文件类型的属性
	private String uploadContentType;
	// 封装上传文件名的属性
	private String uploadFileName;
	// 直接在struts.xml文件中配置的属性
	// 定义该Action允许上传的文件类型
	private String allowTypes;
	
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getAllowTypes() {
		return allowTypes;
	}
	public void setAllowTypes(String allowTypes) {
		this.allowTypes = allowTypes;
	}

	/**
	 * 进入导入学生源信息页面
	 * @return
	 */
	public String toImportStuInfoPage(){
		return "importStuInfoPage";
	}
	
	/**
	 * 导入学生信息页面
	 * @return
	 */
	public String toImportStuPage(){
		return "importStuPage";
	}
	
	public String toImportGraPage(){
		return "importGraPage";
	}
	
	/**
	 * 对文件进行操作（上传学生源信息）
	 * @throws Exception 
	 */
	public void uploadStuInfo() throws Exception{
		System.out.println("uploadStuInfo()");
		System.out.println("文件名为：   "+getUploadFileName());
		FileInputStream fileInputStream = new FileInputStream(getUpload());
		
		String format="";
		if(getUploadFileName().endsWith("xls")){
			format="xls";
		}else if(getUploadFileName().endsWith("xlsx")){
			format="xlsx";
		}
		//上传学生源信息操作
		String result=dataManagementService.toUploadStuInfoByExcel(fileInputStream, format);
		Json json =new Json();
		String res[]=result.split(",");
		if(res[0].equals("1")){
			//成功
			json.setSuccess(true);
		}else{
			json.setSuccess(false);
		}
		json.setMsg(result);
		super.writeJson(json);
		
	}
	
	/**
	 * 对文件进行操作（上传学生就业信息）
	 * @throws Exception 
	 */
	public void uploadGra() throws Exception{
		System.out.println("uploadStuInfo()");
		System.out.println("文件名为：   "+getUploadFileName());
		FileInputStream fileInputStream = new FileInputStream(getUpload());
		
		String format="";
		if(getUploadFileName().endsWith("xls")){
			format="xls";
		}else if(getUploadFileName().endsWith("xlsx")){
			format="xlsx";
		}
		//上传学生就业信息操作
		String result=dataManagementService.toUploadGraByExcel(fileInputStream, format);
		Json json =new Json();
		String res[]=result.split(",");
		if(res[0].equals("1")){
			//成功
			json.setSuccess(true);
		}else{
			json.setSuccess(false);
		}
		json.setMsg(result);
		super.writeJson(json);
		
	}

	/**
	 * 过滤文件类型
	 * @param types 系统所有允许上传的文件类型
	 * @return 如果上传文件的文件类型允许上传，返回null
	 *		   否则返回error字符串
	 */
	public String filterTypes(String[] types)
	{
		// 获取允许上传的所有文件类型
		String fileType = getUploadContentType();
		System.out.println("UploadContentType:    "+getUploadContentType() );
		for (String type : types)
		{
			System.out.println();
			if (type.equals(fileType))
			{
				//System.out.println("允许上传");
				//return null;
				return null;
			}
		}
		//System.out.println("不允许上传");
		return "nono";
	}

	// 执行输入校验
	public void validateUploadStuInfo()
	{
		// 将允许上传文件类型的字符串以英文逗号（,）
		// 分解成字符串数组从而判断当前文件类型是否允许上传
		String filterResult = filterTypes(getAllowTypes().split(","));
		// 如果当前文件类型不允许上传
		if (filterResult != null)
		{
			// 添加FieldError
			System.out.println("类型不支持");
			Json json =new Json();
			json.setSuccess(false);
			json.setMsg(-5+",上传的文件类型有误，请选择以.xls或者.xlsx为后缀的excel文件");
			super.writeJson(json);
			//addFieldError("upload" , "您要上传的文件类型不正确！");
		}
	}
}
