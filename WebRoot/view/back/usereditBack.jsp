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

<title>编辑用户</title>


<link rel="stylesheet" type="text/css" href="<%=basePath%>css/HeadTemplate.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/user.css">

<script src="<%=basePath%>plugjs/front/js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/usereditBack.js"></script>
<style>

</style>
</head>

<body>
	<c:if test="${empty user}">
   	   <a onclick="window.history.go(-1)">back</a>
   </c:if>
   
   <c:if test="${!empty user}">
   
	<%@include file="HeadTemplate.jsp"%>
	<input type="button" value="刷新" onclick="javascript:history.go(0);"/>
	<div class="user">
		<form action="<%=basePath%>user/update.do" method="post">
			<input type="hidden" value='${user.id }' name="id"/>
			<ul class="title">
				<li>名称</li>
				<li>昵称</li>
				<li>邮箱</li>
				<li>操作</li>
			</ul>

			<ul>
				<li><input class="inputadd" type="text" value='${user.username }' name="username" /></li>
				<li><input class="inputadd" type="text" value='${user.nickname }' name="nickname" /></li>
				<li><input class="inputadd" type="text" value='${user.email }' name="email" /></li>
				<li><input type="submit" value="确认" /><input type="button" class="updatepwd_btn" id="alert_btn" value="修改密码" /></li>
			</ul>
		</form>
	</div>
	<div class="alert">
		<form id="updatePwd"  method="post" class="form">
			<input type="hidden" value='${user.id }' name="id"/>
			<div class="input-box"><label>新密码</label><input type="password" name="password"></div>
			<div class="input-box"><label>再次输入</label><input type="password" name="password1"></div>
			<div class="input-box"><span id="errormsg"></span></div>
			<div class="input-box"><input type="button" class="confirm_btn" onclick="updatePassword();" value="确认"/></div>
		</form>
	</div>
	<div class="mask"></div>

	</c:if>
</body>
</html>