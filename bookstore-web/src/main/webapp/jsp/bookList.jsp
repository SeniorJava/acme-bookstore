<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Book list</title>
    <link type="text/css" href="../css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<h1>Book reference</h1>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">

            <table width="100%" border="1" cellspacing="0" cellpadding="2" class="table-bordered">
                <tr>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Genre</th>
                    <th>Price</th>
                </tr>
                <c:forEach items="${books}" var="book">
                    <tr>
                        <td><c:out value="${book.title}"/></td>
                        <td><c:out value="${book.author}"/></td>
                        <td><c:out value="${book.genre}"/></td>
                        <td align="right"><c:out value="${book.price}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

</body>
</html>