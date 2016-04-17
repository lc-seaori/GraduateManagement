<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div style="width: 99%; height: auto">
	<div id="studentToolbar" style="padding: 5px; height: auto">	
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
				<a style="height: 25px" class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#studentToolbar').find('#graduateTime').combobox('clear');" >清空</a>
			</td>
			
			<button href="#" class="easyui-linkbutton" plain="true" 
				onclick="getStudentsByLimit('#studentToolbar', '#studentList')" 
				iconCls="icon-search">查询</button>
	</div>
	<table id="studentList" class="easyui-datagrid" style="width: auto;"
		pagination="true" url="Student_getStudentList.action" toolbar="#studentOperToolbar" 
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
	
    <div id="studentOperToolbar">
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="toCheckStudentInfo()">查看学生源信息</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toEditStudent()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="toDeleteStudent()">删除</a>
    </div>
</div>
<script type="text/javascript">

	$(function(){
		//温馨提示
		$('#studentToolbar').find('#graduateTime').datebox({
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
	
	/* $(function(){
		var code=$("#studentToolbar").find("#code").val().trim();
		var name=$("#studentToolbar").find("#name").val().trim();
		var departmentName=$("#studentToolbar").find("#departmentName").val().trim();
		var clazzName=$("#studentToolbar").find("#clazzName").val().trim();
		var graduateTime=$("#studentToolbar").find('#graduateTime').combobox('getValue');
		$('#studentList').datagrid({
			queryParams: {
				code: code,
				name: name,
				departmentName: departmentName,
				clazzName: clazzName,
				graduateTime: graduateTime
			}
		});
	}); */
	
	function getStudentsByLimit(toolbar,datagrid){
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
		$('#studentList').datagrid({
			queryParams: {
				code: code,
				name: name,
				departmentName: departmentName,
				clazzName: clazzName,
				graduateTime: graduateTime
			}
		});
		//再次发送请求
		$.post('Student_getStudentList.action',{
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
	
	//另开一个Action，方便信息传递
	function toCheckStudentInfo(){
		var rows = $('#studentList').datagrid('getSelections');
   		if (rows.length == 1) {
   			openTab("StudentDetials_toStudentInfoDetials.action?studentId="+rows[0].id,'学生源信息');
   		} else if (rows.length > 1) {
   			 $.messager.alert('提示', '一次查看一条信息', 'error');
   		} else {
   			 $.messager.alert('提示', '请勾选要查看的学生信息！', 'error');
   		}
	}

	//编辑
	function toEditStudent(){
		var rows = $('#studentList').datagrid('getSelections');
   		if (rows.length == 1) {
   			openTab("Student_toEditStudentPage.action?id="+rows[0].id,'编辑学生信息');
   		} else if (rows.length > 1) {
   			 $.messager.alert('提示', '一次只能编辑一条信息！', 'error');
   		} else {
   			 $.messager.alert('提示', '请勾选要编辑的学生信息！', 'error');
   		}
	}
	
	//删除
	function toDeleteStudent(){
		var rows = $('#studentList').datagrid('getSelections');
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
   					var url="Student_toDeleteStudent.action";
   					//alert(url);
                   	$.post(url, {idList:params},
                   	function(data){
                    	if (data.success){
                      		$('#studentList').datagrid('uncheckAll');
                            $.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '删除记录成功'
                            });
                            $('#studentList').datagrid('reload');    // reload the user data
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
