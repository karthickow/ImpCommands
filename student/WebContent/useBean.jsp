<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UseBean Example</title>
</head>
<body>
	<jsp:useBean id="date" class="java.util.Date" />
	<p>The date/time is <%= date %></p>
	
	<jsp:useBean id="students" class="com.student.bean.StudentsBean">
		<jsp:setProperty name="students" property="firstName" value="Karthick"/>
		<jsp:setProperty name="students" property="lastName" value="Krishnan"/>
		<jsp:setProperty name="students" property="age" value="29"/>
	</jsp:useBean>
	
	<p> First Name :
		<jsp:getProperty property="firstName" name="students"/>
	</p>
	
	<p> Last Name :
		<jsp:getProperty property="lastName" name="students"/>
	</p>
	
	<p> Age :
		<jsp:getProperty property="age" name="students"/>
	</p>
	
	<p>${header["user-agent"]}</p>
</body>
</html>