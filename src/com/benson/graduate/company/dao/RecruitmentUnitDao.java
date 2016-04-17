package com.benson.graduate.company.dao;

import java.util.List;

import com.benson.graduate.base.dao.BaseDao;
import com.benson.graduate.company.model.RecruitmentUnit;

/**
 * 招聘单位信息的增删改查等数据库操作（接口）
 * @author benson
 *
 */
public interface RecruitmentUnitDao extends BaseDao<RecruitmentUnit> {
	public Long getRecruitmentUnitsCount(String hql);
	
	public void deleteRecruitmentUnits(List<Integer> ids);
}
