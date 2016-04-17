package com.benson.graduate.data.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.benson.graduate.base.service.impl.BaseServiceImpl;
import com.benson.graduate.common.enumeration.EnumerationType;
import com.benson.graduate.data.service.DataManagementService;
import com.benson.graduate.stu.dao.ClazzDao;
import com.benson.graduate.stu.dao.GraduateInfoDao;
import com.benson.graduate.stu.dao.MajorFieldDao;
import com.benson.graduate.stu.dao.StudentDao;
import com.benson.graduate.stu.dao.StudentInfoDao;
import com.benson.graduate.stu.model.Clazz;
import com.benson.graduate.stu.model.GraduateInfo;
import com.benson.graduate.stu.model.MajorField;
import com.benson.graduate.stu.model.Student;
import com.benson.graduate.stu.model.StudentInfo;
import com.benson.graduate.stu.pagemodel.PageGraduateInfo;
import com.benson.graduate.stu.pagemodel.PageStudent;
import com.benson.graduate.stu.pagemodel.PageStudentInfo;
import com.benson.graduate.sys.dao.EnumerationValueDao;
import com.benson.graduate.sys.model.EnumerationValue;

@Service("dataManagementService")
public class DataManagementServiceImpl extends BaseServiceImpl implements
		DataManagementService {

	private StudentInfoDao  studentInfoDao;
	@Resource(name="studentInfoDao")
	public void setStudentInfoDao(StudentInfoDao studentInfoDao) {
		this.studentInfoDao = studentInfoDao;
	}
	private EnumerationValueDao enumerationValueDao;
	@Resource(name="enumerationValueDao")
	public void setEnumerationValueDao(EnumerationValueDao enumerationValueDao) {
		this.enumerationValueDao = enumerationValueDao;
	}
	private MajorFieldDao majorFieldDao;
	@Resource(name="majorFieldDao")
	public void setMajorFieldDao(MajorFieldDao majorFieldDao) {
		this.majorFieldDao = majorFieldDao;
	}
	private ClazzDao clazzDao;
	@Resource(name="clazzDao")
	public void setClazzDao(ClazzDao clazzDao) {
		this.clazzDao = clazzDao;
	}
	private StudentDao studentDao;
	@Resource(name="studentDao")
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	private GraduateInfoDao graduateInfoDao;
	@Resource(name="graduateInfoDao")
	public void setGraduateInfoDao(GraduateInfoDao graduateInfoDao) {
		this.graduateInfoDao = graduateInfoDao;
	}

	@Override
	public String toUploadGraByExcel(InputStream fileInputStream, String format) {
		Workbook book = null;
		try {
			if(format.equals("xls")){
				System.out.println("xls");
				book=new HSSFWorkbook(fileInputStream);	
			}else if(format.equals("xlsx")){
				System.out.println("xlsx");
				book=new XSSFWorkbook(fileInputStream);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Book的大小为："+book.getNumberOfSheets()+"    ....................");
		//循环获取Sheet
		Sheet sheet;
		//保存所有通过检验的学生就业信息
		List<GraduateInfo> graduateInfos=new ArrayList<GraduateInfo>();
		for(int i=0,sheetSize=book.getNumberOfSheets();i<sheetSize;i++){
			System.out.println("--------------第"+(i+1)+"张Sheet--------------");
			sheet=book.getSheetAt(i);
			//Sheet的名字
			System.out.println("Sheet名字为："+sheet.getSheetName()+"     ::::::::");
			Iterator<Row> rows=sheet.rowIterator();
			//先取第一行
			Row titleRow=rows.next();
			if(!isGraExcelTitleValidate(titleRow)){
				//excel第一行title行格式有错，返回错误代码-1
				return -1+",第"+(i+1)+"张sheet的标题有错，请核对模板后再上传...";
			}
			
			//计数器
			int count=0;
			//接着按照字段来取每一个单元格
			while(rows.hasNext()){
				count++;
				System.out.println("-------------1行-------------");
				Row row =rows.next();
				//Iterator<Cell> cells=row.cellIterator();
				
				Integer unitType=null,applyType=null;
				String code="",name="",unitName="",unitLocation="",unitTypeStr="",unitIndustry="",unitContantPerson="",unitContantPhone="",
						graduatePhone="",applyTypeStr="",unitPostEncoding="",unitAddress="";
				
				//字段数据有错，统一返回错误代码-2
				//学号  String
				Cell cell1=row.getCell(0);
				if(cell1!=null){
					if(cell1.getCellType()==Cell.CELL_TYPE_STRING){
						code=cell1.getStringCellValue().trim();
						System.out.println(code);
					}else if(cell1.getCellType()==Cell.CELL_TYPE_NUMERIC){
						DecimalFormat df = new DecimalFormat("0");  
						code = df.format(cell1.getNumericCellValue());
						System.out.println(code);
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-学号数据有错，请检查后重新上传...";
					}
				}else{
					return -3+",第"+(i+1)+"张sheet-第"+count+"行-学号字段不能为空，请检查后重新上传...";
				}
				
				//姓名 String
				Cell cell2=row.getCell(1); 
				if(cell2!=null){
					if(cell2.getCellType()==Cell.CELL_TYPE_STRING){
						name=cell2.getStringCellValue().trim();
						System.out.println(name);
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-姓名数据有错，请检查后重新上传...";
					}
				}else{
					return -3+",第"+(i+1)+"张sheet-第"+count+"行-姓名字段不能为空，请检查后重新上传...";
				}
				//判断学号是否存在，判断学号与该学生姓名是否匹配
				Student stu=this.getStudentByCode(code);
				if(stu!=null){
					//判断学号与该学生姓名是否匹配
					if(!stu.getName().equals(name)){
						//不匹配，返回错误代码-3
						return -3+",第"+(i+1)+"张sheet-第"+count+"行-该学号的学生姓名与该字段姓名不相同，请检查姓名字段后重新上传...";
					}
				}else{
					//学生信息不存在，返回错误代码-3
					return -3+",第"+(i+1)+"张sheet-第"+count+"行-该学号的学生不存在，请检查学号后重新上传...";
				}
				//判断该学生的就业信息是否存在
				if(isGraduateInfoExit(code)){
					return -3+",第"+(i+1)+"张sheet-第"+count+"行-该学号学生的就业信息已经存在，请检查后重新上传...";
				}else{
					//看看临时列表中是否存在
					if(graduateInfos!=null&&graduateInfos.size()>0){
						for(GraduateInfo gra: graduateInfos){
							if(gra.getStudent().getCode().equals(code)){
								return -3+",第"+(i+1)+"张sheet-第"+count+"行-该学号在excel中已经重复存在，请检查后重新上传...";
							}
						}
					}
				}
				
				//就业单位名称 String
				Cell cell3=row.getCell(2);
				if(cell3!=null){
					if(cell3.getCellType()==Cell.CELL_TYPE_STRING){
						System.out.println("cell3");
						unitName=cell3.getStringCellValue().trim();
						System.out.println(unitName);
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-就业单位名称数据有错，请检查后重新上传...";
					}
				}else{
					//就业单位名称字段不能为null
					return -3+",第"+(i+1)+"张sheet-第"+count+"行-就业单位名称字段不能为空，请检查后重新上传...";
				}
				
				//就业单位所在地 String
				Cell cell4=row.getCell(3);
				if(cell4!=null){
					if(cell4.getCellType()==Cell.CELL_TYPE_STRING){
						unitLocation=cell4.getStringCellValue().trim();
						System.out.println(unitLocation);
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-就业单位名称数据有错，请检查后重新上传...";
					}
				}
				
				//单位性质
				//得到所有枚举值
				List<EnumerationValue> values=enumerationValueDao.getEnumerationValueByEnumerationName(EnumerationType.UNIT_TYPE);
				Cell cell5=row.getCell(4);
				if(cell5!=null){
					if(cell5.getCellType()==Cell.CELL_TYPE_STRING){
						unitTypeStr=cell5.getStringCellValue().trim();
						for(EnumerationValue val:values){
							if(unitTypeStr.equals(val.getName())){
								unitType=val.getId();
							}
						}
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-单位性质项数据有错，请检查后重新上传...";
					}
				}
				
				//就业单位所属行业
				Cell cell6=row.getCell(5);
				if(cell6!=null){
					if(cell6.getCellType()==Cell.CELL_TYPE_STRING){
						unitIndustry=cell6.getStringCellValue().trim();
						System.out.println(unitIndustry);
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-就业单位所属行业项数据有错，请检查后重新上传...";
					}
				}
				
				//单位联系人
				Cell cell7=row.getCell(6);
				if(cell7!=null){
					if(cell7.getCellType()==Cell.CELL_TYPE_STRING){
						unitContantPerson=cell7.getStringCellValue().trim();
						System.out.println(unitContantPerson);
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-单位联系人项数据有错，请检查后重新上传...";
					}
				}
				
				//单位联系电话
				Cell cell8=row.getCell(7);
				if(cell8!=null){
					if(cell8.getCellType()==Cell.CELL_TYPE_STRING){
						unitContantPhone=cell8.getStringCellValue().trim();
						System.out.println(unitContantPhone);
					}else if(cell8.getCellType()==Cell.CELL_TYPE_NUMERIC){
						DecimalFormat df = new DecimalFormat("0");  
						unitContantPhone = df.format(cell8.getNumericCellValue());
						System.out.println(unitContantPhone);
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-单位联系电话项数据有错，请检查后重新上传...";
					}
				}
				
				//毕业生联系方式
				Cell cell9=row.getCell(8);
				if(cell9!=null){
					if(cell9.getCellType()==Cell.CELL_TYPE_STRING){
						graduatePhone=cell9.getStringCellValue().trim();
						System.out.println(graduatePhone);
					}else if(cell9.getCellType()==Cell.CELL_TYPE_NUMERIC){
						DecimalFormat df = new DecimalFormat("0");  
						graduatePhone = df.format(cell9.getNumericCellValue());
						System.out.println(graduatePhone);
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-毕业生联系方式项数据有错，请检查后重新上传...";
					}
				}
				
				//应聘方式
				values=enumerationValueDao.getEnumerationValueByEnumerationName(EnumerationType.APPLY_RECRUITMENT_TYPE);
				Cell cell10=row.getCell(9);
				if(cell10!=null){
					if(cell10.getCellType()==Cell.CELL_TYPE_STRING){
						applyTypeStr=cell10.getStringCellValue().trim();
						for(EnumerationValue val:values){
							if(applyTypeStr.equals(val.getName())){
								applyType=val.getId();
							}
						}
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-应聘方式项数据有错，请检查后重新上传...";
					}
				}
				
				//单位邮政编码
				Cell cell11=row.getCell(10);
				if(cell11!=null){
					if(cell11.getCellType()==Cell.CELL_TYPE_STRING){
						unitPostEncoding=cell11.getStringCellValue().trim();
						System.out.println(unitPostEncoding);
					}else if(cell8.getCellType()==Cell.CELL_TYPE_NUMERIC){
						DecimalFormat df = new DecimalFormat("0");  
						unitPostEncoding = df.format(cell11.getNumericCellValue());
						System.out.println(unitPostEncoding);
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-单位邮政编码项数据有错，请检查后重新上传...";
					}
				}
				
				//单位详细地址
				Cell cell12=row.getCell(11);
				if(cell12!=null){
					if(cell12.getCellType()==Cell.CELL_TYPE_STRING){
						unitAddress=cell12.getStringCellValue().trim();
						System.out.println(unitAddress);
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-单位详细地址数据有错，请检查后重新上传...";
					}
				}
				
				GraduateInfo graduateInfo=new GraduateInfo();
				graduateInfo.setName(name);
				graduateInfo.setUnitName(unitName);
				graduateInfo.setUnitLocation(unitLocation);
				graduateInfo.setUnitType(unitType);
				graduateInfo.setUnitIndustry(unitIndustry);
				graduateInfo.setUnitContantPerson(unitContantPerson);
				graduateInfo.setUnitContantPhone(unitContantPhone);
				graduateInfo.setGraduatePhone(graduatePhone);
				graduateInfo.setApplyType(applyType);
				graduateInfo.setUnitPostEncoding(unitPostEncoding);
				graduateInfo.setUnitAddress(unitAddress);
				graduateInfo.setStudent(stu);
				graduateInfo.setDepartmentGAduitStatus(10001);
				graduateInfo.setSchoolGAduitStatus(10001);
				
				//添加到临时List中
				graduateInfos.add(graduateInfo);
			}
		}
		//批量添加就业信息
		boolean result=graduateInfoDao.addGraduateInfosByList(graduateInfos);
		if(result==true){
			//添加成功返回操作代码1
			return 1+",批量添加（上传）成功";
		}else{
			//添加失败返回错误代码0
			return 0+",批量添加（上传）失败";
		}
	}

	@Override
	public String toUploadStuInfoByExcel(InputStream fileInputStream, String format) {
		// TODO Auto-generated method stub
		Workbook book = null;
		try {
			if(format.equals("xls")){
				System.out.println("xls");
				book=new HSSFWorkbook(fileInputStream);	
			}else if(format.equals("xlsx")){
				System.out.println("xlsx");
				book=new XSSFWorkbook(fileInputStream);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Book的大小为："+book.getNumberOfSheets()+"    ....................");
		//循环获取Sheet
		Sheet sheet;
		//保存所有通过验证的学生源信息，用于批量添加到数据库
		List<StudentInfo> stuInfos=new ArrayList<StudentInfo>();
		//保存所有通过验证的学生信息，用于批量添加到数据库
		List<Student> stus=new ArrayList<Student>();
		
		for(int i=0,sheetSize=book.getNumberOfSheets();i<sheetSize;i++){
			System.out.println("--------------第"+(i+1)+"张Sheet--------------");
			sheet=book.getSheetAt(i);
			System.out.println("Sheet名字为："+sheet.getSheetName()+"     ::::::::");
			
			//Sheet的名字为专业名
			String majorFieldName=sheet.getSheetName();
			System.out.println("专业名为：       "+majorFieldName);
			//先判断专业是否存在
			MajorField major=findMajorFieldByName(majorFieldName);
			if(major==null){
				//专业名字不存在，返回-4错误代码
				return -4+",第"+(i+1)+"张sheet的名字（专业名字）不存在，请检查专业名字后再上传...";
			}
			
			Iterator<Row> rows=sheet.rowIterator();
			//先取第一行
			Row titleRow=rows.next();
			if(!isExcelTitleValidate(titleRow)){
				//excel第一行title行格式有错，返回错误代码-1
				return -1+",第"+(i+1)+"张sheet的标题有错，请核对模板后再上传...";
			}
			System.out.println();
			
			//计数器
			int count=0;
			//接着按照字段来取每一个单元格
			while(rows.hasNext()){
				count++;
				System.out.println("-------------1行-------------");
				Row row =rows.next();
				
				Date birthday=null,enterCollegeTime=null,graduateTime=null;
				String examNum="",name="",graduationSchool="",sex="",idCard="",nation="",politicalFeature="",nativePlace="",household="",householdPlace="",schoolLength="",
				education="",phone="",postEncoding="",code="",schoolStatusStr="",clazzName="";
				Integer sexType=null,nationType=null,politicalFeatureType=null,householdType=null,schoolLengthType=null,educationType=null,schoolStatus=null;

				//字段数据有错，统一返回错误代码-2
				//准考证号  String
				Cell cell1=row.getCell(0);
				if(cell1!=null){
					if(cell1.getCellType()==Cell.CELL_TYPE_STRING){
						examNum=cell1.getStringCellValue().trim();
						System.out.println(examNum);
					}else if(cell1.getCellType()==Cell.CELL_TYPE_NUMERIC){
						DecimalFormat df = new DecimalFormat("0");  
						examNum = df.format(cell1.getNumericCellValue());
						System.out.println(cell1);
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-准考证号数据有错，请检查后重新上传...";
					}
				}else{
					//准考证号不能为空
					return -3+",第"+(i+1)+"张sheet-第"+count+"行-准考证字段不能为空，请检查后重新上传...";
				}
				//判断准考证号是否存在
				if(isStudentInfoExit(examNum)){
					//学生源信息存在，返回错误代码-3
					return -3+",第"+(i+1)+"张sheet-第"+count+"行-准考证号已经存在，请检查准考证号后重新上传...";
				}else{
					//在sheet中是否存在
					if(stuInfos!=null&&stuInfos.size()>0){
						for(StudentInfo s:stuInfos){
							if(s.getExamNum().equals(examNum)){
								return -3+",第"+(i+1)+"张sheet-第"+count+"行-准考证字段在excel表中已经重复存在，请检查后重新上传...";
							}
						}
					}
				}
				
				//姓名 String
				Cell cell2=row.getCell(1);
				if(cell2!=null){
					if(cell2.getCellType()==Cell.CELL_TYPE_STRING){
						name=cell2.getStringCellValue().trim();
						System.out.println(name);
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-姓名数据有错，请检查后重新上传...";
					}
				}else{
					return -3+",第"+(i+1)+"张sheet-第"+count+"行-姓名字段不能为空，请检查后重新上传...";
				}
				
				//毕业学校 String
				Cell cell3=row.getCell(2);
				if(cell3!=null){
					if(cell3.getCellType()==Cell.CELL_TYPE_STRING){
						graduationSchool=cell3.getStringCellValue().trim();
						System.out.println(graduationSchool);
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-毕业学校数据有错，请检查后重新上传...";
					}
				}
				
				//性别类型
				//得到所有枚举值
				List<EnumerationValue> values=enumerationValueDao.getEnumerationValueByEnumerationName(EnumerationType.SEX_TYPE);
				Cell cell4=row.getCell(3); 
				if(cell4!=null){
					if(cell4.getCellType()==Cell.CELL_TYPE_STRING){
						sex=cell4.getStringCellValue().trim();
						for(EnumerationValue val:values){
							if(sex.equals(val.getName())){
								sexType=val.getId();
							}
						}
						//为null，输入不合法
//						if(sexType==null){
//							return -2+",第"+(i+1)+"张sheet-第"+count+"行-性别项数据有错，请检查后重新上传...";
//						}
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-性别项数据有错，请检查后重新上传...";
					}
				}
				
				//出生日期
				Cell cell5=row.getCell(4); 
				if(cell5!=null){
					if(cell5.getCellType()==Cell.CELL_TYPE_NUMERIC){
						if(DateUtil.isCellDateFormatted(cell5)&&cell5.getDateCellValue()!=null){
							birthday=cell5.getDateCellValue();
							System.out.println(birthday);
						}
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-出生年月项数据有错，请检查后重新上传...";
					}
				}else{
					return -3+",第"+(i+1)+"张sheet-第"+count+"行-出生日期字段不能为空，请检查后重新上传...";
				}
				
				//身份证号 String
				Cell cell6=row.getCell(5);
				if(cell6!=null){
					if(cell6.getCellType()==Cell.CELL_TYPE_STRING){
						idCard=cell6.getStringCellValue();
						System.out.println(idCard);
					}else if(cell6.getCellType()==Cell.CELL_TYPE_NUMERIC){
						DecimalFormat df = new DecimalFormat("0");  
						idCard= df.format(cell6.getNumericCellValue());
						System.out.println(idCard);
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-身份证号项数据有错，请检查后重新上传...";
					}
				}else{
					return -3+",第"+(i+1)+"张sheet-第"+count+"行-身份证字段不能为空，请检查后重新上传...";
				}
				//判断身份证号是否存在
				if(findStudentInfoByIdCard(idCard)){
					//学生源信息存在，返回错误代码-3
					return -3+",第"+(i+1)+"张sheet-第"+count+"行-身份证号已经存在，请检查身份证号后重新上传...";
				}else{
					//判断在sheet表中是否存在
					if(stuInfos!=null&&stuInfos.size()>0){
						for(StudentInfo s:stuInfos){
							if(s.getIdCard().equals(idCard)){
								return -3+",第"+(i+1)+"张sheet-第"+count+"行-身份证字段在excel表中已经重复存在，请检查后重新上传...";
							}
						}
					}
				}
				
				//民族类型
				values=enumerationValueDao.getEnumerationValueByEnumerationName(EnumerationType.NATION_TYPE);
				Cell cell7=row.getCell(6);
				if(cell7!=null){
					if(cell7.getCellType()==Cell.CELL_TYPE_STRING){
						nation=cell7.getStringCellValue().trim();
						for(EnumerationValue val:values){
							if(nation.equals(val.getName())){
								nationType=val.getId();
							}
						}
						//为null，输入不合法
//						if(nationType==null){
//							return -2+",第"+(i+1)+"张sheet-第"+count+"行-民族项数据有错，请检查后重新上传...";
//						}
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-民族项数据有错，请检查后重新上传...";
					}
				}
				
				//政治面貌类型
				values=enumerationValueDao.getEnumerationValueByEnumerationName(EnumerationType.POLITICAL_FEATURE_TYPE);
				Cell cell8=row.getCell(7);
				if(cell8!=null){
					if(cell8.getCellType()==Cell.CELL_TYPE_STRING){
						politicalFeature=cell8.getStringCellValue().trim();
						for(EnumerationValue val:values){
							if(politicalFeature.equals(val.getName())){
								politicalFeatureType=val.getId();
							}
						}
						//为null，输入不合法
//						if(politicalFeatureType==null){
//							return -2+",第"+(i+1)+"张sheet-第"+count+"行-政治面貌项数据有错，请检查后重新上传...";
//						}
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-政治面貌项数据有错，请检查后重新上传...";
					}
				}
				
				//籍贯
				Cell cell9=row.getCell(8); 
				if(cell9!=null){
					if(cell9.getCellType()==Cell.CELL_TYPE_STRING){
						nativePlace=cell9.getStringCellValue();
						System.out.println(nativePlace);
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-籍贯项项数据有错，请检查后重新上传...";
					}
				}
				
				//户籍类型
				values=enumerationValueDao.getEnumerationValueByEnumerationName(EnumerationType.HOUSEHOLD_TYPE);
				Cell cell10=row.getCell(9);
				if(cell10!=null){
					if(cell10.getCellType()==Cell.CELL_TYPE_STRING){
						household=cell10.getStringCellValue().trim();
						for(EnumerationValue val:values){
							if(household.equals(val.getName())){
								householdType=val.getId();
							}
						}
						//为null，输入不合法
//						if(householdType==null){
//							return -2+",第"+(i+1)+"张sheet-第"+count+"行-户籍项数据有错，请检查后重新上传...";
//						}
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-户籍项数据有错，请检查后重新上传...";
					}
				}
				
				//户口所在地 String
				Cell cell11=row.getCell(10);
				if(cell11!=null){
					if(cell11.getCellType()==Cell.CELL_TYPE_STRING){
						householdPlace=cell11.getStringCellValue();
						System.out.println(householdPlace);
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-户口所在地项数据有错，请检查后重新上传...";
					}
				}
				
				//学制类型
				values=enumerationValueDao.getEnumerationValueByEnumerationName(EnumerationType.SCHOOL_LENGTH_TYPE);
				Cell cell12=row.getCell(11);
				if(cell12!=null){
					if(cell12.getCellType()==Cell.CELL_TYPE_STRING){
						schoolLength=cell12.getStringCellValue().trim();
						for(EnumerationValue val:values){
							if(schoolLength.equals(val.getName())){
								schoolLengthType=val.getId();
							}
						}
						//为null，输入不合法
						if(schoolLengthType==null){
							return -2+",第"+(i+1)+"张sheet-第"+count+"行-学制项数据有错，请检查后重新上传...";
						}
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-学制项数据有错，请检查后重新上传...";
					}
				}
				
				//学历类型
				values=enumerationValueDao.getEnumerationValueByEnumerationName(EnumerationType.EDUCATION_TYPE);
				Cell cell13=row.getCell(12); 
				if(cell13!=null){
					if(cell13.getCellType()==Cell.CELL_TYPE_STRING){
						education=cell13.getStringCellValue().trim();
						for(EnumerationValue val:values){
							if(education.equals(val.getName())){
								educationType=val.getId();
							}
						}
						//为null，输入不合法
//						if(educationType==null){
//							return -2+",第"+(i+1)+"张sheet-第"+count+"行-学历项数据有错，请检查后重新上传...";
//						}
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-学历项数据有错，请检查后重新上传...";
					}
				}
				
				//联系方式 String
				Cell cell14=row.getCell(13);
				if(cell14!=null){
					if(cell14.getCellType()==Cell.CELL_TYPE_STRING){
						phone=cell14.getStringCellValue();
						System.out.println(phone);
					}else if(cell14.getCellType()==Cell.CELL_TYPE_NUMERIC){
						DecimalFormat df = new DecimalFormat("0");  
						phone= df.format(cell14.getNumericCellValue());
						System.out.println(phone);
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-联系方式项数据有错，请检查后重新上传...";
					}
				}
				
				//入学年份
				Cell cell15=row.getCell(14); 
				if(cell15!=null){
					if(cell15.getCellType()==Cell.CELL_TYPE_NUMERIC){
						if(DateUtil.isCellDateFormatted(cell15)&&cell15.getDateCellValue()!=null){
							enterCollegeTime=cell15.getDateCellValue();
							System.out.println(enterCollegeTime);
						}
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-入学年份项数据有错，请检查后重新上传...";
					}
				}
				
				//毕业时间
				Cell cell16=row.getCell(15);
				if(cell16!=null){
					if(cell16.getCellType()==Cell.CELL_TYPE_NUMERIC){
						if(DateUtil.isCellDateFormatted(cell16)&&cell16.getDateCellValue()!=null){
							graduateTime=cell16.getDateCellValue();
							System.out.println(graduateTime);
						}
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-毕业时间项数据有错，请检查后重新上传...";
					}
				}
				
				//邮政编码String
				Cell cell17=row.getCell(16); 
				if(cell17!=null){
					if(cell17.getCellType()==Cell.CELL_TYPE_STRING){
						postEncoding=cell17.getStringCellValue();
						System.out.println(postEncoding);
					}else if(cell17.getCellType()==Cell.CELL_TYPE_NUMERIC){
						DecimalFormat df = new DecimalFormat("0");  
						postEncoding= df.format(cell17.getNumericCellValue());
						System.out.println(postEncoding);
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-邮政编码项数据有错，请检查后重新上传...";
					}
				}
				
				//学号  String
				Cell cell18=row.getCell(17);
				if(cell18!=null){
					if(cell18.getCellType()==Cell.CELL_TYPE_STRING){
						code=cell18.getStringCellValue().trim();
						System.out.println(code);
					}else if(cell18.getCellType()==Cell.CELL_TYPE_NUMERIC){
						DecimalFormat df = new DecimalFormat("0");  
						code = df.format(cell18.getNumericCellValue());
						System.out.println(code);
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-学号数据有错，请检查后重新上传...";
					}
				}
				//判断学号是否存在
				if(isCodeExit(code)){
					return -3+",第"+(i+1)+"张sheet-第"+count+"行-已存在该学号的学生，请检查学号后重新上传...";
				}else{
					//判断sheet表中是否有
					if(stus!=null&&stus.size()>0){
						for(Student s: stus){
							if(s.getCode().equals(code)){
								return -3+",第"+(i+1)+"张sheet-第"+count+"行-该学号在excel表中已重复存在，请检查后重新上传...";
							}
						}
					}
				}
				
				//学籍状态
				//得到所有枚举值
				values=enumerationValueDao.getEnumerationValueByEnumerationName(EnumerationType.STU_STATUS);
				Cell cell19=row.getCell(18); 
				if(cell19!=null){
					if(cell19.getCellType()==Cell.CELL_TYPE_STRING){
						schoolStatusStr=cell19.getStringCellValue().trim();
						for(EnumerationValue val:values){
							if(schoolStatusStr.equals(val.getName())){
								schoolStatus=val.getId();
							}
						}
						//为null，输入不合法
//						if(schoolStatus==null){
//							return -2+",第"+(i+1)+"张sheet-第"+count+"行-学籍状态项数据有错，请检查后重新上传...";
//						}
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-学籍状态项数据有错，请检查后重新上传...";
					}
				}
				
				//班别  String
				Cell cell20=row.getCell(19);
				if(cell20!=null){
					if(cell20.getCellType()==Cell.CELL_TYPE_STRING){
						clazzName=cell20.getStringCellValue().trim();
						System.out.println(clazzName);
					}else if(cell20.getCellType()==Cell.CELL_TYPE_NUMERIC){
						DecimalFormat df = new DecimalFormat("0");  
						clazzName = df.format(cell20.getNumericCellValue());
						System.out.println(clazzName);
					}else{
						return -2+",第"+(i+1)+"张sheet-第"+count+"行-班别数据有错，请检查后重新上传...";
					}
				}
				//先判断班别是否存在
				Clazz clazz=findClazzByName(clazzName);
				if(clazz==null){
					//班别名字不存在，返回-2错误代码
					return -2+",第"+(i+1)+"张sheet-第"+count+"行-班别数据有错（不存在），请检查后重新上传...";
				}
				if(!clazz.getMajorField().getName().equals(majorFieldName)){
					//填写的班级与前面所报专业不对应
					return -2+",第"+(i+1)+"张sheet-第"+count+"行-班别数据有错（所报专业中没有该班级），请检查后重新上传...";
				}
				
				//经过上面的逻辑判断之后，可以添加到一个List里面保存
				StudentInfo info=new StudentInfo();
				info.setExamNum(examNum);
				//excell导入  全部已通过审核
				info.setDepartmentAduitStatus(10003);
				info.setSchoolAduitStatus(10003);
				info.setName(name);
				info.setGraduationSchool(graduationSchool);
				info.setSexType(sexType);
				info.setMajorField(major);
				info.setBirthday(birthday);
				info.setIdCard(idCard);
				info.setNationType(nationType);
				info.setPoliticalFeatureType(politicalFeatureType);
				info.setNativePlace(nativePlace);
				info.setHouseholdType(householdType);
				info.setHouseholdPlace(householdPlace);
				info.setSchoolLengthType(schoolLengthType);
				info.setEducationType(educationType);
				info.setPhone(phone);
				info.setEnterCollegeTime(enterCollegeTime);
				info.setGraduateTime(graduateTime);
				info.setPostEncoding(postEncoding);
				
				Student student=new Student();
				student.setName(name);
				student.setClazz(clazz);
				student.setCode(code);
				student.setDepartment(major.getDepartment());
				student.setSchoolStatus(schoolStatus);
				student.setStudentInfo(info);
				
				//添加到list中
				stuInfos.add(info);
				stus.add(student);
				System.out.println("list  大小为                                                     :"+stuInfos.size()+"   "+stus.size());
			}
		}
		//批量添加
		boolean result=studentInfoDao.addStudenInfosByList(stuInfos);
		if(result==true){
			boolean res=studentDao.addStudensByList(stus);
			if(res==true){
				//添加成功返回操作代码1
				return 1+",批量添加（上传）成功";
			}else{
				//添加失败返回错误代码0
				return 0+",批量添加（上传）失败";
			}
		}else{
			//添加失败返回错误代码0
			return 0+",批量添加失败";
		}
	}

	/**
	 * 验证excel表每张sheet的标题栏是否有错（学生源信息）
	 * @param titleRow
	 * @return
	 */
	public boolean isExcelTitleValidate(Row titleRow) {
		// TODO Auto-generated method stub
		System.out.println("开始验证标题");
		Iterator<Cell> titleCells=titleRow.cellIterator();
		//判断标题行的每一个单元是否符合格式要求
		Cell cell1=titleCells.next();
		if(cell1.getStringCellValue().trim().equals("")||!cell1.getStringCellValue().trim().equals("准考证号")){
			System.out.println(cell1.getStringCellValue().trim());
			return false;
		}
		Cell cell2=titleCells.next();
		if(cell2.getStringCellValue().trim().equals("")||!cell2.getStringCellValue().trim().equals("姓名")){
			System.out.println(cell2.getStringCellValue().trim());
			return false;
		}
		Cell cell3=titleCells.next();
		if(cell3.getStringCellValue().trim().equals("")||!cell3.getStringCellValue().trim().equals("毕业学校")){
			System.out.println(cell3.getStringCellValue().trim());
			return false;
		}
		Cell cell4=titleCells.next();
		if(cell4.getStringCellValue().trim().equals("")||!cell4.getStringCellValue().trim().equals("性别")){
			System.out.println(cell4.getStringCellValue().trim());
			return false;
		}
		Cell cell5=titleCells.next();
		if(cell5.getStringCellValue().trim().equals("")||!cell5.getStringCellValue().trim().equals("出生日期")){
			System.out.println(cell5.getStringCellValue().trim());
			return false;
		}
		Cell cell6=titleCells.next();
		if(cell6.getStringCellValue().trim().equals("")||!cell6.getStringCellValue().trim().equals("身份证号")){
			System.out.println(cell6.getStringCellValue().trim());
			return false;
		}
		Cell cell7=titleCells.next();
		if(cell7.getStringCellValue().trim().equals("")||!cell7.getStringCellValue().trim().equals("民族")){
			System.out.println(cell7.getStringCellValue().trim());
			return false;
		}
		Cell cell8=titleCells.next();
		if(cell8.getStringCellValue().trim().equals("")||!cell8.getStringCellValue().trim().equals("政治面貌")){
			System.out.println(cell8.getStringCellValue().trim());
			return false;
		}
		Cell cell9=titleCells.next();
		if(cell9.getStringCellValue().trim().equals("")||!cell9.getStringCellValue().trim().equals("籍贯")){
			System.out.println(cell9.getStringCellValue().trim());
			return false;
		}
		Cell cell10=titleCells.next();
		if(cell10.getStringCellValue().trim().equals("")||!cell10.getStringCellValue().trim().equals("户籍类型")){
			System.out.println(cell10.getStringCellValue().trim());
			return false;
		}
		Cell cell11=titleCells.next();
		if(cell11.getStringCellValue().trim().equals("")||!cell11.getStringCellValue().trim().equals("户口所在地")){
			System.out.println(cell11.getStringCellValue().trim());
			return false;
		}
		Cell cell12=titleCells.next();
		if(cell12.getStringCellValue().trim().equals("")||!cell12.getStringCellValue().trim().equals("学制")){
			System.out.println(cell12.getStringCellValue().trim());
			return false;
		}
		Cell cell13=titleCells.next();
		if(cell13.getStringCellValue().trim().equals("")||!cell13.getStringCellValue().trim().equals("学历")){
			System.out.println(cell13.getStringCellValue().trim());
			return false;
		}
		Cell cell14=titleCells.next();
		if(cell14.getStringCellValue().trim().equals("")||!cell14.getStringCellValue().trim().equals("联系方式")){
			System.out.println(cell14.getStringCellValue().trim());
			return false;
		}
		Cell cell15=titleCells.next();
		if(cell15.getStringCellValue().trim().equals("")||!cell15.getStringCellValue().trim().equals("入学时间")){
			System.out.println(cell15.getStringCellValue().trim());
			return false;
		}
		Cell cell16=titleCells.next();
		if(cell16.getStringCellValue().trim().equals("")&&!cell16.getStringCellValue().trim().equals("毕业时间")){
			System.out.println(cell16.getStringCellValue().trim());
			return false;
		}
		Cell cell17=titleCells.next();
		if(cell17.getStringCellValue().trim().equals("")||!cell17.getStringCellValue().trim().equals("邮政编码")){
			System.out.println(cell17.getStringCellValue().trim());
			return false;
		}
		Cell cell18=titleCells.next();
		if(cell18.getStringCellValue().trim().equals("")||!cell18.getStringCellValue().trim().equals("学号")){
			System.out.println(cell18.getStringCellValue().trim());
			return false;
		}
		Cell cell19=titleCells.next();
		if(cell19.getStringCellValue().trim().equals("")||!cell19.getStringCellValue().trim().equals("学籍状态")){
			System.out.println(cell19.getStringCellValue().trim());
			return false;
		}
		Cell cell20=titleCells.next();
		if(cell20.getStringCellValue().trim().equals("")||!cell20.getStringCellValue().trim().equals("班别")){
			System.out.println(cell20.getStringCellValue().trim());
			return false;
		}
		//都没错的话，返回true
		return true;
	}
	
	/**
	 * 验证excel表每张sheet的标题栏是否有错（学生就业信息）
	 * @param titleRow
	 * @return
	 */
	public boolean isGraExcelTitleValidate(Row titleRow) {
		// TODO Auto-generated method stub
		Iterator<Cell> titleCells=titleRow.cellIterator();
		//判断标题行的每一个单元是否符合格式要求
		Cell cell1=titleCells.next();
		if(cell1.getStringCellValue().trim().equals("")||!cell1.getStringCellValue().trim().equals("学号")){
			System.out.println(cell1.getStringCellValue().trim());
			return false;
		}
		Cell cell2=titleCells.next();
		if(cell2.getStringCellValue().trim().equals("")||!cell2.getStringCellValue().trim().equals("姓名")){
			System.out.println(cell2.getStringCellValue().trim());
			return false;
		}
		Cell cell3=titleCells.next();
		if(cell3.getStringCellValue().trim().equals("")||!cell3.getStringCellValue().trim().equals("就业单位名称")){
			System.out.println(cell3.getStringCellValue().trim());
			return false;
		}
		Cell cell4=titleCells.next();
		if(cell4.getStringCellValue().trim().equals("")||!cell4.getStringCellValue().trim().equals("就业单位所在地")){
			System.out.println(cell4.getStringCellValue().trim());
			return false;
		}
		Cell cell5=titleCells.next();
		if(cell5.getStringCellValue().trim().equals("")||!cell5.getStringCellValue().trim().equals("单位性质")){
			System.out.println(cell5.getStringCellValue().trim());
			return false;
		}
		Cell cell6=titleCells.next();
		if(cell6.getStringCellValue().trim().equals("")||!cell6.getStringCellValue().trim().equals("就业单位所属行业")){
			System.out.println(cell6.getStringCellValue().trim());
			return false;
		}
		Cell cell7=titleCells.next();
		if(cell7.getStringCellValue().trim().equals("")||!cell7.getStringCellValue().trim().equals("单位联系人")){
			System.out.println(cell7.getStringCellValue().trim());
			return false;
		}
		Cell cell8=titleCells.next();
		if(cell8.getStringCellValue().trim().equals("")||!cell8.getStringCellValue().trim().equals("单位联系电话")){
			System.out.println(cell8.getStringCellValue().trim());
			return false;
		}
		Cell cell9=titleCells.next();
		if(cell9.getStringCellValue().trim().equals("")||!cell9.getStringCellValue().trim().equals("毕业生联系方式")){
			System.out.println(cell9.getStringCellValue().trim());
			return false;
		}
		Cell cell10=titleCells.next();
		if(cell10.getStringCellValue().trim().equals("")||!cell10.getStringCellValue().trim().equals("应聘方式")){
			System.out.println(cell10.getStringCellValue().trim());
			return false;
		}
		Cell cell11=titleCells.next();
		if(cell11.getStringCellValue().trim().equals("")||!cell11.getStringCellValue().trim().equals("单位邮政编码")){
			System.out.println(cell11.getStringCellValue().trim());
			return false;
		}
		Cell cell12=titleCells.next();
		if(cell12.getStringCellValue().trim().equals("")||!cell12.getStringCellValue().trim().equals("单位详细地址")){
			System.out.println(cell12.getStringCellValue().trim());
			return false;
		}
		//都没错的话，返回true
		return true;
	}
	
	/**
	 * 根据准考证号判断学生源信息是否已经存在
	 * @param examNum
	 * @return
	 */
	public boolean isStudentInfoExit(String examNum) {
		// TODO Auto-generated method stub
		String hql ="from StudentInfo s where s.examNum=?";
		List<StudentInfo> stuInfo=studentInfoDao.findEntityByHQL(hql, examNum);
		if(stuInfo.size()>0){
			return true;
		}else{
			return false;
		}
	}
	

	/**
	 * 根据准身份证号判断学生源信息是否已经存在
	 * @param idCard
	 * @return
	 */
	public boolean findStudentInfoByIdCard(String idCard) {
		// TODO Auto-generated method stub
		String hql="from StudentInfo s where s.idCard=?";
		List<StudentInfo> stuInfo=studentInfoDao.findEntityByHQL(hql, idCard);
		if(stuInfo.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 根据专业名字来得到专业
	 * @param majorFieldName
	 * @return
	 */
	public MajorField findMajorFieldByName(String majorFieldName){
		String hql="from MajorField m where m.name=?";
		List<MajorField> major=majorFieldDao.findEntityByHQL(hql, majorFieldName);
		if(major.size()>0){
			return major.get(0);
		}else{
			return null;
		}
	}

	@Override
	public HSSFWorkbook createHSSFWorkbookByStusList(List<Student> students,int type) {
		// TODO Auto-generated method stub
		//转型
		List<PageStudentInfo> pageStuInfos=studentsChangeToPageModel(students);
//		System.out.println("最后测试excell文件显示的信息：   ");
//		for(PageStudentInfo info:pageStuInfos){
//			System.out.println(info.getId()+"  "+info.getName()+" "+info.getBirthday()+"  "+info.getGraduateTime()+"  "+info.getEducation()
//					+"  "+info.getHousehold()+"   "+info.getMajorFieldName());
//		}
		//创建excell
		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet=null;
		if(pageStuInfos.size()>0){
			if(type==0){
				//查询所有学院的
				sheet=book.createSheet("学校");
			}else if(type==3){
				//查询指定学院的
				sheet=book.createSheet(students.get(0).getDepartment().getName());
			}else if(type==2){
				//查询指定专业的
				sheet=book.createSheet(pageStuInfos.get(0).getMajorFieldName());
			}else if(type==1){
				//查询指定班别的
				sheet=book.createSheet(students.get(0).getClazz().getName());
			}
		}else{
			sheet=book.createSheet("无任何数据");
		}
		//设置列宽(单位：1/20点)
		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 6000);
		sheet.setColumnWidth(3, 2000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 7000);
		sheet.setColumnWidth(6, 2000);
		sheet.setColumnWidth(7,3000);
		sheet.setColumnWidth(8,2000);
		sheet.setColumnWidth(9,3000);
		sheet.setColumnWidth(10,12000);
		sheet.setColumnWidth(11,2000);
		sheet.setColumnWidth(12,2000);
		sheet.setColumnWidth(13,4000);
		sheet.setColumnWidth(14,4000);
		sheet.setColumnWidth(15,4000);
		sheet.setColumnWidth(16,2000);
		//先搞标题行
		HSSFRow titleRow = sheet.createRow(0); // 下标为0的行开始  
		HSSFCell[] titleCells = new HSSFCell[17]; 
		String[] names = new String[17];  
		names[0] = "准考证号";  
        names[1] = "姓名";  
        names[2] = "毕业学校";  
        names[3] = "性别";  
        names[4] = "出生日期";  
        names[5] = "身份证号";  
        names[6] = "民族";  
        names[7] = "政治面貌";  
        names[8] = "籍贯";  
        names[9] = "户籍类型";  
        names[10] = "户口所在地";  
        names[11] = "学制";  
        names[12] = "学历";  
        names[13] = "联系方式";  
        names[14] = "入学时间";  
        names[15] = "毕业时间";  
        names[16] = "邮政编码";  
        
        //设置对齐方式为居中对齐
        HSSFCellStyle style=book.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //中对齐
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //中对齐
        
        for (int j = 0; j < 17; j++) {  
        	titleCells[j] = titleRow.createCell(j);  
        	titleCells[j].setCellValue(new HSSFRichTextString(names[j]));  
    		titleCells[j].setCellStyle(style);
        }
        if(pageStuInfos.size()>0){
	        //数据
	        for(int i=0;i<pageStuInfos.size();i++){
	        	HSSFRow row = sheet.createRow(i+1); // 下标为0的行开始(已经创建了标题行)
	        	
	        	row.createCell(0).setCellValue(pageStuInfos.get(i).getExamNum());
	        	row.getCell(0).setCellStyle(style);
	        	row.createCell(1).setCellValue(pageStuInfos.get(i).getName());
	        	row.getCell(1).setCellStyle(style);
	        	row.createCell(2).setCellValue(pageStuInfos.get(i).getGraduationSchool());
	        	row.getCell(2).setCellStyle(style);
	        	row.createCell(3).setCellValue(pageStuInfos.get(i).getSex());
	        	row.getCell(3).setCellStyle(style);
	        	row.createCell(4).setCellValue(pageStuInfos.get(i).getBirthday());
	        	row.getCell(4).setCellStyle(style);
	        	row.createCell(5).setCellValue(pageStuInfos.get(i).getIdCard());
	        	row.getCell(5).setCellStyle(style);
	        	row.createCell(6).setCellValue(pageStuInfos.get(i).getNation());
	        	row.getCell(6).setCellStyle(style);
	        	row.createCell(7).setCellValue(pageStuInfos.get(i).getPoliticalFeature());
	        	row.getCell(7).setCellStyle(style);
	        	row.createCell(8).setCellValue(pageStuInfos.get(i).getNativePlace());
	        	row.getCell(8).setCellStyle(style);
	        	row.createCell(9).setCellValue(pageStuInfos.get(i).getHousehold());
	        	row.getCell(9).setCellStyle(style);
	        	row.createCell(10).setCellValue(pageStuInfos.get(i).getHouseholdPlace());
	        	row.getCell(10).setCellStyle(style);
	        	row.createCell(11).setCellValue(pageStuInfos.get(i).getSchoolLength());
	        	row.getCell(11).setCellStyle(style);
	        	row.createCell(12).setCellValue(pageStuInfos.get(i).getEducation());
	        	row.getCell(12).setCellStyle(style);
	        	row.createCell(13).setCellValue(pageStuInfos.get(i).getPhone());
	        	row.getCell(13).setCellStyle(style);
	        	row.createCell(14).setCellValue(pageStuInfos.get(i).getEnterCollegeTime());
	        	row.getCell(14).setCellStyle(style);
	        	row.createCell(15).setCellValue(pageStuInfos.get(i).getGraduateTime());
	        	row.getCell(15).setCellStyle(style);
	        	row.createCell(16).setCellValue(pageStuInfos.get(i).getPostEncoding());
	        	row.getCell(16).setCellStyle(style);
	        	
	        }
        }
		return book;
	}
	
	@Override
	public HSSFWorkbook createHSSFWorkbookByStusList2(List<Student> students,int type) {
		System.out.println("come in");
		//转型
		List<PageStudentInfo> pageStuInfos=studentsChangeToPageModel(students);
		System.out.println("PageStudentInfo");
		List<PageStudent> pageStudents=studentsChangeToPageStuModel(students);
		System.out.println("PageStudent");
		//创建excell
		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet=null;
		if(pageStuInfos.size()>0){
			if(type==0){
				//查询所有学院的
				sheet=book.createSheet("学校");
			}else if(type==3){
				//查询指定学院的
				sheet=book.createSheet(students.get(0).getDepartment().getName());
			}else if(type==2){
				//查询指定专业的
				sheet=book.createSheet(pageStuInfos.get(0).getMajorFieldName());
			}else if(type==1){
				//查询指定班别的
				sheet=book.createSheet(students.get(0).getClazz().getName());
			}
		}else{
			sheet=book.createSheet("无任何数据");
		}
		//设置列宽(单位：1/20点)
		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 6000);
		sheet.setColumnWidth(3, 2000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 7000);
		sheet.setColumnWidth(6, 3000);
		sheet.setColumnWidth(7,3000);
		sheet.setColumnWidth(8,3000);
		sheet.setColumnWidth(9,3000);
		sheet.setColumnWidth(10,12000);
		sheet.setColumnWidth(11,3000);
		sheet.setColumnWidth(12,3000);
		sheet.setColumnWidth(13,4000);
		sheet.setColumnWidth(14,4000);
		sheet.setColumnWidth(15,4000);
		sheet.setColumnWidth(16,3000);
		sheet.setColumnWidth(17,4000);
		sheet.setColumnWidth(18,4000);
		sheet.setColumnWidth(19,3000);
		//先搞标题行
		HSSFRow titleRow = sheet.createRow(0); // 下标为0的行开始  
		HSSFCell[] titleCells = new HSSFCell[20]; 
		String[] names = new String[20];  
		names[0] = "准考证号";  
        names[1] = "姓名";  
        names[2] = "毕业学校";  
        names[3] = "性别";  
        names[4] = "出生日期";  
        names[5] = "身份证号";  
        names[6] = "民族";  
        names[7] = "政治面貌";  
        names[8] = "籍贯";  
        names[9] = "户籍类型";  
        names[10] = "户口所在地";  
        names[11] = "学制";  
        names[12] = "学历";  
        names[13] = "联系方式";  
        names[14] = "入学时间";  
        names[15] = "毕业时间";  
        names[16] = "邮政编码";  
        names[17] = "学号";  
        names[18] = "学籍状态";  
        names[19] = "班别"; 
        
        //设置对齐方式为居中对齐
        HSSFCellStyle style=book.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //中对齐
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //中对齐
        
        for (int j = 0; j < 20; j++) {  
        	titleCells[j] = titleRow.createCell(j);  
        	titleCells[j].setCellValue(new HSSFRichTextString(names[j]));  
    		titleCells[j].setCellStyle(style);
        }
        System.out.println("prepare Data");
        if(pageStuInfos.size()>0){
	        //数据
	        for(int i=0;i<pageStuInfos.size();i++){
	        	HSSFRow row = sheet.createRow(i+1); // 下标为0的行开始(已经创建了标题行)
	        	
	        	row.createCell(0).setCellValue(pageStuInfos.get(i).getExamNum());
	        	row.getCell(0).setCellStyle(style);
	        	row.createCell(1).setCellValue(pageStuInfos.get(i).getName());
	        	row.getCell(1).setCellStyle(style);
	        	row.createCell(2).setCellValue(pageStuInfos.get(i).getGraduationSchool());
	        	row.getCell(2).setCellStyle(style);
	        	row.createCell(3).setCellValue(pageStuInfos.get(i).getSex());
	        	row.getCell(3).setCellStyle(style);
	        	row.createCell(4).setCellValue(pageStuInfos.get(i).getBirthday());
	        	row.getCell(4).setCellStyle(style);
	        	row.createCell(5).setCellValue(pageStuInfos.get(i).getIdCard());
	        	row.getCell(5).setCellStyle(style);
	        	row.createCell(6).setCellValue(pageStuInfos.get(i).getNation());
	        	row.getCell(6).setCellStyle(style);
	        	row.createCell(7).setCellValue(pageStuInfos.get(i).getPoliticalFeature());
	        	row.getCell(7).setCellStyle(style);
	        	row.createCell(8).setCellValue(pageStuInfos.get(i).getNativePlace());
	        	row.getCell(8).setCellStyle(style);
	        	row.createCell(9).setCellValue(pageStuInfos.get(i).getHousehold());
	        	row.getCell(9).setCellStyle(style);
	        	row.createCell(10).setCellValue(pageStuInfos.get(i).getHouseholdPlace());
	        	row.getCell(10).setCellStyle(style);
	        	row.createCell(11).setCellValue(pageStuInfos.get(i).getSchoolLength());
	        	row.getCell(11).setCellStyle(style);
	        	row.createCell(12).setCellValue(pageStuInfos.get(i).getEducation());
	        	row.getCell(12).setCellStyle(style);
	        	row.createCell(13).setCellValue(pageStuInfos.get(i).getPhone());
	        	row.getCell(13).setCellStyle(style);
	        	row.createCell(14).setCellValue(pageStuInfos.get(i).getEnterCollegeTime());
	        	row.getCell(14).setCellStyle(style);
	        	row.createCell(15).setCellValue(pageStuInfos.get(i).getGraduateTime());
	        	row.getCell(15).setCellStyle(style);
	        	row.createCell(16).setCellValue(pageStuInfos.get(i).getPostEncoding());
	        	row.getCell(16).setCellStyle(style);
	        	System.out.println("pageStudents.get(i).getCode()  "+pageStudents.get(i).getCode());
	        	row.createCell(17).setCellValue(pageStudents.get(i).getCode());
	        	row.getCell(17).setCellStyle(style);
	        	System.out.println("pageStudents.get(i).getSchoolStatus()  "+pageStudents.get(i).getSchoolStatus());
	        	row.createCell(18).setCellValue(pageStudents.get(i).getSchoolStatus());
	        	row.getCell(18).setCellStyle(style);
	        	System.out.println("pageStudents.get(i).getClazzName()  "+pageStudents.get(i).getClazzName());
	        	row.createCell(19).setCellValue(pageStudents.get(i).getClazzName());
	        	row.getCell(19).setCellStyle(style);
	        	
	        }
        }
		return book;
	}
	
	//提供给createHSSFWorkbookByStusList方法进行转型(student-->pageModel)
	public List<PageStudent> studentsChangeToPageStuModel(List<Student> students){
		List<PageStudent> pageStudents=new ArrayList<PageStudent>();
		//开始转换成页面所需要的实体
		for(Student stu:students){
			PageStudent pageStudent=new PageStudent();
			BeanUtils.copyProperties(stu, pageStudent);
			EnumerationValue enumerationValue =null;
			//转换性别字段
			enumerationValue=enumerationValueDao.getEntity(stu.getSchoolStatus());
			if(enumerationValue!=null){
				pageStudent.setSchoolStatus(enumerationValue.getName());
			}
			//转换班级
			pageStudent.setClazzName(stu.getClazz().getName());
			pageStudents.add(pageStudent);
			
		}
		return pageStudents;
	}
	
	//提供给createHSSFWorkbookByStusList方法进行转型(student-->pageModel)
	public List<PageStudentInfo> studentsChangeToPageModel(List<Student> students){
		List<PageStudentInfo> pageStudentInfos=new ArrayList<PageStudentInfo>();
		//开始转换成页面所需要的实体
		for(Student stu:students){
			PageStudentInfo pageStudent=new PageStudentInfo();
			BeanUtils.copyProperties(stu.getStudentInfo(),pageStudent);
			
			EnumerationValue enumerationValue =null;
			//转换性别字段
			enumerationValue=enumerationValueDao.getEntity(stu.getStudentInfo().getSexType());
			if(enumerationValue!=null){
				pageStudent.setSex(enumerationValue.getName());
			}
			
			//转换学院审核字段
			enumerationValue=enumerationValueDao.getEntity(stu.getStudentInfo().getDepartmentAduitStatus());
			if(enumerationValue!=null){
				pageStudent.setDepartmentAduit(enumerationValue.getName());
			}
			
			//转换学校审核字段
			enumerationValue=enumerationValueDao.getEntity(stu.getStudentInfo().getSchoolAduitStatus());
			if(enumerationValue!=null){
				pageStudent.setSchoolAduit(enumerationValue.getName());
			}
			
			//转换专业方向字段
			MajorField majorField=stu.getStudentInfo().getMajorField();
			if(majorField!=null){
				pageStudent.setMajorFieldName(majorField.getName());
			}
			//转换生日日期格式
			Date date=stu.getStudentInfo().getBirthday();
			if(date!=null){
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
				String birthdayDate=null;
				try {
					birthdayDate=dateFormat.format(date);
					//System.out.println(dateShow);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pageStudent.setBirthday(birthdayDate);
			}
			//转换民族字段
			enumerationValue=enumerationValueDao.getEntity(stu.getStudentInfo().getNationType());
			if(enumerationValue!=null){
				pageStudent.setNation(enumerationValue.getName());
			}
			//转换政治面貌字段
			enumerationValue=enumerationValueDao.getEntity(stu.getStudentInfo().getPoliticalFeatureType());
			if(enumerationValue!=null){
				pageStudent.setPoliticalFeature(enumerationValue.getName());
			}
			//转换户籍字段
			enumerationValue=enumerationValueDao.getEntity(stu.getStudentInfo().getHouseholdType());
			if(enumerationValue!=null){
				pageStudent.setHousehold(enumerationValue.getName());
			}
			//转换学制字段
			enumerationValue=enumerationValueDao.getEntity(stu.getStudentInfo().getSchoolLengthType());
			if(enumerationValue!=null){
				pageStudent.setSchoolLength(enumerationValue.getName());
			}
			//转换学历字段
			enumerationValue=enumerationValueDao.getEntity(stu.getStudentInfo().getEducationType());
			if(enumerationValue!=null){
				pageStudent.setEducation(enumerationValue.getName());
			}
			//转换入学年份
			date=stu.getStudentInfo().getEnterCollegeTime();
			if(date!=null){
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
				String enterDate=null;
				try {
					enterDate=dateFormat.format(date);
					//System.out.println(dateShow);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pageStudent.setEnterCollegeTime(enterDate);
			}
			//转换毕业年份
			date=stu.getStudentInfo().getGraduateTime();
			if(date!=null){
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
				String graduateDate=null;
				try {
					graduateDate=dateFormat.format(date);
					//System.out.println(dateShow);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pageStudent.setGraduateTime(graduateDate);
			}
			pageStudentInfos.add(pageStudent);
		}		
		return pageStudentInfos;
	}

	@Override
	public HSSFWorkbook createHSSFWorkbookByGraInfosList(
			List<GraduateInfo> graduateInfos, int type) {
		// TODO Auto-generated method stub
		//转型
		List<PageGraduateInfo> pageGraInfos=graInfosChangeToPageModel(graduateInfos);
//		System.out.println("最后测试excell文件显示的信息：   ");
//		if(pageGraInfos.size()>0){
//			for(PageGraduateInfo info:pageGraInfos){
//				System.out.println(info.getId()+"  "+info.getName()+" "+info.getApplyTy()+"  "+info.getUnitTy()+"   "+info.getGraduatePhone()+"   "
//						+info.getUnitContantPerson()+"   "+info.getUnitAddress());
//			}
//		}
		//创建excell
		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet=null;
		if(pageGraInfos.size()>0){
			if(type==0){
				//查询所有学院的
				sheet=book.createSheet("学校");
			}else if(type==3){
				//查询指定学院的
				sheet=book.createSheet(graduateInfos.get(0).getStudent().getDepartment().getName());
			}else if(type==2){
				//查询指定专业的
				sheet=book.createSheet(graduateInfos.get(0).getStudent().getStudentInfo().getMajorField().getName());
			}else if(type==1){
				//查询指定班别的
				sheet=book.createSheet(graduateInfos.get(0).getStudent().getClazz().getName());
			}
		}else{
			sheet=book.createSheet("无任何数据");
		}
		
		//设置列宽(单位：1/20点)
		sheet.setColumnWidth(0, 3000);
		sheet.setColumnWidth(1, 6000);
		sheet.setColumnWidth(2, 6000);
		sheet.setColumnWidth(3, 10000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 5000);
		sheet.setColumnWidth(6, 5000);
		sheet.setColumnWidth(7,4000);
		sheet.setColumnWidth(8,15000);
		sheet.setColumnWidth(9,4000);
		sheet.setColumnWidth(10,4000);
		//先搞标题行
		HSSFRow titleRow = sheet.createRow(0); // 下标为0的行开始  
		HSSFCell[] titleCells = new HSSFCell[11]; 
		String[] names = new String[11];  
		names[0] = "姓名";  
        names[1] = "应聘方式";  
        names[2] = "单位性质";  
        names[3] = "就业单位名称";  
        names[4] = "就业单位所在地";  
        names[5] = "就业单位行业";  
        names[6] = "单位联系人";  
        names[7] = "单位联系电话";  
        names[8] = "单位详细地址";  
        names[9] = "毕业生联系方式";  
        names[10] = "单位邮政编码";  
        
        //设置对齐方式为居中对齐
        HSSFCellStyle style=book.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //中对齐
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //中对齐
        
        for (int j = 0; j < 11; j++) {  
        	titleCells[j] = titleRow.createCell(j);  
        	titleCells[j].setCellValue(new HSSFRichTextString(names[j]));  
    		titleCells[j].setCellStyle(style);
        }
        
        if(pageGraInfos.size()>0){
	        //数据
	        for(int i=0;i<pageGraInfos.size();i++){
	        	HSSFRow row = sheet.createRow(i+1); // 下标为0的行开始(已经创建了标题行)
	        	
	        	row.createCell(0).setCellValue(pageGraInfos.get(i).getName());
	        	row.getCell(0).setCellStyle(style);
	        	row.createCell(1).setCellValue(pageGraInfos.get(i).getApplyTy());
	        	row.getCell(1).setCellStyle(style);
	        	row.createCell(2).setCellValue(pageGraInfos.get(i).getUnitTy());
	        	row.getCell(2).setCellStyle(style);
	        	row.createCell(3).setCellValue(pageGraInfos.get(i).getUnitName());
	        	row.getCell(3).setCellStyle(style);
	        	row.createCell(4).setCellValue(pageGraInfos.get(i).getUnitLocation());
	        	row.getCell(4).setCellStyle(style);
	        	row.createCell(5).setCellValue(pageGraInfos.get(i).getUnitIndustry());
	        	row.getCell(5).setCellStyle(style);
	        	row.createCell(6).setCellValue(pageGraInfos.get(i).getUnitContantPerson());
	        	row.getCell(6).setCellStyle(style);
	        	row.createCell(7).setCellValue(pageGraInfos.get(i).getUnitContantPhone());
	        	row.getCell(7).setCellStyle(style);
	        	row.createCell(8).setCellValue(pageGraInfos.get(i).getUnitAddress());
	        	row.getCell(8).setCellStyle(style);
	        	row.createCell(9).setCellValue(pageGraInfos.get(i).getGraduatePhone());
	        	row.getCell(9).setCellStyle(style);
	        	row.createCell(10).setCellValue(pageGraInfos.get(i).getUnitPostEncoding());
	        	row.getCell(10).setCellStyle(style);
	        }
        }
        return book;
	}
	
	//提供给createHSSFWorkbookByGraInfosList方法进行转型(graduateInfos-->pageModel)
	public List<PageGraduateInfo> graInfosChangeToPageModel(List<GraduateInfo> graduateInfos){
		List<PageGraduateInfo> pageGraduateInfos=new ArrayList<PageGraduateInfo>();
		//开始转换成页面所需要的实体
		for(GraduateInfo gra:graduateInfos){
			PageGraduateInfo pageGraduate=new PageGraduateInfo();
			BeanUtils.copyProperties(gra,pageGraduate);
			
			EnumerationValue enumerationValue =null;
			//转换单位性质字段
			enumerationValue=enumerationValueDao.getEntity(gra.getUnitType());
			if(enumerationValue!=null){
				pageGraduate.setUnitTy(enumerationValue.getName());
			}
			
			//转换应聘方式字段
			enumerationValue=enumerationValueDao.getEntity(gra.getApplyType());
			if(enumerationValue!=null){
				pageGraduate.setApplyTy(enumerationValue.getName());
			}
			
			//转换学院审核字段
			enumerationValue=enumerationValueDao.getEntity(gra.getDepartmentGAduitStatus());
			if(enumerationValue!=null){
				pageGraduate.setDepartmentGAduit(enumerationValue.getName());
			}
			
			//转换学校审核字段
			enumerationValue=enumerationValueDao.getEntity(gra.getSchoolGAduitStatus());
			if(enumerationValue!=null){
				pageGraduate.setSchoolGAduit(enumerationValue.getName());
			}
			
			pageGraduateInfos.add(pageGraduate);
		}		
		return pageGraduateInfos;
	}

	/**
	 * 根据班级名字寻找班级
	 * @param clazzName
	 * @return
	 */
	public Clazz findClazzByName(String clazzName){
		String hql="from Clazz c where c.name=?";
		return clazzDao.findEntityByHQL(hql, clazzName).get(0);
	}
	
	/**
	 * 根据准考证号或者该学生源信息 
	 * @param examNum
	 * @return
	 */
//	public StudentInfo findStudentInfoByExamNum(String examNum){
//		String hql ="from StudentInfo s where s.examNum=?";
//		List<StudentInfo> stuInfo=studentInfoDao.findEntityByHQL(hql, examNum);
//		if(stuInfo!=null&&stuInfo.size()>0){
//			return stuInfo.get(0);
//		}else{
//			return null;
//		}
//	}
	
	/**
	 * 根据学号判断学号是否已经存在
	 * @param code
	 * @return
	 */
	public boolean isCodeExit(String code){
		String hql="from Student s where s.code=?";
		List<Student> stus=studentDao.findEntityByHQL(hql, code);
		if(stus.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 根据学号查找学生信息
	 * @param code
	 * @return
	 */
	public Student getStudentByCode(String code){
		String hql="from Student s where s.code=?";
		List<Student> stus=studentDao.findEntityByHQL(hql, code);
		if(stus!=null&&stus.size()>0){
			return stus.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 根据学号查找学生就业信息（是否存在）
	 * @param code
	 * @return
	 */
	public boolean isGraduateInfoExit(String code){
		String hql="from GraduateInfo g where g.student.code=?";
		//只找到一个，或者找不到
		List<GraduateInfo> gras=graduateInfoDao.findEntityByHQL(hql, code);
		if(gras!=null&&gras.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
}
