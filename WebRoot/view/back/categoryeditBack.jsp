<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>编辑文章类别</title>


<link rel="stylesheet" type="text/css" href="<%=basePath%>css/HeadTemplate.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/category.css">
</head>

<body>
	<c:if test="${empty user}">
   	   <a onclick="window.history.go(-1)">back</a>
   </c:if>
   
   <c:if test="${!empty user}">
   
	<%@include file="HeadTemplate.jsp"%>
	<div class="category">
		<form action="<%=basePath%>category/update.do" method="post">
			<input type="hidden" value='${category.id }' name="id" />
			<ul>
				<li>名称</li>
				<li>操作</li>
			</ul>

			<ul>
				<li><input type="text" value='${category.name }' name="name" /></li>
				<li><input type="submit" value="修改" /><input type="button" value="返回"onclick="window.history.go(-1)" /></li>
			</ul>
		</form>
	</div>
	
	</c:if>
</body>
</html>