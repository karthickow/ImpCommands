<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login Page</title>
	</head>
	<body>
		<h1>Please Login</h1>
		<form action="/loginServlet">
			<table align="right" border="0" cellspacing="10">
				<tr>
					<td>Username</td> 
					<td><input type="text" name="username" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input	type="password" name="password" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Submit"/>
					<input type="reset"/> </td>
				</tr>
				<tr>
					<td>
						<font color="red">
							<c:if test="${not empty param.errMsg}">
								<c:out value="${param.errMsg}" />
							</c:if>
						</font>
					</td>
				</tr>
			</table>				
		</form>
	</body>
</html>