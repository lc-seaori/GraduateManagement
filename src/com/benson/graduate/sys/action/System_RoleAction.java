package com.benson.graduate.sys.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.common.enumeration.EnumerationType;
import com.benson.graduate.sys.model.Auth;
import com.benson.graduate.sys.model.EnumerationValue;
import com.benson.graduate.sys.model.Role;
import com.benson.graduate.sys.service.AuthService;
import com.benson.graduate.sys.service.EnumerationValueService;
import com.benson.graduate.sys.service.RoleService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 角色Action
 * @author benson
 *
 */
@Controller("roleAction")
@Scope("prototype")
public class System_RoleAction extends BaseAction implements ModelDriven<Role>,Preparable{

	private static final long serialVersionUID = 270806407278289595L;
	
	private RoleService roleService;
	@Resource(name="roleService")
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	private EnumerationValueService enumerationValueService;
	@Resource(name="enumerationValueService")
	public void setEnumerationValueService(
			EnumerationValueService enumerationValueService) {
		this.enumerationValueService = enumerationValueService;
	}
	private AuthService authService;
	@Resource(name="authService")
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
	//获取前台easy-ui传过来的参数
	private String rows;//每页显示的记录数    
    private String page;//当前第几页
    private String sort; //根据哪个字段排序
    private String order;	//desc,asc
    private String name;	//迷糊查询用
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//接收从roleList.jsp传过来的角色id
	private Integer roleId;
	private String idList;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getIdList() {
		return idList;
	}
	public void setIdList(String idList) {
		this.idList = idList;
	}
	
	//接收从grentAuthToRole.jsp页面传来的参数
	private String resourceIds;
	public String getResourceIds() {
		return resourceIds;
	}
	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
	}
	
	private Role role=new Role();
	@Override
	public Role getModel() {
		// TODO Auto-generated method stub
		return role;
	}
	
	/**
	 * 用户列表页面
	 * @return
	 */
	public String toRoleListPage(){
		return "roleListPage";
	}
	
	/**
	 * 获取角色列表Gird
	 */
	public void getRoleList(){
		System.out.println("记录数： "+rows +"  第几页：  "+page+"  排序字段 ："+sort +"  排序方式：  "+order);
		System.out.println("name   :  "+name);
		int pageNow=Integer.parseInt((page==null||page=="0")?"1":page);
		int pageRows=Integer.parseInt((rows==null||rows=="0")?"10":rows);
		super.writeJson(roleService.getDataGrid(name,sort,order,pageNow,pageRows));
	}
	
	/**
	 * 添加角色页面
	 * @return
	 */
	public String toAddRolePage(){
		List<EnumerationValue> values=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.BOOLEAN_TYPE);
		request.setAttribute("isdefault", values);
		return "addRolePage";
	}
	
	/**
	 * 添加角色
	 */
	public void doAddRole(){
		System.out.println("测试数据  ："+role.getName()+"   "+role.getIsdefault()+"  "+role.getDescription());
		boolean result=roleService.addRole(role);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	public void prepareToEditRolePage(){
		role=roleService.findRoleById(roleId);
	}
	
	/**
	 * 编辑角色页面
	 * @return
	 */
	public String toEditRolePage(){
		List<EnumerationValue> values=enumerationValueService.findAllEnumerationValuesByName(EnumerationType.BOOLEAN_TYPE);
		request.setAttribute("isdefault", values);
		resourceIds="";
		//查找该角色的所有权限
		Set<Auth> hasAuths=role.getAuths();
		if(hasAuths!=null&&hasAuths.size()>0){
			int setSize=hasAuths.size();
			int count=0;
			for(Auth auth:hasAuths){
				count++;
				resourceIds+=auth.getId();
				if(count!=setSize){
					resourceIds+=",";
				}
			}
			System.out.println("resourceIds   :  ---->  "+resourceIds);
		}else{
			//没有权限
			resourceIds="";
		}
		return "editRolePage";
	}
	
	/**
	 * 编辑角色信息
	 */
	public void doEditRole(){
		System.out.println("测试数据  ："+role.getName()+"   "+role.getIsdefault()+"  "+role.getDescription());
		System.out.println("resourceIds    ------>   "+resourceIds);
		if(resourceIds.equals("")){
			//权限置空
			role.setAuths(new HashSet<Auth>());
		}else{
			String [] authIds=resourceIds.split(",");
			//根据权限id查找权限
			Set<Auth> auths=new HashSet<Auth>();
			for(String authId:authIds){
				auths.add(authService.findAuthById(Integer.parseInt(authId)));
			}
			role.setAuths(auths);
		}
		boolean result = roleService.updateRole(role);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 批量删除角色信息
	 */
	public void doDeleteRole(){
		boolean result=roleService.deleteSelectedRoles(idList);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 角色授权页面
	 */
	public String toGrentAuthToRolePage(){
		role=roleService.findRoleById(roleId);
		request.setAttribute("role", role);
		return "grentAuthToRolePage";
	}
	
	/**
	 * 角色授权
	 */
	public void doGrentAuthToRole(){
		Role role =roleService.findRoleById(roleId);
		if(resourceIds.equals("")){
			//权限置空
			role.setAuths(new HashSet<Auth>());
		}else{
			String [] authIds=resourceIds.split(",");
			//根据权限id查找权限
			Set<Auth> auths=new HashSet<Auth>();
			for(String authId:authIds){
				auths.add(authService.findAuthById(Integer.parseInt(authId)));
			}
			role.setAuths(auths);
		}
		boolean result=roleService.updateRole(role);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
}
