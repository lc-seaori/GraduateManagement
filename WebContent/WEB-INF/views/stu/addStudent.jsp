<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<br/>
<br/>
<div class="easyui-panel" style="padding: 30px 70px 50px 70px" align="center">
	<s:form id="addStudentForm" action="StudentInfo_AddNewInfo_addStudent.action" namespace="/" method="post" theme="simple" enctype="multipart/form-data">
		<s:hidden name="id"/>
		<table cellpadding="7">
			<tr>
				<td>学	号：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="code" id="code"  
						data-options="required:true" missingMessage="必须填写考生学号" 
						style="width: 150px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
				
			</tr>
			
			<tr>
				<td>姓	名：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="stuName" id="stuName" readonly="true" 
						style="width: 150px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			
			<tr>
				<td>学	院：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="departmentName" id="departmentName" readonly="true" 
						style="width: 150px; height: 15px; padding: 5px" />
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			
			<tr>
				<td>班	别：</td>
				<td>
					<s:select list="#request.classes" listKey="id" listValue="name" headerKey="0" headerValue="--选择--" 
					cssClass="easyui-combobox" editable="false" 
					id="clazz" name="clazz" style="width: 80px"
					data-options="panelWidth:'60px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
			
			<tr>
				<td>学籍状态：</td>
				<td>
					<s:select list="#request.schoolStatus" listKey="id" listValue="name" headerKey="0" headerValue="--选择--" 
					cssClass="easyui-combobox" editable="false" 
					id="schoolStatus" name="schoolStatus" style="width: 80px"
					data-options="panelWidth:'60px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
		</table>
	</s:form>
	<br/>
	<br/>
	<div style="text-align: center; padding: 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" 
			onclick="toAddStudent('#addStudentForm')">添加</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-no" 
			onclick="exitAddStudentPage()">关闭</a>
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
		
  		$('#addStudentForm').find('#code').change(function(){
  			var code = $(this).val().trim();
			var url="StudentInfo_AddNewInfo_validateCode";
			var args={"code":code};
			$.post(url,args,function(data){
				if(data=="1"){
					//alert("可以用");
				}else if(data=="0"){
					//表示不可用
					$.messager.show({    // show error message
                        title: '禁止提示',
                        msg: '		该学号已存在，请确认后再重新输入',
                        timeout:4000,
                        height:150
                    });
				}else{
					$.messager.alert("系统提示", "服务器出错","warning");
				}
			});
  		});
  	});

	function exitAddStudentPage() {
		$.messager.confirm('请确认','关闭之后审核将会被取消，确定关闭吗？',function(r){
			if(r){
				closeTab(getCurrentTab());
			}
		});
	}
	
	//表单提交
	function toAddStudent(form){
    	//在提交数据之前执行表单验证
		$('#addStudentForm').form('enableValidation').form('validate'); 
    	
    	//获取所有下拉控件的值
    	var clazzVal=$('#clazz').combobox('getValue');
    	var schoolStatusVal=$('#schoolStatus').combobox('getValue');
    	//alert(clazzVal+"   "+schoolStatusVal);
    	
    	//判断表单验证是否通过
		if(!$(form).form('validate')){
			$.messager.alert("错误提示", "注意填写完整信息！","info");  
			return false;
		}else if(clazzVal=="0"||schoolStatusVal=="0"){
			$.messager.alert("错误提示", "注意填写完整信息！","info"); 
			return false;
		}
		
		$(form).ajaxSubmit({
		     type: 'post',
		     url: "StudentInfo_AddNewInfo_addStudent.action" ,
		     success: 
		    	function(data){
		    		var result=eval("("+data+")");
		    		//alert(result.success);
		    		if (result.success){
		   	$.messager.alert("操作提示", "学校审核成功","info");   
		   	$('#schoolAduitList').datagrid('reload');
		   	closeTab(getCurrentTab());
		} else{
			$.messager.alert("操作提示",result.msg ,"error");
		}
		   	}
		});   
	}
      
</script>
