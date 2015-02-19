<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Select client for order</title>
    <link type="text/css" href="../css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<script src="../js/jquery.js"></script>
<script src="../js/bootstrap.min.js"></script>

<div class="page-header">
    <h1>Select client</h1>
    <small>Here you can specify your shipping details</small>
</div>

<div class="container">
    <p class="text-info">
        <c:out value="Total order price: ${order.totalPrice}, books qty: ${order.lines.size()}"/>
    </p>
    <h3>Select existing client:</h3>
    <form:form commandName="order">
        <form:select path="order.client">
            <form:options items="${clients}" itemValue="id" itemLabel="lastName"/>
        </form:select>
    </form:form>
    <h3>or create new:</h3>
</div>

</body>
</html>