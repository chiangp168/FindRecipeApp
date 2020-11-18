<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Nutrition Fact</title>
</head>
<body>
	<h1>Create a Nutrition Fact</h1>
	<form action="nutritioncreate" method="post">
		<p>
			<label for="nutritionFactsId">NutritionFact Id: </label>
			<input id="nutritionFactsId" name="nutritionFactsId" value="">
		</p>
		<p>
			<label for="calories">Calories: </label>
			<input id="calories" name="calories" value="">
		</p>
		<p>
			<label for="totalFat">Total Fat:  </label>
			<input id="totalFat" name="totalFat" value="">
		</p>
		<p>
			<label for="sugar">Sugar: </label>
			<input id="sugar" name="sugar" value="">
		</p>
		<p>
			<label for="sodium">Sodium: </label>
			<input id="sodium" name="sodium" value="">
		</p>
		<p>
			<label for="protein">Protein: </label>
			<input id="protein" name="protein" value="">
		</p>
		<p>
			<label for="saturatedFat">Saturated fat: </label>
			<input id="saturatedFat" name="saturatedFat" value="">
		</p>
		<p>
			<label for="carb">Carb: </label>
			<input id="carb" name="carb" value="">
		</p>
		<p>
			<label for="recipeId">Recipe Id:  </label>
			<input id="recipeId" name="recipeId" value="">
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

