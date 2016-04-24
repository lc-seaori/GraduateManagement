<%@ page pageEncoding="UTF-8" %>
<%@ include file="global.jsp" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<% String contextPath = request.getContextPath(); %>
<c:choose>
	<c:when test="${newsNnPager.totalRecord eq 0 }">暂无新闻信息！</c:when>
	<c:otherwise>
	<ul class="newslist">
	  	<c:forEach items="${newsNnPager.recordList}" var="newsNn">
	  	<li>
        <a href="front_no_nnContent?newsNnId=${newsNn.id}" class="news_img"><img src="<%=contextPath%>/uploadImg/${newsNn.coverImg}"/></a>
        <h4><a href="front_no_nnContent?newsNnId=${newsNn.id}">${newsNn.title}</a></h4>
        <span><em>${newsNn.createName}</em> 发布于 &nbsp;${newsNn.createTime}   &nbsp;&nbsp;&nbsp;&nbsp;<em>${newsNn.viewCount}</em> 人阅读</span>
        <!-- ${fn:substring(newsNn.createTime,0,10)} -->
        <p>${newsNn.summary}</p>
      </li>
	</c:forEach>
	</ul>
	</c:otherwise>
</c:choose>
<c:if test="${fn:length(newsNnPager.recordList) > 0}">
	<div class="page-container">
	<tag:pager_person pager="${newsNnPager}" dataTarget="newsNnPageContainer" dealFun="coursePageList"/>
	</div>
</c:if>
