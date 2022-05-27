<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Warehouse</title>
</head>
<body>
<%@include file="header.jsp"%>
<table class="table table-striped" id="warehouseTable">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Product</th>
        <th scope="col">Amount</th>
        <th scope="col">ID of expert that made order</th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="warehouse" scope="request" type="java.util.List"/>
    <c:forEach var="product" items="${warehouse}">
        <tr>
            <td><c:out value="${product.id}"/></td>
            <td><c:out value="${product.product}"/></td>
            <td><c:out value="${product.amount}"/></td>
            <td><c:out value="${product.expertId}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script>
    $(document).ready(function(){
        $('#warehouseTable').dataTable();
    });
</script>
</body>
</html>
