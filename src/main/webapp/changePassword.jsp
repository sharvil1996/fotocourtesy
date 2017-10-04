<%@page import="com.dao.ForgotPasswordDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password | BookBerries</title>
</head>
<body
	style="background-image: url(res/imgs/defback.png); background-size: cover;">
	<%
		String forgotPasswordId = request.getParameter("forgotPasswordId");

		String customerId = request.getParameter("customerId");

		if (forgotPasswordId.length() == 30 && customerId.length() == 15
				&& new ForgotPasswordDAO().checkAuthentication(forgotPasswordId, customerId)) {
	%>
	<form action="ResetPasswordServlet">
		<input type="hidden" name="forgotPasswordId"
			value="<%=forgotPasswordId%>"> <input type="hidden"
			name="customerId" value="<%=customerId%>"> <input type="text"
			name="txtPassword">
		<button type="submit" name="submit" value="submit">Submit</button>
	</form>

	<%
		}
	%>
</body>
</html>