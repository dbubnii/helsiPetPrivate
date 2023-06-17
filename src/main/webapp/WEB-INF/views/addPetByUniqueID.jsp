<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Головна</title>
    <link href="<c:url value="../../resources/css/addPetByUniqueId.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="../views/header.jsp"/>

<div class="my-container">
    <form class="search-bar" method="POST" action="${pageContext.request.contextPath}/pet/byUniqueID">
        <input type="text" placeholder="Пошук..." name="uniqueID" id="uniqueID">
        <button type="submit">
            <img src="../../resources/images/search.png">
        </button>
    </form>
</div>
<%--<form class="input-group" method="POST" action="${pageContext.request.contextPath}/pet/byUniqueID">--%>
<%--    <input type="search" class="form-control rounded" id="uniqueID" name="uniqueID" placeholder="Пошук..." aria-label="Search" aria-describedby="search-addon" />--%>
<%--    <button type="submit" class="btn btn-outline-primary">search</button>--%>
<%--</form>--%>
<%--<form class="search-container" method="POST" action="${pageContext.request.contextPath}/pet/byUniqueID">--%>
<%--    <input type="search" id="uniqueID" name="uniqueID" placeholder="Пошук..." required>--%>
<%--    <button type="submit">Знайти</button>--%>
<%--</form>--%>
</body>
</html>
<jsp:include page="../views/footer.jsp"/>