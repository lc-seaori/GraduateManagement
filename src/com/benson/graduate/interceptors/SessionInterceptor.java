package com.benson.graduate.interceptors;

import org.apache.struts2.ServletActionContext;

import com.benson.graduate.base.pagemodel.SessionInfo;
import com.benson.graduate.utils.RequestUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * session拦截器
 * 
 * @author Benson
 * 
 */
public class SessionInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;
	
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		System.out.println("进来SessionInterceptor");
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		String requestPath = RequestUtil.getRequestPath(ServletActionContext.getRequest());
		if (sessionInfo == null && !requestPath.contains("front_toHomeIndex")) {
			ServletActionContext.getRequest().setAttribute("msg", "您还没有登录或登录已超时，请重新登录，3秒后将跳转到登录页面！");
			return "noSession";
		}
		return actionInvocation.invoke();
	}

}
