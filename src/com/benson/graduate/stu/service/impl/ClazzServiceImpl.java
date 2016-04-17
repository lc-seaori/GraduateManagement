package com.benson.graduate.stu.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.service.impl.BaseServiceImpl;
import com.benson.graduate.stu.dao.ClazzDao;
import com.benson.graduate.stu.model.Clazz;
import com.benson.graduate.stu.service.ClazzService;
import com.benson.graduate.sys.pagemodel.PageClazz;

@Service("clazzService")
public class ClazzServiceImpl extends BaseServiceImpl implements ClazzService {
	
	private ClazzDao clazzDao;
	@Resource(name="clazzDao")
	public void setClazzDao(ClazzDao clazzDao) {
		this.clazzDao = clazzDao;
	}
	
	@Override
	public List<Clazz> findAllClazzByMajorId(Integer majorId) {
		// TODO Auto-generated method stub
		String hql="from Clazz c where c.majorField.id=?";
		return clazzDao.findEntityByHQL(hql, majorId);
	}

	@Override
	public Clazz findClazzById(Integer clazzId) {
		// TODO Auto-generated method stub
		return clazzDao.getEntity(clazzId);
	}

	@Override
	public List<Clazz> findAllGraClazzes(Integer isboolean) {
		// TODO Auto-generated method stub
		String hql="from Clazz c where c.grade.is_greaduation=?";
		return clazzDao.findEntityByHQL(hql,isboolean);
	}

	@Override
	public DataGrid getDataGrid(String name, String majorId, String sort,
			String order, int pageNow, int pageRows) {
		// TODO Auto-generated method stub
		String hql="from Clazz c";
		List<Clazz> clazzs=null;
		//一个为null就不会继续判断
		if(name==null&&majorId==null){
			hql="from Clazz c";
		}else{
			if(!name.equals("")){
				hql+=" where c.name like \'%"+name+"%\'";
				if(!majorId.equals("0")){
					//不是“请选择”状态
					hql+=" and c.majorField.id="+majorId;
				}
			}else{
				if(!majorId.equals("0")){
					//不是“请选择”状态
					hql+=" where c.majorField.id="+majorId;
				}
			}
			
		}
		if(sort!=null&&order!=null){
			hql+=" order by "+sort+" "+order;
		}
		System.out.println("最后查询的hql为：   "+hql);
		clazzs=this.getAllClazzsByPage(hql,pageNow,pageRows);
		if(clazzs!=null && clazzs.size()>0){
			List<PageClazz> pageClazzs=modelChangeToPageModel(clazzs);
			DataGrid data=new DataGrid();
			data.setTotal(getTotals(hql));
			data.setRows(pageClazzs);
			return data;
		}else{
			DataGrid data=new DataGrid();
			data.setRows(new ArrayList<PageClazz>());
			data.setTotal(0L);
			return data;
		}
	}
	
	//分页查询所有班级
	public List<Clazz> getAllClazzsByPage(String hql,int pageNow,int pageRows) {
		return clazzDao.findEntityByHQL(hql, pageNow, pageRows);
	}
	
	//clazz转换成页面模型PageClazz
	public List<PageClazz> modelChangeToPageModel(List<Clazz> clazzs){
		List<PageClazz> pageClazzs=new ArrayList<PageClazz>();
		for(Clazz clazz:clazzs){
			PageClazz pageClazz=new PageClazz();
			BeanUtils.copyProperties(clazz, pageClazz);
			//转换专业字段
			pageClazz.setMajorFieldName(clazz.getMajorField().getName());
			//转换年级字段
			pageClazz.setGradeName(clazz.getGrade().getName());
			pageClazzs.add(pageClazz);
		}
		return pageClazzs;
	}
	
	public Long getTotals(String hql){
		hql="select count(*) "+hql;
		return clazzDao.getClazzsCount(hql);
	}

	@Override
	public boolean addClazz(Clazz clazz) {
		// TODO Auto-generated method stub
		try {
			clazzDao.saveEntity(clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateClazz(Clazz clazz) {
		// TODO Auto-generated method stub
		try {
			clazzDao.updateEntity(clazz);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteSelectedClazzs(String idList) {
		// TODO Auto-generated method stub
		try {
			clazzDao.deleteClazz(stringToInt(idList));
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
