<%--
  Created by IntelliJ IDEA.
  User: maria
  Date: 27.03.19
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Logowanie</h1>
<p>${message}</p>

<form action="Login" method="post">
    <p>Login <input type="text" name="login"/></p>
    <p>Hasło <input type="text" name="password"/></p>
    <input type="submit" value="Zaloguj"/>
</form>
</body>
</html>
