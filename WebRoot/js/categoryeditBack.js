
function updateCategoryInfo(){
	var formData = $("#updateCategoryInfo").serialize();
	$.ajax({
		type : 'POST',
		url : 'category/updateAjax.shtml',
		data:formData,
		dataType : 'json',
		success : function(data){
			alert(data.res_msg);
			if(data.res_code == 0) {
				window.location.href = data.body.url;
			} 
		},error:function(e){
			alert("error");
		}
	});
}
