<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>后台登陆页面</title>
    
	<link href="<%=basePath%>plugjs/back/css/loginstyle.css" rel="stylesheet" type="text/css" media="all"/>
<!-- Custom Theme files -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<meta name="keywords" content="Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<!--Google Fonts-->
<link href='http://fonts.useso.com/css?family=Roboto:500,900italic,900,400italic,100,700italic,300,700,500italic,100italic,300italic,400' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>

<script type="text/javascript" src="<%=basePath%>plugjs/front/js/jquery.js"></script>
  </head>
  
  <body>
  <div class="login">
	<h2>Accessd Form</h2>
	<div class="login-top">
		<h1>LOGIN FORM</h1>
		<form action="<%=basePath %>user/login.shtml" method="post">
			<input type="text" name="username" value="Username" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '';}">
			<input type="password" name="password" value="password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'password';}">
	    
	    <div class="forgot">
	    	<a style="cursor: pointer" onclick="forgotPwd();">forgot Password</a>
	    	<input type="submit" value="Login" >
	    </div>
	    </form>
	</div>
	<div class="login-bottom">
		<h3>New User &nbsp;<a href="javascript:void(0);">Register</a>&nbsp; Here</h3>
	</div>
	</div>	
	<script type="text/javascript">
		function forgotPwd(){
			var username = document.getElementsByName("username")[0].value;
			$.ajax({
				url : "<%=basePath%>user/forgetPwd.do",
				type: 'post',
				data: {"username":username},
				dataType:"json",
				error: function(){
					alert('操作时出错了！');
				},
				success: function(data){
					alert(data.message);
				}
			});
		}
	</script>
</body>
</html>
