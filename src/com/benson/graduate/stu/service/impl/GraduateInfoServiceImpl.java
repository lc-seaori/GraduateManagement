package com.benson.graduate.stu.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.service.impl.BaseServiceImpl;
import com.benson.graduate.common.enumeration.EnumerationType;
import com.benson.graduate.stu.dao.GraduateInfoDao;
import com.benson.graduate.stu.model.GraduateInfo;
import com.benson.graduate.stu.pagemodel.PageGraduateInfo;
import com.benson.graduate.stu.service.GraduateInfoService;
import com.benson.graduate.sys.dao.EnumerationValueDao;
import com.benson.graduate.sys.model.EnumerationValue;

@Service("graduateInfoService")
public class GraduateInfoServiceImpl extends BaseServiceImpl implements
		GraduateInfoService {
	
	private GraduateInfoDao graduateInfoDao;
	@Resource(name="graduateInfoDao")
	public void setGraduateInfoDao(GraduateInfoDao graduateInfoDao) {
		this.graduateInfoDao = graduateInfoDao;
	}
	private EnumerationValueDao enumerationValueDao;
	@Resource(name="enumerationValueDao")
	public void setEnumerationValueDao(EnumerationValueDao enumerationValueDao) {
		this.enumerationValueDao = enumerationValueDao;
	}

	@Override
	public DataGrid getDataGrid(String code, String name, String idCard,
			String departmentName, String graduateTime, String sort,
			String order, int pageNow, int pageRows) {
		// TODO Auto-generated method stub
		System.out.println("getDataGrid()");
		String hql="";
		List<GraduateInfo> graduateInfo=null;
		System.out.println("测试参数：  "+code);
		//第一次datagrid自动发送请求时，三个字段为null
		if(graduateTime!=null&&!graduateTime.equals("")){
			//对毕业日期处理（精确到月）
			int index=graduateTime.indexOf('-');
			graduateTime=graduateTime. substring(0, index+3);
		}
		//一个为null就不会继续判断
		if(code==null&&name==null&&departmentName==null&&idCard==null&&graduateTime==null){
			hql="from GraduateInfo s";
		}else{
			hql="from GraduateInfo s";
			if(!code.equals("")){
				hql+=" where s.student.code like \'%"+code+"%\'";
				if(!name.equals("")){
					hql+=" and s.name like \'%"+name+"%\'";
					if(!idCard.equals("")){
						hql+=" and s.student.studentInfo.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}
					}
				}else{
					if(!idCard.equals("")){
						hql+=" and s.student.studentInfo.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}
					}
				}
			}else{
				if(!name.equals("")){
					hql+=" where s.name like \'%"+name+"%\'";
					if(!idCard.equals("")){
						hql+=" s.student.studentInfo.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}
					}
				}else{
					if(!idCard.equals("")){
						hql+=" where s.student.studentInfo.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" where s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" where s.student.department.name like \'%"+departmentName+"%\'";
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
		graduateInfo=getAllGraduateInfosByPage(hql,pageNow,pageRows);
		
		if(graduateInfo!=null && graduateInfo.size()>0){
			System.out.println("graduateInfo不为空");
			List<PageGraduateInfo> pageGraduateInfos=modelChangeToPageModel(graduateInfo);
			System.out.println("pageGraduateInfos大小为：   "+pageGraduateInfos.size());
			DataGrid data=new DataGrid();
			data.setTotal(getTotals(hql));
			data.setRows(pageGraduateInfos);
			return data;
		}else{
			DataGrid data=new DataGrid();
			data.setRows(new ArrayList<PageGraduateInfo>());
			data.setTotal(0L);
			return data;
		}
	}
	
	public DataGrid getDepartmentAduitDataGrid(String code,String name,String idCard,String departmentName,String graduateTime,String sort,String order,int pageNow,int pageRows) {
		String hql="";
		List<GraduateInfo> graduateInfo=null;
		System.out.println("测试参数：  "+code);
		//第一次datagrid自动发送请求时，三个字段为null
		if(graduateTime!=null&&!graduateTime.equals("")){
			//对毕业日期处理（精确到月）
			int index=graduateTime.indexOf('-');
			graduateTime=graduateTime. substring(0, index+3);
		}
		//一个为null就不会继续判断
		if(code==null&&name==null&&departmentName==null&&idCard==null&&graduateTime==null){
			hql="from GraduateInfo s where s.departmentGAduitStatus!=\'10001\'";
		}else{
			hql="from GraduateInfo s where s.departmentGAduitStatus!=\'10001\'";
			if(!code.equals("")){
				hql+=" and s.student.code like \'%"+code+"%\'";
				if(!name.equals("")){
					hql+=" and s.name like \'%"+name+"%\'";
					if(!idCard.equals("")){
						hql+=" and s.student.studentInfo.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}
					}
				}else{
					if(!idCard.equals("")){
						hql+=" and s.student.studentInfo.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}
					}
				}
			}else{
				if(!name.equals("")){
					hql+=" and s.name like \'%"+name+"%\'";
					if(!idCard.equals("")){
						hql+=" s.student.studentInfo.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}
					}
				}else{
					if(!idCard.equals("")){
						hql+=" and s.student.studentInfo.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
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
		graduateInfo=getAllGraduateInfosByPage(hql,pageNow,pageRows);
		
		if(graduateInfo!=null && graduateInfo.size()>0){
			System.out.println("graduateInfo不为空");
			List<PageGraduateInfo> pageGraduateInfos=modelChangeToPageModel(graduateInfo);
			System.out.println("pageGraduateInfos大小为：   "+pageGraduateInfos.size());
			DataGrid data=new DataGrid();
			data.setTotal(getTotalsByDAduitId(hql));
			data.setRows(pageGraduateInfos);
			return data;
		}else{
			DataGrid data=new DataGrid();
			data.setRows(new ArrayList<PageGraduateInfo>());
			data.setTotal(0L);
			return data;
		}
	}
	
	public DataGrid getSchoolAduitDataGrid(String code,String name,String idCard,String departmentName,String graduateTime,String sort,String order,int pageNow,int pageRows) {
		String hql="";
		List<GraduateInfo> graduateInfo=null;
		System.out.println("测试参数：  "+code);
		//第一次datagrid自动发送请求时，三个字段为null
		if(graduateTime!=null&&!graduateTime.equals("")){
			//对毕业日期处理（精确到月）
			int index=graduateTime.indexOf('-');
			graduateTime=graduateTime. substring(0, index+3);
		}
		//一个为null就不会继续判断
		if(code==null&&name==null&&departmentName==null&&idCard==null&&graduateTime==null){
			hql="from GraduateInfo s where s.schoolGAduitStatus!=\'10001\'";
		}else{
			hql="from GraduateInfo s where s.schoolGAduitStatus!=\'10001\'";
			if(!code.equals("")){
				hql+=" and s.student.code like \'%"+code+"%\'";
				if(!name.equals("")){
					hql+=" and s.name like \'%"+name+"%\'";
					if(!idCard.equals("")){
						hql+=" and s.student.studentInfo.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}
					}
				}else{
					if(!idCard.equals("")){
						hql+=" and s.student.studentInfo.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}
					}
				}
			}else{
				if(!name.equals("")){
					hql+=" and s.name like \'%"+name+"%\'";
					if(!idCard.equals("")){
						hql+=" s.student.studentInfo.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}
					}
				}else{
					if(!idCard.equals("")){
						hql+=" and s.student.studentInfo.idCard like \'%"+idCard+"%\'";
						if(!graduateTime.equals("")){
							hql+=" and s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}
					}else{
						if(!graduateTime.equals("")){
							hql+=" and s.student.studentInfo.graduateTime like \'%"+graduateTime+"%\'";
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
							}
						}else{
							if(!departmentName.equals("")){
								hql+=" and s.student.department.name like \'%"+departmentName+"%\'";
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
		graduateInfo=getAllGraduateInfosByPage(hql,pageNow,pageRows);
		
		if(graduateInfo!=null && graduateInfo.size()>0){
			List<PageGraduateInfo> pageGraduateInfos=modelChangeToPageModel(graduateInfo);
			DataGrid data=new DataGrid();
			data.setTotal(getTotalsBySAduitId(hql));
			data.setRows(pageGraduateInfos);
			return data;
		}else{
			DataGrid data=new DataGrid();
			data.setRows(new ArrayList<PageGraduateInfo>());
			data.setTotal(0L);
			return data;
		}
	}
	
	public List<GraduateInfo> getAllGraduateInfosByPage(String hql,int pageNow,int pageRows) {
		return graduateInfoDao.findEntityByHQL(hql, pageNow, pageRows);
	}
	
	public List<PageGraduateInfo> modelChangeToPageModel(List<GraduateInfo> graduateInfo){
		List<PageGraduateInfo> pageGraduateInfos=new ArrayList<PageGraduateInfo>();
		for(GraduateInfo gra:graduateInfo){
			PageGraduateInfo pageGraduateInfo=new PageGraduateInfo();
			BeanUtils.copyProperties(gra,pageGraduateInfo);
			
			//转换学院审核
			EnumerationValue enumerationValue=enumerationValueDao.getEntity(gra.getDepartmentGAduitStatus());
			if(enumerationValue!=null){
				pageGraduateInfo.setDepartmentGAduit(enumerationValue.getName());
			}
			//转换学校审核
			enumerationValue=enumerationValueDao.getEntity(gra.getSchoolGAduitStatus());
			if(enumerationValue!=null){
				pageGraduateInfo.setSchoolGAduit(enumerationValue.getName());
			}
			//转换单位性质
			enumerationValue=enumerationValueDao.getEntity(gra.getUnitType());
			if(enumerationValue!=null){
				pageGraduateInfo.setUnitTy(enumerationValue.getName());
			}
			//转换应聘方式
			enumerationValue=enumerationValueDao.getEntity(gra.getApplyType());
			if(enumerationValue!=null){
				pageGraduateInfo.setApplyTy(enumerationValue.getName());
			}
			
			pageGraduateInfos.add(pageGraduateInfo);
		}
		return pageGraduateInfos;
	}
	
	public Long getTotals(String hql){
		hql="select count(*) "+hql;
		return graduateInfoDao.getGraduateInfosCount(hql);
	}
	
	public Long getTotalsByDAduitId(String hql){
		hql="select count(*) "+hql;
		//System.out.println("   总数据数:  "+studentInfoDao.getStudentInfosCount(hql));
		return graduateInfoDao.getGraduateInfosCount(hql);
	}
	
	public Long getTotalsBySAduitId(String hql){
		hql="select count(*) "+hql;
		//System.out.println("   总数据数:  "+studentInfoDao.getStudentInfosCount(hql));
		return graduateInfoDao.getGraduateInfosCount(hql);
	}

	@Override
	public GraduateInfo findGraduateInfoById(Integer id) {
		// TODO Auto-generated method stub
		return graduateInfoDao.getEntity(id);
	}

	@Override
	public boolean updateGraduateInfo(GraduateInfo graduateInfo) {
		// TODO Auto-generated method stub
		try {
			graduateInfoDao.updateEntity(graduateInfo);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean deleteSelectedGraduateInfo(String idList) {
		// TODO Auto-generated method stub
		try {
			 graduateInfoDao.deleteGraduateInfo(stringToInt(idList));
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
	public boolean addGraduateInfo(GraduateInfo graduateInfo) {
		// TODO Auto-generated method stub
		try {
			graduateInfoDao.saveEntity(graduateInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean updateGraDepartmentAduit(String idList) {
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
				GraduateInfo graduateInfo=graduateInfoDao.getEntity(id);
				graduateInfo.setDepartmentGAduitStatus(enumerationValueId);
				graduateInfoDao.updateEntity(graduateInfo);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	
	@Override
	public boolean updateCancelGraDepartmentAduit(String idList) {
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
				GraduateInfo graduateInfo=graduateInfoDao.getEntity(id);
				graduateInfo.setDepartmentGAduitStatus(enumerationValueId);
				graduateInfoDao.updateEntity(graduateInfo);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean updateDepartmentGAduit(String idList) {
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
				GraduateInfo graduateInfo=graduateInfoDao.getEntity(id);
				graduateInfo.setDepartmentGAduitStatus(enumerationValueId);
				graduateInfoDao.updateEntity(graduateInfo);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean updateDepartmentInverseGAduit(String idList) {
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
				GraduateInfo graduateInfo=graduateInfoDao.getEntity(id);
				graduateInfo.setDepartmentGAduitStatus(enumerationValueId);
				graduateInfoDao.updateEntity(graduateInfo);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean updateDoGraSchoolGAduit(String idList) {
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
				GraduateInfo graduateInfo=graduateInfoDao.getEntity(id);
				graduateInfo.setSchoolGAduitStatus(enumerationValueId);
				graduateInfoDao.updateEntity(graduateInfo);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean updateCancelGraSchoolGAduit(String idList) {
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
				GraduateInfo graduateInfo=graduateInfoDao.getEntity(id);
				graduateInfo.setSchoolGAduitStatus(enumerationValueId);
				graduateInfoDao.updateEntity(graduateInfo);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean updateSchoolGAduit(String idList) {
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
				GraduateInfo graduateInfo=graduateInfoDao.getEntity(id);
				graduateInfo.setSchoolGAduitStatus(enumerationValueId);
				graduateInfoDao.updateEntity(graduateInfo);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean updateSchoolInverseGAduit(String idList) {
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
				GraduateInfo graduateInfo=graduateInfoDao.getEntity(id);
				graduateInfo.setSchoolGAduitStatus(enumerationValueId);
				graduateInfoDao.updateEntity(graduateInfo);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<GraduateInfo> findGraduateInfosForExport(int departmentId,
			int majorId, int clazzId) {
		// TODO Auto-generated method stub
		//通过审核的就业信息
		String hql="from GraduateInfo g where g.schoolGAduitStatus=10003";
		if(departmentId!=0){
			if(majorId!=0){
				if(clazzId!=0){
					//某个班的学生就业信息
					hql+=" and g.student.clazz.id=?";
					System.out.println("hql    :"+hql);
					return graduateInfoDao.findEntityByHQL(hql, clazzId);
				}else{
					//某个专业的学生就业信息
					hql+=" and g.student.studentInfo.majorField.id=?";
					System.out.println("hql    :"+hql);
					return graduateInfoDao.findEntityByHQL(hql, majorId);
				}
			}else{
				//获取某个学院的学生就业信息
				hql+=" and g.student.department.id=?";
				System.out.println("hql    :"+hql);
				return graduateInfoDao.findEntityByHQL(hql, departmentId);
			}
		}else{
			//查找所有学生源信息
			System.out.println("hql    :"+hql);
			return graduateInfoDao.findEntityByHQL(hql);
		}
	}

	@Override
	public boolean findGraduateInfoByStuId(Integer stuId) {
		// TODO Auto-generated method stub
		String hql="from GraduateInfo g where g.student.id=? and g.schoolGAduitStatus=10003";
		List<GraduateInfo> infos=graduateInfoDao.findEntityByHQL(hql, stuId);
		if(infos!=null&&infos.size()!=0){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	
}
