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

<title>Blog</title>


<link rel="icon" href="<%=basePath%>plugjs/front/images/favicon.ico">
<link rel="shortcut icon"
	href="<%=basePath%>plugjs/front/images/favicon.ico" />
<link rel="stylesheet" href="<%=basePath%>plugjs/front/css/style.css">
<script src="<%=basePath%>plugjs/front/js/jquery.js"></script>
<script src="<%=basePath%>plugjs/front/js/jquery-migrate-1.1.1.js"></script>
<script src="<%=basePath%>plugjs/front/js/superfish.js"></script>
<script src="<%=basePath%>plugjs/front/js/jquery.equalheights.js"></script>
<script src="<%=basePath%>plugjs/front/js/jquery.easing.1.3.js"></script>
<script src="<%=basePath%>plugjs/front/js/jquery.ui.totop.js"></script>
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
    <h1><a href="articleFront/queryAllArticle.shtml"><img src="<%=basePath%>plugjs/front/images/logo.png" alt="Gerald Harris attorney at law"></a> </h1>
          
         
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
                   <li class="current"><a href="javascript:void(0);">Blog</a></li>
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
    <div class="grid_9">
      <h3><!-- Recent Posts -->最近的帖子</h3>
      
      <c:forEach items="${articleList }" var="article">
      
      <div class="blog">
        <time datetime="${article.pdate }"><fmt:formatDate value="${article.pdate}" type="time" pattern="dd"/><br>
${article.shortmon }</time>
        <div class="extra_wrapper">
          <div class="text1 upp">${article.title } </div>
          <div class="links">Posted by <a href="javascript:void(0);">wenlinzou</a>
          	<a href="javascript:void(0);" class="comment"><fmt:formatDate value="${article.pdate }" type="date" dateStyle="long"/></a>
          </div>
        </div>
        <div class="clear"></div>
        ${article.img }
        <%-- <img src="<%=basePath%>plugjs/front/images/page5_img1.jpg" alt="" class="img_inner fleft"> --%>
        <div class="extra_wrapper">
         <div class="text" style="width:100%; height:200px;overflow:hidden; text-overflow:ellipsis; white-space:nowrap;">
          <p>
          
          <!-- Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse jew wligulawe dolor, condimentum ac justo sed, tincidunt commodo molity wer massarete. Nulla non urnatr nisi. Donec varius lectus in vestibulum auctor. Spendisse magna veliternowe dignissim eu commodo ut vestibulum nectro quam. Pellentesque imperdiet velit neque. Ut vestibulum mi sit ametwertilo ornare consectetur. Quisque sed quamhy loi justo. Nulla congue sed turpis nec lacinia. Nulla facilisi. Ut sit amet gravidatylo wtellus. Morbi id wer nolit consequat eros. -->
          ${article.cont } 
          </p>
          <!-- Vivamus imperdiet ante vitae lorem varius tristique meli. Phasellus tristique lectus id volutpat condimentum. Mauris quam lectus cursus at congue nec ultrices luctus orci quam lectus cursus at congue. -->
          
          <br>
          </div>
         <a href="<%=basePath%>articleFront/queryDetailById.shtml?id=${article.id}" class="btn">Details</a>
        </div>
         
      </div>
      
      </c:forEach>
      
     
    </div>
    <div class="grid_3">
      <h3>类别</h3>
      <ul class="list2 l1">
      	<c:forEach items="${categoryList }" var="category">
        	<li><a href="<%=basePath%>articleFront/queryByThing.shtml?pid=${category.id}">${category.name }</a></li>
        </c:forEach>
        
        <!-- <li><a href="#">Sem psum dr sit ametre conse</a></li>
        <li><a href="#">Rame sum dr sit ame consec</a></li>
        <li><a href="#">Bem psum dr sit ameteko </a></li>
        <li><a href="#">Nem dsum dr sit amewas </a></li>
        <li><a href="#">Vcem psum dr sit </a></li> -->
      </ul>
      <h3>存档</h3>
      <ul class="list2 l1">
      	<c:forEach items="${dateList }" var="dates">
      		<li><a href="">${dates }</a></li>
      	</c:forEach>
        <li><a href="#">August 2012</a></li>
        <!-- <li><a href="#">July 2012</a></li>
        <li><a href="#">June 2012</a></li>
        <li><a href="#">May 2012</a></li>
        <li><a href="#">April 2012</a></li>
        <li><a href="#">March 2012</a></li>
        <li><a href="#">February 2012</a></li>
        <li><a href="#">January 2012</a></li>
        <li><a href="#">December 2011</a></li> -->
      </ul>
    </div>
    <div class="clear"></div>
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

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>