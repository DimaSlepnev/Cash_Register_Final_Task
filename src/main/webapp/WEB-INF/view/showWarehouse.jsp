<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>

<fmt:bundle basename="i18n">
    <fmt:message key="label.id" var="productId"/>
    <fmt:message key="label.product" var="productName"/>
    <fmt:message key="label.amount" var="productAmount"/>
    <fmt:message key="label.id.of.expert.that.made.order" var="whoMade"/>
</fmt:bundle>

<html>
<head>
    <title>Warehouse</title>
</head>
<body>
<%@include file="header.jsp"%>
<table class="table table-striped" id="warehouseTable">
    <thead>
    <tr>
        <th scope="col">${productId}</th>
        <th scope="col">${productName}</th>
        <th scope="col">${productAmount}</th>
        <th scope="col">${whoMade}</th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="warehouses" scope="session" type="java.util.List"/>
    <c:forEach var="warehouse" items="${warehouses}">
        <tr>
            <td><c:out value="${warehouse.id}"/></td>
            <td><c:out value="${warehouse.product}"/></td>
            <td><c:out value="${warehouse.amount}"/></td>
            <td><c:out value="${warehouse.expertId}"/></td>
        </tr>
    </c:forEach>

    </tbody>
</table>
<script>
    $(document).ready(function(){
        $('#warehouseTable').dataTable();
    });
</script>
<my:footer/>
</body>
</html>
