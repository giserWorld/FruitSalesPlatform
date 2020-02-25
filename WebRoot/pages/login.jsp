<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 引入jstl标签库 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 	<base href="<%=basePath%>"><!-- 项目根目录 -->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>登录</title>
	<link rel="stylesheet" type="text/css" href="./pages/css/login.css">
	<script type="text/javascript" src="./apiFiles/jquery/js/jquery-3.3.1.js"></script>
	<style>
		
	</style>
</head>
<body>
	<div id="content">
		<div id="formDiv">
			<h1>用户登录</h1>
			<!-- 表单 -->
			<form id="myform" action="user/login.action" method="post" autocomplete="off">
				<p>
					<label for="username">用户名:</label>
					<input type="text" id="username" name="username" style="width:190px;height:26px;">
				</p>
				<p>
					<label for="password">密   码:</label>
					<input type="text" id="password" name="password" style="width:190px;height:26px;margin-left: 10px;">
				</p>
				<input type="submit" value="登录">
				<a href="./pages/register.jsp">注册</a>
			</form>
			<!-- 显示错误信息 -->
			<c:if test="${errorMsg!=null}">
				<font color="red">${errorMsg}</font>
			</c:if>
			<!-- 显示提示信息 -->
			<c:if test="${noticeMsg!=null}">
				<font color="green">${noticeMsg}</font>
			</c:if>
		</div>
	</div>
	<script>
		
	</script>
</body>
</html>