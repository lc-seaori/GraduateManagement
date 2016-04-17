<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试</title>
</head>
<body>
	<h3><font color="blue">带有验证码的登陆界面</font></h3>
    <!-- User_doNotNeedSession_login.action -->
    <s:form action="System_Image_doNotNeedSession_getCodeImage.action" namespace="/" method="post" theme="simple" enctype="multipart/form-data">
		用户名:<s:textfield name="username"></s:textfield><br>
		密码    :<s:password name="password"></s:password><br>
	 	验证码:<s:textfield name="checkCode"></s:textfield>
　　　　　    <!--若要点击图片刷新，重新得到一个验证码，要在后面加上个随机数，这样保证每次提交过去的都是不一样的path，防止因为缓存而使图片不刷新-->
        <img src="System_Image_doNotNeedSession_getCodeImage.action" onclick="this.src='System_Image_doNotNeedSession_getCodeImage.action?d='+ Math.random()" title="点击图片刷新验证码"/><br>
        <s:submit value="提交"></s:submit>
    </s:form>
</body>
</html>