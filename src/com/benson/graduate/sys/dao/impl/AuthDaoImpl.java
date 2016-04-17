package com.benson.graduate.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.benson.graduate.base.dao.impl.BaseDaoImpl;
import com.benson.graduate.sys.dao.AuthDao;
import com.benson.graduate.sys.model.Auth;

@Repository("authDao")
public class AuthDaoImpl extends BaseDaoImpl<Auth> implements AuthDao {


}
