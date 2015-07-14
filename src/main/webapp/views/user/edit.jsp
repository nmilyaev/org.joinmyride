<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
.error {
    color: #ff0000;
    font-style: italic;
    font-weight: bold;
}
</style>
<title>editing user: ${user}</title>
</head>
<body>
	<div align="center">
		<h1>Edit User</h1>

		<c:url var="formAction" value="/do/user/save"></c:url>
		<form:form action="${formAction}" commandName="user" method="POST">
			<table>
				<c:if test="${!empty user.username}">
					<tr>
						<td><form:label path="id">
								<spring:message text="ID" />
							</form:label></td>
						<td><form:input path="id" readonly="true" size="8"
								disabled="true" /> <form:hidden path="id" /></td>
					</tr>
				</c:if>
				<tr>
					<td><form:label path="username">
							<spring:message text="User Name" />
						</form:label></td>
					<td><form:input path="username" /></td>
					<td><form:errors path="username" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="password">
							<spring:message text="User Password" />
						</form:label></td>
					<td><form:input path="password" /></td>
				</tr>
				<tr>
					<td><form:label path="email">
							<spring:message text="Email" />
						</form:label></td>
					<td><form:input path="email" /></td>
				</tr>
				<tr>
					<td colspan="2"><c:if test="${!empty user.username}">
							<input type="submit" value="<spring:message text="Edit User"/>" />
						</c:if> <c:if test="${empty user.username}">
							<input type="cancel" value="<spring:message text="Cancel"/>" />
						</c:if></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>
