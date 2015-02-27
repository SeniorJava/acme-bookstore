<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Your purchases</title>
    <link type="text/css" href="../css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<script src="../js/jquery.js"></script>
<script src="../js/bootstrap.min.js"></script>

<div class="page-header">
    <h1>Purchase list</h1>
    <small>Here you can view all your purchases</small>
</div>

<div class="container">
    <table class="table table-bordered table-striped">
        <caption>All purchase orders</caption>
        <thead>
            <th>#</th>
            <th>Order date</th>
            <th>Books qty</th>
            <th>Total price</th>
            <th>Status</th>
        </thead>
        <tbody>
        <c:forEach items="${orders}" varStatus="orderStatus" var="order">
            <tr>
                <td><c:out value="${orderStatus.index + 1}"/></td>
                <td><c:out value="${order.orderDate}"/></td>
                <td><c:out value="${order.booksQty}"/></td>
                <td><c:out value="${order.totalPrice}"/></td>
                <td><c:out value="${order.status.title}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <ul class="nav nav-pills right">
        <li class="active"><a href="selectBooks.action">Select books</a></li>
    </ul>
</div>
</body>
</html>