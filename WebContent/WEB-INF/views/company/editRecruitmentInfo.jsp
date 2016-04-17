<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<br/>
<br/>
<div class="easyui-panel" style="padding: 30px 70px 50px 70px" align="center">
	<s:form id="editRecruitmentInfoForm" namespace="/" method="post" theme="simple" enctype="multipart/form-data">
		<s:hidden name="id"></s:hidden>
		<s:hidden name="unitId" value="%{#request.unitId}"/>
		<table cellpadding="7">
			<tr>
				<td>招聘公司：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="unitName" id="unitName" readonly="true" 
						style="width: 150px; height: 15px; padding: 5px" value="%{#request.unitName}"/>
				</td>
			</tr>
		
			<tr>
				<td>招聘职位：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="position" id="position"  
						data-options="required:true" missingMessage="必须填写招聘职位" 
						style="width: 150px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			
			<tr>
				<td>工资范围：</td>
				<td>
					<s:select list="#request.monthlySalarys" listKey="id" listValue="name" headerKey="0" headerValue="--选择--" 
					cssClass="easyui-combobox" editable="false" 
					id="monthlySalary" name="monthlySalary" style="width: 120px"
					data-options="panelWidth:'100px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
			
			<tr>
				<td>工作性质：</td>
				<td>
					<s:select list="#request.workTypes" listKey="id" listValue="name" headerKey="0" headerValue="--选择--" 
					cssClass="easyui-combobox" editable="false" 
					id="workType" name="workType" style="width: 120px"
					data-options="panelWidth:'100px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
			
			<tr>
				<td>行业领域：</td>
				<td>
					<s:select list="#request.industryTypes" listKey="id" listValue="name" headerKey="0" headerValue="--选择--" 
					cssClass="easyui-combobox" editable="false" 
					id="industryType" name="industryType" style="width: 120px"
					data-options="panelWidth:'100px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
			
			<tr>
				<td>学历要求：</td>
				<td>
					<s:select list="#request.educationTypes" listKey="id" listValue="name" headerKey="0" headerValue="--选择--" 
					cssClass="easyui-combobox" editable="false" 
					id="educationType" name="educationType" style="width: 120px"
					data-options="panelWidth:'100px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
			
			<tr>
				<td>招聘人数：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="hireCount" id="hireCount" 
						data-options="required:true" missingMessage="必须填写招聘人数（可以填写不限）" 
						style="width: 150px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			
			<tr>
				<td>发布时间：</td>
				<td>
					<input class="easyui-datebox" id="releaseTimes" name="releaseTimes" 
					data-options="required:true" style="width:130px; height: 25px; padding: 5px" editable="false" value="<s:property value='#request.startTime'/>"></input>
				</td>
			</tr>
			
			<tr>
				<td>截止时间：</td>
				<td>
					<input class="easyui-datebox" id="endTimes" name="endTimes" 
					data-options="required:true" style="width:130px; height: 25px; padding: 5px" editable="false" value="<s:property value='#request.endTime'/>"></input>
				</td>
			</tr>
			
			<tr>
				<td>职位描述：</td>
				<td><s:textarea cssClass="easyui-textbox" id="positionDescription" 
					name="positionDescription" data-options="multiline:true" 
					style="width: 400px; height: 200px; padding: 5px"></s:textarea>
				</td>
			</tr>
			<!-- 
			<tr>
				<td>职位描述：</td>
				<td><s:textarea cssClass="easyui-textbox" id="positionDescription" 
					name="positionDescription" 
					data-options="required:true,multiline:true" 
					style="width: 400px; height: 200px; padding: 5px"></s:textarea>
				</td>
			</tr>
			 -->
		</table>
	</s:form>
	<br/>
	<br/>
	<div style="text-align: center; padding: 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" 
			onclick="editRecruitmentInfo('#editRecruitmentInfoForm')">更新</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-no" 
			onclick="exitEditRecruitmentInfoPage()">关闭</a>
	</div>
</div>
<script type="text/javascript">
  	
	function exitEditRecruitmentInfoPage() {
		$.messager.confirm('请确认','您确定放弃编辑，离开页面  ？',function(r){
            if (r){
            	closeTab(getCurrentTab());
            }
		});
	}
	
	//表单提交
	function editRecruitmentInfo(form){
    	//在提交数据之前执行表单验证
		$('#editRecruitmentInfoForm').form('enableValidation').form('validate'); 
    	
    	//获取所有下拉控件的值
    	//var unitNameVal=$(form).find('#unitName').combobox('getValue');
    	var monthlySalaryVal=$(form).find('#monthlySalary').combobox('getValue');
    	var workTypeVal=$(form).find('#workType').combobox('getValue');
    	var industryTypeVal=$(form).find('#industryType').combobox('getValue');
    	var educationTypeVal=$(form).find('#educationType').combobox('getValue');
    	
    	//判断表单验证是否通过
		if(!$(form).form('validate')){
			$.messager.alert("错误提示", "注意填写完整信息！","info");  
			return false;
		}else if(monthlySalaryVal=="0"||workTypeVal=="0"||industryTypeVal=="0"||educationTypeVal=="0"){
			$.messager.alert("错误提示", "注意填写完整信息！","info"); 
			return false;
		}
		
		$(form).ajaxSubmit({
		     type: 'post',
		     url: "RecruitmentInfo_Eidt_editRecruitmentInfo.action" ,
		     success: 
		    	function(data){
		    		var result=eval("("+data+")");
		    		if (result.success){
					   	$.messager.alert("操作提示", "更新成功",'info');   
					   	$('#recruitmentInfoList').datagrid('reload');
					   	closeTab(getCurrentTab());
					}else{
						$.messager.alert("操作提示","更新失败",'error');
					}
				}
			});   
	}
      
</script>
