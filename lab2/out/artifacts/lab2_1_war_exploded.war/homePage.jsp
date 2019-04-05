<%@ page import="zad3.Osoba" %><%--
  Created by IntelliJ IDEA.
  User: maria
  Date: 27.03.19
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<h1>Księga gości</h1>
<%
    Osoba user = (Osoba) request.getSession().getAttribute("user");
%>
<h2>Witaj <%
    out.println(user.getName());
%>!</h2>

<h2>Dodaj Komentarz</h2>

<form action="" method="post">
    <p>Email: <input type="text" name="email"/></p>
    <p>Komentarz: <input type="text" name="comment"/></p>
    <input type="submit" value="Wyslij"/>
</form>

<a href="Logout">Wyloguj</a>

</body>
</html>
