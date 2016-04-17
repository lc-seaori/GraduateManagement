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
import com.benson.graduate.stu.dao.StudentDao;
import com.benson.graduate.stu.dao.StudentInfoDao;
import com.benson.graduate.stu.model.Student;
import com.benson.graduate.stu.pagemodel.PageStudent;
import com.benson.graduate.stu.service.StudentService;
import com.benson.graduate.sys.dao.EnumerationValueDao;
import com.benson.graduate.sys.model.EnumerationValue;

@Service("studentService")
public class StudentServiceImpl extends BaseServiceImpl implements
		StudentService {
	
	private StudentDao studentDao;
	@Resource(name="studentDao")
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	private EnumerationValueDao enumerationValueDao;
	@Resource(name="enumerationValueDao")
	public void setEnumerationValueDao(EnumerationValueDao enumerationValueDao) {
		this.enumerationValueDao = enumerationValueDao;
	}
	private StudentInfoDao studentInfoDao;
	@Resource(name="studentInfoDao")
	public void setStudentInfoDao(StudentInfoDao studentInfoDao) {
		this.studentInfoDao = studentInfoDao;
	}

	@Override
	public boolean findStudentByCode(String code) {
		// TODO Auto-generated method stub
		String hql="from Student s where s.code=?";
		List<Student> stus=studentDao.findEntityByHQL(hql, code);
		if(stus.size()>0&&stus!=null){
			return true;
		}else{
			return false;
		} 
	}

	@Override
	public boolean saveStudent(Student student) {
		// TODO Auto-generated method stub
		try {
			studentDao.saveEntity(student);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public DataGrid getDataGrid(String code, String name,
			String departmentName, String clazzName, String graduateTime,
			String sort, String order, int pageNow, int pageRows,boolean isNot) {
		// TODO Auto-generated method stub
		
		String hql="";
		//第一次datagrid自动发送请求时，三个字段为null
		List<Student> students=null;
		if(graduateTime!=null&&!graduateTime.equals("")){
			//对毕业日期处理（精确到月）
			int index=graduateTime.indexOf('-');
			graduateTime=graduateTime. substring(0, index+3);
		}
		
		if(isNot){
			hql="from Student s where s.id not in (select g.student.id from GraduateInfo g)";
		}else{
			hql="from Student s";
		}
		
		//一个为null就不会继续判断
		if(code==null&&name==null&&departmentName==null&&clazzName==null&&graduateTime==null){
			hql+="";
		}else{
			if(!code.equals("")){
				hql+=" and s.code like \'%"+code+"%\'";
				if(!name.equals("")){
					hql+=" and s.name like \'%"+name+"%\'";
					if(!clazzName.equals("")){
						hql+=" and s.clazz.name like \'%"+clazzName+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.department.name like \'%"+departmentName+"%\'";
							}
						}
					}
				}else{
					if(!clazzName.equals("")){
						hql+=" and s.clazz.name like \'%"+clazzName+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.department.name like \'%"+departmentName+"%\'";
							}
						}
					}
				}
			}else{
				if(!name.equals("")){
					hql+=" and s.name like \'%"+name+"%\'";
					if(!clazzName.equals("")){
						hql+=" and s.clazz.name like \'%"+clazzName+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.department.name like \'%"+departmentName+"%\'";
							}
						}
					}
				}else{
					if(!clazzName.equals("")){
						hql+=" and s.clazz.name like \'%"+clazzName+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.department.name like \'%"+departmentName+"%\'";
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
		students=getAllStudentsByPage(hql,pageNow,pageRows);
		
		if(students!=null && students.size()>0){
			List<PageStudent> pageStudents=modelChangeToPageModel(students);
			DataGrid data=new DataGrid();
			data.setTotal(getTotals(hql));
			data.setRows(pageStudents);
			return data;
		}else{
			DataGrid data=new DataGrid();
			data.setRows(new ArrayList<PageStudent>());
			data.setTotal(0L);
			return data;
		}
	}
	
	@Override
	public DataGrid getDataGrid() {
		// TODO Auto-generated method stub
		String hql="from Student s where s.id not in (select g.student.id from GraduateInfo g)";
		System.out.println("下拉表单的hql :"+hql);
		List<Student> students=null;
		students=getAllStudents(hql);
		
		if(students!=null && students.size()>0){
			System.out.println("student列表不为空");
			List<PageStudent> pageStudents=modelChangeToPageModel(students);
			for(PageStudent stu:pageStudents){
				System.out.println(stu.getCode()+"   "+stu.getName());
			}
			DataGrid data=new DataGrid();
			data.setTotal(getTotals(hql));
			data.setRows(pageStudents);
			return data;
		}else{
			System.out.println("student列表为空");
			DataGrid data=new DataGrid();
			data.setRows(new ArrayList<PageStudent>());
			data.setTotal(0L);
			return data;
		}
	}
	
	public List<Student> getAllStudentsByPage(String hql,int pageNow,int pageRows) {
		return studentDao.findEntityByHQL(hql, pageNow, pageRows);
	}
	
	public List<Student> getAllStudents(String hql){
		return studentDao.findEntityByHQL(hql);
	}
	
	public List<PageStudent> modelChangeToPageModel(List<Student> students){
		List<PageStudent> pageStudents=new ArrayList<PageStudent>();
		for(Student stu:students){
			PageStudent pageStudent=new PageStudent();
			BeanUtils.copyProperties(stu,pageStudent);
			
			//转换学院字段
			pageStudent.setDepartmentName(stu.getDepartment().getName());
			//转换班别字段
			pageStudent.setClazzName(stu.getClazz().getName());
			//转换学籍状态字段
			EnumerationValue enumerationValue=enumerationValueDao.getEntity(stu.getSchoolStatus());
			if(enumerationValue!=null){
				pageStudent.setSchoolStatus(enumerationValue.getName());
			}
			//转换毕业时间格式
			Date date=stu.getStudentInfo().getGraduateTime();
			if(date!=null){
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
				String graduationTime=null;
				try {
					graduationTime=dateFormat.format(date);
					//System.out.println(dateShow);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pageStudent.setGraduationTime(graduationTime);
			}
			
			pageStudents.add(pageStudent);
		}
		return pageStudents;
	}
	
	public Long getTotals(String hql){
		hql="select count(*) "+hql;
		return studentDao.getStudentsCount(hql);
	}

	@Override
	public Student findStudentById(Integer id) {
		// TODO Auto-generated method stub
		return studentDao.getEntity(id);
	}

	@Override
	public boolean updateStudent(Student stu) {
		// TODO Auto-generated method stub
		try {
			studentDao.updateEntity(stu);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean deleteSelectedStudent(String idList) {
		// TODO Auto-generated method stub
		List<Integer> ids=stringToInt(idList);
		try {
			for(Integer id:ids){
				Student student = studentDao.getEntity(id);
				//设置学校审核的状态为审核中
				student.getStudentInfo().setSchoolAduitStatus(10001);
				studentInfoDao.updateEntity(student.getStudentInfo());
				//删除
				studentDao.deleteStudent(id);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
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
	public String findStudentNameById(Integer id) {
		// TODO Auto-generated method stub
		return studentDao.getStudentNameById(id);
	}

	@Override
	public List<Student> findStudentsForExport(int departmentId, int majorId,
			int clazzId) {
		// TODO Auto-generated method stub
		String hql="from Student s";
		if(departmentId!=0){
			if(majorId!=0){
				if(clazzId!=0){
					//某个班的学生源信息
					hql+=" where s.clazz.id=?";
					System.out.println("hql    :"+hql);
					return studentDao.findEntityByHQL(hql, clazzId);
				}else{
					//某个专业的学生源信息
					hql+=" where s.studentInfo.majorField.id=?";
					System.out.println("hql    :"+hql);
					return studentDao.findEntityByHQL(hql, majorId);
				}
			}else{
				//获取某个学院的学生源信息
				hql+=" where s.department.id=?";
				System.out.println("hql    :"+hql);
				return studentDao.findEntityByHQL(hql, departmentId);
			}
		}else{
			//查找所有学生源信息
			System.out.println("hql    :"+hql);
			return studentDao.findEntityByHQL(hql);
		}
	}

	@Override
	public List<Student> findStudentsByClazzNameAndTime(String clazzName,String graTime) {
		// TODO Auto-generated method stub
		String hql="from Student s where s.clazz.name=? and s.studentInfo.graduateTime like \'%"+graTime+"%\'";
		return studentDao.findEntityByHQL(hql, clazzName);
	}

	@Override
	public List<Student> findStudentsByDeparmentId(Integer departmentId,String graTime) {
		// TODO Auto-generated method stub
		String hql="from Student s where s.department.id=? and s.studentInfo.graduateTime like \'%"+graTime+"%\'";
		return studentDao.findEntityByHQL(hql, departmentId);
	}

	@Override
	public List<Student> findStudentsByMajorId(Integer majorId, String graTime) {
		// TODO Auto-generated method stub
		String hql="from Student s where s.studentInfo.majorField.id=? and s.studentInfo.graduateTime like \'%"+graTime+"%\'";
		return studentDao.findEntityByHQL(hql, majorId);
	}

	@Override
	public List<Student> findStudentsByClazzId(Integer clazzId, String graTime) {
		// TODO Auto-generated method stub
		String hql="from Student s where s.clazz.id=? and s.studentInfo.graduateTime like \'%"+graTime+"%\'";
		return studentDao.findEntityByHQL(hql, clazzId);
	}
}
