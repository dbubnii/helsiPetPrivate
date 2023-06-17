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
    <div class="row" style="border:1px solid black;">
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
                <form style="margin-top: 20px"
                      action="${pageContext.request.contextPath}/pet/history/pdf/${petInfo.ownerUsername}/${petInfo.name}">
                    <button class="btn" style="background-color: #8b8c89; color: white" type="submit">Історія хворіб (PDF)</button>
                </form>
                <c:if test="${sessionScope.userType == 'ВЛАСНИК'}">
                    <form action="${pageContext.request.contextPath}/pet/appointment">
                        <button class="btn" style="background-color: #6096ba; color: white" type="submit">Записатись на прийом</button>
                    </form>
                </c:if>
            </div>
        </div>
        <div class="col-md-5 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3"><h4 class="text-right">Налаштування
                    Профілю</h4>
                </div>
                <hr>
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
                <hr>
                <c:if test="${petInfo.ownerUsername != null}">
                    <div class="mt-5 text-center">
                        <button class="btn profile-button" style="background-color: #1985a1; color: white;" type="button">Зберегти зміни</button>
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
        <div class="col-md-3">
            <div class="p-3 py-5">
                <div class="align-items-center">
                    <h4 class="text-right">Вакцинації
                        <hr>
                    </h4>
                    <c:if test="${sessionScope.userType == 'ВЕТЕРИНАР'}">
                        <div class="row mt-3">
                            <form id="formId" method="POST" action="${pageContext.request.contextPath}/"
                                  onsubmit="return editVaccination()">
                                <div class="col-md-12">
                                    <label class="labels" style="font-size: 20px">Призначення
                                        <input type="text" class="form-control" id="purpose" name="purpose"
                                               value="${vaccine != null ? vaccine.purpose : ''}">
                                    </label>
                                </div>
                                <div class="col-md-12">
                                    <label class="labels" style="font-size: 20px">Назва препарату
                                        <input type="text" class="form-control" id="vaccineName" name="vaccineName"
                                               value="${vaccine != null ? vaccine.vaccineName : ''}">
                                    </label>
                                </div>
                                <div class="col-md-12">
                                    <label class="labels" style="font-size: 20px">Термін придатності
                                        <input type="text" class="form-control" id="expiration" name="expiration"
                                               value="${vaccine != null ? vaccine.expiration : ''}">
                                    </label>
                                </div>
                                <div class="col-md-12">
                                    <label class="labels" style="font-size: 20px">Доза
                                        <input type="text" class="form-control" id="vaccineDoze" name="vaccineDoze"
                                               value="${vaccine != null ? vaccine.vaccineDoze : ''}">
                                    </label>
                                </div>
                                <input type="hidden" id="petName" name="petName" value="${petInfo.name}"/>
                                <div class="mt-5 text-center">
                                    <button class="btn profile-button" style="background-color: #1985a1; color: white" type="submit">Добавити/Змінити
                                        Вакцинацію
                                    </button>
                                </div>
                            </form>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.userType == 'ВЛАСНИК'}">
                        <c:if test="${vaccine != null}">
                            <div class="row mt-3">
                                <div class="col-md-12">
                                    <label class="labels" style="font-size: 20px">Призначення
                                        <input type="text" class="form-control" value="${vaccine.purpose}" readonly>
                                    </label>
                                </div>
                                <div class="col-md-12">
                                    <label class="labels" style="font-size: 20px">Назва препарату
                                        <input type="text" class="form-control" value="${vaccine.vaccineName}" readonly>
                                    </label>
                                </div>
                                <div class="col-md-12">
                                    <label class="labels" style="font-size: 20px">Термін придатності
                                        <input type="text" class="form-control" value="${vaccine.expiration}" readonly>
                                    </label>
                                </div>
                                <div class="col-md-12">
                                    <label class="labels" style="font-size: 20px">Серія
                                        <input type="text" class="form-control" value="${vaccine.uuid}" readonly>
                                    </label>
                                </div>
                                <div class="col-md-12">
                                    <label class="labels" style="font-size: 20px">Доза
                                        <input type="text" class="form-control" value="${vaccine.vaccineDoze}" readonly>
                                    </label>
                                </div>
                            </div>
                        </c:if>
                    </c:if>
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

    function editVaccination() {
        const purpose = document.getElementById("purpose").value;
        const vaccineName = document.getElementById("vaccineName").value;
        const expiration = document.getElementById("expiration").value;
        const vaccineDoze = document.getElementById("vaccineDoze").value;
        const petName = document.getElementById("petName").value;

        $.ajax({
            type: "POST",
            async: false,
            url: "/pet/editVaccination",
            data: {
                purpose: purpose,
                vaccineName: vaccineName,
                vaccineDoze: vaccineDoze,
                expiration: expiration,
                petName: petName
            }
        }).done(function () {
            alert("Дані успішно змінено!");
        }).fail(function () {
            alert("Щось пішло не так!");
        });
    }
</script>