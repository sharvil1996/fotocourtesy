<%@page import="com.bean.CategoryPriceBean"%>
<%@page import="com.bean.CityBean"%>
<%@page import="com.bean.AdminBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Category Price List</title>
<link rel="icon" href="photos/daiict.png" />
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
			Category Price <small>List</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Category Price</li>
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
							ArrayList<CategoryPriceBean> list = (ArrayList<CategoryPriceBean>) request
							.getAttribute("listOfCategoryPrice");
								if (list != null && list.size() != 0) {
							%>
							<thead class="gujju-theme text-uppercase">
								<tr>
									<th><center>Photographer Name</center></th>
									<th><center>User Name</center></th>
									<th><center>Category Name</center></th>
									<th><center>Price</center></th>
									<th><center>Action</center></th>
								</tr>
							</thead>


							<tbody>
								<%
									for (CategoryPriceBean bean : list) {
								%>
								<tr>
									<td align="center"><%=bean.getPhotographerFirstName() + " " +bean.getPhotographerLastName() %></td>
									<td align="center"><%=bean.getPhotographerUserName()%></td>
									<td align="center"><%=bean.getCategoryName()%></td>
									<td align="center"><%=bean.getPrice()%></td>
									<td align="center"><a title="Update"
										href="CategoryPriceEditServlet?categoryPriceId=<%=bean.getCategoryPriceId()%>"><img
											src="photos/edit.ico" height="30" width="30"
											class="img-rounded" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
										title="Delete"
										href="CategoryPriceDeleteServlet?categoryPriceId=<%=bean.getCategoryPriceId()%>"><img
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












<%-- <%@page import="java.util.ArrayList"%>
<%@page import="com.bean.CategoryPriceBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<%
	ArrayList<CategoryPriceBean> listOfCategoryPrice = (ArrayList<CategoryPriceBean>) request
			.getAttribute("listOfCategoryPrice");
if(listOfCategoryPrice!=null)
{
%>
</head>
<body>

	<table>
		<tr>
			<th>Photographer Name</th>
			<th>User Name</th>
			<th>Category Name</th>
			<th>Price</th>
			<th>Action</th>
		</tr>
		<%
			for (CategoryPriceBean c : listOfCategoryPrice) {
		%>
		<tr>
		<td><%=c.getPhotographerFirstName()+" "+c.getPhotographerLastName() %></td>
		<td><%=c.getPhotographerUserName()%></td>
		<td><%=c.getCategoryName() %></td>
		<td><%=c.getPrice()%></td>
		<td><a href="CategoryPriceEditServlet?categoryPriceId=<%=c.getCategoryPriceId()%>">Update</a><a href="CategoryPriceDeleteServlet?categoryPriceId=<%=c.getCategoryPriceId()%>">Delete</a></td>

		</tr>
		<%
			}
		
}else
{%>
<h1>There is no Data</h1>	
<%}
		%>

	</table>

</body>
</html> --%>