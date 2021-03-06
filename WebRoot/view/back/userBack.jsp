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
    <title>用户</title>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/HeadTemplate.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/user.css">
	
	<script src="<%=basePath%>plugjs/front/js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/userBack.js"></script>
 </head>
   <body>
   
   <c:if test="${empty user}">
   	   <a onclick="window.history.go(-1)">back</a>
   </c:if>
   
   <c:if test="${!empty user}">
   
	<%@include file="HeadTemplate.jsp"%>
	<div class="user">
		<input type="button" onclick="showAddBtn();" value="添加"/>
		<ul class="title">
			<li>编号</li>
			<li>名称</li>
			<li>邮箱</li>
			<li>昵称</li>
			<li>操作</li>
		</ul>
		<div id="userList"></div>
		<%-- <c:forEach items="${users }" var="user"> --%>
			<%-- <ul id="userList">
				<li>${user.id }</li>
				<li>${user.username }</li>
				<li>${user.email }</li>
				<li>${user.nickname }</li>
				<li><a href="<%=basePath %>user/queryById.do?id=${user.id }">修改</a></li>
			</ul> --%>
		<%-- </c:forEach> --%>
	</div>
	<div id="showAddDiv" class="addUser" style="display: none;">
		<form onsubmit="return checkName();" method="post" id="saveForm">
		<ul><li>名称</li><li>密码</li><li>昵称</li></ul>
		<ul>
			<li><input class="inputadd" type="text" name="username"/></li>
			<li><input class="inputadd" type="text" name="password"/></li>
			<li><input class="inputadd" type="text" name="nickname"/></li>
			<li><input type="submit" value="添加"/></li>
		</ul>
		</form>
	</div>
	
	</c:if>
	
</body>
</html>