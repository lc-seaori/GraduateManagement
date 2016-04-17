package com.benson.graduate.sys.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.benson.graduate.base.pagemodel.DataGrid;
import com.benson.graduate.base.service.impl.BaseServiceImpl;
import com.benson.graduate.sys.dao.OperationRecordDao;
import com.benson.graduate.sys.model.OperationRecord;
import com.benson.graduate.sys.pagemodel.PageOperationRecord;
import com.benson.graduate.sys.service.OperationRecordService;

@Service("operationRecordService")
public class OperationRecordServiceImpl extends BaseServiceImpl implements
		OperationRecordService{
	
	private OperationRecordDao operationRecordDao;
	@Resource(name="operationRecordDao")
	public void setOperationRecordDao(OperationRecordDao operationRecordDao) {
		this.operationRecordDao = operationRecordDao;
	}
	
	@Override
	public boolean addOperationRecord(OperationRecord record) {
		// TODO Auto-generated method stub
		try {
			operationRecordDao.saveEntity(record);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public DataGrid getDataGrid(String operationPerson, String operationName,
			String time, String sort, String order, int pageNow, int pageRows) {
		// TODO Auto-generated method stub
		String hql="select * from sys_operation_record s";
		List<OperationRecord> operationRecords=null;
		//一个为null就不会继续判断
		if(operationPerson==null&&operationName==null&time==null){
			hql+="";
		}else{
			if(!operationPerson.equals("")){
				hql+=" where s.OPERATION_PERSON like \'%"+operationPerson+"%\'";
				if(!operationName.equals("")){
					hql+=" and s.OPERATION_NAME like \'%"+operationName+"%\'";
					if(!time.equals("0")){
						//不是请选择状态
						SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						if(time.equals("1")){
							//一周内
							Date date1 = new Date();
							String beginDate=dateFormat.format(date1);
							Calendar calendar = Calendar.getInstance();
							calendar.add(Calendar.DAY_OF_MONTH,-7);
							Date date2 =calendar.getTime();
							String endDate=dateFormat.format(date2);
							endDate="\'"+endDate+"\'";
							beginDate="\'"+beginDate+"\'";
							hql+= " and ( s.OPERATION_TIME between "+endDate+" and "+beginDate+" )";
						}else if(time.equals("2")){
							//一个月内
							Date date1 = new Date();
							String beginDate=dateFormat.format(date1);
							Calendar calendar = Calendar.getInstance();
							calendar.add(Calendar.DAY_OF_MONTH,-30);
							Date date2 =calendar.getTime();
							String endDate=dateFormat.format(date2);
							endDate="\'"+endDate+"\'";
							beginDate="\'"+beginDate+"\'";
							hql+= " and ( s.OPERATION_TIME between "+endDate+" and "+beginDate+" )";
						}else if(time.equals("3")){
							//三个月内
							Date date1 = new Date();
							String beginDate=dateFormat.format(date1);
							Calendar calendar = Calendar.getInstance();
							calendar.add(Calendar.DAY_OF_MONTH,-90);
							Date date2 =calendar.getTime();
							String endDate=dateFormat.format(date2);
							endDate="\'"+endDate+"\'";
							beginDate="\'"+beginDate+"\'";
							hql+= " and ( s.OPERATION_TIME between "+endDate+" and "+beginDate+" )";
						}
					}
				}
			}else{
				if(!operationName.equals("")){
					hql+=" and s.OPERATION_NAME like \'%"+operationName+"%\'";
					if(!time.equals("0")){
						//不是请选择状态
						SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						if(time.equals("1")){
							//一周内
							Date date1 = new Date();
							String beginDate=dateFormat.format(date1);
							Calendar calendar = Calendar.getInstance();
							calendar.add(Calendar.DAY_OF_MONTH,-7);
							Date date2 =calendar.getTime();
							String endDate=dateFormat.format(date2);
							endDate="\'"+endDate+"\'";
							beginDate="\'"+beginDate+"\'";
							hql+= " and ( s.OPERATION_TIME between "+endDate+" and "+beginDate+" )";
						}else if(time.equals("2")){
							//一个月内
							Date date1 = new Date();
							String beginDate=dateFormat.format(date1);
							Calendar calendar = Calendar.getInstance();
							calendar.add(Calendar.DAY_OF_MONTH,-30);
							Date date2 =calendar.getTime();
							String endDate=dateFormat.format(date2);
							endDate="\'"+endDate+"\'";
							beginDate="\'"+beginDate+"\'";
							hql+= " and ( s.OPERATION_TIME between "+endDate+" and "+beginDate+" )";
						}else if(time.equals("3")){
							//三个月内
							Date date1 = new Date();
							String beginDate=dateFormat.format(date1);
							Calendar calendar = Calendar.getInstance();
							calendar.add(Calendar.DAY_OF_MONTH,-90);
							Date date2 =calendar.getTime();
							String endDate=dateFormat.format(date2);
							endDate="\'"+endDate+"\'";
							beginDate="\'"+beginDate+"\'";
							hql+= " and ( s.OPERATION_TIME between "+endDate+" and "+beginDate+" )";
						}
					}
				}else{
					if(!time.equals("0")){
						//不是请选择状态
						SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						if(time.equals("1")){
							//一周内
							Date date1 = new Date();
							String beginDate=dateFormat.format(date1);
							Calendar calendar = Calendar.getInstance();
							calendar.add(Calendar.DAY_OF_MONTH,-7);
							Date date2 =calendar.getTime();
							String endDate=dateFormat.format(date2);
							endDate="\'"+endDate+"\'";
							beginDate="\'"+beginDate+"\'";
							hql+= " where s.OPERATION_TIME between "+endDate+" and "+beginDate;
						}else if(time.equals("2")){
							//一个月内
							Date date1 = new Date();
							String beginDate=dateFormat.format(date1);
							Calendar calendar = Calendar.getInstance();
							calendar.add(Calendar.DAY_OF_MONTH,-30);
							Date date2 =calendar.getTime();
							String endDate=dateFormat.format(date2);
							endDate="\'"+endDate+"\'";
							beginDate="\'"+beginDate+"\'";
							hql+= " where s.OPERATION_TIME between "+endDate+" and "+beginDate;
						}else if(time.equals("3")){
							//三个月内
							Date date1 = new Date();
							String beginDate=dateFormat.format(date1);
							Calendar calendar = Calendar.getInstance();
							calendar.add(Calendar.DAY_OF_MONTH,-90);
							Date date2 =calendar.getTime();
							String endDate=dateFormat.format(date2);
							endDate="\'"+endDate+"\'";
							beginDate="\'"+beginDate+"\'";
							hql+= " where s.OPERATION_TIME between "+endDate+" and "+beginDate;
						}
					}
				}
			}
		}
		if(sort!=null&&order!=null){
			sort="OPERATION_PERSON";
			hql+=" order by "+sort+" "+order;
		}
		System.out.println("最后查询的hql为：   "+hql);
		//operationRecords=this.getAllOperationRecordsByPage(hql,pageNow,pageRows);
		List<Object[]> objectList=this.getAllOperationRecordsByPage(hql,pageNow,pageRows);
		operationRecords=this.getOperationRecord(objectList);
		if(operationRecords!=null && operationRecords.size()>0){
			List<PageOperationRecord> pageOperationRecords=modelChangeToPageModel(operationRecords);
			DataGrid data=new DataGrid();
			hql=hql.substring(hql.indexOf("*")+2);
			data.setTotal(getTotals(hql));
			data.setRows(pageOperationRecords);
			return data;
		}else{
			DataGrid data=new DataGrid();
			data.setRows(new ArrayList<PageOperationRecord>());
			data.setTotal(0L);
			return data;
		}
	}
	
	//二次封装
	public List<OperationRecord> getOperationRecord(List<Object[]> objectList){
		List<OperationRecord> operationRecords=new ArrayList<OperationRecord>();
		if(objectList!=null&&objectList.size()>0){
			for(Object object[]:objectList){
				OperationRecord record=new OperationRecord();
				record.setId((int)object[0]);
				record.setOperationPerson((String)object[1]);
				record.setOperationName((String)object[2]);
				record.setOperationUrl((String)object[3]);
				record.setOperationDescription((String)object[4]);
				record.setOperationTime((Date)object[5]);
				operationRecords.add(record);
			}
		}
		return operationRecords;
	}
	
	//分页查询所有用户操作记录
	public List<Object[]> getAllOperationRecordsByPage(String hql,int pageNow,int pageRows) {
		return operationRecordDao.findEntityBySql(hql, pageNow, pageRows);
	}
	
	//OperationRecord转换成页面模型PageOperationRecord
	public List<PageOperationRecord> modelChangeToPageModel(List<OperationRecord> operationRecords){
		List<PageOperationRecord> pageOperationRecords=new ArrayList<PageOperationRecord>();
		for(OperationRecord operationRecord:operationRecords){
			PageOperationRecord pageOperationRecord=new PageOperationRecord();
			BeanUtils.copyProperties(operationRecord, pageOperationRecord);
			//转换时间字段
			Date date=operationRecord.getOperationTime();
			if(date!=null){
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String operTime=null;
				try {
					operTime=dateFormat.format(date);
					//System.out.println(dateShow);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pageOperationRecord.setPageOperationTime(operTime);
			}
			pageOperationRecords.add(pageOperationRecord);
		}
		return pageOperationRecords;
	}
	
	public Long getTotals(String hql){
		hql="select count(*) "+hql;
		System.out.println("hql+   +   : "+hql);
		return operationRecordDao.getOperationRecordsCount(hql);
	}
	
	public boolean deleteSelectedRecord(String idList) {
		// TODO Auto-generated method stub
		try {
			operationRecordDao.deleteRecord(stringToInt(idList));
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
