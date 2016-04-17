package com.benson.graduate.stu.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.service.impl.BaseServiceImpl;
import com.benson.graduate.stu.dao.MajorFieldDao;
import com.benson.graduate.stu.model.MajorField;
import com.benson.graduate.stu.service.MajorFieldService;
import com.benson.graduate.sys.pagemodel.PageMajorField;

@Service("majorFieldService")
public class MajorFieldServiceImpl extends BaseServiceImpl implements
		MajorFieldService {

	private MajorFieldDao majorFieldDao;
	@Resource(name="majorFieldDao")
	public void setMajorFieldDao(MajorFieldDao majorFieldDao) {
		this.majorFieldDao = majorFieldDao;
	}


	@Override
	public List<MajorField> findAllMajorsByDepartmentId(int id) {
		// TODO Auto-generated method stub
		String hql="from MajorField m where m.department.id=?";
		return majorFieldDao.findEntityByHQL(hql, id);
	}


	@Override
	public MajorField findMajorById(int id) {
		// TODO Auto-generated method stub
		return majorFieldDao.getEntity(id);
	}


	@Override
	public List<MajorField> findAllMajorField() {
		// TODO Auto-generated method stub
		String hql="from MajorField";
		return majorFieldDao.findEntityByHQL(hql);
	}


	@Override
	public DataGrid getDataGrid(String name, String departmentId, String sort,
			String order, int pageNow, int pageRows) {
		// TODO Auto-generated method stub
		String hql="from MajorField m";
		List<MajorField> majorFields=null;
		//一个为null就不会继续判断
		if(name==null&&departmentId==null){
			hql="from MajorField m";
		}else{
			if(!name.equals("")){
				hql+=" where name like \'%"+name+"%\'";
				if(!departmentId.equals("0")){
					//不是“请选择”状态
					hql+=" and m.department.id="+departmentId;
				}
			}else{
				if(!departmentId.equals("0")){
					//不是“请选择”状态
					hql+=" where m.department.id="+departmentId;
				}
			}
			
		}
		if(sort!=null&&order!=null){
			hql+=" order by "+sort+" "+order;
		}
		System.out.println("最后查询的hql为：   "+hql);
		majorFields=this.getAllMajorsByPage(hql,pageNow,pageRows);
		if(majorFields!=null && majorFields.size()>0){
			List<PageMajorField> pageMajorFields=modelChangeToPageModel(majorFields);
			DataGrid data=new DataGrid();
			data.setTotal(getTotals(hql));
			data.setRows(pageMajorFields);
			return data;
		}else{
			DataGrid data=new DataGrid();
			data.setRows(new ArrayList<PageMajorField>());
			data.setTotal(0L);
			return data;
		}
	}
	
	//分页查询所有专业
	public List<MajorField> getAllMajorsByPage(String hql,int pageNow,int pageRows) {
		return majorFieldDao.findEntityByHQL(hql, pageNow, pageRows);
	}
	
	//majorfield转换成页面模型PageMajorField
	public List<PageMajorField> modelChangeToPageModel(List<MajorField> majorFields){
		List<PageMajorField> pageMajorFields=new ArrayList<PageMajorField>();
		for(MajorField majorField:majorFields){
			PageMajorField pageMajorField=new PageMajorField();
			BeanUtils.copyProperties(majorField, pageMajorField);
			//转换学院字段
			pageMajorField.setDepartmentName(majorField.getDepartment().getName());
			
			pageMajorFields.add(pageMajorField);
		}
		return pageMajorFields;
	}
	
	public Long getTotals(String hql){
		hql="select count(*) "+hql;
		return majorFieldDao.getMajorsCount(hql);
	}


	@Override
	public boolean addMajorField(MajorField majorField) {
		// TODO Auto-generated method stub
		try {
			majorFieldDao.saveEntity(majorField);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public boolean updateMajor(MajorField majorField) {
		// TODO Auto-generated method stub
		try {
			majorFieldDao.updateEntity(majorField);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	@Override
	public boolean deleteSelectedMajors(String idList) {
		// TODO Auto-generated method stub
		try {
			majorFieldDao.deleteMajor(stringToInt(idList));
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

}
