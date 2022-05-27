<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<html>
<head>
    <title>Creation of bill</title>
</head>
<body>
<%@include file="header.jsp"%>
<center>
    <%if(request.getAttribute("createBill")!=null ){%>
    <%@include file="successfully.jsp"%>
    <%}%>
    <%if (request.getAttribute("errorBill")!=null) {%>
    <%@include file="error.jsp"%>
    <%}%>
    <form action="createBill" method="post">
        <div class="form-group">
            <label for="formGroupExampleInput">Product</label>
            <input type="text"  name="body"   style="width: 300px;height: 40px" class="form-control" id="formGroupExampleInput" placeholder="Input product name or id" required="required" pattern="^[A-Z][a-z\s]+|[А-ЯІЇҐЄ][а-яіїєґ\s]+|[0-9]+$">
        </div>
        <div class="form-group">
            <label for="formGroupExampleInput2">Amount</label>
            <input type="number" name="amount" style="width: 300px;height: 40px" class="form-control" id="formGroupExampleInput2" placeholder="Input Amount" min="1" required="required">
        </div>
        <div class="form-group">
            <label for="formGroupExampleInput3">Price</label>
            <input type="number" name="price" style="width: 300px;height: 40px" class="form-control" id="formGroupExampleInput3" placeholder="Input Price" min="1" required="required">
        </div>
        <button class="btn btn-primary btn-lg btn-block" type="submit">Create bill</button>
    </form>
</center>
</body>
</html>
