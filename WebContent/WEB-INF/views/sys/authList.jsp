<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript">
	var treeGrid;
	$(function() {
		treeGrid = $('#authList').treegrid({
			url : 'System_Auth_getAuthList.action',
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
				title : '资源名称',
				width : 250
				//,align : 'center'
			}, {
				field : 'url',
				title : '资源路径',
				width : 450,
				align : 'center'
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
				field : 'createTimePage',
				title : '创建时间',
				width : 120,
				align : 'center'
			}, {
				field : 'description',
				title : '资源描述',
				width : 300,
				align : 'center'
			}] ],
			toolbar : '#authOperToolbar',
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
	<div data-options="region:'center',fit:true,border:false,iconCls:'icon-resource'" title="权限列表">
		<table id="authList"></table>
	</div>
    <div id="authOperToolbar">
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toAddAuth()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toEditAuth()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="toDeleteAuth()">删除</a>
    </div>
</div>
<script type="text/javascript">
	
	//添加
	function toAddAuth(){
		openTab("System_Auth_toAddAuthPage.action",'添加权限');
	}
	
	//编辑
	function toEditAuth(){
		//得到选中行
		var row = $('#authList').treegrid('getSelected');
   		if (row) {
   			openTab("System_Auth_toEditAuthPage.action?authId="+row.id,'编辑权限信息');
   		}else {
   			 $.messager.alert('提示', '请勾选要编辑的权限信息！', 'error');
   		}
	}
	
	//删除
	function toDeleteAuth(){
		//得到选中行
		var row = $('#authList').treegrid('getSelected');
   		if (row) {
   			$.messager.confirm('请确认','删除权限同时会删除该权限下的所有子权限，您确定删除共该权限吗?',function(r){
   				if(r){
   					var url="System_Auth_delAuth.action";
   					var authId=row.id;
   					$.post(url, {authId:authId},
                   	function(data){
                    	if (data.success){
                      		$('#authList').treegrid('uncheckAll');
                            $.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '删除权限成功'
                            });
                            $('#authList').treegrid('reload');    // reload the user data
                        }else {
                        	$.messager.show({    // show error message
                            	title: 'Error',
                           		msg: '删除权限失败'
                           	});
                        }
                    },'json'); 
   				}
   			});
   		}else {
   			 $.messager.alert('提示', '请勾选要删除的权限信息！', 'error');
   		}
	} 
	
</script>
