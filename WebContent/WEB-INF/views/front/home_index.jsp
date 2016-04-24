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
<title>学生源信息-毕业信息网</title>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/front/home.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/front/main.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/front/common.css">
<script type="text/javascript" src="<%=contextPath%>/js/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/js/jquery.jslides.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/js/global.js"></script>
<script type="text/javascript">
(function ($) {
    $.fn.extend({
        "liteNav": function (t) {
            var $this = $(this), i = 0, $pics = $('#NewsPic'), autoChange = function () {
                var $currentPic = $pics.find('a:eq(' + (i + 1 === 4 ? 0 : i + 1) + ')');
                $currentPic.css({
                    visibility: 'visible',
                    display: 'block'
                }).siblings('a').css({
                    visibility: 'hidden',
                    display: 'none'
                });
                $pics.find('.Nav>span:contains(' + (i + 2 > 4 ? 4 - i : i + 2) + ')').attr('class', 'Cur').siblings('span').attr('class', 'Normal');
                $('#NewsPicTxt').html('<a target="_blank" href="' + $currentPic[0].href + '">' + $currentPic.find('img').attr('title') + '</a>');
                i = i + 1 === 4 ? 0 : i + 1;
            }, st = setInterval(autoChange, t || 5000);
            $this.hover(function () {
                clearInterval(st);
            }, function () { st = setInterval(autoChange, t || 5000) });
            $pics.find('.Nav>span').click(function () {
                i = parseInt($(this).text(), 10) - 2;
                autoChange();
            });
        }
    });
}(jQuery));
</script>
</head>
<body>
<div class="home">
  	<%@ include file="top.jsp" %>
	<div id="banner">
		<div class="bann_code">
		  <div>
		    <ul class="tab_lab">
		      <li class="select">手机访问</li>
		      <li>微信公众号</li>
		    </ul>
		    <div class="tab_cont">
		      <div class="phone" id="share-qrcode"><img src="<%=contextPath%>/css/images/bann_code.png"></div>
		      <div class="weixin" style="display:none;"><img src="<%=contextPath%>/css/images/ybolo_left.png"></div>
		    </div>
		  </div>
		</div>
		<div id="full-screen-slider">
			<ul id="slides">
				<li style="background:url('<%=contextPath%>/css/images/front_slider_1.jpg') no-repeat top center"></li>
				<li style="background:url('<%=contextPath%>/css/images/front_slider_8.jpg') no-repeat top center"></li>
				<li style="background:url('<%=contextPath%>/css/images/front_slider_5.jpg') no-repeat top center"></li>
				<li style="background:url('<%=contextPath%>/css/images/front_slider_10.jpg') no-repeat top center"></li>
				<li style="background:url('<%=contextPath%>/css/images/front_slider_11.jpg') no-repeat top center"></li>
			</ul>
		</div>
	</div>
</div>
<div class="news_cont">
	<div class="side">
		<h3><a href="/sys/enterprise/nn/list/ba537e50a33c4857a89bb8796dba3998/6">更多&gt;&gt;</a><span>招聘单位信息</span></h3>
	    <ul class="new_list">
	    	<c:if test="${empty unitsList}">
   				&nbsp;&nbsp;&nbsp;暂无招聘单位信息！
   			</c:if>
	    	<c:forEach items="${unitsList}" var="unit" begin="0" end="6">
	    		<li><a href="/sys/enterprise/nn/6">${unit.unitName}</a></li>
	    	</c:forEach>
	    </ul>
   </div>
	<div class="news_main" >
		<dl class="picnews">
			<c:forEach items="${newsTypeList}" var="enumVal">
			<c:if test="${enumVal.id == 15001}">
			<dt>${enNameMap[enumVal.id]}</dt>
		    <dd id="hotpic">
		    	<c:if test="${empty nnMap[enumVal.id]}">
	    			 <div id="NewsPicTxt" ><a target="_blank" href="javascript:void(0)">暂无图片新闻</a></div>
	    		</c:if>
	    		<div id="NewsPic">
	    			<c:forEach items="${nnMap[enumVal.id]}" var="nn" begin="0" end="3">
			            <a target="_blank" href="front_no_nnContent?newsNnId=${nn.id}"><img width="368px" height="184px" src="<%=contextPath%>/uploadImg/${nn.coverImg}" title="${nn.title}"/></a>
		            </c:forEach>
		            <div class="Nav">
		            	<c:forEach items="${nnMap[enumVal.id]}" var="news" varStatus="i"  begin="0" end="3">
			                <span <c:choose>
			                	<c:when test="${i.index == 0}">class="Cur"</c:when>
			                	<c:otherwise>class="Normal"</c:otherwise>
			                </c:choose>>${ fn:length(nnMap[enumVal.id]) - i.index }</span>
			            </c:forEach>
		            </div>
		        </div>
		        <c:if test="${not empty nnMap[enumVal.id]}">
			        <c:forEach items="${nnMap[enumVal.id]}" var="nn" begin="${fn:length(nnMap[enumVal.id])-1}" end="${fn:length(nnMap[enumVal.id])-1}">
			        	<div id="NewsPicTxt" ><a target="_blank" href="front_no_nnContent?newsNnId=${nn.id}">${nn.title}</a></div>
			        </c:forEach>
		        </c:if>
			</dd>
			</c:if>
			</c:forEach>
	    </dl>
     <script type="text/javascript">
        $('#hotpic').liteNav(5000);
    </script>
      <div class="center">
	        <h3><a href="/sys/enterprise/nn/list/e53cde01cc854e67b9364c60fd0134be/4">更多&gt;&gt;</a><span>招聘信息</span></h3>
	        <ul class="new_list">
	        	<c:if test="${empty infosList}">
	   				&nbsp;&nbsp;&nbsp;暂无招聘信息！
	   			</c:if>
	   			<c:forEach items="${infosList}" var="info" begin="0" end="6">
	   				<li><span>${info.releaseTime}</span><a href="/sys/enterprise/nn/4">${info.recruitmentUnit.unitName}(${info.position})</a></li>
	   			</c:forEach>
	        </ul>
      </div>
  </div>
</div>

<div class="news">
	<c:forEach items="${newsTypeList}" var="enumVal">
	<c:if test="${enumVal.id == 15005}">
		<div class="t1"><a href="front_no_nnTypeIndex?plateId=${enumVal.id}">更多&gt;&gt;</a><span>${enumVal.name}</span></div>
		  <ul>
		  	<c:if test="${empty nnMap[enumVal.id]}">
		  		&nbsp;&nbsp;&nbsp;暂无最新动态！
	    	</c:if>
	    	<c:forEach items="${nnMap[enumVal.id]}" var="news" begin="0" end="1">
	    		<li> <a href="front_no_nnContent?newsNnId=${news.id}" class="news_img"><img src="<%=contextPath%>/uploadImg/${news.coverImg}"/></a>
			      <h4><a href="front_no_nnContent?newsNnId=${news.id}">${news.title}</a></h4>
			      <span><em>${news.createName}</em> 发布于 ${news.createTime}  &nbsp;&nbsp;&nbsp;&nbsp;<em>${news.viewCount}</em>人阅读</span>
			      <p>${fn:substring(news.summary,0,46)}...</p>
			    </li>
	    	</c:forEach>
		  </ul>
	</c:if>
	</c:forEach>
</div>
<div class="w_1220">
	<c:forEach items="${newsTypeList}" var="enumVal">
	<c:if test="${enumVal.id == 15002}">
	  <div class="t1"><a href="front_no_nnTypeIndex?plateId=${enumVal.id}" target="_blank">更多&gt;&gt;</a><span>宣讲会</span></div>
	  <ul class="live_list">
		<c:if test="${empty nnMap[enumVal.id]}"><span class="no_live">暂无宣讲会信息！</span></c:if>
		<c:forEach items="${nnMap[enumVal.id]}" var="news" begin="0" end="4">
		<li class="live1">
  			<span class="vip_tag"></span>
   			<a href="front_no_nnContent?newsNnId=${news.id}" target="_blank">
   			<div class="live_img"><span>${news.createTime}</span>
   			<c:choose>
   				<c:when test="${not empty news.coverImg }"><img src="<%=contextPath%>/uploadImg/${news.coverImg}" alt="" width="230" height="130"/></c:when>
   				<c:otherwise><img src="<%=contextPath%>/css/images/live.jpg" alt="" width="230" height="130"/></c:otherwise>
   			</c:choose>
   			</div><p class="live_tit" href="front_no_nnContent?newsNnId=${news.id}">${news.title}</p><p class="live_btn"><i></i>阅读宣讲会信息</p></a>
   		</li>
   		</c:forEach>
	  </ul>
	  </c:if>
	</c:forEach>
  <div class="clear"></div>
</div>

<div class="whitebg">
  <div class="w_1220">
    <div class="t1"><a href="no_nnTypeIndex" target="_blank">更多&gt;&gt;</a>
      <dl>
        <dt>通知公告</dt>
      </dl>
    </div>
    <dl class="t_1v8">
    	<c:forEach items="${newsTypeList}" var="enumVal">
		<c:if test="${enumVal.id == 15003}">
      <dt><a href="front_no_nnTypeIndex?plateId=${enumVal.id}" target="_blank"><img src="<%=contextPath%>/css/images/left_notice.jpg"/></a></dt>
      <dd>
        <ul class="video_list">
          <c:if test="${empty nnMap[enumVal.id]}"><span class="no_live">&nbsp;&nbsp;&nbsp;暂无通知公告！</span></c:if>
			<c:forEach items="${nnMap[enumVal.id]}" var="news" begin="0" end="5">
	          	<li><a href="front_no_nnContent?newsNnId=${news.id}" target="_blank">
	            <div class="video_img">
	            	<c:choose>
		   				<c:when test="${not empty news.coverImg }"><img src="<%=contextPath%>/uploadImg/${news.coverImg}"/></c:when>
		   				<c:otherwise><img src="<%=contextPath%>/css/images/news_default.png"/></c:otherwise>
		   			</c:choose>
	            </div>
	            <p>${news.title}</p>
	            <span class="time"><i></i>${news.createTime}</span></a></li>
            </c:forEach>
        </ul>
      </dd>
      </c:if>
      </c:forEach>
    </dl>
  </div>
</div>
<%@ include file="footer.jsp" %>
<script type="text/javascript">  
  $(function(){
		window.onload = function()
		{
			var $li_c = $('.tab_lab > li');
			var $ul_c = $('.tab_cont > div');
						
			$li_c.click(function(){
				
				var $this = $(this);
				var $t_c = $this.index();
				$li_c.removeClass();
				$this.addClass('select');
				$ul_c.css('display','none');
				$ul_c.eq($t_c).css('display','block');
         	})
		}
	});
  
function logout(){
	$.post( 'User_doNotNeedSession_loginout', function(result){
		if(result.success){
			window.location.href='${ctx}/index.jsp';
		}
	}, 'json');
}
</script>
</body>
</html>
