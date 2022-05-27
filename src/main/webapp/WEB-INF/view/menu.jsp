<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
</head>
<body>
<%@include file="header.jsp" %>
<h1>You logged as <%= employee.getPosition()%> <%= employee.getFirstName()%> <%= employee.getSecondName()%> </h1>
</body>
</html>
