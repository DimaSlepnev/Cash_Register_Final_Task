<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>

<fmt:bundle basename="i18n">
    <fmt:message key="label.product.id" var="productId"/>
    <fmt:message key="label.product" var="product"/>
    <fmt:message key="label.amount" var="amount"/>
    <fmt:message key="label.price" var="price"/>
    <fmt:message key="label.confirmation" var="confirmation"/>
    <fmt:message key="button.delete" var="delete"/>
</fmt:bundle>

<html>
<head>
    <title>Delete</title>
</head>
<body>
<%@include file="header.jsp"%>
<center>
    <form action="deleteBill" method="post">
        <jsp:useBean id="bill" scope="request" type="org.example.model.Bill"/>
        <div class="form-group row" >
            <label class="col-sm-2 col-form-label">${productId}:</label>
            <div class="col-sm-10">
                <c:out value="${bill.id}"/>
                <input name="id" type="hidden" value="${bill.id}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">${product}:</label>
            <div class="col-sm-10">
                <c:out value="${bill.body}"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">${amount}:</label>
            <div class="col-sm-10">
                <c:out value="${bill.amount}"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">${price}:</label>
            <div class="col-sm-10">
                <c:out value="${bill.price}"/>
            </div>
        </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">${confirmation}:</label>
            <div class="col-sm-10">
                <c:out value="${bill.confirmation}"/>
            </div>
        </div>
        <button class="btn btn-primary btn-lg btn-block" type="submit">${delete}</button>
    </form>
</center>
<my:footer/>
</body>
</html>
