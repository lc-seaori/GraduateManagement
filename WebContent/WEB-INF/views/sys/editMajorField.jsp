<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="easyui-panel" style="padding: 30px 70px 50px 70px" align="center">
	<s:form id="editMajorForm" namespace="/" method="post" theme="simple" enctype="multipart/form-data">
		<s:hidden name="id"/>
		<table cellpadding="7">
			<tr>
				<td>所属学院：</td>
				<td>
					<s:select list="#request.departments" listKey="id" listValue="name" headerKey="0" headerValue="--选择--" 
					cssClass="easyui-combobox" editable="false" 
					id="departmentSelected" name="department.id" style="width: 140px" 
					data-options="panelWidth:'120px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
		
			<tr>
				<td>专业名称：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="name" id="name" 
						data-options="required:true" missingMessage="必须输入项" placeholder="请输入专业名称" 
						style="width: 150px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			
			<tr>
				<td>专业描述:</td>
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
			onclick="toEditMajors('#editMajorForm')">更新</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-no" 
			onclick="exitEditMajorPage()">关闭</a>
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

	function exitEditMajorPage(){
		$.messager.confirm('请确认','确认放弃修改，离开页面？',function(r){
			if(r){
				$('#editMajorDialog').dialog('close');
			}
		});
	}
	
	//表单提交
	function toEditMajors(form){
    	//在提交数据之前执行表单验证
		$(form).form('enableValidation').form('validate'); 
    	
    	var departmentSelected=$(form).find('#departmentSelected').combobox('getValue');
    	
    	//判断表单验证是否通过
		if(!$(form).form('validate')){
			$.messager.alert("错误提示", "注意填写必须填写的信息！","info");  
			return false;
		}else if(departmentSelected=="0"){
			$.messager.alert("错误提示", "注意选择学院！","info");  
			return;
		}
    	
    	//发送异步请求
		$(form).ajaxSubmit({
		     type: 'post',
		     url: "System_Major_editMajor.action",
		     success: 
		    	function(data){
		    		var result=eval("("+data+")");
		    		if (result.success){
		    			//关闭添加页面
		    			$('#editMajorDialog').dialog('close');
					   	$.messager.show({
			                title:'操作提示',
			                msg:'修改专业信息成功',
			                showType:'show'
			            });
					   	//重新加载
					   	$('#majorList').datagrid('reload');
					} else{
						$.messager.alert("操作提示","修心专业信息失败" ,"error");
					}
				}
		});    
	}
      
</script>
