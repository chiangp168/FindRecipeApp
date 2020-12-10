<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Delete a NutritionFact</title>
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
                    <a class="nav-link" href="FilterPrepTime.jsp">Fast & Delicious</a>
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
<div class='container'>
    <div class="row">
        <div class="col-lg-12">
            <h3 class="text-dark">Delete a Nutrition Fact</h3>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <div class="job-detail mt-2 p-4">
                <div class="custom-form">
                <h1>${messages.title}</h1>
                    <form action="nutritiondelete" method="post">
                        <div class='row'>
                            <div class="col-md-6">
                                <div class="form-group app-label">
                                <div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
                                    <label for="nutritionFactsId" class="text-muted">Please enter the Nutrition Fact Id to delete</label>
                                    <input id="nutritionFactsId" type="text" class="form-control resume" name="nutritionFactsId" value="${fn:escapeXml(param.nutritionFactsId)}">
                                </div>
                            </div>
                        </div>
                        <p>
                            <span id="submitButton" <c:if test="${messages.disableSubmit}">
                            style="display:none"</c:if>>
							<input type="submit">
							</span>
                        </p>
                    </form>
                </div>
              
            </div>
        </div>
    </div>
</div>

</body>
</html>