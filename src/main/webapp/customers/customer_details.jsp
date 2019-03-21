<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mistral
  Date: 2019-03-21
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="../fragments/header.jspf" %>
<h2>Informacje o kliencie ${Customer.firstName} ${Customer.lastName}</h2>
<p>Imię: ${Customer.firstName}</p>
<p>Nazwisko: ${Customer.lastName}</p>
<p>Data urodzenia: ${Customer.birthday}</p>

<h3>Samochody klienta:</h3>
<table border="2">
    <tr>
        <td>
            <b>Id</b>
        </td>
        <td>
            <b>Numer rejestracyjny</b>
        </td>
        <td>
            <b>Marka</b>
        </td>
        <td>
            <b>Model</b>
        </td>
        <td>
            <b>Data produkcji</b>
        </td>
        <td>
            <b>Data następnego badania technicznego</b>
        </td>
    </tr>
    <c:forEach items="${Vehicles}" var="vehicle">
        <tr>
            <td>${vehicle.id}</td>
            <td>${vehicle.registrationNumber}</td>
            <td>${vehicle.make}</td>
            <td>${vehicle.model}</td>
            <td>${vehicle.manufactured}</td>
            <td>${vehicle.nextReview}</td>
            <td>
                <form action="../vehicles_main" method="post">
                    <input type="hidden" name="activity" value="editRequest">
                    <input type="hidden" name="id" value="${vehicle.id}">
                    <input type="submit" value="Edytuj">
                </form>
            </td>
            <td>
                <form action="../vehicles_main" method="post">
                    <input type="hidden" name="activity" value="delete">
                    <input type="hidden" name="id" value="${vehicle.id}">
                    <input type="submit" value="Usuń">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="../fragments/footer.jspf" %>
</body>
</html>
