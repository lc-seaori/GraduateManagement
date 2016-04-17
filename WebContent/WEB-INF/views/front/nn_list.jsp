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
<title>新闻公告列表</title>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/lib/jquery-finger/css/flickerplate.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/front/main.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/front/common.css">
<script type="text/javascript" src="<%=contextPath%>/js/jquery.min.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/lib/jquery-finger/js/min/modernizr-custom-v2.7.1.min.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/lib/jquery-finger/js/min/jquery-finger-v0.1.0.min.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/lib/jquery-finger/js/min/flickerplate.min.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/js/pagenav.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/global.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/front/nn_index.js"></script>
<script type="text/javascript">
$(function(){
	$('#flicker-ybolo').flicker();
	var isXj = '${isXj}';
	if(isXj == 1){
		selectblock(0,'${pidPlate.plateId}');
	}else{
		window.nnPageList(1,'','');
	}
});
</script>
</head>
<body>
</body>
<%@ include file="top2.jsp" %>
<div class="main">
 	<div class="t2">
	  	<div class="p_nav">首页<i>&gt;</i>学院新闻</div>
	  	<span>学院新闻</span>
	</div>
  	<div class="filter">
    	<dl class="f1"><dt>类型：</dt><dd id="pp-0" class="cur" onclick="selectquerytype(0)" style="cursor: pointer;">所有</dd>
    	<c:forEach items="${plateList}" var="pp" varStatus="sta">
    		<dd id="pp-${pp.plateId}" onclick="selectquerytype('${pp.plateId}')" style="cursor: pointer;">${pp.plateName}</dd>
 		</c:forEach>
 		</dl>
    	<dl class="video_sort"><dt>版块：</dt><span id="plateIdDt"></span></dl>
    </div>
  	<div class="newslist" id="nn-list">${coOrder.newsName}加载中...</div>
</div>
<%@ include file="footer.jsp" %>
</html>