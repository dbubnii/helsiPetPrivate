<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Профіль</title>
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js">
    </script>
    <script src="https://code.jquery.com/jquery-3.5.0.js">
    </script>
    <style>
        body {
            background: #1985a1;
        }

        .form-control:focus {
            box-shadow: none;
            border-color: #1985a1;
        }

        .profile-button {
            background: #1985a1;
            box-shadow: none;
            border: none;
        }

        .profile-button:hover {
            background: #1985a1;
        }

        .profile-button:focus {
            background: #1985a1;
            box-shadow: none;
        }

        .profile-button:active {
            background: #1985a1;
            box-shadow: none;
        }

        .back:hover {
            color: #1985a1;
            cursor: pointer;
        }
    </style>
</head>

<jsp:include page="../views/header.jsp"/>
<div class="container rounded bg-white mt-4">
    <div class="row" style="border:1px solid black; background-color: #fbfbf2">
        <div class="col-md-4 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                <img class="rounded-circle mt-5" src="../../resources/images/img_7.png" width="90" alt="">
                <span class="font-weight-bold">${userInfo.firstName} ${userInfo.lastName}</span>
                <span class="text-black-50">${userInfo.email}</span>
            </div>
        </div>
        <div class="col-md-8">
            <div class="p-3 py-5">
                <form id="formId" method="POST" action="${pageContext.request.contextPath}/user/edit"
                      onsubmit="return editProfile()">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h6 class="text-right">Змінити профіль</h6>
                    </div>
                    <div class="row mt-2">
                        <div class="col-md-6">
                            <label for="firstName">Ім'я
                                <input type="text" class="form-control" id="firstName" name="firstName"
                                       value="${userInfo.firstName}">
                            </label>
                        </div>
                        <div class="col-md-6">
                            <label for="lastName">Прізвище
                                <input type="text" class="form-control" id="lastName" name="lastName"
                                       value="${userInfo.lastName}">
                            </label>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-6">
                            <label for="email">Електронна пошта
                                <input type="text" class="form-control" id="email" name="email"
                                       value="${userInfo.email}">
                            </label>
                        </div>
                        <div class="col-md-6">
                            <label for="phoneNumber">Номер телефона
                                <input type="text" class="form-control" id="phoneNumber" name="phoneNumber"
                                       value="${userInfo.phoneNumber}">
                            </label>
                        </div>
                    </div>
                    <c:if test="${userType == 'ВЕТЕРИНАР'}">
                        <div class="row mt-3">
                            <div class="col-md-6">
                                <label for="dateEmployed">Дата початку роботи
                                    <input type="text" class="form-control" id="dateEmployed" name="dateEmployed"
                                           value="${userInfo.dateEmployed}">
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="area">Спеціалізація
                                    <input type="text" class="form-control" id="area" name="area"
                                           value="${userInfo.area}">
                                </label>
                            </div>
                        </div>
                    </c:if>

                    <div class="mt-5 text-right">
                        <button id="submitButton" class="btn profile-button" style="background-color: #1985a1; color: white" type="submit">
                            Зберегти зміни
                        </button>
                    </div>
                </form>
                <c:if test="${userType == 'ВЛАСНИК'}">
                    <form action="${pageContext.request.contextPath}/pet">
                        <button class="btn " style="background-color: #1985a1; color: white" type="submit">Переглянути моїх тварин</button>
                    </form>
                </c:if>
            </div>
        </div>
    </div>
</div>


</html>
<jsp:include page="../views/footer.jsp"/>


<script>
    function editProfile() {
        const firstName = document.getElementById("firstName").value;
        const lastName = document.getElementById("lastName").value;
        const email = document.getElementById("email").value;
        const phoneNumber = document.getElementById("phoneNumber").value;
        const dateEmployed = document.getElementById("dateEmployed").value;
        const area = document.getElementById("area").value;

        let success = false;
        $(() => {
            // function will get executed
            // on click of submit button
            $("#submitButton").click(function (ev) {
                var form = $("#formId");
                var url = form.attr('action');
                $.ajax({
                    type: "POST",
                    url: url,
                    data: {
                        firstName: firstName,
                        lastName: lastName,
                        email: email,
                        phoneNumber: phoneNumber,
                        dateEmployed: dateEmployed,
                        area: area
                    }
                }).done(function () {
                    alert("Ваш запит створено успішно!")
                    success = true;
                }).fail(function (response) {
                    success = false;
                    if (response.status === 400) {
                        alert("Щось пішло не так!");
                    }
                });

                return success;
            })
        });
    }
</script>

