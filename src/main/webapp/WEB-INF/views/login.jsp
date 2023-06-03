<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Вхід в систему</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js">
    </script>
    <link href="<c:url value="../../resources/css/login.css"/>" rel="stylesheet" type="text/css">
</head>
<body style="background-color: #E0FFFF">
<jsp:include page="../views/header.jsp"/>
<section style="margin-top: 5rem" class="vh-130">
    <div class="container-fluid h-custom">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-9 col-lg-6 col-xl-5">
                <img style="border-radius: 50%;" src="../../resources/images/military_2.jpg"
                     class="img-fluid" alt="Sample image">
            </div>
            <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                <h1 class="title">Вхід в систему</h1>
                <form method="POST" action="${pageContext.request.contextPath}/login" autocomplete="off">
                    <!-- Email input -->
                    <div class="form-outline mb-4">
                        <input type="text" id="username" name="username" class="form-control form-control-lg"
                               placeholder="Псевдонім" required/>
                    </div>

                    <!-- Password input -->
                    <div class="form-outline mb-3">
                        <input type="password" id="password" name="password" class="form-control form-control-lg"
                               placeholder="Пароль" required/>
                    </div>


                    <div class="text-center text-lg-start mt-4 pt-2">
                        <button type="submit" class="button-new">Увійти</button>
                        <p class="small fw-bold mt-2 pt-1 mb-0"><b>Вперше на сайті ?</b> <a href="/registration"
                                                                                            class="link-danger"><b>Зареєструйтесь</b></a>
                        </p>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
</body>
</html>
<jsp:include page="../views/footer.jsp"/>