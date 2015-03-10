<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>List of book orders</title>
    <link type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<div class="page-header">
    <h1>Book orders
        <small>Here you can view all orders.</small>
    </h1>
</div>

<c:if test="${not empty message}">
    <br/>

    <div class="alert alert-info alert-dismissable">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
        <c:out value="${message}"/>
    </div>
</c:if>

<div class="container">
    <ul class="nav nav-pills right">
        <li class="active"><a href="${pageContext.request.contextPath}/manager/showDashboard.action">Dashboard</a></li>
        <li>
            <a href="${pageContext.request.contextPath}/j_spring_security_logout">Logout</a>
        </li>
    </ul>

    <!-- Orders table -->
    <table class="table table-bordered table-striped">
        <caption class="caption">All orders</caption>
        <thead>
        <th>#</th>
        <th>ID</th>
        <th>Status</th>
        <th>Order date</th>
        <th>Last name</th>
        <th>First name</th>
        <th>Book qty</th>
        <th>Total price</th>
        </thead>
        <tbody>
        <c:forEach items="${orders}" var="order" varStatus="orderStatus">
            <tr>
                <td><c:out value="${orderStatus.index + 1}"/></td>
                <td><c:out value="${order.id}"/></td>
                <td><c:out value="${order.status}"/></td>
                <td><c:out value="${order.orderDate}"/></td>
                <td><c:out value="${order.client.lastName}"/></td>
                <td><c:out value="${order.client.firstName}"/></td>
                <td><c:out value="${order.booksQty}"/></td>
                <td><c:out value="${order.totalPrice}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>