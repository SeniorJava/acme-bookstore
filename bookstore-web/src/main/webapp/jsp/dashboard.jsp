<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dashboard</title>
    <link type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<div class="page-header">
    <h1>Dashboard
        <small>All important information</small>
    </h1>
</div>

<div class="container">

    <div class="panel panel-primary">
        <div class="panel-heading">Total performance index</div>
        <div class="panel-body">
            <table class="table table-bordered">
                <caption>Order statistic</caption>
                <tr>
                    <td width="20%" align="right">Orders qty:</td>
                    <td><c:out value="${stats.ordersQty}"/></td>
                </tr>
                <tr>
                    <td width="20%" align="right">Total orders sum:</td>
                    <td><c:out value="${stats.totalSum}"/></td>
                </tr>
            </table>
        </div>
    </div>

    <ul class="nav nav-pills right">
        <li class="active"><a href="${pageContext.request.contextPath}/manager/showDashboard.action">Refresh</a></li>
        <li><a href="${pageContext.request.contextPath}/manager/showAllBooks.action">Book list</a></li>
        <li><a href="${pageContext.request.contextPath}/manager/showAllOrders.action">Order list</a></li>
        <li>
            <a href="${pageContext.request.contextPath}/j_spring_security_logout">Logout</a>
        </li>
    </ul>
</div>
</body>
</html>