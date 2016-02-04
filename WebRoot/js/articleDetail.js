$(window).load(function() {
	loadComment();
});
/*加载comment*/
function loadComment(){
	var articleid = document.getElementById("articleid").value;
	$.ajax({
		type:"POST",
		url:"commentFront/queryCommectArticleAjax.do",
		data:"articleid=" + articleid,
		dataType:'json',
		success: function(data){
			/*var objData = data;
			if(typeof(objData.comments.length)!="undefined"){
				if(objData.comments.length>0){
					$("#commentDiv").empty();
					$("#commentDiv").prepend("<h3>Comments</h3>");
				}
			}*/
			
			$("#comtitles").empty();
			$.each(data.comments, function(i, item){
				var tempdate = new Date(item.date); 
				var tempTime = tempdate.Format("yyyy年MM月dd hh时mm分ss秒");
					
				var commentobj = "<dl>"+
					"<dt><span class='commentTitle'>" + item.visitname + "</span>&nbsp;"+
					"<span>" + tempTime + "</span></dt>" +
					"<dd>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class='commentCont'>" + item.comment + "</span></dd>" +
					"</dl>";
				$("#comtitles").append(commentobj);
			});
			
		}
	});
}

function checkName(){
	var formData = $("#saveForm").serialize();
	$.ajax({
		type:"post",
		url:"commentFront/checkName.do",
		data:formData,
		dataType : 'json',
		success : function(data){
			if(data.canSave){
				alert(data.msg);
				loadComment();
				//清空form
				$('#saveForm')[0].reset();  
			}else{
				alert(data.msg);
			}
		},
		error:function(){
			alert("error");
		}
	});
}

Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}