<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
	<div align="center">
		<h1>User List</h1>
		<table border="1">
			<th>No</th>
			<th>Username</th>
			<th>Email</th>
			<th>Edit</th>
			<th>Delete</th>

			<c:forEach var="user" items="${userList}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${user.username}</td>
					<td>${user.email}</td>
					<td><a href="../user/edit/?id=${user.id}">edit</a></td>
					<td><a href="../user/delete?id=${user.id}">delete</a></td>
				</tr>
			</c:forEach>
			
		</table>
		<a href="../user/add">Add User</a>		
	</div>
</body>
</html>
