package com.benson.graduate.data.action;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.company.model.RecruitmentInfo;
import com.benson.graduate.company.pagemodel.PageRecruitmentInfo;
import com.benson.graduate.company.service.RecruitmentInfoService;
import com.benson.graduate.company.service.RecruitmentUnitService;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.style.RtfFont;

/**
 * 导出学生源信息Action
 * 
 * @author benson
 *
 */
@Controller("exportRecInfosAction")
@Scope("prototype")
public class DataManagement_ExportRecInfosDataAction extends BaseAction {

	private static final long serialVersionUID = -6058263894841463551L;


	private RecruitmentInfoService recruitmentInfoService;
	@Resource(name = "recruitmentInfoService")
	public void setRecruitmentInfoService(
			RecruitmentInfoService recruitmentInfoService) {
		this.recruitmentInfoService = recruitmentInfoService;
	}

	private RecruitmentUnitService recruitmentUnitService;
	@Resource(name = "recruitmentUnitService")
	public void setRecruitmentUnitService(
			RecruitmentUnitService recruitmentUnitService) {
		this.recruitmentUnitService = recruitmentUnitService;
	}

	// 接收从exportRecInfos.jsp传来的参数
	// 选择的公司单位
	private String recUnitSelected;
	// 发布时间
	private String timeSelected;
	public String getRecUnitSelected() {
		return recUnitSelected;
	}
	public void setRecUnitSelected(String recUnitSelected) {
		this.recUnitSelected = recUnitSelected;
	}
	public String getTimeSelected() {
		return timeSelected;
	}
	public void setTimeSelected(String timeSelected) {
		this.timeSelected = timeSelected;
	}

	// 下载文件需要的
	private String fileName; // 初始通过param指定的文件名属性
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private InputStream is;

	/**
	 * 进入导出招聘信息页面
	 * 
	 * @return
	 */
	public String toExportRecInfosPage() {
		// 查询所有公司
		request.setAttribute("recUnits",
				recruitmentUnitService.findAllRecruitmentUnits());
		return "exportRecInfosPage";
	}

	public InputStream getWordStream() throws Exception {
		System.out.println("测试数据（公司id和发布时间id）：     " + recUnitSelected + "    "
				+ timeSelected);
		int recUnitId, timeId;
		if (!recUnitSelected.equals("---请选择公司---")) {
			if (!timeSelected.equals("---选择发布时间---")) {
				recUnitId = Integer.parseInt(recUnitSelected);
				timeId = Integer.parseInt(timeSelected);
			} else {
				recUnitId = Integer.parseInt(recUnitSelected);
				timeId = 0;
			}
		} else {
			if (!timeSelected.equals("---选择发布时间---")) {
				recUnitId = 0;
				timeId = Integer.parseInt(timeSelected);
			} else {
				recUnitId = 0;
				timeId = 0;
			}
		}

		// 根据公司和发布时间来查找其公司的所有招聘信息
		List<RecruitmentInfo> recruitmentInfos = recruitmentInfoService
				.getAllRecruitmentInfos(recUnitId, timeId);
		//使用iText 开始创建word文档
		Document doc = new Document(PageSize.A4);
		// 新建字节数组输出流
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 建立一个书写器与document对象关联，通过书写器可以将文档写入到输出流中
		RtfWriter2.getInstance(doc, baos);
		doc.open();
		// 标题字体
		RtfFont titleFont = new RtfFont("仿宋_GB2312", 14, Font.NORMAL,
				Color.BLACK);
		// 正文字体
		RtfFont contextFont = new RtfFont("仿宋_GB2312", 11, Font.NORMAL,
				Color.BLACK);
		
		// 第一行（标题）
		String titleString ="";
		if (recruitmentInfos != null && recruitmentInfos.size() > 0){
			titleString= "---- 所有招聘信息   ----"+"\n";
		}else{
			titleString="该公司在指定时间段内没有发布任何招聘信息";
		}
        Paragraph title = new Paragraph(titleString);
        // 设置标题格式对其方式
        title.setAlignment(Element.ALIGN_CENTER);
        title.setFont(titleFont);
        doc.add(title);
		
		if (recruitmentInfos != null && recruitmentInfos.size() > 0) {
			// 查询到有招聘信息
//			System.out.println("大小为：  " + recruitmentInfos.size());
			//模型转换
			List<PageRecruitmentInfo> pageRecInfos=recruitmentInfoService.changeToPageModel(recruitmentInfos);
			for (PageRecruitmentInfo pageInfo : pageRecInfos) {
//				System.out.println("招聘职位：   " + pageInfo.getPosition());
				//招聘信息
				String contextString="招聘职位：  "+pageInfo.getPosition()+"                     月薪：  "+pageInfo.getMonthlySalary()+"\n"
						+"工作性质：  "+pageInfo.getWorkType()+"                     	 学历要求：  "+pageInfo.getEducationType()+"\n"
						+"招聘人数：  "+pageInfo.getHireCount()+"\n"
						+"发布时间：  "+pageInfo.getReleaseTime()+"\n"
						+"截止时间：  "+pageInfo.getEndTime()+"\n"
						+"所属行业：  "+pageInfo.getIndustryType()+"\n"
						+"职位描述：  "+"\n"
						+str_changebr(pageInfo.getPositionDescription())+"\n"+"\n";
				Paragraph context = new Paragraph(contextString);
		        // 正文格式对齐方式
		        context.setAlignment(Element.ALIGN_LEFT);
		        context.setFont(contextFont);
		        // 与上一段落（标题）的行距
		        context.setSpacingBefore(10);
		        // 设置第一行空的列数（缩进）
		        // context.setFirstLineIndent(20);
		        doc.add(context);
			}
		}
		
		doc.close();
		byte[] fileContent = baos.toByteArray();
		is = new ByteArrayInputStream(fileContent);
		baos.flush();
		baos.close();
		return is;
	}

	public String str_changenbsp(String str) {
		if (str != null) {
			return str.replaceAll("&nbsp;", "");
		} else {
			return "";
		}
	}

	public String str_changebr(String str) {
		if (str != null) {
			return str.replaceAll("<br>", "\n");
		} else {
			return "";
		}
	}

	/**
	 * 开始导出招聘信息信息
	 * 
	 * @throws IOException
	 */
	public String exportRecInfos() throws IOException {
		return "success";
	}

	// 提供转换编码后的供下载用的文件名  
	public String getDownloadFileName() {
		String downFileName = fileName;
//		System.out.println("downFileName   :" + downFileName);
		// 解决浏览器下载文件时出现的乱码问题
		String Agent = request.getHeader("User-Agent");
		if (Agent != null) {
			Agent = Agent.toLowerCase();
			try {
				if (Agent.indexOf("firefox") != -1) {
					// 火狐
					downFileName = new String(downFileName.getBytes(),
							"ISO8859-1");
				} else if (Agent.indexOf("msie") != -1) {
					// ie
					downFileName = java.net.URLEncoder.encode(downFileName,
							"UTF-8");
				} else {
					// 其他（谷歌，苹果等）
					downFileName = java.net.URLEncoder.encode(downFileName,
							"UTF-8");
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		System.out.println("downFileName  after  :" + downFileName);
		return downFileName;
	}

}
