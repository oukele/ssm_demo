<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/btn_add">添加书籍</a>
<table border="1" style="margin: auto" width="300px">
    <tr>
        <th>编号</th>
        <th>书名</th>
        <th>数量</th>
    </tr>
    <tbody>
    <c:forEach items="${books}" var="book">
        <tr>
            <td>${book.bookid}</td>
            <td>${book.name}</td>
            <td>${book.cnt}</td>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>
</html>
