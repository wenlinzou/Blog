<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/user.css">
</head>

<body>
	<%@include file="HeadTemplate.jsp"%>
	<div class="user">
		<form action="<%=basePath%>article/update.do" method="post">
			<input type="hidden" value='${article.id }' name="id" />
			<ul>
				<li>类别</li>
				<li>类型</li>
				<li>标题</li>
				<li>内容</li>
				<li>时间</li>
				<li>可见</li>
				<li>操作</li>
			</ul>

			<ul>
				<li><input type="text" value='${article.pid }'	name="pid" /></li>
				<li><input type="text" value='${article.rootid }'	name="rootid" /></li>
				<li><input type="text" value='${article.title }'	name="title" /></li>
				<li><input type="text" value='${article.cont }'	name="cont" /></li>
				<li><fmt:formatDate value="${article.pdate }" pattern="yyyy-MM-dd HH:mm:ss" /></li>
				<li><input type="text" value='${article.isleaf }' name="isleaf" /></li>
				<li><input type="submit" value="修改" /><input type="button" value="返回"onclick="window.history.go(-1)" /></li>
			</ul>
		</form>
	</div>
</body>
</html>