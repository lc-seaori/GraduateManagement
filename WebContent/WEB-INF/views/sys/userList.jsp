<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="easyui-layout" data-options="fit:true,border:false" style="width: 99%; height: auto">
	<div data-options="region:'center',fit:true,border:false,iconCls:'icon-user'" title="用户列表">
		<div id="userToolbar" style="padding: 5px; height: auto" >	
				<td>登录名:</td>
				<td><input class="easyui-validatebox textbox" id="loginname" 
					name="loginname" 
					style="width: 100px; height: 13px; padding: 5px" />
				</td>
				&nbsp;
				<td>姓	名:</td>
				<td><input class="easyui-validatebox textbox" id="name" 
					name="name" 
					style="width: 100px; height: 13px; padding: 5px" />
				</td>
				<button href="#" class="easyui-linkbutton" plain="true" 
					onclick="getUsersByLimit('#userToolbar', '#userList')" 
					iconCls="icon-search">查询</button>
		</div>
	
		<table id="userList" class="easyui-datagrid" style="width: 920px;"
			pagination="true" url="User_getUserList.action" toolbar="#userOperToolbar" 
			rownumbers="true" data-options="pageSize:10,loadMsg:'数据加载中,请稍后...',idField :'id',sortName:'loginname',sortOrder:'desc'" >
			<thead>
				<tr>
					<th field="id" width="100" align="center" checkbox="true">id</th>
					<th field="loginname" width="120" align="center" sortable="true">登陆名</th>
					<th field="name" width="80" align="center">姓名</th>
					<th field="organizationId" width="100" align="center" hidden="true"></th>
					<th field="organizationName" width="200" align="center">所属组织部门</th>
					<th field="isDefaultPage" width="80" align="center">是否默认</th>
					<th field="statePage" width="80" align="center">状态</th>
					<th field="createTimePage" width="150" align="center">创建时间</th>
					<th field="modifyTimePage" width="150" align="center">最后更新时间</th>
				</tr>
			</thead>
		</table>
	</div>
    <div id="userOperToolbar">
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toAddUser()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toEditUser()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="toDeleteUser()">删除</a>
    </div>
    <div data-options="region:'west',border:false,split:true,iconCls:'icon-org'" title="组织部门列表" style="width:200px;overflow: hidden; ">
		<table id="organizationTree" style="width:180px;margin: 10px 10px 10px 10px"></table>
	</div>
</div>
<script type="text/javascript">
	
	var organizationTree;
	//避免分页查询出现错误
	$(function(){
		organizationTree = $('#organizationTree').tree({
			url : 'Organization_getTree.action',
			lines : true,
			animate: true,
			onClick : function(node) {
				/* alert(node.id);*/
				//$(this).tree('collapseAll');
				$('#userList').datagrid('load', {
				    organizationId: node.id
				});
			}
		}); 
	});
	
	function getUsersByLimit(toolbar,datagrid){
		//解决浏览器不兼容tirm()方法，如ie8以下不兼容trim()，该方法去掉前后空格
		if(!String.prototype.trim) {
			String.prototype.trim = function () {
		    	return this.replace(/^\s+|\s+$/g,'');
		  	};
		}
		var loginname=$("#userToolbar").find("#loginname").val().trim();
		var name=$("#userToolbar").find("#name").val().trim();
		//alert(graduateTime);
		$('#userList').datagrid({
			queryParams: {
				loginname: loginname,
				name: name
			}
		});
		
		//再次发送请求
		$.post('User_getUserList.action',{
			loginname: loginname,
			name: name
		},function(data){
			if (data.total){
				$(datagrid).datagrid('loadData', data); 
            }else{
           		$(datagrid).datagrid('loadData', data); 
           	 	$.messager.show({    // show error message
                    title: '提示',
                    msg: '没有任何记录'
                });
            }
			
		},"json")
	}
	
	//添加
	function toAddUser(){
		openTab("System_AddUser_toAddUserPage.action",'添加用户');
	}
	
	//编辑
	function toEditUser(){
		var rows = $('#userList').datagrid('getSelections');
   		if (rows.length == 1) {
   			openTab("System_EditUser_toEditUserPage.action?userId="+rows[0].id,'编辑用户信息');
   		} else if (rows.length > 1) {
   			 $.messager.alert('提示', '一次只能编辑一条信息！', 'error');
   		} else {
   			 $.messager.alert('提示', '请勾选要编辑的用户信息！', 'error');
   		}
	}
	
	//删除
	function toDeleteUser(){
		var rows = $('#userList').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示', '请勾选要删除信息！', 'info');
   			return;
		}else{
			var length=rows.length;
			$.messager.confirm('请确认','您确定删除共'+length+'条信息吗?',function(r){
                   if (r){
                   	var params="";
                   	for(i=0;i<rows.length;i++){
                   		params=params+rows[i].id;
                   		if(!(i==rows.length-1)){
                   			params=params+",";
                   		}
                   	}
   					var url="User_toDeleteUser.action";
   					//alert(url);
                   	$.post(url, {idList:params},
                   	function(data){
                    	if (data.success){
                      		$('#userList').datagrid('uncheckAll');
                            $.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '删除记录成功'
                            });
                            $('#userList').datagrid('reload');    // reload the user data
                        }else {
                        	$.messager.show({    // show error message
                            	title: 'Error',
                           		msg: '删除记录失败'
                           	});
                        }
                    },'json'); 
                 }
            });
		}
	} 
	
</script>
