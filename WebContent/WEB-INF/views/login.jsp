<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String contextPath = request.getContextPath(); %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>毕业生信息管理系统</title>
		<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/login/normalize.css" />
		<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/login/default.css">
		<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/login/styles.css">
		<link rel="shortcut icon" type="image/x-icon" href="<%=contextPath%>/css/images/favicon.png">
		<jsp:include page="includes/styles.jsp"></jsp:include>
	</head>
	<body style="background:#F2F2F2">
		<div class="htmleaf-container">
			<div class="wrapper" >
				<div class="container" style="padding-top: 30px;">
					<h1>Welcome</h1>
					<s:if test="#request.msg!=null">
						<s:property value="#request.msg"/>
					</s:if>
					<s:form id="loginForm" class="form" namespace="/" action="User_doNotNeedSession_login" theme="simple" method="post" enctype="multipart/form-data">
						<input type="text" placeholder="Username" id="loginname" name="loginname">
						<input type="password" placeholder="Password" id="password" name="password">
						<input type="text" placeholder="checkCode" id="checkCode" name="checkCode" style="width: 150px;float: left;margin-left: 176px;">
						<img src="System_Image_doNotNeedSession_getCodeImage.action" onclick="this.src='System_Image_doNotNeedSession_getCodeImage.action?d='+ Math.random()" title="点击图片刷新验证码" style="display: block;padding-left: 20px;padding-top: 10px;"/><br/><br/>
						<button type="submit" id="login-button" style="display: block;clear: both;margin-left: 176px;">Login</button>
					</s:form>
				</div>
				<ul class="bg-bubbles">
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
				</ul>
			</div>
		</div>
		
		<script>window.jQuery || document.write('<script src="js/jquery-2.1.1.min.js"><\/script>')</script>
		<script>
			$('#login-button').click(function (event) {
				event.preventDefault();
			  	//解决浏览器不兼容tirm()方法，如ie8以下不兼容trim()，该方法去掉前后空格
				if(!String.prototype.trim) {
					String.prototype.trim = function () {
				    	return this.replace(/^\s+|\s+$/g,'');
				  	};
				}
			    var loginName=$('#loginForm').find('#loginname').val().trim();
			    var password=$('#loginForm').find('#password').val().trim();
			    if(loginName==""||password==""){
			    	$.messager.show({
			    		title:' Tips',
		                msg:'用户帐号或者密码不能为空',
		                height:'150px',
		                width:'300px',
		                showType:'show'
		            });
			    	return;
			    }
			    $('form').fadeOut(500);
			    $('.wrapper').addClass('form-success');
			    $('#loginForm').submit();
			});
		</script>
		
		<script type="text/javascript">
		    $(function () {
		        var Sys = {};
		        var ua = navigator.userAgent.toLowerCase();
		        var s;
		        (s = ua.match(/rv:([\d.]+)\) like gecko/)) ? Sys.ie = s[1] :
		        (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
		        (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
		        (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
		        (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
		        (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
		        });
		</script>
	</body>
</html>
