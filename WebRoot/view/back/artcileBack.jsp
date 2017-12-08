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
    
    <title>管理文章</title>
    
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/HeadTemplate.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/article.css">
	<script type="text/javascript" src="<%=basePath%>plugjs/front/js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>plugjs/back/js/dateUtils.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/articleBack.js"></script>
  </head>
  
  <body>
  
   <c:if test="${empty user}">
   	   <a onclick="window.history.go(-1)">back</a>
   </c:if>
   
   <c:if test="${!empty user}">
  
	<%@include file="HeadTemplate.jsp"%>
	<input type="button" value="添加" onclick="addArticle();"/>
	<div class="article">
		<ul class="title">
			<li>编号</li>
			<li>类别</li>
			<li style="width:7%;">置顶</li>
			<li style="width:20%;">标题</li>
			<!-- <li>内容</li> -->
			<li>时间</li>
			<li>状态</li>
			<li>操作</li>
		</ul>
		<div id="articleInfoList"></div>
		<%-- <c:forEach items="${articleList }" var="article">
			<ul name="articleul" >
				<li>${article.id }</li>
				<li>
					<select>
						<c:forEach items="${categoryList }" var="category">
						<option value='${category.id }' <c:if test='${category.id==article.pid }'>selected="selected"</c:if>>${category.name }</option>
						</c:forEach>
					</select>
				</li>
				<li  style="width:7%;">
					<c:if test="${article.rootid==1 }">
						<input type="button" value="ON" class="currentBtnTop" onclick="updateArticleIsTop('<%=basePath%>', ${article.id}, 0);" />
					</c:if>
					<c:if test="${article.rootid==0 }">
						<input type="button" value="OFF" class="currentBtnNormal" onclick="updateArticleIsTop('<%=basePath%>', ${article.id}, 1);" />
					</c:if>
				</li>
				<li style="width:20%;">${article.title }</li>
				<li><fmt:formatDate value="${article.pdate }" pattern="yyyy-MM-dd HH:mm:ss" /></li>
				<li>
					<c:if test="${article.isleaf==1 }">
						<input type="button" value="SHOW" class="currentBtnShow" onclick="updateArticleView('<%=basePath%>', ${article.id}, 0);" />
					</c:if>
					<c:if test="${article.isleaf==0 }">
						<input type="button" value="HIDDEN" class="currentBtnHidden" onclick="updateArticleView('<%=basePath%>', ${article.id}, 1);" />
						<input type="button" value="SHARE" class="currentBtnShare" onclick="updateArticleShare('<%=basePath%>', ${article.id}, 1);" />
						<input type="button" value="CLOSE" class="currentBtnShareClose" onclick="updateArticleShare('<%=basePath%>', ${article.id}, 0);" />
					</c:if>
				</li>
				<li>
					<input type="button" class="editBtn" value="修改" onclick="editArticle('<%=basePath%>',${article.id})"/>
					<input type="button" class="editBtn" value="查看" onclick="viewArticle('<%=basePath%>',${article.id})"/>
				</li>
			</ul>
		</c:forEach> --%>
	</div>
	
	<div class="page pageCenter">
		<ul class="pageNum"  id="pageDiv">
		<%-- <c:forEach begin="1" end="${pageData.pager.pageCount }" varStatus="status">
			<c:if test="${status.count > (pageData.page - 3) && status.count < (pageData.page + 3) }">
				<li><a href="<%=basePath%>article/queryAll.shtml?page=${status.count }<c:if test='${!empty pid }'>&pid=${pid }</c:if><c:if test='${!empty date }'>&date=${date }</c:if><c:if test='${!empty keyword }'>&keyword=${keyword }</c:if>"
					<c:if test="${status.count == pageData.page && pageData.page%2==0 }">class="currentPageOdd"</c:if>
					<c:if test="${status.count == pageData.page && pageData.page%2!=0 }">class="currentPageEven"</c:if> > ${status.count }</a>
				</li>
			</c:if>
		</c:forEach>
			<li>共</li>
			<li>
				<a href="<%=basePath%>article/queryAll.shtml?page=${pageData.pager.pageCount }<c:if test='${!empty pid }'>&pid=${pid }</c:if><c:if test='${!empty date }'>&date=${date }</c:if><c:if test='${!empty keyword }'>&keyword=${keyword }</c:if>">${pageData.pager.pageCount }</a>
			</li>
			<li>页</li> --%>
		</ul>
	</div>
	
	<div class="alert">
		<div><span id="sharemsg"></span></div>
		<div class="input-box"><input type="button" class="confirm_btn" onclick="hiddenShareDiv();" value="确认"/></div>
	</div>
	<div class="mask"></div>
	
	</c:if>
	
</body>
</html>