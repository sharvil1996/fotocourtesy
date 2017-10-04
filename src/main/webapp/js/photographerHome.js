deleteLink("Dashboard");
var photographer=$(".pid").text();
showLoader();
callAjaxLocal("/rest/photographer/photographerUserName?userName=" + photographer, function (data) {
    if (data.status == "success") {
        photographer = data.photographer;
        photographer.fullname = photographer.photographersFirstName + " " + photographer.photographersLastName;
        $("title").text(photographer.fullname + " | FotoCourtesy");
        $(".photographername").text(photographer.fullname);
        $(".addresspg").text(photographer.photographersAddress + ", " + photographer.cityName + ", " + photographer.countryName);
        $(".cntno").text(photographer.photographersContact1 + ((photographer.photographersContact2 != null) ? ", " + photographer.photographersContact2 : ""));
        $(".emailpg").text(photographer.photographersEmailId);
        $(".xp").text(photographer.photographersExperience);
        $(".xp").parent().removeClass("hidden");
        $(".pageviews").text(photographer.photographersPageView);
        $(".albumviews").text(photographer.photographersAlbumView);
        $(".statblock").show();
        if (photographer.photographersDescription != "NULL") $(".desc").text(photographer.photographersDescription);
        else $(".desc").hide();
        $(".contactdetails").show();
        hideLoader();
        startCategory();
        startAlbum();
    }
    else window.open("photographer404.jsp", "_self");
});
//-------------------------Category----------------------------
var rawcategoryList;
var categoryList, categoryCount;
function startCategory() {
    showLoader();
    callAjaxLocal("/rest/categoryPrice/categoryPhotographerPriceList?photographerId=" + photographer.photographersId, function (data) {
        if (data.status == "success") categoryList = data.categoryPrice;
        else categoryList = null;
        callAjaxLocal("/rest/category/categoryList", function (data) {
            if (data.status == "success") {
                rawcategoryList = data.category;
                rawcategoryList.sort(function (a, b) {
                    return (a.categoryName > b.categoryName) ? 1 : ((b.categoryName > a.categoryName) ? -1 : 0);
                });
            }
            else rawcategoryList = null;
            loadCategories();
        });
    });
}
var categorytemplate = "<div class='cm4' style='padding-left: 10px;padding-right: 10px;'><div class='whitebg shadow categoryblock'><div class='id hidden'></div><div class='cat nomargin'><div class='paragraph-end'></div></div><div class='price nomargin'></div></div></div>";
function loadCategories() {
    if (categoryList == null) {
        $(".nocategory").show();
        $(".categoryList").hide();
    }
    else {
        $(".nocategory").hide();
        $(".categoryList").show();
        categoryCount = categoryList.length;
        for (var i = 0; i < categoryCount; i++) {
            for (var j = 0; j < rawcategoryList.length; j++) {
                if (rawcategoryList[j].categoryId == categoryList[i].categoryId) {
                    categoryList[i].categoryName = rawcategoryList[j].categoryName;
                    break;
                }
            }
        }
        for (var i = 0; i < categoryCount; i++) {
            categoryList.sort(function (a, b) {
                return (a.categoryName > b.categoryName) ? 1 : ((b.categoryName > a.categoryName) ? -1 : 0);
            });
            var x = $(categorytemplate);
            x.children(".categoryblock").children(".id").text(categoryList[i].categoryPriceId);
            x.children(".categoryblock").children(".cat").text(categoryList[i].categoryName);
            x.children(".categoryblock").children(".price").text(categoryList[i].price + "+");
            $(".categoryListBlock").append(x);
        }
    }
    hideLoader();
}
//----------------------------------Album-----------------------------
function getFlickrPhotos(link, myindex) {
    var apikey = "0088d4eb7cf7dd0989a3e57728665a61";
    var userid = link.split("/")[4];
    var albumid = link.split("/")[6];
    callAjaxLocal("https://api.flickr.com/services/rest/?method=flickr.photosets.getPhotos&api_key=" + apikey
        + "&photoset_id=" + albumid + "&user_id=" + userid, function (data, bowl=myindex) {
        var targetgroup = $(data).children("rsp").children("photoset").children("photo");
        var finalLinks = new Array();
        for (var i = 0; i < targetgroup.length; i++) {
            targetgroup[i] = $(targetgroup[i]);
            finalLinks[i] = {
                farm: targetgroup[i].attr("farm"),
                server: targetgroup[i].attr("server"),
                id: targetgroup[i].attr("id"),
                secret: targetgroup[i].attr("secret"),
                title: targetgroup[i].attr("title")
            };
        }
        finalLinks.sort(function (a, b) {
            return (a.title > b.title) ? 1 : ((b.title > a.title) ? -1 : 0);
        });
        for (var i = 0; i < finalLinks.length; i++)
            finalLinks[i].link = "http://farm" + finalLinks[i].farm + ".staticflickr.com/" + finalLinks[i].server + "/" + finalLinks[i].id + "_" + finalLinks[i].secret + ".jpg";
        setImageinAlbum(bowl, finalLinks);
    });
}
var albumList;
function startAlbum() {
    callAjaxLocal("/rest/album/albumPhotographerList?photographerId=" + photographer.photographersId, function (data) {
        if (data.status == "success") albumList = data.album;
        else albumList = null;
        loadAlbums();
    });
}
function loadAlbums() {
    if (albumList == null) {
        $(".noalbum").show();
        $(".albumList").hide();
    }
    else {
        $(".noalbum").hide();
        $(".albumList").show();
        albumcount = albumList.length;
        showLoader();
        refreshAlbums();
    }
}
function refreshAlbums() {
    for (var i = 0; i < albumList.length; i++) {
        albumList[i].images = new Array();
        getFlickrPhotos(albumList[i].albumLink, i);
    }
}
var albumcount, albumprocount = 0;
function setImageinAlbum(index, images) {
    albumList[index].images = images;
    albumprocount++;
    if (albumcount == albumprocount) {
        for (var i = 0; i < albumList.length; i++) {
            var x = $(albumtemplate);
            x.children(".albumholder").children(".top").children(".title").text(albumList[i].albumName);
            x.children(".albumholder").children(".top").children(".desc").text(albumList[i].albumDescription);
            for (var j = 0; j < albumList[i].images.length; j++) {
                x.children(".albumholder").children(".images").append("<div class='image' style='background-image:url(" + albumList[i].images[j].link + ");'></div>");
            }
            $(".albumcontainer").append(x);
        }
        checkLanguage();
        hideLoader();
    }
}
$("body").on("click", ".showmorebutton", function () {
    $(this).parent().siblings(".images").css("max-height", "none");
    $(this).parent().remove();
});
var albumtemplate = "<div class='cm6'><div class='albumholder shadow whitebg'><div class='top'><div class='title'></div><div class='desc'></div></div><div class='images'></div><div class='tcenter' style='padding-top: 15px;'><div class='button shadow showmorebutton showmore'>Show More</div></div></div></div>";
$("body").on("click", ".images .image", function () {
    var image = $(this).css("background-image").split("\"")[1];
    $(".imageviewer").children("img").attr("src", image);
    $(".imageviewer").addClass("open");
    $("body").css("overflow", "hidden");
    alignvertical(".imageviewer img", window);
});
$(".editprofilebutton").click(function () {
    $(".dashboardview").hide();
    $(".editprofileblock").show();
    $(".fnbox").val(photographer.photographersFirstName);
    $(".lnbox").val(photographer.photographersLastName);
    $(".addressbox").val(photographer.photographersAddress);
    $(".cntnobox").val(photographer.photographersContact1);
    $(".xpbox").val(photographer.photographersExperience);
    if(photographer.photographersContact2==" ")photographer.photographersContact2="";
    $(".cnt1nobox").val(photographer.photographersContact2);
    $(".descbox").val(photographer.photographersDescription);
    $(".citysearchbox").val(photographer.cityName+", "+photographer.countryName);
    $(".citybox").val(photographer.cityId);
    window.scrollTo(0,0);
    hideError(".emptyError");
});
$(".cancelprofilebutton").click(function () {
    $(".dashboardview").show();
    $(".editprofileblock").hide();
    window.scrollTo(0,0);
});
$(".citysearchbox").keyup(function(){
    if(cityList!=null){
        $(".citysugbox").children("li").remove();
        for(var i=0;i<cityList.length;i++){
            var flag=false;
            if(cityList[i].finalname.toLowerCase().includes($(this).val().toLowerCase()))flag=true;
            else if($(this).val()=="")flag=true;
            if(flag){
                $(".citysugbox").append("<li id='"+cityList[i].cityId+"'>"+cityList[i].finalname+"</li>");
            }
        }
        $(this).siblings(".sugtarget").val("");
        hideError(".emptyError");
    }
});
if(cityList!=null){
    for(var i=0;i<cityList.length;i++){
        cityList[i].finalname=cityList[i].cityName+", "+cityList[i].countryName;
        $(".citysugbox").append("<li id='"+cityList[i].cityId+"'>"+cityList[i].finalname+"</li>");
    }
}
$(".updateButton").click(function () {
    var fn=$(".fnbox").val();
    var ln=$(".lnbox").val();
    var address=$(".addressbox").val();
    var mobile=$(".cntnobox").val();
    var mobile1=$(".cnt1nobox").val();
    var city=$(".citybox").val();
    var desc=$(".descbox").val();
    var xp=$(".xpbox").val();
    var email=photographer.photographersEmailId;
    var username=photographer.username;
    var id=photographer.photographersId;
    var price=photographer.price;
    if(fn==""||ln==""||address==""||mobile==""||city==""||desc==""||xp=="")showError(".emptyError");
    else{
        showLoader();
        callAjaxLocal("/rest/photographer/photographerUpdate?emailId="+email+"&firstName="+fn+"&lastName="+ln+"&userName="+username+"&contactNo1="+mobile+"&contactNo2="+mobile1+"&description="+desc+"&experience="+xp+"&address="+address+"&cityName="+city+"&price="+price+"&photographerId="+id,function(data){
            var obj=data;
            if(obj.status=="success")window.location.reload();
            else window.open("photographer404.jsp","_self");
        });
    }
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
$(".cntnobox,.cnt1nobox,.xpbox").keyup(function(){
    hideError(".emptyError");
}).focusout(function(){
    var value=$(this).val();
    value=value.replace(/\W/g, '');
    value=value.replace(/[^0-9]/g, '');
    $(this).val(value);
});
$(".changepassbutton").click(function () {
    $(".dashboardview").hide();
    $(".changepassblock").show();
    window.scrollTo(0,0);
    hideError(".emptyError");
});
$(".cancelpassbutton").click(function () {
    $(".dashboardview").show();
    $(".changepassblock").hide();
    window.scrollTo(0,0);
});
$(".updatepassButton").click(function () {
    var oldpass=$(".oldpassbox").val();
    var newpass=$(".newpassbox").val();
    if(oldpass==""||newpass=="")showError(".emptyError");
    else{
    }
});
$(".oldpassbox,.newpassbox").keyup(function(){
    hideError(".emptyError");
})