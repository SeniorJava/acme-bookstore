<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Book catalogue</title>
    <link type="text/css" href="../css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<script src="../js/jquery.js"></script>
<script src="../js/bootstrap.min.js"></script>

<div class="page-header">
    <h1>Book catalogue</h1>
    <small>Here you can select books for purchase</small><br/>
    <a href="showCart">View cart</a>
</div>

<div class="container">
    <form action="addToCart" method="post">
        <table class="table table-bordered table-striped">
            <caption>All available books</caption>
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
        <button type="submit">Add to cart</button>
    </form>
</div>

<br/>

</body>
</html>