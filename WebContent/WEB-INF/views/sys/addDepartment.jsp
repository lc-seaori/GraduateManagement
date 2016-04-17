<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="easyui-panel" style="padding: 30px 70px 50px 70px" align="center">
	<s:form id="addDepForm" namespace="/" method="post" theme="simple" enctype="multipart/form-data">
		<s:hidden name="id"/>
		<table cellpadding="7">
			<tr>
				<td>学院名称：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="name" id="name"  
						data-options="required:true" missingMessage="必须输入项" placeholder="请输入学院名称" 
						style="width: 150px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			
			<tr>
				<td>学院描述:</td>
				<td><s:textarea cssClass="easyui-textbox" id="remark" 
					name="remark" data-options="multiline:true" 
					style="width: 230px; height: 80px; padding: 5px"></s:textarea>
				</td>
			</tr>
		</table>
	</s:form>
	<br/>
	<div style="text-align: center; padding: 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" 
			onclick="toAddDeps('#addDepForm')">添加</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-no" 
			onclick="exitAddDepPage()">关闭</a>
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
  	});

	function exitAddDepPage(){
		$.messager.confirm('请确认','确认关闭添加页面？',function(r){
			if(r){
				$('#addDepDialog').dialog('close');
			}
		});
	}
	
	//表单提交
	function toAddDeps(form){
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
		     url: "System_Department_addDepartment.action",
		     success: 
		    	function(data){
		    		var result=eval("("+data+")");
		    		if (result.success){
		    			//关闭添加页面
		    			$('#addDepDialog').dialog('close');
					   	$.messager.show({
			                title:'操作提示',
			                msg:'添加学院信息成功',
			                showType:'show'
			            });
					   	//重新加载
					   	$('#departmentList').datagrid('reload');
					} else{
						$.messager.alert("操作提示","添加学院信息失败" ,"error");
					}
				}
		});    
	}
      
</script>
