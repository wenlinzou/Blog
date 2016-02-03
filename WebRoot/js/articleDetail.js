
function checkName(){
	var formData = $("#saveForm").serialize();
	$.ajax({
		type:"POST",
		url:"commentFront/checkName.do",
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