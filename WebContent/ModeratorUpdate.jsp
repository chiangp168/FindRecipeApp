<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update a Moderator</title>
</head>
<body>
	<h1>Update Moderator</h1>
	<form action="moderatorupdate" method="post">
		<p>
			<label for="userId">User Id: </label>
			<input id="userId" name="userId" value="${fn:escapeXml(param.userId)}">
		</p>
		<p>
			<label for="noAddRecipesDelete">Number of recipe to add: </label>
			<input id="noAddRecipesDelete" name="noAddRecipesDelete" value="">
		</p>
 		<p>
			<label for="noSubRecipesDelete">Number of recipe to subtract: </label>
			<input id="noSubRecipesDelete" name="noSubRecipesDelete" value="">
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