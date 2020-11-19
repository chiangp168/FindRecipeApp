<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Favorite</title>
</head>
<body>
	<form action="favoritefind" method="post">
		<h1>Search for a Favorite By UserId</h1>
		<p>
			<label for="userId">UserId</label>
			<input id="userId" name="userId" value="${fn:escapeXml(param.userId)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<h1>Matching Favorites</h1>
        <table border="1">
            <tr>
            	<th>FavoriteId</th>
                <th>RecipeId</th>
                <th>RecipeName</th>
                <th>UserId</th>                
            </tr>
            <c:forEach items="${favorites}" var="favorite" >
                <tr>
                	<td><c:out value="${favorite.getFavoriteId()}" /></td>
                    <td><c:out value="${favorite.getRecipe().getRecipeId()}" /></td>
                    <td><c:out value="${favorite.getRecipe().getRecipeName()}" /></td>                    
                    <td><c:out value="${favorite.getUser().getUserId()}" /></td>                    
                </tr>
            </c:forEach>
       </table>
</body>
</html>