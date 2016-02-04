/*window.onbeforeunload = function(event) {
	if(confirm("确定离开此页面吗？")){
		window.location.href = "user/loginOut.do";
	}
}*/

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