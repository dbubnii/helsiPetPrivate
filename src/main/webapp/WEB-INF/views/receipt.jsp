<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Додати рецепт</title>
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js">
    </script>
    <link href="<c:url value="../../resources/css/login.css"/>" rel="stylesheet" type="text/css">
    <style>

        .my-container {
            height: 1050px;
        }
    </style>
</head>
<body>
<jsp:include page="../views/header.jsp"/>
<div class="my-container">
<section class="vh-100">
    <div class="container-fluid h-custom">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-9 col-lg-6 col-xl-5">
                <img style="border-radius: 50%; width: 1024px" src="../../resources/images/page-1_i.jpg"
                     class="img-fluid" alt="Sample image">
            </div>
            <div style="margin-top: 5rem" class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                <h1 class="title">Форма Рецепту</h1>
                <hr style="margin-bottom: 20px">
                <form method="POST" action="${pageContext.request.contextPath}/" autocomplete="off">
                    <div class="form-outline mb-4">
                        <label for="vetFullName">ПІБ Лікаря
                            <input style="width: 600px" type="text" id="vetFullName" name="vetFullName" class="form-control form-control-lg"
                                   required/>
                        </label>
                    </div>
                    <div class="form-outline mb-4">
                        <label for="clinicName">Введіть назву клініки
                            <input style="width: 600px" type="text" id="clinicName" name="clinicName" class="form-control form-control-lg"
                                   required/>
                        </label>
                    </div>
                    <div class="form-outline mb-4">
                        <label for="ownerFullName">ПІБ Пацієнта
                            <input style="width: 600px" type="text" id="ownerFullName" name="ownerFullName"
                                   class="form-control form-control-lg" required/>
                        </label>
                    </div>
                    <div class="form-outline mb-4">
                        <label for="drugName">Введіть назву лікарських засобів
                            <input style="width: 600px" type="email" id="drugName" name="drugName" class="form-control form-control-lg"
                                   required/>
                        </label>
                    </div>
                    <div class="form-outline mb-4">
                        <label for="drugCount">Спосіб застосування лікарських записів
                            <input style="width: 600px" type="text" id="drugCount" name="drugCount" class="form-control form-control-lg"
                                   required/>
                        </label>
                    </div>

                    <div class="form-outline mb-4">
                        <label for="dateStart">Дата виписки рецепту
                            <input style="width: 600px" type="text" id="dateStart" name="dateStart" class="form-control form-control-lg"
                                   required/>
                        </label>
                    </div>

                    <div class="form-outline mb-4">
                        <label for="dateEnd">Кінцева дата дії рецепта
                            <input style="width: 600px" type="text" id="dateEnd" name="dateEnd" class="form-control form-control-lg"
                                   required/>
                        </label>
                    </div>

                    <div class="form-outline mb-4">
                        <label for="details">Вкажіть деталі щодо рецепту
                            <input style="width: 600px" type="text" id="details" name="details" class="form-control form-control-lg"
                                   required/>
                        </label>
                    </div>

                    <input type="hidden" id="appointmentId" name="appointmentId" value="${appointmentId}"/>

                    <div class="text-center text-lg-start mt-4 pt-2">
                        <button type="submit" class="button-new" onclick="createReceipt()">Створити рецепт</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
</div>
</body>
</html>
<jsp:include page="../views/footer.jsp"/>

<script>
    function createReceipt() {
        const vetFullName = document.getElementById("vetFullName").value;
        const ownerFullName = document.getElementById("ownerFullName").value;
        const drugName = document.getElementById("drugName").value;
        const drugCount = document.getElementById("drugCount").value;
        const dateStart = document.getElementById("dateStart").value;
        const dateEnd = document.getElementById("dateEnd").value;
        const clinicName = document.getElementById("clinicName").value;
        const details = document.getElementById("details").value;
        const appointmentId = document.getElementById("appointmentId").value;


        $.ajax({
            type: "POST",
            async: false,
            url: "/receipt/create",
            data: {
                vetFullName: vetFullName,
                ownerFullName: ownerFullName,
                drugName: drugName,
                drugCount: drugCount,
                dateStart: dateStart,
                dateEnd: dateEnd,
                clinicName: clinicName,
                details: details,
                appointmentId: appointmentId
            }
        }).done(function () {
            alert("Рецепт успішно створено!");
        }).fail(function () {
            alert("Щось пішло не так!");
        });
    }
</script>