<%@page import="com.bean.AdminBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Admin List</title>
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
			Admin <small>List</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Admin</li>
		</ol>
		<br>
		<br>
		<a href="adminInsert.jsp"><input type="button" value="ADD"
			name="ADD" class="btn btn-primary"></a> <br>
		<br>
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-body">
						<table id="example1"
							class="table table-bordered table-hover table-striped">
							<%
								ArrayList<AdminBean> listofAdmin = (ArrayList) request.getAttribute("listOfAdmin");
								if (listofAdmin != null) {
							%>
							<thead class="gujju-theme text-uppercase">
								<tr>
									<th><center>Full Name</center></th>
									<th><center>Email</center></th>
									<th><center>Contact number1</center></th>
									<th><center>Contact number2</center></th>
									<th><center>Is Available</center></th>
									<th><center>Action</center></th>
								</tr>
							</thead>


							<tbody>
								<%
									for (int i = 0; i < listofAdmin.size(); i++) {
											AdminBean adminBean = listofAdmin.get(i);
								%>
								<tr>
									<td align="center"><%=adminBean.getFirstName()%> <%=adminBean.getLastName()%></td>
									<td align="center"><%=adminBean.getEmailId()%></td>
									<td align="center"><%=adminBean.getContact1()%></td>
									<td align="center"><%=adminBean.getContact2()%></td>
									<%
										if (adminBean.getIsAvailable().equals("Y")) {
									%>
									<td align="center"><img src="photos/yes1.jpg" height="30"
										width="30" class="img-rounded" /></td>
									<%
										} else {
									%>
									<td align="center"><img src="photos/no1.jpg" height="30"
										width="30" class="img-rounded" /></td>
									<%
										}
									%>
									<td align="center"><a title="Update"
										href="AdminEditServlet?adminId=<%=adminBean.getAdminId()%>"><img
											src="photos/edit.ico" height="30" width="30"
											class="img-rounded" /></a>&nbsp;&nbsp; <a title="Delete"
										href="AdminDeleteServlet?adminId=<%=adminBean.getAdminId()%>"><img
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


