<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="easyui-layout" data-options="fit:true,border:false" style="width: 99%; height: auto">
	<div data-options="region:'center',fit:true,border:false,iconCls:'icon-bug'" title="用户操作记录">
		<div id="operRecordToolbar" style="padding: 5px; height: auto">	
				<td>操作人:</td>
				<td><input class="easyui-validatebox textbox" id="operationPerson"
					name="operationPerson" 
					style="width: 100px; height: 13px; padding: 5px" />
				</td> 
				&nbsp;
				<td>操作:</td>
				<td><input class="easyui-validatebox textbox" id="operationName"
					name="operationName" 
					style="width: 100px; height: 13px; padding: 5px" />
				</td> 
				&nbsp;
				<td>操作时间：</td>
				<td><s:select list="#{1:'一周内',2:'一个月内',3:'三个月内'}" listKey="key" listValue="value" editable="false" 
					headerKey="0" headerValue="--选择--" cssClass="easyui-combobox" id="time" name="time" 
					style="width: 100px; height: 25px; padding: 5px" data-options="panelWidth:'80px', panelHeight:'auto'"></s:select>
				</td> 
				&nbsp;
				<button href="#" class="easyui-linkbutton" plain="true" 
					onclick="getOperRecordsByLimit('#operRecordToolbar', '#operRecordList')" 
					iconCls="icon-search">查询</button>
		</div>
		<table id="operRecordList" class="easyui-datagrid" style="width: auto;"
			pagination="true" url="System_Record_getOperRecordList.action" toolbar="#operRecordOperToolbar" 
			rownumbers="true" data-options="pageSize:10,loadMsg:'数据加载中,请稍后...',idField :'id',sortName:'operationPerson',sortOrder:'desc'" >
			<thead>
				<tr>
					<th field="id" width="100" align="center" checkbox="true">id</th>
					<th field="operationPerson" width="100" align="center" sortable="true">操作人</th>
					<th field="operationName" width="200" align="center">操作</th>
					<th field="operationUrl" width="400" align="center">操作路径</th>
					<th field="pageOperationTime" width="150" align="center">操作时间</th>
					<th field="operationDescription" width="300" align="center">操作描述</th>
				</tr>
			</thead>
		</table>
	</div>
	
    <div id="operRecordOperToolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="toDeleteOperRecord()">删除</a>
    </div>
</div>
<div id="addClazzDialog" data-options="iconCls:'icon-add'" style="width:600px;height:300px;padding:10px"></div>
<div id="editClazzDialog" data-options="iconCls:'icon-edit'" style="width:600px;height:300px;padding:10px"></div>
<script type="text/javascript">

	function getOperRecordsByLimit(toolbar,datagrid){
		//解决浏览器不兼容tirm()方法，如ie8以下不兼容trim()，该方法去掉前后空格
		if(!String.prototype.trim) {
			String.prototype.trim = function(){
		    	return this.replace(/^\s+|\s+$/g,'');
		  	};
		}
		
		var operationPerson=$(toolbar).find("#operationPerson").val().trim();
		var operationName=$(toolbar).find("#operationName").val().trim();
		var time=$(toolbar).find('#time').combobox('getValue');
		
		$('#operRecordList').datagrid({
			queryParams: {
				operationPerson: operationPerson,
				operationName: operationName,
				time: time
			}
		});
		
		//再次发送请求
		$.post('System_Record_getOperRecordList.action',{
			operationPerson: operationPerson,
			operationName: operationName,
			time: time
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
	function toDeleteOperRecord(){
		var rows = $('#operRecordList').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示', '请勾选要删除信息！', 'info');
   			return;
		}else{
			var length=rows.length;
			$.messager.confirm('请确认','您确定删除共'+length+'条操作信息吗?',function(r){
                   if (r){
                   	var params="";
                   	for(i=0;i<rows.length;i++){
                   		params=params+rows[i].id;
                   		if(!(i==rows.length-1)){
                   			params=params+",";
                   		}
                   	}
   					var url="System_Record_deleteRecord.action";
                   	$.post(url, {idList : params},
                   	function(data){
                    	if (data.success){
                      		$('#operRecordList').datagrid('uncheckAll');
                            $.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '删除记录成功'
                            });
                            $('#operRecordList').datagrid('reload');    // reload the user data
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
