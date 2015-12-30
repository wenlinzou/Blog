<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>用户</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/HeadTemplate.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/category.css">
	
	<script type="text/javascript" src="<%=basePath%>js/categoryBack.js"></script>
 </head>
   <body>
	<%@include file="HeadTemplate.jsp"%>
	<div class="category">
		<input type="button" onclick="showAddBtn();" value="添加"/>
		<ul class="title">
			<li>编号</li>
			<li>名称</li>
			<li>操作</li>
		</ul>
		<c:forEach items="${categorys }" var="category">
			<ul>
				<li>${category.id }</li>
				<li>${category.name }</li>
				<li><a href="<%=basePath %>category/queryById.do?id=${category.id }">修改</a></li>
			</ul>
		</c:forEach>
	</div>
	<div id="showAddCategoryDiv" class="addCategory" style="display: none;">
		<form action="<%=basePath%>category/save.do" method="post">
		<ul><li>名称</li></ul>
		<ul>
			<li><input type="text" name="name"/></li>
			<li><input type="submit" value="添加"/></li>
		</ul>
		</form>
	</div>
</body>
</html>