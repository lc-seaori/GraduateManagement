package com.benson.graduate.company.dao;

import java.util.List;

import com.benson.graduate.base.dao.BaseDao;
import com.benson.graduate.company.model.RecruitmentInfo;

public interface RecruitmentInfoDao extends BaseDao<RecruitmentInfo> {
	/**
	 * 获取招聘信息总数
	 */
	public Long getRecruitmentInfosCount(String hql);
	
	/**
	 * 根据id列表删除招聘信息
	 */
	public void deleteRecruitmentInfos(List<Integer> ids);
}
