<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error Page</title>
</head>
<%-- <body>
	<h1>Oops...</h1>
	<p>Sorry, an error occured.</p>
	<p>Here is the exception stack trace : </p>
	<pre>
		<%exception.printStackTrace(response.getWriter()); %>
	</pre>
</body> --%>
	<body>
		<h1>Sorry, an error occurred. Please find the error details in the below table. </h1>
		<table border="1">
			<tr valign="top">
				<td><b>Error:</b></td>
				<td>${pageContext.exception}</td>
			</tr>
			<tr valign="top">
				<td><b>URI:</b></td>
				<td>${pageContext.errorData.requestURI}</td>
			</tr>
			<tr valign="top">
				<td><b>Status code:</b></td>
				<td>${pageContext.errorData.statusCode}</td>
			</tr>
			<tr valign="top">
				<td><b>Stack trace:</b></td>
				<td>
					<c:forEach var="trace" items="${pageContext.exception.stackTrace}">
						<p>${trace}</p>
					</c:forEach>
				</td>
			</tr>
		</table>
	</body>
</html>