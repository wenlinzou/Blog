$(function(){
	showUpdatePwd();
	closeUpdatePwd();
});

function showUpdatePwd(){
	$('#alert_btn').click(function(){
		$('.alert').show('slow');
		$('.mask').show('fast');
	});
}
function closeUpdatePwd(){
	$('.mask').click(function(){
		$('.alert').hide('fast');
		$('.mask').hide('fast');
	});
}
function hiddenPwdDiv(){
	$('.alert').hide('fast');
	$('.mask').hide('fast');
}

function updatePassword(){
	var isOK = check2Pwd();
	if(!isOK){
		$('#errormsg').html("输入密码不一致!");
		$('#errormsg').addClass('errormsg');
		return false;
	}else{
		var formData = $("#updatePwd").serialize();
		$.ajax({
			type : 'POST',
			url : 'user/updatePwd.shtml',
			data:formData,
			dataType : 'json',
			success : function(data){
				alert(data.message);
				hiddenPwdDiv();
			},error:function(e){
				alert("error");
			}
		});
	}
}
function updateUserInfo(){
	var formData = $("#updateInfo").serialize();
	$.ajax({
		type : 'POST',
		url : 'user/updateAjax.shtml',
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
function check2Pwd(){
	var pwd1 = $("input[name='password']").val();
	var pwd2 = $("input[name='password1']").val();
	if(pwd1!=''){
		return pwd1==pwd2;
	}else{
		return false;
	}
}
/*
function updatePwd(){
	var name = prompt("请输入您的新密码", ""); //将输入的内容赋给变量 name ，  
	  
    //这里需要注意的是，prompt有两个参数，前面是提示的话，后面是当对话框出来后，在对话框里的默认值  
    if (name)//如果返回的有内容  
    {  
        var userid = document.getElementById("userid").value;
        
        $.ajax({
    		type : 'POST',
    		url : 'user/updatePwd.shtml?password=' + name + "&id=" + userid,
    		dataType : 'json',
    		success : function(data){
    			alert(data.message);
    		},error:function(e){
    			alert("error");
    		}
    	});
    }  
}
*/