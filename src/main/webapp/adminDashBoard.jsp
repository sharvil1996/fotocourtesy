<%@page import="com.dao.CategoryDAO"%>
<%@page import="com.dao.AlbumDAO"%>
<%@page import="com.dao.PhotographerDAO"%>
<%@page import="com.dao.AdminDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Admin Dashboard</title>

<noscript>
	<div style="color: #FF0000">place enable java script</div>
</noscript>
<link rel="icon" type="image/png" href="imgs/favicon.png" sizes="256x256">
</head>
<body>
	<%@include file="adminHeader.jsp"%>
	<div style="margin-top: -850px; margin-left: 250px;">
		<section class="content-header">
		<h1>
			Dashboard <small></small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Dashboard</li>
		</ol>
		</section>
		<br> <br>
		<br><br>

		<div class="container">
			<div class="" align="center">
				<div class="row">
				<div class="col-sm-1"></div>
					<div class="col-xs-9">
						<div class="panel panel-default"
							style="background-color: rgba(24,97,139,1);">
							<div class="panel-body " style="font-weight: bold; color: white;">Total</div>
						</div>
					</div>
					<div class="col-sm-1"></div>
				</div>
				<div class="row">
				<div class="col-sm-1"></div>
					<div class="col-sm-3">

						<div class="panel panel-default  ">
							<div class="panel-heading "
								style="color: white; background-color: #367FA9;">
								<i class="fa-ticket fa" style="font-size: 40px;">&emsp;&emsp;
									 <%=new PhotographerDAO().list().size()%> 
								</i>
							</div>
							<div class="panel-body gujju-theme-d2" style="font-weight: bold;">Accepted photographer</div>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="panel panel-default   gujju-card-16 ">
							<div class="panel-heading  "
								style="color: white; background-color: #367FA9;">
								<i class="fa-ticket fa" style="font-size: 40px;">&emsp;&emsp;
									 <%=new PhotographerDAO().pendingList().size()%> 
								</i>
							</div>
							<div class="panel-body gujju-theme-d2" style="font-weight: bold;">Pending photographer</div>
						</div>
					</div>

					<div class="col-sm-3">
						<div class="panel panel-default     gujju-card-16 ">
							<div class="panel-heading "
								style="color: white; background-color: #367FA9;">
								<i class="fa-ticket fa" style="font-size: 40px;">&emsp;&emsp;
									 <%=new PhotographerDAO().rejectedList().size()%> 
								</i>
							</div>
							<div class="panel-body gujju-theme-d2" style="font-weight: bold;">Rejected photographer</div>
						</div>
					</div>
					<div class="col-sm-1"></div>
				</div>
				
				<div class="row">
				<div class="col-sm-1"></div>
					<div class="col-sm-3">
						<div class="panel panel-default     gujju-card-16 ">
							<div class="panel-heading "
								style="color: white; background-color: #367FA9;">
								<i class="fa fa-user-plus" style="font-size: 40px;">&emsp;&emsp;
									 <%=new AdminDAO().list().size()%> 
								</i>
							</div>
							<div class="panel-body gujju-theme-d2" style="font-weight: bold;">Admin</div>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="panel panel-default     gujju-card-16 ">
							<div class="panel-heading "
								style="color: white; background-color: #367FA9;">
								<i class="fa fa-ticket" style="font-size: 40px;">&emsp;&emsp;
									 <%=new AlbumDAO().list().size()%>
								</i>
							</div>
							<div class="panel-body gujju-theme-d2" style="font-weight: bold;">
								Album</div>
						</div>
					</div>

					<div class="col-sm-3">
						<div class="panel panel-default     gujju-card-16 ">
							<div class="panel-heading "
								style="color: white; background-color: #367FA9;">
								<i class="fa fa-newspaper-o" style="font-size: 40px;">&emsp;&emsp;
									 <%=new CategoryDAO().list().size()%>
								</i>
							</div>
							<div class="panel-body gujju-theme-d2" style="font-weight: bold;">Category</div>
						</div>
					</div>
					<div class="col-sm-1"></div>
				</div>


			</div>
		</div>
	</div>
	<!-- <script>
		$(document).ready(function() {
			$("#desh").addClass("effect");
		});
	</script> -->
	<%-- <%@include file="footer.jsp" %> --%>
</body>
</html>