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
    <link href="./css/search-results.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Search Recipes by Ingredient</title>
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
    <div class="row">
        <div class="col-lg-12">
            <div class="job-detail mt-2 p-4">
                <div class="custom-form">
                    <form action="ingredientsearch" method="post">
                        <div class='row'>
                            <div class="col-md-6">
                                <div class="form-group app-label">

                                    <div class="ingredient_form">
                                        <p>
                                            <label for="ingredient1" class="text-muted">Ingredient Id</label>
                                            <input id="ingredient1" type="text" class="form-control resume"
                                                name="ingredient1">
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" id="num_ingredient" name="num_ingredient" value="1">
                        <button id="add_field" class="btn btn-dark my-2 my-sm-0">Add More Ingredient
                        </button>
                        <button class="btn btn-dark my-2 my-sm-0 button-search" type="submit">Search</button>
                    </form>
                </div>
                <p>
                    <span id="successMessage"><b>${messages.success}</b></span>
                </p>
            </div>
        </div>
    </div>
    </div>

    <c:forEach items="${recipes}" var="recipe">
        <div class="well search-result">
            <div class="row">
                <div class="col-lg-4">
                    <img class="img-responsive" src="https://source.unsplash.com/400x200/?healthyfood" alt="" />
                </div>
                <div class=" col-lg-8 title">
                    <h6>Recipe Name:
                        <c:out value="${recipe.getRecipeName()}" />
                    </h6>
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
                    <a class="btn btn-info" href="recipeupdate?recipename=<c:out value="
                        ${recipe.getRecipeName()}" />">UPDATE</a>
                    <a class="btn btn-info" href="recipedelete?recipename=<c:out value="
                        ${recipe.getRecipeName()}" />">DELETE</a>

                </div>
            </div>
        </div>
    </c:forEach>

</body>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        $("#num_ingredient").val(1);
        var add_button = $("#add_field");
        var form_container = $(".ingredient_form");
        $(add_button).click(function (e) {
            e.preventDefault();
            var num_ingredients = parseInt($("#num_ingredient").val());
            num_ingredients++;
            var new_label = "ingredient" + num_ingredients;
            $(form_container).append("<p><label for='" + new_label + "' class='text-muted'" + ">Ingredient Name</label>"
                + "<input id='" + new_label + "' name='" + new_label + "' class='form-control resume'></p>");
            $("#num_ingredient").val(num_ingredients);

        });
    });
</script>

</html>