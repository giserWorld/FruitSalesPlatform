<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<base href="<%=basePath%>"><!-- 项目根目录 -->
    <title>主页</title>
    <link rel="stylesheet" type="text/css" href="./pages/css/navigation.css">
    <link rel="stylesheet" type="text/css" href="./pages/css/home.css">
	<!-- <script type="text/javascript" src="api.js"></script> -->
    <style>
    	
    </style>
  </head>
  <body>
      	<%@ include file="./menu.jsp" %><!-- 引用共用菜单元素 -->
  </body>
</html>
