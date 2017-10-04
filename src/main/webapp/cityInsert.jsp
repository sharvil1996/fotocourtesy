<%@page import="com.bean.CountryBean"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.CountryDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | City Insert</title>
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
			City <small>Insert</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">City</li>
		</ol>
		</section>
		<br> <br>
		<div class="col-lg-6">
			<div class="container">
				<form action="CityInsertServlet" method="post">
					<br />
					<div class="row">
						<label class="col-sm-2"> <font size="+1">City Name
								:</font>
						</label>

						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtCityName"
								placeholder="Ahmedabad" maxlength="50" oninput="nodigit(this);"
								value="${txtCityName}" /> <font color="red">${cityName}
							</font>
						</div>
					</div>
					<br />
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Select
								Country</font>
						</label>
						<div class="col-lg-6">
							<select name="selCountryName" class="form-control">
								<option value="0" selected="selected">Select Country</option>
								<%
									List<CountryBean> countryList = new CountryDAO().getList();
									for (int i = 0; i < countryList.size(); i++) {

										String tmp = "unselected";
										String type = request.getParameter("selCountryName");
										if (type == null || type.equals("0"))
											tmp = "unselected";
										else if (countryList.get(i).getCountryName().equals(type))
											tmp = "selected";
								%>

								<option value=<%=countryList.get(i).getCountryName()%> <%=tmp%>>
									<%=countryList.get(i).getCountryName()%></option>
								<%
									}
								%>
							</select><font color="red">${countryName}</font>
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
