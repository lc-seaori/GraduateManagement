package com.benson.graduate.stu.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.service.impl.BaseServiceImpl;
import com.benson.graduate.common.enumeration.EnumerationType;
import com.benson.graduate.stu.dao.StudentInfoDao;
import com.benson.graduate.stu.model.MajorField;
import com.benson.graduate.stu.model.StudentInfo;
import com.benson.graduate.stu.pagemodel.PageStudentInfo;
import com.benson.graduate.stu.service.StudentInfoService;
import com.benson.graduate.sys.dao.EnumerationValueDao;
import com.benson.graduate.sys.model.EnumerationValue;

@Service("studentInfoService")
public class StudentInfoServiceImpl extends BaseServiceImpl implements
		StudentInfoService {

	
	private StudentInfoDao studentInfoDao;
	@Resource(name="studentInfoDao")
	public void setStudentInfoDao(StudentInfoDao studentInfoDao) {
		this.studentInfoDao = studentInfoDao;
	}
	
	private EnumerationValueDao enumerationValueDao;
	@Resource(name="enumerationValueDao")
	public void setEnumerationValueDao(EnumerationValueDao enumerationValueDao) {
		this.enumerationValueDao = enumerationValueDao;
	}
	
	public List<StudentInfo> getAllStudentInfosByPage(String hql,int pageNow,int pageRows) {
		
		return studentInfoDao.findEntityByHQL(hql, pageNow, pageRows);
	}


	@Override
	public List<StudentInfo> getAllStudentInfos() {
		// TODO Auto-generated method stub
		String hql="from StudentInfo";
		return studentInfoDao.findEntityByHQL(hql);
	}

	@Override
	public DataGrid getDataGrid(String examNum,String name,String idCard,String graduateTime,String sort,String order,int pageNow,int pageRows) {

		String hql="";
		//第一次datagrid自动发送请求时，三个字段为null
		List<StudentInfo> studentInfos=null;
		if(graduateTime!=null&&!graduateTime.equals("")){
			//对毕业日期处理（精确到月）
			int index=graduateTime.indexOf('-');
			graduateTime=graduateTime. substring(0, index+3);
			//System.out.println("处理过后的毕业时间：  "+graduateTime);
		}
		if(examNum==null&&name==null&&idCard==null){
			hql="from StudentInfo s";
		}else{
			hql="from StudentInfo s";
			if(!examNum.equals("")){
				hql+=" where s.examNum like \'%"+examNum+"%\'";
				if(!name.equals("")){
					hql+=" and s.name like \'%"+name+"%\'";
					if(!idCard.equals("")){
						hql+=" and s.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.graduateTime like \'%"+graduateTime+"%\'";
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.graduateTime like \'%"+graduateTime+"%\'";
						}
					}
				}else{
					if(!idCard.equals("")){
						hql+=" and s.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.graduateTime like \'%"+graduateTime+"%\'";
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.graduateTime like \'%"+graduateTime+"%\'";
						}
					}
				}
			}else{
				if(!name.equals("")){
					hql+=" where s.name like \'%"+name+"%\'";
					if(!idCard.equals("")){
						hql+=" and s.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.graduateTime like \'%"+graduateTime+"%\'";
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.graduateTime like \'%"+graduateTime+"%\'";
						}
					}
				}else{
					if(!idCard.equals("")){
						hql+=" where s.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.graduateTime like \'%"+graduateTime+"%\'";
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" where s.graduateTime like \'%"+graduateTime+"%\'";
						}
					}
				}
			}
		}
		if(sort!=null&&order!=null){
			hql+=" order by "+sort+" "+order;
		}
		System.out.println("最后查询的hql为：   "+hql);
		studentInfos=getAllStudentInfosByPage(hql,pageNow,pageRows);
		for(StudentInfo stu:studentInfos){
			System.out.println(stu.getId()+" "+stu.getIdCard()+"  "+stu.getName()+" "+stu.getExamNum()+" "+stu.getGraduationSchool());
		}
		if(studentInfos!=null && studentInfos.size()>0){
			List<PageStudentInfo> pageStudentInfos=modelChangeToPageModel(studentInfos);
			DataGrid data=new DataGrid();
			data.setTotal(getTotals(hql));
			data.setRows(pageStudentInfos);
			return data;
		}else{
			DataGrid data=new DataGrid();
			data.setRows(new ArrayList<PageStudentInfo>());
			data.setTotal(0L);
			return data;
		}
		
	}
	
	@Override
	public DataGrid getDepartmentAduitDataGrid(String examNum,String name,String idCard,String departmentName,String graduateTime,String sort,String order,int pageNow,int pageRows) {

		String hql="";
		//第一次datagrid自动发送请求时，三个字段为null
		List<StudentInfo> studentInfos=null;
		if(graduateTime!=null&&!graduateTime.equals("")){
			//对毕业日期处理（精确到月）
			int index=graduateTime.indexOf('-');
			graduateTime=graduateTime. substring(0, index+3);
			//System.out.println("处理过后的毕业时间：  "+graduateTime);
		}
		if(examNum==null&&name==null&&idCard==null){
//			hql="from StudentInfo s where (s.departmentAduitStatus=\'10002\' or s.departmentAduitStatus=\'10003\')";
			hql="from StudentInfo s where s.departmentAduitStatus!=\'10001\'";
		}else{
			hql="from StudentInfo s where s.departmentAduitStatus!=\'10001\'";
			if(!examNum.equals("")){
				hql+=" and s.examNum like \'%"+examNum+"%\'";
				if(!name.equals("")){
					hql+=" and s.name like \'%"+name+"%\'";
					if(!idCard.equals("")){
						hql+=" and s.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}
					}
				}else{
					if(!idCard.equals("")){
						hql+=" and s.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}
					}
				}
			}else{
				if(!name.equals("")){
					hql+=" and s.name like \'%"+name+"%\'";
					if(!idCard.equals("")){
						hql+=" and s.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}
					}
				}else{
					if(!idCard.equals("")){
						hql+=" and s.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}
					}
				}
			}
		}
		if(sort!=null&&order!=null){
			hql+=" order by "+sort+" "+order;
		}
		System.out.println("最后查询的hql为：   "+hql);
		studentInfos=getAllStudentInfosByPage(hql,pageNow,pageRows);
//		System.out.println("studentInfos    :" +studentInfos.size());
		
		if(studentInfos!=null && studentInfos.size()>0){
			List<PageStudentInfo> pageStudentInfos=modelChangeToPageModel(studentInfos);
			DataGrid data=new DataGrid();
			data.setTotal(getTotalsByDAduitId(hql));
			data.setRows(pageStudentInfos);
			return data;
		}else{
			DataGrid data=new DataGrid();
			data.setRows(new ArrayList<PageStudentInfo>());
			data.setTotal(0L);
			return data;
		}
		
	}
	
	@Override
	public DataGrid getSchoolAduitDataGrid(String examNum,String name,String idCard,String departmentName,String graduateTime,String sort,String order,int pageNow,int pageRows) {

		String hql="";
		//第一次datagrid自动发送请求时，三个字段为null
		List<StudentInfo> studentInfos=null;
		if(graduateTime!=null&&!graduateTime.equals("")){
			//对毕业日期处理（精确到月）
			int index=graduateTime.indexOf('-');
			graduateTime=graduateTime. substring(0, index+3);
			//System.out.println("处理过后的毕业时间：  "+graduateTime);
		}
		if(examNum==null&&name==null&&idCard==null){
//			hql="from StudentInfo s where (s.schoolAduitStatus=\'10002\' or s.schoolAduitStatus=\'10003\')";
			hql="from StudentInfo s where s.schoolAduitStatus!=\'10001\'";
		}else{
			hql="from StudentInfo s where s.schoolAduitStatus!=\'10001\'";
			if(!examNum.equals("")){
				hql+=" and s.examNum like \'%"+examNum+"%\'";
				if(!name.equals("")){
					hql+=" and s.name like \'%"+name+"%\'";
					if(!idCard.equals("")){
						hql+=" and s.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}
					}
				}else{
					if(!idCard.equals("")){
						hql+=" and s.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}
					}
				}
			}else{
				if(!name.equals("")){
					hql+=" and s.name like \'%"+name+"%\'";
					if(!idCard.equals("")){
						hql+=" and s.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}
					}
				}else{
					if(!idCard.equals("")){
						hql+=" and s.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.majorField.department.name like \'%"+departmentName+"%\'";
							}
						}
					}
				}
			}
		}
		if(sort!=null&&order!=null){
			hql+=" order by "+sort+" "+order;
		}
		System.out.println("最后查询的hql为：   "+hql);
		studentInfos=getAllStudentInfosByPage(hql,pageNow,pageRows);
//		System.out.println("studentInfos    :" +studentInfos.size());
		
		if(studentInfos!=null && studentInfos.size()>0){
			List<PageStudentInfo> pageStudentInfos=modelChangeToPageModel(studentInfos);
			DataGrid data=new DataGrid();
			data.setTotal(getTotalsBySAduitId(hql));
			data.setRows(pageStudentInfos);
			return data;
		}else{
			DataGrid data=new DataGrid();
			data.setRows(new ArrayList<PageStudentInfo>());
			data.setTotal(0L);
			return data;
		}
		
	}
	
	public List<PageStudentInfo> modelChangeToPageModel(List<StudentInfo> studentInfos){
		
		List<PageStudentInfo> pageStudentInfos=new ArrayList<PageStudentInfo>();
		//开始转换成页面所需要的实体
		for(StudentInfo info:studentInfos){
			PageStudentInfo pageStudent=new PageStudentInfo();
			BeanUtils.copyProperties(info,pageStudent);
			//System.out.println("原来info列表里面的基本信息 :   "+info.getId()+"  "+info.getGraduationSchool()+"  "+info.getIdCard() +"  "+info.getName());
			//System.out.println("基本信息：     "+pageStudent.getId()+"  "+pageStudent.getGraduationSchool()+"  "+pageStudent.getIdCard()+"  "+pageStudent.getName());
			
			EnumerationValue enumerationValue =null;
			//转换性别字段
			enumerationValue=enumerationValueDao.getEntity(info.getSexType());
			if(enumerationValue!=null){
				pageStudent.setSex(enumerationValue.getName());
			}
			
			//转换学院审核字段
			enumerationValue=enumerationValueDao.getEntity(info.getDepartmentAduitStatus());
			if(enumerationValue!=null){
				pageStudent.setDepartmentAduit(enumerationValue.getName());
			}
			
			//转换学校审核字段
			enumerationValue=enumerationValueDao.getEntity(info.getSchoolAduitStatus());
			if(enumerationValue!=null){
				pageStudent.setSchoolAduit(enumerationValue.getName());
			}
			
			//转换专业方向字段
			MajorField majorField=info.getMajorField();
			if(majorField!=null){
				pageStudent.setMajorFieldName(majorField.getName());
			}
			//转换生日日期格式
			Date date=info.getBirthday();
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
			enumerationValue=enumerationValueDao.getEntity(info.getNationType());
			if(enumerationValue!=null){
				pageStudent.setNation(enumerationValue.getName());
			}
			//转换政治面貌字段
			enumerationValue=enumerationValueDao.getEntity(info.getPoliticalFeatureType());
			if(enumerationValue!=null){
				pageStudent.setPoliticalFeature(enumerationValue.getName());
			}
			//转换户籍字段
			enumerationValue=enumerationValueDao.getEntity(info.getHouseholdType());
			if(enumerationValue!=null){
				pageStudent.setHousehold(enumerationValue.getName());
			}
			//转换学制字段
			enumerationValue=enumerationValueDao.getEntity(info.getSchoolLengthType());
			if(enumerationValue!=null){
				pageStudent.setSchoolLength(enumerationValue.getName());
			}
			//转换学历字段
			enumerationValue=enumerationValueDao.getEntity(info.getEducationType());
			if(enumerationValue!=null){
				pageStudent.setEducation(enumerationValue.getName());
			}
			//转换入学年份
			date=info.getEnterCollegeTime();
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
			date=info.getGraduateTime();
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
	
	public Long getTotals(String hql){
		hql="select count(*) "+hql;
		return studentInfoDao.getStudentInfosCount(hql);
	}
	
	public Long getTotalsByDAduitId(String hql){
		hql="select count(*) "+hql;
		//System.out.println("   总数据数:  "+studentInfoDao.getStudentInfosCount(hql));
		return studentInfoDao.getStudentInfosCount(hql);
	}
	
	public Long getTotalsBySAduitId(String hql){
		hql="select count(*) "+hql;
		//System.out.println("   总数据数:  "+studentInfoDao.getStudentInfosCount(hql));
		return studentInfoDao.getStudentInfosCount(hql);
	}

	@Override
	public boolean addStudentInfo(StudentInfo studentInfo) {
		// TODO Auto-generated method stub
		try {
			studentInfoDao.saveEntity(studentInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public StudentInfo findStudentInfoById(int id) {
		// TODO Auto-generated method stub
		return studentInfoDao.getEntity(id);
	}

	@Override
	public boolean updateStudentInfo(StudentInfo studentInfo) {
		// TODO Auto-generated method stub
		try {
			studentInfoDao.updateEntity(studentInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteSelectedStudentInfo(String idList) {
		// TODO Auto-generated method stub
		try {
			 studentInfoDao.deleteStudentInfo(stringToInt(idList));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//将逗号隔开的id列表转化为Integer数组
	public List<Integer> stringToInt(String idList){
		String []str_id=idList.split(",");
		List<Integer> ids=new ArrayList<Integer>();
		for(int i=0;i<str_id.length;i++){
			ids.add(Integer.parseInt(str_id[i]));
		}
		return ids;
	}

	@Override
	public boolean findStudentInfoByExamNum(String examNum) {
		// TODO Auto-generated method stub
		String hql ="from StudentInfo s where s.examNum=?";
		List<StudentInfo> stuInfo=studentInfoDao.findEntityByHQL(hql, examNum);
		if(stuInfo.size()>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
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

	@Override
	public boolean updateDoStuDepartmentAduit(String idList) {
		// TODO Auto-generated method stub
		try {
			List<Integer> ids=stringToInt(idList);
			List<EnumerationValue> values=enumerationValueDao.getEnumerationValueByEnumerationName(EnumerationType.ADUIT_STATUS);
			int enumerationValueId=0;
			for(EnumerationValue value:values){
				if(value.getName().equals("审核中")){
					enumerationValueId=value.getId();
				}
			}
			for(Integer id:ids){
				StudentInfo studentInfo=studentInfoDao.getEntity(id);
				studentInfo.setDepartmentAduitStatus(enumerationValueId);
				studentInfoDao.updateEntity(studentInfo);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean updateCancelStuDepartmentAduit(String idList) {
		// TODO Auto-generated method stub
		try {
			List<Integer> ids=stringToInt(idList);
			List<EnumerationValue> values=enumerationValueDao.getEnumerationValueByEnumerationName(EnumerationType.ADUIT_STATUS);
			int enumerationValueId=0;
			for(EnumerationValue value:values){
				if(value.getName().equals("未审核")){
					enumerationValueId=value.getId();
				}
			}
			for(Integer id:ids){
				StudentInfo studentInfo=studentInfoDao.getEntity(id);
				studentInfo.setDepartmentAduitStatus(enumerationValueId);
				studentInfoDao.updateEntity(studentInfo);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean updateDepartmentAduit(String idList) {
		// TODO Auto-generated method stub
		try {
			List<Integer> ids=stringToInt(idList);
			List<EnumerationValue> values=enumerationValueDao.getEnumerationValueByEnumerationName(EnumerationType.ADUIT_STATUS);
			int enumerationValueId=0;
			for(EnumerationValue value:values){
				if(value.getName().equals("通过")){
					enumerationValueId=value.getId();
				}
			}
			for(Integer id:ids){
				StudentInfo studentInfo=studentInfoDao.getEntity(id);
				studentInfo.setDepartmentAduitStatus(enumerationValueId);
				studentInfoDao.updateEntity(studentInfo);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean updateDepartmentInverseAduit(String idList) {
		// TODO Auto-generated method stub
		try {
			List<Integer> ids=stringToInt(idList);
			List<EnumerationValue> values=enumerationValueDao.getEnumerationValueByEnumerationName(EnumerationType.ADUIT_STATUS);
			int enumerationValueId=0;
			for(EnumerationValue value:values){
				if(value.getName().equals("不通过")){
					enumerationValueId=value.getId();
				}
			}
			for(Integer id:ids){
				StudentInfo studentInfo=studentInfoDao.getEntity(id);
				studentInfo.setDepartmentAduitStatus(enumerationValueId);
				studentInfoDao.updateEntity(studentInfo);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	

	
	@Override
	public boolean updateDoStuSchoolAduit(String idList) {
		// TODO Auto-generated method stub
		try {
			List<Integer> ids=stringToInt(idList);
			List<EnumerationValue> values=enumerationValueDao.getEnumerationValueByEnumerationName(EnumerationType.ADUIT_STATUS);
			int enumerationValueId=0;
			for(EnumerationValue value:values){
				if(value.getName().equals("审核中")){
					enumerationValueId=value.getId();
				}
			}
			for(Integer id:ids){
				StudentInfo studentInfo=studentInfoDao.getEntity(id);
				studentInfo.setSchoolAduitStatus(enumerationValueId);
				studentInfoDao.updateEntity(studentInfo);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean updateCancelStuSchoolAduit(String idList) {
		// TODO Auto-generated method stub
		try {
			List<Integer> ids=stringToInt(idList);
			List<EnumerationValue> values=enumerationValueDao.getEnumerationValueByEnumerationName(EnumerationType.ADUIT_STATUS);
			int enumerationValueId=0;
			for(EnumerationValue value:values){
				if(value.getName().equals("未审核")){
					enumerationValueId=value.getId();
				}
			}
			for(Integer id:ids){
				StudentInfo studentInfo=studentInfoDao.getEntity(id);
				studentInfo.setSchoolAduitStatus(enumerationValueId);
				studentInfoDao.updateEntity(studentInfo);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void updateSchoolAduit(Integer id) {
		// TODO Auto-generated method stub
		
		//取出“通过”这个枚举值的id
		List<EnumerationValue> values=enumerationValueDao.getEnumerationValueByEnumerationName(EnumerationType.ADUIT_STATUS);
		int enumerationValueId=0;
		for(EnumerationValue value:values){
			if(value.getName().equals("通过")){
				enumerationValueId=value.getId();
			}
		}
		//根据id取出学生源信息
		StudentInfo studentInfo=studentInfoDao.getEntity(id);
		studentInfo.setSchoolAduitStatus(enumerationValueId);
		//更新学校审核状态
		studentInfoDao.updateEntity(studentInfo);
	}
	
	@Override
	public boolean updateSchoolInverseAduit(String idList) {
		// TODO Auto-generated method stub
		try {
			List<Integer> ids=stringToInt(idList);
			List<EnumerationValue> values=enumerationValueDao.getEnumerationValueByEnumerationName(EnumerationType.ADUIT_STATUS);
			int enumerationValueId=0;
			for(EnumerationValue value:values){
				if(value.getName().equals("不通过")){
					enumerationValueId=value.getId();
				}
			}
			for(Integer id:ids){
				StudentInfo studentInfo=studentInfoDao.getEntity(id);
				studentInfo.setSchoolAduitStatus(enumerationValueId);
				studentInfoDao.updateEntity(studentInfo);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
}
