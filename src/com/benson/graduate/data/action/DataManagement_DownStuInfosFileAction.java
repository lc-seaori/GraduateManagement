package com.benson.graduate.data.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;

/**
 * 导出信息Action
 * @author benson
 *
 */
@Controller("downStuInfosFileAction")
@Scope("prototype")
public class DataManagement_DownStuInfosFileAction extends BaseAction {

	private static final long serialVersionUID = -8901740796414711894L;

	private String inputPath;
	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	private String fileName;   //初始通过param指定的文件名属性
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public InputStream getStuInfosFile() throws Exception{
		//ServletContext提供getResourceAsStream()方法
		//返回指定文件对应的输入流
		return ServletActionContext.getServletContext().getResourceAsStream(inputPath);
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("lai le ");
		System.out.println(inputPath);
		return super.execute();
	}
	
	//提供转换编码后的供下载用的文件名  
	public String getDownloadFileName(){
		String downFileName=fileName;
		System.out.println("downFileName  :"+downFileName);
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
