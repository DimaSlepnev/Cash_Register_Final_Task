<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Report</title>
</head>
<body>
<%@include file="header.jsp"%>
<center>
<form action="xReport" method="post">
    <button type="submit" class="btn btn-outline-info">Create X-Report</button>
</form>
<form action="zReport" method="post">
    <button type="submit" class="btn btn-outline-info">Create Z-Report</button>
</form>
</center>
</body>
</html>
