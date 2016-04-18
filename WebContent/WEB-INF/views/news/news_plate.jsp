<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div style="width: 99%; height: auto">
	<div id="newsPlateToolbar" style="padding: 5px; height: auto">	
			<td>新闻板块名:</td>
			<td><input class="easyui-validatebox textbox" id="plateName"
				name="plateName" 
				style="width: 100px; height: 13px; padding: 5px" />
			</td>
			
			<button href="#" class="easyui-linkbutton" plain="true" 
				onclick="getNewsPlateByLimit('#newsPlateToolbar', '#newsPlateList')" 
				iconCls="icon-search">查询</button>
	</div>
	<table id="newsPlateList" class="easyui-datagrid" style="width: auto;"
		pagination="true" url="newsplate_list.action" toolbar="#newsPlateOperToolbar" 
		rownumbers="true" data-options="pageSize:10,loadMsg:'数据加载中,请稍后...',idField :'id',sortName:'plateName',sortOrder:'desc'" >
		<thead>
			<tr>
				<th field="id" width="100" align="center" checkbox="true">id</th>
				<th field="plateName" width="160" align="center" sortable="true">板块名称</th>
				<th field="parentPlateName" width="160" align="center">一级板块名称</th>
				<th field="plateLevel" width="80" align="center">板块级别</th>
			</tr>
		</thead>
	</table>
	
	<div id="editNewsPlateDialog" data-options="iconCls:'icon-save'" style="width:500px;height:350px;padding:10px"></div>
    <div id="newsPlateOperToolbar">
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="editNewsPlate(1)">添加一级模板</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="editNewsPlate(2)">添加二级模板</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editNewsPlate(0)">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="delNewsPlate()">删除</a>
    </div>
</div>
<script type="text/javascript">
	
	function editNewsPlate(type){
		var url = "";
		var titleName = "";
		if(type == '1' || type == '2'){
			if(type == '1'){
				url = "newsplate_edit.action?isAdd=1&type=1";
			}else{
				url = "newsplate_edit.action?isAdd=1&type=2";
			}
			titleName = "新闻板块添加";
		}else if(type == '0'){
			var rows = $('#newsPlateList').datagrid('getSelections');
	   		if (rows.length == 1) {
	   			if(rows[0].parentPlateName == ''){
	   				url = "newsplate_edit.action?isAdd=0&type=1&newsPlateId="+rows[0].id;
	   			}else{
	   				url = "newsplate_edit.action?isAdd=0&type=2&newsPlateId="+rows[0].id;
	   			}
				titleName = "新闻板块修改";
	   		} else if (rows.length > 1) {
	   			 $.messager.alert('提示', '一次只能编辑一条信息！', 'error');
	   			 return ;
	   		} else {
	   			 $.messager.alert('提示', '请勾选要编辑的信息！', 'error');
	   			 return ;
	   		}
		}
		$('#editNewsPlateDialog').dialog({
		    title: titleName,
		    width: 500,
		    height: 250,
		    cache: false,
		    href: url,
		    collapsible: true,
		    maximizable: true,
		    resizable: true,
		    modal: true,
		    closed: true
		});
		$('#editNewsPlateDialog').dialog('open');
	} 
	
	function getNewsPlateByLimit(toolbar,datagrid){
		//解决浏览器不兼容tirm()方法，如ie8以下不兼容trim()，该方法去掉前后空格
		if(!String.prototype.trim) {
			String.prototype.trim = function () {
		    	return this.replace(/^\s+|\s+$/g,'');
		  	};
		}
		var plateName=$(toolbar).find("#plateName").val().trim();
		
		/* $('#newsPlateList').datagrid({
			queryParams: {
				plateName: plateName
			}
		}); */
		
		//再次发送请求
		$.post('newsplate_list.action',{
			plateName: plateName
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
	
	//删除
	function delNewsPlate(){
		var rows = $('#newsPlateList').datagrid('getSelections');
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
   					var url="newsplate_delete.action";
                   	$.post(url, {idList:params},
                   	function(data){
                    	if (data.success){
                      		$('#newsPlateList').datagrid('uncheckAll');
                            $.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '删除记录成功'
                            });
                            $('#newsPlateList').datagrid('reload');
                        }else {
                        	$.messager.show({    // show error message
                            	title: 'Error',
                           		msg: data.msg
                           	});
                        }
                    },'json'); 
                 }
            });
		}
	}
	
</script>
