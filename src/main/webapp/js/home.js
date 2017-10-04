var categoryList=null;
for(var i=0;i<cityList.length;i++){
	cityList[i].finalname=cityList[i].cityName+", "+cityList[i].countryName;
	$(".citysugbox").append("<li id='"+cityList[i].cityId+"'>"+cityList[i].finalname+"</li>");
}

$(".citysearchbox").keyup(function(){
	if(cityList!=null){
		$(".citysugbox").children("li").remove();
		for(var i=0;i<cityList.length;i++){
			var flag=false;
			if(cityList[i].cityName.toLowerCase().includes($(this).val().toLowerCase()))flag=true;
			else if($(this).val()=="")flag=true;
			if(flag){
				$(".citysugbox").append("<li id='"+cityList[i].cityId+"'>"+cityList[i].finalname+"</li>");
			}
		}
		$(this).siblings(".sugtarget").val("");
	}
});
callAjaxLocal("/rest/category/categoryList",function(data) {
	var tempobj=data;
	if(tempobj.status=="success"){
		categoryList=tempobj.category;
		categoryList.sort(function(a,b) {return (a.categoryName > b.categoryName) ? 1 : ((b.categoryName > a.categoryName) ? -1 : 0);} );
		for(var i=0;i<categoryList.length;i++){
			$(".categorysugbox").append("<li id='"+categoryList[i].categoryId+"'>"+categoryList[i].categoryName+"</li>");
		}
	}
	else window.open("404.html","_self");
});
$(".categorysearchbox").keyup(function(){
	if(categoryList!=null){
		$(".categorysugbox").children("li").remove();
		for(var i=0;i<categoryList.length;i++){
			var flag=false;
			if(categoryList[i].categoryName.toLowerCase().includes($(this).val().toLowerCase()))flag=true;
			else if($(this).val()=="")flag=true;
			if(flag){
				$(".categorysugbox").append("<li id='"+categoryList[i].categoryId+"'>"+categoryList[i].categoryName+"</li>");
			}
		}
		$(this).siblings(".sugtarget").val("");
	}
});
$(".searchButton").click(function(){
	var cityId=$(".citybox").val();
	var categoryId=$(".categorybox").val();
	if(cityId.length==15){
		if(categoryId.length==15)window.open("search.html?c="+cityId+"&C="+categoryId,"_self");
		else window.open("search.html?c="+cityId,"_self");
	}
});