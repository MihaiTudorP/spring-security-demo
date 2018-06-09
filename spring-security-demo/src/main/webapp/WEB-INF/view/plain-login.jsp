<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SpringSecurityDemo</title>
</head>
<body>
<h1>SpringSecurityDemo - Login page</h1>
<form:form action="${pageContext.request.contextPath}/authenticateUser" method="POST">
	<!-- Check for login error -->
	<c:if test="${param.error != null}">
		<i>Sorry! You entered an invalid username/password.</i>
	</c:if>
	<!-- Login credential fields -->
	<p>User name: <input type="text" name="username"></p>
	<p>Password: <input type="password" name="password"></p>
	<p><input type="submit" name="login" value="Login"></p>
</form:form>
</body>
</html>