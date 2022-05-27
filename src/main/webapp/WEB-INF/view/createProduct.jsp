<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<html>
<head>
    <title>Creation of product</title>
</head>
<body>
<%@include file="header.jsp"%>
    <%if(request.getAttribute("createProduct")!=null ){%>
    <%@include file="successfully.jsp"%>
    <%}%>
<center>
<form action="createProduct" method="post">
    <div class="form-group row">
        <label for="formGroupExampleInput" class="col-sm-2 col-form-label">Product</label>
        <div class="col-sm-10">
        <input type="text"  name="product"   style="width: 300px;height: 40px" class="form-control" id="formGroupExampleInput" placeholder="Input product" required="required" pattern="^[A-Z][a-z\s]+|[А-ЯІЇҐЄ][а-яіїєґ\s]+$">
    </div>
    </div>
    <div class="form-group row">
        <label for="formGroupExampleInput2" class="col-sm-2 col-form-label">Amount</label>
        <div class="col-sm-10">
        <input type="number" name="amount" style="width: 300px;height: 40px" class="form-control" id="formGroupExampleInput2" placeholder="Input Amount" min="1" required="required">
    </div>
    </div>
    <button class="btn btn-primary btn-lg btn-block" type="submit">Confirm</button>
</form>
</center>
</body>
</html>
