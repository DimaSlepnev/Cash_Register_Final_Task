<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
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
            <label for="formGroupExampleInput">Name</label>
            <input type="text"  name="firstName"   style="width: 300px;height: 40px" class="form-control" id="formGroupExampleInput" placeholder="Input name of new employee" required="required" pattern="^[A-Z][a-z\s]+|[А-ЯІЇҐЄ][а-яіїєґ\s]+$">
        </div>
        <div class="form-group">
            <label for="formGroupExampleInput2">Surname</label>
            <input type="text"  name="secondName"   style="width: 300px;height: 40px" class="form-control" id="formGroupExampleInput2" placeholder="Input surname of new employee" required="required" pattern="^[A-Z][a-z\s]+|[А-ЯІЇҐЄ][а-яіїєґ\s]+$">
        </div>
        <div class="form-group">
            <label for="formGroupExampleInput" class="col-sm-2 col-form-label">Position:</label>
            <select name="position" id="formGroupExampleInput3">
                <option value="expert">expert</option>
                <option value="senior cashier">senior cashier</option>
                <option value="cashier">cashier</option>
            </select>
        </div>
        <div class="form-group">
            <label for="formGroupExampleInput4">Login</label>
            <input type="text" name="login" style="width: 300px;height: 40px" class="form-control" id="formGroupExampleInput4" placeholder="Create login for new employee" required="required">
        </div>
        <div class="form-group">
            <label for="formGroupExampleInput5">Password</label>
            <input type="text" name="pass" style="width: 300px;height: 40px" class="form-control" id="formGroupExampleInput5" placeholder="Create password for new employee" required="required">
        </div>
        <button class="btn btn-primary btn-lg btn-block" type="submit">Add</button>
    </form>
</center>
</body>
</html>
