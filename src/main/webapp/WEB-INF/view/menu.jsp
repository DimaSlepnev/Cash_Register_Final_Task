<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>

<fmt:bundle basename="i18n">
    <fmt:message key="menu.logged.as" var="loggedAs"/>
</fmt:bundle>

<html>
<head>
</head>
<body>
<%@include file="header.jsp" %>
<h1>${loggedAs} <%= employee.getPosition()%> <%= employee.getFirstName()%> <%= employee.getSecondName()%> </h1>
<my:footer/>
</body>
</html>
