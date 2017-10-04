<%@ page language="java" contentType="text/html;charset=UTF-8" 
pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<!-- <meta name="viewport" content="width=device-width, initial-scale=1" /> -->
<meta name="viewport"
	content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width, height=device-height, target-densitydpi=device-dpi" />

<title>Album | FotoCourtesty</title>
<link href="css/photographerStyle.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="imgs/favicon.ico">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans:400,700&amp;subset=devanagari,latin-ext" rel="stylesheet">
<link rel="icon" type="image/png" href="imgs/favicon.png" sizes="256x256">
</head>
<body>	
<%@ include file="photographerHeader.jsp"%>

<span class="pid hidden"><%= photographerBean.getPhotographersId() %><!-- 8D1KPwwdDDhjnTZ --></span>
<section style="background-image: url(imgs/album.jpg);background-position: center center;background-size: cover;">
	<div class="container" style="padding: 10px;padding-top: 125px;">
		<div class="jumbotext albums">Albums</div>
	</div>
</section>
<section class="backgrey">
	<div class="container noalbum" style="display: none;">
		<div class="whitebg shadow">
			<br><br><br>
			<h1 class="noalbumsfound">No Albums Found</h1>
			<div class="tcenter"><button class="button shadow modelbutton addalbum" type="button" style="margin-bottom: 30px;" target=".addAlbumModel">Add Album</button></div>
			<br><br>
		</div>
	</div>
	<div class="container albumList" style="display: none;">
		<div class="tright"><button class="button shadow modelbutton addalbum" type="button" style="margin-bottom: 30px;" target=".addAlbumModel">Add Album</button></div>
		<div class="row albumparent">
		</div>
	</div>
</section>
<div class="addAlbumModel model">
	<div class="body">
		<div class="content">
			<h1 class="textnowrap tleft addalbum">Add Album</h1>
			<br><br>
			<div class="form nopadding noshadow">
				<label class="albumname">Album Name</label>
				<input type="text" class="addAlbumName"/>
				<label class="error float addNameAlreadyError albumalreadyexist">Album already exist</label>
				<label class="flickrlink">Flickr Link</label>
				<input type="text" class="addAlbumLink"/>
				<label class="error float addLinkError invalidlink">Invalid Link</label>
				<label class="description">Description</label>
				<input type="text" class="addAlbumDesc"/>
			</div>
			<br>
			<div class="tcenter textnowrap">
				<button class="button shadow addAlbumButton add">Add</button>
				<button class="button shadow modelcloser addAlbumCancel cancel">Cancel</button>
				<br><br>
				<label class="error addCommonError allfieldsarerequired">All Fields are required</label>
				<br><br>
			</div>
		</div>
	</div>
</div>
<div class="editAlbumModel model">
	<div class="body">
		<div class="content">
			<h1 class="textnowrap tleft editalbum">Edit Album</h1>
			<br><br>
			<div class="form nopadding noshadow">
				<label class="albumname">Album Name</label>
				<input type="text" class="editAlbumName"/>
				<label class="error float editNameAlreadyError albumalreadyexist">Album already exist</label>
				<label class="flickrlink">Flickr Link</label>
				<input type="text" class="editAlbumLink"/>
				<label class="error float editLinkError invalidlink">Invalid Link</label>
				<label class="description">Description</label>
				<input type="text" class="editAlbumDesc"/>
			</div>
			<br>
			<div class="tcenter textnowrap">
				<button class="button shadow editAlbumButton save">Save</button>
				<button class="button shadow modelcloser editAlbumCancel cancel">Cancel</button>
				<br><br>
				<label class="error editCommonError allfieldsarerequired">All Fields are required</label>
				<br><br>
			</div>
		</div>
	</div>
</div>
<%@ include file="photographerFooter.jsp"%><div class='fullloader'><img src='imgs/loading.svg'/></div>
<!--<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>-->
<script type="text/javascript" src="js/photographerJQ.js"></script>
<script type="text/javascript" src="js/photographerCode.js"></script>
<script type="text/javascript" src="js/photographerAlbum.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script type="text/javascript" src="js/photographerLang.js"></script>
</body>
</html>