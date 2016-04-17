<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div align="center" style="padding-top: 50px;">
	<s:hidden id="msg" value="%{#request.msg}"/>
	<span style="color: #ff0000;">${msg}</span>
</div>
<%-- <script type="text/javascript" charset="utf-8">
	$(function(){
		var msg=$('#msg').val();
		$.messager.show({
            title:'权限提醒',
            msg:msg,
            timeout:5000,
            showType:'show',
            height: 200,
            width: 400
        });
	});
</script> --%>
