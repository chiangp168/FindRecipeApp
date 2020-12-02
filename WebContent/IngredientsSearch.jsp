<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Recipe using Ingredient</title>
</head>
<body>
	<form action="ingredientsearch" method="post">
		<h1>Search for a Recipe by Ingredients</h1>
		<h3>Find available recipes to cook with ingredients in your kitchen!</h3>
		<div class="ingredient_form">
			<p>
			<label for="ingredient1">Ingredient Name</label>
			<input id="ingredient1" name="ingredient1">
			</p>
		</div>
		<input type="hidden" id="num_ingredient" name="num_ingredient" value="1">
		 <button id="add_field">Add More Ingredient
   		 </button>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$( document ).ready(function() {
	$("#num_ingredient").val(1);
	var add_button = $("#add_field");
	var form_container = $(".ingredient_form");
	$(add_button).click(function(e) {
	    e.preventDefault();
	    var num_ingredients = parseInt($("#num_ingredient").val());
	    num_ingredients++;
	    var new_label = "ingredient" + num_ingredients;
	    $(form_container).append("<p><label for='" + new_label + "'>Ingredient Name</label>"
				+ "<input id='"+ new_label + "' name='"+ new_label +"'></p>");
	    $("#num_ingredient").val(num_ingredients);
	   
	});
});

</script> 
</html>
