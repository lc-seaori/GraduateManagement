<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
	<head>
		<title>毕业生管理系统欢迎您</title>
		<link rel="shortcut icon" type="image/x-icon" href="<%=contextPath%>/css/images/favicon.png">
		<jsp:include page="includes/styles.jsp"></jsp:include>
		<style type="text/css">
			#background #header{
				position: fixed;
				z-index: -1;
			}
			#background img {
				width:100%;
				float: left;
			}
			#headerDiv{
				position: relative;
				float: right;		
				margin-top: -110px;		
				margin-right: 20px;
			}
			#head{
				float: left;
			}
			#head #headImg{
				position: relative;
				float:left;
				margin-top: -120px;
				margin-left: 50px;
			}
			#head span{
				position: absolute;
				font-style: italic;
				font-stretch: ultra-expanded;
				font-size: 38px;
				font-weight: bold;
				letter-spacing: 8px;
				text-shadow: 1px 2px #f2f2f2;
				margin-top: -104px;
				margin-left: 150px;
			}
			#headerDiv a{
				text-decoration: none;
				color: #ffffff;
			}
			#headerDiv a:HOVER{
				text-decoration: none;
				color: #ff0000;
			}
			
		</style>
	</head>
	<body style="background-color: #f2f2f2">
		<div id="background" style="height: 120px;">
			<img alt="loading..." src="<%=contextPath%>/css/images/headbg.png">
		</div>
		<div id="head">
			<div id="headImg">
				<img src="<%=contextPath%>/css/images/logo.png" alt="logo"/>
			</div>
			<%-- <img alt="" src="<%=contextPath%>/css/images/index.jpg"/> --%>
			<span>毕业生信息管理系统</span>
		</div>
		<div id="headerDiv">
			<b>[${sessionInfo.name}]</b>，欢迎您！<a href="javascript:void(0)" onclick="returnIndex()">返回首页</a> &nbsp;&nbsp;<a href="javascript:void(0)" onclick="editUserPwd()">修改密码</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="logout()">安全退出</a>
		</div>
		
		<div class="easyui-layout" style="margin: 0 auto; width: 100%; height: 645px;">
			<!-- 1230px,600px -->
			<%@ include file="/WEB-INF/views/includes/menu.jsp"%>
			<div data-options="region:'center'"
				style="padding: 5px; background: #eee;">
				<div id="main" class="easyui-tabs" style="width: 100%; height: 630px">
					<!--1070px,585px-->
					<div title="Welcome" style="padding-left: 100px;padding-top: 30px;" data-options="iconCls:'icon-home'">
						<br/>
						<h1>Welcome to </h1>
						<br/>
						<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Use graduate manament system ...</h2>
						<br/>
						<h2 style="padding-left:200px;color: #ff0000;">欢迎使用毕业生信息管理系统</h2>
					</div>
				</div>
			</div>
		</div>
		<div id="editDialog" data-options="iconCls:'icon-save'" style="width:500px;height:350px;padding:10px">
		</div>
		
		<script type="text/javascript">
			function editUserPwd(){
				$('#editDialog').dialog({
				    title: '密码修改',
				    width: 500,
				    height: 350,
				    cache: false,
				    href: 'User_doNotNeedAuth_toEditUserPwd.action',
				    collapsible: true,
				    maximizable: true,
				    resizable: true,
				    modal: true,
				    closed: true
				});
				$('#editDialog').dialog('open');
			} 
			
			function logout(){
				$.messager.confirm('请确认','您确定退出系统吗？',function(r){
               		if (r){
               			$.post( 'User_doNotNeedSession_loginout', 
               				function(result) {
	        					if(result.success){
	        						window.location.href='${ctx}/index.jsp';
	        					}
        					}, 'json');
               		}
                });
			}
			
			function returnIndex(){
				window.location.href='${ctx}/index.jsp';
			}
		</script>
	</body>
</html>

