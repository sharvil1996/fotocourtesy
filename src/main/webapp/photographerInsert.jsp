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
<title>Admin | Photographer Insert</title>
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
			Photographer <small>Insert</small>
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
				<form action="PhotographerInsertServlet" method="post">
					<br />
					<div class="row ">
						<label class="col-sm-2"> <font size="+1">Full Name
								:</font>
						</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="txtFirstName"
								maxlength="20" placeholder="First Name" value="${txtFirstName}"
								oninput="space(this);onlytext(this); nodigit(this);" /> <font
								color="red">${firstName} </font>
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="txtLastName"
								maxlength="20" placeholder="Last Name" value="${txtLastName}"
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
								value="${txtEmailId}" /> <font color="red">${emailId} </font>
						</div>
					</div>
					<br />
					
					<div class="row">
						<label class="col-sm-2"> <font size="+1">User Name
								:</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtUsername"
								value="${txtUsername}" placeholder="Create Username" maxlength="20"
								oninput="space(this);" /> <font color="red">${username}
							</font>
						</div>
					</div>
					<br />
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Contact No :</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtContactNo1"
								placeholder="Contact number" maxlength="15"
								value="${txtContactNo1}" oninput="space(this);onlydigit(this);" />
							<font color="red">${contact1}</font>
						</div>
					</div>
					<br />
					
					
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Address :</font>
						</label>
						<div class="col-lg-6">
							<textarea rows="3" cols="89" maxlength="255" name="txtAddress">${txtAddress}</textarea>
							<font color="red">${address}</font>
						</div>
					</div>
					<br />
					
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Select
								Country : </font>
						</label>
						<div class="col-lg-6">
							<select onChange="chngOne()" name="selCountryName"
								class="form-control" id="selCountryName">
								<option value="0">--Select Country--</option>
								<%
									ArrayList<CountryBean> list = (ArrayList<CountryBean>) new CountryDAO().getList();
									for (CountryBean bean : list) {
								%>
								<option value="<%=bean.getCountryName()%>"><%=bean.getCountryName()%>
								</option>
								<%
									}
								%>
							</select>
						</div>
					</div>
					<br />
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Select
								City : </font>
						</label>
						<div class="col-lg-6">
							<select data-placeholder="Select a City" class="form-control"
								name="cmbCity" id="cmbCity">
								<option value="0">---select City---</option>
							</select>${cityId}
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
</body>
</html>