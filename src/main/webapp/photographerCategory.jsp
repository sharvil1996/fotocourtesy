<%@ page language="java" contentType="text/html;charset=UTF-8" 
pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Category | FotoCourtesty</title>
<link href="css/photographerStyle.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="imgs/favicon.ico">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans:400,700&amp;subset=devanagari,latin-ext" rel="stylesheet">
<link rel="icon" type="image/png" href="imgs/favicon.png" sizes="256x256">
</head>
<body>	
<%@ include file="photographerHeader.jsp"%>
<span class="pid hidden"><%= photographerBean.getPhotographersId() %><!-- 8D1KPwwdDDhjnTZ --></span>
<section style="background-image: url(imgs/category.jpg);background-position: center center;background-size: cover;">
	<div class="container" style="padding: 10px;padding-top: 125px;">
		<div class="jumbotext categories">Categories</div>
	</div>
</section>
<section class="backgrey">
	<div class="container nocategory" style="display: none;">
		<div class="whitebg shadow">
			<br><br><br>
			<h1 class="nocategoriesfound">No Categories Found</h1>
			<div class="tcenter"><button class="button shadow modelbutton addcategory" type="button" style="margin-bottom: 30px;" target=".addCategoryModel">Add Category</button></div>
			<br><br>
		</div>
	</div>
	<div class="container categoryList" style="display: none;">
		<div class="tright addcategorybutton"><button class="button shadow modelbutton addcategory" type="button" style="margin-bottom: 30px;" target=".addCategoryModel">Add Category</button></div>
		<div class="row categoryListBlock">
			
		</div>
	</div>
</section>
<div class="addCategoryModel model">
	<div class="body">
		<div class="content">
			<h1 class="textnowrap tleft addcategory">Add Category</h1>
			<br><br>
			<div class="form nopadding noshadow">
				<label class="selectcategory">Select Category</label>
				<div class="sugcontainer">
					<input type="text" class="categorysearchbox sugrep">
					<ul class="sugbox categorysugbox" style="top:80%;left: -1px;">
					</ul>
					<input type="text" class="addcategorybox sugtarget" style="display: none;">
				</div>
				<label class="error float addCategoryAlreadyError categoryalreadyexist">Category already exist</label>
				<label class="startingprice">Starting Price</label>
				<input type="text" class="addCategoryPrice"/>
			</div>
			<br>
			<div class="tcenter textnowrap">
				<button class="button shadow addCategoryButton add">Add</button>
				<button class="button shadow modelcloser addCategoryCancel cancel">Cancel</button>
				<br><br>
				<label class="error addCommonError allfieldsarerequired">All Fields are required</label>
				<br><br>
			</div>
		</div>
	</div>
</div>
<%@ include file="photographerFooter.jsp"%>
<div class='fullloader'><img src='imgs/loading.svg'/></div>
<!--<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>-->
<script type="text/javascript" src="js/photographerJQ.js"></script>
<script type="text/javascript" src="js/photographerCode.js"></script>
<script type="text/javascript" src="js/photographerCategories.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script type="text/javascript" src="js/photographerLang.js"></script>
</body>
</html>