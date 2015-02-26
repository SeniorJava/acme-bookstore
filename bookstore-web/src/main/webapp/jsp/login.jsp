<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login to ACME Bookstore</title>
    <link type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<div class="page-header">
    <h1>Welcome to ACME Bookstore</h1>
    <small>Please, login or sign up</small>
    <br/>
</div>

<c:if test="${param.error == 'true'}">
    <div class="alert alert-danger">
        Login failed: check your credentials
    </div>
</c:if>

<div class="container">
    <form action="${pageContext.request.contextPath}/j_spring_security_check" method="post" role="form">
        <legend>Enter your credentials:</legend>
        <fieldset>
            <div class="form-group">
                <label for="j_username">Username</label>
                <input type="text" id="j_username" name="j_username" class="form-control text"
                       placeholder="User name"/>
            </div>
            <div class="form-group">
                <label for="j_password">Password</label>
                <input type="password" id="j_password" name="j_password" class="form-control"
                       placeholder="Password"/>
            </div>
        </fieldset>
        <button type="submit" class="btn btn-lg btn-primary">Login</button>
        &nbsp;or&nbsp;<a href="${pageContext.request.contextPath}/signUp.action" class="btn btn-lg btn-warning">Sign up</a>
    </form>
</div>
</body>
</html>