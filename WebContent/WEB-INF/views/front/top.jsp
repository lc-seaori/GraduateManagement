<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="global.jsp" %>
 <div class="top">
    <div class="top_logo">
      <a href="front_toHomeIndex" class="logo"> <img src="<%=contextPath%>/css/images/logo.png" alt="logo"/> <b>五邑大学学生信息网</b> <em>WUYI UNIVERSITY INFO</em> </a>
      <div class="top_right">
        <div class="search"><input type="text"  class="search_txt" placeholder="请输入您的关键词"/><input type="button"  class="search_btn"/> </div>
        <div class="userinfo">
        	<c:choose>
	        	<c:when test="${sessionInfo != null}">
	        		您好，&nbsp;<a href="javascript:void(0)">${sessionInfo.name}</a>&nbsp;| <c:if test="${sessionInfo.loginname == 'admin'}"><a href="front_toBackstage">后台管理</a></c:if>|<a onclick="logout()" href="javascript:void(0)" id="logout">退出</a>
	        	</c:when>
	        	<c:otherwise>
	        		游客&nbsp;|<a id="loginBtn" href="front_doNotNeedSession_toLogin">登 录</a>
	        	</c:otherwise>
			</c:choose>
        </div>
      </div>
    </div>
    <div class="top_menu">
        <dl class="drop_menu">
	        <dt><a href="front_no_nnIndex"><i class="list_all"></i>重要信息</a></dt>
	        <c:forEach items="${newsPlateList}" var="newsPlate" begin="0" end="6">
	        	<dd>
		        <h3><a href="front_no_nnIndex?plateId=${newsPlate.id}">${newsPlate.plateName}</a></h3>
		        	<a href="front_no_nnIndex?plateId=${newsPlate.id}">详情</a>
		        </dd>
	        </c:forEach>
        </dl>
        <ul class="list">
          <li><a href="front_toHomeIndex">首页</a></li>
          <li><a href="front_no_infoIndex?whatType=unitType">招聘公司</a></li>
          <li><a href="front_no_infoIndex?whatType=infoType">招聘信息</a></li>
          <c:forEach items="${newsTypeList}" var="newsType">
          	<li><a href="front_no_nnTypeIndex?plateId=${newsType.id}">${newsType.name}</a></li>
          </c:forEach>
          <li><a href="javascript:void(0)">联系我们</a></li>
        </ul>
      <div class="cont_menu"> <i></i><span><em>15088132331</em></span> </div>
    </div>
  </div>