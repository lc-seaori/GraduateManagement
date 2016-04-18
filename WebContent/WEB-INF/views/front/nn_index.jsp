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
<title>新闻列表</title>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/front/main.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/front/common.css">
<script type="text/javascript" src="<%=contextPath%>/js/jquery.min.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/js/global.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/pagenav.js"></script>
<%-- <script type="text/javascript" src="${BASE}/www/lib/showDialog/showDialog.js"></script> --%>
<script type="text/javascript" src="<%=contextPath%>/js/front/nn_index.js"></script>
<script type="text/javascript">
$(function(){
	var idVal = $('#dl-query1 dd[class="cur"]').attr("id");
	if(idVal.replace('type-','')=='0'){
		window.coursePageList(1,'','create_time');
	}else{
		window.coursePageList(1 ,idVal.replace('type-','') ,'create_time');
	}
});
</script>
</head>
<body>
<%@include file="top2.jsp" %>
<div class="main">
  	<div class="t3"><div class="p_nav">首页<i>&gt;</i>课程</div><span>课程</span></div>
    <div class="filter">
    	<dl id="dl-query1" class="f1">
    		<dt>课程分类：</dt>
    		<c:choose>
    		<c:when test="${empty groupId}">
    			<dd id="type-0" class="cur" onclick="selectquerytype(0)">全部新闻</dd>
    			<c:forEach items="${groupList}" var="group">
	    			<dd id="type-${group.groupId}" onclick="selectquerytype('${group.groupId}')">${group.groupName}</dd>
	    		</c:forEach>
    		</c:when>
    		<c:otherwise>
    			<dd id="type-0" onclick="selectquerytype(0)">全部新闻</dd>
    			<c:forEach items="${groupList}" var="group">
	    			<dd id="type-${group.groupId}" <c:if test="${group.groupId == groupId}">class="cur"</c:if> onclick="selectquerytype('${group.groupId}')">${group.groupName}</dd>
	    		</c:forEach>
	    	</c:otherwise>
    		</c:choose>
    	</dl>
    	<dl id="dl-query2" class="f1">
    		<dt>课程排序：</dt>
    		<dd id="sort-create_time" class="cur" onclick="selectquerysort('create_time')">最新发布</dd>
    		<dd id="sort-reg_count" onclick="selectquerysort('reg_count')">最多报名</dd>
    	</dl>
    </div>
    <div id="course_list"></div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
