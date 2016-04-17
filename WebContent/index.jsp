<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Index</title>
  </head>
  
  <body>
   <%-- <jsp:forward page="/WEB-INF/views/login.jsp"></jsp:forward> --%>
    <jsp:forward page="/WEB-INF/views/forward.jsp"></jsp:forward>
  </body>
</html>
