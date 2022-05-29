<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>

<fmt:bundle basename="i18n">
    <fmt:message key="label.product" var="labelProduct"/>
    <fmt:message key="input.product" var="inputProduct"/>
    <fmt:message key="label.amount" var="labelAmount"/>
    <fmt:message key="input.amount" var="inputAmount"/>
    <fmt:message key="button.confirm" var="buttonConfirm"/>
</fmt:bundle>
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
    <div class="form-group">
        <label for="formGroupExampleInput" class="col-sm-2 col-form-label">${labelProduct}</label>
        <input type="text"  name="product"   style="width: 300px;height: 40px" class="form-control" id="formGroupExampleInput" placeholder="${inputProduct}" required="required" pattern="^[A-Z][a-z\s]+|[А-ЯІЇҐЄ][а-яіїєґ\s]+$">
    </div>
    </div>
    <div class="form-group">
        <label for="formGroupExampleInput2" class="col-sm-2 col-form-label">${labelAmount}</label>
        <input type="number" name="amount" style="width: 300px;height: 40px" class="form-control" id="formGroupExampleInput2" placeholder="${inputAmount}" min="1" required="required">
    </div>
    </div>
    <button class="btn btn-primary btn-lg btn-block" type="submit">${buttonConfirm}</button>
</form>
</center>
<my:footer/>
</body>
</html>
