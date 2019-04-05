<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: maria
  Date: 26.03.19
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Lab SOA - Pierwszy Projekt</title>
  </head>
  <body>
  <h1>To jest moja pierwsza aplikacja web w JavaEE</h1>
  <p>Wyswietlamy na raie stronę JSP</p>
  <% Date tmp = new Date();
     out.print("<h2>" + tmp.toString() + "</h2>");
     %>
  </body>
</html>
