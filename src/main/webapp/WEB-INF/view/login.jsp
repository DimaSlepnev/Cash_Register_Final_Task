<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>

<fmt:bundle basename="i18n">
    <fmt:message key="login.page.sign.in" var="signIn"/>
    <fmt:message key="login.page.input.login" var="inputLogin"/>
    <fmt:message key="login.page.input.password" var="inputPassword"/>
    <fmt:message key="login.page.button.login" var="buttonLogin"/>
</fmt:bundle>

<html>
<body>
<%@include file="header.jsp"%>
<section class="vh-100" style="background-color: #508bfc;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card shadow-2-strong" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">

                        <h3 class="mb-5">${signIn}</h3>
                        <form action="login" method="post">

                        <div class="form-outline mb-4">
                            <input name="login" type="text" id="typeEmailX-2" class="form-control form-control-lg" required="required" placeholder="${inputLogin}"  />
                        </div>

                        <div class="form-outline mb-4">
                            <input name="pass" type="password" id="typePasswordX-2" class="form-control form-control-lg" required="required" placeholder="${inputPassword}" />
                        </div>

                        <button class="btn btn-primary btn-lg btn-block" type="submit">${buttonLogin}</button>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<my:footer/>
</body>
</html>
