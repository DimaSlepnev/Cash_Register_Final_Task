<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>

<fmt:bundle basename="i18n">
    <fmt:message key="label.id" var="id"/>
    <fmt:message key="select.position" var="position"/>
    <fmt:message key="label.firstName" var="firstName"/>
    <fmt:message key="label.secondName" var="secondName"/>
    <fmt:message key="label.action" var="action"/>
</fmt:bundle>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">

<html>
<head>
    <title>All employees</title>
</head>
<body>
<%@include file="header.jsp" %>
<%if (request.getAttribute("employeeDelete") != null) {%>
<%@include file="successfully.jsp" %>
<%}%>
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">${id}
            <form action="showAllEmployeesAs">
                <button class="btn btn-outline-primary" type="submit">
            <i class="bi bi-arrow-bar-up"></i>
                </button>
                <input type="hidden" name="sort" value="id_ASC">
            </form>
            <form action="showAllEmployeesAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-down"></i>
                </button>
                <input type="hidden" name="sort" value="id_DESC">
            </form>
        </th>
        <th scope="col">${position}
            <form action="showAllEmployeesAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-up"></i>
                </button>
                <input type="hidden" name="sort" value="position_ASC">
            </form>
            <form action="showAllEmployeesAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-down"></i>
                </button>
                <input type="hidden" name="sort" value="position_DESC">
            </form></th>
        <th scope="col">${firstName}
            <form action="showAllEmployeesAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-up"></i>
                </button>
                <input type="hidden" name="sort" value="name_ASC">
            </form>
            <form action="showAllEmployeesAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-down"></i>
                </button>
                <input type="hidden" name="sort" value="name_DESC">
            </form></th>
        <th scope="col">${secondName}
            <form action="showAllEmployeesAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-up"></i>
                </button>
                <input type="hidden" name="sort" value="surname_ASC">
            </form>
            <form action="showAllEmployeesAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-down"></i>
                </button>
                <input type="hidden" name="sort" value="surname_DESC">
            </form></th>
        <%if (employee.getPosition().equals("senior cashier")) {%>
        <th scope="col">${action}</th>
        <%}%>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="employees" scope="request" type="java.util.List"/>
    <jsp:useBean id="eId" scope="request" type="java.lang.Integer"/>
    <c:set var="count" value="0" scope="page"/>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <td><c:out value="${employee.id}"/></td>
            <td><c:out value="${employee.position}"/></td>
            <td><c:out value="${employee.firstName}"/></td>
            <td><c:out value="${employee.secondName}"/></td>
            <%if (employee.getPosition().equals("senior cashier")) {%>
            <td>
                <c:if test="${employee.id != eId}">
                <form action="redirectToDeleteEmployee">
                    <input type="hidden" value="${employee.id}" name="employeeIdDelete">
                    <button class="btn btn-outline-danger" type="submit"><i class="bi bi-trash3-fill"></i></button>
                </form>
                </c:if>
            </td>
            <%}%>
        </tr>
        <c:set var="count" value="${count + 1}" scope="page"/>
    </c:forEach>
    </tbody>
</table>

<jsp:useBean id="numberOfPages" scope="request" type="java.lang.Integer"/>

<center>
    <table>
        <ul class="pagination justify-content-center">
        <c:forEach var="i" begin="1" end="${numberOfPages}">
            <li class="page-item"><a class="page-link" href="?page=${i}">${i}</a></li>
        </c:forEach>
        </ul>
    </table>
</center>

<my:footer/>
</body>
</html>
