<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="global.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta  charset="UTF-8">
<meta  http-equiv="X-UA-Compatible"  content="IE=8">
<title>招聘相关</title>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/front/main.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/front/common.css">
<script type="text/javascript" src="<%=contextPath%>/js/jquery.min.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/js/global.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/pagenav.js"></script>
<%-- <script type="text/javascript" src="${BASE}/www/lib/showDialog/showDialog.js"></script> --%>
<script type="text/javascript" src="<%=contextPath%>/js/front/info_index.js"></script>
<script type="text/javascript">
$(function(){
	window.infoPageList(1);
});
</script>
</head>
<body>
<%@include file="top2.jsp" %>
<div class="main">
	<input type="hidden" id="whatType" name="whatType" value="${whatType}"/>
  	<div class="t3"><div class="p_nav">首页<i>&gt;</i>招聘相关</div><span>招聘相关</span></div>
    <div id="info_list"></div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
