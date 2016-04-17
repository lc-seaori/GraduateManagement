<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="easyui-panel" style="padding: 30px 70px 50px 70px;width:500px;height:150px;" align="center">
	<s:form id="chooseTimeForm" action="DataManagement_toWorkAndGraduateListPage.action" namespace="/" method="post" theme="simple" enctype="multipart/form-data">
		<span>温馨提示：选择完毕业时间之后会查询该年份所有学院、专业和班别的毕业率以及就业率(时间精确到年份)</span>
		<table cellpadding="7">
			<tr>
				<td>毕业时间：</td>
				<td>
					<input class="easyui-datebox" id="graTime" name="graTime"
					data-options="required:true" style="width:130px; height: 25px; padding: 5px" editable="false"></input>
				</td>
			</tr>
		</table>
	</s:form>
	<div style="text-align: center; padding: 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" 
			onclick="toCalRate('#chooseTimeForm')">查询</a>
	</div>
</div>

<script type="text/javascript">
	function toCalRate(form){
		//得到用户选择的时间
		var graTime=$(form).find('#graTime').combobox('getValue');
		if(graTime==""){
			$.messager.alert("错误提示", "请选择时间","info");
			return ;
		}
		openTab('DataManagement_toWorkAndGraduateListPage.action?graTime='+graTime,'就业率与毕业率');
	}
</script>