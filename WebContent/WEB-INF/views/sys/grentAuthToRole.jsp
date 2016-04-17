<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">

	var resourceTree;
	$(function() {
		$.messager.progress({
			title:'操作信息',
			text: '正在加载，等稍等...'
		});
		resourceTree = $('#resourceTree').tree({
			url : 'System_Auth_doNotNeedSession_tree.action',
			parentField : 'pid',
			lines : true,
			checkbox : true,
			onClick : function(node) {
			},
			onLoadSuccess : function(node, data) {
				//progressLoad();
				$.post( 'System_Auth_getAuthsRoleId.action', {
					roleId : '${role.id}'
				}, function(result) {
					var ids;
					if (result.msg!= undefined&&result.msg!="") {
						ids = (result.msg).split(",");
					}
					if(ids!=undefined){
						if (ids.length > 0) {
							for ( var i = 0; i < ids.length; i++) {
								if (resourceTree.tree('find', ids[i])) {
									resourceTree.tree('check', resourceTree.tree('find', ids[i]).target);
								}
							}
						}
					}
					$.messager.progress('close');
				}, 'json');
			},
			cascadeCheck : false
		});
	});
	
	//关闭
	function exitGrentAuthsPage() {
		$.messager.confirm('请确认','确认关闭授权页面？',function(r){
			if(r){
				$('#grantAuthDialog').dialog('close');
			}
		});
	}
	
	//表单提交
	function toAddRoles(form){
		var checknodes = resourceTree.tree('getChecked');
		var ids = [];
		if (checknodes && checknodes.length > 0) {
			for ( var i = 0; i < checknodes.length; i++) {
				ids.push(checknodes[i].id);
			}
		}
		$('#resourceIds').val(ids);
		//发送异步请求
		$(form).ajaxSubmit({
		     type: 'post',
		     url: "System_Role_doGrentAuthToRole.action",
		     success: 
		    	function(data){
		    		var result=eval("("+data+")");
		    		if (result.success){
		    			//关闭添加页面
		    			$('#grantAuthDialog').dialog('close');
					   	$.messager.show({
			                title:'操作提示',
			                msg:'授权成功',
			                showType:'show'
			            });
					   	//重新加载
					   	$('#roleList').datagrid('reload');
					} else{
						$.messager.alert("操作提示","授权失败" ,"error");
					}
				}
		});    
	}
	
	function checkAll() {
		var nodes = resourceTree.tree('getChecked', 'unchecked');
		if (nodes && nodes.length > 0) {
			for ( var i = 0; i < nodes.length; i++) {
				resourceTree.tree('check', nodes[i].target);
			}
		}
	}
	function uncheckAll() {
		var nodes = resourceTree.tree('getChecked');
		if (nodes && nodes.length > 0) {
			for ( var i = 0; i < nodes.length; i++) {
				resourceTree.tree('uncheck', nodes[i].target);
			}
		}
	}
	function checkInverse() {
		var unchecknodes = resourceTree.tree('getChecked', 'unchecked');
		var checknodes = resourceTree.tree('getChecked');
		if (unchecknodes && unchecknodes.length > 0) {
			for ( var i = 0; i < unchecknodes.length; i++) {
				resourceTree.tree('check', unchecknodes[i].target);
			}
		}
		if (checknodes && checknodes.length > 0) {
			for ( var i = 0; i < checknodes.length; i++) {
				resourceTree.tree('uncheck', checknodes[i].target);
			}
		}
	} 
</script>
<div id="roleGrantLayout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'west'" title="系统权限" style="width: 300px; padding: 1px;">
		<div class="well well-small">
			<s:form id="roleGrantForm" method="post" theme="simple" enctype="multipart/form-data">
				<input name="roleId" type="hidden"  value="${role.id}" readonly="readonly">
				<ul id="resourceTree"></ul>
				<input id="resourceIds" name="resourceIds" type="hidden">
			</s:form>
		</div>
	</div>
	<div data-options="region:'center'" title="" style="overflow: hidden; padding: 10px;">
		<div>
			<button class="btn btn-success" onclick="checkAll();">全选</button>
			<br /> <br />
			<button class="btn btn-warning" onclick="checkInverse();">反选</button>
			<br /> <br />
			<button class="btn btn-inverse" onclick="uncheckAll();">取消</button>
		</div>
	</div>
	<br/>
	<!-- <div style="text-align: center; padding: 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="toGrentAuths('#roleGrantForm')">添加</a>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="exitGrentAuthsPage()">关闭</a>
	</div> -->
</div>