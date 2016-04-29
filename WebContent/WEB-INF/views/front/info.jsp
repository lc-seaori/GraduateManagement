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
	.nn_tool li i{ float:left; width:24px; height:24px;background-image:url(<%=contextPath%>/css/images/icon.gif); margin-right:6px; display:inline;}
	.video-comments a{color: #999;}
	.video-comments a:hover{color: #333;}
	.video-comments a:visited{color: #999;}
	.news_summary{background-color: #f3f3f3; padding: 20px; margin-top: 10px;}
	.news_summary span{font-weight: bold; font-size: 14px;}
</style>
</head>
<body>
</body>
<%@ include file="top2.jsp" %>
<div class="main">
  	<div class="t2"><div class="p_nav">首页<i>&gt;</i>招聘<i>&gt;</i>正文</div><span>招聘<i>&gt;</i>正文</span></div>
  	<div>
  		<c:if test="${whatType == 'unitType'}">
  			<h1>${recru.unitName }</h1>
	        <div class="author"><i>${recru.unitContantPerson }</i> 联系方式 ${recru.unitContantPhone } &nbsp;&nbsp; 单位编号：${recru.unitCode } &nbsp;&nbsp; 单位性质与招聘方式：${recru.unit}、${recru.recruitment}
	        <ul class="nn_tool">
	    		<li class="video-comments"><a href="javascript:void(0)"><i class="i1"></i></a></li>
	    	</ul>
	        </div>
	        <div class="news_summary"><span>[单位地址]</span>${recru.unitAddress }</div>
	        <div class="news_txt">${recru.unitDescription}</div>
  		</c:if>
  		<c:if test="${whatType == 'infoType'}">
  			<h1>${recru.position}(${recru.unitName})</h1>
        <div class="author">月薪： <i>${recru.monthlySalary}</i> &nbsp;&nbsp; 发表于 ${recru.releaseTime }/结束于${recru.endTime} &nbsp;&nbsp; 学历要求：${recru.educationType } 
        <ul class="nn_tool">
    		<li class="video-comments"><a href="javascript:void(0)"><i class="i1"></i>招聘人数（${recru.hireCount}）</a></li>
	    	</ul>
	        </div>
	        <div class="news_summary"><span>[行业领域与工作性质]</span>${recru.industryType}、${recru.workType}</div>
	        <div class="news_txt">${recru.positionDescription}</div>
  		</c:if>
    </div>
</div>
<%@ include file="footer.jsp" %>
</html>