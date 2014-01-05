<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="hello" uri="WEB-INF/hello.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>A sample custom hello tag</title>
</head>
<body>
	<%-- <hello:Hello/> --%>
	<%-- <hello:Hello>
		This is message body!!!.
	</hello:Hello> --%>
	
	<hello:Hello message="This is a custom tag!!!"/>
</body>
</html>