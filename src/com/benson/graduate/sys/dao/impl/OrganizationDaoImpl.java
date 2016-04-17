package com.benson.graduate.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.benson.graduate.base.dao.impl.BaseDaoImpl;
import com.benson.graduate.sys.dao.OrganizationDao;
import com.benson.graduate.sys.model.Organization;

/**
 * 对组织部门数据的数据库操作
 * @author benson
 *
 */
@Repository("organizationDao")
public class OrganizationDaoImpl extends BaseDaoImpl<Organization> implements
		OrganizationDao {

}
