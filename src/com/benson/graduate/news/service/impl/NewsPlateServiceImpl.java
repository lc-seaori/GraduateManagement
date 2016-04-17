package com.benson.graduate.news.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.service.impl.BaseServiceImpl;
import com.benson.graduate.news.dao.NewsPlateDao;
import com.benson.graduate.news.model.NewsPlate;
import com.benson.graduate.news.pagemodel.PageNewsPlate;
import com.benson.graduate.news.service.NewsPlateService;
import com.benson.graduate.stu.pagemodel.PageStudentInfo;
import com.benson.graduate.utils.CastUtil;
import com.benson.graduate.utils.StringUtil;

@Service("newsPlateService")
public class NewsPlateServiceImpl extends BaseServiceImpl implements
		NewsPlateService {

	private NewsPlateDao newsPlateDao;
	@Resource(name="newsPlateDao")
	public void setNewsPlateDao(NewsPlateDao newsPlateDao) {
		this.newsPlateDao = newsPlateDao;
	}
	
	@Override
	public DataGrid getDataGrid(Map<String, Object> fieldMap, String sort, String order, int pageNow, int pageRows) {
		
		StringBuffer hql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		String plateName = CastUtil.castString(fieldMap.get("plateName"));
		
		//拼接hql和准备参数
		hql.append("from NewsPlate np where 1=1");
		if(StringUtil.isNotEmpty(plateName)){
			hql.append(" and np.plateName like ?");
			params.add("%" + plateName + "%");
		}
		//查询
		List<NewsPlate> newsPlateList = getAllNewsPlateByPage(hql.toString(), pageNow, pageRows, params);
		List<PageNewsPlate> pagePlateList = changeToPageModelList(newsPlateList);
		//构造easyui前端所需要的grid数据
		if (newsPlateList != null && newsPlateList.size() > 0) {
			DataGrid data = new DataGrid();
			data.setTotal(getTotals(hql.toString(), params));
			data.setRows(pagePlateList);
			return data;
		} else {
			DataGrid data = new DataGrid();
			data.setRows(new ArrayList<PageStudentInfo>());
			data.setTotal(0L);
			return data;
		}
	}
	
	public List<PageNewsPlate> changeToPageModelList(List<NewsPlate> newsPlateList){
		List<PageNewsPlate> pagePlateList = new ArrayList<PageNewsPlate>();
		PageNewsPlate pagePlate = null;
		for(NewsPlate newsPlate : newsPlateList){
			pagePlate = new PageNewsPlate();
			BeanUtils.copyProperties(newsPlate, pagePlate);
			if(newsPlate.getNewsPlate() == null){
				pagePlate.setParentPlateName("");
				pagePlate.setPlateLevel("一级");
			}else{
				pagePlate.setParentPlateName(newsPlate.getNewsPlate().getPlateName());
				pagePlate.setPlateLevel("二级");
			}
			pagePlateList.add(pagePlate);
		}
		return pagePlateList;
	}
	
	public List<NewsPlate> getAllNewsPlateByPage(String hql, int pageNow, int pageRows, List<Object> params){
		return newsPlateDao.findEntityByHQL(hql, pageNow, pageRows, params.toArray());
	}
	
	public Long getTotals(String hql, List<Object> params){
		hql="select count(*) "+hql;
		return newsPlateDao.getNewsPlateCount(hql, params.toArray());
	}

	@Override
	public List<NewsPlate> findAllRootNewsPlate() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from news_plate where plate_pid is NULL");
		List<Object[]> objects = newsPlateDao.findEntityBySql(sql.toString());
		List<NewsPlate> list = new ArrayList<NewsPlate>();
		NewsPlate plate = null;
		for(Object[] object : objects){
			plate = new NewsPlate();
			plate.setId((int)object[0]);
			plate.setPlateName((String)object[1]);
			plate.setNewsType((int)object[2]);
			list.add(plate);
		}
		return list;
	}
	
	@Override
	public List<NewsPlate> findChildNewsPlate(int platePid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from news_plate where plate_pid = ?");
		List<Object[]> objects = newsPlateDao.findEntityBySql(sql.toString(), platePid);
		List<NewsPlate> list = new ArrayList<NewsPlate>();
		NewsPlate plate = null;
		for(Object[] object : objects){
			plate = new NewsPlate();
			plate.setId((int)object[0]);
			plate.setPlateName((String)object[1]);
			plate.setNewsType((int)object[2]);
			list.add(plate);
		}
		return list;
	}

	@Override
	public NewsPlate findById(int id) {
		return newsPlateDao.getEntity(id);
	}

	@Override
	public boolean insertNewsPlate(NewsPlate newsPlate) {
		try {
			newsPlateDao.saveEntity(newsPlate);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateNewsPlate(int newsPlateId, Map<String, Object> fieldMap) {
		boolean flag = false;
		StringBuffer sql = new StringBuffer();
		sql.append("update news_plate set plate_name=? ");
		String plateName = CastUtil.castString(fieldMap.get("plateName"));
		int newsPlatePid = CastUtil.castInt(fieldMap.get("newsPlatePid"), 0);
		if(newsPlatePid > 0){
			sql.append(", plate_pid=? ");
			flag = true;
		}
		sql.append("where id=?");
		if(flag){
			return newsPlateDao.updateNewsPlate(sql.toString(), plateName, newsPlatePid, newsPlateId);
		}else{
			return newsPlateDao.updateNewsPlate(sql.toString(), plateName, newsPlateId);
		}
	}

	@Override
	public boolean isContext(String idList) {
		List<Integer> ids = stringToInt(idList);
		for(int id : ids){
			if(newsPlateDao.getPlateCount("select count(*) from news_plate where plate_pid=?", id) > 0){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean deleteNewsPlates(String idList) {
		try {
			newsPlateDao.deleteNewsPlates(stringToInt(idList));
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
			System.out.println(str_id[i]);
			ids.add(Integer.parseInt(str_id[i]));
		}
		return ids;
	}
}
