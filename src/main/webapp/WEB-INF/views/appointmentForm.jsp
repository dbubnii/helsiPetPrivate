<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Реєстрація</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js">
    </script>
    <link href="<c:url value="../../resources/css/login.css"/>" rel="stylesheet" type="text/css">

</head>
<body style="background-color: white">
<jsp:include page="../views/header.jsp"/>
<section class="vh-100">
    <div class="container-fluid h-custom">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-9 col-lg-6 col-xl-5">
                <img style="width: 1000px; height: 500px" src="../../resources/images/img_4.png"
                     class="img-fluid" alt="Sample image">
            </div>
            <div style="margin-top: 5rem" class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                <h1 class="title">Форма запису на прийом</h1>
                <form method="POST" action="${pageContext.request.contextPath}/#" autocomplete="off"
                      onsubmit="return makeAnAppointment()">

                    <div class="form-outline mb-4">
                        <label> ПІБ Ветеринара
                            <input type="text" class="form-control form-control-lg"
                                   value="${vet.firstName} ${vet.lastName}" readonly/>
                            <input type="hidden" id="vetUsername" name="vetUsername" value="${vet.usersUsername}"/>
                        </label>
                    </div>
                    <div class="form-outline mb-4">
                        <label> ПІБ Власника
                            <input type="text" class="form-control form-control-lg"
                                   value="${owner.firstName} ${owner.lastName}" readonly/>
                            <input type="hidden" id="ownerUsername" name="ownerUsername"
                                   value="${owner.usersUsername}"/>
                        </label>
                    </div>
                    <div class="form-outline mb-4">
                        <label for="pet-select">Виберіть тварину, яку бажаєте записати</label>

                        <select name="pets" id="pet-select">
                            <option value="">Кличка тваринки:</option>
                            <c:forEach items="${ownerPets}" var="ownerPet">
                                <option value="${ownerPet.name}">${ownerPet.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-outline mb-4">
                        <label for="pet-select">Виберіть доступні дати на запис: </label>

                        <select name="availableDates" id="date-select">
                            <option value="">Оберіть дату:</option>
                            <c:forEach items="${vetAvailableDates}" var="vetAvailableDate">
                                <option value="${vetAvailableDate}">${vetAvailableDate}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-outline mb-4">
                        <label for="details">Вкажіть деталі запису</label>
                        <input type="text" id="details" name="details"
                               class="form-control form-control-lg" required/>
                    </div>

                    <div class="text-center text-lg-start mt-4 pt-2">
                        <button type="submit" class="button-new">Відправити форму</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
</body>
</html>
<jsp:include page="../views/footer.jsp"/>

<script>
    function makeAnAppointment() {
        const vetUsername = document.getElementById("vetUsername").value;
        const ownerUsername = document.getElementById("ownerUsername").value;
        const ownerPetName = document.getElementById("pet-select").value;
        const vetAvailableDate = document.getElementById("date-select").value;
        const details = document.getElementById("details").value;

        let success = false;

        $.ajax({
            type: "POST",
            async: false,
            url: "/appointment",
            data: {
                vetUsername: vetUsername,
                ownerUsername: ownerUsername,
                ownerPetName: ownerPetName,
                vetAvailableDate: vetAvailableDate,
                details: details
            }
        }).done(function (response) {
            alert("Ваш запит створено успішно! Після підтвердження від лікаря, ви отримаєте лист на пошту!")
            success = true;
        }).fail(function (response) {
            success = false;
            if (response.status === 400) {
                alert("Щось пішло не так!");
            }
        });

        return success;
    }
</script>