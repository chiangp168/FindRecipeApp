<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Nutrition Fact</title>
</head>
<body>
	<form action="findnutritionfacts" method="post">
		<h1>Search for a nutrition fact by percentage of daily recommended amount of protein</h1>
		<p>
			<label for=protein>Protein in percentage of daily recommended amount: </label>
			<input id="protein" name="protein" value="${fn:escapeXml(param.protein)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="nutritionCreate"><a href="nutritioncreate">Create a NutritionFact</a></div>
	<br/>
	<h1>Matching Nutrition Facts</h1>
        <table border="1">
            <tr>
                <th>NutritionFactsId</th>
                <th>Calories</th>
                <th>Total Fat</th>
                <th>Sugar</th>
                <th>Sodium </th>
                <th>Protein	</th>
                <th>Saturated Fat</th>
                <th>Carb</th>
                <th>RecipeId</th>
                <th>Update Nutrition Fact</th>
                <th>Delete Nutrition Fact</th>
                
         	</tr>
           	<c:forEach items="${nutritionFacts}" var="nutritionFact" >
                <tr>
                    <td><c:out value="${nutritionFact.getNutritionFactsId()}" /></td>
                    <td><c:out value="${nutritionFact.getCalories()}" /></td>
                    <td><c:out value="${nutritionFact.getTotalFat()}" /></td>
                    <td><c:out value="${nutritionFact.getSugar()}" /></td>
                    <td><c:out value="${nutritionFact.getSodium()}" /></td>
                    <td><c:out value="${nutritionFact.getProtein()}" /></td>
                    <td><c:out value="${nutritionFact.getSaturatedFat()}" /></td>
                    <td><c:out value="${nutritionFact.getCarb()}" /></td>
               		<td><c:out value="${nutritionFact.getRecipe().getRecipeId()}" /></td>
                    <td><a href="nutritionupdate?nutritionFactId<c:out value="${nutritionFact.getNutritionFactsId()}"/>">Update</a></td>
                    <td><a href="nutritiondelete?nutritionFactId=<c:out value="${nutritionFact.getNutritionFactsId()}"/>">Delete</a></td>
                </tr>
             </c:forEach>
       </table>
</body>
</html>
