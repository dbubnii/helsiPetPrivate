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
            background: #BA68C8;
        }

        .form-control:focus {
            box-shadow: none;
            border-color: #BA68C8;
        }

        .profile-button {
            background: #BA68C8;
            box-shadow: none;
            border: none;
        }

        .profile-button:hover {
            background: #682773;
        }

        .profile-button:focus {
            background: #682773;
            box-shadow: none;
        }

        .profile-button:active {
            background: #682773;
            box-shadow: none;
        }

        .back:hover {
            color: #682773;
            cursor: pointer;
        }
    </style>
</head>
<body style="background-color: #E0FFFF">
<jsp:include page="../views/header.jsp"/>
<div class="container rounded bg-white mt-5">
    <div class="row">
        <div class="col-md-4 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5"
                                                                                         src="https://i.imgur.com/0eg0aG0.jpg"
                                                                                         width="90">
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
                        <div class="col-md-6"><input type="text" class="form-control" placeholder="Ім'я" id="firstName"
                                                     name="firstName" value="${userInfo.firstName}"></div>
                        <div class="col-md-6"><input type="text" class="form-control" placeholder="Прізвище"
                                                     id="lastName" name="lastName" value="${userInfo.lastName}"></div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-6"><input type="text" class="form-control" placeholder="Пошта" id="email"
                                                     name="email" value="${userInfo.email}"></div>
                        <div class="col-md-6"><input type="text" class="form-control" placeholder="Номер телефону"
                                                     id="phoneNumber" name="phoneNumber"
                                                     value="${userInfo.phoneNumber}"></div>
                    </div>
                    <c:if test="${userType == 'ВЕТЕРИНАР'}">

                        <div class="row mt-3">
                            <div class="col-md-6"><input type="text" class="form-control"
                                                         placeholder="Дата початку роботи"
                                                         id="dateEmployed" name="dateEmployed"
                                                         value="${userInfo.dateEmployed}"></div>
                            <div class="col-md-6"><input type="text" class="form-control" placeholder="Спеціалізація"
                                                         id="area" name="area"
                                                         value="${userInfo.area}"></div>
                        </div>
                    </c:if>

                    <div class="mt-5 text-right">
                        <button id="submitButton" class="btn btn-primary profile-button" type="submit">Зберегти зміни
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<c:if test="${userType == 'ВЛАСНИК'}">
<div class="d-flex flex-row align-items-center back"><i class="fa fa-long-arrow-left mr-1 mb-1"></i>
    <a href="${pageContext.request.contextPath}/pet"><b>Переглянути моїх тварин</b></a>
</div>
</c:if>
<c:if test="${userType == 'ВЕТЕРИНАР'}">
    <div class="d-flex flex-row align-items-center back"><i class="fa fa-long-arrow-left mr-1 mb-1"></i>
        <a href="${pageContext.request.contextPath}/pet/forVet/${userInfo.id}"><b>Переглянути моїх пацієнтів</b></a>
    </div>
</c:if>

</body>
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

