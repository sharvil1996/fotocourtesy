var cityid=getParameterByName("c");
var categoryid=getParameterByName("C");
if(categoryid!=null){
	showLoader();
	callAjaxLocal("/rest/category/categoryList",function(data){
		if(data.status=="success"){
			var flag=false;
			categoryList=data.category;
			for(var i=0;i<data.category.length;i++){
				if(data.category[i].categoryId==categoryid){
					flag=true;
					$(".categorysearchbox").val(data.category[i].categoryName);
					$(".categorybox").val(data.category[i].categoryId);
					break;
				}
			}
			if(!flag)window.open("404.html","_self");
		}
		else categoryList=null;
		startPhotographer();
	});
}
else{
	startPhotographer();
}
if(cityid==null)window.open("404.html","_self");
if(cityList!=null){
	var flag=false;
	for(var i=0;i<cityList.length;i++){
		if(cityList[i].cityId==cityid){
			flag=true;
			$(".citysearchbox").val(cityList[i].finalname);
			$(".citybox").val(cityList[i].cityId);
			break;
		}
	}
	if(!flag)window.open("404.html","_self");
}
var searchedPhotographers;
var photographerdetailtemplate="<div class='cl6 photographerShortDetailParent' style='padding-left: 10px;padding-right:10px;'><div class='photographerShortDetail whitebg shadow'><div class='header'><div class='text'>Akshat Mewada</div><a href='#' class='contactbutton contact'>Contact</a></div><div class='body'><ul class='categories'></ul><ul class='price'></ul></div></div></div>";
function startPhotographer(){
	showLoader();
	callAjaxLocal("/rest/photographer/photographerCityList?cityId="+cityid,function(data){
		if(data.status=="success"){
			$(".photographerfound").show();
			$(".nophotographer").hide();
			searchedPhotographers=data.photographer;
			pgfound=data.photographer.length;
			for(var i=0;i<searchedPhotographers.length;i++){
				var temp=i;
				callAjaxLocal("/rest/categoryPrice/categoryPhotographerPriceList?photographerId="+searchedPhotographers[i].photographersId,function(data,index=temp){
					pgcommit++;
					if(data.categoryPrice!=undefined){
						for(var i=0;i<searchedPhotographers.length;i++){
							if(searchedPhotographers[i].photographersId==data.categoryPrice[0].photographersId){
								searchedPhotographers[i].categories=data.categoryPrice;
								for(var j=0;j<searchedPhotographers[i].categories.length;j++){
									searchedPhotographers[i].categories[j].categoryName=getCategoryNameById(searchedPhotographers[i].categories[j].categoryId);
								}		
								break;
							}
						}
					}
					if(pgfound==pgcommit)checkcategoryofphotographer();
				});
			}
		}else{
			$(".photographerfound").hide();
			$(".nophotographer").show();
			hideLoader();
		}
	});
}
var pgfound,pgcommit=0;
function checkcategoryofphotographer(){
	if(categoryid!=null){
		var truelist=new Array();
		for(var i=0;i<searchedPhotographers.length;i++){
			for(var j=0;searchedPhotographers[i].categories!=undefined&&j<searchedPhotographers[i].categories.length;j++){
				if(categoryid==searchedPhotographers[i].categories[j].categoryId)truelist.push(searchedPhotographers[i]);
			}
		}
		searchedPhotographers=truelist;
	}
	if(pgcommit==0){
		$(".photographerfound").hide();
		$(".nophotographer").show();
	}
	for(var i=0;i<searchedPhotographers.length;i++){
		var x=$(photographerdetailtemplate);
		var details=searchedPhotographers[i];
		x.children(".photographerShortDetail").children(".header").children(".text").text(details.photographersFirstName+" "+details.photographersLastName);
		x.children(".photographerShortDetail").children(".header").children(".contactbutton").attr("href","/"+details.username);
		if(details.categories!=undefined){
			for(var j=0;j<details.categories.length;j++){
				x.children(".photographerShortDetail").children(".body").children(".categories").append("<li>"+details.categories[j].categoryName+"</li>");
				x.children(".photographerShortDetail").children(".body").children(".price").append("<li>"+details.categories[j].price+"+</li>");
			}
		}
		$(".photographerList").append(x);
	}
	$(".photographerCount").text(searchedPhotographers.length);
	setTimeout(function(){
		checkLanguage();
	},500);
	hideLoader();
}