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
    
    <title>添加文章</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/HeadTemplate.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/articleadd.css">
	
	<script type="text/javascript" charset="utf-8"  src="<%=basePath%>plugjs/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8"  src="<%=basePath%>plugjs/ueditor/ueditor.all.js"></script>
  </head>
  
  <body>
	<%@include file="HeadTemplate.jsp"%>
	<!-- <input type="button" onclick="showAddBtn();" value="添加"/> -->
	<div class="article">
		<ul class="articleleft">
			<li>类别</li>
			<li>类型</li>
			<li>标题</li>
			<li class="articleleftcont">内容</li>
			<li>可见</li>
			<li></li>
		</ul>
		<form action="<%=basePath%>article/add.do" method="post">
		<ul class="articleright">
			<li><input type="text" name="pid"/></li>
			<li><input type="text" name="rootid"/></li>
			<li><input type="text" name="title"/></li>
			<li class="cont">
				<script type="text/plain" id="contue" name="cont" style="width:800px;height:500px;"></script>
			</li>
			<li><input type="text" name="isleaf"/></li>
			<li><input type="submit" value="添加"/><input type="button" value="返回"onclick="window.history.go(-1)" /></li>
			
		</ul>
		</form>
			<script type="text/javascript">var ue = UE.getEditor('contue');</script>
				
	</div>
</body>
</html>