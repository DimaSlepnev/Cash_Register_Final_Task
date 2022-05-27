<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
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
<table class="table table-striped" id="employeeTable">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Position</th>
        <th scope="col">Name</th>
        <th scope="col">Surname</th>
        <%if (employee.getPosition().equals("senior cashier")) {%>
        <th scope="col">Action</th>
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
<script>
    $(document).ready(function(){
        $('#employeeTable').dataTable();
    });
</script>
</body>
</html>
