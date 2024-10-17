<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>LibTrack Login</title>
</head>
<body>
<h2>Login to LibTrack</h2>

<form action="Login" method="post">
    <div>
        <label for="email">Email:</label>
        <input type="email" name="email" required>
    </div>
    <div>
        <label for="password">Password:</label>
        <input type="password" name="password" required>
    </div>
    <div>
        <input type="submit" value="Login">
    </div>
    <p>If you don't have an account, <a href="memberRegister.jsp">register here</a>.</p>
</form>

</body>
</html>