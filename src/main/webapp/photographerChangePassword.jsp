<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="PhotographersChangePasswordServlet" method="post">
		<table>
			<tr>
				<td>Enter Old Password</td>
				<td><input type="password" name="pwdOldPassword">${oldPassword}</td>
			</tr>
			<tr>
				<td>Enter New Password</td>
				<td><input type="password" name="pwdNewPassword">${newPassword}</td>
			</tr>
			<tr>
				<td>Enter Confirm Password</td>
				<td><input type="password" name="pwdConfirmPassword">${confirmPassword}</td>
			</tr>
			<tr>
				<td colspan="2"><center>
						<input type="submit" value="Submit">
					</center></td>
			</tr>
		</table>
		</div>
	</form>
</body>
</html>