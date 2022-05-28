<%@ page import="org.example.model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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


<html>
<%Employee employee = (Employee) session.getAttribute("employee");%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand">Cash Register</a>
    <% if (employee != null) {%>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">

                <a class="navbar-text">
                    <%=employee.getFirstName()%> <%=employee.getSecondName()%>; Position: <%=employee.getPosition()%>
                 </a>

            </li>
            <% if (employee.getPosition().equals("expert")) {%>
            <li class="nav-item active">
                <a class="nav-link" href="redirectToCreateProduct">Create Product</a>
            </li>
            <%}%>
            <%if (employee.getPosition().equals("senior cashier")){%>
            <li class="nav-item active">
                <a class="nav-link" href="redirectToAddNewEmployee">Add new Employee</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="redirectToAllBills">All Bills</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="redirectToReport">Make report</a>
            </li>
            <%}%>
            </li>
            <%if (employee.getPosition().equals("cashier")){%>
            <li class="nav-item active">
                <a class="nav-link" href="redirectToCreateBill">Create Bill</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="redirectToAllBills">All Bills</a>
            </li>
            <%}%>
            <li class="nav-item">
                <a class="nav-link" href="redirectToAllEmployees">All Employees</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="redirectToWarehouse">Warehouse</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="logout">Logout</a>
            </li>
        </ul>
    </div>
    <%}%>
</nav>
</html>
