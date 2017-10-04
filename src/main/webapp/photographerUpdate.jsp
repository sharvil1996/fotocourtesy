<%@page import="com.dao.CityDAO"%>
<%@page import="com.bean.CityBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bean.PhotographerBean"%>
<%@page import="com.bean.AdminBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Photographer Update</title>
<noscript>
	<div style="color: #FF0000">place enable java script</div>
</noscript>
<link rel="icon" type="image/png" href="imgs/favicon.png" sizes="256x256">
<script type="text/javascript">
	function chngOne() {
		var val = document.getElementById('selCountryName').value;
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState === 4 && xhttp.status === 200) {
				document.getElementById('cmbCity').innerHTML = xhttp.responseText;
			}
		};
		xhttp.open("POST", "CityListServlet?valAjax=" + val, true);
		xhttp.send();
	}
</script>
</head>
<body>
	<%@ include file="adminHeader.jsp"%>
	<div style="margin-top: -850px; margin-left: 250px;">
		<section class="content-header">
		<h1>
			Photographer <small>update</small>
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
				<%
					PhotographerBean a = (PhotographerBean) request.getAttribute("photographerBean");
					if (a != null) {
				%>
				<form action="PhotographerUpdateServlet" method="post">

					<input type="hidden" name="photographerId"
						value="<%=a.getPhotographersId()%>"> <br />

					<%
						String fname = a.getPhotographersFirstName();
							if (fname == null)
								fname = "";
							String lname = a.getPhotographersLastName();
							if (lname == null)
								lname = "";
							String email = a.getPhotographersEmailId();
							if (email == null)
								email = "";
							String c1 = a.getPhotographersContact1();
							if (c1 == null)
								c1 = "";
							String c2 = a.getPhotographersContact2();
							if (c2 == null)
								c2 = "";
							String desc = a.getPhotographersDescription();
							if (desc == null || desc.equalsIgnoreCase("null"))
								desc = "";
							String expr = a.getPhotographersExperience();
							if (expr == null)
								expr = "";
							String addr = a.getPhotographersAddress();
							if (addr == null)
								addr = "";
							String username=a.getUsername();
							if(username==null)
								username="";
					%>
					<input type="hidden" name="hidUsername" value="<%=username%>">
					<div class="row ">
						<label class="col-sm-2"> <font size="+1">Full Name
								:</font>
						</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="txtFirstName"
								maxlength="20" placeholder="First Name" value="<%=fname%>"
								oninput="space(this);onlytext(this); nodigit(this);" /> <font
								color="red">${firstName} </font>
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="txtLastName"
								maxlength="20" placeholder="Last Name" value="<%=lname%>"
								oninput="space(this);onlytext(this); nodigit(this);" /> <font
								color="red">${lastName}</font>
						</div>
					</div>
					<br />
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Email :</font>
						</label>

						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtEmailId"
								placeholder="example@gmail.com" maxlength="50"
								value="<%=email%>" /> <font color="red">${emailId} </font>
						</div>
					</div>
					<br />
					
					<div class="row">
						<label class="col-sm-2"> <font size="+1">User Name :</font>
						</label>

						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtUsername"
								placeholder="Create User Name" maxlength="50"
								value="<%=username%>" /> <font color="red">${username} </font>
						</div>
					</div>
					<br />
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Contact No
								1 :</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtContactNo1"
								placeholder="Contact number" maxlength="15" value="<%=c1%>"
								oninput="space(this);onlydigit(this);" /> <font color="red">${contact1}</font>
						</div>
					</div>
					<br />
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Contact No
								2 :</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtContactNo2"
								placeholder="Contact number" maxlength="15" value="<%=c2%>"
								oninput="space(this);onlydigit(this);" /> <font color="red">${contact2}</font>
						</div>
					</div>
					<br />
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Experience
								:</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtExperience"
								placeholder="Experience(In Years)" maxlength="2"
								value="<%=expr%>" oninput="space(this);onlydigit(this);" /> <font
								color="red">${experience}</font>
						</div>
					</div>
					<br />
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Address :</font>
						</label>
						<div class="col-lg-6">
							<textarea rows="3" cols="89" maxlength="255" name="txtAddress"><%=addr%></textarea>
							<font color="red">${address}</font>
						</div>
					</div>
					<br />
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Description
								:</font>
						</label>
						<div class="col-lg-6">
							<textarea rows="3" cols="89" name="txtDescription"><%=desc%></textarea>
							<font color="red">${description}</font>
						</div>
					</div>
					<br />
					<%
					String sDate =  a.getStartDate();
					String eDate = a.getEndDate();
					if(sDate == null || sDate.equalsIgnoreCase("null")) sDate="";
					if(eDate == null || eDate.equalsIgnoreCase("null")) eDate="";
					%>
					<div class="row ">
						<label class="col-sm-2"> <font size="+1">Time Duration :</font>
						</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="txtStartDate"
								value="<%= sDate %>" /> <font
								color="red">${startDateError} </font>
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="txtEndDate"
								value="<%= eDate %>" /> <font
								color="red">${endDateError} </font>
						</div>
					</div>
					<br />
					<% String price = a.getPrice();
					if(price == null ||price.equalsIgnoreCase("null")) price="0";%>
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Price :</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtPrice"
								value="<%=price %>" /><font
								color="red">${priceError} </font>
						</div>
					</div>
					<br />
					<%
						String city = (String) a.getCityId();
					%>
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Select
								City : </font>
						</label>
						<div class="col-lg-6">
							<select class="form-control" name="cmbCity" id="cmbCity">
								<option value="0">Select The City</option>
								<%
									ArrayList<CityBean> list = new CityDAO().getList();
										for (CityBean c : list) {
											if (c.getCityId().equals(city)) {
								%>
								<option value="<%=c.getCityId()%>" selected="selected"><%=c.getCityName()%></option>
								<%
									} else {
								%>
								<option value="<%=c.getCityId()%>"><%=c.getCityName()%></option>
								<%
									}
										}
								%>
							</select><font color="red">${city}</font>
						</div>
					</div>
					<br />
					 
					 <br /> <label class="col-sm-2 control-label"></label> <input
						type="reset" value="Reset" name="reset" class="btn  btn-danger">
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<input type="Submit" value="Register" name="submit"
						class="btn btn-success">
				</form>
			</div>
		</div>
	</div>
	<%
		} else {

			request.setAttribute("msgLogin", "Please Login To Continue");
			response.sendRedirect("login.jsp");

		}
	%>
</body>
</html>




<%-- 


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<%
		PhotographerBean a = (PhotographerBean) request.getAttribute("photographerBean");
	%>
	<form action="PhotographerUpdateServlet" method="post">

		<input type="hidden" name="photographerId"
			value="<%=a.getPhotographersId()%>">
		<table>
			<tr>
				<td>Enter Your First Name</td>
				<td><input type="text" name="txtFirstName"
					value="<%=a.getPhotographersFirstName()%>"><font
					color="red">${firstName}</font></td>
			</tr>
			<tr>
				<td>Enter Your Last Name</td>
				<td><input type="text" name="txtLastName"
					value="<%=a.getPhotographersLastName()%>"><font color="red">${lastName}</font></td>
			</tr>
			<tr>
				<td>Enter Your ContactNo1</td>
				<td><input type="text" name="txtContactNo1"
					value="<%=a.getPhotographersContact1()%>"><font color="red">${contact1}</font></td>
			</tr>
			<tr>
				<td>Enter Your ContactNo2</td>
				<td><input type="text" name="txtContactNo2"
					value="<%=a.getPhotographersContact2()%>"><font color="red">${contact2}</font></td>
			</tr>
			<tr>
				<td>Enter Your EmailId</td>
				<td><input type="text" name="txtEmailId"
					value="<%=a.getPhotographersEmailId()%>"><font color="red">${emailId}</font></td>
			</tr>

			<tr>
				<td>Enter Your Description</td>
				<td><textarea rows="" cols="" name="txtDescription"><%=a.getPhotographersDescription()%></textarea><font
					color="red">${description}</font></td>
			</tr>

			<tr>
				<td>Enter Your Experience</td>
				<td><input type="text" name="txtExperience"
					value="<%=a.getPhotographersExperience()%>"><font
					color="red">${experience}</font></td>
			</tr>

			<tr>
				<td>Enter Your Address</td>
				<td><input type="text" name="txtAddress"
					value="<%=a.getPhotographersAddress()%>"><font color="red">${address}</font></td>
			</tr>

			<tr>
				<%
					String city = (String) a.getCityId();
				%>
				<td>Select Your City</td>
				<td><select name="cmbCity">
						<option value="0">Select The City</option>
						<%
							ArrayList<CityBean> list = new CityDAO().getList();
							for (CityBean c : list) {
								if (c.getCityId().equals(city)) {
						%>
						<option value="<%=c.getCityId()%>" selected="selected"><%=c.getCityName()%></option>
						<%
							} else {
						%>
						<option value="<%=c.getCityId()%>"><%=c.getCityName()%></option>
						<%
							}
							}
						%>
				</select> <font color="red">${city}</font></td>
			</tr>

			<tr>
				<td>Is Available</td>
				<td>
					<%
						System.out.print("sssssssss " + a.getPhotographersIsAvailable());
						if (a.getPhotographersIsAvailable().equalsIgnoreCase("n")) {
					%> <input type="radio" value="N" checked="checked"
					name="rdoIsAvailable" id="rdoIsAvailable">No <%
 	} else {
 %> <input type="radio" value="N" name="rdoIsAvailable"
					id="rdoIsAvailable">No <%
 	}
 %> <%
 	if (a.getPhotographersIsAvailable().equalsIgnoreCase("y")) {
 %> <input type="radio" value="Y" checked="checked"
					name="rdoIsAvailable" id="rdoIsAvailable">Yes <%
 	} else {
 %> <input type="radio" value="Y" name="rdoIsAvailable"
					id="rdoIsAvailable">Yes <%
 	}
 %>

				</td>
			</tr>

			<tr>
				<td colspan="2"><center>
						<input type="submit" value="Update">
					</center></td>
			</tr>

		</table>




	</form>

</body>
</html> --%>