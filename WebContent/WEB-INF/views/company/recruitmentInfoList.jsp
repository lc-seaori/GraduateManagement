<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div style="width: 99%; height: auto">
	<div id="recruitmentInfoListToolbar" style="padding: 5px; height: auto">	
			<td>招聘职位：</td>
			<td><input class="easyui-validatebox textbox" id="position"
				name="position" 
				style="width: 100px; height: 13px; padding: 5px" />
			</td>
			&nbsp;
			<td>公司名称：</td>
			<td><input class="easyui-validatebox textbox" id="unitName"
				name="unitName" 
				style="width: 100px; height: 13px; padding: 5px" />
			</td> 
			&nbsp;
			<td>工作性质：</td>
			<td><input class="easyui-validatebox textbox" id="workTy"
				name="workTy" 
				style="width: 100px; height: 13px; padding: 5px" />
			</td> 
			&nbsp;
			<td>行业领域：</td>
			<td><input class="easyui-validatebox textbox" id="industryTy"
				name="industryTy" 
				style="width: 100px; height: 13px; padding: 5px" />
			</td> 
			&nbsp;
			<td>学历要求：</td>
			<td><input class="easyui-validatebox textbox" id="educationTy"
				name="educationTy" 
				style="width: 100px; height: 13px; padding: 5px" />
			</td> 
			
			<button href="#" class="easyui-linkbutton" plain="true" 
				onclick="getRecruitmentInfosByLimit('#recruitmentInfoListToolbar', '#recruitmentInfoList')" 
				iconCls="icon-search">查询</button>
	</div>
	<table id="recruitmentInfoList" class="easyui-datagrid" style="width: auto;"
		pagination="true" url="RecruitmentInfo_List_getRecruitmentInfoList.action" toolbar="#recruitmentInfoOperToolbar" 
		rownumbers="true" data-options="pageSize:10,loadMsg:'数据加载中,请稍后...',idField :'id',sortName:'position',sortOrder:'desc'">
		<thead>
			<tr>
				<th field="id" width="100" align="center" checkbox="true">id</th>
				<th field="position" width="100" align="center" sortable="true">招聘职位</th>
				<th field="unitName" width="200" align="center">招聘公司</th>
				<th field="monthlySalary" width="90" align="center">月薪</th>
				<th field="hireCount" width="80" align="center">招聘人数</th>
				<th field="workType" width="70" align="center">工作性质</th>
				<th field="industryType" width="100" align="center">行业领域</th>
				<th field="educationType" width="70" align="center">学历要求</th>
				<th field="releaseTime" width="120" align="center">发布时间</th>
				<th field="endTime" width="120" align="center">截止时间</th>
				<th field="positionDescription" width="800" align="center">职位描述</th>
			</tr>
		</thead>
	</table>
	
    <div id="recruitmentInfoOperToolbar">
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="toAddRecruitmentInfo()">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="toEditRecruitmentInfo()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-clear" plain="true" onclick="toDeleteRecruitmentInfo()">删除</a>
    </div>
</div>
<script type="text/javascript">

	/* $(function(){
		var position=$("#recruitmentInfoListToolbar").find("#position").val().trim();
		var unitName=$("#recruitmentInfoListToolbar").find("#unitName").val().trim();
		var workTy=$("#recruitmentInfoListToolbar").find("#workTy").val().trim();
		var industryTy=$("#recruitmentInfoListToolbar").find("#industryTy").val().trim();
		var educationTy=$("#recruitmentInfoListToolbar").find("#educationTy").val().trim();
		$('#recruitmentInfoList').datagrid({
			queryParams: {
				position: position,
				unitName: unitName,
				workTy: workTy,
				industryTy: industryTy,
				educationTy: educationTy
			}
		});
	}); */
	
	function getRecruitmentInfosByLimit(toolbar,datagrid){
		//解决浏览器不兼容tirm()方法，如ie8以下不兼容trim()，该方法去掉前后空格
		if(!String.prototype.trim) {
			String.prototype.trim = function () {
		    	return this.replace(/^\s+|\s+$/g,'');
		  	};
		}
		var position=$("#recruitmentInfoListToolbar").find("#position").val().trim();
		var unitName=$("#recruitmentInfoListToolbar").find("#unitName").val().trim();
		var workTy=$("#recruitmentInfoListToolbar").find("#workTy").val().trim();
		var industryTy=$("#recruitmentInfoListToolbar").find("#industryTy").val().trim();
		var educationTy=$("#recruitmentInfoListToolbar").find("#educationTy").val().trim();
		$('#recruitmentInfoList').datagrid({
			queryParams: {
				position: position,
				unitName: unitName,
				workTy: workTy,
				industryTy: industryTy,
				educationTy: educationTy
			}
		});
		
		//再次发送请求
		$.post('RecruitmentInfo_List_getRecruitmentInfoList.action',{
			position: position,
			unitName:  unitName,
			workTy: workTy,
			industryTy: industryTy,
			educationTy: educationTy
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
	function toAddRecruitmentInfo(){
		openTab("RecruitmentInfo_Add_toAddRecruitmentInfoPage.action",'添加招聘信息');
	}
	
	
	//编辑
	function toEditRecruitmentInfo(){
		var rows = $('#recruitmentInfoList').datagrid('getSelections');
   		if (rows.length == 1) {
   			openTab("RecruitmentInfo_Eidt_toEditRecruitmentInfoPage.action?recrId="+rows[0].id,'就业信息编辑');
   		} else if (rows.length > 1) {
   			 $.messager.alert('提示', '一次只能编辑一条信息！', 'error');
   		} else {
   			 $.messager.alert('提示', '请勾选要编辑的信息！', 'error');
   		}
	}
	
	//删除
	function toDeleteRecruitmentInfo(){
		var rows = $('#recruitmentInfoList').datagrid('getSelections');
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
   					var url="RecruitmentInfo_Del_toDelRecruitmentInfo.action";
   					//alert(url);
                   	$.post(url, {idList:params},
                   	function(data){
                    	if (data.success){
                      		$('#recruitmentInfoList').datagrid('uncheckAll');
                            $.messager.show({    // show error message
                            	title: 'Tips',
                                msg: '删除记录成功'
                            });
                            $('#recruitmentInfoList').datagrid('reload');    // reload the user data
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
