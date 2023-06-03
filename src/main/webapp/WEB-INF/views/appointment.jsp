<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Головна</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous">
    </script>
</head>
<body>
<jsp:include page="../views/header.jsp"/>
<nav class="navbar navbar-light bg-light">
    <form class="form-inline" method="POST" class="example" action="${pageContext.request.contextPath}/user">
        <input class="form-control mr-sm-2" type="search" placeholder="Пошук..." aria-label="Search" id="name" name="name">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
</nav>
</body>
</html>
<jsp:include page="../views/footer.jsp"/>