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