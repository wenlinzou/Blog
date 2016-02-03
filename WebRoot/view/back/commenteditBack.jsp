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
    
    <title>管理文章评论状态</title>
    
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/HeadTemplate.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/commentedit.css">
	<script type="text/javascript" src="<%=basePath %>js/commenteditBack.js"></script>
  </head>
  
  <body>
  
   <c:if test="${empty user}">
   	   <a onclick="window.history.go(-1)">back</a>
   </c:if>
   
   <c:if test="${!empty user}">
  
	<%@include file="HeadTemplate.jsp"%>
	<a href="<%=basePath %>article/queryAllComment.do">${article.title }</a>
	
	<div class="comment">
		<c:forEach items="${comments }" var="comment">	
		<table>
			<tr class="commenttitle">
				<td>${comment.visitname }</td>
				<td><fmt:formatDate value="${comment.date }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>状态</td>
			</tr>
			<tr>
				<td class="commentleft">邮箱</td><td class="commentright">${comment.email }</td>
				<td>
					<c:if test="${comment.isshow==1 }">
						<input type="button" value="PASS" class="currentBtnPass" onclick="updateCommentView('<%=basePath%>',${comment.articleid}, ${comment.id}, 0);" />
					</c:if>
					<c:if test="${comment.isshow==0 }">
						<input type="button" value="FAIL" class="currentBtnFail" onclick="updateCommentView('<%=basePath%>',${comment.articleid}, ${comment.id}, 1);" />
					</c:if>
				</td>
			</tr>
			<tr>
				<td class="commentleft">内容</td><td class="commentright">${comment.comment }</td>
			</tr>
		</table>
		</c:forEach>
	</div>
	
	</c:if>
	
</body>
</html>