<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="exportRecInfosDiv" style="position:relative;padding-top: 40px" align="center">
   	<s:form id="exportRecInfosForm" action="DataManagement_ExportRecInfos_exportRecInfos.action" method="post" namespace="/" enctype="multipart/form-data" theme="simple">
   		<table cellpadding="7">
			<tr>
				<td>招聘公司：</td>
				<td>
					<s:select list="#request.recUnits" listKey="id" listValue="unitName" headerKey="---请选择公司---" headerValue="---请选择公司---" 
						cssClass="easyui-combobox" editable="false" 
						id="recUnitSelected" name="recUnitSelected" style="width: 180px"
						data-options="panelWidth:'160px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
			<tr>
				<td>发布时间：</td>
				<td>
					<s:select list="#{1:'一天内',2:'三天内',3:'一周内',4:'一个月内'}" listKey="key" listValue="value" headerKey="---选择发布时间---" headerValue="---选择发布时间---" 
						cssClass="easyui-combobox" editable="false" 
						id="timeSelected" name="timeSelected" style="width: 150px"
						data-options="panelWidth:'130px', panelHeight:'auto'"></s:select>
				</td>
			</tr>
		</table>
		<a href="javascript:void(0)" id="exportRecInfosSubmit" class="easyui-linkbutton" iconCls="icon-save">导出</a>
   	</s:form>
</div>

<script type="text/javascript">
	$('#exportRecInfosForm').find('#exportRecInfosSubmit').click(function(){
		$('#exportRecInfosForm').submit();
	});
</script>