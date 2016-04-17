<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<br/>
<br/>
<div class="easyui-panel" style="padding: 30px 70px 50px 70px" align="center">
	<s:form id="editAuthForm" namespace="/" method="post" theme="simple" enctype="multipart/form-data">
		<s:hidden name="id"/>
		<s:hidden name="createTime"/>
		<s:hidden  id="parentId" name="authId" value="%{#request.parentId}"></s:hidden>
		<table cellpadding="7">
			<tr>
				<td>权限名称：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="name" id="name"  
						data-options="required:true" missingMessage="必须输入项" placeholder="请输入权限名称" 
						style="width: 150px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
				
			</tr>
			
			<tr>
				<td>权限地址：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="url" id="url" 
						placeholder="输入权限地址（url）" style="width: 150px; height: 15px; padding: 5px"/>
				</td>
			</tr>
			
			<tr>
				<td>权限图标：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="icon" id="icon"  
						placeholder="输入权限图标名称" style="width: 150px; height: 15px; padding: 5px"/>
				</td>
				
			</tr>
			
			<tr>
				<td>上级权限：</td>
				<td>
					<s:textfield name="pid" id="pid" style="width: 240px; height: 25px; padding: 5px"/>
					<a style="height: 25px" class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#pid').combotree('clear');" >清空</a>
				</td>
			</tr>
			
			<tr>
				<td>权限描述:</td>
				<td><s:textarea cssClass="easyui-textbox" id="description" 
					name="description" data-options="multiline:true" 
					style="width: 230px; height: 80px; padding: 5px"></s:textarea>
				</td>
			</tr>
		</table>
	</s:form>
	<br/>
	<br/>
	<div style="text-align: center; padding: 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" 
			onclick="toEditAuths('#editAuthForm')">编辑</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-no" 
			onclick="exitEditAuthPage()">关闭</a>
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
  		$('#editAuthForm').find('#pid').combotree({
  			lines : true,
			url : 'System_Auth_doNotNeedSession_tree.action',
			onLoadSuccess:function(row,data){
				//数据异步加载完成执行这个
				var parentId=$('#editAuthForm').find('#parentId').val();
				$('#editAuthForm').find('#pid').combotree('setValue',parentId);
			}
  		});
  	});

	function exitEditAuthPage() {
		$.messager.confirm('请确认','确认取消编辑，退出页面？',function(r){
			if(r){
				closeTab(getCurrentTab());
			}
		});
	}
	
	//表单提交
	function toEditAuths(form){
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
		     url: "System_Auth_doEditAuth.action",
		     success: 
		    	function(data){
		    		var result=eval("("+data+")");
		    		if (result.success){
					   	$.messager.alert("操作提示", "编辑权限成功","info");  
					   	//重新加载
					   	$('#authList').treegrid('reload');
					   	//关闭添加页面
					   	closeTab(getCurrentTab());
					} else{
						$.messager.alert("操作提示","编辑权限失败" ,"error");
					}
				}
		});    
	}
      
</script>
