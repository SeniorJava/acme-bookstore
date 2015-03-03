<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Cart contents</title>
    <link type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<div class="page-header">
    <h1>Book cart <small>Here you can make your purchase final.</small></h1>
    <strong>Dear ${userInfo}!</strong> Please, proceed with your purchase.
</div>

<div class="container">
    <table class="table table-bordered table-striped">
        <caption>Cart content</caption>
        <thead>
        <th>#</th>
        <th>Title</th>
        <th>Author</th>
        <th>Genre</th>
        <th>Price</th>
        <th width="1%">Qty</th>
        </thead>
        <tbody>
        <c:forEach items="${sessionScope['bookCart'].lines}" var="line" varStatus="lineStatus">
            <tr>
                <td><c:out value="${lineStatus.index + 1}"/></td>
                <td><c:out value="${line.book.title}"/></td>
                <td><c:out value="${line.book.author}"/></td>
                <td><c:out value="${line.book.genre}"/></td>
                <td align="right"><c:out value="${line.book.price}"/></td>
                <td><c:out value="${line.qty}"/></td>
            </tr>
        </c:forEach>
        <tr class="panel-success">
            <td colspan="6"><c:out value="${sessionScope['bookCart'].status}"/></td>
        </tr>
        </tbody>
    </table>
    <ul class="nav nav-pills right">
        <c:if test="${not sessionScope['bookCart'].lines.isEmpty()}">
            <li class="active"><a href="makePurchase.action">Make purchase</a></li>
            <li><a href="clearCart.action">Clear cart</a></li>
        </c:if>
        <li><a href="selectBooks.action">Add more books</a></li>
    </ul>
</div>
</body>
</html>