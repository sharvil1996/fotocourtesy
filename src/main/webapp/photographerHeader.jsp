<%@page import="com.bean.PhotographerBean"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contact | FotoCourtesty</title>
<!-- <link href="css/photographerStyle.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="imgs/favicon.ico">
<link rel="icon" type="image/png" href="imgs/favicon.png" sizes="256x256">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans:400,700&amp;subset=devanagari,latin-ext" rel="stylesheet"> -->
</head>
<body>
	<%
		PhotographerBean photographerBean = (PhotographerBean) session.getAttribute("photographerBean");
		if (photographerBean != null) {
	%>
	<header>
	<div class="container">
		<a href="photographerIndex.jsp" class="logo"><img
			src="imgs/logo.svg" alt="" /></a>
		<div class="navpanel">
			<a href="photographerIndex.jsp" class="navitem dashboard">Dashboard</a>
			<a href="photographerAlbum.jsp" class="navitem albums">Albums</a> <a
				href="photographerCategory.jsp" class="navitem categories">Categories</a>
			<a href="photographerContact.jsp" class="navitem contact">Contact</a>
			<a href="PhotographerLogoutServlet" class="navitem logout">Logout</a>
		</div>
		<div class="menubutton">
			<span></span><span></span><span></span>
		</div>
	</div>
	<ul class="mobilemenu">
		<li style="background-color: white; cursor: auto;"><img
			src="imgs/Close.svg" class="mobilemenuclose" /></li>
	</ul>
	</header>
	<!-- <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="js/photographerCode.js"></script>
<script type="text/javascript" src="js/photographerHome.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script type="text/javascript" src="js/photographerLang.js"></script>
 -->
	<%
		} else
			response.sendRedirect("login.jsp");
	%>

</body>
</html>