
var basePath = "/Blog/";
var page = 1;
var pid = "", date = "", keyword = "";
getArticleList(page, pid, date, keyword);
function getArticleList(page, pid, date, keyword) {
	$.ajax({
		type:"POST",
		url:"article/queryAllAjax.do",
		data:"page=" + page +"&pid=" + pid + "&date=" + date + "&keyword=" + keyword,
		success : function(data) {
			if (data.res_code == 0) {
				 //取出数据放入到dom中
				var count = 0;
				var articles = data.body.articleList;
				var categorys = data.body.categoryList;
				var s = "";
				var p = "";
				var pageCount = data.body.pageData.pager.pageCount;
				var page = data.body.pageData.page;
				var pageSize = data.body.pageData.pager.pageSize;
				$.each(articles, function(i, o) {  
	                s+='<ul name="articleul" >';
	                s+='<li>' + ((++i) + ((page-1) * pageSize)) + '</li>';
	                s+='<li>';
	                s+='<select>';
	                		for ( var j = 0; j < categorys.length; j++) {
	                			s+='<option value="' + categorys[j].id + '" ';
								if (o.pid == categorys[j].id) {s+= 'selected="selected"';}
								s+='>'+ categorys[j].name + '</option>';
							}
					s+='</select>';
					s+='</li>';
					s+='<li  style="width:7%;">';
					if (o.rootid == 1) {
						s+='<input type="button" value="ON" class="currentBtnTop" onclick="updateArticleIsTop(\'' + basePath + '\', ' + o.id + ', 0);" />';
					} else if (o.rootid == 0) {
						s+='<input type="button" value="OFF" class="currentBtnNormal" onclick="updateArticleIsTop(\'' + basePath + '\', ' + o.id + ', 1);" />';
					}
					s+='</li>';
					s+='<li style="width:20%;">' + o.title + '</li>';
					var d = new Date(o.pdate);
					var date = formatDateTime(d);
					s+='<li>' + date + '</li>';
					s+='<li>';
					if (o.isleaf == 1) {
						s+='<input type="button" value="SHOW" class="currentBtnShow" onclick="updateArticleView(\'' + basePath + '\', ' + o.id + ', 0);" />';
					} else if (o.isleaf == 0) {
						s+='<input type="button" value="HIDDEN" class="currentBtnHidden" onclick="updateArticleView(\'' + basePath + '\', ' + o.id + ', 1);" />';
						s+='<input type="button" value="SHARE" class="currentBtnShare" onclick="updateArticleShare(\'' + basePath + '\', ' + o.id + ', 1);" />';
						s+='<input type="button" value="CLOSE" class="currentBtnShareClose" onclick="updateArticleShare(\'' + basePath + '\',' + o.id + ', 0);" />';
					}
					s+='</li>';
					s+='<li>';
					s+='<input type="button" class="editBtn" value="修改" onclick="editArticle(\'' + basePath + '\',' + o.id + ')"/>';
					s+='<input type="button" class="editBtn" value="查看" onclick="viewArticle(\'' + basePath + '\',' + o.id + ')"/>';
					s+='</li>';
					s+='</ul>';
	            });
				
				if (pageCount > 0) {
					for ( var i = 1; i <= pageCount; i++) {
						if (i > (page - 3) && i < (page + 3)) {
							p+='<li><a onclick="getArticleList('+ i + ', \''+ pid +'\', \'' + date + '\', \'' + keyword + '\');"';
							if (i == page && page % 2 == 0) {
								p+=' class="currentPageOdd"';
							} else if(i == page && page % 2 != 0) {
								p+=' class="currentPageEven"';
							}
							p+='>' + i +'</a>';
							p+='</li>';
						}
					}
				}
				p+='<li>共</li>';
				p+='<li>';
					p+='<a onclick="getArticleList(' + pageCount + ', \'' + pid + '\', \'' + date + '\', \'' + keyword + '\');">' + pageCount + '</a>';
				p+='</li>';
				p+='<li>页</li>';
				
	            if (articles.length > 0) {
	                $("#articleInfoList").html(s);
	            }
	            $("#pageDiv").html(p);
			} else {
				alert(data.res_msg);
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			alert(textStatus);
			alert("error");
			return false;
		}
	});
}

function editArticle(url,articleid){
	window.location.href = url + "article/queryById.do?id="+articleid;
}
function viewArticle(url,articleid){
	window.open(url + "articleFront/queryDetailById.shtml?id="+articleid);
}
function updateArticleView(url, id, isleaf){
	var realurl = url+"article/update.do?isleaf="+isleaf+"&id="+id;
	window.location.href = realurl;
}

function updateArticleShare(_url, id, isshare){
	$.ajax({
		type:"POST",
		url: _url + "article/updateShare.do",
		data : {
			articleid : id,
			isshare : isshare,
		},
		dataType : "json",
		success: function(data){
			if(data.can){
				showShareDiv();
				var sharemsg = "文章地址：" + data.shareUrl + " 密码：" + data.shareCode;
				$("#sharemsg").html(sharemsg);
			}else{
				alert(articleId + "文章关闭分享");
			}
		},
		error: function(data){
			alert("分享失败！");
		}
	});
}
function updateArticleIsTop(url, id, istop){
	var realurl = url+"article/update.do?rootid="+istop+"&id="+id;
	window.location.href = realurl;
}
function addArticle(){
	var jumpurl = "article/queryLoadAdd.shtml";
	window.location.href = jumpurl;
}
/*$(function(){
	showShareDiv();
	closeShareDiv();
});
*/
function showShareDiv(){
	$('.alert').show('slow');
	$('.mask').show('fast');
}
function closeShareDiv(){
	$('.mask').click(function(){
		$('.alert').hide('fast');
		$('.mask').hide('fast');
	});
}
function hiddenShareDiv(){
	$('.alert').hide('fast');
	$('.mask').hide('fast');
}

//更改行样式
var ulColorName;
function ulColor() {
	var collUlNodes = document.getElementsByName("articleul");

	for (var i = 0; i < collUlNodes.length; i++) {
		if (i % 2 == 1)
			collUlNodes[i].className = "mytrOne";
		else
			collUlNodes[i].className = "mytrTwo";
		collUlNodes[i].onmouseover = function() {
			ulColorName = this.className;
			this.className = "over";
		}
		collUlNodes[i].onmouseout = function() {
			this.className = ulColorName;
		}
	}
}
onload = function() {
	ulColor();
}