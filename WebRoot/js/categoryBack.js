getCategorysList();
var basePath = "/Blog";
function getCategorysList() {
	$.ajax({
		type:"POST",
		url:"category/queryAllAjax.do",
		success : function(data) {
			if (data.res_code == 0) {
				 //取出数据放入到dom中
				var count = 0;
				var categorys = data.categorys;
				var s = "";
				$.each(categorys, function(i, o) {  
					s+='<ul>';
					s+='<li>' + (++i) + '</li>';
	                s+='<li>' + o.name+'</li>';
	                s+='<li><a href="' + basePath + '/category/queryById.do?id=' + o.id + '">修改</a></li>';
	                s+='</ul>';
	            });
	            if (categorys.length > 0) {
	                $("#categoryList").html(s);
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

function i$(inputid) {
	return document.getElementById(inputid);
}
function showAddBtn() {
	var nodediv = i$('showAddCategoryDiv');
	if (nodediv.style.display == "none") {
		nodediv.style.display = "block";
	} else {
		nodediv.style.display = "none";
	}
}