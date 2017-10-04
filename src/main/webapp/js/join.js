deleteLink("Become a Member");
/*callAjax("https://photographers.herokuapp.com/rest/country/countryList",function(data) {
	var tempobj=$.parseJSON(data);
	if(tempobj.status=="success")countryList=tempobj.country;
	else countryList=null;
});*/
/*callAjax("https://photographers.herokuapp.com/rest/city/cityList",function(data) {
	var tempobj=$.parseJSON(data);
	if(tempobj.status=="success")cityList=tempobj.city;
	else cityList=null;
});*/
if(countryList!=null){
	$(".countrysugbox").children("li").remove();
	for(var i=0;i<countryList.length;i++){
		var temp=countryList[i].countryCode.split(",");
		if(temp.length>1){
			$(".countrysugbox").append("<li id='"+countryList[i].countryId+"'>"+countryList[i].countryName+" (+"+countryList[i].countryCode.replace(/, /g , ", +")+")</li>");
			countryList[i].countryCode=countryList[i].countryCode.replace(/, /g , ", +");
		}
		else{
			$(".countrysugbox").append("<li id='"+countryList[i].countryId+"'>"+countryList[i].countryName+" (+"+countryList[i].countryCode+")</li>");
		}
	}
}
$(".countrysearchbox").keyup(function(){
	if(countryList!=null){
		$(".countrysugbox").children("li").remove();
		for(var i=0;i<countryList.length;i++){
			var flag=false;
			if((countryList[i].countryName+countryList[i].countryCode).toLowerCase().includes($(this).val().toLowerCase()))flag=true;
			else if($(this).val()=="")flag=true;
			if(flag){
				$(".countrysugbox").append("<li id='"+countryList[i].countryId+"'>"+countryList[i].countryName+" (+"+countryList[i].countryCode+")</li>");
			}
		}
		$(this).siblings(".sugtarget").val("");
		$(this).siblings(".sugtarget").trigger("input");
		hideError(".emptyError");
	}
}).change(function(){
	if($(".countrybox").val().length==15)updateCitySug();
});
$(".citysearchbox").keyup(function(){
	if(cityList!=null){
		$(".citysugbox").children("li").remove();
		for(var i=0;i<cityList.length;i++){
			var flag=false;
			if(cityList[i].cityName.toLowerCase().includes($(this).val().toLowerCase())&&cityList[i].countryId==$(".countrybox").val())flag=true;
			else if($(this).val()==""&&cityList[i].countryId==$(".countrybox").val())flag=true;
			if(flag){
				$(".citysugbox").append("<li id='"+cityList[i].cityId+"'>"+cityList[i].cityName+"</li>");
			}
		}
		$(this).siblings(".sugtarget").val("");
		hideError(".emptyError");
	}
});
function disableCityBox(){
	$(".citysearchbox").siblings(".sugtarget").val("");
	$(".citysearchbox").val("");
	$(".citysearchbox").attr("readonly","true");
	$(".citysearchbox").parent().addClass("disable");
	$(".citysearchbox").parent().siblings("label").addClass("disable");
}
function updateCitySug(){
	disableCityBox();
	$(".citysearchbox").removeAttr("readonly");
	$(".citysearchbox").parent().removeClass("disable");
	$(".citysearchbox").parent().siblings("label").removeClass("disable");
	if(cityList!=null){
		$(".citysugbox").children("li").remove();
		for(var i=0;i<cityList.length;i++){
			if(cityList[i].countryId==$(".countrybox").val())$(".citysugbox").append("<li id='"+cityList[i].cityId+"'>"+cityList[i].cityName+"</li>");
		}
	}
}
$("body").on("input",".countrybox",function(){
	if($(this).val().length==15)updateCitySug();
	else disableCityBox();
});
$(".fnbox,.lnbox").focusout(function(){
	var value=$(this).val();
	value=value.replace(/\W/g, '');
	value=value.replace(/[^a-zA-Z]/g, '');
	value=titleCase(value);
	$(this).val(value);
}).keyup(function(){
	hideError(".emptyError");
});
$(".addressbox").focusout(function(){
	var value=$(this).val();
	value=titleCaseSentence(value);
	$(this).val(value);
}).keyup(function(){
	hideError(".emptyError");
});
$(".emailbox").focusout(function(){
	var value=$(this).val();
	if(value!=""){
		if(validateEmail(value))existEmail(value);
		else showError(".invalidEmailError");
	}
}).keyup(function(){
	hideError(".invalidEmailError");
	hideError(".existEmailError");
	hideError(".emptyError");
});
$(".usernamebox").focusout(function(){
	var value=$(this).val();
	if(value!="")existUsername(value);
}).keyup(function(){
	hideError(".emptyError");
});
function userNameReturnCall(flag){
	if(flag)showError(".userNameError");
	else hideError(".userNameError");
}
function emailReturnCall(flag){
	if(flag)showError(".existEmailError");
	else hideError(".existEmailError");
}
$(".cntnobox").keyup(function(){
	hideError(".emptyError");
}).focusout(function(){
	var value=$(this).val();
	value=value.replace(/\W/g, '');
	value=value.replace(/[^0-9]/g, '');
	$(this).val(value);
});
$(".joinButton").click(function(){
	var fn=$(".fnbox").val();
	var ln=$(".lnbox").val();
	var username=$(".usernamebox").val();
	var email=$(".emailbox").val();
	var address=$(".addressbox").val();
	var mobile=$(".cntnobox").val();
	var country=$(".countrybox").val();
	var city=$(".citybox").val();
	if(fn==""||ln==""||username==""||email==""||address==""||mobile==""||country==""||city=="")showError(".emptyError");
	else{
		if(!$(".existEmailError").hasClass("show")&&!$(".invalidEmailError").hasClass("show")&&!$(".userNameError").hasClass("show")){
			$(".fullloader").addClass("show");
			callAjaxLocal("/rest/photographer/photographerInsertDetails?emailId="+email+"&firstName="+fn+"&lastName="+ln+"&userName="+username+"&contactNo1="+mobile+"&address="+address+"&cityId="+city,function(data){
				var obj=data;
				if(obj.status="success"){
					$(".fullloader").removeClass("show");
					$(".mainform").css("display","none");
					$(".successform").css("display","block");
				}
				else{
					$(".fullloader").removeClass("show");
					$(".mainform").css("display","none");
					$(".failform").css("display","block");
				}
			});
		}
	}
});