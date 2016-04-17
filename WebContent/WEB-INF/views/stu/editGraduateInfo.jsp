<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="easyui-panel" style="padding: 30px 70px 50px 70px" align="center">
	<s:form id="editGraduateInfoForm" action="GraduateInfo_Edit_editGraduateInfo.action" namespace="/" method="post" theme="simple" enctype="multipart/form-data">
		<s:hidden id="id" name="id"/>
		<s:hidden name="departmentGAduitStatus"></s:hidden>
		<s:hidden name="schoolGAduitStatus"></s:hidden>
		<s:hidden name="stuId" value="%{#request.stuId}"></s:hidden>
		<table cellpadding="7">
			<tr>
				<td>学生姓名：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="name" name="name" 
						data-options="required:true" missingMessage="必须填写学生姓名" readonly="true" 
						style="width: 200px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			
			<tr>
				<td>单位名称：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="unitName" name="unitName" 
						data-options="required:true" missingMessage="必须填写就业单位名称" 
						style="width: 200px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>

			<tr>
				<td>单位所在地（省市）：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="unitLocation" name="unitLocation"  
						style="width: 200px; height: 15px; padding: 5px"/>
				</td>
			</tr>
			<!-- 
			<tr>
				<td>单位所在地（省市）：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="unitLocation" name="unitLocation"  
						data-options="required:true" missingMessage="必须填写单位所在地" 
						style="width: 200px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px">*</font>
				</td>
			</tr>
			 -->
			
			<tr>
				<td>单位性质：</td>
				<td>
					<s:select list="#request.unitTypes" listKey="id" listValue="name" headerKey="0" headerValue="--选择--" 
					cssClass="easyui-combobox" editable="false" 
					id="unitTy" name="unitType" style="width: 120px"
					data-options="panelWidth:'100px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
			
			<tr>
				<td>应聘方式：</td>
				<td>
					<s:select list="#request.applyTypes" listKey="id" listValue="name" headerKey="0" headerValue="--选择--" 
					cssClass="easyui-combobox" editable="false" 
					id="applyTy" name="applyType" style="width: 100px"
					data-options="panelWidth:'80px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
			
			<tr>
				<td>单位所属行业：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="unitIndustry" name="unitIndustry"  
						style="width: 200px; height: 15px; padding: 5px"/>
				</td>
			</tr>
			<!-- 
			<tr>
				<td>单位所属行业：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="unitIndustry" name="unitIndustry"  
						data-options="required:true" missingMessage="必须填写就业单位行业" 
						style="width: 200px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			 -->
			
			<tr>
				<td>单位联系人：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="unitContantPerson" name="unitContantPerson" 
						style="width: 200px; height: 15px; padding: 5px"/>
				</td>
			</tr>
			<!-- 
			<tr>
				<td>单位联系人：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="unitContantPerson" name="unitContantPerson" 
						data-options="required:true" missingMessage="必须填写学单位联系人"
						style="width: 200px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px">*</font>
				</td>
			</tr>
			 -->
			
			<tr>
				<td>单位联系方式：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="unitContantPhone" name="unitContantPhone" 
						style="width: 200px; height: 15px; padding: 5px"/>
				</td>
			</tr>
			<!-- 
			<tr>
				<td>单位联系方式：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="unitContantPhone" name="unitContantPhone" 
						data-options="required:true" missingMessage="必须填写单位联系方式"
						style="width: 200px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px">*</font>
				</td>
			</tr>
			 -->
			
			<tr>
				<td>毕业生联系方式：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="graduatePhone" name="graduatePhone" 
						style="width: 200px; height: 15px; padding: 5px"/>
				</td>
			</tr>
			<!-- 
			<tr>
				<td>毕业生联系方式：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="graduatePhone" name="graduatePhone" 
						data-options="required:true" missingMessage="必须填写毕业生联系方式"
						style="width: 200px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px">*</font>
				</td>
			</tr>
			 -->
			
			<tr>
				<td>单位邮政编码：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="unitPostEncoding" name="unitPostEncoding" 
						data-options="validType:'ZipCode'" style="width: 200px; height: 15px; padding: 5px"/>
				</td>
			</tr>
			<!-- 
			<tr>
				<td>单位邮政编码：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="unitPostEncoding" name="unitPostEncoding" 
						data-options="required:true,validType:'ZipCode'" 
						style="width: 200px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px">*</font>
				</td>
			</tr>
			 -->
			
			<tr>
				<td>单位详细地址:</td>
				<td><s:textarea cssClass="easyui-textbox" type="text" id="unitAddress" 
					name="unitAddress" data-options="multiline:true" 
					style="width: 400px; height: 50px; padding: 5px"></s:textarea>
				</td>
			</tr>
		</table>
	</s:form>
	
	<div style="text-align: center; padding: 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" 
			onclick="editGraduateInfo('#editGraduateInfoForm')">更新</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-no" 
			onclick="exitEditGraduateInfoPage()">关闭</a>
	</div>
</div>
<script type="text/javascript">
	//扩展easyui表单的验证
	$.extend($.fn.validatebox.defaults.rules, {
		//验证长度
		minLength: { 
			validator: function(value, param){ 
				return value.length == param[0]; 
			}, 
			message: '请输入正确的身份证号码' 
		} ,
		//验证汉字
	    CHS: {
	        validator: function (value) {
	            return /^[\u0391-\uFFE5]+$/.test(value);
	        },
	        message: 'The input Chinese characters only.'
	    },
	    //手机号码验证
	    Mobile: {//value值为文本框中的值
	        validator: function (value) {
	            var reg = /^1[3|4|5|8|9]\d{9}$/;
	            return reg.test(value);
	        },
	        message: '请输入正确的手机号码'
	    },
	    //国内邮编验证
	    ZipCode: {
	        validator: function (value) {
	            var reg = /^[0-9]\d{5}$/;
	            return reg.test(value);
	        },
	        message: '请输入正确的邮编'
	    },
	  //数字
	    Number: {
	        validator: function (value) {
	            var reg =/^[0-9]*$/;
	            return reg.test(value);
	        },
	        message: 'Please input number.'
	    },
	});
	function exitEditGraduateInfoPage() {
		$.messager.confirm('请确认','您确定放弃编辑，离开页面  ？',function(r){
            if (r){
            	closeTab(getCurrentTab());
            }
		});
	}
	
	//表单提交
	function editGraduateInfo(form){
    	//在提交数据之前执行表单验证
		$(form).form('enableValidation').form('validate'); 
    	
    	//获取所有下拉控件的值
    	var unitTyVal=$(form).find('#unitTy').combobox('getValue');
    	var applyTyVal=$(form).find('#applyTy').combobox('getValue');
    	
    	//判断表单验证是否通过
		if(!$(form).form('validate')){
			$.messager.alert("错误提示", "注意填写完整信息！","info");  
			return false;
		}else if(unitTyVal=="0"||applyTyVal=="0"){
			$.messager.alert("错误提示", "注意填写完整信息！","info"); 
			return false;
		}
		
		$(form).ajaxSubmit({
			type: 'post',
			url: "GraduateInfo_Edit_editGraduateInfo.action",
			success: 
			function(data){
				var result=eval("("+data+")");
				if (result.success){
					$.messager.show({    // show error message
			            title: 'Tips',
			            msg: '更新信息成功'
			        	});
					$('#graduateInfoList').datagrid('reload'); 
					$('#departmentGAduitList').datagrid('reload'); 
					$('#schoolGAduitList').datagrid('reload'); 
				}else{
					$.messager.alert("操作提示", "更新信息失败！","warning");
				}
				    		closeTab(getCurrentTab());
				   	}
				});    
	} 
      
</script>
	