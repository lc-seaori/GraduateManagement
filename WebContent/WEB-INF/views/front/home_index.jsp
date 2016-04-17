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
				<li style="background:url('<%=contextPath%>/css/images/front_slider_6.jpg') no-repeat top center"></li>
				<li style="background:url('<%=contextPath%>/css/images/front_slider_2.jpg') no-repeat top center"></li>
				<li style="background:url('<%=contextPath%>/css/images/front_slider_3.jpg') no-repeat top center"></li>
				<li style="background:url('<%=contextPath%>/css/images/front_slider_4.jpg') no-repeat top center"></li>
				<li style="background:url('<%=contextPath%>/css/images/front_slider_5.jpg') no-repeat top center"></li>
				<%-- <c:if test="${not empty co.coSlidesImg1 }">
				  <li style="background:url(/Rec/coImg/${co.coSlidesImg1}) no-repeat top center"></li>
				</c:if>
				<c:if test="${not empty co.coSlidesImg2 }">
				  <li style="background:url(/Rec/coImg/${co.coSlidesImg2}) no-repeat top center"></li>
				</c:if>
				<c:if test="${not empty co.coSlidesImg3 }">
				  <li style="background:url(/Rec/coImg/${co.coSlidesImg3}) no-repeat top center"></li>
				</c:if>
				<c:if test="${not empty co.coSlidesImg4 }">
				  <li style="background:url(/Rec/coImg/${co.coSlidesImg4}) no-repeat top center"></li>
				</c:if>
				<c:if test="${not empty co.coSlidesImg5 }">
				  <li style="background:url(/Rec/coImg/${co.coSlidesImg5}) no-repeat top center"></li>
				</c:if> --%>
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
	  	<c:forEach items="${platesList}" var="plate">
	  		<c:if test="${plate.plateId == '2c249de2b7124e4dbd8e9847825d4892'}">
		  	<dt>${plate.plateName}</dt>
		    <dd id="hotpic">
		    	<c:if test="${empty nnBeanMap[plate.plateId]}">
	    			 <div id="NewsPicTxt" ><a target="_blank" href="#">暂无图片新闻</a></div>
	    		</c:if>
	    		<div id="NewsPic">
	    			<c:forEach items="${nnBeanMap[plate.plateId]}" var="nn">
			            <a target="_blank" href="/sys/enterprise/nn/${nn.nn.nnId }/8"><img width="368px" height="184px" src="${nn.thumbnail}" title="${nn.nn.title}" /></a>
		            </c:forEach>
		            <div class="Nav">
		            	<c:forEach items="${nnBeanMap[plate.plateId]}" var="nn" varStatus="i">
			                <span <c:choose>
			                	<c:when test="${i.index == 0}">class="Cur"</c:when>
			                	<c:otherwise>class="Normal"</c:otherwise>
			                </c:choose>>${ fn:length(nnBeanMap[plate.plateId]) - i.index }</span>
			            </c:forEach>
		            </div>
		        </div>
		        <c:if test="${not empty nnBeanMap[plate.plateId]}">
			        <c:forEach items="${nnBeanMap[plate.plateId]}" var="nn" begin="${fn:length(nnBeanMap[plate.plateId])-1}" end="${fn:length(nnBeanMap[plate.plateId])-1}">
			        	<div id="NewsPicTxt" ><a target="_blank" href="/sys/enterprise/nn/${nn.nn.nnId }/8">${nn.nn.title}</a></div>
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
	<c:forEach items="${platesList}" var="plate">
		<c:if test="${plate.plateId == 'f6730827529647c1aa2e58572610feb9'}">
		  <div class="t1"><a href="/sys/enterprise/nn/list/${co.coId}/f6730827529647c1aa2e58572610feb9/7">更多&gt;&gt;</a><span>${plate.plateName}</span></div>
		  <ul>
		  	<c:if test="${empty nnBeanMap[plate.plateId]}">
		  		&nbsp;&nbsp;&nbsp;暂无电子杂志！
	    	</c:if>
	    	<c:forEach items="${nnBeanMap[plate.plateId]}" var="nn" begin="0" end="1">
	    		<li> <a href="/sys/enterprise/nn/${nn.nn.nnId }/7" class="news_img"><img src="${nn.thumbnail}"/></a>
			      <h4><a href="/sys/enterprise/nn/${nn.nn.nnId }/7">${nn.nn.title}</a></h4>
			      <span><em>${nn.nn.userName}</em> 发布于 ${fn:substring(nn.nn.createTime,0,10)}  &nbsp;&nbsp;&nbsp;&nbsp;<em>${nn.commentCount}</em>条评论</span>
			      <p>${fn:substring(nn.nn.summary,0,46)}...</p>
			    </li>
	    	</c:forEach>
		  </ul>
		</c:if>
	</c:forEach>
</div>
<div class="w_1220">
  <div class="t1"><a href="${BASE}/sys/enterprise/live/More/1/" target="_blank">更多&gt;&gt;</a><span>直播推荐</span></div>
  <ul class="live_list">
    <c:if test="${fn:length(liveList) eq 0}"><span class="no_live">暂无直播！</span></c:if>
    <c:forEach var="live" items="${liveList}" varStatus="i" begin="0" end="7">
    	<c:choose>
    		<c:when test="${live.liveFlag eq 0}">
    			<li>
    				<c:choose>
    					<c:when test="${live.payType eq 'VIP'}">
    						<span class="vip_tag"></span>
    					</c:when>
    					<c:when test="${live.payType eq 'PAY'}">
    						<span class="price_tag">￥${live.payMoney}</span>
    					</c:when>
    				</c:choose>
    				<a href="/sys/live/play/pc/${live.liveId}/0/${live.coId}" target="_blank">
	    				<div class="live_img"><span>${live.liveStart} - ${fn:substring(live.liveEnd,11,16)}</span>
		    				<c:choose>
		    					<c:when test="${not empty live.cfgBackgroundImg1 }"><img src="/Rec/coImg/${live.cfgBackgroundImg1}" alt="" width="230" height="130"/></c:when>
		    					<c:otherwise><img src="/www/images/enterprise/live.jpg" alt="" width="230" height="130"/></c:otherwise>
		    				</c:choose>
	    				</div>
    					<p class="live_tit">${live.liveName}</p><p class="live_btn"><i></i>预约直播</p>
    				</a>
    			</li>
    		</c:when>
    		<c:when test="${live.liveFlag eq 1}">
    			<li class="live1">
    			<c:choose>
   					<c:when test="${live.payType eq 'VIP'}">
   						<span class="vip_tag"></span>
   					</c:when>
   					<c:when test="${live.payType eq 'PAY'}">
   						<span class="price_tag">￥${live.payMoney}</span>
   					</c:when>
   				</c:choose>
    			<a href="/sys/live/play/pc/${live.liveId}/0/${live.coId}" target="_blank">
    			<div class="live_img"><span>${live.liveStart} - ${fn:substring(live.liveEnd,11,16)}</span>
    			<c:choose>
    				<c:when test="${not empty live.cfgBackgroundImg1 }"><img src="/Rec/coImg/${live.cfgBackgroundImg1}" alt="" width="230" height="130"/></c:when>
    				<c:otherwise><img src="/www/images/enterprise/live.jpg" alt="" width="230" height="130"/></c:otherwise>
    			</c:choose>
    			</div><p class="live_tit" href="#">${live.liveName}</p><p class="live_btn"><i></i>观看直播</p></a>
    			</li>
    		</c:when>
    		<c:when test="${live.liveFlag eq 2}">
    			<li class="live0">
    			<c:choose>
    					<c:when test="${live.payType eq 'VIP'}">
    						<span class="vip_tag"></span>
    					</c:when>
    					<c:when test="${live.payType eq 'PAY'}">
    						<span class="price_tag">￥${live.payMoney}</span>
    					</c:when>
    				</c:choose>
    			<a href="/ybolo/album/${live.liveId}" target="_blank">
    			<div class="live_img"><span>${live.liveStart} - ${fn:substring(live.liveEnd,11,16)}</span>
    		    <c:choose>
    				<c:when test="${not empty live.cfgBackgroundImg1 }"><img src="/Rec/coImg/${live.cfgBackgroundImg1}" alt="" width="230" height="130"/></c:when>
    				<c:otherwise><img src="/www/images/enterprise/live.jpg" alt="" width="230" height="130"/></c:otherwise>
    			</c:choose>
    		</div><p class="live_tit" href="#">${live.liveName}</p><p class="live_btn"><i></i>精彩回放</p></a></li>
    		</c:when>
    	</c:choose>
	</c:forEach>
  </ul>
  <div class="clear"></div>
</div>

<div class="whitebg">
  <div class="w_1220">
    <div class="t1"><a href="/sys/enterprise/train/" target="_blank">更多&gt;&gt;</a>
      <dl>
        <dt>在线培训</dt>
        <dd>
	        <c:forEach items="${groupList}" var="group" varStatus="i">
	        	<c:if test="${i.index > 0}">/</c:if><a href="/sys/enterprise/train/${co.coId}/${group.groupId}" target="_blank">${group.groupName}</a>
	        </c:forEach>
        </dd>
      </dl>
    </div>
    <dl class="t_1v8">
      <dt><a href="/sys/enterprise/train/" target="_blank"><img src="/www/templet/pc/skin4/images/t1.jpg"/></a></dt>
      <dd>
        <ul class="video_list">
          <c:forEach items="${courseList}" var="course" varStatus="i">
          	<c:if test="${empty courseList}">
          		&nbsp;&nbsp;&nbsp;暂无培训视频信息！
          	</c:if>
          	<li><a href="${BASE}/sys/enterprise/train/info/${course.courseId}/${co.coId}" target="_blank">
            <div class="video_img"><img src="${course.headImg}"/></div>
            <p>${course.courseName}</p>
            <span class="time"><i></i>${fn:substring(course.createTime,0,10)}</span></a></li>
          </c:forEach>
        </ul>
      </dd>
    </dl>
  </div>
</div>
<%@ include file="footer.jsp" %>
<%-- <script type="text/javascript" src="${BASE}/www/js/system/enterprise/jquery.qrcode.min.js"></script> --%>
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
		/* var mode = !!document.createElement('canvas').getContext ? 'canvas' : 'table';
		$('#share-qrcode').qrcode({render:mode,width:200,height:200,correctLevel:0,text:"http://scu.ybolo.cn/wx/mooc?cmd=10000&coId=${co.coId}"}); */
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
