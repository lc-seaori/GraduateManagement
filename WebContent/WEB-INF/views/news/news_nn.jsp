<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div style="width: 99%; height: auto">
	<div id="newsNnToolbar" style="padding: 5px; height: auto">	
			<td>新闻创建者名称:</td>
			<td><input class="easyui-validatebox textbox" id="createName"
				name="createName" 
				style="width: 100px; height: 13px; padding: 5px" />
			</td>
			&nbsp;
			<td>新闻标题:</td>
			<td><input class="easyui-validatebox textbox" id="title"
				name="title" 
				style="width: 100px; height: 13px; padding: 5px" />
			</td> 
			&nbsp;
			<td>新闻关键字:</td>
			<td><input class="easyui-validatebox textbox" id="keyWords"
				name="keyWords" 
				style="width: 100px; height: 13px; padding: 5px" />
			</td> 
			&nbsp;
			<button href="#" class="easyui-linkbutton" plain="true" 
				onclick="getNewsNnByLimit('#newsNnToolbar', '#newsNnList')" 
				iconCls="icon-search">查询</button>
	</div>
	<table id="newsNnList" class="easyui-datagrid" style="width: auto;"
		pagination="true" url="newsnn_list.action" toolbar="#newsNnOperToolbar" 
		rownumbers="true" data-options="pageSize:10,loadMsg:'数据加载中,请稍后...',idField :'id',sortName:'createTime',sortOrder:'desc'" >
		<thead>
			<tr>
				<th field="id" width="100" align="center" checkbox="true">id</th>
				<th field="title" width="130" align="center">新闻标题</th>
				<th field="createName" width="70" align="center">创建者</th>
				<th field="keyWords" width="100" align="center">关键字</th>
				<th field="viewCount" width="60" align="center">阅读数</th>
				<th field="createTime" width="250" align="center" sortable="true">创建时间</th>
				<th field="newsTypeStr" width="70" align="center">新闻类型</th>
				<th field="summary" width="200" align="center">新闻简介</th>
				<th field="platePname" width="130" align="center">所属一级板块</th>
				<th field="plateName" width="130" align="center">所属二级板块</th>
				<th field="content" width="300" align="center">新闻内容</th>
			</tr>
		</thead>
	</table>
	
    <div id="newsNnOperToolbar">
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="editNewsNn(1)">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editNewsNn(0)">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="delNewsNn()">删除</a>
    </div>
</div>
<script type="text/javascript">

	
	function getNewsNnByLimit(toolbar,datagrid){
		//解决浏览器不兼容tirm()方法，如ie8以下不兼容trim()，该方法去掉前后空格
		if(!String.prototype.trim) {
			String.prototype.trim = function () {
		    	return this.replace(/^\s+|\s+$/g,'');
		  	};
		}
		var createName=$(toolbar).find("#createName").val().trim();
		var title=$(toolbar).find("#title").val().trim();
		var keyWords=$(toolbar).find("#keyWords").val().trim();
		
		/* $('#newsNnList').datagrid({
			queryParams: {
				createName: createName,
				title: title,
				keyWords: keyWords
			}
		});  */
		
		//再次发送请求
		$.post('newsnn_list.action',{
			createName: createName,
			title:  title,
			keyWords: keyWords
		},function(data){
			if (data.total){
				//$(datagrid).datagrid('loadData', data); 
				$('#newsNnList').datagrid({
					queryParams: {
						createName: createName,
						title: title,
						keyWords: keyWords
					}
				}); 
            }else{
           		//$(datagrid).datagrid('loadData', data); 
           		$('#newsNnList').datagrid({
        			queryParams: {
        				createName: createName,
        				title: title,
        				keyWords: keyWords
        			}
        		}); 
           	 	$.messager.show({    // show error message
                    title: '提示',
                    msg: '没有任何记录'
                });
            }
			
		},"json")
	}
	
	//编辑
	function editNewsNn(type){
		var title="";
		if(type == '1'){
			title = "添加新闻信息";
			openTab("newsnn_edit.action?isAdd=1", title);
		} else {
			title = "更新新闻信息";
			var rows = $('#newsNnList').datagrid('getSelections');
			if (rows.length == 1) {
				openTab("newsnn_edit.action?id=" + rows[0].id + "&isAdd=0", title);
			} else if (rows.length > 1) {
				$.messager.alert('提示', '一次只能编辑一条信息！', 'error');
			} else {
				$.messager.alert('提示', '请勾选要编辑的信息！', 'error');
			}
		}
	}

	//删除
	function delNewsNn() {
		var rows = $('#newsNnList').datagrid('getSelections');
		if (rows.length == 0) {
			$.messager.alert('提示', '请勾选要删除信息！', 'info');
			return;
		} else {
			var length = rows.length;
			$.messager.confirm('请确认', '您确定删除共' + length + '条信息吗?', function(r) {
				if (r) {
					var params = "";
					for (i = 0; i < rows.length; i++) {
						params = params + rows[i].id;
						if (!(i == rows.length - 1)) {
							params = params + ",";
						}
					}
					var url = "newsnn_delete.action";
					$.post(url, {
						idList : params
					}, function(data) {
						if (data.success) {
							$('#newsNnList').datagrid('uncheckAll');
							$.messager.show({ // show error message
								title : 'Tips',
								msg : '删除记录成功'
							});
							$('#newsNnList').datagrid('reload'); // reload the user data
						} else {
							$.messager.show({ // show error message
								title : 'Error',
								msg : '删除记录失败'
							});
						}
					}, 'json');
				}
			});
		}
	}
</script>
