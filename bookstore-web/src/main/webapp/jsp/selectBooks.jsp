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
    <h1>Book catalogue
        <small>Here you can select books for purchase.</small>
    </h1>
    <strong>Welcome, ${userInfo}!</strong>
</div>

<div class="container">
    <ul class="nav nav-pills navbar-right">
        <li class="active">
            <a href="${pageContext.request.contextPath}/purchase/showCart.action">
                <span class="badge pull-right"><c:out value="${sessionScope['bookCart'].getBooksQty()}"/></span>
                Your cart
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/purchase/showOrders.action">Orders</a>
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
    <!-- Genre selection -->
    <ul class="nav nav-pills navbar-left">
        <c:forEach items="${genres}" var="genre">
            <li <c:if test="${not empty selectedGenre and genre == selectedGenre}">class="active"</c:if>>
                <a href="${pageContext.request.contextPath}/purchase/selectBooksByGenre.action?genre=<c:out value="${genre}"/>">
                    <c:out value="${genre}"/></a>
            </li>
        </c:forEach>
        <li <c:if test="${empty selectedGenre}">class="active"</c:if>>
            <a href="${pageContext.request.contextPath}/purchase/selectBooksByGenre.action?genre=">Show all books</a>
        </li>
    </ul>

    <!-- Books table -->
    <table class="table table-bordered table-striped">
        <caption class="caption">All available books</caption>
        <thead>
        <th>#</th>
        <th>Title</th>
        <th>Author</th>
        <th>Genre</th>
        <th>Price</th>
        <th width="1%"/>
        </thead>
        <tbody>
        <c:forEach items="${allBooks}" var="book" varStatus="bookStatus">
            <tr>
                <td><c:out value="${bookStatus.index + 1}"/></td>
                <td><c:out value="${book.title}"/></td>
                <td><c:out value="${book.author}"/></td>
                <td><c:out value="${book.genre}"/></td>
                <td align="right"><c:out value="${book.price}"/></td>
                <td><a href="${pageContext.request.contextPath}/purchase/addBookToCart.action?id=${book.id}"
                       class="btn btn-success">Add to cart</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>