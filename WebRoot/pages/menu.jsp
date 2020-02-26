<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 导航菜单html元素 -->
<div id="menuContent" style="background-color:#173e65;color:#ffffff;height:100px;">
   <h1 style="margin-left: 10px;margin-top:10px;">
           水果网络销售平台
   </h1>
   <div style="margin-left: 10px;margin-top: 22px;">
	     <a href="./retailer/list.action">零售商管理</a>|
	     <a href="./commodities/list.action">货物管理</a>|
	     <a href="#">购销合同</a>|
	     <a>用户设置</a>
   </div>
</div>
<div style="background-color:#cccccc">
	<span style="margin-left: 10px;">欢迎您，${user.getName()} </span>
</div>