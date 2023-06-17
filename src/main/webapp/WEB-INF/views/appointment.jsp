<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Головна</title>
    <link href="<c:url value="../../resources/css/myRequest.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="../views/header.jsp"/>
<%--<nav class="navbar navbar-light bg-light">--%>
<%--    <form class="form-inline" method="POST" class="example" action="${pageContext.request.contextPath}/user">--%>
<%--        <input class="form-control mr-sm-2" type="search" placeholder="Пошук..." aria-label="Search" id="name"--%>
<%--               name="name">--%>
<%--        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>--%>
<%--    </form>--%>
<%--</nav>--%>
<form class="search-container" method="POST" action="${pageContext.request.contextPath}/user">
    <input type="text" placeholder="Пошук..." class="search-input" id="name" name="name">
    <button type="submit" class="search-button">Пошук</button>
</form>
<table class="table">
    <tbody>
    <tr>
        <th scope="row">1</th>
        <td>Іван</td>
        <td>Козаченко</td>
        <td>ikozachenko@gmail.com</td>
        <td><button type="button" class="btn " style="background-color: #847577; color: white">Записатись</button></td>
    </tr>
    <tr>
        <th scope="row">2</th>
        <td>Степан</td>
        <td>Марків</td>
        <td>smarkiv@gmail.com</td>
        <td><button type="button" class="btn " style="background-color: #847577; color: white">Записатись</button></td>
    </tr>
    <tr>
        <th scope="row">3</th>
        <td>Тетяна</td>
        <td>Івахнюк</td>
        <td>tetanaIv@gmail.com</td>
        <td><button type="button" class="btn " style="background-color: #847577; color: white">Записатись</button></td>
    </tr>
    </tbody>
</table>
</body>
</html>
<jsp:include page="../views/footer.jsp"/>