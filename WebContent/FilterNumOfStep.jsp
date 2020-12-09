<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="./css/search-results.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Filter by Number of steps</title>
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
			<h1>Filter by number of steps</h1>
		  	<form action="filterNumOfStep" method="post">
		      <input class="form-control mr-sm-2" type="search" name="numofstep" value="${fn:escapeXml(param.numofstep)}" placeholder="Enter maximum step" aria-label="Search">
		      <button class="btn btn-dark my-2 my-sm-0 button-search" type="submit">Search</button>
		      <span id="successMessage"><b>${messages.success}</b></span>
	    	</form>
		</div>
	</div>
	<c:forEach items="${recipes}" var="recipe" >
	  <div class="well search-result">
        <div class="row">
            <div class="col-lg-4">
              <img class="img-responsive" src="https://source.unsplash.com/400x200/?food" alt=""/>
            </div>
            <div class=" col-lg-8 title">
              <h6><c:out value="${recipe.getRecipeName()}" /></h6>
              <p>Username: 
                <c:out value="${recipe.getUser().getUserName()}" />
              </p>
              <p>Time to cook: 
                <c:out value="${recipe.getTimeToCook()}" />
              </p>
              <p>Carb Content: 
                <c:out value="${nutritionFact.getCarb()}" />
              </p>
              <p>Number of steps: 
                <c:out value="${recipe.getNumOfStep()}" />
              </p>
              
            
              <a class="btn btn-info" href="recipecreate">CREATE</a>
		      <a class="btn btn-info" href="nutritionupdate?nutritionFactsId=<c:out value="${nutritionFact.getNutritionFactsId()}"/>">UPDATE</a>
		      <a class="btn btn-info" href="nutritiondelete?nutritionFactsId=<c:out value="${nutritionFact.getNutritionFactsId()}"/>">DELETE</a>
		      
            </div>
        </div>
      </div>
	</c:forEach>

</body>
</html>
