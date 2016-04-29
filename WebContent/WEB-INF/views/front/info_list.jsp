<%@ page pageEncoding="UTF-8" %>
<%@ include file="global.jsp" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<% String contextPath = request.getContextPath(); %>
<c:choose>
	<c:when test="${infoPager.totalRecord eq 0 }">暂无招聘信息！</c:when>
	<c:otherwise>
		<ul class="newslist">
		  	<c:forEach items="${infoPager.recordList}" var="info">
			  	<c:if test="${whatType == 'unitType'}">
			  		<li>
				        <h4><a href="front_no_infoContent?recruId=${info.id}&whatType=${whatType}">${info.unitName}</a></h4>
				        <span><em>单位联系人：${info.unitContantPerson}</em> <%-- 发布于 &nbsp;${info.releaseTime}--%> &nbsp;&nbsp;&nbsp;&nbsp;联系电话： <em>${info.unitContantPhone}</em></span>
				        <p>${info.unitDescription}</p>
				    </li>
			  	</c:if>
			  	<c:if test="${whatType == 'infoType'}">
			  		<li>
				        <%-- <a href="front_no_nnContent?newsNnId=${newsNn.id}" class="news_img"><img src="<%=contextPath%>/uploadImg/${newsNn.coverImg}"/></a> --%>
				        <h4><a href="front_no_infoContent?recruId=${info.id}&whatType=${whatType}">${info.position}</a></h4>
				        <span><em>月薪资：${info.monthlySalary}</em> 发布于 &nbsp;${info.releaseTime}   &nbsp;&nbsp;&nbsp;&nbsp;招聘人数： <em>${info.hireCount}</em></span>
				        <p>${info.positionDescription}</p>
				    </li>
			  	</c:if>
			</c:forEach>
		</ul>
	</c:otherwise>
</c:choose>
<c:if test="${fn:length(infoPager.recordList) > 0}">
	<div class="page-container">
	<tag:pager_person pager="${infoPager}" dataTarget="infoPageContainer" dealFun="infoPageList"/>
	</div>
</c:if>
