<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript">
	var treeGrid;
	$(function() {
		treeGrid = $('#organizationList').treegrid({
			url : 'Organization_getOrganizationList.action',
			idField : 'id',
			treeField : 'name',
			parentField : 'pid',
			nowrap : false,
			fit : true,
			fitColumns : false,
			border : false,
			animate : true,
			frozenColumns : [ [ {
				title : '编号',
				field : 'id',
				width : 40,
				align : 'center',
				hidden : true
			} ] ],
			columns : [ [ {
				field : 'name',
				title : '组织部门名称',
				width : 250
				//,align : 'center'
			}, {
				field : 'icon',
				title : '图标',
				width : 120,
				align : 'center'
			}, {
				field : 'pid',
				title : '上级资源ID',
				width : 150,
				hidden : true
			}, {
				field : 'description',
				title : '资源描述',
				width : 300,
				align : 'center'
			}] ],
			toolbar : '#organizationOperToolbar',
			onExpand : function(row) {
				treeGrid.treegrid('unselectAll');
			},
			onCollapse : function(row) {
				treeGrid.treegrid('unselectAll');
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false" style="width: 99%; height: auto">
	<div data-options="region:'center',fit:true,border:false,iconCls:'icon-org'" title="组织部门列表">
		<table id="organizationList"></table>
	</div>
    <div id="organizationOperToolbar">
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toAddOrganization()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toEditOrganization()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="toDeleteOrganization()">删除</a>
    </div>
</div>
<div id="addOrganizationDialog" data-options="iconCls:'icon-add'" style="width:600px;height:300px;padding:10px"></div>
<div id="editOrganizationDialog" data-options="iconCls:'icon-edit'" style="width:600px;height:300px;padding:10px"></div>
<script type="text/javascript">
	
	//添加
	function toAddOrganization(){
		//openTab("System_Auth_toAddAuthPage.action",'添加权限');
		$('#addOrganizationDialog').dialog({
		    title: '	添加组织部门',
		    width: 600,
		    height: 400,
		    cache: false,
		    href: 'Organization_addOrganizationPage.action',
		    collapsible: true,
		    maximizable: true,
		    resizable: true,
		    modal: true,
		    closed: true
		});
		$('#addOrganizationDialog').dialog('open');
	}
	
	//编辑
	function toEditOrganization(){
		//得到选中行
		var row = $('#organizationList').treegrid('getSelected');
   		if (row) {
   			//openTab("System_Auth_toEditAuthPage.action?authId="+row.id,'编辑权限信息');
   			var orgId=row.id;
   			$('#editOrganizationDialog').dialog({
   			    title: '	编辑组织部门',
   			    width: 600,
   			    height: 400,
   			    cache: false,
   			    href: 'Organization_toEditOrganizationPage.action?orgId='+orgId,
   			    collapsible: true,
   			    maximizable: true,
   			    resizable: true,
   			    modal: true,
   			    closed: true
   			});
   			$('#editOrganizationDialog').dialog('open');
   		}else {
   			 $.messager.alert('提示', '请勾选要编辑的组织部门信息！', 'error');
   		}
	}
	
	//删除
	function toDeleteOrganization(){
		//得到选中行
		var row = $('#organizationList').treegrid('getSelected');
   		if (row) {
   			$.messager.confirm('请确认','删除组织同时会删除该组织下的所有子组织部门，您确定删除共该组织部门吗?',function(r){
   				if(r){
   					var url="Organization_delOrganization.action";
   					var orgId=row.id;
   					$.post(url, {orgId:orgId},
                   	function(data){
                    	if (data.success){
                      		$('#organizationList').treegrid('uncheckAll');
                            $.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '删除组织部门成功'
                            });
                            $('#organizationList').treegrid('reload');    // reload the user data
                        }else {
                        	$.messager.show({    // show error message
                            	title: 'Error',
                           		msg: '删除组织部门失败'
                           	});
                        }
                    },'json'); 
   				}
   			});
   		}else {
   			 $.messager.alert('提示', '请勾选要删除的组织部门信息！', 'error');
   		}
	} 
	
</script>
