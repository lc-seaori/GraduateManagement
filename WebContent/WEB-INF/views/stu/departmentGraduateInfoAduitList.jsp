<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div style="width: 99%; height: auto">
	<div id="departmentGAduitListToolbar" style="padding: 5px; height: auto">	
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
				<a style="height: 25px" class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#departmentGAduitListToolbar').find('#graduateTime').combobox('clear');" >清空</a>
			</td>
			
			<button href="#" class="easyui-linkbutton" plain="true" 
				onclick="getDepartmentGAduitListByLimit('#departmentGAduitListToolbar', '#departmentGAduitList')"
				iconCls="icon-search">查询</button>
	</div>
	<table id="departmentGAduitList" class="easyui-datagrid" style="width: auto;"
		pagination="true" url="GraduateInfo_List_getDepartmentAduitList.action" toolbar="#departmentGAduitListOperToolbar" 
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
	
    <div id="departmentGAduitListOperToolbar">
    	&nbsp;&nbsp;<span>选择学生信息：</span><input id="studentSelected" class="easyui-combogrid" style="width:120px" data-options="
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
            fitColumns: true
        ">
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toDAddNewGraduateInfo()">添加</a>
        <br/>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toDEditGraduateInfo()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="toDDeleteGraduateInfo()">删除</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-redo" plain="true" onclick="toDGAduit()">审核批准</a>
   		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="toDCancelGAduit()">审核不批准</a>
   		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-filter" plain="true" onclick="toDApplySchoolGAduitGra()">申请学校审核</a>
   		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-filter" plain="true" onclick="toDCancelApplySchoolAduitGra()">取消申请学校审核</a>
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
	
	function getDepartmentGAduitListByLimit(toolbar,datagrid){
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
		
		$('#departmentGAduitList').datagrid({
			queryParams: {
				code: code,
				name: name,
				idCard: idCard,
				departmentName: departmentName,
				graduateTime: graduateTime
			}
		});
		
		//再次发送请求
		$.post('GraduateInfo_List_getDepartmentAduitList.action',{
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
	function toDAddNewGraduateInfo(){
		var stuId=$('#departmentGAduitListOperToolbar').find('#studentSelected').combogrid('getValue');
		if(stuId==""){
			$.messager.alert('提示', '请先在左方下拉列表中选择将添加就业信息的学生', 'warning');
		}else{
			openTab("GraduateInfo_Add_toAddGraduateInfoPage?stuId="+stuId,'添加学生就业信息');
		}
	}
	 
	//编辑
	function toDEditGraduateInfo(){
		var rows = $('#departmentGAduitList').datagrid('getSelections');
   		if (rows.length == 1) {
   			openTab("GraduateInfo_Edit_toEditGraduateInfoPage?id="+rows[0].id,'学生就业信息编辑');
   		} else if (rows.length > 1) {
   			 $.messager.alert('提示', '一次只能编辑一条信息！', 'error');
   		} else {
   			 $.messager.alert('提示', '请勾选要编辑的信息！', 'error');
   		}
	}
	
	//删除
	function toDDeleteGraduateInfo(){
		var rows = $('#departmentGAduitList').datagrid('getSelections');
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
                      		$('#departmentGAduitList').datagrid('uncheckAll');
                            $.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '删除记录成功'
                            });
                            $('#departmentGAduitListOperToolbar').find('#studentSelected').combogrid('grid').datagrid('load');
                            $('#departmentGAduitList').datagrid('reload');    // reload the user data
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
	function toDGAduit(){
		var applyRows=$('#departmentGAduitList').datagrid('getSelections');
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
                   		if(applyRows[i].departmentGAduit=="通过"){
                   			$.messager.alert('提示', '勾选信息中有已通过院系审核的信息，不用再重复审核', 'warning');
                   			return ;
                   		}
                   		params=params+applyRows[i].id;
                		if(!(i==applyRows.length-1)){
                			params=params+",";
                		}
                   	}
                  	//发送请求到action
                   	var url="GraduateInfo_Aduit_toDoDepartmentGAduit.action";
   					//alert(url);
                   	$.post(url, {idList:params},
                   	function(data){
                    	if (data.success){
                    		//$('#departmentAduitList').datagrid('uncheckAll');
                       		$.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '审核成功',
                            });
                            $('#departmentGAduitList').datagrid('reload');    // reload the user data
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
	function toDCancelGAduit(){
		var applyRows=$('#departmentGAduitList').datagrid('getSelections');
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
                   		
                   		if(applyRows[i].schoolGAduit=="通过"){
                   			$.messager.alert('提示', '选中的学生信息已经通过学校的审核，不能再次审核', 'info');
                   			return ;
                   		}
                   		//没通过申请的不用设置不通过
                   		if(applyRows[i].departmentGAduit=="不通过"){
                   			$.messager.alert('提示', '勾选信息中有不通过院系审核的信息，不用再重复审核', 'warning');
                   			return ;
                   		}
                   		params=params+applyRows[i].id;
                		if(!(i==applyRows.length-1)){
                			params=params+",";
                		}
                   	}
                  	//发送请求到action
                   	var url="GraduateInfo_Aduit_toDoCancelDepartmentGAduit.action";
   					//alert(url);
                   	$.post(url, {idList:params},
                   	function(data){
                    	if (data.success){
                    		//$('#departmentAduitList').datagrid('uncheckAll');
                       		$.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '审核成功',
                            });
                            $('#departmentGAduitList').datagrid('reload');    // reload the user data
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
	function toDApplySchoolGAduitGra(){
		var applyRows=$('#departmentGAduitList').datagrid('getSelections');
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
                   		if(applyRows[i].departmentGAduit!="通过"){
                   			$.messager.alert('提示', '申请学校审批，必须先通过院系审批', 'warning');
                   			return ;
                   		}else if(applyRows[i].schoolGAduit=="审核中"){
                   			$.messager.alert('提示', '勾选的信息中有在【审核中】的信息，请重选选择', 'warning');
                   			return ;
                   		}else if(applyRows[i].schoolGAduit=="通过"){
                   			$.messager.alert('提示', '勾选的信息中有已通过审核的信息，请重选选择', 'warning');
                   			return ;
                   		}
                		params=params+applyRows[i].id;
                		if(!(i==applyRows.length-1)){
                			params=params+",";
                		}
                    }
                   	//发送请求到action
                   	var url="GraduateInfo_Aduit_toDoApplySchoolGAduit.action";
   					//alert(url);
                   	$.post(url, {idList:params},
                   	function(data){
                    	if (data.success){
                       		$.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '申请成功，等待学校审核'
                            });
                            $('#departmentGAduitList').datagrid('reload');    // reload the user data
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
	function toDCancelApplySchoolAduitGra(){
		var applyRows=$('#departmentGAduitList').datagrid('getSelections');
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
                   		if(applyRows[i].schoolGAduit=="未审核"){
                   			$.messager.alert('提示', '勾选的信息中有未提交审核的信息，请重选选择', 'warning');
                   			return ;
                   		}else if(applyRows[i].schoolGAduit=="通过"){
                   			$.messager.alert('提示', '勾选的信息中有已通过审核的信息，请重选选择', 'warning');
                   			return ;
                   		}
                		params=params+applyRows[i].id;
                		if(!(i==applyRows.length-1)){
                			params=params+",";
                		}
                    }
                   	//发送请求到action
                   	var url="GraduateInfo_Aduit_toDoCancelApplySchoolGAduit.action";
   					//alert(url);
                   	$.post(url, {idList:params},
                   	function(data){
                    	if (data.success){
                       		$.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '取消审核申请成功'
                            });
                            $('#departmentGAduitList').datagrid('reload');    // reload the user data
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
