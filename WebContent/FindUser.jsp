<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a User Using UserId</title>
</head>
<body>
	<form action="finduser" method="post">
		<h1>Search for a User by UserId</h1>
		<p>
			<label for="userId">UserId</label>
			<input id="userId" name="userId" value="${fn:escapeXml(param.userId)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="UserCreate"><a href="usercreate">Create Person</a></div>
	<br/>
	<h1>Matching User</h1>
        <table border="1">
            <tr>
                <th>UserId</th>
                <th>UserName</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Delete User</th>
                <th>Update User</th>
            </tr>
            <tr>
                <td><c:out value="${user.getUserId()}" /></td>
                <td><c:out value="${user.getUserName()}" /></td>
                <td><c:out value="${user.getFirstName()}" /></td>
                <td><c:out value="${user.getLastName()}" /></td>
                <td><a href="userdelete?userId=<c:out value="${user.getUserId()}"/>">Delete</a></td>
                <td><a href="userupdate?userId=<c:out value="${user.getUserId()}"/>">Update</a></td>
            </tr>
       </table>
</body>
</html>