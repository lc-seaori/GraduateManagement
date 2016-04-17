<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div style="width: 99%; height: auto">
	<div id="empUnitListToolbar" style="padding: 5px; height: auto">	
			<td>单位编号：</td>
			<td><input class="easyui-validatebox textbox" id="unitCode"
				name="unitCode" 
				style="width: 100px; height: 13px; padding: 5px" />
			</td>
			&nbsp;
			<td>单位名称：</td>
			<td><input class="easyui-validatebox textbox" id="unitName"
				name="unitName" 
				style="width: 100px; height: 13px; padding: 5px" />
			</td> 
			&nbsp;
			<td>单位性质：</td>
			<td><input class="easyui-validatebox textbox" id="unitTy"
				name="unitTy" 
				style="width: 100px; height: 13px; padding: 5px" />
			</td> 
			&nbsp;
			<td>招聘方式：</td>
			<td><input class="easyui-validatebox textbox" id="recruitmentTy"
				name="recruitmentTy" 
				style="width: 100px; height: 13px; padding: 5px" />
			</td> 
			<button href="#" class="easyui-linkbutton" plain="true" 
				onclick="getEmpUnitsByLimit('#empUnitListToolbar', '#empUnitList')" 
				iconCls="icon-search">查询</button>
	</div>
	<table id="empUnitList" class="easyui-datagrid" style="width: auto;"
		pagination="true" url="EmployingUnit_List_getEmpUnitList.action" toolbar="#empUnitOperToolbar" 
		rownumbers="true" data-options="pageSize:10,loadMsg:'数据加载中,请稍后...',idField :'id',sortName:'unitCode',sortOrder:'desc'">
		<thead>
			<tr>
				<th field="id" width="100" align="center" checkbox="true">id</th>
				<th field="unitCode" width="70" align="center" sortable="true">单位编号</th>
				<th field="unitName" width="200" align="center">单位名称</th>
				<th field="recruitment" width="90" align="center">招聘方式</th>
				<th field="unit" width="100" align="center">单位性质</th>
				<th field="unitContantPerson" width="80" align="center">单位联系人</th>
				<th field="unitContantPhone" width="120" align="center">单位联系电话</th>
				<th field="unitAddress" width="400" align="center">单位详细地址</th>
				<th id="unitDescription" field="unitDescription" width="400" align="center">用人单位描述</th>
			</tr>
		</thead>
	</table>
	
    <div id="empUnitOperToolbar">
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toAddEmpUnit()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toEditEmpUnit()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="toDeleteEmpUnit()">删除</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-blank" plain="true" onclick="toGetRecruitmentInfos()">单位招聘信息</a> 
    </div>
</div>
<script type="text/javascript">
	
	function getEmpUnitsByLimit(toolbar,datagrid){
		
		//解决浏览器不兼容tirm()方法，如ie8以下不兼容trim()，该方法去掉前后空格
		if(!String.prototype.trim) {
			String.prototype.trim = function () {
		    	return this.replace(/^\s+|\s+$/g,'');
		  	};
		}
		
		var unitCode=$(toolbar).find("#unitCode").val().trim();
		var unitName=$(toolbar).find("#unitName").val().trim();
		var unitTy=$(toolbar).find("#unitTy").val().trim();
		var recruitmentTy=$(toolbar).find("#recruitmentTy").val().trim();
		$('#empUnitList').datagrid({
			queryParams: {
				unitCode: unitCode,
				unitName: unitName,
				unitTy: unitTy,
				recruitmentTy: recruitmentTy
			}
		});
		//再次发送请求
		$.post('EmployingUnit_List_getEmpUnitList.action',{
			unitCode: unitCode,
			unitName:  unitName,
			unitTy: unitTy,
			recruitmentTy: recruitmentTy,
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
		},"json");
	}
	
	//添加
	function toAddEmpUnit(){
		openTab("EmployingUnit_Add_toAddEmpUnitPage.action",'添加招聘单位信息');
	}
	
	
	//编辑
	function toEditEmpUnit(){
		var rows = $('#empUnitList').datagrid('getSelections');
   		if (rows.length == 1) {
   			openTab("EmployingUnit_Edit_toEditEmpUnitPage.action?id="+rows[0].id,'就业信息编辑');
   		} else if (rows.length > 1) {
   			 $.messager.alert('提示', '一次只能编辑一条信息！', 'error');
   		} else {
   			 $.messager.alert('提示', '请勾选要编辑的信息！', 'error');
   		}
	}
	
	//删除
	function toDeleteEmpUnit(){
		var rows = $('#empUnitList').datagrid('getSelections');
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
   					var url="EmployingUnit_Del_toDelEmpUnit.action";
   					//alert(url);
                   	$.post(url, {idList:params},
                   	function(data){
                    	if (data.success){
                      		$('#empUnitList').datagrid('uncheckAll');
                            $.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '删除记录成功'
                            });
                            $('#empUnitList').datagrid('reload');    // reload the user data
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
	
	//根据招聘单位查询其所有的招聘信息
	function toGetRecruitmentInfos(){
		var rows = $('#empUnitList').datagrid('getSelections');
   		if (rows.length == 1) {
   			openTab("EmployingUnit_InfoList_toRecruitmentInfosPage?id="+rows[0].id,'招聘单位所有招聘信息');
   		} else if (rows.length > 1) {
   			 $.messager.alert('提示', '一次只能查看一个公司招聘信息！', 'error');
   		} else {
   			 $.messager.alert('提示', '请勾选公司招聘单位信息！', 'error');
   		}
		
	}
</script>
