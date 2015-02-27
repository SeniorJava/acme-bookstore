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
        <c:out value="Cart info: ${sessionScope['bookCart'].status}"/>
    </p>

    <p class="text-info">

    </p>

</div>

</body>
</html>