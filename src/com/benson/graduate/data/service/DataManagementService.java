package com.benson.graduate.data.service;

import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.benson.graduate.base.service.BaseService;
import com.benson.graduate.stu.model.GraduateInfo;
import com.benson.graduate.stu.model.Student;

public interface DataManagementService extends BaseService {
	
	/**
	 * 通过excel上传学生源信息
	 * @param fileInputStream
	 * @param format
	 * @return
	 */
	public String toUploadStuInfoByExcel(InputStream fileInputStream,
			String format);
	
	/**
	 * 通过excel上传学生就业信息
	 * @param fileInputStream
	 * @param format
	 * @return
	 */
	public String toUploadGraByExcel(InputStream fileInputStream,
			String format);
	
	/**
	 * 根据查询结果StudentList ， 来创建一个Excel表，并返回（导出已经审核的源信息）
	 * @param students ,type
	 * @return
	 */
	public HSSFWorkbook createHSSFWorkbookByStusList(List<Student> students,int type);
	
	/**
	 * 根据查询结果StudentList,来创建一个Excel表，并返回（导出学生信息，包括源信息）
	 *  * @param students ,type
	 * @return
	 */
	public HSSFWorkbook createHSSFWorkbookByStusList2(List<Student> students,int type);
	
	
	/**
	 * 根据查询结果GraduateInfoList ， 来创建一个Excel表，并返回
	 * @param graduateInfos ,type
	 * @return
	 */
	public HSSFWorkbook createHSSFWorkbookByGraInfosList(List<GraduateInfo> graduateInfos,int type);
	
}
