<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TestSecurity - Access Denied</title>
</head>
<body>
	<h1>Access Denied</h1>
	<hr />
	<p>You are not authorized to access this resource.</p>
	<hr />
	<a href="${pageContext.request.contextPath}/">Back to home page</a>
</body>
</html>