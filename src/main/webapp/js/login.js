deleteLink("Login");
$(".loginform").submit(function(){
	var id=$(".emailbox").val();
	var pass=$(".passbox").val();
	if(id==""||pass==""){
		showError(".loginerror");
		return false;	
	}
	else{
		$(".fullloader").addClass("show");
	}
});
$(".emailbox,.passwordbox").keydown(function(){
	if($(".loginerror").hasClass("show"))hideError(".loginerror");
});