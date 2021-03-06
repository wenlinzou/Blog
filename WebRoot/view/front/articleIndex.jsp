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

<title>爱折腾生活</title>


<link rel="icon" href="<%=basePath%>plugjs/front/images/favicon.ico">
<link rel="shortcut icon" href="<%=basePath%>plugjs/front/images/favicon.ico" />
<link rel="stylesheet" href="<%=basePath%>plugjs/front/css/style.css">
<link rel="stylesheet" href="<%=basePath%>css/articleIndex.css">

<script src="<%=basePath%>plugjs/front/js/jquery.js"></script>
<script src="<%=basePath%>plugjs/front/js/jquery-migrate-1.1.1.js"></script>
<script src="<%=basePath%>plugjs/front/js/superfish.js"></script>
<script src="<%=basePath%>plugjs/front/js/jquery.equalheights.js"></script>
<script src="<%=basePath%>plugjs/front/js/jquery.easing.1.3.js"></script>
<script src="<%=basePath%>plugjs/front/js/jquery.ui.totop.js"></script>
<script src="<%=basePath%>js/articleIndex.js"></script>
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
    <h1><a href="articleFront/queryAllArticlePage.shtml"><img src="<%=basePath%>plugjs/front/images/logo.png" alt="Gerald Harris attorney at law"></a> </h1>
          
         
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
      <h3><!-- Recent Posts -->最近的帖子</h3>
      
      <c:forEach items="${articleList }" var="article">
      
      <div class="blog">
        <time datetime="${article.pdate }"><fmt:formatDate value="${article.pdate}" type="time" pattern="dd"/><br/>${article.shortmon }</time>
        <div class="extra_wrapper">
          <div class="text1 upp"><a target="_blank" href="<%=basePath%>articleFront/queryDetailById.shtml?id=${article.id}">${article.title }</a> </div>
          <div class="links">Posted by <a href="javascript:void(0);">wenlinzou</a>
          	<a href="javascript:void(0);" style="font-size:16px;" class="comment"><fmt:formatDate value="${article.pdate }" type="date" dateStyle="long"/></a>
          </div>
        </div>
        <div class="clear"></div>
        ${article.img }
        <div class="extra_wrapper">
         <div class="text" style="width:100%; height:200px;overflow:hidden; text-overflow:ellipsis; ">
          <p>
          ${article.cont } 
          </p>
          <br>
          </div>
          <!-- target="_blank" -->
         <a target="_blank" href="<%=basePath%>articleFront/queryDetailById.shtml?id=${article.id}" class="btn">Details</a>
        </div>
       <br>
      </div>
      </c:forEach>
      
      	<div class="pageCenter">
	     
	      <ul class="pageNum">
	      
	      <%-- <c:if test="${pageData.page > 1}">
	      <li><a href="<%=basePath%>articleFront/queryAllArticlePage.shtml?page=1<c:if test='${!empty pid }'>&pid=${pid }</c:if><c:if test='${!empty date }'>&date=${date }</c:if>">1&nbsp;</a></li>
	      </c:if> --%>
	      
			<c:forEach begin="1" end="${pageData.pager.pageCount }" varStatus="status">
				<c:if test="${status.count > (pageData.page - 3) && status.count < (pageData.page + 3) }">
					<%-- <li><a href="<%=basePath%>articleFront/queryAllArticlePage.shtml?page=${status.count }<c:if test='${!empty pid }'>&pid=${pid }</c:if><c:if test='${!empty date }'>&date=${date }</c:if>" <c:if test="${status.count == pageData.page }">class="currentPage"</c:if> >${status.count }</a></li> --%>
					<li><a href="<%=basePath%>articleFront/queryAllArticlePage.shtml?page=${status.count }<c:if test='${!empty pid }'>&pid=${pid }</c:if><c:if test='${!empty date }'>&date=${date }</c:if><c:if test='${!empty keyword }'>&keyword=${keyword }</c:if>"
						<c:if test="${status.count == pageData.page && pageData.page%2==0 }">class="currentPageOdd"</c:if>
						<c:if test="${status.count == pageData.page && pageData.page%2!=0 }">class="currentPageEven"</c:if> > ${status.count }</a>
					</li>
				</c:if>
			</c:forEach>
			
	     <%--  <c:if test="${pageData.page < pageData.pager.pageCount}">
	      <li><a href="<%=basePath%>articleFront/queryAllArticlePage.shtml?page=${pageData.pager.pageCount}<c:if test='${!empty pid }'>&pid=${pid }</c:if><c:if test='${!empty date }'>&date=${date }</c:if>">&nbsp;${pageData.pager.pageCount}</a></li>
	      </c:if> --%>
	      	<li><c:if test="${pageData.page < pageData.pager.pageCount}">共:${pageData.pager.pageCount}</c:if></li>
	      </ul>
	      
		</div>
		
    </div>
    <div class="grid_3">
      <h3>类别</h3>
      <ul class="list2 l1">
      	<c:forEach items="${categoryList }" var="category">
        	<li><a href="<%=basePath%>articleFront/queryAllArticlePage.shtml?pid=${category.id}">${category.name }</a></li>
        </c:forEach>
        
        <!-- <li><a href="#">Sem psum dr sit ametre conse</a></li>
        <li><a href="#">Rame sum dr sit ame consec</a></li>
        <li><a href="#">Bem psum dr sit ameteko </a></li>
        <li><a href="#">Nem dsum dr sit amewas </a></li>
        <li><a href="#">Vcem psum dr sit </a></li> -->
      </ul>
      <h3>存档</h3>
      <ul class="list2 l1">
      	
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
    <!-- <div class="grid_2">
      <h4>Contact Us:</h4>
      TEL: 1-800-234-5678<br><a href="#">info@demolink.org</a>
    </div> -->
    <div class="grid_2" id="searchKey">
        <h4>
        <input class="serach_input" placeholder="Keyword ..." type="text" id="keyword"/>
        </h4>
        <input class="serach_btn" type="button" value="Search" name="name" onclick="searchWord();"/>
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
      	<a target="_blank" href="http://www.miitbeian.gov.cn/">苏ICP备16007444号</a>
     &copy; Copyright &copy; 2016.Wenlinzou All rights reserved.<a target="_blank" href="http://sc.chinaz.com/moban/"></a>
     </div></div>
     <div class="clear"></div>
  </div>

</footer>

<!-- <div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div> -->
</body>
</html>