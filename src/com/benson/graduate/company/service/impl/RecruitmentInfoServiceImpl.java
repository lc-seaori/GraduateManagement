package com.benson.graduate.company.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.service.impl.BaseServiceImpl;
import com.benson.graduate.company.dao.RecruitmentInfoDao;
import com.benson.graduate.company.dao.RecruitmentUnitDao;
import com.benson.graduate.company.model.RecruitmentInfo;
import com.benson.graduate.company.model.RecruitmentUnit;
import com.benson.graduate.company.pagemodel.PageRecruitmentInfo;
import com.benson.graduate.company.service.RecruitmentInfoService;
import com.benson.graduate.stu.pagemodel.PageGraduateInfo;
import com.benson.graduate.sys.dao.EnumerationValueDao;
import com.benson.graduate.sys.model.EnumerationValue;

@Service("recruitmentInfoService")
public class RecruitmentInfoServiceImpl extends BaseServiceImpl implements
		RecruitmentInfoService {

	private RecruitmentInfoDao recruitmentInfoDao;
	@Resource(name="recruitmentInfoDao")
	public void setRecruitmentInfoDao(RecruitmentInfoDao recruitmentInfoDao) {
		this.recruitmentInfoDao = recruitmentInfoDao;
	}
	private EnumerationValueDao enumerationValueDao;
	@Resource(name="enumerationValueDao")
	public void setEnumerationValueDao(EnumerationValueDao enumerationValueDao) {
		this.enumerationValueDao = enumerationValueDao;
	}
	private RecruitmentUnitDao recruitmentUnitDao;
	@Resource(name="recruitmentUnitDao")
	public void setRecruitmentUnitDao(RecruitmentUnitDao recruitmentUnitDao) {
		this.recruitmentUnitDao = recruitmentUnitDao;
	}

	@Override
	public List<RecruitmentInfo> getAllRecruitmentInfosByUnitId(Integer id) {
		// TODO Auto-generated method stub
		String hql ="from RecruitmentInfo r where r.recruitmentUnit.id=?";
		return recruitmentInfoDao.findEntityByHQL(hql, id);
	}

	//这个没有公司名称
	@Override
	public List<PageRecruitmentInfo> changeToPageModel(
			List<RecruitmentInfo> infos) {
		// TODO Auto-generated method stub
		List<PageRecruitmentInfo> recruitmentInfos=new ArrayList<PageRecruitmentInfo>();
		for(RecruitmentInfo info:infos){
			PageRecruitmentInfo pageInfo=new PageRecruitmentInfo();
			BeanUtils.copyProperties(info, pageInfo);
			//开始转换字段
			//转换月薪范围字段
			EnumerationValue value=enumerationValueDao.getEntity(info.getMonthlySalary());
			if(value!=null){
				pageInfo.setMonthlySalary(value.getName());
				System.out.println(value.getId()+"   "+value.getName());
			}
			//转换工作性质字段
			value=enumerationValueDao.getEntity(info.getWorkType());
			if(value!=null){
				pageInfo.setWorkType(value.getName());
				System.out.println(value.getId()+"   "+value.getName());
			}
			//转换行业领域
			value=enumerationValueDao.getEntity(info.getIndustryType());
			if(value!=null){
				pageInfo.setIndustryType(value.getName());
				System.out.println(value.getId()+"   "+value.getName()+"  industryType ");
			}
			//转换学历要求
			value=enumerationValueDao.getEntity(info.getEducationType());
			if(value!=null){
				pageInfo.setEducationType(value.getName());
				System.out.println(value.getId()+"   "+value.getName());
			}
			//转换发布时间和截止时间
			Date date1=info.getReleaseTime();
			Date date2=info.getEndTime();
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			try {
				pageInfo.setReleaseTime(dateFormat.format(date1));
				pageInfo.setEndTime(dateFormat.format(date2));
				//System.out.println(dateShow);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			recruitmentInfos.add(pageInfo);
		}
		return recruitmentInfos;
	}


	@Override
	public DataGrid getDataGrid(String position, String unitName,
			String workTy, String industryTy, String educationTy, String sort,
			String order, int pageNow, int pageRows) {
		// TODO Auto-generated method stub
		String hql="";
		List<RecruitmentInfo> recruitmentInfos=null;
		//第一次datagrid自动发送请求时，五个个字段为null
		//一个为null就不会继续判断
		if(position==null&&unitName==null&&workTy==null&&industryTy==null&&educationTy==null){
			hql="from RecruitmentInfo r";
		}else{
			hql="from RecruitmentInfo r";
			if(!position.equals("")){
				hql+=" where r.position like \'%"+position+"%\'";
				if(!unitName.equals("")){
					hql+=" and r.recruitmentUnit.unitName like \'%"+unitName+"%\'";
				}
			}else{
				if(!unitName.equals("")){
					hql+=" where r.recruitmentUnit.unitName like \'%"+unitName+"%\'";
				}
			}
			
			//工作性质
			String tempHql="";
			List<EnumerationValue> values=null;
			if(!workTy.equals("")){
				tempHql="from EnumerationValue e where e.name like \'%"+workTy+"%\'";
				System.out.println("tempHql"+tempHql);
				values=enumerationValueDao.findEntityByHQL(tempHql);
				if(position.equals("")&&unitName.equals("")){
					hql+=" where r.workType in (";
				}else{
					hql+=" and r.workType in (";
				}
				if(values!=null&&values.size()!=0){
					for(int i=0;i<values.size();i++){
						System.out.println(values.get(i).getId()+"   "+values.get(i).getName());
						hql+=values.get(i).getId();
						if(i<(values.size()-1)){
							hql+=",";
						}
					}
				}else{
					//没有相对应的id的话，默认设为0
					hql+=0;
				}
				hql+=")";
				System.out.println("workTy测试hql  :"+hql);
			}
			if(!industryTy.equals("")){
				//行业领域
				tempHql="from EnumerationValue e where e.name like \'%"+industryTy+"%\'";
				System.out.println("tempHql"+tempHql);
				values=enumerationValueDao.findEntityByHQL(tempHql);
				if(position.equals("")&&unitName.equals("")&&workTy.equals("")){
					hql+=" where r.industryType in (";
				}else{
					hql+=" and r.industryType in (";
				}
				if(values!=null&&values.size()!=0){
					for(int i=0;i<values.size();i++){
						System.out.println(values.get(i).getId()+"   "+values.get(i).getName());
						hql+=values.get(i).getId();
						if(i<(values.size()-1)){
							hql+=",";
						}
					}
				}else{
					//没有相对应的id的话，默认设为0
					hql+=0;
				}
				hql+=")";
				System.out.println("industryTy测试hql  :"+hql);
			}
			if(!educationTy.equals("")){
				//学历要求
				tempHql="from EnumerationValue e where e.name like \'%"+educationTy+"%\'";
				System.out.println("tempHql"+tempHql);
				values=enumerationValueDao.findEntityByHQL(tempHql);
				if(position.equals("")&&unitName.equals("")&&workTy.equals("")&&industryTy.equals("")){
					hql+=" where r.educationType in (";
				}else{
					hql+=" and r.educationType in (";
				}
				if(values!=null&&values.size()!=0){
					for(int i=0;i<values.size();i++){
						System.out.println(values.get(i).getId()+"   "+values.get(i).getName());
						hql+=values.get(i).getId();
						if(i<(values.size()-1)){
							hql+=",";
						}
					}
				}else{
					//没有相对应的id的话，默认设为0
					hql+=0;
				}
				hql+=")";
				System.out.println("educationTy测试hql  :"+hql);
			}
		}
		if(sort!=null&&order!=null){
			hql+=" order by "+sort+" "+order;
		}
		System.out.println("最后查询的hql为：   "+hql);
		recruitmentInfos=getAllRecruitmentInfosByPage(hql,pageNow,pageRows);
		
		if(recruitmentInfos!=null && recruitmentInfos.size()>0){
			System.out.println("recruitmentInfo不为空");
			List<PageRecruitmentInfo> pageGraduateInfos=modelChangeToPageModel(recruitmentInfos);
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
	
	public List<RecruitmentInfo> getAllRecruitmentInfosByPage(String hql,int pageNow,int pageRows) {
		return recruitmentInfoDao.findEntityByHQL(hql, pageNow, pageRows);
	}
	
	//转换公司名称
	public List<PageRecruitmentInfo> modelChangeToPageModel(List<RecruitmentInfo> recruitmentInfos){
		List<PageRecruitmentInfo> pageRecruitmentInfos=new ArrayList<PageRecruitmentInfo>();
		for(RecruitmentInfo info:recruitmentInfos){
			PageRecruitmentInfo pageInfo=new PageRecruitmentInfo();
			BeanUtils.copyProperties(info,pageInfo);
			
			EnumerationValue enumerationValue =null;
			//转换工资字段
			enumerationValue=enumerationValueDao.getEntity(info.getMonthlySalary());
			if(enumerationValue!=null){
				pageInfo.setMonthlySalary(enumerationValue.getName());
			}
			//转换工作性质字段
			enumerationValue=enumerationValueDao.getEntity(info.getWorkType());
			if(enumerationValue!=null){
				pageInfo.setWorkType(enumerationValue.getName());
			}
			//转换行业领域字段
			enumerationValue=enumerationValueDao.getEntity(info.getIndustryType());
			if(enumerationValue!=null){
				pageInfo.setIndustryType(enumerationValue.getName());
			}
			//转换学历要求字段
			enumerationValue=enumerationValueDao.getEntity(info.getEducationType());
			if(enumerationValue!=null){
				pageInfo.setEducationType(enumerationValue.getName());
			}
			//转换发布时间和截止时间
			Date date1=info.getReleaseTime();
			Date date2=info.getEndTime();
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			try {
				pageInfo.setReleaseTime(dateFormat.format(date1));
				pageInfo.setEndTime(dateFormat.format(date2));
				//System.out.println(dateShow);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//转换公司名称
			pageInfo.setUnitName(info.getRecruitmentUnit().getUnitName());
			
			pageRecruitmentInfos.add(pageInfo);
		}
		return pageRecruitmentInfos;
	}

	public Long getTotals(String hql){
		hql="select count(*) "+hql;
		return recruitmentInfoDao.getRecruitmentInfosCount(hql);
	}

	@Override
	public boolean addRecruitmentInfo(RecruitmentInfo info) {
		// TODO Auto-generated method stub
		try {
			recruitmentInfoDao.saveEntity(info);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public RecruitmentInfo findRecruitmentInfoById(Integer id) {
		// TODO Auto-generated method stub
		return recruitmentInfoDao.getEntity(id);
	}

	@Override
	public boolean updateRecruitmentInfo(RecruitmentInfo info) {
		// TODO Auto-generated method stub
		try {
			recruitmentInfoDao.updateEntity(info);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean deleteSelectedRecruitmentInfo(String idList) {
		// TODO Auto-generated method stub
		try {
			recruitmentInfoDao.deleteRecruitmentInfos(stringToInt(idList));
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
	public List<RecruitmentInfo> getAllRecruitmentInfos(
			Integer recUnitId, Integer timeId) {
		// TODO Auto-generated method stub
		String sql="select * from company_recruitment_info r";
		
		if(recUnitId!=0){
			//判断recUnitId是否为0（为0就是查询所有招聘信息）
			sql+=" where r.RECRUITMENTUNIT_ID=?";
			if(timeId!=0){
				//判断发布时间id
				if(timeId==1){
					//如果是1，则是一天内
					sql+=" and r.RELEASE_TIME>=date_sub(NOW(),INTERVAL 1 DAY) and r.RELEASE_TIME<NOW()";
				}else if(timeId==2){
					//三天内
					sql+=" and r.RELEASE_TIME>=date_sub(NOW(),INTERVAL 3 DAY) and r.RELEASE_TIME<NOW()";
				}else if(timeId==3){
					//一周内
					sql+=" and r.RELEASE_TIME>=date_sub(NOW(),INTERVAL 1 WEEK) and r.RELEASE_TIME<NOW()";
				}else if(timeId==4){
					//一个月内
					sql+=" and r.RELEASE_TIME>=date_sub(NOW(),INTERVAL 1 MONTH) and r.RELEASE_TIME<NOW()";
				}
			}
		}else{
			if(timeId!=0){
				//判断发布时间id
				if(timeId==1){
					//如果是1，则是一天内
					sql+=" where r.RELEASE_TIME>=date_sub(NOW(),INTERVAL 1 DAY) and r.RELEASE_TIME<NOW()";
				}else if(timeId==2){
					//三天内
					sql+=" where r.RELEASE_TIME>=date_sub(NOW(),INTERVAL 3 DAY) and r.RELEASE_TIME<NOW()";
				}else if(timeId==3){
					//一周内
					sql+=" where r.RELEASE_TIME>=date_sub(NOW(),INTERVAL 1 WEEK) and r.RELEASE_TIME<NOW()";
				}else if(timeId==4){
					//一个月内
					sql+=" where r.RELEASE_TIME>=date_sub(NOW(),INTERVAL 1 MONTH) and r.RELEASE_TIME<NOW()";
				}
			}
		}
		System.out.println("sql 为：  "+sql);
		List<RecruitmentInfo> recruitmentInfos=new ArrayList<RecruitmentInfo>();
		List<Object[]> objects=null;
		try {
			if(recUnitId!=0){
				objects= recruitmentInfoDao.findEntityBySql(sql, recUnitId);
			}else{
				objects= recruitmentInfoDao.findEntityBySql(sql);
			}
			//二次封装
			if(objects!=null){
				for(Object[] object:objects){
					RecruitmentInfo info=new RecruitmentInfo();
					info.setId((Integer)object[0]);
					info.setPosition((String)object[1]);
					info.setMonthlySalary((Integer)object[2]);
					info.setWorkType((Integer)object[3]);
					info.setEducationType((Integer)object[4]);
					info.setHireCount((Integer)object[5]);
					info.setReleaseTime((Date)object[6]);
					info.setEndTime((Date)object[7]);
					info.setPositionDescription((String)object[8]);
					//根据公司单位id查找公司
					RecruitmentUnit recruitmentUnit=recruitmentUnitDao.getEntity((Integer)object[9]);
					info.setRecruitmentUnit(recruitmentUnit);
					info.setIndustryType((Integer)object[10]);
					recruitmentInfos.add(info);
				}
			}
			return recruitmentInfos;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}