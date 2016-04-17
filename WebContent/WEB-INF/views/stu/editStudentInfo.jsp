<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="easyui-panel" style="padding: 30px 70px 50px 70px">
	<s:form id="editStudentInfoForm" action="StudentInfo_EditStuInfo_editStudentInfo.action" namespace="/" method="post" theme="simple" enctype="multipart/form-data">
		<s:hidden id="id" name="id"/>
		<s:hidden name="departmentAduitStatus"></s:hidden>
		<s:hidden name="schoolAduitStatus"></s:hidden>
		<table cellpadding="7">
			<tr>
				<td>准考证号：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="examNum" name="examNum" 
						data-options="required:true" missingMessage="必须填写考生准考证号" 
						style="width: 200px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			
			<tr>
				<td>学生姓名：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="name" name="name" 
						data-options="required:true" missingMessage="必须填写学生姓名" 
						style="width: 200px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			
			<tr>
				<td>毕业学校：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="graduationSchool" name="graduationSchool" 
						style="width: 200px; height: 15px; padding: 5px"/>
				</td>
			</tr>
			<!-- 
			<tr>
				<td>毕业学校：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="graduationSchool" name="graduationSchool" 
						data-options="required:true" missingMessage="必须填写学生毕业学校" 
						style="width: 200px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			 -->
			
			<tr>
				<td>性	别：</td>
				<td>
					<s:select list="sex" listKey="id" listValue="name" headerKey="0" headerValue="--选择--" 
					cssClass="easyui-combobox" editable="false" 
					id="sex" name="sexType" style="width: 80px"
					data-options="panelWidth:'60px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
			
			<tr>
				<td>学	院：</td>
				<td>
					<s:select list="#request.departments" listKey="id" listValue="name" headerKey="---请选择学院---" headerValue="---请选择学院---"
					cssClass="easyui-combobox" editable="false" 
					id="departmentSelected" name="majorField.department.id" style="width: 150px"
					data-options="panelWidth:'180px', panelHeight:'auto'"></s:select>
					<span>&nbsp;专		业：</span>
					<s:select list="#request.majors" listKey="id" listValue="name" headerKey="---请选择专业---" headerValue="---请选择专业---" 
					cssClass="easyui-combobox" editable="false" 
					id="majorFieldSelected" name="majorField.id" style="width: 150px"
					data-options="panelWidth:'130px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
			
			<tr>
				<td>出生年月：</td>
				<td>
					<input class="easyui-datebox" id="birthdays" name="birthdays" 
					data-options="required:true" style="width:200px; height: 25px; padding: 5px" 
					editable="false" value="<s:property value='#request.rebirthday'/>"></input>
				</td>
			</tr>
			
			<tr>
				<td>身份证号：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="idCard" name="idCard"  
						data-options="required:true,validType:'minLength[18]'" missingMessage="必须填写学生身份证" 
						style="width: 200px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px">*</font>
				</td>
			</tr>
			
			<tr>
				<td>民	族：</td>
				<td>
					<s:select list="nation" listKey="id" listValue="name" headerKey="0" headerValue="--选择--" 
					cssClass="easyui-combobox" editable="false" 
					id="nation" name="nationType" style="width: 80px"
					data-options="panelWidth:'60px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
			
			<tr>
				<td>政治面貌：</td>
				<td>
					<s:select list="politicalFeature" listKey="id" listValue="name" headerKey="0" headerValue="--选择--" 
					cssClass="easyui-combobox" editable="false" 
					id="politicalFeature" name="politicalFeatureType" style="width: 100px"
					data-options="panelWidth:'80px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
			
			<tr>
				<td>籍	贯：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="nativePlace" name="nativePlace"  
						style="width: 200px; height: 15px; padding: 5px"/>
				</td>
			</tr>
			<!-- 
			<tr>
				<td>籍	贯：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="nativePlace" name="nativePlace"  
						data-options="required:true" missingMessage="必须填写学生籍贯" 
						style="width: 200px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			 -->
			
			<tr>
				<td>户籍类型：</td>
				<td>
					<s:select list="household" listKey="id" listValue="name" headerKey="0" headerValue="--选择--" 
					cssClass="easyui-combobox" editable="false" 
					id="household" name="householdType" style="width: 100px"
					data-options="panelWidth:'80px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
			
			<tr>
				<td>学	制：</td>
				<td>
					<s:select list="schoolLength" listKey="id" listValue="name" headerKey="0" headerValue="--选择--" 
					cssClass="easyui-combobox" editable="false" 
					id="schoolLength" name="schoolLengthType" style="width: 100px"
					data-options="panelWidth:'80px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
			
			<tr>
				<td>学	历：</td>
				<td>
					<s:select list="education" listKey="id" listValue="name" headerKey="0" headerValue="--选择--" 
					cssClass="easyui-combobox" editable="false" 
					id="education" name="educationType" style="width: 100px"
					data-options="panelWidth:'80px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
			
			<tr>
				<td>联系方式：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="phone" name="phone" 
						data-options="validType:'Mobile'" 
						style="width: 200px; height: 15px; padding: 5px"/>
				</td>
			</tr>
			<!-- 
			<tr>
				<td>联系方式：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="phone" name="phone" 
						data-options="required:true,validType:'Mobile'" missingMessage="必须填写学生联系方式"
						style="width: 200px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px">*</font>
				</td>
			</tr>
			 -->
			
			<tr>
				<td>入学年份：</td>
				<td>
					<input class="easyui-datebox" id="enterCollegeTimes" name="enterCollegeTimes" 
					 style="width:200px; height: 25px; padding: 5px" 
					editable="false" value="<s:property value='#request.reenterCollegeTime'/>"></input>
				</td>
			</tr>
			
			<tr>
				<td>毕业日期：</td>
				<td>
					<input class="easyui-datebox" id="graduateTimes" name="graduateTimes" 
					 style="width:200px; height: 25px; padding: 5px" 
					editable="false" value="<s:property value='#request.regraduateTime'/>"></input>
				</td>
			</tr>
			
			<tr>
				<td>邮政编码：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="postEncoding" name="postEncoding" 
						data-options="validType:'ZipCode'" 
						style="width: 200px; height: 15px; padding: 5px"/>
				</td>
			</tr>
			<!-- 
			<tr>
				<td>邮政编码：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" id="postEncoding" name="postEncoding" 
						data-options="required:true,validType:'ZipCode'" 
						style="width: 200px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px">*</font>
				</td>
			</tr>
			 -->
			
			<tr>
				<td>户口所在地:</td>
				<td><s:textarea cssClass="easyui-textbox" type="text" id="householdPlace" 
					name="householdPlace" data-options="multiline:true" 
					style="width: 400px; height: 50px; padding: 5px"></s:textarea>
				</td>
			</tr>
		</table>
	</s:form>
	
	<div style="text-align: center; padding: 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" 
			onclick="editStudentInfo('#editStudentInfoForm')">更新</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-no" 
			onclick="exitEditPage()">关闭</a>
	</div>
</div>
<script type="text/javascript">
	
	//获取专业
	$(function(){
		var tcSelect = $('#departmentSelected');
		tcSelect.combobox({
			onSelect : function() {
				var departmentId = tcSelect.combobox('getValue');
				var name=tcSelect.combobox('getText');
				if(departmentId!="---请选择学院---"&&departmentId!=null){
					$('#majorFieldSelected').combobox({
						valueField : "id",
						textField : "name",
						url : "StudentInfo_AddNewInfo_toGetMajorField.action?departmentId=" + departmentId,
						editable : false,
						value : "---请选择专业---"
					});		
				}else{
					//清空combobox
					$('#majorFieldSelected').combobox('loadData', {});
					$('#majorFieldSelected').combobox('setValue', '---请选择专业---');
				}
			}
		});
	});
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
	function exitEditPage() {
		$.messager.confirm('请确认','您确定放弃编辑，离开页面  ？',function(r){
            if (r){
            	closeTab(getCurrentTab());
            }
		});
	}
	
	//表单提交
	function editStudentInfo(form){
    	//在提交数据之前执行表单验证
		$(form).form('enableValidation').form('validate'); 
    	
    	//获取所有下拉控件的值
    	var sexVal=$('#editStudentInfoForm').find('#sex').combobox('getValue');
    	//var departmentSelectedVal=$('#departmentSelected').combobox('getValue');
    	var majorFieldSelectedVal=$('#majorFieldSelected').combobox('getValue');
    	var nationVal=$('#nation').combobox('getValue');
    	var politicalFeatureVal=$('#politicalFeature').combobox('getValue');
    	var householdVal=$('#household').combobox('getValue');
    	var schoolLengthVal=$('#schoolLength').combobox('getValue');
    	var educationVal=$('#education').combobox('getValue');
    	//alert(sexVal+"    "+departmentSelectedVal+"    "+majorFieldSelectedVal+"   "+nationVal);
    	
    	//判断表单验证是否通过
		if(!$(form).form('validate')){
			$.messager.alert("错误提示", "注意填写完整信息！","info");  
			return false;
		}else if(majorFieldSelectedVal=="---请选择专业---"){
			$.messager.alert("错误提示", "注意选择专业项","info"); 
			return false;
		}else if(sexVal=="0"||nationVal=="0"||politicalFeatureVal=="0"||householdVal=="0"||schoolLengthVal=="0"||educationVal=="0"){
			$.messager.alert("错误提示", "注意填写完整信息！","info"); 
			return false;
		}
		//alert(majorFieldSelectedVal);
		
		$(form).ajaxSubmit({
                  type: 'post',
                  url: "StudentInfo_EditStuInfo_editStudentInfo.action?majorFieldId="+ majorFieldSelectedVal,
                  success: 
                 	function(data){
                 		var result=eval("("+data+")");
                 		//alert(result.success);
                 		if (result.success){
                 			$.messager.show({    // show error message
                              title: 'Tips',
                              msg: '更新信息成功'
                          	});
                 			$('#studentInfoList').datagrid('reload'); 
                 			$('#departmentAduitList').datagrid('reload'); 
                 			$('#schoolAduitList').datagrid('reload'); 
 		        		} else{
 		        			$.messager.alert("操作提示", "更新信息失败！","warning");
 		        		}
                 		closeTab(getCurrentTab());
                	}
            	});    
	}
      
</script>
	