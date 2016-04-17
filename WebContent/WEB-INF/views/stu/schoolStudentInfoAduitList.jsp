<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div style="width: 99%; height: auto">
	<div id="schoolAduitListToolbar" style="padding: 5px; height: auto">	
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
				<a style="height: 25px" class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#schoolAduitListToolbar').find('#graduateTime').combobox('clear');" >清空</a>
			</td>
			
			<button href="#" class="easyui-linkbutton" plain="true" 
				onclick="getSchoolAduitListByLimit('#schoolAduitListToolbar', '#schoolAduitList')"
				iconCls="icon-search">查询</button>
	</div>
	<table id="schoolAduitList" class="easyui-datagrid" style="width: auto;"
		pagination="true" url="StudentInfo_List_getSchoolAduitList.action" toolbar="#schoolAduitListOperToolbar" 
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
	
    <div id="schoolAduitListOperToolbar">
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toSAddNewStudentInfo()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toSEditStudentInfo()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="toSDeleteStudentInfo()">删除</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="toSAduit()">审核批准</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="toSCancelAduit()">审核不批准</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-filter" plain="true" onclick="">信息变更记录(待定)</a>
    </div>
</div>
<script type="text/javascript">

	

	$(function(){
		//温馨提示
		$('#schoolAduitListToolbar').find('#graduateTime').datebox({
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
		var examNum=$("#schoolAduitListToolbar").find("#examNum").val().trim();
		var name=$("#schoolAduitListToolbar").find("#name").val().trim();
		var idCard=$("#schoolAduitListToolbar").find("#idCard").val().trim();
		var departmentName=$("#schoolAduitListToolbar").find("#departmentName").val().trim();
		var graduateTime=$("#schoolAduitListToolbar").find('#graduateTime').combobox('getValue');
		$('#schoolAduitList').datagrid({
			queryParams: {
				examNum: examNum,
				name: name,
				idCard: idCard,
				departmentName: departmentName,
				graduateTime: graduateTime
			}
		});
	}); */
	
	function getSchoolAduitListByLimit(toolbar,datagrid){
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
		
		$('#schoolAduitList').datagrid({
			queryParams: {
				examNum: examNum,
				name: name,
				idCard: idCard,
				departmentName: departmentName,
				graduateTime: graduateTime
			}
		});
		
		//再次发送请求
		$.post('StudentInfo_List_getSchoolAduitList.action',{
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
	function toSAddNewStudentInfo(){
		openTab("StudentInfo_AddNewInfo_toAddStudentInfoPage",'添加新生信息');
	}
	
	//编辑
	function toSEditStudentInfo(){
		var rows = $('#schoolAduitList').datagrid('getSelections');
   		if (rows.length == 1) {
   			openTab("StudentInfo_EditStuInfo_toEditStudentInfoPage?id="+rows[0].id,'编辑新生信息');
   		} else if (rows.length > 1) {
   			 $.messager.alert('提示', '一次只能编辑一条信息！', 'error');
   		} else {
   			 $.messager.alert('提示', '请勾选要编辑的信息！', 'error');
   		}
	}
	
	//删除
	function toSDeleteStudentInfo(){
		var rows = $('#schoolAduitList').datagrid('getSelections');
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
                      		$('#schoolAduitList').datagrid('uncheckAll');
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
	
	//学校审核信息（通过）
	function toSAduit(){
		var applyRows=$('#schoolAduitList').datagrid('getSelections');
		if(applyRows.length==0){
			$.messager.alert('提示', '请勾选审核的学生信息！', 'info');
   			return;
		}else if(applyRows.length==1){
			if(applyRows[0].schoolAduit=="通过"||applyRows[0].schoolAduit=="不通过"){
				$.messager.alert('提示', '该信息已经完成审核', 'info');
	   			return;
			}else{
				$.messager.confirm('请确认','您将对该条信息进行审核，按确定完善学生的基本信息',function(r){
	            	if (r){
	                	var param=applyRows[0].id;
	                	openTab("StudentInfo_AduitStuInfo_toDoSchoolAduit.action?id="+param,'完善学生信息');
	                }
				});
			}
		}else{
			//学校审核只能一条一条审核，因为要设置学号等信息
			$.messager.alert('提示', '学校审核一次只能审核一条学生记录', 'info');
   			return ;
		}
	}
	
	//学校审核信息（不通过）
	function toSCancelAduit(){
		var applyRows=$('#schoolAduitList').datagrid('getSelections');
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
                   		//只有审核中的状态才能改变审核状态
                   		if(applyRows[i].schoolAduit!="审核中"){
                   			$.messager.alert('提示', '勾选信息中有已经审核的信息，不需要重复审核', 'warning');
                   			return ;
                   		}
                   		params=params+applyRows[i].id;
                		if(!(i==applyRows.length-1)){
                			params=params+",";
                		}
                   	}
                  	//发送请求到action
                   	var url="StudentInfo_AduitStuInfo_toDoCancelSchoolAduit.action";
   					//alert(url);
                   	$.post(url, {idList:params},
                   	function(data){
                    	if (data.success){
                    		//$('#schoolAduitList').datagrid('uncheckAll');
                       		$.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '审核成功',
                            });
                            $('#schoolAduitList').datagrid('reload');    // reload the user data
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
	
	
</script>
