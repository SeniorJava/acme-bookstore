<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Book catalogue</title>
    <link type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<div class="page-header">
    <h1>Book catalogue <small>Here you can select books for purchase.</small></h1>
    <strong>Welcome, ${userInfo}!</strong>
</div>

<div class="container">
    <ul class="nav nav-pills navbar-right">
        <li class="active">
            <a href="showCart.action">
                <span class="badge pull-right"><c:out value="${sessionScope['bookCart'].getBooksQty()}"/></span>
                Your cart
            </a>
        </li>
        <li>
            <a href="showOrders.action">Orders</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/j_spring_security_logout">Logout</a>
        </li>
    </ul>
</div>

<c:if test="${not empty message}">
    <br/>
    <div class="alert alert-info alert-dismissable">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
        <c:out value="${message}"/>
    </div>
</c:if>

<div class="container">
    <form action="addToCart.action" method="post">
        <table class="table table-bordered table-striped">
            <caption class="caption">All available books</caption>
            <thead>
            <th>#</th>
            <th>Title</th>
            <th>Author</th>
            <th>Genre</th>
            <th>Price</th>
            <th width="1%">Qty</th>
            </thead>
            <tbody>
            <c:forEach items="${allBooks}" var="book" varStatus="bookStatus">
                <tr>
                    <td><c:out value="${bookStatus.index + 1}"/></td>
                    <td><c:out value="${book.title}"/></td>
                    <td><c:out value="${book.author}"/></td>
                    <td><c:out value="${book.genre}"/></td>
                    <td align="right"><c:out value="${book.price}"/></td>
                    <td><input type="number" name="${book.id}" value="1" class="text-right"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <button type="submit" class="btn btn-primary btn-lg">Add to cart</button>
    </form>
</div>
</body>
</html>