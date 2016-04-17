<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div style="width: 99%; height: auto">
	<div id="graduateInfoToolbar" style="padding: 5px; height: auto">	
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
			<td>身份证号码:</td>
			<td><input class="easyui-validatebox textbox" id="idCard"
				name="idCard" 
				style="width: 100px; height: 13px; padding: 5px" />
			</td> 
			&nbsp;
			<td>学院：</td>
			<td><input class="easyui-validatebox textbox" id="departmentName"
				name="departmentName" 
				style="width: 100px; height: 13px; padding: 5px" />
			</td> 
			&nbsp;
			<td>毕业年月:</td>
			<td><input class="easyui-datebox" id="graduateTime" 
				name="graduateTime" editable="false" 
				style="width: 140px; height: 25px; padding: 5px" />
				<a style="height: 25px" class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#graduateInfoToolbar').find('#graduateTime').combobox('clear');" >清空</a>
			</td>
			
			<button href="#" class="easyui-linkbutton" plain="true" 
				onclick="getGraduateInfosByLimit('#graduateInfoToolbar', '#graduateInfoList')" 
				iconCls="icon-search">查询</button>
	</div>
	<table id="graduateInfoList" class="easyui-datagrid" style="width: auto;"
		pagination="true" url="GraduateInfo_List_getGraduateInfoList.action" toolbar="#graduateInfoOperToolbar" 
		rownumbers="true" data-options="pageSize:10,loadMsg:'数据加载中,请稍后...',idField :'id',sortName:'name',sortOrder:'desc'" >
		<thead>
			<tr>
				<th field="id" width="100" align="center" checkbox="true">id</th>
				<th field="departmentGAduit" width="70" align="center">院系审核</th>
				<th field="schoolGAduit" width="70" align="center">学校审核</th>
				<th field="name" width="70" align="center" sortable="true">姓名</th>
				<th field="unitName" width="200" align="center">就业单位名称</th>
				<th field="unitLocation" width="110" align="center">就业单位所在地</th>
				<th field="unitTy" width="120" align="center">单位性质</th>
				<th field="unitIndustry" width="140" align="center">就业单位行业</th>
				<th field="unitContantPerson" width="70" align="center">单位联系人</th>
				<th field="unitContantPhone" width="120" align="center">单位联系电话</th>
				<th field="graduatePhone" width="120" align="center">毕业生联系方式</th>
				<th field="applyTy" width="100" align="center">应聘方式</th>
				<th field="unitPostEncoding" width="80" align="center">单位邮政编码</th>
				<th id="unitAddress" field="unitAddress" width="400" align="center">单位详细地址</th>
			</tr>
		</thead>
	</table>
	
    <div id="graduateInfoOperToolbar">
	     &nbsp;&nbsp;
	     <span>选择学生信息：</span><input id="studentSelected" class="easyui-combogrid" style="width:120px" data-options="
            panelWidth: 800,
            idField: 'id',
            textField: 'name',
            url: 'Student_getAllStudents.action',
            method: 'get',
            columns: [[
                {field:'id',title:'编号',width:30,align:'center'},
                {field:'name',title:'学生名字',width:80,align:'center'},
                {field:'code',title:'学号',width:100,align:'center'},
                {field:'schoolStatus',title:'学籍状态',width:60,align:'center'},
                {field:'departmentName',title:'学院名字',width:120,align:'center'},
                {field:'clazzName',title:'班别',width:80,align:'center'},
                {field:'graduationTime',title:'毕业时间',width:120,align:'center'}
            ]],
            fitColumns: true">
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toAddNewGraduateInfo()">添加</a>&nbsp;&nbsp;
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toStudentList()">精确添加</a>
        <br/>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toEditGraduateInfo()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="toDeleteGraduateInfo()">删除</a>
   		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="toApplyDepartmentAduitGra()">申请院系审核</a>
   		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="toCancelApplyDepartmentAduitGra()">取消申请院系审核</a>
    </div>
</div>
<script type="text/javascript">

	$(function(){
		//温馨提示
		$('#graduateInfoToolbar').find('#graduateTime').datebox({
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
		var code=$("#graduateInfoToolbar").find("#code").val().trim();
		var name=$("#graduateInfoToolbar").find("#name").val().trim();
		var idCard=$("#graduateInfoToolbar").find("#idCard").val().trim();
		var departmentName=$("#graduateInfoToolbar").find("#departmentName").val().trim();
		var graduateTime=$("#graduateInfoToolbar").find('#graduateTime').combobox('getValue');
		$('#graduateInfoList').datagrid({
			queryParams: {
				code: code,
				name: name,
				idCard: idCard,
				departmentName: departmentName,
				graduateTime: graduateTime
			}
		});
	}); */
	
	function getGraduateInfosByLimit(toolbar,datagrid){
		//解决浏览器不兼容tirm()方法，如ie8以下不兼容trim()，该方法去掉前后空格
		if(!String.prototype.trim) {
			String.prototype.trim = function () {
		    	return this.replace(/^\s+|\s+$/g,'');
		  	};
		}
		
		var code=$(toolbar).find("#code").val().trim();
		var name=$(toolbar).find("#name").val().trim();
		var idCard=$(toolbar).find("#idCard").val().trim();
		var departmentName=$(toolbar).find("#departmentName").val().trim();
		var graduateTime=$(toolbar).find('#graduateTime').combobox('getValue');
		
		$('#graduateInfoList').datagrid({
			queryParams: {
				code: code,
				name: name,
				idCard: idCard,
				departmentName: departmentName,
				graduateTime: graduateTime
			}
		});
		
		//再次发送请求
		$.post('GraduateInfo_List_getGraduateInfoList.action',{
			code: code,
			name:  name,
			idCard: idCard,
			departmentName: departmentName,
			graduateTime: graduateTime
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
	function toAddNewGraduateInfo(){
		var stuId=$('#graduateInfoOperToolbar').find('#studentSelected').combogrid('getValue');
		if(stuId==""){
			$.messager.alert('提示', '请先在左方下拉列表中选择将添加就业信息的学生', 'warning');
		}else{
			openTab("GraduateInfo_Add_toAddGraduateInfoPage.action?stuId="+stuId,'添加学生就业信息');
		}
		//alert(test);
	}
	
	//精确添加（进入学生列表页面，选中后再进行添加）
	function toStudentList(){
		openTab("GraduateInfo_Add_toStudentListPage.action",'学生信息');
	}
	
	//编辑
	function toEditGraduateInfo(){
		var rows = $('#graduateInfoList').datagrid('getSelections');
   		if (rows.length == 1) {
   			openTab("GraduateInfo_Edit_toEditGraduateInfoPage.action?id="+rows[0].id,'就业信息编辑');
   		} else if (rows.length > 1) {
   			 $.messager.alert('提示', '一次只能编辑一条信息！', 'error');
   		} else {
   			 $.messager.alert('提示', '请勾选要编辑的信息！', 'error');
   		}
	}

	//删除
	function toDeleteGraduateInfo(){
		var rows = $('#graduateInfoList').datagrid('getSelections');
		if(rows.length==0){
			$.messager.alert('提示', '请勾选要删除的信息！', 'info');
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
   					var url="GraduateInfo_Del_toDelGraduateInfo.action";
   					//alert(url);
                   	$.post(url, {idList:params},
                   	function(data){
                    	if (data.success){
                      		$('#graduateInfoList').datagrid('uncheckAll');
                            $.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '删除记录成功'
                            });
                            $('#graduateInfoOperToolbar').find('#studentSelected').combogrid('grid').datagrid('load');
                            $('#graduateInfoList').datagrid('reload');    // reload the user data
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
	
	//申请院系审核
	function toApplyDepartmentAduitGra(){
		var applyRows=$('#graduateInfoList').datagrid('getSelections');
		if(applyRows.length==0){
			$.messager.alert('提示', '请勾选申请院系审核的学生就业信息！', 'info');
   			return;
		}else{
			var length=applyRows.length;
			$.messager.confirm('请确认','您确定将'+length+'条信息提交给院系审核吗?',function(r){
            	if (r){
                	var params="";
                   	for(i=0;i<applyRows.length;i++){
                   		//判断院系申请状态是否已经在[审核中]或者已通过
                   		if(applyRows[i].departmentGAduit=="审核中"){
                   			$.messager.alert('提示', '勾选的信息中有在审核中的信息，请重选选择', 'warning');
                   			return ;
                   		}else if(applyRows[i].departmentGAduit=="通过"){
                   			$.messager.alert('提示', '勾选的信息中有已通过审核的信息，请重选选择', 'warning');
                   			return ;
                   		}
                		params=params+applyRows[i].id;
                		if(!(i==applyRows.length-1)){
                			params=params+",";
                		}
                    }
                   	//发送请求到action
                   	var url="GraduateInfo_Aduit_toDoApplyDepartmentGAduit.action";
   					//alert(url);
                   	$.post(url, {idList:params},
                   	function(data){
                    	if (data.success){
                       		$.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '申请成功，等待院系审核'
                            });
                            $('#graduateInfoList').datagrid('reload');    // reload the user data
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
	
	//取消申请院系审核
	function toCancelApplyDepartmentAduitGra(){
		var applyRows=$('#graduateInfoList').datagrid('getSelections');
		if(applyRows.length==0){
			$.messager.alert('提示', '请勾选需要取消申请院系审核的学生就业信息！', 'info');
   			return;
		}else{
			//alert(applyRows[0].departmentAduit);
			var length=applyRows.length;
			$.messager.confirm('请确认','您确定取消'+length+'条信息的院系审核吗?',function(r){
            	if (r){
                	var params="";
                   	for(i=0;i<applyRows.length;i++){
                   		//判断院系申请状态是否已经未审核或者已经通过
                   		if(applyRows[i].departmentGAduit=="未审核"){
                   			$.messager.alert('提示', '勾选的信息中有未提交审核的信息，请重选选择', 'warning');
                   			return ;
                   		}else if(applyRows[i].departmentGAduit=="通过"){
                   			$.messager.alert('提示', '勾选的信息中有已通过审核的信息，请重选选择', 'warning');
                   			return ;
                   		}
                		params=params+applyRows[i].id;
                		if(!(i==applyRows.length-1)){
                			params=params+",";
                		}
                    }
                   	//发送请求到action
                   	var url="GraduateInfo_Aduit_toDoCancelApplyDepartmentGAduit.action";
   					//alert(url);
                   	$.post(url, {idList:params},
                   	function(data){
                    	if (data.success){
                       		$.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '取消审核申请成功'
                            });
                            $('#graduateInfoList').datagrid('reload');    // reload the user data
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
