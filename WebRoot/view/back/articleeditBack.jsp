<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>编辑文章</title>


<link rel="stylesheet" type="text/css" href="<%=basePath%>css/HeadTemplate.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/articleadd.css">

	<script type="text/javascript" charset="utf-8"  src="<%=basePath%>plugjs/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8"  src="<%=basePath%>plugjs/ueditor/ueditor.all.js"></script>
	
</head>

<body>
	<c:if test="${empty user}">
   	   <a onclick="window.history.go(-1)">back</a>
   </c:if>
   
   <c:if test="${!empty user}">
   
	<%@include file="HeadTemplate.jsp"%>
	<div class="article">
		<form action="<%=basePath%>article/update.do" method="post">
			<input type="hidden" value='${article.id }' name="id" />
			<ul class="articleleft">
				<li>类别</li>
				<li>置顶</li>
				<li>标题</li>
				<li class="articleleftcont">内容</li>
				<li>时间</li>
				<li>状态</li>
				<li>操作</li>
			</ul>

			<ul class="articleright">
				<li>
					<select name="pid">
						<c:forEach items="${categoryList }" var="category">
						<option value='${category.id }' <c:if test='${category.id==article.pid }'>selected="selected"</c:if>>${category.name }</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<select name="rootid">
						<option value="1" <c:if test='${article.rootid==0 }'>selected="selected"</c:if>>正常</option>
						<option value="0" <c:if test='${article.rootid==1 }'>selected="selected"</c:if>>置顶</option>
					</select>
				</li>
				<li><input type="text" class="inputright"  value='${article.title }'	name="title" /></li>
				<li  class="cont">
					<script type="text/plain" id="contue" name="cont" style="width:800px;height:500px;">${article.cont }</script>
				</li>
				<li><fmt:formatDate value="${article.pdate }" pattern="yyyy-MM-dd HH:mm:ss" /></li>
				<li>
					<select name="isleaf">
						<option value="1" <c:if test='${article.isleaf==1 }'>selected="selected"</c:if>>可见</option>
						<option value="0" <c:if test='${article.isleaf==0 }'>selected="selected"</c:if>>隐藏</option>
					</select>
				</li>
				<li><input type="submit" value="修改" /><input type="button" value="返回"onclick="window.history.go(-1)" /></li>
			</ul>
		</form>
		<script type="text/javascript">var ue = UE.getEditor('contue');</script>
	</div>
	
	</c:if>
	
</body>
</html>