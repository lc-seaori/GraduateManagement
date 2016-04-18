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
	        <dt><a href="/sys/enterprise/train/"><i class="list_all"></i>重要信息</a></dt>
	        <c:forEach items="${newsPlateList}" var="newsPlate" begin="0" end="6">
	        	<dd>
		        <h3><a href="front_no_nnlist?plateId=${newsPlate.id}">${newsPlate.plateName}</a></h3>
		        	<a href="front_no_nnlist?plateId=${newsPlate.id}">详情</a>
		        </dd>
	        </c:forEach>
          <%-- <dd>
          	<h3><a href="/sys/enterprise/train/${co.coId}/${group.groupId}">新生导航</a></h3>
            <a href="/sys/enterprise/train/${co.coId}/${group.groupId}">详情</a>
          </dd>
          <dd>
          	<h3><a href="/sys/enterprise/train/${co.coId}/${group.groupId}">招生计划</a></h3>
            <a href="/sys/enterprise/train/${co.coId}/${group.groupId}">详情</a>
          </dd>
          <dd>
          	<h3><a href="/sys/enterprise/train/${co.coId}/${group.groupId}">图片新闻</a></h3>
            <a href="/sys/enterprise/train/${co.coId}/${group.groupId}">详情</a>
          </dd>
          <dd>
          	<h3><a href="/sys/enterprise/train/${co.coId}/${group.groupId}">招聘信息</a></h3>
            <a href="/sys/enterprise/train/${co.coId}/${group.groupId}">详情</a>
          </dd>
          <dd>
          	<h3><a href="/sys/enterprise/train/${co.coId}/${group.groupId}">最新宣讲会</a></h3>
            <a href="/sys/enterprise/train/${co.coId}/${group.groupId}">详情</a>
          </dd>
          <dd>
          	<h3><a href="/sys/enterprise/train/${co.coId}/${group.groupId}">最新动态</a></h3>
            <a href="/sys/enterprise/train/${co.coId}/${group.groupId}">详情</a>
          </dd> --%>
        </dl>
        <ul class="list">
          <li><a href="front_toHomeIndex">首页</a></li>
          <li><a href="javascript:void(0)">招聘公司</a></li>
          <li><a href="javascript:void(0)">招聘信息</a></li>
          <li><a href="/sys/enterprise/nn/list/2c249de2b7124e4dbd8e9847825d4892/8">宣讲会</a></li>
          <li><a href="/sys/enterprise/nn/list/ba537e50a33c4857a89bb8796dba3998/6">政策法规</a></li>
          <li><a href="/sys/enterprise/nn/list/f6730827529647c1aa2e58572610feb9/7">通知公告</a></li>
          <li><a href="/sys/enterprise/about/">最新动态</a></li>
          <li><a href="#">联系我们</a></li>
        </ul>
      <div class="cont_menu"> <i></i><span><em>15088132331</em></span> </div>
    </div>
  </div>