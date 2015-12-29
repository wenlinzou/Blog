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
    
    <title>管理文章</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/HeadTemplate.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/article.css">
  </head>
  
  <body>
	<%@include file="HeadTemplate.jsp"%>
	<!-- <input type="button" onclick="showAddBtn();" value="添加"/> -->
	<a href="<%=basePath %>view/back/artcileaddBack.jsp">添加</a>
	<div class="article">
		<ul class="title">
			<li>编号</li>
			<li>类别</li>
			<li>类型</li>
			<li>标题</li>
			<!-- <li>内容</li> -->
			<li>时间</li>
			<li>可见</li>
			<li>操作</li>
		</ul>
		<c:forEach items="${articleList }" var="article">
			<ul>
				<li>${article.id }</li>
				<li>${article.pid }</li>
				<li>${article.rootid }</li>
				<li>${article.title }</li>
				<%-- <li>${article.cont }</li> --%>
				<li><fmt:formatDate value="${article.pdate }" pattern="yyyy-MM-dd HH:mm:ss" /></li>
				<li>${article.isleaf }</li>
				<li><a href="<%=basePath%>article/queryById.do?id=${article.id }">修改</a></li>
			</ul>
		</c:forEach>
	</div>
</body>
</html>