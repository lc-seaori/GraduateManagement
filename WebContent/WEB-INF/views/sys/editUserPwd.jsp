<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	$(function(){
		$.extend($.fn.validatebox.defaults.rules, {
	    	/*必须和某个字段相等*/
	   		equalTo: { validator: function (value, param) { return $(param[0]).val() == value; }, message: '两次输入的密码不匹配' }
	    });
	});
</script>

<div class="easyui-panel" style="padding: 30px 70px 50px 70px" align="center">
	<s:form id="editUserPwdForm" action="User_doNotNeedAuth_editUserPwd.action" namespace="/" method="post" theme="simple" enctype="multipart/form-data">
		<table cellpadding="7">
			<tr>
				<td>登录名:</td>
				<td>
					${sessionInfo.name}
				</td>
			</tr>
			<tr>
				<td>原密码：</td>
				<td>
					<s:password cssClass="easyui-validatebox textbox" name="oldPwd" id="oldPwd"  
						data-options="required:true" missingMessage="必须输入项" placeholder="请输入原密码" 
						style="width: 150px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			<tr>
				<td>新密码：</td>
				<td>
					<s:password cssClass="easyui-validatebox textbox" name="newPwd" id="newPwd"  
						data-options="required:true" missingMessage="必须输入项" placeholder="请输入新密码" 
						style="width: 150px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			<tr>
				<td>再次输入：</td>
				<td>
					<s:password cssClass="easyui-validatebox textbox" name="rePwd" id="rePwd"  
						data-options="required:true" missingMessage="必须输入项" placeholder="请再次输入新密码" 
						validType="equalTo['#newPwd']" invalidMessage="两次输入的密码不匹配" 
						style="width: 150px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
		</table>
	</s:form>
	<div style="text-align: center; padding: 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="toEditUserPwd('#editUserPwdForm')">修改</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="exitEditPwdPage()">关闭</a>
	</div>
</div>
<script type="text/javascript">
	//修改密码
	function toEditUserPwd(form){
		//在提交数据之前执行表单验证
		$(form).form('enableValidation').form('validate'); 
		$(form).ajaxSubmit({
		     type: 'post',
		     url: "User_doNotNeedAuth_editUserPwd.action" ,
		     success: 
		    	function(data){
		    		var result=eval("("+data+")");
		    		if (result.success){
					   	$.messager.alert("操作提示", result.msg,"info");   
					   	$('#editDialog').dialog('close');
					} else{
						$.messager.alert("操作提示",result.msg ,"error");
					}
				}
		});   
	}
	
	//取消修改密码
	function exitEditPwdPage(){
		$.messager.confirm('请确认','您确定取消修改密码吗？',function(r){
            if (r){
            	$('#editDialog').dialog('close');
            }
        });
	}
	</script>