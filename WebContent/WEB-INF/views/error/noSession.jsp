<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<% String contextPath = request.getContextPath(); %>
<s:hidden id="msg" value="%{#request.msg}"/>
<script src="<%=contextPath%>/js/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8">
	$(function(){
		var msg=$('#msg').val();
		$.messager.alert('Tips',msg,'info');
	});
	setTimeout("parent.location.href='${ctx}/index.jsp'",3000);
</script>