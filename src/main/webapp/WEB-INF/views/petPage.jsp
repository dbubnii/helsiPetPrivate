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
        <div class="column" style="width: 250px; margin-left: 30px">
            <div class="card mt-5 ml-5 mb-5" style="font-family: Fira Code Light">
                <c:if test="${pet.photo == null}">
                    <img class="card-img-top" src="../../resources/images/cat_md.jpg" alt=""/>
                </c:if>
                <c:if test="${pet.photo != null}">
                    <img class="card-img-top" src="${pet.photo}" alt=""/>
                </c:if>
                <h4><b>${pet.name}</b></h4>
                <b> Вік: ${pet.age} роки</b>
                <b>Унікальний ідентифікатор: ${pet.uniqueID}</b>
                <form action="/pet/profile/${pet.id}">
                    <button class="btn " style="background-color: #847577; color: white; width: 90%" type="submit">Переглянути профіль</button>
                </form>
            </div>
        </div>
    </c:forEach>
    <div class="column" style="width: 250px; margin-left: 30px; margin-top: 175px">
        <div class="card mt-5 ml-5 mb-5">
            <form action="/pet/add" style="margin-bottom: 0">
                <button class="btn" style="background-color: #1985a1; color: white; width: 100%; height: 100%" type="submit">Додати тварину</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<jsp:include page="../views/footer.jsp"/>