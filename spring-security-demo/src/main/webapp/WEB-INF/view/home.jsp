<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TestSecurity Company Home Page</title>
</head>
<body>
	<h1>TestSecurity Company Home Page</h1>
	<hr />
	<p>
		Hello,
		<security:authentication property="principal.username" />
	<hr />
	<p>Welcome to the TestSecurity company home page!</p>
	<hr />
	<p>
		Your roles are:
		<security:authentication property="principal.authorities" />
	</p>
	<hr />
	
	<security:authorize access="hasRole('MANAGER')">
		<!-- Add link to point to /leaders/** for managers -->
		<p>
			<a href="${pageContext.request.contextPath}/leaders">Leadership
				meeting</a> (Only for management people)
		</p>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
		<!-- Add link to point to /systems/** for admins -->
		<p>
			<a href="${pageContext.request.contextPath}/systems">Admin
				meeting</a> (Only for admin people)
		</p>
	</security:authorize>
	<hr />
	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">
		<input type="submit" value="Logout" />
	</form:form>
</body>
</html>