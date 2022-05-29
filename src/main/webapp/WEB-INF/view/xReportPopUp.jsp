<%@ page import="org.example.model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="i18n">
    <fmt:message key="label.x.report" var="xReportLabel"/>
    <fmt:message key="out.is.sum.for.all.reports" var="isSumForAllReports"/>
</fmt:bundle>


<html>
<head>
    <title></title>
</head>
<body>
<%@include file="createReport.jsp"%>
<jsp:useBean id="xReport" scope="request" type="java.lang.Integer"/>

<div class="container">
    <!-- Modal -->
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">${xReportLabel}</h4>
                </div>
                <div class="modal-body">
                    <c:out value="${xReport} ${isSumForAllReports}"/>
                </div>
            </div>

        </div>
    </div>

</div>
<script>
    $(document).ready(function(){
    $('#myModal').modal('show');
    });
</script>
</body>
</html>
