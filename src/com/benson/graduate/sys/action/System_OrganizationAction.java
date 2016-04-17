package com.benson.graduate.sys.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.base.pagemodel.TreeNode;
import com.benson.graduate.sys.model.Organization;
import com.benson.graduate.sys.service.OrganizationService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 组织部门Action
 * @author benson
 *
 */
@Controller("organizationAction")
@Scope("prototype")
public class System_OrganizationAction extends BaseAction implements Preparable,ModelDriven<Organization>{
	
	private static final long serialVersionUID = 8487908606196316754L;
	private OrganizationService organizationService;
	@Resource(name="organizationService")
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	private Organization organization=new Organization();
	@Override
	public Organization getModel() {
		// TODO Auto-generated method stub
		return organization;
	}
	
	//接收addOrganization.jsp页面传来的上级权限id
	private String pid;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	//接收从organizationList.jsp传来的组织部门id
	private Integer orgId;
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	/**
	 * 获取组织部门树数据
	 */
	public void getTree(){
		List<TreeNode> trees=new ArrayList<TreeNode>();
		List<Organization> organizations=organizationService.findAllOrganization();
		if(organizations!=null&&organizations.size()>0){
			for(Organization o:organizations){
				TreeNode treeNode=this.treeRecursion(o);
				Organization temp=organizationService.findOrganizationById(Integer.parseInt(treeNode.getId()));
				if(temp.getOrganization()==null){
					trees.add(treeNode);
				}
			}
		}
		super.writeJson(trees);
	}
	
	public TreeNode treeRecursion(Organization organization){
		TreeNode treeNode =new TreeNode();
		treeNode.setId(organization.getId()+"");
		treeNode.setText(organization.getName());
		treeNode.setIconCls(organization.getIcon());
		//找出以该组织部门为父部门的所有子部 门
		List<Organization> organizations=organizationService.findChildrensByOrganizationId(organization.getId());
		if(organizations!=null&&organizations.size()>0){
			treeNode.setState("closed");
			List<TreeNode> childs=new ArrayList<TreeNode>();
			for(Organization o:organizations){
				TreeNode t=treeRecursion(o);
				childs.add(t);
			}
			treeNode.setChildren(childs);
		}
		return treeNode;
	}
	
	/**
	 * 组织部门列表
	 * @return
	 */
	public String toOrgListPage(){
		return "orgListPage";
	}
	
	
	/**
	 * 获取组织部门树的Grid
	 */
	public void getOrganizationList(){
		super.writeJson(organizationService.treeGrid());
	}
	
	/**
	 * 添加组织部门页面
	 */
	public String addOrganizationPage(){
		return "addOrganizationPage";
	}
	
	/**
	 * 获取下拉树数据或者是树数据(combotree/tree)
	 */
	public void doNotNeedSession_tree(){
		System.out.println("doNotNeedSession_tree()");
		super.writeJson(organizationService.combotree());
	}

	/**
	 * 添加组织部门
	 */
	public void doAddOrganization(){
		//根据pid获取上级权限
		if(pid!=null&&!pid.equals("")){
			//不为null或者不是""
			organization.setOrganization(organizationService.findOrganizationById(Integer.parseInt(pid)));
		}else{
			organization.setOrganization(null);
		}
		boolean result=organizationService.addOrganization(organization);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void prepareToEditOrganizationPage(){
		//查找父级部门id
		organization=organizationService.findOrganizationById(orgId);
		if(organization.getOrganization()!=null){
			request.setAttribute("parentId", organization.getOrganization().getId());
		}
	}
	
	/**
	 * 编辑组织部门信息页面
	 */
	public String toEditOrganizationPage(){
		return "editOrganizationPage";
	}
	
	/**
	 * 编辑组织部门信息
	 */
	public void doEditOrganization(){
		//根据pid获取上级权限
		if(pid!=null&&!pid.equals("")){
			//不为null或者不是""
			organization.setOrganization(organizationService.findOrganizationById(Integer.parseInt(pid)));
		}else{
			organization.setOrganization(null);
		}
		boolean result=organizationService.updateOrganization(organization);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 删除组织部门信息
	 */
	public void delOrganization(){
		//删除该组织信息
		organization=organizationService.findOrganizationById(orgId);
		boolean result=this.delChilds(organization);
		Json json=new Json();
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 递归删除子组织部门节点
	 */
	public boolean delChilds(Organization organization){
		System.out.println("待删除组织部门名字：   "+organization.getId()+"   "+organization.getName());
		//查找所有以该权限为父权限的子权限
		List<Organization> childs=organizationService.findChildOrganizationsById(organization.getId());
		if(childs!=null&&childs.size()>0){
			System.out.println("它孩子个数为   :"+childs.size());
			for(Organization o:childs){
				delChilds(o);
			}
		}
		//没有子权限
		System.out.println("已删除权限名字：   "+organization.getId()+"   "+organization.getName());
		System.out.println("没孩子");
		return organizationService.deleteOrganization(organization);
		
	}

}
