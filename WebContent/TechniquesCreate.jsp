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
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Create a Technique</title>
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
	<div class='container'>
		<div class="row">
			<div class="col-lg-12">
				<h3 class="text-dark">Create Technique</h3>
			</div>
		</div>

		<div class="row">

			<div class="col-lg-12">
				<div class="job-detail mt-2 p-4">
					<div class="custom-form">

						<form action="techniquescreate" method="post">
							<div class='row'>
								<div class="col-md-4">
									<div class="form-group app-label">
										<label for="recipeId" class="text-muted">Recipe Id</label>
										<input id="recipeId" type="text" class="form-control resume" name="recipeId"
											value="">
									</div>
								</div>
								<div class="col-md-4">
									<div class="form-group app-label">
										<label for="technique" class="text-muted">Technique</label>
										<input id="technique" name="technique" type="text" class="form-control resume"
											value="">
									</div>
								</div>
							</div>
							<p>
								<button class="btn btn-dark my-2 my-sm-0 button-search" type="submit">Create</button>
							</p>

						</form>

					</div>
					<p>
						<span id="successMessage"><b>${messages.success}</b></span>
					</p>
				</div>
			</div>
		</div>
	</div>
</body>

</html>