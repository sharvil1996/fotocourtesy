deleteLink("Categories");
var photographer=$(".pid").text();
var rawcategoryList;
var categoryList,categoryCount;
showLoader();
callAjaxLocal("/rest/categoryPrice/categoryPhotographerPriceList?photographerId="+photographer,function(data){
	if(data.status=="success")categoryList=data.categoryPrice;
	else categoryList=null;
	callAjaxLocal("/rest/category/categoryList",function(data) {
		if(data.status=="success"){
			rawcategoryList=data.category;
			rawcategoryList.sort(function(a,b) {return (a.categoryName > b.categoryName) ? 1 : ((b.categoryName > a.categoryName) ? -1 : 0);} );
			var flag=true;
			if(categoryList!=null&&rawcategoryList.length==categoryList.length)$(".addcategorybutton").hide();
			for(var i=0;i<rawcategoryList.length;i++,flag=true){
				$(".categorysugbox").append("<li id='"+rawcategoryList[i].categoryId+"'>"+rawcategoryList[i].categoryName+"</li>");
			}
		}
		else rawcategoryList=null;
		loadCategories();
	});
});
var categorytemplate="<div class='cm4' style='padding-left: 10px;padding-right: 10px;'><div class='whitebg shadow categoryblock'><div class='id hidden'></div><div class='cat nomargin'><div class='paragraph-end'></div></div><div class='price nomargin'></div><div class='icons'><div class='icon categoryeditbutton' style='display:none;'><img src='imgs/edit.svg'/></div><div class='icon categorydeletebutton'><img src='imgs/delete.svg'/></div></div></div></div>";
function loadCategories(){
	if(categoryList==null){
		$(".nocategory").show();
		$(".categoryList").hide();
	}
	else{
		$(".nocategory").hide();
		$(".categoryList").show();
		categoryCount=categoryList.length;
		for(var i=0;i<categoryCount;i++){
			for(var j=0;j<rawcategoryList.length;j++){
				if(rawcategoryList[j].categoryId==categoryList[i].categoryId){
					categoryList[i].categoryName=rawcategoryList[j].categoryName;
					break;
				}
			}
		}
		for(var i=0;i<categoryCount;i++){
			categoryList.sort(function(a,b) {return (a.categoryName > b.categoryName) ? 1 : ((b.categoryName > a.categoryName) ? -1 : 0);} );
			var x=$(categorytemplate);
			x.children(".categoryblock").children(".id").text(categoryList[i].categoryPriceId);
			x.children(".categoryblock").children(".cat").text(categoryList[i].categoryName);
			x.children(".categoryblock").children(".price").text(categoryList[i].price+"+");
			$(".categoryListBlock").append(x);
		}
	}
	hideLoader();
}
$("body").on("click",".categorydeletebutton",function(){
	var id=$(this).parent().siblings(".id").text();
	if(confirm("Are you sure, you want to delete category?"))deleteCategory(id);
});
function deleteCategory(id){
	showLoader();
	callAjaxLocal("/rest/categoryPrice/categoryPriceDelete?categoryPriceId="+id,function(data){
		if(data.status=="success")window.location.reload();
		else if(data.status=="fail"&&categoryList.length==1)window.location.reload();
		else window.open("photographer404.jsp","_self");
	});
}
$(".categorysearchbox").keyup(function(){
	if(rawcategoryList!=null){
		$(".categorysugbox").children("li").remove();
		for(var i=0;i<rawcategoryList.length;i++){
			var flag=false;
			if(rawcategoryList[i].categoryName.toLowerCase().includes($(this).val().toLowerCase()))flag=true;
			//else if($(this).val()=="")flag=true;
			if(flag){
				$(".categorysugbox").append("<li id='"+rawcategoryList[i].categoryId+"'>"+rawcategoryList[i].categoryName+"</li>");
			}
		}
		$(this).siblings(".sugtarget").val("");
	}
});
$(".addCategoryCancel").click(function(){
	$(".categorysearchbox,.addcategorybox,.addCategoryPrice").val("");
});
$(".categorysearchbox,.addcategorybox,.addCategoryPrice").keydown(function(){
	hideError(".addCommonError");
});
$("body").on("input",".addcategorybox",function(){
	hideError(".addCommonError");
	hideError(".addCategoryAlreadyError");
});
$(".addCategoryButton").click(function(){
	var categoryid=$(".addcategorybox").val();
	var price=$(".addCategoryPrice").val();
	if(categoryid==""||price==""){
		showError(".addCommonError");
	}
	else{
		var flag=true;
		if(categoryList!=null){
			for(var i=0;i<categoryList.length;i++){
				if(categoryList[i].categoryId==categoryid){
					showError(".addCategoryAlreadyError");
					flag=false;
					break;
				}
			}
		}
		showLoader();
		if(flag){
			callAjaxLocal("/rest/categoryPrice/categoryPriceInsert?photographersId="+photographer+"&categoryId="+categoryid+"&price="+price,function(data){
				if(data.status=="success")window.location.reload();
				else window.open("photographer404.jsp","_self");
			});
		}
		else hideLoader();
	}
});
$(".addCategoryPrice").focusout(function(){
	var value=$(this).val();
	value=value.replace(/\W/g, '');
	value=value.replace(/[^0-9]/g, '');
	$(this).val(value);
});