<%@ page import="java.util.List" %>
<%@ page import="org.example.model.Bill" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
<html>
<head>
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
        <th scope="col">ID</th>
        <th scope="col">Product ID</th>
        <th scope="col">Body</th>
        <th scope="col">Amount</th>
        <th scope="col">Price</th>
        <th scope="col">Confirmation</th>
        <%if (employee.getPosition().equals("senior cashier")) {%>
        <th scope="col">Action</th>
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
<script>
    $(document).ready(function(){
        $('#billsTable').dataTable();
    });
</script>
</body>
</html>
