<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Find Recipes By Tag</title>
</head>
<body>
<form action="findrecipesbytag" method="post">
    <h1>Search for Recipes by Tag</h1>
    <p>
        <input type="radio" id="vegetarian" name="tag" value="vegetarian" checked>
        <label for="vegetarian">Vegetarian</label><br>
        <input type="radio" id="low-fat" name="tag" value="low-fat">
        <label for="low-fat">Low Fat</label><br>
        <input type="radio" id="low-sodium" name="tag" value="low-sodium">
        <label for="low-sodium">Low Sodium</label><br>
        <input type="radio" id="low-carb" name="tag" value="low-carb">
        <label for="low-carb">Low Carb</label><br>
        <input type="radio" id="low-calorie" name="tag" value="low-calorie">
        <label for="low-calorie">Low Calorie</label><br>
    </p>
    <p>
        <input type="submit">
        <br/><br/><br/>
        <span id="successMessage"><b>${messages.success}</b></span>
    </p>
</form>
<h1>Matching Recipes</h1>
<table border="1">
    <tr>
        <th>RecipeName</th>
        <th>UserName</th>
        <th>Time To Cook</th>
        <th>Number Of Steps</th>
    </tr>
    <c:forEach items="${recipes}" var="recipe" >
        <tr>
            <td><c:out value="${recipe.getRecipeName()}" /></td>
            <td><c:out value="${recipe.getUser().getUserName()}" /></td>
            <td><c:out value="${recipe.getTimeToCook()}" /></td>
            <td><c:out value="${recipe.getNumOfStep()}" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>