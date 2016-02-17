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
    
    <title>管理文章评论</title>
    
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/HeadTemplate.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/comment.css">
  </head>
  
  <body>
  
   <c:if test="${empty user}">
   	   <a onclick="window.history.go(-1)">back</a>
   </c:if>
   
   <c:if test="${!empty user}">
  
	<%@include file="HeadTemplate.jsp"%>
	<!-- <input type="button" onclick="showAddBtn();" value="添加"/> -->
	<input type="button" value="刷新" onclick="javascript:history.go(0);"/>
	<div class="comment">
		<ul class="title">
			<li>编号</li>
			<li>标题</li>
			<li>时间</li>
			<li>状态</li>
			<li>操作</li>
		</ul>
		<c:forEach items="${articleList }" var="article">
			<ul>
				<li>${article.id }</li>
				<li>${article.title }</li>
				<li><fmt:formatDate value="${article.pdate }" pattern="yyyy-MM-dd HH:mm:ss" /></li>
				<li>
					<c:if test="${article.isleaf==1 }">
						<input type="button" value="SHOW" class="currentBtnShow" />
					</c:if>
					<c:if test="${article.isleaf==0 }">
						<input type="button" value="HIDDEN" class="currentBtnHidden" />
					</c:if>
				</li>
				<li>
					<a href="<%=basePath%>comment/queryListByArticle.do?articleid=${article.id }">评论</a>
				</li>
			</ul>
		</c:forEach>
	</div>
	
	</c:if>
	
</body>
</html>