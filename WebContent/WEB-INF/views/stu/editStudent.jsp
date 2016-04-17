<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="easyui-panel" style="padding: 30px 70px 50px 70px" align="center">
	<s:form id="editStudentForm" action="Student_editStudent.action" namespace="/" method="post" theme="simple" enctype="multipart/form-data">
		<s:hidden id="id" name="id"/>
		<table cellpadding="7">
			<tr>
				<td>学	号：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="code" name="code" 
						data-options="required:true" missingMessage="必须填写学号" 
						style="width: 200px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			
			<tr>
				<td>姓	名：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="name" name="name" readonly="true"  
						style="width: 200px; height: 15px; padding: 5px"/>
				</td>
			</tr>
			
			<tr>
				<td>学	院：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="departmentName" name="departmentName" readonly="true"
						style="width: 200px; height: 15px; padding: 5px"/>
				</td>
			</tr>
			
			<tr>
				<td>毕业时间：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="graduateTime" name="graduateTime" readonly="true"
						style="width: 200px; height: 15px; padding: 5px"/>
				</td>
			</tr>
			
			<tr>
				<td>班	别：</td>
				<td>
					<s:select list="#request.classes" listKey="id" listValue="name" headerKey="0" headerValue="--选择--" 
					cssClass="easyui-combobox" editable="false" 
					id="clazzName" name="clazzVal" style="width: 80px"
					data-options="panelWidth:'60px', panelHeight:'auto'" value="#request.classVal"></s:select>
				</td>
			</tr>
			
			<tr>
				<td>学籍状态：</td>
				<td>
					<s:select list="schoolStatus" listKey="id" listValue="name" headerKey="0" headerValue="--选择--" 
					cssClass="easyui-combobox" editable="false" 
					id="stuStatus" name="stuStatusVal" style="width: 80px"
					data-options="panelWidth:'60px', panelHeight:'auto'" value="#request.stuStatus"></s:select>
				</td>
			</tr>
		</table>
	</s:form>
	
	<div style="text-align: center; padding: 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" 
			onclick="editStudent('#editStudentForm')">更新</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-no" 
			onclick="exitEditStudentPage()">关闭</a>
	</div>
</div>
<script type="text/javascript">
	function exitEditStudentPage() {
		$.messager.confirm('请确认','您确定放弃编辑，离开页面  ？',function(r){
            if (r){
            	closeTab(getCurrentTab());
            }
		});
	}
	
	//表单提交
	function editStudent(form){
    	//在提交数据之前执行表单验证
		$(form).form('enableValidation').form('validate'); 
    	
    	//获取所有下拉控件的值
    	var clazzVal=$('#editStudentForm').find('#clazzName').combobox('getValue');
    	var stuStatusVal=$('#editStudentForm').find('#stuStatus').combobox('getValue');
    	
    	//判断表单验证是否通过
		if(!$(form).form('validate')){
			$.messager.alert("错误提示", "注意填写完整信息！","info");  
			return false;
		}else if(clazzVal=="0"||stuStatusVal=="0"){
			$.messager.alert("错误提示", "注意填写完整信息！","info"); 
			return false;
		}
		//alert(majorFieldSelectedVal);
		
		$(form).ajaxSubmit({
                  type: 'post',
                  url: "Student_editStudent.action",
                  success: 
                 	function(data){
                 		var result=eval("("+data+")");
                 		if (result.success){
                 			$.messager.show({    // show error message
                              title: 'Tips',
                              msg: '更新信息成功'
                          	});
                 			$('#studentList').datagrid('reload'); 
 		        		} else{
 		        			$.messager.alert("操作提示", "更新信息失败！","warning");
 		        		}
                 		closeTab(getCurrentTab());
                	}
            	});    
	}      
</script>
	