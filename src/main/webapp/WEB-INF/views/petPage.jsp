<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Тварини</title>
    <link href="<c:url value="../../resources/css/cards.css"/>" rel="stylesheet" type="text/css">
</head>
<body style="background-color: #E0FFFF">
<jsp:include page="../views/header.jsp"/>
<div class="row" id="petsContainer">
    <c:forEach items="${pets}" var="pet">
        <div class="column">
            <div class="card mt-5 ml-5 mb-5">
                <c:if test="${pet.photo == null}">
                    <img class="card-img-top" src="../../resources/images/cat_md.jpg" alt=""/>
                </c:if>
                <c:if test="${pet.photo != null}">
                    <img class="card-img-top" src="${pet.photo}" alt=""/>
                </c:if>
                <h4><b>${pet.name}</b></h4>
                <b> Вік: ${pet.age} роки</b>
                <b>Унікальний ідентифікатор: ${pet.uniqueID}</b>
                <p class="small fw-bold mt-2 pt-1 mb-0">
                    <a href="/pet/profile/${pet.id}" class="link-danger"><b>Переглянути профіль</b></a>
                </p>
            </div>
        </div>
    </c:forEach>
    <div class="column">
        <div class="card mt-5 ml-5 mb-5">
            <a href="/pet/add" class="btn btn-info btn-lg">
                <span class="glyphicon glyphicon-plus-sign"></span> Додати тварину
            </a>
        </div>
    </div>
</div>
</body>
</html>
<jsp:include page="../views/footer.jsp"/>