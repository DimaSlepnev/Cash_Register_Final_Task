<%@ page import="org.example.model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><html>
<%@ page isELIgnored="false" %>
<head>
    <title></title>
</head>
<body>
<%@include file="createReport.jsp"%>
<jsp:useBean id="zReport" scope="request" type="java.lang.Integer"/>
<div class="container">
    <!-- Modal -->
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Z-Report</h4>
                </div>
                <div class="modal-body">
                    <c:out value="${zReport} was earned today"/>
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
