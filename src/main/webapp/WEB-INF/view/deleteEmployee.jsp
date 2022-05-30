<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>

<fmt:bundle basename="i18n">
    <fmt:message key="label.id" var="id"/>
    <fmt:message key="select.position" var="position"/>
    <fmt:message key="label.firstName" var="firstName"/>
    <fmt:message key="label.secondName" var="secondName"/>
    <fmt:message key="button.delete" var="delete"/>
</fmt:bundle>

<html>
<head>
    <title>Delete employee</title>
</head>
<body>
<%@include file="header.jsp"%>
<center>
    <form action="deleteEmployee" method="post">
        <jsp:useBean id="emp" scope="request" type="org.example.model.Employee"/>
        <div class="form-group row" >
            <label class="col-sm-2 col-form-label">${id}:</label>
            <div class="col-sm-10">
                <c:out value="${emp.id}"/>
                <input name="empIdDelete" type="hidden" value="${emp.id}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">${position}:</label>
            <div class="col-sm-10">
                <c:out value="${emp.position}"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">${firstName}:</label>
            <div class="col-sm-10">
                <c:out value="${emp.firstName}"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">${secondName}:</label>
            <div class="col-sm-10">
                <c:out value="${emp.secondName}"/>
            </div>
        </div>
        <button class="btn btn-primary btn-lg btn-block" type="submit">${delete}</button>
    </form>
</center>
<my:footer/>
</body>
</html>
