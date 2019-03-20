<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mistral
  Date: 2019-03-20
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="../fragments/header.jspf" %>

<table border="2">

    <tr>
        <td>Id</td>
        <td>Imię</td>
        <td>Nazwisko</td>
        <td colspan="2">Czynności</td>
    </tr>
    <c:forEach items="${Customers}" var="customer">
        <tr>
            <td>${customer.id}</td>
            <td>${customer.firstName}</td>
            <td>${customer.lastName}</td>
            <td>Wyświetl szczegóły</td>
            <td>Usuń</td>
        </tr>
    </c:forEach>
</table>

<%@ include file="../fragments/footer.jspf" %>
</body>
</html>
