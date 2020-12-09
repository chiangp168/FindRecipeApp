<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<link href="./css/search-results.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Find Recipes By Tag</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="index.jsp">WFH Kitchen</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp">Recipes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="FindRecipesByTag.jsp">Dietary Restriction</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="CommentFind.jsp">Comments</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="RatingFind.jsp">Ratings</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="FavoriteFind.jsp">Favorites</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="FindNutritionFacts.jsp">NutritionFacts</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="TechniquesRead.jsp">Techniques</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="IngredientsSearch.jsp">Ingredient Search</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="FindUser.jsp">User</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="FindPerson.jsp">Person</a>
                </li>
            </ul>

        </div>
    </nav>
    
    <div class="well search-result">
		<div class="search-above-fold">
			<h1>Search for recipes by restriction</h1>
		  	<form action="findrecipesbytag" method="post">
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
		      <button class="btn btn-dark my-2 my-sm-0 button-search" type="submit">Search</button>
		      <span id="successMessage"><b>${messages.success}</b></span>
	    	</form>
		</div>
	</div>
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
</form> --%>
	<c:forEach items="${recipes}" var="recipe" >
	  <div class="well search-result">
        <div class="row">
            <div class="col-lg-4">
              <img class="img-responsive" src="https://source.unsplash.com/400x200/?healthyfood" alt=""/>
            </div>
            <div class=" col-lg-8 title">
              <h6>Recipe Name: <c:out value="${recipe.getRecipeName()}" /></h6>
              <p>Created by: 
                <c:out value="${recipe.getUser().getUserName()}" />
              </p>
              <p>Time to cook: 
                <c:out value="${recipe.getTimeToCook()}" />
              </p>
              <p>Number of step: 
                <c:out value="${recipe.getNumOfStep()}" />
              </p>
              
			<a class="btn btn-info" href="recipecreate">CREATE</a>
		    <a class="btn btn-info" href="recipeupdate?recipename=<c:out value="${recipe.getRecipeName()}"/>">UPDATE</a>
		    <a class="btn btn-info" href="recipedelete?recipename=<c:out value="${recipe.getRecipeName()}"/>">DELETE</a>
		                
            </div>
        </div>
      </div>
	</c:forEach>

</body>
</html>