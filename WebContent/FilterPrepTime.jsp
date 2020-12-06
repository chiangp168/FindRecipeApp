<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Filter Recipes by Cook Time</title>
</head>
<body>
	<form action="filterPrepTime" method="post">
		<h1>Search Recipes By Preparation Time</h1>
		<p>
			<label for="prepTime">Enter Your Desire Preparation Time</label>
			<input id="prepTime" name="prepTime" value="${fn:escapeXml(param.prepTime)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="numOfStep"><a href="filterNumOfStep">Search By Number Of Steps</a></div>
	<br/>
	<h1>Matching Recipes</h1>
        <table border="1">
            <tr>
                <th>RecipeName</th>
                <th>UserName</th>
                <th>Time To Cook</th>
                <th>Number Of Steps</th>
                <th>Delete Recipe</th>
                <th>Update Recipe</th>
            </tr>
            <c:forEach items="${recipes}" var="recipe" >
                <tr>
                    <td><c:out value="${recipe.getRecipeName()}" /></td>
                    <td><c:out value="${recipe.getUser().getUserName()}" /></td>
                    <td><c:out value="${recipe.getTimeToCook()}" /></td>
                    <td><c:out value="${recipe.getNumOfStep()}" /></td>
                    <td><a href="recipedelete?recipename=<c:out value="${recipe.getRecipeName()}"/>">Delete</a></td>
                    <td><a href="recipeupdate?recipename=<c:out value="${recipe.getRecipeName()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>