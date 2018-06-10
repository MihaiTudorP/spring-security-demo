<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TestSecurity - leaders' view</title>
</head>
<body>
	<h1>TestSecurity - leaders' home page</h1>
	<hr />
	<p>
		See you in Brazil ... for our annual Leadership retreat!<br> Keep
		this trip a secret, don't tell the regular employees :D
	</p>
	<hr />
	<a href="${pageContext.request.contextPath}/">Back to home page</a>
	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">
		<input type="submit" value="Logout" />
	</form:form>
</body>
</html>