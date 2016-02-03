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
<link rel="shortcut icon"
	href="<%=basePath%>plugjs/front/images/favicon.ico" />
<link rel="stylesheet" href="<%=basePath%>plugjs/front/css/style.css">
<link rel="stylesheet" href="<%=basePath%>css/articleDetail.css">

<script src="<%=basePath%>plugjs/front/js/jquery.js"></script>
<script src="<%=basePath%>plugjs/front/js/jquery-migrate-1.1.1.js"></script>
<script src="<%=basePath%>plugjs/front/js/superfish.js"></script>
<script src="<%=basePath%>plugjs/front/js/jquery.equalheights.js"></script>
<script src="<%=basePath%>plugjs/front/js/jquery.easing.1.3.js"></script>
<script src="<%=basePath%>plugjs/front/js/jquery.ui.totop.js"></script>

<script src="<%=basePath%>js/articleDetail.js"></script>
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
    <div class="grid_9">
      <h3>Recent Posts</h3>
      <div class="blog">
        <time datetime="2013-01-01"><fmt:formatDate value="${article.pdate}" type="time" pattern="dd"/><br/>${article.shortmon }</time>
        <div class="extra_wrapper">
          <div class="text1 upp">${article.title }</div>
          <div class="links">Posted by <a href="javascript:void(0);">wenlinzou</a><a href="#" class="comment">Views Comment(s)&nbsp;${article.click }</a></div>
        </div>
        <div class="clear"></div>
        <div class="extra_wrapper">
          ${article.cont }
          <br>
          <!-- <a onclick="window.history.go(-1)" class="btn">Back</a> -->
        </div>
        
        <div>
		<form id="saveForm" onsubmit="return checkName();" method="post" class="basic-grey">
		<h1>Contact Form
		<span>Please fill all the texts in the fields.</span>
		</h1>
		<label>
		<input type="hidden" name="articleid" value='${article.id }'/>
		<span>Your Name :</span>
		<input id="name" type="text" name="visitname" placeholder="Your Full Name" />
		</label>
		<label>
		<span>Your Email :</span>
		<input id="email" type="email" name="email" placeholder="Valid Email Address" />
		</label>
		<label>
		<span>Message :</span>
		<textarea id="message" name="comment" placeholder="Your Message to Us"></textarea>
		</label>
		<!-- <label>
		<span>Subject :</span><select name="selection">
		<option value="Job Inquiry">Job Inquiry</option>
		<option value="General Question">General Question</option>
		</select>
		</label> -->
		<label>
		<span>&nbsp;</span>
		<input type="submit" class="button" value="Send" />
		</label>
		</form>
		</div>


		<div class="commentStyle">
		<h3>Comments</h3>
			<c:forEach items="${comments }" var="comment">
			<dl>
				<dt>&nbsp;&nbsp;<span class="commentTitle">${comment.visitname }</span>&nbsp;
					<span><fmt:formatDate value="${comment.date }" type="both" dateStyle="long" timeStyle="long"/></span></dt>
				<dd>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="commentCont">${comment.comment }</span></dd>
			</dl>
			</c:forEach>
		</div>
      </div>
     
    </div>
    <div class="grid_3">
      <h3>类别</h3>
      <ul class="list2 l1">
      
      	<c:forEach items="${categoryList }" var="category">
        	<li><a href="<%=basePath%>articleFront/queryAllArticlePage.shtml?pid=${category.id}">${category.name }</a></li>
        </c:forEach>
        
      </ul>
      <h3>存档</h3>
      <ul class="list2 l1">
        <!-- <li><a href="#">August 2012</a></li> -->
        <c:forEach items="${monthMap }" var="months">
      		<li><a href="<%=basePath%>articleFront/queryAllArticlePage.shtml?date=${months.key }">${months.value }</a></li>
      	</c:forEach>
        
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