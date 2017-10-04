<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Photographer Accept</title>
<noscript>
	<div style="color: #FF0000">place enable java script</div>
</noscript>
<link rel="icon" type="image/png" href="imgs/favicon.png" sizes="256x256">
</head>
<body>
	<%
		String photographerId = (String) request.getParameter("photographerId");
	%>
	<%@ include file="adminHeader.jsp"%>
	<div style="margin-top: -850px; margin-left: 250px;">
		<section class="content-header">
		<h1>
			Photographer <small>accept</small>
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
				<form action="PhotographerAcceptServlet" method="post">
					<input type="hidden" value="<%=photographerId%>"
						name="photographerId"> <br />
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Price :</font>
						</label>
						<div class="col-lg-6">
							<input type="text" name="txtPrice" class="form-control"
								value="${txtPrice}" oninput="space(this);"> <font
								color="red">${price} </font>
						</div>
					</div>
					<br />
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Start Date
								:</font>
						</label>
						<div class="col-lg-6">
							<input type="text" id="datepicker1" class="form-control"
								name="txtStartDate" value="${txtStartDate}" /> <font color="red">${startDate}
							</font>
						</div>
					</div>
					<br />
					<div class="row">
						<label class="col-sm-2"> <font size="+1">End Date
								:</font>
						</label>
						<div class="col-lg-6">
							<input type="text" id="datepicker2" class="form-control"
								name="txtEndDate" value="${txtEndDate}" /> <font color="red">${endDate}
							</font>
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
	<script type="text/javascript">
		var dates = $("#datepicker1").datepicker({
			minDate : "0",
			maxDate : "+2Y",
			defaultDate : "+1w",
			dateFormat : 'mm/dd/yy',
			numberOfMonths : 1,
			onSelect : function(date) {
				for (var i = 0; i < dates.length; ++i) {
					if (dates[i].id < this.id)
						$(dates[i]).datepicker('option', 'maxDate', date);
					else if (dates[i].id > this.id)
						$(dates[i]).datepicker('option', 'minDate', date);
				}
			}
		});
		var dates = $("#datepicker2").datepicker({
			minDate : "0",
			maxDate : "+2Y",
			defaultDate : "+1w",
			dateFormat : 'mm/dd/yy',
			numberOfMonths : 1,
			onSelect : function(date) {
				for (var i = 0; i < dates.length; ++i) {
					if (dates[i].id < this.id)
						$(dates[i]).datepicker('option', 'maxDate', date);
					else if (dates[i].id > this.id)
						$(dates[i]).datepicker('option', 'minDate', date);
				}
			}
		});
	</script>
</body>
</html>