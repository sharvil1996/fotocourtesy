<!DOCTYPE html>
<%@page import="com.bean.AdminBean"%>
<html>
<head>

<title>Photography | Dashboard</title>

<noscript>
	<meta HTTP-EQUIV="Refresh" CONTENT="0;URL=JavaScriptErrorPage.jsp">
</noscript>

<!-- <link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.3.11/css/AdminLTE.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.3.11/css/skins/_all-skins.min.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.15/css/dataTables.bootstrap.css" />
 -->
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/ionicons.min.css">
<link rel="stylesheet" href="css/AdminLTE.min.css">
<link rel="stylesheet" href="css/_all-skins.min.css">
<link rel="stylesheet" href="css/dataTables.bootstrap.css">

<link rel="icon" type="image/png" href="imgs/favicon.png"
	sizes="256x256">
</head>
<body class="hold-transition skin-blue sidebar-mini fixed">
	<%
		AdminBean adminBeanHeader = (AdminBean) session.getAttribute("adminBean");

		if (adminBeanHeader != null) {
	%>
	<div class="wrapper">
		<header class="main-header">
			<a href="adminDashBoard.jsp" class="logo"> <span
				class="logo-mini"><b></b>P+</span> <span class="logo-lg"><b>Photography</b></span>
			</a>
			<nav class="navbar navbar-static-top" role="navigation">
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
					role="button"></a>
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<li class="dropdown user user-menu"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"> <i
								class="fa-user fa"></i> <%=adminBeanHeader.getEmailId()%> <b
								class="caret"></b>
						</a>
							<ul class="dropdown-menu" style="border-color: black;">
								<li class="user-header"><img src="imgs/fotoCourtesy.png"
									class="img-circle" alt="User Image"
									style="border-radius: 0; border: none; width: auto; max-height: 90px;">
									<p>
										<%=adminBeanHeader.getFirstName() + " " + adminBeanHeader.getLastName()%>
									</p></li>
								<li class="user-footer">
									<div class="pull-left">
										<a href="adminChangePassword.jsp"
											class="btn btn-default btn-flat">Change Password</a>
									</div>

									<div class="pull-right">
										<a href="AdminLogoutServlet" class="btn btn-default btn-flat">Sign
											out</a>
									</div>
								</li>
							</ul></li>
					</ul>
				</div>
			</nav>
		</header>
		<aside class="main-sidebar" style="min-height: 900px;">
			<section class="sidebar">
				<div class="user-panel">
					<div class="pull-left image">
						<img src="imgs/fotoCourtesy.png" class="img-circle"
							alt="User Image" style="border-radius: 0;">
					</div>
					<div class="pull-left info">
						<p style="margin-top: 10px;">
							&emsp;
							<%=adminBeanHeader.getFirstName() + " " + adminBeanHeader.getLastName()%>
						</p>
					</div>
				</div>
				<ul class="sidebar-menu">
					<li class="header">MAIN NAVIGATION</li>
					<li class=" treeview"><a href="adminDashBoard.jsp"> <i
							class="fa fa-dashboard"></i> <span>Dashboard</span> <!--<i class="fa fa-angle-left pull-right"></i>-->
					</a></li>
					<li class=" treeview"><a href="AdminListServlet"> <i
							class="fa fa-user-secret"></i> <span>Admin </span> <!--<i class="fa fa-angle-left pull-right"></i>-->
					</a></li>

					<li class="treeview"><a href="PhotographerListServlet"> <i
							class="fa fa-user"></i> <span>Photographers</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li><a href="photographerInsert.jsp"><i class="fa fa-gg"></i>
									Insert Photographer</a></li>
							<li><a href="PhotographerListServlet"><i
									class="fa fa-gg"></i> Accepted Photographer</a></li>
							<li><a href="PendingPhotographerListServlet"><i class="fa fa-gg"></i>
									Pending Photographer</a></li>
							<li><a href="RejectedPhotographerListServlet"><i
									class="fa fa-gg"></i> Rejected Photographer</a></li>
						</ul></li>

					<!-- <li class="treeview"><a href="PhotographerListServlet"> <i
							class="fa fa-user"></i> <span>Photographers</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li><a href="photographerInsert.jsp"><i class="fa fa-gg"></i>Insert
									Photographer</a></li>
						</ul>
						<ul class="treeview-menu">
							<li><a href="PhotographerListServlet"><i
									class="fa fa-gg"></i> Accepted Photographer</a></li>
						</ul>
						<ul class="treeview-menu">
							<li><a href="PendingPhotographerListServlet"><i
									class="fa fa-gg"></i> Pending Photographer</a></li>
						</ul>
						<ul class="treeview-menu">
							<li><a href="RejectedPhotographerListServlet"><i
									class="fa fa-gg"></i> Rejected Photographer</a></li>
						</ul></li> -->

					

					<li class="treeview"><a href="CategoryListServlet"> <i
							class="fa fa-sitemap"></i> <span>Category Details</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li><a href="CategoryListServlet"><i class="fa fa-gg"></i>
									Category</a></li>
							<li><a href="CategoryPriceListServlet"><i
									class="fa fa-gg"></i> Category Price</a></li>
						</ul></li>
					<li class=" treeview"><a href="AlbumListServlet"> <i
							class="fa fa-picture-o"></i> <span>Album </span> <!--<i class="fa fa-angle-left pull-right"></i>-->
					</a></li>
					
					<li class=" treeview"><a href="CountryListServlet"> <i
							class="fa fa-flag"></i> <span>Country </span> <!--<i class="fa fa-angle-left pull-right"></i>-->
					</a></li>
					<li class=" treeview"><a href="CityListServlet"> <i
							class="fa fa-map-marker"></i> <span>City </span> <!--<i class="fa fa-angle-left pull-right"></i>-->
					</a></li>

					<!-- 

					-----------------------------------Management-------------------------------  

					<li class="treeview"><a href="PhotographerListServlet"> <i
							class="fa fa-user"></i> <span>User's Management</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">


							-----------------------------------Management > Admin-------------------------------  

							<li class="treeview"><a href="AdminListServlet"> <i
									class="fa fa-user-secret"></i> <span>Admin Management</span> <i
									class="fa fa-angle-left pull-right"></i>
							</a>
								<ul class="treeview-menu">
									<li><a href="adminInsert.jsp"><i
											class="fa fa-user-plus"></i> Admin Insert </a></li>
									<li><a href="AdminListServlet"><i
											class="fa fa-users"></i> Admin List </a></li>
								</ul></li>

							-----------------------------------Add special Questions-------------------------------  

							<li class="treeview"><a href="PhotographerListServlet">
									<i class="fa fa-user"></i> <span>Photographer
										Management</span> <i class="fa fa-angle-left pull-right"></i>
							</a>
								<ul class="treeview-menu">
									<li><a href="photographerInsert.jsp"><i
											class="fa fa-user-plus"></i> add Photographer </a></li>
									<li><a href="PhotographerListServlet"><i
											class="fa fa-users"></i> List Photographer </a></li>
								</ul></li>


						</ul></li>

					-----------------------------------Management close-------------------------------  


					<li class="treeview"><a href="AlbumListServlet"> <i
							class="fa fa-picture-o"></i> <span>Album Details</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li><a href="albumInsert.jsp"><i class="fa fa-gg"></i>
									Album Insert</a></li>
							<li><a href="AlbumListServlet"><i class="fa fa-gg"></i>
									Album List</a></li>
						</ul></li>


					<li class="treeview"><a href="CategoryListServlet"> <i
							class="fa fa-sitemap"></i> <span>Category Details</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li><a href="categoryInsert.jsp"><i class="fa fa-gg"></i><span>
										Category Insert</span></a></li>
							<li><a href="CategoryListServlet"><i class="fa fa-gg"></i>
									Category List</a></li>
						</ul></li>

					<li class="treeview"><a href="CountryListServlet"> <i
							class="fa fa-flag"></i> <span>Country Details</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li><a href="countryInsert.jsp"><i class="fa fa-gg"></i><span>
										Country Insert</span></a></li>
							<li><a href="CountryListServlet"><i class="fa fa-gg"></i>
									Country List</a></li>
						</ul></li>

					<li class="treeview"><a href="CityListServlet"> <i
							class="fa fa-map-marker"></i> <span>City Details</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li><a href="cityInsert.jsp"><i class="fa fa-gg"></i><span>
										City Insert</span></a></li>
							<li><a href="CityListServlet"><i class="fa fa-gg"></i>
									City List</a></li>
						</ul></li> -->

				</ul>
			</section>
		</aside>
		<div class="content-wrapper" style="min-height: 900px; height: 900px;">
			<section class="content-header">
				<h1>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <small>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</small>
				</h1>
				<!--  <ol class="breadcrumb">
            <li> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
            <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
          </ol>   -->
			</section>
		</div>
	</div>

	<div>
		<script type="text/javascript" src="javaScript/mainscript.js"></script>
		<!-- 		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/jQuery-slimScroll/1.3.8/jquery.slimscroll.min.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/fastclick/1.0.6/fastclick.min.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.3.11/js/app.min.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-bootstrap/0.5pre/assets/js/demo.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.15/js/jquery.dataTables.min.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.15/js/dataTables.bootstrap.min.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.js"></script>
			 -->
		<script src="javaScript/jQuery-2.1.4.min.js"></script>
		<script src="javaScript/bootstrap.min.js"></script>
		<script src="javaScript/jquery.slimscroll.min.js"></script>
		<script src="javaScript/fastclick.min.js"></script>
		<!-- <script src="javaScript/app.min.js"></script> -->
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.3.11/js/app.min.js"></script>

		<script src="javaScript/demo.js"></script>
		<script src="javaScript/datatables/jquery.dataTables.min.js"></script>
		<script src="javaScript/datatables/dataTables.bootstrap.min.js"></script>
		<script type="text/javascript" src="javaScript/jquery-ui.js"></script>

		<script>
			$(function() {
				$("#example1").DataTable();
				$('#example2').DataTable({
					"paging" : true,
					"lengthChange" : false,
					"searching" : false,
					"ordering" : true,
					"info" : true,
					"autoWidth" : false
				});
			});
		</script>

	</div>
	<%
		} else {

			request.setAttribute("msgLogin", "Please Login To Continue");
			request.getRequestDispatcher("adminLogin.jsp").forward(request, response);

		}
	%>
</body>
</html>
