<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<br/>
<br/>
<div class="easyui-panel" style="padding: 30px 70px 50px 70px" align="center">
	<s:form id="addGraduateInfoForm" namespace="/" method="post" theme="simple" enctype="multipart/form-data">
		<s:hidden name="stuId"/>
		<table cellpadding="7">
			<tr>
				<td>姓	名：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="name" id="name" readonly="true" 
						style="width: 150px; height: 15px; padding: 5px" value="%{#request.stuName}"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			
			<tr>
				<td>就业单位名称：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="unitName" id="unitName"  
						data-options="required:true" missingMessage="必须填写就业单位名称" 
						style="width: 200px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			
			<tr>
				<td>单位所在地：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="unitLocation" id="unitLocation"   
						style="width: 200px; height: 15px; padding: 5px"/>
				</td>
			</tr>
			<!-- 
			<tr>
				<td>单位所在地：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="unitLocation" id="unitLocation"  
						data-options="required:true" missingMessage="必须填写就业单位所在地（xx省xx市）" 
						style="width: 200px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			 -->
			
			<tr>
				<td>单位性质：</td>
				<td>
					<s:select list="#request.unitTypes" listKey="id" listValue="name" headerKey="0" headerValue="--选择--" 
					cssClass="easyui-combobox" editable="false" 
					id="unitType" name="unitType" style="width: 120px"
					data-options="panelWidth:'100px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
			
			<tr>
				<td>就业单位行业：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="unitIndustry" id="unitIndustry"  
						style="width: 200px; height: 15px; padding: 5px"/>
				</td>
			</tr>
			<!-- 
			<tr>
				<td>就业单位行业：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="unitIndustry" id="unitIndustry"  
						data-options="required:true" missingMessage="必须填写就业单位所属行业" 
						style="width: 200px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			 -->
			
			<tr>
				<td>单位联系人：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="unitContantPerson" id="unitContantPerson"  
						style="width: 200px; height: 15px; padding: 5px"/>
				</td>
			</tr>
			<!-- 
			<tr>
				<td>单位联系人：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="unitContantPerson" id="unitContantPerson"  
						data-options="required:true" missingMessage="必须填写就业单位联系人" 
						style="width: 200px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			 -->
			
			<tr>
				<td>单位联系方式：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="unitContantPhone" id="unitContantPhone"  
						style="width: 200px; height: 15px; padding: 5px"/>
				</td>
			</tr>
			<!-- 
			<tr>
				<td>单位联系方式：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="unitContantPhone" id="unitContantPhone"  
						data-options="required:true" missingMessage="必须填写就业单位联系方式" 
						style="width: 200px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			 -->
			
			<tr>
				<td>毕业生联系方式：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="graduatePhone" id="graduatePhone"  
						style="width: 200px; height: 15px; padding: 5px"/>
				</td>
			</tr>
			<!-- 
			<tr>
				<td>毕业生联系方式：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="graduatePhone" id="graduatePhone"  
						data-options="required:true" missingMessage="必须填写毕业生联系方式" 
						style="width: 200px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			 -->
			
			<tr>
				<td>应聘方式：</td>
				<td>
					<s:select list="#request.applyTypes" listKey="id" listValue="name" headerKey="0" headerValue="--选择--" 
					cssClass="easyui-combobox" editable="false" 
					id="applyType" name="applyType" style="width: 120px"
					data-options="panelWidth:'100px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
			
			<tr>
				<td>单位邮政编码：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="unitPostEncoding" id="unitPostEncoding"  
						data-options="validType:'ZipCode'" style="width: 200px; height: 15px; padding: 5px"/>
				</td>
			</tr>
			<!-- 
			<tr>
				<td>单位邮政编码：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="unitPostEncoding" id="unitPostEncoding"  
						data-options="required:true,validType:'ZipCode'" missingMessage="必须填写单位邮政编码" 
						style="width: 200px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			 -->
			
			<tr>
				<td>单位详细地址:</td>
				<td><s:textarea cssClass="easyui-textbox" id="unitAddress" 
					name="unitAddress" data-options="multiline:true" 
					style="width: 400px; height: 50px; padding: 5px"></s:textarea>
				</td>
			</tr>
		</table>
	</s:form>
	<br/>
	<br/>
	<div style="text-align: center; padding: 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" 
			onclick="toAddGraduateInfo('#addGraduateInfoForm')">添加</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-no" 
			onclick="exitAddGraduateInfoPage()">关闭</a>
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
  	
  	//关闭
	function exitAddGraduateInfoPage() {
		closeTab(getCurrentTab());
	}
	
	//表单提交
	function toAddGraduateInfo(form){
    	//在提交数据之前执行表单验证
		$(form).form('enableValidation').form('validate'); 
    	
    	//获取所有下拉控件的值
    	var unitTypeVal=$(form).find('#unitType').combobox('getValue');
    	var applyTypeVal=$(form).find('#applyType').combobox('getValue');
    	//alert(unitTypeVal+  "  "+applyTypeVal);
    	//判断表单验证是否通过
		if(!$(form).form('validate')){
			$.messager.alert("错误提示", "注意填写完整信息！","info");  
			return false;
		}else if(unitTypeVal=="0"||applyTypeVal=="0"){
			$.messager.alert("错误提示", "注意填写完整信息！","info"); 
			return false;
		}
		
		$(form).ajaxSubmit({
		     type: 'post',
		     url: "GraduateInfo_Add_addGraudateInfo.action" ,
		     success: 
		    	function(data){
		    		var result=eval("("+data+")");
		    		//alert(result.success);
		    		if (result.success){
					   	$.messager.alert("操作提示", "添加成功","info");   
					   	//重新加载combogrid，并置空
					   	if($('#schoolGAduitListOperToolbar').find('#studentSelected').length>0){
					   		$('#schoolGAduitListOperToolbar').find('#studentSelected').combogrid('grid').datagrid('load');
						   	$('#schoolGAduitListOperToolbar').find('#studentSelected').combogrid('setValue','');
					   	}
					   	if($('#departmentGAduitListOperToolbar').find('#studentSelected').length>0){
					   		$('#departmentGAduitListOperToolbar').find('#studentSelected').combogrid('grid').datagrid('load');
						   	$('#departmentGAduitListOperToolbar').find('#studentSelected').combogrid('setValue','');
					   	}
					   	$('#graduateInfoOperToolbar').find('#studentSelected').combogrid('grid').datagrid('load');
					   	$('#graduateInfoOperToolbar').find('#studentSelected').combogrid('setValue','');
					   	
					   	//如果该列表存在，更新
					   	if($('#otherStuList').length>0){
					   		$('#otherStuList').datagrid('reload');
					   	}
					    //重新加载就业信息列表
					   	$('#graduateInfoList').datagrid('reload');
					   	closeTab(getCurrentTab());
					} else{
						$.messager.alert("操作提示",result.msg ,"error");
					}
				}
		});   
	}
       
</script>
