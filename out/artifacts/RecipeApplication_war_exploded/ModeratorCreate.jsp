<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Moderator</title>
</head>
<body>
	<h1>Create a moderator</h1>
	<form action="moderatorcreate" method="post">
		<p>
			<label for="userId">User Id: </label>
			<input id="userId" name="userId" value="">
		</p>
		<p>
			<label for="userName">Username: </label>
			<input id="userName" name="userName" value="">
		</p>
		<p>
			<label for="firstName">First Name: </label>
			<input id="firstName" name="firstName" value="">
		</p>
		<p>
			<label for="lastname">Last Name: </label>
			<input id="lastname" name="lastname" value="">
		</p>
		<p>
			<label for="email">Email: </label>
			<input id="email" name="email" value="">
		</p>
		<p>
			<label for="phone">Phone: </label>
			<input id="phone" name="phone" value="">
		</p>
		<p>
			<label for="numOfRecipesDeleted">Number of recipes deleted:  </label>
			<input id="numOfRecipesDeleted" name="numOfRecipesDeleted" value="">
		</p>
		<p>
			<label for="numOfRecipesApproved">Number of recipes approved:  </label>
			<input id="numOfRecipesApproved" name="numOfRecipesApproved" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>

