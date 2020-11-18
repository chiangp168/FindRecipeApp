<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Moderator</title>
</head>
<body>
	<form action="findmoderators" method="post">
		<h1>Search for a Moderator by Number of Recipe Deleted</h1>
		<p>
			<label for=numOfRecipesDeleted>Number of Recipes Deleted: </label>
			<input id="numOfRecipesDeleted" name="numOfRecipesDeleted" value="${fn:escapeXml(param.numOfRecipesDeleted)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="userCreate"><a href="usercreate">Create a Moderator</a></div>
	<br/>
	<h1>Matching Moderators</h1>
        <table border="1">
            <tr>
                <th>UserId</th>
                <th>User name</th>
                <th>First name</th>
                <th>Last Name</th>
                <th>Email </th>
                <th>Phone</th>
                <th>Number Of Recipes Deleted</th>
                <th>Number Of Recipes Approved</th>
                <th>Update Moderator</th>
                <th>Delete Moderator</th>
                
         	</tr>
           	<c:forEach items="${moderators}" var="moderator" >
                <tr>
                    <td><c:out value="${moderator.getUserId()}" /></td>
                    <td><c:out value="${moderator.getUserName()}" /></td>
                    <td><c:out value="${moderator.getFirstName()}" /></td>
                    <td><c:out value="${moderator.getLastName()}" /></td>
                    <td><c:out value="${moderator.getEmail()}" /></td>
                    <td><c:out value="${moderator.getPhone()}" /></td>
                    <td><c:out value="${moderator.getNumOfRecipesDeleted()}" /></td>
                    <td><c:out value="${moderator.getNumOfRecipesApproved()}" /></td>
               
                    <td><a href="moderatordelete?userId=<c:out value="${moderator.getUserId()}"/>">Update</a></td>
                    <td><a href="moderatordelete?userId=<c:out value="${moderator.getUserId()}"/>">Delete</a></td>
                </tr>
             </c:forEach>
       </table>
</body>
</html>
