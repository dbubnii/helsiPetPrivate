<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Реєстрація</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js">
    </script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link href="<c:url value="../../resources/css/registration.css"/>" rel="stylesheet" type="text/css">

</head>
<body>
<jsp:include page="../views/header.jsp"/>
<div class="container">
    <div class="title">Зареєструйтесь</div>
    <div class="content">
        <form method="POST" action="${pageContext.request.contextPath}/registration">
            <div class="user-details">
                <div class="input-box">
                    <span class="details">Ім'я</span>
                    <input type="text" id="firstname" name="firstname" placeholder="Введіть ваше ім'я" required>
                </div>
                <div class="input-box">
                    <span class="details">Прізвище</span>
                    <input type="text" id="lastname" name="lastname" placeholder="Введіть ваше прізвище" required>
                </div>
                <div class="input-box">
                    <span class="details">Пошта</span>
                    <input type="text" id="email" name="email" placeholder="Введіть вашу пошту" required>
                </div>
                <div class="input-box">
                    <span class="details">Псевдонім</span>
                    <input type="text" id="username" name="username" placeholder="Введіть ваш псевдонім" required>
                </div>
                <div class="input-box">
                    <span class="details">Номер телефона</span>
                    <input type="text" id="phoneNumber" name="phoneNumber" placeholder="Введіть ваш номер телефона" required>
                </div>
                <div class="input-box" id="dateEmployedBox">
                    <span class="details">Дата початку роботи</span>
                    <input type="text" id="dateEmployed" name="dateEmployed" placeholder="Введіть дату початку роботи">
                </div>
                <div class="input-box" id="areaBox">
                    <span class="details">Спеціалізація</span>
                    <input type="text" id="area" name="area" placeholder="Введіть вашу спеціалізацію">
                </div>
                <div class="input-box">
                    <span class="details">Пароль</span>
                    <input type="password" id="password" name="password" placeholder="Введіть пароль" required>
                </div>
                <div class="input-box">
                    <span class="details">Підтвердження Паролю</span>
                    <input type="password" id="passwordConfirm" name="passwordConfirm" placeholder="Введіть пароль ще раз"
                           required>
                </div>
            </div>
            <div class="gender-details">
                <select class="form-select" aria-label="Default select example" name="userType" id="userType">
                    <option value="ВЛАСНИК">ВЛАСНИК</option>
                    <option value="ВЕТЕРИНАР">ВЕТЕРИНАР</option>
                </select>
            </div>
            <div class="button">
                <input type="submit" value="Зареєструватись">
            </div>
        </form>
        <div>
            <p class="mb-0"><b>Вже зареєстровані?</b>
                <a href="/login" class="text-black-50 fw-bold"><b>Увійдіть в систему</b></a>
            </p>
        </div>
    </div>
</div>

<%--<section class="vh-100">--%>
<%--    <div class="container py-5 h-100">--%>
<%--        <div class="row d-flex justify-content-center align-items-center h-100">--%>
<%--            <div class="col-12 col-md-8 col-lg-6 col-xl-5">--%>
<%--                <div class="card text-white" style="border-radius: 1rem;background-color:#5c7bbc ">--%>
<%--                    <div class="card-body p-5 text-center">--%>

<%--                        <h2 class="fw-bold mb-2 text-uppercase">Реєстрація</h2>--%>
<%--                        <p class="text-white-50 mb-5"><b>Будь ласка, введіть свій логін та пароль!</b></p>--%>

<%--                        <form method="POST" action="${pageContext.request.contextPath}/registration" autocomplete="off"--%>
<%--                              style="font-size: 20px">--%>
<%--                            <!-- Name input -->--%>

<%--                            <label for="userType">Виберіть тип користувача: </label>--%>
<%--                            <div class="form-outline form-white mb-4">--%>
<%--                                <select class="form-select" aria-label="Default select example" name="userType"--%>
<%--                                        id="userType">--%>
<%--                                    <option value="ВЛАСНИК">ВЛАСНИК</option>--%>
<%--                                    <option value="ВЕТЕРИНАР">ВЕТЕРИНАР</option>--%>
<%--                                </select>--%>
<%--                            </div>--%>

<%--                            <div class="row">--%>
<%--                                <div class="col-md-6 mb-4">--%>
<%--                                    <div class="form-outline form-white mb-4">--%>
<%--                                        <label for="firstname">Введіть Ім'я користувача--%>
<%--                                            <input type="text" id="firstname" name="firstname"--%>
<%--                                                   class="form-control form-control-lg"--%>
<%--                                                   placeholder="Ім'я" required/>--%>
<%--                                        </label>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                                <div class="col-md-6 mb-4">--%>
<%--                                    <div class="form-outline form-white mb-4">--%>
<%--                                        <label for="lastname">Введіть Прізвище користувача--%>
<%--                                            <input type="text" id="lastname" name="lastname"--%>
<%--                                                   class="form-control form-control-lg"--%>
<%--                                                   placeholder="Прізвище" required/>--%>
<%--                                        </label>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="row">--%>
<%--                                <div class="col-md-6 mb-4">--%>
<%--                                    <div class="form-outline form-white mb-4">--%>
<%--                                        <label for="username">Введіть псевдонім--%>
<%--                                            <input type="text" id="username" name="username"--%>
<%--                                                   class="form-control form-control-lg"--%>
<%--                                                   placeholder="Псевдонім" required/>--%>
<%--                                        </label>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                                <div class="col-md-6 mb-4">--%>
<%--                                    <div class="form-outline form-white mb-4">--%>
<%--                                        <label for="email">Введіть електронну пошту--%>
<%--                                            <input type="email" id="email" name="email"--%>
<%--                                                   class="form-control form-control-lg"--%>
<%--                                                   placeholder="Пошта" required/>--%>
<%--                                        </label>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="row">--%>
<%--                                <div class="col-md-6 mb-4">--%>
<%--                                    <div class="form-outline form-white mb-4">--%>
<%--                                        <label for="phoneNumber">Введіть номер телефона--%>
<%--                                            <input type="text" id="phoneNumber" name="phoneNumber"--%>
<%--                                                   class="form-control form-control-lg"--%>
<%--                                                   placeholder="Номер телефону" required/>--%>
<%--                                        </label>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                                <div class="col-md-6 mb-4">--%>
<%--                                    <div class="form-outline form-white mb-4">--%>
<%--                                        <label id="dateEmployedLabel" for="dateEmployed">Коли ви почали працювати тут ?--%>
<%--                                            <input type="date" id="dateEmployed" name="dateEmployed"--%>
<%--                                                   class="form-control form-control-lg"--%>
<%--                                                   placeholder="Початок роботи"/>--%>
<%--                                        </label>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>

<%--                            <div class="row">--%>
<%--                                <div class="col-md-6 mb-4">--%>

<%--                                    <div class="form-outline form-white mb-4">--%>
<%--                                        <label id="areaLabel" for="area">Введіть вашу спеціалізацію--%>
<%--                                            <input type="text" id="area" name="area"--%>
<%--                                                   class="form-control form-control-lg"--%>
<%--                                                   placeholder="Спеціалізація"/>--%>
<%--                                        </label>--%>
<%--                                    </div>--%>
<%--                                </div>--%>

<%--                                <div class="col-md-6 mb-4">--%>

<%--                                    <!-- Password input -->--%>
<%--                                    <div class="form-outline form-white mb-4">--%>
<%--                                        <label class="form-label" for="password">Пароль</label>--%>
<%--                                        <input type="password" id="password" name="password"--%>
<%--                                               class="form-control form-control-lg"--%>
<%--                                               placeholder="Пароль" required/>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                                <div class="col-md-6 mb-4">--%>

<%--                                    <div class="form-outline form-white mb-4">--%>
<%--                                        <label class="form-label" for="password">Введіть пароль ще раз--%>
<%--                                            <input type="password" id="passwordConfirm" name="passwordConfirm"--%>
<%--                                                   class="form-control form-control-lg"--%>
<%--                                                   placeholder="Введіть пароль ще раз, будь ласка" required/>--%>
<%--                                        </label>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>

<%--                            <button class="btn btn-outline-light btn-lg px-5" type="submit">Зареєструватись</button>--%>

<%--                            <div>--%>
<%--                                <p class="mb-0"><b>Вже зареєстровані?</b>--%>
<%--                                    <a href="/login" class="text-white-50 fw-bold"><b>Увійдіть в систему</b></a>--%>
<%--                                </p>--%>
<%--                            </div>--%>
<%--                        </form>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</section>--%>
</body>
</html>
<jsp:include page="../views/footer.jsp"/>

<script>
    $(document).ready(function () {
        toggleInput();
        $("#userType").change(function () {
            toggleInput();
        });
    });

    function toggleInput() {
        if ($("#userType").val() === "ВЕТЕРИНАР") {
            $("#firstname").show();
            $("#lastname").show();
            $("#username").show();
            $("#email").show();
            $("#phoneNumber").show();
            $("#password").show();
            $("#passwordConfirm").show();
            $("#dateEmployed").show();
            $("#area").show();
            $("#dateEmployedBox").show();
            $("#areaBox").show();
        } else {
            $("#firstname").show();
            $("#lastname").show();
            $("#username").show();
            $("#email").show();
            $("#phoneNumber").show();
            $("#password").show();
            $("#passwordConfirm").show();
            $("#dateEmployed").hide();
            $("#area").hide();
            $("#dateEmployedBox").hide();
            $("#areaBox").hide();
        }
    }
</script>