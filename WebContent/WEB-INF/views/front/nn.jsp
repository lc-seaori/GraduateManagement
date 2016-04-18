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
<script type="text/javascript" src="<%=contextPath%>/js/jquery.jslides.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/js/global.js"></script>
<script type="text/javascript" src="<%=contextPath%>/lib/jquery-finger/js/min/modernizr-custom-v2.7.1.min.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/lib/jquery-finger/js/min/jquery-finger-v0.1.0.min.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/lib/jquery-finger/js/min/flickerplate.min.js"></script> 
<script type="text/javascript">
	$(function(){
		$('#flicker-ybolo').flicker();
	});
</script>
<style type="text/css">
	.nn_tool{ float:right;}
	.nn_tool li { height:24px; line-height:24px; float:right; margin-left:10px; cursor:pointer;}
	.nn_tool li i{ float:left; width:24px; height:24px;background-image:url(<%=contextPath%>/css/images/enterprise/icon.gif); margin-right:6px; display:inline;}
	.video-comments a{color: #999;}
	.video-comments a:hover{color: #333;}
	.video-comments a:visited{color: #999;}
	.news_summary{background-color: #f3f3f3; padding: 20px; margin-top: 10px;}
	.news_summary span{font-weight: bold; font-size: 14px;}
</style>
</head>
<body>
</body>
<%@ include file="top.jsp" %>
<div class="main">
  	<div class="t2"><div class="p_nav">首页<i>&gt;</i>学院新闻<i>&gt;</i>正文</div><span>学院新闻<i>&gt;</i>正文</span></div>
  	<div>
    	<h1>${nn.title }</h1>
        <div class="author"><i>${nn.createName }</i> 发表于 ${nn.createTime } &nbsp;&nbsp; 关键字：${nn.keyWords } &nbsp;&nbsp; ${nn.viewCount }次浏览
        <ul class="nn_tool">
    		<li class="video-comments"><a href="javascript:void(0)"><i class="i1"></i>评论（0）</a></li>
    	</ul>
        </div>
        <div class="news_summary"><span>[摘要]</span>${nn.summary}</div>
        <div class="news_txt">${nn.content}</div>
    </div>
</div>
<%@ include file="footer.jsp" %>
</html>