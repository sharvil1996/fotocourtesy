<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Admin Insert</title>
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
		Admin <small>Insert</small>
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
			<form action="AdminInsertServlet" method="post">
				<br />
				<div class="row ">
					<label class="col-sm-2"> <font size="+1">Name :</font> </label>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="txtFirstName"
							maxlength="20" placeholder="First Name"
							value="${txtFirstName}" oninput="space(this);onlytext(this); nodigit(this);" /> 
						<font color="red">${firstName}
						</font>
					</div>
					<div class="col-sm-3">
						<input type="text" class="form-control" name="txtLastName"
							maxlength="20" placeholder="Last Name"
							value="${txtLastName}" oninput="space(this);onlytext(this); nodigit(this);" /> 
							<font color="red">${lastName}</font>
					</div>
				</div>
				<br />
				<div class="row">
					<label class="col-sm-2"> <font size="+1">Email :</font> </label>

					<div class="col-lg-6">
						<input type="text" class="form-control" name="txtEmailId"
							placeholder="example@gmail.com" maxlength="50"
							value="${txtEmailId}" /> <font color="red">${emailId}
						</font>
					</div>
				</div>
				<br />
				<div class="row">
					<label class="col-sm-2"> <font size="+1">Password :</font>
					</label>
					<div class="col-lg-6">
						<input type="password" class="form-control"
							name="pwdPassword" value="" placeholder="Create Password"
							maxlength="30" oninput="space(this);" /> <font color="red">${password} </font>
					</div>
				</div>
				<br />
				<div class="row">
				  	<label class="col-sm-2"> <font size="+1">Contact No 1 :</font> </label>
					<div class="col-lg-6">
						<input type="text" class="form-control" name="txtContactNo1"
							placeholder="Contact number" maxlength="15" value="${txtContactNo1}" oninput="space(this);onlydigit(this);"/> 
						<font color="red">${contact1}</font>
					</div>
				</div>
				<br />
				<div class="row">
				  	<label class="col-sm-2"> <font size="+1">Contact No 2 :</font> </label>
					<div class="col-lg-6">
						<input type="text" class="form-control" name="txtContactNo2"
							placeholder="Contact number" maxlength="15" value="${txtContactNo2}" oninput="space(this);onlydigit(this);"/> 
						<font color="red">${contact2}</font>
					</div>
				</div>
				<br /> 
				<br /> 
					<label class="col-sm-2 control-label"></label>
					<input type="reset" value="Reset" name="reset" class="btn  btn-danger">
						&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<input type="Submit" value="Register" name="submit" class="btn btn-success">
			</form>
		</div>
	</div>
</div>
</body>
</html>