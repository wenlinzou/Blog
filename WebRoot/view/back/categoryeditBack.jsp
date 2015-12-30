<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>编辑用户</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/HeadTemplate.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/category.css">
</head>

<body>
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
</body>
</html>