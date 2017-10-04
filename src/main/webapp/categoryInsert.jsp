<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Category Insert</title>
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
		Category <small>Insert</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
				Home</a></li>
		<li class="active">Category</li>
	</ol>
	</section>
	<br> <br>
	<div class="col-lg-6">
		<div class="container">
			<form action="CategoryInsertServlet" method="post">
				<br />
				<div class="row">
					<label class="col-sm-2"> <font size="+1">Category Name :</font> </label>
					<div class="col-lg-6">
						<input type="text" class="form-control" name="txtCategory"
							placeholder="Nature" maxlength="50"
							value="${txtCategory}" /> <font color="red">${category}
						</font>
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



