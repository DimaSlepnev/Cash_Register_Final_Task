<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
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
            <label class="col-sm-2 col-form-label">Product ID:</label>
            <div class="col-sm-10">
                <c:out value="${bill.id}"/>
                <input name="id" type="hidden" value="${bill.id}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Product:</label>
            <div class="col-sm-10">
                <c:out value="${bill.body}"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Amount:</label>
            <div class="col-sm-10">
                <c:out value="${bill.amount}"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Price:</label>
            <div class="col-sm-10">
                <c:out value="${bill.price}"/>
            </div>
        </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Confirmation:</label>
            <div class="col-sm-10">
                <c:out value="${bill.confirmation}"/>
            </div>
        </div>
        <button class="btn btn-primary btn-lg btn-block" type="submit">Delete</button>
    </form>
</center>
</body>
</html>
