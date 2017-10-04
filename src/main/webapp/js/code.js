aligncenter(".fullloader img",window);
var cors_api_url = 'https://cors-anywhere.herokuapp.com/';
function callAjax(url, executor) {
	var x = new XMLHttpRequest();
	x.open('GET', cors_api_url + url);
	x.onload = x.onerror = function() {executor($.parseJSON((x.responseText || '')));};
	if (/^POST/i.test('GET')) {x.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');}
	x.send();
}
function callAjaxLocal(url,executor){
	$.ajax({
		url : url,
		success : function(data) {
			executor(data);
		}
	});
}
$(".navpanel .navitem").each(function(){
	$(".mobilemenu").append("<li><a class='"+$(this).attr("class")+"' href='"+$(this).attr("href")+"'>"+$(this).text()+"</a></li>");
});
$(".menubutton").click(function(){
	$(".mobilemenu").addClass("open");
	$("body").css("overflow","hidden");
});
$(".mobilemenuclose").click(function(){
	$(".mobilemenu").removeClass("open");
	$("body").css("overflow","auto");
});
function showError(target){
	$(target).addClass("show");
}
function hideError(target){
	$(target).removeClass("show");
}
$(".passwordbox div").click(function(){
	var te=$(this);
	if($(this).siblings("input").attr("type")=="password"){
		te.css("opacity","0");
		setTimeout(function(){
			te.children("img").attr("src","imgs/eye-slash.svg");
			te.siblings("input").attr("type","text");
			te.css("opacity","1");
			te.siblings("input").focus();
		},200);
	}
	else{
		te.css("opacity","0");
		setTimeout(function(){
			te.children("img").attr("src","imgs/eye.svg");
			te.siblings("input").attr("type","password");
			te.css("opacity","1");
			te.siblings("input").focus();
		},200);
	}
});
$(".sugcontainer input").focusin(function(){
	if(!$(this).parent().hasClass("disable"))$(this).siblings(".sugbox").addClass("open");
});
$(".sugcontainer input").focusout(function(){
	var target=$(this);
	setTimeout(function(){
		target.siblings(".sugbox").removeClass("open");
	},200);
});
$("body").on("click",".sugbox li",function(){
	if($(this).parent().siblings(".sugrep").val()!="")$(this).parent().siblings(".sugrep").trigger("change");
	$(this).parent().siblings(".sugrep").attr("value",$(this).text());
	$(this).parent().siblings(".sugtarget").attr("value",$(this).attr("id"));
	$(this).parent().siblings(".sugrep").val($(this).text());
	$(this).parent().siblings(".sugtarget").val($(this).attr("id"));
	$(this).parent().siblings(".sugtarget").trigger("input");
});
function titleCase(str) {
    var final = "";
    if (str.length > 0) {
        final = str[0].toUpperCase();
        for (i = 1; i < str.length; i++) final += str[i].toLowerCase();
    }
    return final;
}
function titleCaseSentence(str) {
    var capflag = true;
    var final = "";
	str=str.replace(/\./g, '. ');
	str=str.replace(/\.  /g, '. ');
	str=str.replace(/,/g, ', ');
	str=str.replace(/,  /g, ', ');
    if (str.length > 0) {
        for (i = 0; i < str.length; i++) {
            if (capflag) {
                final += str[i].toUpperCase();
                capflag = false;
            }
            else final += str[i].toLowerCase();
            if (str[i] == ' ' || str[i] == '.') capflag = true;
        }
    }
    return final;
}
function validateEmail(mail)
{
	if (/^\w+([\.-]?\ w+)*@\w+([\.-]?\ w+)*(\.\w{2,3})+$/.test(mail))return true;
	return false;
}
function existUsername(username){
	callAjaxLocal("/rest/photographer/photographerUserName?userName="+username,function(data){
		var obj=data;
		if(obj.status=="success")userNameReturnCall(true);
		else userNameReturnCall(false);
	});
}
function existEmail(email){
	callAjaxLocal("/rest/photographer/photographerEmailId?emailId="+email,function(data){
		var obj=data;
		if(obj.status=="success")emailReturnCall(true);
		else emailReturnCall(false);
	});
}
function aligncenter(target,relevance){
	var rh=$(relevance).outerHeight();
	var th=$(target).outerHeight();
	var ft=parseInt((rh/2)-(th/2));
	$(target).css({"top":ft});
}
function alignvertical(target,relevance){
	var rh=$(relevance).outerHeight();
	var th=$(target).outerHeight();
	var rw=$(relevance).outerWidth();
	var tw=$(target).outerWidth();
	var ft=parseInt((rh/2)-(th/2));
	var fl=parseInt((rw/2)-(tw/2));
	$(target).css({"top":ft,"left":fl});
}
function deleteLink(name){
	$("a").each(function(){
		if($(this).text()==name){
			if($(this).parent()[0].localName=="li")$(this).parent().remove();
			else $(this).remove();
		}
	});
}
var galleries=new Array();
var galcount=0;
if($(".gallery")[0]){
	$(".gallery").each(function(){
		var imgcount=0;
		var path=new Array();
		$(this).children(".imgs").children("img").each(function(){
			path[imgcount++]=$(this).attr("src");
		});
		galleries[galcount++]={
			gallery:$(this),
			curimg:0,
			paths:path,
			useflag:0,
			slot:1,
			opslot:2
		};
		changeimage(galleries[galcount-1]);
	});
}
function changeimage(target){
	if(target.useflag==0){
		target.useflag=1;
		target.gallery.children(".img"+target.slot).css("background-image","url("+target.paths[target.curimg]+")");
		target.curimg++;
		if(target.slot==1)target.slot=2;
		else if(target.slot==2)target.slot=1;
		if(target.opslot==1)target.opslot=2;
		else if(target.opslot==2)target.opslot=1;
	}
	if(target.curimg>target.paths.length-1)target.curimg=0;
	target.gallery.children(".img"+target.slot).css({"opacity":"0","z-index":"2"});
	setTimeout(function(){
		target.gallery.children(".img"+target.opslot).css({"z-index":"1"});
		target.gallery.children(".img"+target.slot).css("background-image","url("+target.paths[target.curimg]+")");
		target.curimg++;
		target.gallery.children(".img"+target.slot).css({"opacity":"1"});
		if(target.slot==1)target.slot=2;
		else if(target.slot==2)target.slot=1;
		if(target.opslot==1)target.opslot=2;
		else if(target.opslot==2)target.opslot=1;
		changeimage(target);
	},7000);
}
var galleries=new Array();
var galcount=0;
if($(".gallery")[0]){
	$(".gallery").each(function(){
		var imgcount=0;
		var path=new Array();
		$(this).children(".imgs").children("img").each(function(){
			path[imgcount++]=$(this).attr("src");
		});
		galleries[galcount++]={
			gallery:$(this),
			curimg:0,
			paths:path,
			useflag:0,
			slot:1,
			opslot:2
		};
		changeimage(galleries[galcount-1]);
	});
}
function changeimage(target){
	if(target.useflag==0){
		target.useflag=1;
		target.gallery.children(".img"+target.slot).css("background-image","url("+target.paths[target.curimg]+")");
		target.curimg++;
		if(target.slot==1)target.slot=2;
		else if(target.slot==2)target.slot=1;
		if(target.opslot==1)target.opslot=2;
		else if(target.opslot==2)target.opslot=1;
	}
	if(target.curimg>target.paths.length-1)target.curimg=0;
	target.gallery.children(".img"+target.slot).css({"opacity":"0","z-index":"2"});
	setTimeout(function(){
		target.gallery.children(".img"+target.opslot).css({"z-index":"1"});
		target.gallery.children(".img"+target.slot).css("background-image","url("+target.paths[target.curimg]+")");
		target.curimg++;
		target.gallery.children(".img"+target.slot).css({"opacity":"1"});
		if(target.slot==1)target.slot=2;
		else if(target.slot==2)target.slot=1;
		if(target.opslot==1)target.opslot=2;
		else if(target.opslot==2)target.opslot=1;
		changeimage(target);
	},7000);
}
function getParameterByName(name, url) {
	if (!url)
		url = window.location.href;
	name = name.replace(/[\[\]]/g, "\\$&");
	var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"), results = regex
			.exec(url);
	if (!results)
		return null;
	if (!results[2])
		return '';
	return decodeURIComponent(results[2].replace(/\+/g, " "));
}
function getCityNameById(id){
	for(var i=0;i<cityList.length;i++)if(cityList[i].cityId==id)return cityList[i].cityName;
	return null;
}
function getCategoryNameById(id){
	for(var i=0;i<categoryList.length;i++)if(categoryList[i].categoryId==id)return categoryList[i].categoryName;
	return null;
}
function showLoader(){
	$(".fullloader").addClass("show");
}
function hideLoader(){
	$(".fullloader").removeClass("show");
}
$(".imageviewer .back,.imageviewer .closebutton").click(function(){
	closeimgview();
});
$(document).click(function () {
}).keyup(function (e) {
    if (e.keyCode == 27) closeimgview();
});
function closeimgview(){
	$(".imageviewer").removeClass("open");
	$("body").css("overflow","auto");
}