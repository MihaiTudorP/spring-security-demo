<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SpringSecurityDemo</title>
</head>
<body>
<form:form action="${pageContext.request.contextPath}/authenticateUser" method="POST">
	<p>User name: <input type="text" name="username"></p>
	<p>Password: <input type="password" name="password"></p>
	<p><input type="submit" name="login" value="Login"></p>
</form:form>
</body>
</html>