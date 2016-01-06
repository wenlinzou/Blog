/*window.onload=function(){
	articleAjax(1);
};*/

function articleAjax(page){
	$.ajax({
		type : 'POST',
		url : 'articleFront/queryAllArticlePageAjax.shtml?page='+page,
		dataType : 'json',
		success : function(data){
			$.each(data.articleList, function(i, item){
				var obj = "<div clas='blog'>" +
				"<time datetime=" +item.pdate+"><fmt:formatDate value=${"+item.pdate+"} type='time' pattern='dd'/><br/>"+item.shortmon+"</time>"+
					"<div class='extra_wrapper'>"+
						"<div class='text1 upp'>"+item.title+"</div>"+
						"<div class='links'>Posted by <a href='javascript:void(0);'>wenlinzou</a>"+
			          		"<a href='javascript:void(0);' class='comment'><fmt:formatDate value=${"+item.pdate+" } type='date' dateStyle='long'/></a>"+
			          	"</div>"+
					"</div>"+
					"<div class='clear'></div>"+ item.img +
					"<div class='extra_wrapper'>"+
			         	"<div class='text' style='width:100%; height:200px;overflow:hidden; text-overflow:ellipsis; white-space:nowrap;'>"+
				         	"<p>"+ item.cont+ "</p>"+
				         	"<br>"+
			         	"</div>"+
			         "<a href='articleFront/queryDetailById.shtml?id="+item.id+"' class='btn'>Details</a>"+
			        "</div>"+
				"</div>";
				$(".grid_9").append(obj);
			});
				
		},error:function(e){
			alert("error");
		}
	});
};
var page = 1;

/*$(window).scroll(function() {
	check();
});
*/

function check(){
	var docHight = $(document).height();
	var scrollHeight = $(document).scrollTop();
	var offsetHeight = $(window).height();
	if(docHight <= (scrollHeight + offsetHeight+2)){
		page++;
		articleAjax(page);
	}
}