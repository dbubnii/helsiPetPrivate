<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Профіль тварини</title>
    <link href="<c:url value="../../resources/css/petProfile.css"/>" rel="stylesheet" type="text/css">
</head>
<body style="background-color: #E0FFFF">
<jsp:include page="../views/header.jsp"/>
<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                <c:if test="${petInfo.photo != null}">
                    <img class="mt-5" style="width: 180px;height: 279px" src="${petInfo.photo}" alt="">
                </c:if>
                <c:if test="${petInfo.photo == null}">
                    <img class="mt-5" style="width: 180px;height: 279px"
                         src="../../resources/images/cat_md.jpg" alt="">
                </c:if>
                <span class="font-weight-bold">${petInfo.name}</span>
                <span class="text-black-50">${petInfo.age} роки</span>
                <span> </span>
            </div>
        </div>
        <div class="col-md-5 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3"><h4 class="text-right">Налаштування
                    Профілю</h4></div>
                <div class="row mt-2">
                    <c:if test="${petInfo.ownerUsername != null}">
                        <div class="col-md-6">
                            <label class="labels" style="font-size: 20px">ПІБ власника
                                <input type="text" class="form-control" value="${owner.firstName} ${owner.lastName}"
                                       readonly>
                            </label>
                        </div>
                    </c:if>
                </div>
                <div class="row mt-3">
                    <div class="col-md-12">
                        <label class="labels" style="font-size: 20px">Порода
                            <input type="text" class="form-control" value="${petInfo.breed}" readonly>
                        </label>
                    </div>
                    <div class="col-md-12">
                        <label class="labels" style="font-size: 20px">Стать
                            <input type="text" class="form-control" value="${petInfo.sex}" readonly>
                        </label>
                    </div>
                    <div class="col-md-12">
                        <label class="labels" style="font-size: 20px">Розмір
                            <input type="text" class="form-control" value="${petInfo.size}" readonly>
                        </label>
                    </div>
                    <div class="col-md-12">
                        <label class="labels" style="font-size: 20px">Тип
                            <input type="text" class="form-control" value="${petInfo.type}" readonly>
                        </label>
                    </div>
                    <div class="col-md-12">
                        <label class="labels" style="font-size: 20px">Стерилізовано
                            <input type="text" class="form-control"
                                   value="<c:out value="${petInfo.sterilized eq true ? 'ТАК': 'НІ'}"/>" readonly>
                        </label>
                    </div>
                    <div class="col-md-12">
                        <label class="labels" style="font-size: 20px">Вага
                            <input type="text" class="form-control" value="${petInfo.weight}">
                        </label>
                    </div>

                </div>
                <div class="row mt-3">
                    <div class="col-md-6">
                        <label class="labels" style="font-size: 20px">Деталі
                            <input type="text" class="form-control" value="${petInfo.details}">
                        </label>
                    </div>
                </div>

                <c:if test="${petInfo.ownerUsername != null}">
                    <div class="mt-5 text-center">
                        <button class="btn btn-primary profile-button" type="button">Зберегти зміни</button>
                    </div>
                </c:if>
                <c:if test="${petInfo.ownerUsername == null}">
                    <div class="mt-5 text-center">
                        <button type="button" class="btn btn-primary" onclick="assignPetToMe(${petInfo.id})">
                            Це моя тварина
                        </button>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="col-md-4">
            <div class="p-3 py-5">
                <br>
                <div class="col-md-12">
                    <div class="col-md-12">
                        <form action="${pageContext.request.contextPath}/pet/history/pdf/${petInfo.id}">
                            <button type="submit" style="color: darkred;">Історія хворіб (PDF)</button>
                        </form>
                    </div>
                    <br>
                    <div class="col-md-12">
                        <form action="${pageContext.request.contextPath}/pet/appointment">
                            <button type="submit" style="color: #4CAF50">Записатись на прийом</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<jsp:include page="../views/footer.jsp"/>

<script>
    function assignPetToMe(petId) {
        $.ajax({
            type: "POST",
            async: false,
            url: "/pet/assignToMe",
            data: {
                petId: petId
            }
        }).done(function () {
            alert("Запит успішно опрацьовано!");
        }).fail(function () {
            alert("Щось пішло не так!");
        });
    }
</script>