<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Create a comment</title>
</head>
<body>
<h1>Create comment</h1>
<form action="commentscreate" method="post">
    <p>
        <label for="recipeId">Recipe Id</label>
        <input id="recipeId" name="recipeId" value="">
    </p>
    <p>
        <label for="userId">User Id</label>
        <input id="userId" name="userId" value="">
    </p>
    <p>
        <label for="content">Content Id</label>
        <input id="content" name="content" value="">
    </p>
    <p>
        <input type="submit">
    </p>
</form>
<br/><br/>
<p>
    <span id="successMessage"><b>${messages.success}</b></span>
</p>
</body>
</html>