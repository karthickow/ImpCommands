<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SELECT Operation Using JSTL SQL tag</title>
</head>
<body>
	<sql:setDataSource var="student" driver="oracle.jdbc.OracleDriver" url="jdbc:oracle:thin:@localhost:1521:xe" user="student" password="student"/>
	<%-- <sql:update dataSource="${student}" var="result">
		INSERT INTO Employees (firstname, lastname, age) VALUES ('Krishnan', 'Appasamy', 68)
	</sql:update> --%>
	
	<%-- <c:set var="empid" value="5"/> --%>
	
	<%-- <sql:update dataSource="${student}" var="result">
		DELETE FROM Employees WHERE empid=?
		<sql:param value="${empid}"/>
	</sql:update> --%>

	<%-- <sql:update dataSource="${student}" var="count">
  		UPDATE Employees SET age = 23 where empid = ?
  		<sql:param value="${empid}" />
	</sql:update> --%>

	<sql:query var="result" dataSource="${student}">
		select * from employees
	</sql:query>
	
	<table border="1">
		<tr>
			<th>Emp Id</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Age</th>
		</tr>
		<c:forEach var="row" items="${result.rows}">
		<tr>
			<td><c:out value="${row.empid}"/></td>
			<td><c:out value="${row.firstname}"/></td>
			<td><c:out value="${row.lastname}"/></td>
			<td><c:out value="${row.age}"/></td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>