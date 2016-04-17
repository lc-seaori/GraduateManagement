<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<%=contextPath%>/js/jquery.min.js"></script>
</head>
<body>
	<body>
		<a id="a_forward" href="front_toHomeIndex"></a>
	</body>
	<script type="text/javascript">
		$(function(){
			document.getElementById("a_forward").click();
		});
	</script>
</body>
</html>