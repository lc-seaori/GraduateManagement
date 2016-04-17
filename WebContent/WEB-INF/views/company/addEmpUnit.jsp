<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<br/>
<br/>
<div class="easyui-panel" style="padding: 30px 70px 50px 70px" align="center">
	<s:form id="addEmpUnitForm" namespace="/" method="post" theme="simple" enctype="multipart/form-data">
		<table cellpadding="7">
			<tr>
				<td>招聘单位编号：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="unitCode" id="unitCode"  
						data-options="required:true" missingMessage="必须填写单位编号" 
						style="width: 150px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
				
			</tr>
			
			<tr>
				<td>招聘单位名称：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="unitName" id="unitName" 
						data-options="required:true" missingMessage="必须填写单位名称" 
						style="width: 150px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			
			<tr>
				<td>招聘方式：</td>
				<td>
					<s:select list="#request.recruitmentTypes" listKey="id" listValue="name" headerKey="0" headerValue="--选择--" 
					cssClass="easyui-combobox" editable="false" 
					id="recruitmentType" name="recruitmentType" style="width: 120px"
					data-options="panelWidth:'100px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
			
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
				<td>单位联系人：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="unitContantPerson" id="unitContantPerson" 
						style="width: 150px; height: 15px; padding: 5px"/>
				</td>
			</tr>
			<!-- 
			<tr>
				<td>单位联系人：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="unitContantPerson" id="unitContantPerson" 
						data-options="required:true" missingMessage="必须填写单位联系人" 
						style="width: 150px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			 -->
			
			<tr>
				<td>单位联系方式：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="unitContantPhone" id="unitContantPhone" 
						style="width: 150px; height: 15px; padding: 5px"/>
				</td>
			</tr>
			<!-- 
			<tr>
				<td>单位联系方式：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="unitContantPhone" id="unitContantPhone" 
						data-options="required:true" missingMessage="必须填写单位联系人方式" 
						style="width: 150px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			 -->
			
			<tr>
				<td>单位详细地址：</td>
				<td><s:textarea cssClass="easyui-textbox" id="unitAddress" 
					name="unitAddress" data-options="multiline:true" 
					style="width: 400px; height: 50px; padding: 5px"></s:textarea>
				</td>
			</tr>
			<!-- 
			<tr>
				<td>单位详细地址：</td>
				<td><s:textarea cssClass="easyui-textbox" id="unitAddress" 
					name="unitAddress" 
					data-options="required:true,multiline:true" missingMessage="必须填写单位详细地址" 
					style="width: 400px; height: 50px; padding: 5px"></s:textarea>
				</td>
			</tr>
			 -->
			
			<tr>
				<td>单位描述：</td>
				<td><s:textarea cssClass="easyui-textbox" id="unitDescription" 
					name="unitDescription" data-options="multiline:true" 
					style="width: 400px; height: 200px; padding: 5px"></s:textarea>
				</td>
			</tr>
			<!-- 
			<tr>
				<td>单位描述：</td>
				<td><s:textarea cssClass="easyui-textbox" id="unitDescription" 
					name="unitDescription" 
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
			onclick="addEmpUnit('#addEmpUnitForm')">添加</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-no" 
			onclick="exitAddEmpUnitPage()">关闭</a>
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
		
  		$('#addEmpUnitForm').find('#unitCode').change(function(){
  			var unitCode = $(this).val().trim();
			var url="EmployingUnit_Add_validateUnitCode";
			var args={"valiUnitCode":unitCode};
			$.post(url,args,function(data){
				if(data=="1"){
					//alert("可以用");
				}else if(data=="0"){
					//表示不可用
					$.messager.show({    // show error message
                        title: '禁止提示',
                        msg: '		该单位编号已存在，请确认后再重新输入',
                        timeout:4000,
                        height:150
                    });
				}else{
					$.messager.alert("系统提示", "服务器出错","warning");
				}
			});
  		});
  	});

	function exitAddEmpUnitPage() {
		closeTab(getCurrentTab());
	}
	
	//表单提交
	function addEmpUnit(form){
    	//在提交数据之前执行表单验证
		$('#addEmpUnitForm').form('enableValidation').form('validate'); 
    	
    	//获取所有下拉控件的值
    	var unitTypeVal=$('#unitType').combobox('getValue');
    	var recruitmentTypeVal=$('#recruitmentType').combobox('getValue');
    	//alert(clazzVal+"   "+schoolStatusVal);
    	
    	//判断表单验证是否通过
		if(!$(form).form('validate')){
			$.messager.alert("错误提示", "注意填写完整信息！","info");  
			return false;
		}else if(unitTypeVal=="0"||recruitmentTypeVal=="0"){
			$.messager.alert("错误提示", "注意填写完整信息！","info"); 
			return false;
		}
		
		$(form).ajaxSubmit({
		     type: 'post',
		     url: "EmployingUnit_Add_addEmpUnit.action" ,
		     success: 
		    	function(data){
		    		var result=eval("("+data+")");
		    		if (result.success){
					   	$.messager.alert("操作提示", "添加成功",'info');   
					   	$('#empUnitList').datagrid('reload');
					   	closeTab(getCurrentTab());
					}else{
						$.messager.alert("操作提示",result.msg,'error');
					}
				}
			});   
	}
      
</script>
