<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="easyui-panel" style="padding: 30px 70px 50px 70px" align="center">
	<s:form id="addOrganizationForm" namespace="/" method="post" theme="simple" enctype="multipart/form-data">
		<s:hidden name="id"/>
		<table cellpadding="7">
			<tr>
				<td>组织部门名称：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="name" id="name"  
						data-options="required:true" missingMessage="必须输入项" placeholder="请输入组织部门名称" 
						style="width: 150px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
				
			</tr>
			
			<tr>
				<td>组织部门图标：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="icon" id="icon"  
						placeholder="输入组织部门图标名称" style="width: 150px; height: 15px; padding: 5px"/>
				</td>
				
			</tr>
			
			<tr>
				<td>上级组织部门：</td>
				<td>
					<s:textfield name="pid" id="pid" style="width: 240px; height: 25px; padding: 5px" />
					<a style="height: 25px" class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#pid').combotree('clear');" >清空</a>
				</td>
			</tr>
			
			<tr>
				<td>组织部门描述:</td>
				<td><s:textarea cssClass="easyui-textbox" id="description" 
					name="description" data-options="multiline:true" 
					style="width: 230px; height: 80px; padding: 5px"></s:textarea>
				</td>
			</tr>
		</table>
	</s:form>
	<br/>
	<div style="text-align: center; padding: 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" 
			onclick="toAddOrganizations('#addOrganizationForm')">添加</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-no" 
			onclick="exitAddOrganizationPage()">关闭</a>
	</div>
</div>
<script type="text/javascript">
  	
  	$(function(){
  		//解决浏览器不兼容tirm()方法，如ie8以下不兼容trim()，该放去去掉前后空格
		if(!String.prototype.trim) {
			String.prototype.trim = function () {
		    	return this.replace(/^\s+|\s+$/g,'');
		  	};
		}
  		
		//初始化下拉树(combotree)
  		$('#addOrganizationForm').find('#pid').combotree({
  			lines : true,
			url : 'Organization_doNotNeedSession_tree.action'
  		});
  	});

	function exitAddOrganizationPage(){
		$.messager.confirm('请确认','确认关闭添加页面？',function(r){
			if(r){
				$('#addOrganizationDialog').dialog('close');
			}
		});
	}
	
	//表单提交
	function toAddOrganizations(form){
    	//在提交数据之前执行表单验证
		$(form).form('enableValidation').form('validate'); 
    	
    	//判断表单验证是否通过
		if(!$(form).form('validate')){
			$.messager.alert("错误提示", "注意填写必须填写的信息！","info");  
			return false;
		}
    	
    	//发送异步请求
		$(form).ajaxSubmit({
		     type: 'post',
		     url: "Organization_doAddOrganization.action",
		     success: 
		    	function(data){
		    		var result=eval("("+data+")");
		    		if (result.success){
		    			//关闭添加页面
		    			$('#addOrganizationDialog').dialog('close');
					   	$.messager.show({
			                title:'操作提示',
			                msg:'添加组织部门成功',
			                showType:'show'
			            });
					   	//重新加载
					   	$('#organizationList').treegrid('reload');
					} else{
						$.messager.alert("操作提示","添加组织部门失败" ,"error");
					}
				}
		});    
	}
      
</script>
