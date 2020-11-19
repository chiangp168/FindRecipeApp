<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Find Comments</title>
</head>
<body>
<h1>${messages.title}</h1>
<h1>Find Comments</h1>
<form action="commentsfind" method="post">
    <h1>Search for Comments</h1>
    <p>
        <label for="userId">UserId</label>
        <input id="userId" name="userId" value="${fn:escapeXml(param.userId)}">
    </p>
    <p>
        <label for="recipeId">recipeId</label>
        <input id="recipeId" name="recipeId" value="${fn:escapeXml(param.recipeId)}">
    </p>
    <p>
        <input type="submit">
        <br/><br/><br/>
        <span id="successMessage"><b>${messages.success}</b></span>
    </p>
</form>
<h1>Matching Comments</h1>
<table border="1">
    <tr>
        <th>commentId</th>
        <th>userId</th>
        <th>recipeId</th>
        <th>content</th>
        <th>createdTime</th>
    </tr>
    <c:forEach items="${comments}" var="comment" >
        <tr>
            <td><c:out value="${comment.getCommentId()}"/></td>
            <td><c:out value="${comment.getUserId()}" /></td>
            <td><c:out value="${comment.getRecipeId()}" /></td>
            <td><c:out value="${comment.getContent()}" /></td>
            <td><c:out value="${comment.getCreatedTime()}" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

