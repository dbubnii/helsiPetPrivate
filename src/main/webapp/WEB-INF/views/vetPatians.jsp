<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Головна</title>
    <link href="<c:url value="../../resources/css/vetPatians.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="../views/header.jsp"/>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Ім'я власника</th>
        <th scope="col">Кличка тварини</th>
        <th scope="col">Останній запис</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <c:forEach items="${pets}" var="pet">
        <tbody>
        <tr>
            <th class="counterCell" scope="row"></th>
            <td>${pet.ownerUsername}</td>
            <td>${pet.name}</td>
            <td>01.06.2023</td>
            <td>
                <div class="row">
                    <button type="button" class="btn btn-primary" style="margin-right: 10px">Відкрити профіль</button>
                    <button type="button" class="btn btn-primary">Виписати рецепт</button>
                </div>
            </td>
        </tr>
        </tbody>
    </c:forEach>
</table>

</body>
</html>
<jsp:include page="../views/footer.jsp"/>
