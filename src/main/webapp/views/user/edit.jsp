<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="context"><%=getServletContext().getContextPath()%></c:set>
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
${context}
		<c:url var="formAction" value="/do/user/save"></c:url>
		<form:form action="${formAction}" commandName="user" method="POST">
			<table>
				<tr>
						<td><form:label path="id">
								<spring:message text="ID" />
							</form:label></td>
						<td><form:input path="id" readonly="true" size="8"
								disabled="true" /><form:hidden path="id" /></td>
				</tr>
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
					<td><form:input path="password" type="password"/></td>
					<td><form:errors path="password" cssClass="error" /></td>
				</tr>
				<tr>
                	<td><form:label path="confirmPassword">
                		<spring:message text="Confirm Password" />
                    	</form:label></td>
                		<td><form:input path="confirmPassword" type="password"/></td>
                		<td><form:errors path="confirmPassword" cssClass="error" /></td>
                </tr>
				<tr>
					<td><form:label path="email">
							<spring:message text="Email" />
						</form:label></td>
					<td><form:input path="email" type="email"/></td>
					<td><form:errors path="email" cssClass="error" /></td>
				</tr>
				<tr>
					<td>
							<input type="submit" value="<spring:message text="Save"/>" />
						</td>
						<td>
							<button type="button" onclick="window.location='${context}/do/user/list'"><spring:message text="Cancel"/></button>
						</td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>
