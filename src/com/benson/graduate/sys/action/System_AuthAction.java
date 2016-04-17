package com.benson.graduate.sys.action;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.sys.model.Auth;
import com.benson.graduate.sys.service.AuthService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 权限Action
 * @author benson
 */
@Controller("authAction")
@Scope("prototype")
public class System_AuthAction extends BaseAction implements ModelDriven<Auth>,Preparable{

	private static final long serialVersionUID = -1512778292209124278L;
	
	private AuthService authService;
	@Resource(name="authService")
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}

	//接收addAuth.jsp页面传来的上级权限id
	private String pid;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	//接收authList.jsp传来的数据
	private Integer authId;
	public Integer getAuthId() {
		return authId;
	}
	public void setAuthId(Integer authId) {
		this.authId = authId;
	}
	//接收从grentaAuthToRole.jsp传送过来的数据
	private Integer roleId;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	private Auth auth=new Auth();
	@Override
	public Auth getModel() {
		// TODO Auto-generated method stub
		return auth;
	}
	/**
	 * 进入权限列表页面
	 * @return
	 */
	public String toAuthListPage(){
		return "authListPage";
	}

	/**
	 * 获取权限树的Grid
	 */
	public void getAuthList(){
		super.writeJson(authService.treeGrid());
	}
	
	/**
	 * 进入添加权限页面
	 */
	public String toAddAuthPage(){
		return "addAuthPage";
	}
	
	/**
	 * 获取下拉树数据或者是树数据(combotree/tree)
	 */
	public void doNotNeedSession_tree(){
		System.out.println("doNotNeedSession_tree()");
		super.writeJson(authService.combotree());
	}
	
	/**
	 * 添加权限
	 */
	public void doAddAuth(){
		//ValueStack vs = ActionContext.getContext().getValueStack();  
		System.out.println("测试数据：   "+auth.getName()+"  "+auth.getIcon()+"  "+auth.getUrl()+"  "+auth.getDescription());
		//System.out.println("上级权限id  ："+vs.findValue("pid").toString());
		System.out.println("pid  :+"+ pid);
		//根据pid获取上级权限
		if(pid!=null&&!pid.equals("")){
			//不为null或者不是""
			auth.setAuth(authService.findAuthById(Integer.parseInt(pid)));
		}else{
			auth.setAuth(null);
		}
		//创建时间
		auth.setCreateTime(new Date());
		boolean result=authService.addAuth(auth);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	public void prepareToEditAuthPage(){
		System.out.println("authId  :"+authId);
		//request.setAttribute("authId", authId);
		//根据id得到权限
		auth=authService.findAuthById(authId);
		if(auth.getAuth()!=null){
			request.setAttribute("parentId", auth.getAuth().getId());
		}
	}
	
	/**
	 * 进入编辑权限页面
	 */
	public String toEditAuthPage(){
		System.out.println("toEditAuthPage  :"+auth.getId()+"  "+auth.getName());
		return "editAuthPage";
	}
	
	/**
	 * 编辑权限
	 */
	public void doEditAuth(){
		System.out.println("测试数据：   "+auth.getName()+"  "+auth.getIcon()+"  "+auth.getUrl()+"  "+auth.getDescription());
		//System.out.println("上级权限id  ："+vs.findValue("pid").toString());
		System.out.println("pid  :+"+ pid);
		//根据pid获取上级权限
		if(pid!=null&&!pid.equals("")){
			//不为null或者不是""
			auth.setAuth(authService.findAuthById(Integer.parseInt(pid)));
		}else{
			auth.setAuth(null);
		}
		boolean result=authService.updateAuth(auth);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 删除权限信息
	 */
	public void delAuth(){
		System.out.println("authId   :  "+authId);
		//删除该权限信息
		auth=authService.findAuthById(authId);
		
		//boolean result=authService.deleteAuth(auth);
		boolean result=this.delChilds(auth);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 递归删除子权限节点
	 */
	public boolean delChilds(Auth auth){
		System.out.println("待删除权限名字：   "+auth.getId()+"   "+auth.getName());
		//查找所有以该权限为父权限的子权限
		List<Auth> childs=authService.findChildAuthsById(auth.getId());
		if(childs!=null&&childs.size()>0){
			System.out.println("它孩子个数为   :"+childs.size());
			for(Auth a:childs){
				delChilds(a);
			}
		}
		//没有子权限
		System.out.println("已删除权限名字：   "+auth.getId()+"   "+auth.getName());
		System.out.println("没孩子");
		return authService.deleteAuth(auth);
		
	}
	
	/**
	 * 根据角色id获取该角色的所有权限
	 */
	public void getAuthsRoleId(){
		//System.out.println("roleId   :  "+roleId);
		//根据角色id查找所有角色权限
		List<Auth> auths=authService.findAllAuthsByRoleId(roleId);
		System.out.println("auths.size:   "+auths.size());
		String resourceIds="";
		if(auths!=null&&auths.size()>0){
			for(int i=0,size=auths.size();i<size;i++){
				resourceIds+=auths.get(i).getId();
				if(i!=size-1){
					resourceIds+=",";
				}
			}
		}
		//System.out.println("resourceIds  :"+resourceIds);
		Json json=new Json();
		json.setMsg(resourceIds);
		super.writeJson(json);
	}
	
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
