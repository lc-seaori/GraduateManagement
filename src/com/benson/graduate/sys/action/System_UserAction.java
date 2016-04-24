package com.benson.graduate.sys.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.base.pagemodel.SessionInfo;
import com.benson.graduate.common.enumeration.EnumerationType;
import com.benson.graduate.company.service.RecruitmentInfoService;
import com.benson.graduate.company.service.RecruitmentUnitService;
import com.benson.graduate.news.model.NewsNn;
import com.benson.graduate.news.service.NewsNnService;
import com.benson.graduate.news.service.NewsPlateService;
import com.benson.graduate.sys.model.Auth;
import com.benson.graduate.sys.model.EnumerationValue;
import com.benson.graduate.sys.model.Role;
import com.benson.graduate.sys.model.User;
import com.benson.graduate.sys.service.AuthService;
import com.benson.graduate.sys.service.EnumerationValueService;
import com.benson.graduate.sys.service.RoleService;
import com.benson.graduate.sys.service.UserService;
import com.benson.graduate.utils.MD5Util;

/**
 * 用户Action
 * @author benson
 *
 */
@Controller("userAction")
@Scope("prototype")
public class System_UserAction extends BaseAction{

	private static final long serialVersionUID = -3192161391468961263L;
	
	private UserService userService;
	@Resource(name="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private RoleService roleService;
	@Resource(name="roleService")
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	private AuthService authService;
	@Resource(name="authService")
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
	private RecruitmentInfoService recruitmentInfoService;
	@Resource(name="recruitmentInfoService")
	public void setRecruitmentInfoService(RecruitmentInfoService recruitmentInfoService) {
		this.recruitmentInfoService = recruitmentInfoService;
	}
	private RecruitmentUnitService recruitmentUnitService;
	@Resource(name="recruitmentUnitService")
	public void setRecruitmentUnitService(
			RecruitmentUnitService recruitmentUnitService) {
		this.recruitmentUnitService = recruitmentUnitService;
	}
	private NewsPlateService newsPlateService;
	@Resource(name="newsPlateService")
	public void setNewsPlateService(NewsPlateService newsPlateService) {
		this.newsPlateService = newsPlateService;
	}
	private NewsNnService newsNnService ;
	@Resource(name="newsNnService")
	public void setNewsNnService(NewsNnService newsNnService) {
		this.newsNnService = newsNnService;
	}
	private EnumerationValueService enumerationValueService;
	@Resource(name="enumerationValueService")
	public void setEnumerationValueService(
			EnumerationValueService enumerationValueService) {
		this.enumerationValueService = enumerationValueService;
	}
	
	private String loginname;
	private String password;
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	//验证码参数
	private String checkCode;
    public String getCheckCode(){
        return checkCode;
    }
    public void setCheckCode(String checkCode){
        this.checkCode = checkCode;
    }

	//接收从editUserPwd.jsp页面传来的参数
	private String oldPwd;
	private String newPwd;
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	
	//接收从editUserPwd.jsp页面传来的参数(用户列表模糊查询)
	private String rows;//每页显示的记录数    
    private String page;//当前第几页
    private String sort; //根据哪个字段排序
    private String order;	//desc,asc
	private String name;
	private Integer organizationId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	
	//接收从userList.jsp传过来的数据
	private String idList;
	public String getIdList() {
		return idList;
	}
	public void setIdList(String idList) {
		this.idList = idList;
	}
	
	/**
	 * 登陆验证
	 * @return
	 */
	public String doNotNeedSession_login(){
		
		//新闻导航
		request.setAttribute("newsPlateList", newsPlateService.findAllRootNewsPlate());
		//招聘信息和招聘单位信息
		request.setAttribute("infosList", recruitmentInfoService.getAllRecruitmentInfos(0, 4));
		request.setAttribute("unitsList", recruitmentUnitService.findAllRecruitmentUnits());
		//其他新闻类
		Map<Integer,List<NewsNn>> nnMap = new HashMap<Integer, List<NewsNn>>();
		Map<Integer,String> enNameMap = new HashMap<Integer, String>();
		List<EnumerationValue> newsTypeList = enumerationValueService.findAllEnumerationValuesByName(EnumerationType.NEWS_TYPE);
		for(EnumerationValue enVal : newsTypeList){
			List<NewsNn> nnList = newsNnService.findByNewsPlateId(enVal.getId());
			nnMap.put(enVal.getId(), nnList);
			enNameMap.put(enVal.getId(), enVal.getName());
		}
		request.setAttribute("nnMap", nnMap);
		request.setAttribute("newsTypeList", newsTypeList);
		request.setAttribute("enNameMap", enNameMap);
		
		if(request.getSession().getAttribute("sessionInfo")!=null){
			//证明不是第一次登陆，直接放行
			return "index";
		}
		//先判断验证码，如果错误
		if(httpSession.getAttribute("checkCode")!=null){
			String checkCodeStr = (String)httpSession.getAttribute("checkCode");
	        if(!checkCode.equals(checkCodeStr)){
	            //this.addActionError("输入的验证码不正确，请重新输入！");
	        	request.setAttribute("msg", "验证码错误");
	    		return "relogin";
	        }
		}
        
		//根据用户帐号查找user
		List<User> users=userService.findUserByLoginName(loginname);
		if(users!=null&&users.size()>0){
			//一个帐号肯定对应一个用户
			if(users.get(0).getPassword().equals(MD5Util.md5(password))){
				//通过验证
				SessionInfo sessionInfo=new SessionInfo();
				sessionInfo.setId(users.get(0).getId());
				sessionInfo.setLoginname(loginname);
				sessionInfo.setName(users.get(0).getName());
				//判断用户角色
				//先查询该用户的所有角色
				List<Role> roles=roleService.findAllRolesByUserId(users.get(0).getId());
				//该用户拥有的所有权限
				List<String> authList=new ArrayList<String>();
				if(roles!=null&&roles.size()>0){
					//查看所有角色的所有权限
					for(Role role:roles){
						//通过角色id查找该角色所拥有的所有权限
						List<Auth> auths=authService.findAllAuthsByRoleId(role.getId());
						if(auths!=null&&auths.size()>0){
							for(Auth auth:auths){
								System.out.println("url:   "+auth.getUrl());
								authList.add(auth.getUrl());
							}
						}
					}
				}
				sessionInfo.setAuthList(authList);
				//sessionInfo.set
				request.getSession().setAttribute("sessionInfo", sessionInfo);
				//设置sessio生命周期为15分钟
				request.getSession().setMaxInactiveInterval(15*60);
				return "index";
			}
		}
		request.setAttribute("msg", "用户帐号不存在或密码错误，请重新登陆");
		return "relogin";
	}
	
	/**
	 * 弹出修改密码对话框
	 */
	public String doNotNeedAuth_toEditUserPwd(){
		System.out.println("toEditUserPwd()");
		return "editUserPwd";
	}
	
	/**
	 * 修改密码
	 */
	public void doNotNeedAuth_editUserPwd(){
		System.out.println("原来密码为：   "+oldPwd); 
		SessionInfo sessionInfo=(SessionInfo) request.getSession().getAttribute("sessionInfo");
		Integer userId=sessionInfo.getId();
		User user=userService.findUserByUserId(userId);
		Json json=new Json();
		if(user.getPassword().equals(MD5Util.md5(oldPwd))){
			//原密码正确，更新新密码
			user.setPassword(MD5Util.md5(newPwd));
			boolean result=userService.updateUser(user);
			json.setSuccess(result);
			json.setMsg("修改密码成功，请重新登陆!");
		}else{
			//原密码都错
			json.setSuccess(false);
			json.setMsg("原密码错误，请重新输入!");
		}
		super.writeJson(json);
	}
	
	/**
	 * 退出登陆
	 */
	public void doNotNeedSession_loginout(){
		//移除
		request.getSession().removeAttribute("sessionInfo");
		Json json=new Json();
		json.setSuccess(true);
		super.writeJson(json);
	}
	
	/**
	 * 进入用户列表界面
	 */
	public String toUserListPage(){
		return "userListPage";
	}
	
	/**
	 * 得到用户列表的grid
	 */
	public void getUserList(){
		System.out.println("记录数： "+rows +"  第几页：  "+page+"  排序字段 ："+sort +"  排序方式：  "+order);
		System.out.println(loginname+"   "+name+"   "+organizationId);
		//第一次datagrid自动向url发一次请求，都是为null,需要初始化
		int pageNow=Integer.parseInt((page==null||page=="0")?"1":page);
		int pageRows=Integer.parseInt((rows==null||rows=="0")?"10":rows);
		System.out.println("pageNow = "+pageNow +"  pageRows= "+pageRows);
		super.writeJson(userService.getDataGrid(loginname, name,organizationId ,sort, order, pageNow, pageRows));
	}
	
	/**
	 * 删除多个用户
	 */
	public void toDeleteUser(){
		System.out.println("开始删除");
		boolean result=userService.deleteSelectedUser(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
}
