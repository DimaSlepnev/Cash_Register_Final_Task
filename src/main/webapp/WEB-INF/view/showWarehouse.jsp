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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
    <title>Warehouse</title>
</head>
<body>
<%@include file="header.jsp"%>
<table class="table table-striped" id="warehouseTable">
    <thead>
    <tr>
        <th scope="col">${productId}
            <form action="showWarehouseAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-up"></i>
                </button>
                <input type="hidden" name="sort" value="id_ASC">
            </form>
            <form action="showWarehouseAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-down"></i>
                </button>
                <input type="hidden" name="sort" value="id_DESC">
            </form>
        </th>
        <th scope="col">${productName}
            <form action="showWarehouseAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-up"></i>
                </button>
                <input type="hidden" name="sort" value="product_ASC">
            </form>
            <form action="showWarehouseAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-down"></i>
                </button>
                <input type="hidden" name="sort" value="product_DESC">
            </form>
        </th>
        <th scope="col">${productAmount}
            <form action="showWarehouseAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-up"></i>
                </button>
                <input type="hidden" name="sort" value="amount_ASC">
            </form>
            <form action="showWarehouseAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-down"></i>
                </button>
                <input type="hidden" name="sort" value="amount_DESC">
            </form>
        </th>
        <th scope="col">${whoMade}
            <form action="showWarehouseAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-up"></i>
                </button>
                <input type="hidden" name="sort" value="expertId_ASC">
            </form>
            <form action="showWarehouseAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-down"></i>
                </button>
                <input type="hidden" name="sort" value="expertId_DESC">
            </form>
        </th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="warehouses" scope="request" type="java.util.List"/>
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
