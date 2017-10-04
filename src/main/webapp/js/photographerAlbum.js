deleteLink("Albums");
$(".addAlbumCancel").click(function(){
	$(".addAlbumName,.addAlbumLink,.addAlbumDesc").val("");
});
var oldalbum,oldlink,curalbumid;
$(".addAlbumButton").click(function(){
	var name=$(".addAlbumName").val();
	var link=$(".addAlbumLink").val();
	var desc=$(".addAlbumDesc").val();
	if(name==""||link==""||desc==""){
		showError(".addCommonError");
	}
	else{
		albumaddflag=true;
		if(albumList!=null){
			for(var i=0;i<albumList.length;i++){
				if(albumList[i].albumName.toLowerCase()==name.toLowerCase()){
					showError(".addNameAlreadyError");
					albumaddflag=false;
					break;
				}
			}
			for(var i=0;i<albumList.length;i++){
				if(albumList[i].albumLink==link){
					showError(".addNameAlreadyError");
					albumaddflag=false;
					break;
				}
			}
		}
		showLoader();
		var functionret=testFlickrLink(link,flickrAlbumAddFailed);
		if(functionret!=undefined){
			if(functionret==false){
				albumaddflag=false;
				showError(".addLinkError");
				hideLoader();
			}
		}
		addAlbum();
	}
});
$(".editAlbumButton").click(function(){
	var name=$(".editAlbumName").val();
	var link=$(".editAlbumLink").val();
	var desc=$(".editAlbumDesc").val();
	if(name==""||link==""||desc==""){
		showError(".editCommonError");
	}
	else{
		albumeditflag=true;
		if(albumList!=null){
			if(oldalbum!=name){
				for(var i=0;i<albumList.length;i++){
					if(albumList[i].albumName.toLowerCase()==name.toLowerCase()){
						showError(".editNameAlreadyError");
						albumeditflag=false;
						break;
					}
				}
			}
			if(oldlink!=link){
				for(var i=0;i<albumList.length;i++){
					if(albumList[i].albumLink==link){
						showError(".editNameAlreadyError");
						albumeditflag=false;
						break;
					}
				}
			}
		}
		showLoader();
		var functionret=testFlickrLink(link,flickrAlbumEditFailed);
		if(functionret!=undefined){
			if(functionret==false){
				albumeditflag=false;
				showError(".editLinkError");
				hideLoader();
			}
		}
		updateAlbum();
	}
});
function addAlbum(){
	if(albumaddflag){
		var name=$(".addAlbumName").val();
		var link=$(".addAlbumLink").val();
		var desc=$(".addAlbumDesc").val();
		callAjaxLocal("/rest/album/albumInsert?albumName="+name+"&photographerId="+photographer+"&albumDescription="+desc+"&albumLink="+link,function(data){
			if(data.status=="success")window.location.reload();
			else window.open("photographer404.jsp","_self");
		});
	}
	else{
		hideLoader();
	}
}
function updateAlbum(){
	if(albumeditflag){
		var name=$(".editAlbumName").val();
		var link=$(".editAlbumLink").val();
		var desc=$(".editAlbumDesc").val();
		callAjaxLocal("/rest/album/albumUpdate?albumName="+name+"&photographerId="+photographer+"&albumDescription="+desc+"&albumLink="+link+"&albumId="+curalbumid,function(data){
			if(data.status=="success")window.location.reload();
			else window.open("photographer404.jsp","_self");
		});
	}
	else{
		hideLoader();
	}
}
function deleteAlbum(id){
	showLoader();
	callAjaxLocal("/rest/album/albumDelete?albumId="+id,function(data){
		if(data.status=="success")window.location.reload();
		else if(data.status=="fail"&&albumList.length==1)window.location.reload();
		else window.open("photographer404.jsp","_self");
	});
}
var albumaddflag,albumeditflag;
$(".addAlbumName,.addAlbumLink,.addAlbumDesc").keydown(function(){
	hideError(".addCommonError");
});
$(".addAlbumName").keydown(function(){
	hideError(".addNameAlreadyError");
});
$(".addAlbumLink").keydown(function(){
	hideError(".addLinkError");
});
$(".editAlbumName,.editAlbumLink,.editAlbumDesc").keydown(function(){
	hideError(".editCommonError");
});
$(".editAlbumName").keydown(function(){
	hideError(".editNameAlreadyError");
});
$(".editAlbumLink").keydown(function(){
	hideError(".editLinkError");
});
var photographer=$(".pid").text();
var albumList;
callAjaxLocal("/rest/album/albumPhotographerList?photographerId="+photographer,function(data){
	if(data.status=="success")albumList=data.album;
	else albumList=null;
	loadAlbums();
});
function getFlickrPhotos(link,myindex){
	var apikey="0088d4eb7cf7dd0989a3e57728665a61";
	var userid=link.split("/")[4];
	var albumid=link.split("/")[6];
	callAjaxLocal("https://api.flickr.com/services/rest/?method=flickr.photosets.getPhotos&api_key="+apikey
	+"&photoset_id="+albumid+"&user_id="+userid,function(data,bowl=myindex){
		var targetgroup=$(data).children("rsp").children("photoset").children("photo");
		var finalLinks=new Array();
		for(var i=0;i<targetgroup.length;i++){
			targetgroup[i]=$(targetgroup[i]);
			finalLinks[i]={
				farm:targetgroup[i].attr("farm"),
				server:targetgroup[i].attr("server"),
				id:targetgroup[i].attr("id"),
				secret:targetgroup[i].attr("secret"),
				title:targetgroup[i].attr("title")
			};
		}
		finalLinks.sort(function(a,b) {return (a.title > b.title) ? 1 : ((b.title > a.title) ? -1 : 0);} );
		for(var i=0;i<finalLinks.length;i++)
			finalLinks[i].link="http://farm"+finalLinks[i].farm+".staticflickr.com/"+finalLinks[i].server+"/"+finalLinks[i].id+"_"+finalLinks[i].secret+".jpg";
		setImageinAlbum(bowl,finalLinks);
	});
}
function testFlickrLink(link,innerCallback){
	var apikey="0088d4eb7cf7dd0989a3e57728665a61";
	var userid=link.split("/")[4];
	var albumid=link.split("/")[6];
	if(userid==undefined||albumid==undefined)return false;
	callAjaxLocal("https://api.flickr.com/services/rest/?method=flickr.photosets.getPhotos&api_key="+apikey
	+"&photoset_id="+albumid+"&user_id="+userid,function(data,callback=innerCallback){
		if($(data).children("rsp").attr("stat")=="fail")callback();
	});
}
function flickrAlbumAddFailed(){
	albumaddflag=false;
	showError(".addLinkError");
	hideLoader();
	addAlbum();
}
function flickrAlbumEditFailed(){
	albumeditflag=false;
	showError(".editLinkError");
	hideLoader();
	updateAlbum();
}
function loadAlbums(){
	if(albumList==null){
		$(".noalbum").show();
		$(".albumList").hide();
	}
	else{
		$(".noalbum").hide();
		$(".albumList").show();
		albumcount=albumList.length;
		showLoader();
		refreshAlbums();
	}
}
function refreshAlbums(){
	for(var i=0;i<albumList.length;i++){
		albumList[i].images=new Array();
		getFlickrPhotos(albumList[i].albumLink,i);
	}
}
var albumcount,albumprocount=0;
function setImageinAlbum(index,images){
	albumList[index].images=images;
	albumprocount++;
	if(albumcount==albumprocount){
		for(var i=0;i<albumList.length;i++){
			var x=$(albumtemplate);
			x.children(".albumview").children(".albumid").text(albumList[i].albumId);
			x.children(".albumview").children(".albumname").html(albumList[i].albumName+"<div class='paragraph-end black'></div>");
			x.children(".albumview").css("background-image","url("+albumList[i].images[0].link+")");
			$(".albumparent").append(x);
		}
		hideLoader();
	}
}
var albumtemplate="<div class='cm4' style='padding-left: 10px;padding-right: 10px;margin-bottom: 10px;'><div class='whitebg shadow albumview' style='background-image: url(imgs/1.jpeg);'><div class='albumid hidden'></div><div class='icons'><div class='icon albumeditbutton modelbutton' style='display:none;' target='.editAlbumModel'><img src='imgs/edit.svg'/></div><div class='icon albumdeletebutton'><img src='imgs/delete.svg'/></div></div><div class='albumname'></div></div></div>";
$("body").on("click",".albumdeletebutton",function(){
	var id=$(this).parent().siblings(".albumid").text();
	if(confirm("Are you sure, you want to delete album?"))deleteAlbum(id);
});
$("body").on("click",".albumeditbutton",function(){
	var id=$(this).parent().siblings(".albumid").text();
	for(var i=0;i<albumList.length;i++){
		if(albumList[i].albumId==id){
			curalbumid=id;
			oldalbum=albumList[i].albumName;
			oldlink=albumList[i].albumLink;
			$(".editAlbumName").val(albumList[i].albumName);
			$(".editAlbumLink").val(albumList[i].albumLink);
			$(".editAlbumDesc").val(albumList[i].albumDescription);
			break;
		}
	}
});