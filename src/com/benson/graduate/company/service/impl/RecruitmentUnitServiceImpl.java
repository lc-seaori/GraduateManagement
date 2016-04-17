package com.benson.graduate.company.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.service.impl.BaseServiceImpl;
import com.benson.graduate.company.dao.RecruitmentUnitDao;
import com.benson.graduate.company.model.RecruitmentUnit;
import com.benson.graduate.company.pagemodel.PageRecruitmentUnit;
import com.benson.graduate.company.service.RecruitmentUnitService;
import com.benson.graduate.sys.dao.EnumerationValueDao;
import com.benson.graduate.sys.model.EnumerationValue;

@Service("recruitmentUnitService")
public class RecruitmentUnitServiceImpl extends BaseServiceImpl implements
		RecruitmentUnitService {
	
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
	public DataGrid getDataGrid(String unitCode, String unitName,
			String unitTy, String recruitmentTy, String sort, String order,
			int pageNow, int pageRows) {
		// TODO Auto-generated method stub
		String hql="";
		//第一次datagrid自动发送请求时，四个字段为null
		List<RecruitmentUnit> recruitmentUnits=null;
		if(unitCode==null&&unitName==null&&unitTy==null&&recruitmentTy==null){
			hql="from RecruitmentUnit r";
		}else{
			hql="from RecruitmentUnit r";
			if(!unitCode.equals("")){
				hql+=" where r.unitCode like \'%"+unitCode+"%\'";
				if(!unitName.equals("")){
					hql+=" and r.unitName like \'%"+unitName+"%\'";
				}
			}else{
				if(!unitName.equals("")){
					hql+=" where r.unitName like \'%"+unitName+"%\'";
				}
			}
			//完成招聘方式和单位性质条件查询的hql
			//单位性质
			String tempHql="";
			List<EnumerationValue> values=null;
			if(!unitTy.equals("")){
				tempHql="from EnumerationValue e where e.name like \'%"+unitTy+"%\'";
				System.out.println("tempHql"+tempHql);
				values=enumerationValueDao.findEntityByHQL(tempHql);
				if(unitCode.equals("")&&unitName.equals("")){
					hql+=" where r.unitType in (";
				}else{
					hql+=" and r.unitType in (";
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
				System.out.println("unitTy测试hql  :"+hql);
			}
			if(!recruitmentTy.equals("")){
				//招聘方式
				tempHql="from EnumerationValue e where e.name like \'%"+recruitmentTy+"%\'";
				System.out.println("tempHql"+tempHql);
				values=enumerationValueDao.findEntityByHQL(tempHql);
				if(unitCode.equals("")&&unitName.equals("")&&unitTy.equals("")){
					hql+=" where r.recruitmentType in (";
				}else{
					hql+=" and r.recruitmentType in (";
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
				System.out.println("recruitmentTy测试hql  :"+hql);
			}
		}
		if(sort!=null&&order!=null){
			hql+=" order by "+sort+" "+order;
		}
		System.out.println("最后查询的hql为：   "+hql);
		recruitmentUnits=getAllRecruitmentUnitsByPage(hql,pageNow,pageRows);
		for(RecruitmentUnit rec:recruitmentUnits){
			System.out.println(rec.getId()+" "+rec.getUnitName());
		}
		if(recruitmentUnits!=null && recruitmentUnits.size()>0){
			List<PageRecruitmentUnit> pageRecruitmentUnits=modelChangeToPageModel(recruitmentUnits);
			DataGrid data=new DataGrid();
			data.setTotal(getTotals(hql));
			data.setRows(pageRecruitmentUnits);
			return data;
		}else{
			DataGrid data=new DataGrid();
			data.setRows(new ArrayList<PageRecruitmentUnit>());
			data.setTotal(0L);
			return data;
		}
	}
	
	public List<RecruitmentUnit> getAllRecruitmentUnitsByPage(String hql,int pageNow,int pageRows) {
		return recruitmentUnitDao.findEntityByHQL(hql, pageNow, pageRows);
	}
	
	public List<PageRecruitmentUnit> modelChangeToPageModel(List<RecruitmentUnit> recruitmentUnits){
		List<PageRecruitmentUnit> pageRecruitmentUnits=new ArrayList<PageRecruitmentUnit>();
		for(RecruitmentUnit unit:recruitmentUnits){
			PageRecruitmentUnit pageUnit=new PageRecruitmentUnit();
			BeanUtils.copyProperties(unit,pageUnit);
			EnumerationValue enumerationValue =null;
			//转换单位性质字段
			enumerationValue=enumerationValueDao.getEntity(unit.getUnitType());
			if(enumerationValue!=null){
				pageUnit.setUnit(enumerationValue.getName());
			}
			//转换招聘方式字段
			enumerationValue=enumerationValueDao.getEntity(unit.getRecruitmentType());
			if(enumerationValue!=null){
				pageUnit.setRecruitment(enumerationValue.getName());
			}
			pageRecruitmentUnits.add(pageUnit);
		}
		return pageRecruitmentUnits;
	}

	public Long getTotals(String hql){
		hql="select count(*) "+hql;
		return recruitmentUnitDao.getRecruitmentUnitsCount(hql);
	}

	@Override
	public boolean findRecruitmentUnitByUnitCoide(String unitCode) {
		// TODO Auto-generated method stub
		String hql="from RecruitmentUnit r where r.unitCode=?";
		List<RecruitmentUnit> units=recruitmentUnitDao.findEntityByHQL(hql, unitCode);
		if(units!=null&&units.size()>0){
			//存在
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean addRecruitmentUnit(RecruitmentUnit unit) {
		// TODO Auto-generated method stub
		try {
			recruitmentUnitDao.saveEntity(unit);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public RecruitmentUnit findRecruitmentUnitById(Integer id) {
		// TODO Auto-generated method stub
		return recruitmentUnitDao.getEntity(id);
	}
	
	@Override
	public boolean updateRecruitmentUnit(RecruitmentUnit unit){
		try {
			recruitmentUnitDao.updateEntity(unit);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteSelectedRecruitmentUnit(String idList) {
		// TODO Auto-generated method stub
		try {
			recruitmentUnitDao.deleteRecruitmentUnits(stringToInt(idList));
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
		public List<RecruitmentUnit> findAllRecruitmentUnits() {
			// TODO Auto-generated method stub
			String hql ="from RecruitmentUnit";
			return recruitmentUnitDao.findEntityByHQL(hql);
		}
}
