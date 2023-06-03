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
<body style="background-color: #E0FFFF">
<jsp:include page="../views/header.jsp"/>
<section class="vh-100">
    <div class="container-fluid h-custom">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-9 col-lg-6 col-xl-5">
                <img style="border-radius: 50%;" src="../../resources/images/photo_1.jpg"
                     class="img-fluid" alt="Sample image">
            </div>
            <div style="margin-top: 5rem" class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                <h1 class="title">Форма реєстрації</h1>
                <form method="POST" action="${pageContext.request.contextPath}/registration" autocomplete="off">
                    <!-- Name input -->
                    <label for="userType"></label>
                    <select name="userType" id="userType">
                        <option value="ВЛАСНИК">ВЛАСНИК</option>
                        <option value="ВЕТЕРИНАР">ВЕТЕРИНАР</option>
                    </select>

                    <div class="form-outline mb-4">
                        <input type="text" id="firstname" name="firstname" class="form-control form-control-lg"
                               placeholder="Ім'я" required/>
                    </div>
                    <div class="form-outline mb-4">
                        <input type="text" id="lastname" name="lastname" class="form-control form-control-lg"
                               placeholder="Прізвище" required/>
                    </div>
                    <div class="form-outline mb-4">
                        <input type="text" id="username" name="username" class="form-control form-control-lg"
                               placeholder="Псевдонім" required/>
                    </div>
                    <div class="form-outline mb-4">
                        <input type="email" id="email" name="email" class="form-control form-control-lg"
                               placeholder="Пошта" required/>
                    </div>
                    <div class="form-outline mb-4">
                        <input type="text" id="phoneNumber" name="phoneNumber" class="form-control form-control-lg"
                               placeholder="Номер телефону" required/>
                    </div>

                    <div class="form-outline mb-4">
                        <input type="text" id="dateEmployed" name="dateEmployed" class="form-control form-control-lg"
                               placeholder="Початок роботи"/>
                    </div>

                    <div class="form-outline mb-4">
                        <input type="text" id="area" name="area" class="form-control form-control-lg"
                               placeholder="Спеціалізація"/>
                    </div>

                    <!-- Password input -->
                    <div class="form-outline mb-3">
                        <label class="form-label" for="password">Пароль</label>
                        <input type="password" id="password" name="password" class="form-control form-control-lg"
                               placeholder="Пароль" required/>
                    </div>
                    <div class="form-outline mb-3">
                        <label class="form-label" for="password">Введіть пароль ще раз</label>
                        <input type="password" id="passwordConfirm" name="passwordConfirm"
                               class="form-control form-control-lg"
                               placeholder="Введіть пароль ще раз, будь ласка" required/>
                    </div>


                    <div class="text-center text-lg-start mt-4 pt-2">
                        <button type="submit" class="button-new">Зареєструватись</button>
                        <p class="small fw-bold mt-2 pt-1 mb-0"><b>Вже зареєстровані ?</b> <a href="/login"
                                                                                              class="link-danger"><b>Увійдіть
                            в систему</b></a></p>
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
        }
    }
</script>