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
        <td colspan="2"><b>Dodaj nowy pojazd</b></td>
    </tr>
    <form action="../vehicles_main" method="post">
        <input type="hidden" value="add" name="activity">
        <tr>
            <td>Wprowadź markę</td>
            <td>
                <input type="text" placeholder="marka" name="make">
            </td>
        </tr>
        <tr>
            <td>Wprowadź model</td>
            <td>
                <input type="text" placeholder="model" name="model">
            </td>
        </tr>
        <tr>
            <td>Wprowadź datę produkcji</td>
            <td>
                <input type="date" placeholder="yyyy-mm-dd" name="manufactured">
            </td>
        </tr>
        <tr>
            <td>Wprowadź datę następnego badania technicznego</td>
            <td>
                <input type="date" placeholder="yyyy-mm-dd" name="nextReview">
            </td>
        </tr>
        <tr>
            <td>Wprowadź numer rejestracyjny</td>
            <td>
                <input type="text" placeholder="numer rejestracyjny" name="registrationNumber">
            </td>
        </tr>

        <tr>
            <td>Wybierz właściciela</td>
            <td>
                <select name="customer">
                    <option value="">Rozwiń listę...</option>

                    <c:forEach items="${Customers}" var="customer">
                        <option value="${customer.id}">${customer.lastName} ${customer.firstName}</option>
                    </c:forEach>

                </select>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="Dodaj">
            </td>
        </tr>
    </form>
</table>
<br><br>
<table border="2">
    <tr>
        <td colspan=7><b>Zarejestrowane pojazdy</td>
    </tr>
    <tr>
        <td><i>Id</td>
        <td><i>Marka</td>
        <td><i>Model</td>
        <td><i>Numer rejestracyjny</td>
        <td><i>Właściciel</td>
        <td colspan="2"><i>Czynności</td>
    </tr>
    <c:forEach items="${Vehicles}" var="vehicle">
    <c:if test="${vehicle.id==VehicleToEdit.id}">
        <form action="../vehicles_main" method="post">
            <input type="hidden" name="activity" value="editedValues">
            <input type="hidden" name="id" value="${vehicle.id}">
            <tr>
                <td>${vehicle.id}</td>
                <td><input type="text" value="${vehicle.make}" name="make"></td>
                <td><input type="text" value="${vehicle.model}" name="model"></td>
                <td><input type="text" value="${vehicle.registrationNumber}" name="registrationNumber"></td>
                <td><select name="customer">
                    <option value="${vehicle.owner.id}">${vehicle.owner.lastName} ${vehicle.owner.firstName} </option>

                    <c:forEach items="${Customers}" var="customer">
                        <option value="${customer.id}">${customer.lastName} ${customer.firstName}</option>
                    </c:forEach>

                </select>
                </td>
                <td>Wyświetl szczegóły</td>
                <td>
                    <input type="submit" value="Zatwierdź">
                </td>
        </form>
        <td>
            <form action="../vehicles_main" method="post">
                <input type="hidden" name="activity" value="delete">
                <input type="hidden" name="id" value="${vehicle.id}">
                <input type="submit" value="Usuń">
            </form>
        </td>
        </tr>
    </c:if>
        <c:if test="${vehicle.id!=VehicleToEdit.id}">
            <tr>
                <td>${vehicle.id}</td>
                <td>${vehicle.make}</td>
                <td>${vehicle.model}</td>
                <td>${vehicle.registrationNumber}</td>
                <td>${vehicle.owner.firstName} ${vehicle.owner.lastName}</td>
                <td>Wyświetl szczegóły</td>
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
    </c:if>
    </c:forEach>
</table>

<%@ include file="../fragments/footer.jspf" %>
</body>
</html>
