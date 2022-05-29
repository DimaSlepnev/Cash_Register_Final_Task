<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>

<fmt:bundle basename="i18n">
    <fmt:message key="label.product.id" var="productId"/>
    <fmt:message key="label.product" var="product"/>
    <fmt:message key="label.amount" var="amount"/>
    <fmt:message key="label.price" var="price"/>
    <fmt:message key="button.edit.bill" var="editBill"/>
</fmt:bundle>

<html>
<head>
    <title>Edit bill</title>
</head>
<body>
<%@include file="header.jsp" %>
<center>
    <%if (request.getAttribute("errorEditBill") != null) {%>
    <%@include file="error.jsp" %>
    <%}%>
    <form action="editBill" method="post">
        <jsp:useBean id="bill" scope="request" type="org.example.model.Bill"/>
        <div class="form-group">
            <label>${productId}</label>
            <c:out value="${bill.id}"/>
            <input type="hidden" name="id" value="${bill.id}">
        </div>
        <div class="form-group">
            <label>${product}</label>
            <c:out value="${bill.body}"/>
        </div>
        <div class="form-group">
            <input type="hidden" name="amountWas" value="${bill.amount}">
            <label for="formGroupExampleInput3">${amount}</label>
            <input type="number" name="amount" style="width: 300px;height: 40px" class="form-control"
                   id="formGroupExampleInput3" value="${bill.amount}" min="1" required="required">
        </div>
        <div class="form-group">
            <label for="formGroupExampleInput4">${price}</label>
            <input type="number" name="price" style="width: 300px;height: 40px" class="form-control"
                   id="formGroupExampleInput4" value="${bill.price}" min="1" required="required">
        </div>
        <button class="btn btn-primary btn-lg btn-block" type="submit">${editBill}</button>
    </form>
</center>
<my:footer/>
</body>
</html>
