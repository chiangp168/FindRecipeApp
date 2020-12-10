<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="RecipeApplication1/WebContent/WEB-INF/resource/font-awesome.min.css">
<!-- <link href="./css/index.css" rel="stylesheet" type="text/css">-->
<link href="./css/search-results.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>High Protein Diet</title>
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
                    <a class="nav-link" href="FilterNumOfStep.jsp">Easy Recipes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="FindRecipesByTag.jsp">Dietary Restriction</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="CommentFind.jsp">Comments</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="FilterByRatings.jsp">Ratings</a>
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
			<h1>Enter protein amount</h1>
		  	<form action="findnutritionfacts" method="post">
		      <input class="form-control mr-sm-2" type="search" name="protein" value="${fn:escapeXml(param.protein)}" placeholder="Search for recipe by protein amount" aria-label="Search">
		      <button class="btn btn-dark my-2 my-sm-0 button-search" type="submit">Search</button>
		      <span id="successMessage"><b>${messages.success}</b></span>
	    	</form>
		</div>
	</div>
	
	<c:forEach items="${nutritionFacts}" var="nutritionFact">
	  <div class="well search-result">
        <div class="row">
            <div class="col-lg-4">
              <img class="img-responsive" src="https://source.unsplash.com/400x200/?food" alt=""/>
            </div>
            <div class=" col-lg-8 title">
              <h6><c:out value="${nutritionFact.getRecipe().getRecipeName()}"/></h6>
              <p>Fat Content:
                <c:out value="${nutritionFact.getTotalFat()}" />
              </p>
              <p>Protein Content
                <c:out value="${nutritionFact.getProtein()}" />
              </p>
              <p>Carb Content
                <c:out value="${nutritionFact.getCarb()}" />
              </p>
              <p>Sugar Content
                <c:out value="${nutritionFact.getSugar()}" />
              </p>
              
            
              <a class="btn btn-info" href="nutritioncreate">CREATE</a>
		      <a class="btn btn-info" href="nutritionupdate?nutritionFactsId=<c:out value="${nutritionFact.getNutritionFactsId()}"/>">UPDATE</a>
		      <a class="btn btn-info" href="nutritiondelete?nutritionFactsId=<c:out value="${nutritionFact.getNutritionFactsId()}"/>">DELETE</a>
		      
            </div>
        </div>
      </div>
	</c:forEach>
</body>
</html>
