<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Restaurant</th>
        <th>Menu</th>
        <th>Votes</th>
    </tr>
    </thead>
    <c:forEach items="${restaurants}" var="r">
        <jsp:useBean id="r" scope="page" type="com.pobeguts.RestaurantSearch.model.Restaurant"/>
            <td>${r.name}</td>
            <td>${r.menu}</td>
            <td>${r.users.size()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
