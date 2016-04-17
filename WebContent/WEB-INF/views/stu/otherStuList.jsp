<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div style="width: 99%; height: auto">
	<div id="otherStuToolbar" style="padding: 5px; height: auto">	
			<td>学号:</td>
			<td><input class="easyui-validatebox textbox" id="code"
				name="code" 
				style="width: 100px; height: 13px; padding: 5px" />
			</td>
			&nbsp;
			<td>学生姓名:</td>
			<td><input class="easyui-validatebox textbox" id="name"
				name="name" 
				style="width: 100px; height: 13px; padding: 5px" />
			</td> 
			&nbsp;
			<td>班别:</td>
			<td><input class="easyui-validatebox textbox" id="clazzName"
				name="clazzName" 
				style="width: 100px; height: 13px; padding: 5px" />
			</td> 
			&nbsp;
			<td>学院:</td>
			<td><input class="easyui-validatebox textbox" id="departmentName"
				name="departmentName" 
				style="width: 100px; height: 13px; padding: 5px" />
			</td> 
			&nbsp;
			<td>毕业年月:</td>
			<td><input class="easyui-datebox" id="graduateTime" 
				name="graduateTime" editable="false" 
				style="width: 140px; height: 25px; padding: 5px" />
				<a style="height: 25px" class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#otherStuToolbar').find('#graduateTime').combobox('clear');" >清空</a>
			</td>
			
			<button href="#" class="easyui-linkbutton" plain="true" 
				onclick="getOtherStusByLimit('#otherStuToolbar', '#otherStuList')" 
				iconCls="icon-search">查询</button>
	</div>
	<table id="otherStuList" class="easyui-datagrid" style="width: auto;"
		pagination="true" url="Student_getStudentsNotInGra.action" toolbar="#otherStuOperToolbar" 
		rownumbers="true" data-options="pageSize:10,loadMsg:'数据加载中,请稍后...',idField :'id',sortName:'code',sortOrder:'desc'" >
		<thead>
			<tr>
				<th field="id" width="100" align="center" checkbox="true">id</th>
				<th field="code" width="120" align="center" sortable="true">学号</th>
				<th field="name" width="80" align="center">姓名</th>
				<th field="departmentName" width="200" align="center">学院</th>
				<th field="clazzName" width="120" align="center">班别</th>
				<th field="schoolStatus" width="80" align="center">学籍状态</th>
				<th field="graduationTime" width="150" align="center">毕业时间</th>
			</tr>
		</thead>
	</table>
	
    <div id="otherStuOperToolbar">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toOtherAddGraduateInfo()">添加就业信息</a>
    </div>
</div>
<script type="text/javascript">

	$(function(){
		//温馨提示
		$('#otherStuToolbar').find('#graduateTime').datebox({
            onSelect: function (date) {
            	$.messager.show({    // show error message
    	            title: '温馨提示',
    	            msg: '根据毕业时间查询，查询结果精确到月份',
    	            height: 150,
    	            timeout: 5000
    	        });
            }
        });
	});
	
	function getOtherStusByLimit(toolbar,datagrid){
		//解决浏览器不兼容tirm()方法，如ie8以下不兼容trim()，该方法去掉前后空格
		if(!String.prototype.trim) {
			String.prototype.trim = function () {
		    	return this.replace(/^\s+|\s+$/g,'');
		  	};
		}
		var code=$(toolbar).find("#code").val().trim();
		var name=$(toolbar).find("#name").val().trim();
		var departmentName=$(toolbar).find("#departmentName").val().trim();
		var clazzName=$(toolbar).find("#clazzName").val().trim();
		var graduateTime=$(toolbar).find('#graduateTime').combobox('getValue');
		//alert(graduateTime);
		$('#otherStuList').datagrid({
			queryParams: {
				code: code,
				name: name,
				departmentName: departmentName,
				clazzName: clazzName,
				graduateTime: graduateTime
			}
		});
		//再次发送请求
		$.post('Student_getStudentsNotInGra.action',{
			code: code,
			name:  name,
			departmentName: departmentName,
			clazzName: clazzName,
			graduateTime:graduateTime
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

	//添加就业信息
	function toOtherAddGraduateInfo(){
		var rows = $('#otherStuList').datagrid('getSelections');
   		if (rows.length == 1) {
   			openTab("GraduateInfo_Add_toAddGraduateInfoPage.action?stuId="+rows[0].id,'添加学生就业信息');
   		} else if (rows.length > 1) {
   			 $.messager.alert('提示', '一次只能为一名学生添加就业信息', 'error');
   		} else {
   			 $.messager.alert('提示', '请勾选学生信息', 'error');
   		}
	}
	
</script>
