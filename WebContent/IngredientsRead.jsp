<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="RecipeApplication1/WebContent/WEB-INF/resource/font-awesome.min.css">
    <link href="./css/index.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Find an Ingredient</title>
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

    <div class="row">
        <div class="col-lg-12">
            <div class="job-detail mt-2 p-4">
                <div class="custom-form">
                    <form action="ingredientsread" method="post">
                        <div class='row'>
                            <div class="col-md-6">
                                <div class="form-group app-label">

                                    <p>
                                        <label for="recipeId" class="text-muted">Recipe Id</label>
                                        <input id="recipeId" name="recipeId" value="${fn:escapeXml(param.recipeId)}"
                                            class="form-control resume">
                                    </p>
                                </div>
                            </div>
                        </div>
                </div>
                <button class="btn btn-dark my-2 my-sm-0 button-search" type="submit">Search</button>
                <br>
                <a class="btn btn-info" href="ingredientscreate">CREATE</a>
                </form>

            </div>
            <p>
                <span id="successMessage"><b>${messages.success}</b></span>
            </p>
        </div>
    </div>
    </div>
    </div>

    <c:forEach items="${ingredients}" var="ingredient">
        <div class="well search-result">
            <div class="row">
                <div class="col-lg-4">
                    <img class="img-responsive" src="https://source.unsplash.com/400x200/?healthyfood" alt="" />
                </div>
                <div class=" col-lg-8 title">
                    <h6>
                        <c:out value="${ingredient.getIngredient()}" />
                    </h6>

                    <p>Ingredient Id:
                        <c:out value="${ingredient.getIngredientId()}" />
                    </p>
                    <p>Recipe Id:
                        <c:out value="${ingredient.getRecipe().getRecipeId()}" />
                    </p>

                    <a class="btn btn-info" href="ingredientsupdate?ingredientId=<c:out value="
                        ${ingredient.getIngredientId()}" />">UPDATE</a>
                    <a class="btn btn-info" href="ingredientsdelete?ingredientId=<c:out value="
                        ${ingredient.getIngredientId()}" />">DELETE</a>

                </div>
            </div>
        </div>
    </c:forEach>

</body>

</html>