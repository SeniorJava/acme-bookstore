<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>List of books</title>
    <link type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<div class="page-header">
    <h1>Book orders
        <small>Here you can view all books.</small>
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

    <!-- Books table -->
    <table class="table table-bordered table-striped">
        <caption class="caption">All books</caption>
        <thead>
        <th>#</th>
        <th>Title</th>
        <th>Author</th>
        <th>Genre</th>
        <th>ISBN</th>
        <th>Price</th>
        </thead>
        <tbody>
        <c:forEach items="${allBooks}" var="book" varStatus="bookStatus">
            <tr>
                <td><c:out value="${bookStatus.index + 1}"/></td>
                <td><c:out value="${book.title}"/></td>
                <td><c:out value="${book.author}"/></td>
                <td><c:out value="${book.genre}"/></td>
                <td><c:out value="${book.ISBN}"/></td>
                <td align="right"><c:out value="${book.price}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>