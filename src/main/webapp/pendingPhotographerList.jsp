<%@page import="com.bean.PhotographerBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Photographer List</title>
<!-- <meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport"> -->
<link rel="icon" type="image/png" href="imgs/favicon.png" sizes="256x256">
<style>
td, tr, th {
	text-transform: uppercase;
}
</style>
</head>
<body>
	<%@include file="adminHeader.jsp"%>
	<div style="margin-top: -850px; margin-left: 250px;">
		<section class="content content-header">
		<h1>
			Pending Photographer <small>List</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Photographer</li>
		</ol>
		<br>
		<br>
		<br>
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-body">
						<table id="example1"
							class="table table-bordered table-hover table-striped">
							<%
							ArrayList<PhotographerBean> list = (ArrayList<PhotographerBean>) request.getAttribute("listOfPhotographer");
								if (list != null) {
							%>
							<thead class="gujju-theme text-uppercase">
								<tr>
									<th><center>Full Name</center></th>
									<th><center>Email</center></th>
									<th><center>User Name</center></th>
									<th><center>Contact number1</center></th>
									<th><center>Experience</center></th>
									<!-- <th><center>Description</center></th> -->
									<th><center>Address</center></th>
									<th><center>City - Country</center></th>
									<th><center>Action</center></th>
								</tr>
							</thead>


							<tbody>
								<%
								for (PhotographerBean a : list) {
								%>
								<tr>
									<td align="center"><%=a.getPhotographersFirstName()%> <%=a.getPhotographersLastName()%></td>
									<td align="center"><%=a.getPhotographersEmailId()%></td>
									<td align="center"><%=a.getUsername()%></td>
									<td align="center"><%=a.getPhotographersContact1()%></td>
									<td align="center"><%=a.getPhotographersExperience()%></td>
									<%-- <td align="center"><textarea disabled="disabled"><%=a.getPhotographersDescription()%></textarea></td> --%>
									<td align="center"><textarea disabled="disabled"><%=a.getPhotographersAddress()%></textarea></td>
									<td align="center"><%=a.getCityName()%> - <%=a.getCountryName()%></td>
									<td align="center"><a title="Accept" 
										href="PhotographerConfirmServlet?photographerId=<%=a.getPhotographersId()%>"><img
											src="photos/edit.ico" height="30" width="30"
											class="img-rounded" /></a>&nbsp;&nbsp;
										<a title="Reject" 
										href="PendingPhotographerRejectServlet?photographerId=<%=a.getPhotographersId()%>"><img
											src="photos/Recycle Bin.ico" height="30" width="30"
											class="img-rounded" /></a>
									<%
										}
									%>
								</tr>

								<%
									}

									else {
								%>

								<h1>
									<center>No Record Found....!</center>
								</h1>
								<%
									}
								%>

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		</section>
	</div>
</body>
</html>
