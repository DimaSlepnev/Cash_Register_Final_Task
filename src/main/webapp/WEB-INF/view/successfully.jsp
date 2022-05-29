<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="i18n">
    <fmt:message key="alert.data.was.successfully.added" var="dataAdd"/>
</fmt:bundle>

<html>
<head>
    <title>Success</title>
</head>
<body>
<script>
    alert("${dataAdd}")
</script>
</body>
</html>
