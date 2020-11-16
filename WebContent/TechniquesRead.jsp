<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Technique</title>
</head>
<body>
	<form action="techniquesread" method="post">
		<h1>Search for technique by Recipe Id</h1>
		<p>
			<label for="recipeId">FirstName</label>
			<input id="recipeId" name="recipeId" value="${fn:escapeXml(param.recipeId)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="techniquesCreate"><a href="techniquescreate">Create Technique</a></div>
	<br/>
	<h1>Matching Techniques</h1>
        <table border="1">
            <tr>
                <th>Technique Id</th>
                <th>Recipe Id</th>
                <th>Technique</th>
            </tr>
            <c:forEach items="${techniques}" var="technique" >
                <tr>
                    <td><c:out value="${technique.getTechniqueId()}" /></td>
                    <td><c:out value="${technique.getRecipe().getRecipeId}" /></td>
                    <td><c:out value="${technique.getDescription}" /></td>
                    <td><a href="techniquesdelete?techniqueid=<c:out value="${technique.getTechniqueId()}"/>">Delete</a></td>
                    <td><a href="ingredidentsupdate?techniqueid=<c:out value="${technique.getTechniqueId()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
