<%@page import="com.bean.AlbumBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Album List</title>
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
			Album <small>List</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Album</li>
		</ol>
		<br>
		<br>
		<a href="albumInsert.jsp"><input type="button" value="ADD"
			name="ADD" class="btn btn-primary"></a> <br>
		<br>
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-body">
						<table id="example1"
							class="table table-bordered table-hover table-striped">
							<%
								ArrayList<AlbumBean> list = (ArrayList<AlbumBean>) request.getAttribute("listOfAlbum");
								if (list != null) {
							%>
							<thead class="gujju-theme text-uppercase">
								<tr>
									<th><center>Album Name</center></th>
									<th><center>Description</center></th>
									<th><center>Album Link</center></th>
									<th><center>Photographer</center></th>
									<th><center>Action</center></th>
								</tr>
							</thead>


							<tbody>
								<%
									for (AlbumBean a : list) {
								%>
								<tr>
									<td align="center"><%=a.getAlbumName()%></td>
									<td align="center"><%=a.getAlbumDescription()%></td>
									<td align="center"><textarea disabled="disabled"><%=a.getAlbumLink()%></textarea></td>
									<td align="center"><%=a.getPhotographersFirstName()%> <%=a.getPhotographersLastName()%></td>
									<td align="center"><a title="Update"
										href="AlbumEditServlet?albumId=<%=a.getAlbumId()%>"><img
											src="photos/edit.ico" height="30" width="30"
											class="img-rounded" /></a>&nbsp;&nbsp; <a title="Delete"
										href="AlbumDeleteServlet?albumId=<%=a.getAlbumId()%>"><img
											src="photos/Recycle Bin.ico" height="30" width="30"
											class="img-rounded" /></a> <%
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
