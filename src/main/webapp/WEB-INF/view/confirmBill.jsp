<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
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
            <label class="col-sm-2 col-form-label">Product ID:</label>
            <div class="col-sm-10">
                <c:out value="${bill.id}"/>
                <input type="hidden" name="id" value="${bill.id}">
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
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Price:</label>
            <div class="col-sm-10">
                <c:out value="${bill.price}"/>
            </div>
        </div>
        <div class="form-group">
            <label for="formGroupExampleInput" class="col-sm-2 col-form-label">Confirmation:</label>
            <select name="confirmation" id="formGroupExampleInput">
                <option value="yes">yes</option>
                <option value="no">no</option>
            </select>
        </div>
        <button class="btn btn-primary btn-lg btn-block" type="submit">Submit</button>
    </form>
</center>
</body>
</html>
