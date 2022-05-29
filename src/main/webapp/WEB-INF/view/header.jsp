<%@ page import="org.example.model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<link rel="stylesheet"
      href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css"></style>
<script type="text/javascript"
        src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
        src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>


<fmt:bundle basename="i18n">
    <fmt:message key="header.cash.register" var="cashRegister"/>
    <fmt:message key="header.employee.position" var="position"/>
    <fmt:message key="header.create.product" var="createProduct"/>
    <fmt:message key="header.add.new.employee" var="addEmployee"/>
    <fmt:message key="header.all.bills" var="allBills"/>
    <fmt:message key="header.make.report" var="makeReport"/>
    <fmt:message key="header.create.bill" var="createBill"/>
    <fmt:message key="header.all.employees" var="allEmployees"/>
    <fmt:message key="header.warehouse" var="warehouse"/>
    <fmt:message key="header.logout" var="logout"/>
    <fmt:message key="dropdown.language" var="language"/>
    <fmt:message key="dropdown.select.en" var="english"/>
    <fmt:message key="dropdown.select.ua" var="ukranian"/>
</fmt:bundle>

<html>
<%Employee employee = (Employee) session.getAttribute("employee");%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand">${cashRegister}</a>
    <% if (employee != null) {%>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">

                <a class="navbar-text">
                    <%=employee.getFirstName()%> <%=employee.getSecondName()%>; ${position} <%=employee.getPosition()%>
                 </a>

            </li>
            <% if (employee.getPosition().equals("expert")) {%>
            <li class="nav-item active">
                <a class="nav-link" href="redirectToCreateProduct">${createProduct}</a>
            </li>
            <%}%>
            <%if (employee.getPosition().equals("senior cashier")){%>
            <li class="nav-item active">
                <a class="nav-link" href="redirectToAddNewEmployee">${addEmployee}</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="redirectToAllBills">${allBills}</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="redirectToReport">${makeReport}</a>
            </li>
            <%}%>
            </li>
            <%if (employee.getPosition().equals("cashier")){%>
            <li class="nav-item active">
                <a class="nav-link" href="redirectToCreateBill">${createBill}</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="redirectToAllBills">${allBills}</a>
            </li>
            <%}%>
            <li class="nav-item">
                <a class="nav-link" href="redirectToAllEmployees">${allEmployees}</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="redirectToWarehouse">${warehouse}</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="logout">${logout}</a>
            </li>
        </ul>
    </div>
    <%}%>
    <a href="?lang=en" class="btn btn-primary btn-sm active" style="width: 100px" role="button" aria-pressed="true">${english}</a>
    <a href="?lang=ua" class="btn btn-primary btn-sm active" style="margin: 0 20px; width: 100px" role="button" aria-pressed="true">${ukranian}</a>
</nav>
</html>
