<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title><c:if test="${!empty article.title }">${article.title }</c:if><c:if test="${empty article.title }">Blog</c:if></title>


<link rel="icon" href="<%=basePath%>plugjs/front/images/favicon.ico">
<link rel="shortcut icon" href="<%=basePath%>plugjs/front/images/favicon.ico" />
<link rel="stylesheet" href="<%=basePath%>plugjs/front/css/style.css">
<link rel="stylesheet" href="<%=basePath%>css/articleDetail.css">

<script src="<%=basePath%>plugjs/front/js/jquery.js"></script>
<script src="<%=basePath%>plugjs/front/js/jquery-migrate-1.1.1.js"></script>
<script src="<%=basePath%>plugjs/front/js/superfish.js"></script>
<script src="<%=basePath%>plugjs/front/js/jquery.equalheights.js"></script>
<script src="<%=basePath%>plugjs/front/js/jquery.easing.1.3.js"></script>
<script src="<%=basePath%>plugjs/front/js/jquery.ui.totop.js"></script>

<script src="<%=basePath%>js/articleShare.js"></script>
<script>
	$(window).load(function() {
		$().UItoTop({
			easingType : 'easeOutQuart'
		});
	});
</script>
</head>

 <body  class="">
<!--==============================header=================================-->
 <header> 
  <div class="container_12">
    <div class="grid_12"> 
    <h1><a href="<%=basePath%>articleFront/queryAllArticlePage.shtml"><img src="<%=basePath%>plugjs/front/images/logo.png" alt="Gerald Harris attorney at law"></a> </h1>
          
         
           <div class="clear"></div>
      </div>
<div class="menu_block">
           <nav  class="" >
             <ul class="sf-menu">
                   <li><a href="javascript:void(0);">Home</a></li>
                   <li><a href="javascript:void(0);">About</a>
                     <ul>
                        <li><a href="javascript:void(0);"> Agency</a></li>
                        <li><a href="javascript:void(0);">News</a></li>
                        <li><a href="javascript:void(0);">Team</a></li>
                     </ul>
                   </li>
                   <li><a href="javascript:void(0);">Gallery</a></li>
                   <li><a href="javascript:void(0);">Tours</a></li>
                   <li><a href="javascript:void(0);">Journey</a></li>
                   <li><a href="javascript:void(0);">Contacts</a></li>
                 </ul>
            </nav>
           <div class="clear"></div>
           </div>
           <div class="clear"></div>
          </div>
</header>

<div class="main">
<!--=======content================================-->

<div class="content">
  <div class="container_12">
        
        <div>
		<form id="checkShareCode" action="<%=basePath %>articleFront/queryDetailById.shtml" method="post" class="basic-grey">
		<h1>Contact Form
		<span>Please fill all the texts in the fields.</span>
		</h1>
		<label>
		<input type="hidden" name="id" value='${articleId }' id="articleid"/>
		<span>Your ShareCode :</span>
		<input id="shareCode" type="text" name="shareCode" placeholder="Share Code" />
		</label>
		<label>
		<span>&nbsp;</span>
		<input type="button" onclick="sendShareCode();" class="button" value="Send" />
		</label>
		</form>
		</div>

     
  </div>
</div>

<!--=======bottom================================-->
<div class="bottom_block">
  <div class="container_12">
    <div class="grid_2 prefix_2">
      <ul>
        <li><a href="#">FAQS Page</a></li>
        <li><a href="#">People Say</a></li>
      </ul>
    </div>
    <div class="grid_2">
      <ul>
        <li><a href="#">Useful links</a></li>
        <li><a href="#">Partners</a></li>
      </ul>
    </div>
    <div class="grid_2">
      <ul>
        <li><a href="#">Insurance</a></li>
        <li><a href="#">Family Travel</a></li>
      </ul>
    </div>
    <div class="grid_2">
      <h4>Contact Us:</h4>
      TEL: 1-800-234-5678<br><a href="#">info@demolink.org</a>
     
    </div>
    <div class="clear"></div>
    </div>
  </div>
<!--==============================footer=================================-->

</div>
<footer>    
  <div class="container_12">
  <div class="grid_12">
    <div class="socials">
      <a href="#"></a>
      <a href="#"></a>
      <a href="#"></a>
      <a href="#"></a>
    </div>
      <div class="copy">
     &copy; Copyright &copy; 2015.Company name All rights reserved.<a target="_blank" href="http://sc.chinaz.com/moban/"></a>
     </div></div>
     <div class="clear"></div>
  </div>

</footer>

</body>
</html>