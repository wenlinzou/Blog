<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>管理文章类别</title>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/HeadTemplate.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/category.css">
	
	<script src="<%=basePath%>plugjs/front/js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/categoryBack.js"></script>
 </head>
   <body>
   
   <c:if test="${empty user}">
   	   <a onclick="window.history.go(-1)">back</a>
   </c:if>
   
   <c:if test="${!empty user}">
   
	<%@include file="HeadTemplate.jsp"%>
	<div class="category">
		<input type="button" onclick="showAddBtn();" value="添加"/>
		<ul class="title">
			<li>编号</li>
			<li>名称</li>
			<li>操作</li>
		</ul>
		<div id="categoryList"></div>
		<%-- <c:forEach items="${categorys }" var="category">
			<ul>
				<li>${category.id }</li>
				<li>${category.name }</li>
				<li><a href="<%=basePath %>category/queryById.do?id=${category.id }">修改</a></li>
			</ul>
		</c:forEach> --%>
	</div>
	<div id="showAddCategoryDiv" class="addCategory" style="display: none;">
		<form action="<%=basePath%>category/save.do" method="post">
		<ul><li></li><li>名称</li></ul>
		<ul><li></li>
			<li><input class="inputadd" type="text" name="name"/></li>
			<li><input type="submit" value="添加"/></li>
		</ul>
		</form>
	</div>
	
	</c:if>
	
</body>
</html>