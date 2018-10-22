<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加书籍</title>
</head>
<body>
<form action="/insert" method="post">
    <p>书籍名称：<input type="text" name="bookname"></p>
    <p>书籍数量：<input type="number" min="0" name="cnt" ></p>
    <p><input type="submit" value="添加"></p>
</form>
</body>
</html>
