<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Edit employee</title>
</head>
<body>
<%@include file="header.jsp"%>
<center>
    <form action="deleteEmployee" method="post">
        <jsp:useBean id="emp" scope="request" type="org.example.model.Employee"/>
        <div class="form-group row" >
            <label class="col-sm-2 col-form-label">ID:</label>
            <div class="col-sm-10">
                <c:out value="${emp.id}"/>
                <input name="id" type="hidden" value="${emp.id}">
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Position:</label>
            <div class="col-sm-10">
                <c:out value="${emp.position}"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Name:</label>
            <div class="col-sm-10">
                <c:out value="${emp.firstName}"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Surname:</label>
            <div class="col-sm-10">
                <c:out value="${emp.secondName}"/>
            </div>
        </div>
        <button class="btn btn-primary btn-lg btn-block" type="submit">Delete</button>
    </form>
</center>
</body>
</html>
