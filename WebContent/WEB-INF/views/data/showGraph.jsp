<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="showGraphDiv" style="position:relative;padding-top: 100px;width: 500px;float: left;" align="center">
   	<s:form id="showGraphForm" action="DataManagement_ExportGraInfos_exportGraInfos.action" method="post" namespace="/" enctype="multipart/form-data" theme="simple">
   		<p>温馨提示： 如果不选择学院，将会生成所有学院的毕业率或就业率图</p>
   		<p>&nbsp;&nbsp;&nbsp;&nbsp;选择学院，将会生成该学院所有专业的毕业率或就业率图</p>
   		<p>&nbsp;&nbsp;&nbsp;&nbsp;选择专业，将会生成该专业所有班别的毕业率或就业率图</p>
   		<p>&nbsp;&nbsp;&nbsp;毕业时间精确到年份</p>
   		<table cellpadding="7">
   			<tr>
				<td>毕业时间：</td>
				<td>
					<input class="easyui-datebox" id="graTime" name="graTime" 
					data-options="required:true" style="width:130px; height: 25px; padding: 5px" editable="false"></input>
				</td>
			</tr>
			<tr>
				<td>学院：</td>
				<td>
					<s:select list="#request.department" listKey="id" listValue="name" headerKey="---请选择学院---" headerValue="---请选择学院---" 
						cssClass="easyui-combobox" editable="false" 
						id="departmentSelected" name="departmentSelected" style="width: 150px"
						data-options="panelWidth:'130px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
			<tr>
				<td>专业：</td>
				<td>
					<select class="easyui-combobox" editable="false" 
						id="majorFieldSelected" name="majorFieldSelected" style="width: 150px"
						data-options="panelWidth:'130px', panelHeight:'auto'">
						<option value="---请选择专业---">---请选择专业---</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>图类型：</td>
				<td>
					<s:select list="#{1:'饼图',2:'柱状图',3:'折线图'}" listKey="key" listValue="value" headerKey="0" headerValue="---请选择图形---" 
						cssClass="easyui-combobox" editable="false" 
						id="graphSelected" name="graphSelected" style="width: 150px"
						data-options="panelWidth:'130px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
		</table>
		<a href="javascript:void(0)" id="showGraGraphSubmit" class="easyui-linkbutton" iconCls="icon-large-chart">生成毕业率图</a>
		<a href="javascript:void(0)" id="showWorkGraphSubmit" class="easyui-linkbutton" iconCls="icon-large-chart">生成就业率图</a>
   	</s:form>
</div>

<div id="imgDiv" style="height:400px;padding-top:20px;display: none;padding-left: 10px;">
	<h2 style="color:#0099FF;font-size: 20px;">报表统计</h2>
    <img alt="记载中...." style="display: none;padding-top: 30px;" id="graImg" src=""></img>
</div>

<script type="text/javascript">
	//获取专业
	$(function(){
		var tcSelect = $('#showGraphForm').find('#departmentSelected');
		tcSelect.combobox({
			onSelect : function() {
				var departmentId = tcSelect.combobox('getValue');
				if(departmentId!="---请选择学院---"&&departmentId!=null){
					$('#showGraphForm').find('#majorFieldSelected').combobox({
						valueField : "id",
						textField : "name",
						url : "StudentInfo_AddNewInfo_toGetMajorField.action?departmentId=" + departmentId,
						editable : false,
						value : "---请选择专业---"
					});		
				}else{
					//清空combobox
					$('#showGraphForm').find('#majorFieldSelected').combobox('loadData', {});
					$('#showGraphForm').find('#majorFieldSelected').combobox('setValue', '---请选择专业---');
				}
			}
		});
	});
	
	$('#showGraphForm').find('#showGraGraphSubmit').click(function(){
		//$('#exportGraInfosForm').submit();
		//先清空原先img的值
		$('#graImg').attr('src','');
		//取得所有控件的值
		var graTime=$('#showGraphForm').find('#graTime').combobox('getValue');
		//alert(graTime);
		var departmentId=$('#showGraphForm').find('#departmentSelected').combobox('getValue');
		var majorId=$('#showGraphForm').find('#majorFieldSelected').combobox('getValue');
		var graphType=$('#showGraphForm').find('#graphSelected').combobox('getValue');
		
		//错误操作判断与提醒
		if(graTime==''){
			$.messager.alert("错误提示", "请选择时间","info");
			return ;
		}
		if(graphType=='0'){
			$.messager.alert("错误提示", "请选择生成图的类型","info");
			return ;
		}
		if(departmentId=='---请选择学院---'){
			departmentId=0;
		}
		if(majorId=='---请选择专业---'){
			majorId=0;
		}
		//打开panel并且对img标签的src赋值
		$('#graImg').attr('src','DataManagement_Graph_showGraph.action?graTime='+graTime+'&departmentId='+departmentId+'&majorId='+majorId+'&graphType='+graphType+'&type=1');
		$('#imgDiv').show();
		$('#graImg').show();
		$.messager.progress('close'); 
	});
	
	$('#showGraphForm').find('#showWorkGraphSubmit').click(function(){
		//$('#exportGraInfosForm').submit();
		//先清空原先img的值
		$('#graImg').attr('src','');
		//取得所有控件的值
		var graTime=$('#showGraphForm').find('#graTime').combobox('getValue');
		//alert(graTime);
		var departmentId=$('#showGraphForm').find('#departmentSelected').combobox('getValue');
		var majorId=$('#showGraphForm').find('#majorFieldSelected').combobox('getValue');
		var graphType=$('#showGraphForm').find('#graphSelected').combobox('getValue');
		
		//错误操作判断与提醒
		if(graTime==''){
			$.messager.alert("错误提示", "请选择时间","info");
			return ;
		}
		if(graphType=='0'){
			$.messager.alert("错误提示", "请选择生成图的类型","info");
			return ;
		}
		if(departmentId=='---请选择学院---'){
			departmentId=0;
		}
		if(majorId=='---请选择专业---'){
			majorId=0;
		}
		//打开panel并且对img标签的src赋值
		$('#graImg').attr('src','DataManagement_Graph_showGraph.action?graTime='+graTime+'&departmentId='+departmentId+'&majorId='+majorId+'&graphType='+graphType+'&type=2');
		$('#imgDiv').show();
		$('#graImg').show();
		$.messager.progress('close'); 
	});
	
</script>