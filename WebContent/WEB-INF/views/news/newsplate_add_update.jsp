<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div class="easyui-panel" style="padding: 30px 70px 50px 70px" align="center">
	<s:form id="newsPlateForm" action=".action" namespace="/" method="post" theme="simple" enctype="multipart/form-data">
		<input type="hidden" name="isAdd" value="${isAdd}"/>
		<input type="hidden" name="type" value="${type}"/>
		<input type="hidden" name="newsPlateId" value="${newsPlateId}"/>
		<c:if test="${isAdd == '0'}">
			<input type="hidden" id="platePid" name="platePid" value="${newsPlate.newsPlate.id}"/>
		</c:if>
		<table cellpadding="7">
			<tr>
				<td>版块名称:</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="plateName" id="plateName"  
						data-options="required:true" missingMessage="必须输入项" placeholder="请输入版块名称" 
						style="width: 120px; height: 15px; padding: 5px" value="%{#request.newsPlate.plateName}"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			<c:if test="${type == '2'}">
				<tr>
					<td>一级版块：</td>
					<td>
						<s:select list="%{#request.rootNewsPlateList}" listKey="id" listValue="plateName" headerKey="0" headerValue="--选择--" 
						cssClass="easyui-combobox" editable="false" 
						id="plateSelected" name="plateSelected" style="width: 80px" 
						data-options="panelWidth:'60px', panelHeight:'auto'"></s:select>
					</td>
				</tr>
			</c:if>
		</table>
	</s:form>
	<div style="text-align: center; padding: 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="saveNewsPlateData('#newsPlateForm')">提交</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="exitNewsPlatePage()">关闭</a>
	</div>
</div>
<script type="text/javascript">
	<c:if test="${isAdd == '0'}">
		//初始化下拉框
		$(function(){
			$('#plateSelected').find('option').each(function(){
				if($(this).val() == $('#platePid').val()){
					$(this).attr("selected","selected");
				}
			});
		});
	</c:if>
	
	//提交修改
	function saveNewsPlateData(form){
		//在提交数据之前执行表单验证
		//$(form).form('enableValidation').form('validate'); 
		if($('#plateSelected').length > 0){
			if($('#plateSelected').combobox('getValue') == '0'){
				$.messager.alert("操作提示","请选择一级版块" ,"error");
				return false;
			}
		}
		//判断表单验证是否通过
		if(!$(form).form('validate')){
			$.messager.alert("错误提示", "注意填写完整信息！","info");  
			return false;
		}
		
		$(form).ajaxSubmit({
		     type: 'post',
		     url: "newsplate_editSubmit.action" ,
		     success: 
		    	function(data){
		    		var result=eval("("+data+")");
		    		if (result.success){
					   	$.messager.alert("操作提示", result.msg,"info");   
					   	$('#editNewsPlateDialog').dialog('close');
					   	$('#newsPlateList').datagrid('reload');
					} else{
						$.messager.alert("操作提示",result.msg ,"error");
					}
				}
		});   
	}
	
	//取消修改密码
	function exitNewsPlatePage(){
		$.messager.confirm('请确认','您确定关闭吗？',function(r){
            if (r){
            	$('#editNewsPlateDialog').dialog('close');
            }
        });
	}
	</script>