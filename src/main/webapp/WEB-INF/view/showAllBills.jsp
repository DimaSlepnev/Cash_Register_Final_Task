<%@ page import="java.util.List" %>
<%@ page import="org.example.model.Bill" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">

<fmt:bundle basename="i18n">
    <fmt:message key="label.id" var="id"/>
    <fmt:message key="label.product.id" var="productId"/>
    <fmt:message key="label.body" var="body"/>
    <fmt:message key="label.amount" var="amount"/>
    <fmt:message key="label.price" var="price"/>
    <fmt:message key="label.confirmation" var="confirmation"/>
    <fmt:message key="label.action" var="action"/>
</fmt:bundle>

<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
    <title>All bills</title>
</head>
<body>
<%@include file="header.jsp" %>
<%if (request.getAttribute("billConfirm") != null || request.getAttribute("editBill")!= null ||request.getAttribute("deleteBill")!=null) {%>
<%@include file="successfully.jsp"%>
<%}%>
<table class="table table-striped" id="billsTable">
    <thead>
    <tr>
        <th scope="col">${id}
            <form action="showAllBillsAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-up"></i>
                </button>
                <input type="hidden" name="sort" value="id_ASC">
            </form>
            <form action="showAllBillsAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-down"></i>
                </button>
                <input type="hidden" name="sort" value="id_DESC">
            </form>
        </th>
        <th scope="col">${productId}
            <form action="showAllBillsAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-up"></i>
                </button>
                <input type="hidden" name="sort" value="productId_ASC">
            </form>
            <form action="showAllBillsAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-down"></i>
                </button>
                <input type="hidden" name="sort" value="productId_DESC">
            </form>
        </th>
        <th scope="col">${body}
            <form action="showAllBillsAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-up"></i>
                </button>
                <input type="hidden" name="sort" value="body_ASC">
            </form>
            <form action="showAllBillsAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-down"></i>
                </button>
                <input type="hidden" name="sort" value="body_DESC">
            </form>
        </th>
        <th scope="col">${amount}
            <form action="showAllBillsAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-up"></i>
                </button>
                <input type="hidden" name="sort" value="amount_ASC">
            </form>
            <form action="showAllBillsAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-down"></i>
                </button>
                <input type="hidden" name="sort" value="amount_DESC">
            </form>
        </th>
        <th scope="col">${price}
            <form action="showAllBillsAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-up"></i>
                </button>
                <input type="hidden" name="sort" value="price_ASC">
            </form>
            <form action="showAllBillsAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-down"></i>
                </button>
                <input type="hidden" name="sort" value="price_DESC">
            </form>
        </th>
        <th scope="col">${confirmation}
            <form action="showAllBillsAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-up"></i>
                </button>
                <input type="hidden" name="sort" value="confirmation_ASC">
            </form>
            <form action="showAllBillsAs">
                <button class="btn btn-outline-primary" type="submit">
                    <i class="bi bi-arrow-bar-down"></i>
                </button>
                <input type="hidden" name="sort" value="confirmation_DESC">
            </form>
        </th>
        <%if (employee.getPosition().equals("senior cashier")) {%>
        <th scope="col">${action}</th>
        <%}%>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="bills" scope="request" type="java.util.List"/>
    <c:set var="count" value="0" scope="page"/>
    <c:forEach var="bill" items="${bills}">
        <tr>
            <td><c:out value="${bill.id}"/></td>
            <td><c:out value="${bill.productId}"/></td>
            <td><c:out value="${bill.body}"/></td>
            <td><c:out value="${bill.amount}"/></td>
            <td><c:out value="${bill.price}"/></td>
            <td><c:out value="${bill.confirmation}"/></td>
            <%if (employee.getPosition().equals("senior cashier")) {%>
            <td>
                <div class="d-flex justify-content-evenly">
                    <c:if test="${!bill.confirmation}">
                <form action="redirectToConfirmBill">
                    <input name="idConfirm" type="hidden" value="${bill.id}">
                    <button class="btn btn-outline-success" type="submit"><i class="bi bi-check2-square"></i></button>
                </form>
                    </c:if>
                <c:if test="${!bill.confirmation}">
                <form action="redirectToEditBill">
                    <input type="hidden" value="${bill.id}" name="idEdit">
                    <button class="btn btn-outline-primary" type="submit"><i class="bi bi-pencil-square"></i></button>
                </form>
                </c:if>
                <form action="redirectToDeleteBill">
                    <input type="hidden" value="${bill.id}" name="idDelete">
                    <button class="btn btn-outline-danger" type="submit"><i class="bi bi-trash3-fill"></i></button>
                </form>
                </div>
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
