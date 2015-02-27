<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>User profile</title>
    <link type="text/css" href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>

<div class="page-header">
    <h1>
        <c:if test="${client.id == 0}">New client</c:if>
        <c:if test="${client.id > 0}">Edit client</c:if>
    </h1>
    <small>Edit client details</small>
</div>

<c:if test="${not empty errors}">
    <div class="alert alert-danger">
        <c:out value="${errors}"/>
    </div>
</c:if>

<div class="container">
    <form:form commandName="client" action="createNewClient.action" cssClass="form-horizontal">
        <legend>Client details</legend>
        <fieldset>
            <div class="form-group">
                <label for="firstName">First name</label>
                <form:input path="firstName"/>
            </div>
            <div class="form-group">
                <label for="lastName">Last name</label>
                <form:input path="lastName"/>
            </div>
            <div class="form-group">
                <label for="phone">Phone</label>
                <form:input path="phone"/>
            </div>
            <div class="form-group">
                <label for="address">Address</label>
                <form:textarea path="address"/>
            </div>
        </fieldset>
        <button type="submit">Save</button>
    </form:form>
</div>
</body>
</html>