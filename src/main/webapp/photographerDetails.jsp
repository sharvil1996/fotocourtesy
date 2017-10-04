<%@page import="com.bean.PhotographerBean"%>
<%@page import="com.dao.CountryDAO"%>
<%@page import="com.bean.CountryBean"%>
<%@page import="com.dao.CityDAO"%>
<%@page import="com.bean.CityBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Photographer Details</title>
<noscript>
	<div style="color: #FF0000">place enable java script</div>
</noscript>
<link rel="icon" type="image/png" href="imgs/favicon.png" sizes="256x256">
</head>
<body>
<% PhotographerBean photographer = (PhotographerBean) request.getAttribute("photographer"); %>
	<%@ include file="adminHeader.jsp"%>
	<div style="margin-top: -850px; margin-left: 250px;">
		<section class="content-header">
		<h1>
			<%= photographer.getUsername()%>'s <small>Details</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Photographer</li>
		</ol>
		</section>
		<br> <br>
		<div class="col-lg-6">
			<div class="container">
					<br />
					<div class="row ">
						<label class="col-sm-2"> <font size="+1">Full Name
								:</font>
						</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="txtFirstName"
								value="<%= photographer.getPhotographersFirstName() %>" readonly="readonly"/> 
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="txtLastName"
								value="<%= photographer.getPhotographersLastName() %>" readonly="readonly" /> 
						</div>
					</div>
					<br />
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Email :</font>
						</label>

						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtEmailId"
								value="<%= photographer.getPhotographersEmailId() %>" readonly="readonly" />
						</div>
					</div>
					<br />
					
					<div class="row">
						<label class="col-sm-2"> <font size="+1">User Name
								:</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtUsername"
								value="<%= photographer.getUsername()%>" readonly="readonly" />
						</div>
					</div>
					<br />
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Contact No 1 :</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtContactNo1"
								value="<%= photographer.getPhotographersContact1() %>" readonly="readonly" />
						</div>
					</div>
					<br />
					<%
					String no = photographer.getPhotographersContact2();
					if(no==null || no.equalsIgnoreCase("null"))
						no="";
					%>
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Contact No 2 :</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtContactNo2"
								value="<%= no %>" readonly="readonly" />
						</div>
					</div>
					<br />
					
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Address :</font>
						</label>
						<div class="col-lg-6">
							<textarea rows="3" cols="89" maxlength="255" name="txtAddress" disabled="disabled"><%= photographer.getPhotographersAddress() %></textarea>
						</div>
					</div>
					<br />
					<%
					String desc = photographer.getPhotographersDescription();
					if(desc==null || desc.equalsIgnoreCase("null")) desc="";
					%>
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Description :</font>
						</label>
						<div class="col-lg-6">
							<textarea rows="3" cols="89" maxlength="255" name="txtDescription" disabled="disabled"><%= desc %></textarea>
						</div>
					</div>
					<br />
					<% 
						String status = photographer.getPhotographersIsAvailable();
					if(status.equalsIgnoreCase("y"))
						status="Accepted";
					else if(status.equalsIgnoreCase("N"))
						status="Rejected";
					else status="Pendeing";
					
					%>
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Status :</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtCountryName"
								value="<%= status %>" readonly="readonly" />
						</div>
					</div>
					<br />
					<%
					String sDate =  photographer.getStartDate();
					String eDate = photographer.getEndDate();
					if(sDate == null || sDate.equalsIgnoreCase("null")) sDate="";
					if(eDate == null || eDate.equalsIgnoreCase("null")) eDate="";
					%>
					<div class="row ">
						<label class="col-sm-2"> <font size="+1">Time Duration :</font>
						</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="txtFirstName"
								value="<%= sDate %>" readonly="readonly" /> 
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="txtLastName"
								value="<%= eDate %>" readonly="readonly" /> 
						</div>
					</div>
					<br />
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Experience :</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtCountryName"
								value="<%= photographer.getPhotographersExperience() %>" readonly="readonly" />
						</div>
					</div>
					<br />
					<% String price = photographer.getPrice();
					if(price == null ||price.equalsIgnoreCase("null")) price="0";%>
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Price :</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtCountryName"
								value="<%=price %>" readonly="readonly" />
						</div>
					</div>
					<br />
					
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Country :</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtCountryName"
								value="<%= photographer.getCountryName()%>" readonly="readonly" />
						</div>
					</div>
					<br />
					<div class="row">
						<label class="col-sm-2"> <font size="+1">City :</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtCityName"
								value="<%= photographer.getCityName() %>" readonly="readonly" />
						</div>
					</div>
			</div>
		</div>
	</div>
</body>
</html>