<%@page import="com.bean.AdminBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Admin Update</title>
<noscript>
	<div style="color: #FF0000">place enable java script</div>
</noscript>
<link rel="icon" type="image/png" href="imgs/favicon.png" sizes="256x256">
</head>
<body>
	<%@ include file="adminHeader.jsp"%>
	<div style="margin-top: -850px; margin-left: 250px;">
		<section class="content-header">
		<h1>
			Admin <small>Update</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Admin</li>
		</ol>
		</section>
		<br> <br>
		<div class="col-lg-6">
			<div class="container">
				<%
					AdminBean a = (AdminBean) request.getAttribute("adminBean");
					if (a != null) {
				%>
				<form action="AdminUpdateServlet" method="post">
					<input type="hidden" name="adminId" value="<%=a.getAdminId()%>">
					<br />
					<div class="row ">
						<label class="col-sm-2"> <font size="+1">Name :</font>
						</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="txtFirstName"
								maxlength="20" placeholder="First Name"
								value="<%=a.getFirstName()%>"
								oninput="space(this);onlytext(this); nodigit(this);" /> <font
								color="red">${firstName} </font>
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="txtLastName"
								maxlength="20" placeholder="Last Name"
								value="<%=a.getLastName()%>"
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
								value="<%=a.getEmailId()%>" /> <font color="red">${emailId}
							</font>
						</div>
					</div>
					<br />
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Contact No
								1 :</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtContactNo1"
								placeholder="Contact number" maxlength="15"
								value="<%=a.getContact1()%>"
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
								placeholder="Contact number" maxlength="15"
								value="<%=a.getContact2()%>"
								oninput="space(this);onlydigit(this);" /> <font color="red">${contact2}</font>
						</div>
					</div>
					<br />
					<div class="row">

						<%
							String isAvailable = (String) (a.getIsAvailable() + "");
								String yes = "", no = "";
								if (isAvailable.equalsIgnoreCase("Y"))
									yes = "checked";
								else
									no = "checked";
						%>

						<label class="col-sm-3"><font size="+1">Is
								Available : </font> </label>
						<div class="col-md-7">
							<div class="col-sm-6">
								<input type="radio" class="radio" name="rdoIsAvailable" value="Y"
									<%=yes%> />Yes
							</div>
							<div class="col-sm-6">
								<input type="radio" class="radio" name="rdoIsAvailable" value="N"
									<%=no%> />No
							</div>
						</div>

					</div>
					<br /> <br /> <label class="col-sm-2 control-label"></label> <input
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




