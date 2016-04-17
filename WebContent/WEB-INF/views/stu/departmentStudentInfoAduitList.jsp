<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div style="width: 99%; height: auto">
	<div id="departmentAduitListToolbar" style="padding: 5px; height: auto">	
			<td>准考证号:</td>
			<td><input class="easyui-validatebox textbox" id="examNum"
				name="examNum" 
				style="width: 100px; height: 13px; padding: 5px" />
			</td>
			&nbsp;
			<td>学生姓名:</td>
			<td><input class="easyui-validatebox textbox" id="name"
				name="name" 
				style="width: 100px; height: 13px; padding: 5px" />
			</td> 
			&nbsp;
			<td>身份证号码:</td>
			<td><input class="easyui-validatebox textbox" id="idCard"
				name="idCard" 
				style="width: 100px; height: 13px; padding: 5px" />
			</td> 
			&nbsp;
			<td>学生学院：</td>
			<td><input class="easyui-validatebox textbox" id="departmentName" 
				name="departmentName" 
				style="width: 130px; height: 13px; padding: 5px" />
			</td> 
			&nbsp;
			<td>毕业年月:</td>
			<td><input class="easyui-datebox" id="graduateTime" 
				name="graduateTime" editable="false" 
				style="width: 140px; height: 25px; padding: 5px" />
				<a style="height: 25px" class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#departmentAduitListToolbar').find('#graduateTime').combobox('clear');" >清空</a>
			</td>
			
			<button href="#" class="easyui-linkbutton" plain="true" 
				onclick="getDepartmentAduitListByLimit('#departmentAduitListToolbar', '#departmentAduitList')"
				iconCls="icon-search">查询</button>
	</div>
	<table id="departmentAduitList" class="easyui-datagrid" style="width: auto;"
		pagination="true" url="StudentInfo_List_getDepartmentAduitList.action" toolbar="#departmentAduitListOperToolbar" 
		rownumbers="true" data-options="pageSize:10,loadMsg:'数据加载中,请稍后...',idField :'id',sortName:'examNum',sortOrder:'desc'" >
		<thead>
			<tr>
				<th field="id" width="100" align="center" checkbox="true">考生id</th>
				<th field="departmentAduit" width="70" align="center">院系审核</th>
				<th field="schoolAduit" width="70" align="center">学校审核</th>
				<th field="examNum" width="80" align="center" sortable="true">准考证号</th>
				<th field="name" width="70" align="center">姓名</th>
				<th field="sex" width="40" align="center">性别</th>
				<th field="majorFieldName" width="120" align="center">所报专业</th>
				<th field="idCard" width="250" align="center">身份证号码</th>
				<th field="graduationSchool" width="200" align="center">毕业学校</th>
				<th field="nation" width="80" align="center">民族</th>
				<th field="politicalFeature" width="100" align="center">政治面貌</th>
				<th field="nativePlace" width="130" align="center">籍贯</th>
				<th field="household" width="80" align="center">户籍类型</th>
				<th field="birthday" width="180" align="center">出生年月</th>
				<th field="schoolLength" width="70" align="center">学制</th>
				<th field="education" width="60" align="center">学历</th>
				<th field="phone" width="100" align="center">联系电话</th>
				<th field="enterCollegeTime" width="180" align="center">入学时间</th>
				<th field="graduateTime" width="180" align="center">毕业时间</th>
				<th field="postEncoding" width="80" align="center">邮政编码</th>
				<th id="householdPlace" field="householdPlace" width="400" align="center">户口所在地</th>
			</tr>
		</thead>
	</table>
	
    <div id="departmentAduitListOperToolbar">
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toDAddNewStudentInfo()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toDEditStudentInfo()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="toDDeleteStudentInfo()">删除</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="toDAduit()">审核批准</a>
   		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="toDCancelAduit()">审核不批准</a>
   		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-filter" plain="true" onclick="toDApplySchoolAduitStu()">申请学校审核</a>
   		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-filter" plain="true" onclick="toDCancelApplySchoolAduitStu()">取消申请学校审核</a>
    </div>
</div>
<script type="text/javascript">

	

	$(function(){
		//温馨提示
		$('#departmentAduitListToolbar').find('#graduateTime').datebox({
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
		var examNum=$("#departmentAduitListToolbar").find("#examNum").val().trim();
		var name=$("#departmentAduitListToolbar").find("#name").val().trim();
		var idCard=$("#departmentAduitListToolbar").find("#idCard").val().trim();
		var departmentName=$("#departmentAduitListToolbar").find("#departmentName").val().trim();
		var graduateTime=$("#departmentAduitListToolbar").find('#graduateTime').combobox('getValue');
		$('#departmentAduitList').datagrid({
			queryParams: {
				examNum: examNum,
				name: name,
				idCard: idCard,
				departmentName: departmentName,
				graduateTime: graduateTime
			}
		});
	}); */
	
	function getDepartmentAduitListByLimit(toolbar,datagrid){
		//解决浏览器不兼容tirm()方法，如ie8以下不兼容trim()，该方法去掉前后空格
		if(!String.prototype.trim) {
			String.prototype.trim = function () {
		    	return this.replace(/^\s+|\s+$/g,'');
		  	};
		}
		var examNum=$(toolbar).find("#examNum").val().trim();
		var name=$(toolbar).find("#name").val().trim();
		var idCard=$(toolbar).find("#idCard").val().trim();
		var departmentName=$(toolbar).find("#departmentName").val().trim();
		var graduateTime=$(toolbar).find('#graduateTime').combobox('getValue');
		//alert(graduateTime);
		
		$('#departmentAduitList').datagrid({
			queryParams: {
				examNum: examNum,
				name: name,
				idCard: idCard,
				departmentName: departmentName,
				graduateTime: graduateTime
			}
		});
		
		//再次发送请求
		$.post('StudentInfo_List_getDepartmentAduitList.action',{
			examNum: examNum,
			name:  name,
			idCard: idCard,
			departmentName: departmentName,
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
	
	//添加
	function toDAddNewStudentInfo(){
		openTab("StudentInfo_AddNewInfo_toAddStudentInfoPage",'添加新生信息');
	}
	
	//编辑
	function toDEditStudentInfo(){
		var rows = $('#departmentAduitList').datagrid('getSelections');
   		if (rows.length == 1) {
   			openTab("StudentInfo_EditStuInfo_toEditStudentInfoPage?id="+rows[0].id,'编辑新生信息');
   		} else if (rows.length > 1) {
   			 $.messager.alert('提示', '一次只能编辑一条信息！', 'error');
   		} else {
   			 $.messager.alert('提示', '请勾选要编辑的信息！', 'error');
   		}
	}
	
	//删除
	function toDDeleteStudentInfo(){
		var rows = $('#departmentAduitList').datagrid('getSelections');
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
   					var url="StudentInfo_DelStuInfo_toDelStuInfo.action";
   					//alert(url);
                   	$.post(url, {idList:params},
                   	function(data){
                    	if (data.success){
                      		$('#departmentAduitList').datagrid('uncheckAll');
                            $.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '删除记录成功'
                            });
                            $('#departmentAduitList').datagrid('reload');    // reload the user data
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
	
	//院系审核信息（通过）
	function toDAduit(){
		var applyRows=$('#departmentAduitList').datagrid('getSelections');
		if(applyRows.length==0){
			$.messager.alert('提示', '请勾选通过审核的学生信息！', 'info');
   			return;
		}else{
			//alert(applyRows[0].departmentAduit);
			var length=applyRows.length;
			$.messager.confirm('请确认','您确定通过这'+length+'条信息的审核吗?',function(r){
            	if (r){
                	var params="";
                   	for(i=0;i<applyRows.length;i++){
                   		//已经通过申请的不用再申请
                   		
                   		if(applyRows[i].departmentAduit=="通过"){
                   			$.messager.alert('提示', '勾选信息中有已通过院系审核的信息，不用再重复审核', 'warning');
                   			return ;
                   		}
                   		params=params+applyRows[i].id;
                		if(!(i==applyRows.length-1)){
                			params=params+",";
                		}
                   	}
                  	//发送请求到action
                   	var url="StudentInfo_AduitStuInfo_toDoDepartmentAduit.action";
   					//alert(url);
                   	$.post(url, {idList:params},
                   	function(data){
                    	if (data.success){
                    		//$('#departmentAduitList').datagrid('uncheckAll');
                       		$.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '审核成功',
                            });
                            $('#departmentAduitList').datagrid('reload');    // reload the user data
                        }else{
                         	$.messager.show({    // show error message
                            	title: 'Error',
                            	msg: '审核失败'
                            });
                    	}
                    },'json'); 
                }
			});
		}
	}
	
	//院系审核信息（不通过）
	function toDCancelAduit(){
		var applyRows=$('#departmentAduitList').datagrid('getSelections');
		if(applyRows.length==0){
			$.messager.alert('提示', '请勾选不通过审核的学生信息！', 'info');
   			return;
		}else{
			//alert(applyRows[0].departmentAduit);
			var length=applyRows.length;
			$.messager.confirm('请确认','您确定不通过这'+length+'条信息的审核吗?',function(r){
            	if (r){
                	var params="";
                   	for(i=0;i<applyRows.length;i++){
                   		
                   		if(applyRows[i].schoolAduit=="通过"){
                   			$.messager.alert('提示', '选中的学生信息已经通过学校的审核，不能再次审核', 'info');
                   			return ;
                   		}
                   		//没通过申请的不用设置不通过
                   		if(applyRows[i].departmentAduit=="不通过"){
                   			$.messager.alert('提示', '勾选信息中有不通过院系审核的信息，不用再重复审核', 'warning');
                   			return ;
                   		}
                   		params=params+applyRows[i].id;
                		if(!(i==applyRows.length-1)){
                			params=params+",";
                		}
                   	}
                  	//发送请求到action
                   	var url="StudentInfo_AduitStuInfo_toDoCancelDepartmentAduit.action";
   					//alert(url);
                   	$.post(url, {idList:params},
                   	function(data){
                    	if (data.success){
                    		//$('#departmentAduitList').datagrid('uncheckAll');
                       		$.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '审核成功',
                            });
                            $('#departmentAduitList').datagrid('reload');    // reload the user data
                        }else{
                         	$.messager.show({    // show error message
                            	title: 'Error',
                            	msg: '审核失败'
                            });
                    	}
                    },'json'); 
                }
			});
		}
	}	
	
	//申请学校审核
	function toDApplySchoolAduitStu(){
		var applyRows=$('#departmentAduitList').datagrid('getSelections');
		if(applyRows.length==0){
			$.messager.alert('提示', '请勾选申请学校审核的学生信息！', 'info');
   			return;
		}else{
			//alert(applyRows[0].departmentAduit);
			var length=applyRows.length;
			$.messager.confirm('请确认','您确定将'+length+'条信息提交给学校审核吗?',function(r){
            	if (r){
                	var params="";
                   	for(i=0;i<applyRows.length;i++){
                   		//判断院系申请状态是否已经在[审核中]或者已通过
                   		if(applyRows[i].departmentAduit!="通过"){
                   			$.messager.alert('提示', '申请学校审批，必须先通过院系审批', 'warning');
                   			return ;
                   		}else if(applyRows[i].schoolAduit=="审核中"){
                   			$.messager.alert('提示', '勾选的信息中有在【审核中】的信息，请重选选择', 'warning');
                   			return ;
                   		}else if(applyRows[i].schoolAduit=="通过"){
                   			$.messager.alert('提示', '勾选的信息中有已通过审核的信息，请重选选择', 'warning');
                   			return ;
                   		}
                		params=params+applyRows[i].id;
                		if(!(i==applyRows.length-1)){
                			params=params+",";
                		}
                    }
                   	//发送请求到action
                   	var url="StudentInfo_AduitStuInfo_toDoApplySchoolAduit.action";
   					//alert(url);
                   	$.post(url, {idList:params},
                   	function(data){
                    	if (data.success){
                       		$.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '申请成功，等待学校审核'
                            });
                            $('#departmentAduitList').datagrid('reload');    // reload the user data
                        }else{
                         	$.messager.show({    // show error message
                            	title: 'Error',
                            	msg: '申请失败'
                            });
                    	}
                    },'json'); 
                }
			});
		}
	}
	
	//取消申请学校审核
	function toDCancelApplySchoolAduitStu(){
		var applyRows=$('#departmentAduitList').datagrid('getSelections');
		if(applyRows.length==0){
			$.messager.alert('提示', '请勾选需要取消申请学校审核的学生信息！', 'info');
   			return;
		}else{
			//alert(applyRows[0].departmentAduit);
			var length=applyRows.length;
			$.messager.confirm('请确认','您确定取消'+length+'条信息的学校审核吗?',function(r){
            	if (r){
                	var params="";
                   	for(i=0;i<applyRows.length;i++){
                   		//判断院系申请状态是否已经未审核或者已经通过
                   		if(applyRows[i].schoolAduit=="未审核"){
                   			$.messager.alert('提示', '勾选的信息中有未提交审核的信息，请重选选择', 'warning');
                   			return ;
                   		}else if(applyRows[i].schoolAduit=="通过"){
                   			$.messager.alert('提示', '勾选的信息中有已通过审核的信息，请重选选择', 'warning');
                   			return ;
                   		}
                		params=params+applyRows[i].id;
                		if(!(i==applyRows.length-1)){
                			params=params+",";
                		}
                    }
                   	//发送请求到action
                   	var url="StudentInfo_AduitStuInfo_toDoCacelApplySchoolAduit.action";
   					//alert(url);
                   	$.post(url, {idList:params},
                   	function(data){
                    	if (data.success){
                       		$.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '取消审核申请成功'
                            });
                            $('#departmentAduitList').datagrid('reload');    // reload the user data
                        }else{
                         	$.messager.show({    // show error message
                            	title: 'Error',
                            	msg: '取消审核申请失败'
                            });
                    	}
                    },'json'); 
                }
			});
		}
	} 
	
</script>
