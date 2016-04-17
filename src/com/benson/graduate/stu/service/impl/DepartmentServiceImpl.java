package com.benson.graduate.stu.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.service.impl.BaseServiceImpl;
import com.benson.graduate.stu.model.Department;
import com.benson.graduate.stu.service.DepartmentService;
import com.benson.graduate.stu.dao.DepartmentDao;

@Service("departmentService")
public class DepartmentServiceImpl extends BaseServiceImpl implements
		DepartmentService {

	private DepartmentDao departmentDao;
	@Resource(name="departmentDao")
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public List<Department> findAllDepartments() {
		String hql="from Department";
		return departmentDao.findEntityByHQL(hql);
	}

	@Override
	public Department findDepartmentById(Integer id) {
		// TODO Auto-generated method stub
		return departmentDao.getEntity(id);
	}

	@Override
	public DataGrid getDataGrid(String name, String sort, String order,
			int pageNow, int pageRows) {
		// TODO Auto-generated method stub
		String hql="from Department d";
		List<Department> departments=null;
		//一个为null就不会继续判断
		if(name==null){
			hql="from Department d";
		}else{
			hql+=" where name like \'%"+name+"%\'";
		}
		if(sort!=null&&order!=null){
			hql+=" order by "+sort+" "+order;
		}
		System.out.println("最后查询的hql为：   "+hql);
		departments=this.getAllDepartmentsByPage(hql,pageNow,pageRows);
		if(departments!=null && departments.size()>0){
			//List<PageRole> pageRoles=modelChangeToPageModel(roles);
			DataGrid data=new DataGrid();
			data.setTotal(getTotals(hql));
			data.setRows(departments);
			return data;
		}else{
			DataGrid data=new DataGrid();
			data.setRows(new ArrayList<Department>());
			data.setTotal(0L);
			return data;
		}
	}

	//分页查询所有学院
	public List<Department> getAllDepartmentsByPage(String hql,int pageNow,int pageRows) {
		return departmentDao.findEntityByHQL(hql, pageNow, pageRows);
	}
	
	public Long getTotals(String hql){
		hql="select count(*) "+hql;
		return departmentDao.getDepartmentsCount(hql);
	}

	@Override
	public boolean addDepartment(Department d){
		try {
			departmentDao.saveOrUpdateEntity(d);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateDepartment(Department d) {
		// TODO Auto-generated method stub
		try {
			departmentDao.updateEntity(d);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteSelectedDeps(String idList) {
		// TODO Auto-generated method stub
		try {
			departmentDao.deleteDepartment(stringToInt(idList));
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
