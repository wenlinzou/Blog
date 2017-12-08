
getArticleCommentsList();
var basePath = "/Blog";
function getArticleCommentsList() {
	$.ajax({
		type:"POST",
		url:"article/queryAllCommentAjax.do",
		success : function(data) {
			if (data.res_code == 0) {
				 //取出数据放入到dom中
				var count = 0;
				var articleComments = data.articleComments;
				var s = "";
				$.each(articleComments, function(i, o) {  
					s+='<ul>';
					s+='<li>' + (++i) + '</li>';
	                s+='<li>' + o.title+'</li>';
	                var d = new Date(o.pdate);
	                var date = formatDateTime(d);
	                s+='<li>' + date + '</li>';
	                if (o.isleaf == 1) {
	                	s+='<li><input type="button" value="SHOW" class="currentBtnShow" /></li>';
	                } else {
	                	s+='<li><input type="button" value="HIDDEN" class="currentBtnHidden" /></li>';
	                }
	                s+='<li><a href="' + basePath + '/comment/queryListByArticle.do?articleid=' + o.id + '">' + o.comments + '条</a></li>';
	                s+='</ul>';
	            });
	            if (articleComments.length > 0) {
	                $("#articleComments").html(s);
	            }
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
