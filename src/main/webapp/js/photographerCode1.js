aligncenter(".fullloader img",window);
var cors_api_url = 'https://cors-anywhere.herokuapp.com/';
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
});
$(".mobilemenuclose").click(function(){
	$(".mobilemenu").removeClass("open");
});
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
var modelid=0;
$("body").on("click",".modelbutton",function(){
	target=$($(this).attr("target"));
	openmodel(target);
});
$("body").on("click",".modelcloser,.modelsupport",function(){
	closemodel();
});
$(document).click(function () {
}).keyup(function (e) {
    if (e.keyCode == 27) closemodel();
});
$("body").on("click",".model",function(){
	modelid=1;
});
function openmodel(target){
	var transition=target.css("transition");
	$("body").append("<div class='modelsupport'></div>");
	target.css({"display":"inline-block","transition":"none","opacity":"0"});
	target.addClass("open");
	var oh=target.outerHeight();
	var ow=target.outerWidth();
	var dh=$(window).outerHeight();
	var dw=$(document).outerWidth();
	var inhw=40;
	if(dw<650){
		$("body").css("overflow","hidden");
	}
	var tt=parseInt(dh/2)-parseInt(inhw/2);
	var tl=parseInt(dw/2)-parseInt(inhw/2);
	target.css({"top":tt+"px","left":tl+"px"});
	target.children(".body").css("opacity","0");
	target.css({"width":inhw+"px","height":inhw+"px"});
	setTimeout(function(){
		if(dw<650){
			oh=dh;
			ow=dw;
			target.css({"max-height":"100%","max-width":"100%"});
		}
		$(".modelsupport").css("opacity","1");
		target.css({"width":ow+"px","height":oh+"px","transition":transition,"opacity":"1"});
		var tt=parseInt(dh/2)-parseInt(oh/2);
		var tl=parseInt(dw/2)-parseInt(ow/2);
		target.css({"top":tt+"px","left":tl+"px"});
		setTimeout(function(){
			target.children(".body").css("opacity","1");
		},150);
	},150);
	modelid=1;
}
function closemodel(){
	var target=$(".model.open");
	target.css("opacity","0");
	$(".modelsupport").css("opacity","0");
	setTimeout(function(){
		target.css({"display":"none"});
		target.removeClass("open");
		$(".modelsupport").remove();
		$("body").css("overflow","auto");
	},300);
	modelid=0;
}
function showLoader(){
	$(".fullloader").addClass("show");
}
function hideLoader(){
	$(".fullloader").removeClass("show");
}
function showError(target){
	$(target).addClass("show");
}
function hideError(target){
	$(target).removeClass("show");
}
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
$(".mobilemenu li").click(function(){
	var link=$(this).children("a").attr("href");
	window.open(link,"_self");
});
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