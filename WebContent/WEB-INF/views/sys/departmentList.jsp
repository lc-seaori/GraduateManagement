<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="easyui-layout" data-options="fit:true,border:false" style="width: 99%; height: auto">
	<div data-options="region:'center',fit:true,border:false,iconCls:'icon-dic'" title="学院列表">
		<div id="departmentToolbar" style="padding: 5px; height: auto">	
				<td>学院名称:</td>
				<td><input class="easyui-validatebox textbox" id="name"
					name="name" 
					style="width: 100px; height: 13px; padding: 5px" />
				</td> 
				&nbsp;
				<button href="#" class="easyui-linkbutton" plain="true" 
					onclick="getDepartmentsByLimit('#departmentToolbar', '#departmentList')" 
					iconCls="icon-search">查询</button>
		</div>
		<table id="departmentList" class="easyui-datagrid" style="width: auto;"
			pagination="true" url="System_Department_getDepList.action" toolbar="#departmentOperToolbar" 
			rownumbers="true" data-options="pageSize:10,loadMsg:'数据加载中,请稍后...',idField :'id',sortName:'name',sortOrder:'desc'" >
			<thead>
				<tr>
					<th field="id" width="100" align="center" checkbox="true">id</th>
					<th field="name" width="120" align="center" sortable="true">学院名字</th>
					<th field="remark" width="400" align="center">学院描述</th>
				</tr>
			</thead>
		</table>
	</div>
	
    <div id="departmentOperToolbar">
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toAddDepartment()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toEditDepartment()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="toDeleteDepartment()">删除</a>
    </div>
</div>
<div id="addDepDialog" data-options="iconCls:'icon-add'" style="width:600px;height:300px;padding:10px" title="	添加学院信息"></div>
<div id="editDepDialog" data-options="iconCls:'icon-edit'" style="width:600px;height:300px;padding:10px" title="	编辑学院信息"></div>
<script type="text/javascript">

	/* $(function(){
	}); */
	
	function getDepartmentsByLimit(toolbar,datagrid){
		//解决浏览器不兼容tirm()方法，如ie8以下不兼容trim()，该方法去掉前后空格
		if(!String.prototype.trim) {
			String.prototype.trim = function(){
		    	return this.replace(/^\s+|\s+$/g,'');
		  	};
		}
		var name=$(toolbar).find("#name").val().trim();
		$('#departmentList').datagrid({
			queryParams: {
				name: name
			}
		});
		
		//再次发送请求
		$.post('System_Department_getDepList',{
			name:  name
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
	function toAddDepartment(){
		$('#addDepDialog').dialog({
		    width: 600,
		    height: 400,
		    cache: false,
		    href: 'System_Department_addDepPage.action',
		    collapsible: true,
		    maximizable: true,
		    resizable: true,
		    modal: true,
		    closed: true
		});
		$('#addDepDialog').dialog('open');
	}
	
	//编辑
	function toEditDepartment(){
		var rows = $('#departmentList').datagrid('getSelections');
   		if (rows.length == 1) {
   			$('#editDepDialog').dialog({
   			    width: 600,
   			    height: 400,
   			    cache: false,
   			    href: 'System_Department_editDepPage.action?depId='+rows[0].id,
   			    collapsible: true,
   			    maximizable: true,
   			    resizable: true,
   			    modal: true,
   			    closed: true
   			});
   			$('#editDepDialog').dialog('open');
   		} else if (rows.length > 1) {
   			 $.messager.alert('提示', '一次只能编辑一条信息！', 'error');
   		} else {
   			 $.messager.alert('提示', '请勾选要编辑的学院信息！', 'error');
   		}
	}
	
	//删除
	function toDeleteDepartment(){
		var rows = $('#departmentList').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示', '请勾选要删除信息！', 'info');
   			return;
		}else{
			var length=rows.length;
			$.messager.confirm('请确认','您确定删除共'+length+'条学院信息吗?',function(r){
                   if (r){
                   	var params="";
                   	for(i=0;i<rows.length;i++){
                   		params=params+rows[i].id;
                   		if(!(i==rows.length-1)){
                   			params=params+",";
                   		}
                   	}
   					var url="System_Department_toDeleteDepartment.action";
                   	$.post(url, {idList:params},
                   	function(data){
                    	if (data.success){
                      		$('#departmentList').datagrid('uncheckAll');
                            $.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '删除记录成功'
                            });
                            $('#departmentList').datagrid('reload');    // reload the user data
                        }else {
                        	$.messager.show({    // show error message
                            	title: 'Error',
                           		msg: '删除学院失败，请先删除该学院的所有专业信息，再执行该操作...'
                           	});
                        }
                    },'json'); 
                 }
            });
		}
	} 
	
</script>
