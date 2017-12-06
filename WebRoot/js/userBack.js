/*window.onbeforeunload = function(event) {
	if(confirm("确定离开此页面吗？")){
		window.location.href = "user/loginOut.do";
	}
}*/
getUserList();
var basePath = "/Blog";
function getUserList() {
	$.ajax({
		type:"POST",
		url:"user/queryAllAjax.do",
		success : function(data) {
			if (data.res_code == 0) {
				 //取出数据放入到dom中
				var count = 0;
				var users = data.users;
				var s = "";
				$.each(users, function(i, o) {  
					s+='<ul>';
					s+='<li>' + (++i) + '</li>';
	                s+='<li>' + o.username+'</li>';
	                s+='<li>' + o.email + '</li>';
	                s+='<li>' + o.nickname + '</li>';
	                s+='<li><a href="' + basePath + '/user/queryById.do?id=' + o.id + '">修改</a></li>';
	                s+='</ul>';
	            });
	            if (users.length > 0) {
	                $("#userList").html(s);
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
function $name(inputid) {
	return document.getElementById(inputid);
}
function showAddBtn() {
	var nodediv = $name('showAddDiv');
	if (nodediv.style.display == "none") {
		nodediv.style.display = "block";
	} else {
		nodediv.style.display = "none";
	}
}

function checkName(){
	var formData = $("#saveForm").serialize();
	$.ajax({
		type:"POST",
		url:"user/checkName.do",
		data:formData,
		async:false,//取消异步
		dataType : 'json',
		success : function(data){
			if(data.canSave){
				$.ajax({
					type:"POST",
					url:data.jumpUrl,
					data:formData,
					async:false,
					dataType:'json',
				});
				window.location.reload();
				 
			}else{
				alert(data.msg);
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			alert(textStatus);
			alert("error");
			return false;
		}
	});
}