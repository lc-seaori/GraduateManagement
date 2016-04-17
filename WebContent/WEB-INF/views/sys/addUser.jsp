<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<br/>
<br/>
<script type="text/javascript">
	$(function(){
		$.extend($.fn.validatebox.defaults.rules, {
	    	/*必须和某个字段相等*/
	   		equalTo: { validator: function (value, param) { return $(param[0]).val() == value; }, message: '两次输入的密码不匹配' }
	    });
	});
</script>
<div class="easyui-panel" style="padding: 30px 70px 50px 70px" align="center">
	<s:form id="addUserForm" namespace="/" method="post" theme="simple" enctype="multipart/form-data">
		<s:hidden name="id"/>
		<table cellpadding="7">
			<tr>
				<td>用户名：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="loginname" id="loginname"  
						data-options="required:true" missingMessage="必须输入项" placeholder="请输入登陆用户名" 
						style="width: 150px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
				
			</tr>
			
			<tr>
				<td>密	码：</td>
				<td>
					<s:password cssClass="easyui-validatebox textbox" name="password" id="password" 
						data-options="required:true" missingMessage="必须输入项" placeholder="请输入密码" 
						style="width: 150px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			
			<tr>
				<td>再输入密码：</td>
				<td>
					<s:password cssClass="easyui-validatebox textbox" name="rePwd" id="rePwd" 
						data-options="required:true" missingMessage="必须输入项" placeholder="请再次输入新密码" 
						style="width: 150px; height: 15px; padding: 5px" validType="equalTo['#password']" invalidMessage="两次输入的密码不匹配"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
			</tr>
			
			<tr>
				<td>姓	名：</td>
				<td>
					<s:textfield cssClass="easyui-validatebox textbox" name="name" id="name"  
						data-options="required:true" missingMessage="必须输入项" placeholder="请输入用户真实名字" 
						style="width: 150px; height: 15px; padding: 5px"/>
					<font color="red" style="padding-left: 5px;">*</font>
				</td>
				
			</tr>
			
			<tr>
				<td>是否默认：</td>
				<td>
					<s:select list="#request.isdefault" listKey="id" listValue="name" headerKey="0" headerValue="--选择--" 
					cssClass="easyui-combobox" editable="false" 
					id="isdefault" name="isdefault" style="width: 80px" 
					data-options="panelWidth:'60px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
			
			<tr>
				<td>用户状态：</td>
				<td>
					<s:select list="#request.status" listKey="id" listValue="name" headerKey="0" headerValue="--选择--" 
					cssClass="easyui-combobox" editable="false" 
					id="state" name="state" style="width: 80px" 
					data-options="panelWidth:'60px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
			
			<tr>
				<td>所属组织部门：</td>
				<td>
					<s:select list="#request.organizations" listKey="id" listValue="name" headerKey="0" headerValue="--选择--" 
					cssClass="easyui-combobox" editable="false" 
					id="organization" name="organization" style="width: 130px"
					data-options="panelWidth:'110px', panelHeight:'auto'"></s:select>
				</td>
			</tr> 
			
			<tr>
				<td>所属角色：</td>
				<td>
					<s:select list="#request.roles" listKey="id" listValue="name" headerKey="0" headerValue=""
					cssClass="easyui-combobox" editable="false" 
					id="role" name="role" style="width: 220px" 
					data-options="panelWidth:'200px', panelHeight:'auto',multiple:true,multiline:true"></s:select>
				</td>
			</tr>
		</table>
	</s:form>
	<br/>
	<br/>
	<div style="text-align: center; padding: 5px;"> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" 
			onclick="toAddUsers('#addUserForm')">添加</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-no" 
			onclick="exitAddUserPage()">关闭</a>
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
		
  		$('#addUserForm').find('#loginname').change(function(){
  			var loginname = $(this).val().trim();
			var url="System_AddUser_validateLoginname";
			var args={"loginname":loginname};
			$.post(url,args,function(data){
				var result=eval('('+data+')');
				if(result.success&&loginname!=''){
					//alert("可以用");
					//表示不可用
					$.messager.show({    // show error message
                        title: 'Tips',
                        msg: '		该用户名可用',
                        timeout:4000,
                        height:150
                    });
				}else if(!result.success){
					//表示不可用
					$.messager.show({    // show error message
                        title: '禁止提示',
                        msg: '		该用户名已存在，请确认后再重新输入',
                        timeout:4000,
                        height:150
                    });
				}
				/* else{
					$.messager.alert("系统提示", "服务器出错","warning");
				} */
			});
  		});
  	});

	function exitAddUserPage() {
		$.messager.confirm('请确认','确认关闭添加页面？',function(r){
			if(r){
				closeTab(getCurrentTab());
			}
		});
	}
	
	//表单提交
	function toAddUsers(form){
    	//在提交数据之前执行表单验证
		$(form).form('enableValidation').form('validate'); 
    	
    	//获取所有下拉控件的值
    	var isdefault=$('#isdefault').combobox('getValue');
    	var state=$('#state').combobox('getValue'); 
    	var organizationName=$('#organization').combobox('getValue'); 
    	//var roleName=$('#role').combobox('getValue'); 
    	//alert(clazzVal+"   "+schoolStatusVal);
    	
    	//判断表单验证是否通过
		if(!$(form).form('validate')){
			$.messager.alert("错误提示", "注意填写完整而且正确的信息！","info");  
			return false;
		}else if(isdefault=="0"||state=="0"||organizationName=="0"){
			$.messager.alert("错误提示", "注意填写完整信息！","info"); 
			return false;
		}
		
		$(form).ajaxSubmit({
		     type: 'post',
		     url: "System_AddUser_doAddUser.action",
		     success: 
		    	function(data){
		    		var result=eval("("+data+")");
		    		//alert(result.success);
		    		if (result.success){
					   	$.messager.alert("操作提示", "添加用户成功","info");   
					   	$('#userList').datagrid('reload');
					   	closeTab(getCurrentTab());
					} else{
						$.messager.alert("操作提示","添加用户失败" ,"error");
					}
				}
		});   
	}
      
</script>
