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
        <td colspan="2"><b>Dodaj nowego klienta</b></td>
    </tr>
    <form action="../customers_main" method="post">
        <input type="hidden" value="add" name="activity">
        <tr>
            <td>Wprowadź imię</td>
            <td>
                <input type="text" placeholder="imię" name="firstName">
            </td>
        </tr>
        <tr>
            <td>Wprowadź nazwisko</td>
            <td>
                <input type="text" placeholder="nazwisko" name="lastName">
            </td>
        </tr>
        <tr>
            <td>Wprowadź datę urodzenia</td>
            <td>
                <input type="date" placeholder="yyyy-mm-dd" name="birthday">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Dodaj">
            </td>
        </tr>
    </form>
    </td>
    </form>
    </tr>

</table>
<br><br>
<table border="2">
    <tr>
        <td colspan="6"><b>Zarejestrowani klienci</td>
    </tr>
    <tr>
        <td><i>Id</td>
        <td><i>Imię</td>
        <td><i>Nazwisko</td>
        <td colspan="3"><i>Czynności</td>
    </tr>
    <c:forEach items="${Customers}" var="customer">
        <%--<c:if test="${customer.id!=CustomerToEdit.id}">--%>
        <c:if test="${customer.id!=CustomerToEdit.id && customer.id!=999}">
            <tr>
                <td>${customer.id}</td>
                <td>${customer.firstName}</td>
                <td>${customer.lastName}</td>
                <td>
                    <form action="../customers_main" method="post">
                        <input type="hidden" name="activity" value="editRequest">
                        <input type="hidden" name="id" value="${customer.id}">
                        <input type="submit" value="Edytuj">
                    </form>
                </td>
                <td>
                    <form action="../customers_main" method="post">
                        <input type="hidden" name="activity" value="delete">
                        <input type="hidden" name="id" value="${customer.id}">
                        <input type="submit" value="Usuń">
                    </form>
                </td>
                <td>
                    <form action="../customers_main" method="post">
                        <input type="hidden" name="activity" value="details">
                        <input type="hidden" name="id" value="${customer.id}">
                        <input type="submit" value="Wyświetl szczegóły">
                    </form>
                </td>
            </tr>
        </c:if>
        <c:if test="${customer.id==CustomerToEdit.id}">
            <form action="../customers_main" method="post">
                <input type="hidden" name="activity" value="editedValues">
                <input type="hidden" name="id" value="${customer.id}">
                <tr>
                    <td>${customer.id}</td>
                    <td><input type="text" value="${customer.firstName}" name="firstName"></td>
                    <td><input type="text" value="${customer.lastName}" name="lastName"></td>
                    <td><input type="submit" value="Zatwierdź">
            </form>
            </td>
            <td>
                <form action="../customers_main" method="post">
                    <input type="hidden" name="activity" value="delete">
                    <input type="hidden" name="id" value="${customer.id}">
                    <input type="submit" value="Usuń"></form>
            </td>
            <td>
                <form action="../customers_main" method="post">
                    <input type="hidden" name="activity" value="details">
                    <input type="hidden" name="id" value="${customer.id}">
                    <input type="submit" value="Wyświetl szczegóły"></form>
                </form>
            </td>
            </tr>

        </c:if>
    </c:forEach>
</table>

<%@ include file="../fragments/footer.jspf" %>
</body>
</html>
