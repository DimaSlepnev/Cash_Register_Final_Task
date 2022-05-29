<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>

<fmt:bundle basename="i18n">
    <fmt:message key="label.firstName" var="firstName"/>
    <fmt:message key="input.new.employee.name" var="inputFirstName"/>
    <fmt:message key="label.secondName" var="secondName"/>
    <fmt:message key="input.new.employee.surname" var="inputSecondName"/>
    <fmt:message key="select.position" var="Position"/>
    <fmt:message key="select.option.expert" var="expert"/>
    <fmt:message key="select.option.senior.cashier" var="seniorCashier"/>
    <fmt:message key="select.option.cashier" var="cashier"/>
    <fmt:message key="label.login" var="login"/>
    <fmt:message key="input.new.employee.login" var="inputLogin"/>
    <fmt:message key="label.password" var="password"/>
    <fmt:message key="input.new.employee.password" var="inputPassword"/>
    <fmt:message key="button.add" var="buttonAdd"/>
</fmt:bundle>

<html>
<head>
    <title>Add new employee</title>
</head>
<body>
<%@include file="header.jsp"%>
<center>
    <%if(request.getAttribute("addEmployee")!=null ){%>
    <%@include file="successfully.jsp"%>
    <%}%>
    <%if(request.getAttribute("addEmployeeError")!= null){%>
    <%@include file="error.jsp"%>
    <%}%>
    <form action="addNewEmployee" method="post">
        <div class="form-group">
            <label for="formGroupExampleInput">${firstName}</label>
            <input type="text"  name="firstName"   style="width: 300px;height: 40px" class="form-control" id="formGroupExampleInput" placeholder="${inputFirstName}" required="required" pattern="^[A-Z][a-z\s]+|[А-ЯІЇҐЄ][а-яіїєґ\s]+$">
        </div>
        <div class="form-group">
            <label for="formGroupExampleInput2">${secondName}</label>
            <input type="text"  name="secondName"   style="width: 300px;height: 40px" class="form-control" id="formGroupExampleInput2" placeholder="${inputSecondName}" required="required" pattern="^[A-Z][a-z\s]+|[А-ЯІЇҐЄ][а-яіїєґ\s]+$">
        </div>
        <div class="form-group">
            <label for="formGroupExampleInput" class="col-sm-2 col-form-label">${Position}:</label>
            <select name="position" id="formGroupExampleInput3">
                <option value="expert">${expert}</option>
                <option value="senior cashier">${seniorCashier}</option>
                <option value="cashier">${cashier}</option>
            </select>
        </div>
        <div class="form-group">
            <label for="formGroupExampleInput4">${login}</label>
            <input type="text" name="login" style="width: 300px;height: 40px" class="form-control" id="formGroupExampleInput4" placeholder="${inputLogin}" required="required" pattern="^[A-Za-z0-9]+$">
        </div>
        <div class="form-group">
            <label for="formGroupExampleInput5">${password}</label>
            <input type="text" name="pass" style="width: 300px;height: 40px" class="form-control" id="formGroupExampleInput5" placeholder="${inputPassword}" required="required" pattern="^[A-Za-z0-9]+$">
        </div>
        <button class="btn btn-primary btn-lg btn-block" type="submit">${buttonAdd}</button>
    </form>
</center>
<my:footer/>
</body>
</html>
