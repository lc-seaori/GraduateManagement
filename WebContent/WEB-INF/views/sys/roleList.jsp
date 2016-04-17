<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="easyui-layout" data-options="fit:true,border:false" style="width: 99%; height: auto">
	<div data-options="region:'center',fit:true,border:false,iconCls:'icon-role'" title="角色列表">
		<div id="roleToolbar" style="padding: 5px; height: auto">	
				<td>角色名称</td>
				<td><input class="easyui-validatebox textbox" id="name"
					name="name" 
					style="width: 100px; height: 13px; padding: 5px" />
				</td>
				<button href="#" class="easyui-linkbutton" plain="true" 
					onclick="getRolesByLimit('#roleToolbar', '#roleList')" 
					iconCls="icon-search">查询</button>
		</div>
		<table id="roleList" class="easyui-datagrid" style="width: auto;"
			pagination="true" url="System_Role_getRoleList.action" toolbar="#roleOperToolbar" 
			rownumbers="true" data-options="pageSize:10,loadMsg:'数据加载中,请稍后...',idField :'id',sortName:'name',sortOrder:'desc'" >
			<thead>
				<tr>
					<th field="id" width="100" align="center" checkbox="true">id</th>
					<th field="name" width="200" align="center" sortable="true">角色名称</th>
					<th field="isdefaultPage" width="80" align="center">是否默认</th>
					<th field="description" width="350" align="center">角色描述</th>
				</tr>
			</thead>
		</table>
	</div>
		
    <div id="roleOperToolbar">
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-lock" plain="true" onclick="toGrantAuth()">授权</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toAddRole()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toEditRole()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="toDeleteRole()">删除</a>
    </div>
    <div id="addRoleDialog" data-options="iconCls:'icon-add'" style="width:600px;height:300px;padding:10px"></div>
	<div id="editRoleDialog" data-options="iconCls:'icon-edit'" style="width:600px;height:300px;padding:10px"></div>
	<div id="grantAuthDialog" data-options="iconCls:'icon-edit'" style="width:600px;height:500px;padding:10px"></div>
	
</div>
<script type="text/javascript">
	
	function getRolesByLimit(toolbar,datagrid){
		//解决浏览器不兼容tirm()方法，如ie8以下不兼容trim()，该方法去掉前后空格
		if(!String.prototype.trim) {
			String.prototype.trim = function () {
		    	return this.replace(/^\s+|\s+$/g,'');
		  	};
		}
		var name=$(toolbar).find("#name").val().trim();
		$('#roleList').datagrid({
			queryParams: {
				name: name
			}
		});
		//再次发送请求
		$.post('System_Role_getRoleList.action',{
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
	
	//用户授权
	function toGrantAuth(){
		var rows = $('#roleList').datagrid('getSelections');
   		if (rows.length == 1) {
   			var roleId=rows[0].id;
   			$('#grantAuthDialog').dialog({
   			    title: '	角色授权',
   			    width: 600,
   			    height: 500,
   			    cache: false,
   			    href: 'System_Role_toGrentAuthToRolePage.action?roleId='+roleId,
   			    collapsible: true,
   			    maximizable: true,
   			    resizable: true,
   			    modal: true,
   			    closed: true,
   			 	buttons : [{
 					text : '授	权',
 					handler : function() {
 						toAddRoles('#roleGrantForm');
 					}
 				}]
   			});
   			$('#grantAuthDialog').dialog('open');
   		} else if (rows.length > 1) {
   			 $.messager.alert('提示', '每次只能给一个角色授权', 'error');
   		} else {
   			 $.messager.alert('提示', '请勾选需要授权的角色信息！', 'error');
   		}
	}
	
	//添加
	function toAddRole(){
		$('#addRoleDialog').dialog({
		    title: '	添加角色',
		    width: 600,
		    height: 400,
		    cache: false,
		    href: 'System_Role_toAddRolePage.action',
		    collapsible: true,
		    maximizable: true,
		    resizable: true,
		    modal: true,
		    closed: true
		});
		$('#addRoleDialog').dialog('open');
	}

	//编辑
	function toEditRole(){
		var rows = $('#roleList').datagrid('getSelections');
   		if (rows.length == 1) {
   			var roleId=rows[0].id;
   			$('#editRoleDialog').dialog({
   			    title: '	编辑角色',
   			    width: 600,
   			    height: 400,
   			    cache: false,
   			    href: 'System_Role_toEditRolePage.action?roleId='+roleId,
   			    collapsible: true,
   			    maximizable: true,
   			    resizable: true,
   			    modal: true,
   			    closed: true
   			});
   			$('#editRoleDialog').dialog('open');
   		} else if (rows.length > 1) {
   			 $.messager.alert('提示', '一次只能编辑一条信息！', 'error');
   		} else {
   			 $.messager.alert('提示', '请勾选要编辑的角色信息！', 'error');
   		}
	}
	
	//删除
	function toDeleteRole(){
		var rows = $('#roleList').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示', '请勾选要删除的信息！', 'info');
   			return;
		}else{
			var length=rows.length;
			$.messager.confirm('请确认','您确定删除共'+length+'条角色信息吗?',function(r){
                   if (r){
                   	var params="";
                   	for(i=0;i<rows.length;i++){
                   		params=params+rows[i].id;
                   		if(!(i==rows.length-1)){
                   			params=params+",";
                   		}
                   	}
   					var url="System_Role_doDeleteRole.action";
                   	$.post(url, {idList:params},
                   	function(data){
                    	if (data.success){
                      		$('#roleList').datagrid('uncheckAll');
                            $.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '删除记录成功'
                            });
                            $('#roleList').datagrid('reload');    // reload the user data
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
