<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Rating</title>
</head>
<body>
	<form action="ratingfind" method="post">
		<h1>Search for a rating By RatingPoints</h1>
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