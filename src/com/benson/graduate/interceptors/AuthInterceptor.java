package com.benson.graduate.interceptors;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.benson.graduate.base.pagemodel.SessionInfo;
import com.benson.graduate.sys.model.Auth;
import com.benson.graduate.sys.model.OperationRecord;
import com.benson.graduate.sys.service.AuthService;
import com.benson.graduate.sys.service.OperationRecordService;
import com.benson.graduate.utils.RequestUtil;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 权限拦截器
 * 
 * @author Benson
 * 
 */
@Component
public class AuthInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;

	private AuthService authService;
	@Resource(name="authService")
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
	private OperationRecordService operationRecordService;
	@Resource(name="operationRecordService")
	public void setOperationRecordService(
			OperationRecordService operationRecordService) {
		this.operationRecordService = operationRecordService;
	}



	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//ActionContext actionContext = actionInvocation.getInvocationContext();
		System.out.println("进来AuthInterceptor");
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		String requestPath = RequestUtil.getRequestPath(ServletActionContext.getRequest());
		if(sessionInfo!=null){
			if (sessionInfo.getLoginname().equals("admin")) {
				//直接放行
				return actionInvocation.invoke();
			}
		}else{
			if(requestPath.contains("front_toHomeIndex")){
				//主页直接放行
				return actionInvocation.invoke();
			}
		}
		//获取所有url
		List<String> authUrls = sessionInfo.getAuthList();
		boolean b = false;
		if(authUrls!=null&&authUrls.size()>0){
			for(String url:authUrls){
				if (requestPath.equals(url)) {
					//根据权限路径查找权限
					Auth auth=authService.findAuthsByUrl(url);
					OperationRecord record=new OperationRecord();
					if(auth!=null){
						record.setOperationPerson(sessionInfo.getName());
						record.setOperationName(auth.getName());
						record.setOperationUrl(url);
						record.setOperationDescription(auth.getDescription());
					}
					//插入到数据库中
					operationRecordService.addOperationRecord(record);
					b = true;
					break;
				}
			}
		}
		//在权限范围之内，放行
		if (b) {
			return actionInvocation.invoke();
		}
		ServletActionContext.getRequest().setAttribute("msg", "您没有访问此功能的权限！权限路径为[  " + requestPath + "  ], 请联系管理员给你赋予相应权限。");
		return "noSecurity";
	}

}
