<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>

<fmt:bundle basename="i18n">
    <fmt:message key="label.product" var="product"/>
    <fmt:message key="input.product.name.or.id" var="inputNameOrId"/>
    <fmt:message key="label.amount" var="amount"/>
    <fmt:message key="input.amount" var="inputAmount"/>
    <fmt:message key="label.price" var="price"/>
    <fmt:message key="input.price" var="inputPrice"/>
    <fmt:message key="button.create.bill" var="createBill"/>
</fmt:bundle>

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
            <label for="formGroupExampleInput">${product}</label>
            <input type="text" name="body" style="width: 300px;height: 40px" class="form-control" id="formGroupExampleInput" placeholder="${inputNameOrId}" required="required" pattern="^[A-Z][a-z\s]+|[А-ЯІЇҐЄ][а-яіїєґ\s]+|[0-9]+$">
        </div>
        <div class="form-group">
            <label for="formGroupExampleInput2">${amount}</label>
            <input type="number" name="amount" style="width: 300px;height: 40px" class="form-control" id="formGroupExampleInput2" placeholder="${inputAmount}" min="1" required="required">
        </div>
        <div class="form-group">
            <label for="formGroupExampleInput3">${price}</label>
            <input type="number" name="price" style="width: 300px;height: 40px" class="form-control" id="formGroupExampleInput3" placeholder="${inputPrice}" min="1" required="required">
        </div>
        <button class="btn btn-primary btn-lg btn-block" type="submit">${createBill}</button>
    </form>
</center>
</body>
</html>
