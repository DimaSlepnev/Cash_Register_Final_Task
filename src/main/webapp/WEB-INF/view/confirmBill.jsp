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
    <fmt:message key="option.yes" var="yes"/>
    <fmt:message key="option.no" var="no"/>
    <fmt:message key="button.confirm" var="confirmation"/>
    <fmt:message key="button.confirm" var="submit"/>
</fmt:bundle>

<html>
<head>
    <title>Confirmation</title>
</head>
<body>
<%@include file="header.jsp" %>
<center>
    <form action="confirmBill" method="post">
        <jsp:useBean id="bill" scope="request" type="org.example.model.Bill"/>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">${productId}:</label>
            <div class="col-sm-10">
                <c:out value="${bill.id}"/>
                <input type="hidden" name="id" value="${bill.id}">
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
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">${price}:</label>
            <div class="col-sm-10">
                <c:out value="${bill.price}"/>
            </div>
        </div>
        <div class="form-group">
            <label for="formGroupExampleInput" class="col-sm-2 col-form-label">${confirmation}:</label>
            <select name="confirmation" id="formGroupExampleInput">
                <option value="yes">${yes}</option>
                <option value="no">${no}</option>
            </select>
        </div>
        <button class="btn btn-primary btn-lg btn-block" type="submit">${submit}</button>
    </form>
</center>
<my:footer/>
</body>
</html>
