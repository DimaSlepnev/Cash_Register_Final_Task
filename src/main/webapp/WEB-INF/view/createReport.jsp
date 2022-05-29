<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>

<fmt:bundle basename="i18n">
    <fmt:message key="button.create.x.report" var="xReportButton"/>
    <fmt:message key="button.create.z.report" var="zReportButton"/>
</fmt:bundle>

<html>
<head>
    <title>Report</title>
</head>
<body>
<%@include file="header.jsp"%>
<center>
<form action="xReport" method="post">
    <button type="submit" class="btn btn-outline-info">${xReportButton}</button>
</form>
<form action="zReport" method="post">
    <button type="submit" class="btn btn-outline-info">${zReportButton}</button>
</form>
</center>
<my:footer/>
</body>
</html>
