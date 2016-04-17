<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="exportGraInfosDiv" style="position:relative;padding-top: 40px" align="center">
   	<s:form id="exportGraInfosForm" action="DataManagement_ExportGraInfos_exportGraInfos.action" method="post" namespace="/" enctype="multipart/form-data" theme="simple">
   		<table cellpadding="7">
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
				<td>班别：</td>
				<td>
					<select class="easyui-combobox" editable="false" 
						id="clazzSelected" name="clazzSelected" style="width: 150px"
						data-options="panelWidth:'130px', panelHeight:'auto'">
						<option value="---请选择班别---">---请选择班别---</option>
					</select>
				</td>
			</tr>
		</table>
		<a href="javascript:void(0)" id="exportGraInfosSubmit" class="easyui-linkbutton" iconCls="icon-save">导出</a>
   	</s:form>
</div>

<script type="text/javascript">
	//为了不用重写，直接是使用之前action
	//获取专业
	$(function(){
		var tcSelect = $('#exportGraInfosForm').find('#departmentSelected');
		tcSelect.combobox({
			onSelect : function() {
				var departmentId = tcSelect.combobox('getValue');
				if(departmentId!="---请选择学院---"&&departmentId!=null){
					$('#exportGraInfosForm').find('#majorFieldSelected').combobox({
						valueField : "id",
						textField : "name",
						url : "StudentInfo_AddNewInfo_toGetMajorField.action?departmentId=" + departmentId,
						editable : false,
						value : "---请选择专业---"
					});		
				}else{
					//清空combobox
					$('#exportGraInfosForm').find('#majorFieldSelected').combobox('loadData', {});
					$('#exportGraInfosForm').find('#majorFieldSelected').combobox('setValue', '---请选择专业---');
					$('#exportGraInfosForm').find('#clazzSelected').combobox('loadData', {});
					$('#exportGraInfosForm').find('#clazzSelected').combobox('setValue', '---请选择班别---');
				}
			}
		});
		
		//根据专业id获取所有班级
		var mfSelect = $('#exportGraInfosForm').find('#majorFieldSelected');
		mfSelect.combobox({
			onSelect : function() {
				var majorId = mfSelect.combobox('getValue');
				if(majorId!="---请选择专业---"&&majorId!=null){
					$('#exportGraInfosForm').find('#clazzSelected').combobox({
						valueField : "id",
						textField : "name",
						url : "StudentInfo_AddNewInfo_toGetClazz.action?majorId=" + majorId,
						editable : false,
						value : "---请选择班别---"
					});		
				}else{
					//清空combobox
					$('#exportGraInfosForm').find('#clazzSelected').combobox('loadData', {});
					$('#exportGraInfosForm').find('#clazzSelected').combobox('setValue', '---请选择班别---');
				}
			}
		});
	});
	
	$('#exportGraInfosForm').find('#exportGraInfosSubmit').click(function(){
		$('#exportGraInfosForm').submit();
	});
</script>