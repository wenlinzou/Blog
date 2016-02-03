function updateCommentView(url, articleid, id, isshow){
	var realurl = url+"comment/updateCommentShow.do?isshow="+isshow+"&articleid="+articleid+"&id="+id;
	window.location.href = realurl;
}