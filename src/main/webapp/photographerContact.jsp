<%@ page language="java" contentType="text/html;charset=UTF-8" 
pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1" /> -->
<meta name="viewport"
	content="user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1, width=device-width, height=device-height, target-densitydpi=device-dpi" />

<title>Contact | FotoCourtesty</title>
<link href="css/photographerStyle.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="imgs/favicon.ico">
<link rel="icon" type="image/png" href="imgs/favicon.png" sizes="256x256">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans:400,700&amp;subset=devanagari,latin-ext" rel="stylesheet">
</head>
<body>	
<%@ include file="photographerHeader.jsp"%>
<section style="background-image: url(imgs/2.jpeg);background-position: center center;background-size: cover;">
	<div class="container" style="padding: 10px;padding-top: 125px;">
		<div class="jumbotext contactus">Contact Us</div>
	</div>
</section>
<section>
	<div class="container">
		<div class="row">
			<div class="cm6">
				<h2 class="tleft proprietary" style="font-size: 1.5em;">Proprietary</h2>
				<div class="paragraph">D J IT Solutions</div>
			</div>
			<div class="cm6">
				<h2 class="tleft calluson" style="font-size: 1.5em;">Call Us on</h2>
				<div class="paragraph noblock">+91 8980555590</div><br>
				<a href="tel:+918980555590" class="button small mobilebutton callnow" style="margin-top: 10px;">Call Now</a>
				<div class="normalspace"></div>
			</div>
		</div>
		<div class="row">
			<div class="cm6">
				<h2 class="tleft skypeid" style="font-size: 1.5em;">Skype ID</h2>
				<div class="paragraph noblock">shahdeep1989</div>
			</div>
			<div class="cm6">
				<h2 class="tleft email" style="font-size: 1.5em;">Email</h2>
				<div class="paragraph noblock">shahdeep1989@gmail.com</div><br>
				<a href="mailto:shahdeep1989@gmail.com" class="button small mobilebutton emailnow" style="margin-top: 10px;">Email Now</a>
			</div>
		</div>
	</div>
</section>
<%@ include file="photographerFooter.jsp"%>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="js/photographerCode.js"></script>
<script>
	$("a").each(function(){
		if($(this).text()=="Contact")$(this).remove();
	});
</script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script type="text/javascript" src="js/photographerLang.js"></script>
</body>
</html>
