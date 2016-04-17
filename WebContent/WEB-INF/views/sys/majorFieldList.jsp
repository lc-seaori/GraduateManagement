<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="easyui-layout" data-options="fit:true,border:false" style="width: 99%; height: auto">
	<div data-options="region:'center',fit:true,border:false,iconCls:'icon-dic'" title="专业方向列表">
		<div id="majorToolbar" style="padding: 5px; height: auto">	
				<td>专业名称:</td>
				<td><input class="easyui-validatebox textbox" id="name"
					name="name" 
					style="width: 100px; height: 13px; padding: 5px" />
				</td> 
				&nbsp;
				<td>所属学院:</td>
				<td><s:select cssClass="easyui-combobox" id="departmentSelected" editable="false" 
					name="departmentSelected" list="#request.departments" listKey="id" listValue="name" headerKey="0" headerValue="---请选择---"  
					cssStyle="width: 140px;height:26px" data-options="panelWidth:'120px', panelHeight:'auto'"></s:select>
				</td> 
				&nbsp;
				<button href="#" class="easyui-linkbutton" plain="true" 
					onclick="getMajorsByLimit('#majorToolbar', '#majorList')" 
					iconCls="icon-search">查询</button>
		</div>
		<table id="majorList" class="easyui-datagrid" style="width: auto;"
			pagination="true" url="System_Major_getMajorList.action" toolbar="#majorOperToolbar" 
			rownumbers="true" data-options="pageSize:10,loadMsg:'数据加载中,请稍后...',idField :'id',sortName:'name',sortOrder:'desc'" >
			<thead>
				<tr>
					<th field="id" width="100" align="center" checkbox="true">id</th>
					<th field="name" width="120" align="center" sortable="true">专业名字</th>
					<th field="departmentName" width="120" align="center">所属学院</th>
					<th field="remark" width="400" align="center">专业描述</th>
				</tr>
			</thead>
		</table>
	</div>
	
    <div id="majorOperToolbar">
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toAddMajor()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toEditMajor()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="toDeleteMajor()">删除</a>
    </div>
</div>
<div id="addMajorDialog" data-options="iconCls:'icon-add'" style="width:600px;height:300px;padding:10px"></div>
<div id="editMajorDialog" data-options="iconCls:'icon-edit'" style="width:600px;height:300px;padding:10px"></div>
<script type="text/javascript">

	function getMajorsByLimit(toolbar,datagrid){
		//解决浏览器不兼容tirm()方法，如ie8以下不兼容trim()，该方法去掉前后空格
		if(!String.prototype.trim) {
			String.prototype.trim = function(){
		    	return this.replace(/^\s+|\s+$/g,'');
		  	};
		}
		
		var name=$(toolbar).find("#name").val().trim();
		var departmentSelected=$(toolbar).find('#departmentSelected').combobox('getValue');
		
		$('#majorList').datagrid({
			queryParams: {
				name: name,
				departmentSelected: departmentSelected
			}
		});
		
		//再次发送请求
		$.post('System_Major_getMajorList.action',{
			name: name,
			departmentSelected: departmentSelected
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
	function toAddMajor(){
		$('#addMajorDialog').dialog({
			title :"	添加专业信息",
		    width: 600,
		    height: 400,
		    cache: false,
		    href: 'System_Major_addMajorPage.action',
		    collapsible: true,
		    maximizable: true,
		    resizable: true,
		    modal: true,
		    closed: true
		});
		$('#addMajorDialog').dialog('open');
	}
	
	//编辑
	function toEditMajor(){
		var rows = $('#majorList').datagrid('getSelections');
   		if (rows.length == 1) {
   			$('#editMajorDialog').dialog({
   				title :"	编辑专业信息",
   			    width: 600,
   			    height: 400,
   			    cache: false,
   			    href: 'System_Major_editMajorPage.action?majorId='+rows[0].id,
   			    collapsible: true,
   			    maximizable: true,
   			    resizable: true,
   			    modal: true,
   			    closed: true
   			});
   			$('#editMajorDialog').dialog('open');
   		} else if (rows.length > 1) {
   			 $.messager.alert('提示', '一次只能编辑一条信息！', 'error');
   		} else {
   			 $.messager.alert('提示', '请勾选要编辑的专业信息！', 'error');
   		}
	}
	
	//删除
	function toDeleteMajor(){
		var rows = $('#majorList').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示', '请勾选要删除信息！', 'info');
   			return;
		}else{
			var length=rows.length;
			$.messager.confirm('请确认','您确定删除共'+length+'条专业信息吗?',function(r){
                   if (r){
                   	var params="";
                   	for(i=0;i<rows.length;i++){
                   		params=params+rows[i].id;
                   		if(!(i==rows.length-1)){
                   			params=params+",";
                   		}
                   	}
   					var url="System_Major_toDeleteMajor.action";
                   	$.post(url, {idList:params},
                   	function(data){
                    	if (data.success){
                      		$('#majorList').datagrid('uncheckAll');
                            $.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '删除记录成功'
                            });
                            $('#majorList').datagrid('reload');    // reload the user data
                        }else {
                        	$.messager.show({    // show error message
                            	title: 'Error',
                           		msg: '删除专业方向失败，请先删除该专业的所有班级信息，再执行该操作...'
                           	});
                        }
                    },'json'); 
                 }
            });
		}
	} 
	
</script>
