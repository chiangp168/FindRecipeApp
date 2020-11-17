<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find an Ingredient</title>
</head>
<body>
	<form action="ingredientsread" method="post">
		<h1>Search for ingredient by Recipe Id</h1>
		<p>
			<label for="recipeId">Recipe Id</label>
			<input id="recipeId" name="recipeId" value="${fn:escapeXml(param.recipeId)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="ingredientsCreate"><a href="ingredientscreate">Create Ingredient</a></div>
	<br/>
	<h1>Matching Ingredients</h1>
        <table border="1">
            <tr>
                <th>Ingredient Id</th>
                <th>Recipe Id</th>
                <th>Ingredient</th>
            </tr>
            <c:forEach items="${ingredients}" var="ingredient" >
                <tr>
                    <td><c:out value="${ingredient.getIngredientId()}" /></td>
                    <td><c:out value="${ingredient.getRecipe().getRecipeId()}" /></td>
                    <td><c:out value="${ingredient.getIngredient()}" /></td>
                    <td><a href="ingredientsdelete?ingredientId=<c:out value="${ingredient.getIngredientId()}"/>">Delete</a></td>
                    <td><a href="ingredientsupdate?ingredientId=<c:out value="${ingredient.getIngredientId()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
