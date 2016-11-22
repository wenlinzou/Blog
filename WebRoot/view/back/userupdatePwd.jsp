<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">

<title>修改密码页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/userupdatePwd.css">
	<script type="text/javascript" src="<%=basePath%>plugjs/front/js/jquery.js"></script>
</head>

<body>
	
	<div>
	<center>
	<form action="<%=basePath%>register.html" method="post" id="updateForm">
	
	    <table border="0px" >
	    	
	    	<input type="hidden" name="userid" value="${user.id }">
	    	<tr>
	    		<td colspan="2" class="titlecenter">${user.username }</td>
	    	</tr>
	    	<tr>
	    		<td class="cueInfo">密码&nbsp;</td>
	    		<td>
	    			<input type="password" name="password" value="${form.password }" class="inputReg">
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<td class="cueInfo">确认密码&nbsp;</td>
	    		<td>
	    			<input type="password" name="password2" value="${form.password2 }" class="inputReg">
	    		</td>
	    	</tr>
	    	
	    	<tr>
	    		<td>
	    		</td>
	    		<td>
	    			<input type="button" value="修改密码" class="btnReg" onclick="pwdUpdate();"/>
	    		</td>
	    	</tr>
	    </table>
    </form>
    
    </center>
    </div>
	<br>
	<script type="text/javascript">
		function pwdUpdate(){
			var formData = $("#updateForm").serialize(); 
			$.ajax({
				url : "<%=basePath%>user/canUpdatePwd.do",
				type: 'post',
				data: formData,
				dataType:"json",
				error: function(){
					alert('修改时出错了！');
				},
				success: function(data){
					console.log(data);
				    if(data.result){
						alert(data.message);
						window.location.href="<%=basePath%>"+data.url;
				    }else{
						alert(data.message);
						window.close();
				    }
				}
			});
		}
	</script>
</body>
</html>
