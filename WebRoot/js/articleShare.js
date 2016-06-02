function sendShareCode(){
	var shareCode = $("#shareCode").val();
	if(""!=shareCode){
		$("#checkShareCode").submit();
	}
}