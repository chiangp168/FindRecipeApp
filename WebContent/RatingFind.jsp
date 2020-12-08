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
<title>Find a Rating</title>
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
            </ul>

        </div>
    </nav>
	<form action="ratingfind" method="post">
		<h1>Search for A Rating By RatingPoints</h1>
		<p>
			<label for="ratingPoint">RatingPoints</label>
			<input id="ratingPoint" name="ratingPoint" value="${fn:escapeXml(param.ratingPonit)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<h1>Matching Ratings</h1>
        <table border="1">
            <tr>
                <th>RatingPoints</th>
                <th>RecipeName</th>
                <th>RecipeId</th>
                <th>UserId</th>
                <th>RatingId</th>
            </tr>
            <c:forEach items="${ratings}" var="rating" >
                <tr>
                    <td><c:out value="${rating.getRatingPoints()}" /></td>
                    <td><c:out value="${rating.getRecipes().getRecipeName()}" /></td>
                    <td><c:out value="${rating.getRecipes().getRecipeId()}" /></td>
                    <td><c:out value="${rating.getUser().getUserId()}" /></td>
                    <td><c:out value="${rating.getRatingId()}" /></td>
                </tr>
            </c:forEach>
       </table>