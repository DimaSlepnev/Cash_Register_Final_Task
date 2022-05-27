<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Edit bill</title>
</head>
<body>
<%@include file="header.jsp"%>
<center>
    <%if (request.getAttribute("errorEditBill")!=null) {%>
    <%@include file="error.jsp"%>
    <%}%>
    <form action="editBill" method="post">
        <jsp:useBean id="bill" scope="request" type="org.example.model.Bill"/>
        <div class="form-group">
            <label>Product ID</label>
            <c:out value="${bill.id}"/>
            <input type="hidden" name="id" value="${bill.id}">
        </div>
        <div class="form-group">
            <label>Product</label>
            <c:out value="${bill.body}"/>
        </div>
        <div class="form-group">
            <label for="formGroupExampleInput3">Amount</label>
            <input type="number" name="amount" style="width: 300px;height: 40px" class="form-control" id="formGroupExampleInput3" value="${bill.amount}" min="1" required="required">
        </div>
        <div class="form-group">
            <label for="formGroupExampleInput4">Price</label>
            <input type="number" name="price" style="width: 300px;height: 40px" class="form-control" id="formGroupExampleInput4" value="${bill.price}" min="1" required="required">
        </div>
        <button class="btn btn-primary btn-lg btn-block" type="submit">Edit bill</button>
    </form>
</center>
</body>
</html>
