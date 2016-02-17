function updateArticleView(url, id, isleaf){
	var realurl = url+"article/update.do?isleaf="+isleaf+"&id="+id;
	window.location.href = realurl;
}
function updateArticleIsTop(url, id, istop){
	var realurl = url+"article/update.do?rootid="+istop+"&id="+id;
	window.location.href = realurl;
}
function addArticle(){
	var jumpurl = "article/queryLoadAdd.shtml";
	window.location.href = jumpurl;
}