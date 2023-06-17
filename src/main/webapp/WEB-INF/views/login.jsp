<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Вхід в систему</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js">
    </script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <%--    <link href="<c:url value="../../resources/css/login.css"/>" rel="stylesheet" type="text/css">--%>
</head>
<jsp:include page="../views/header.jsp"/>
<body>
    <!-- Body content goes here -->
    <section class="vh-100 gradient-custom" style="background-color: white">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                    <div class="card text-white" style="border-radius: 1rem;background-color:#1985a1 ">
                        <div class="card-body p-5 text-center">

                            <form method="POST" action="${pageContext.request.contextPath}/login">
                                <div class="mb-md-5 mt-md-4 pb-5">

                                    <h2 class="fw-bold mb-2 text-uppercase">Вхід</h2>
                                    <p class="text-white-50 mb-5"><b>Будь ласка, введіть свій логін та пароль!</b></p>

                                    <div class="form-outline form-white mb-4">
                                        <input type="text" id="username" name="username"
                                               class="form-control form-control-lg"
                                               placeholder="Псевдонім" required/>
                                        <label class="form-label" for="username">Псевдонім</label>
                                    </div>

                                    <div class="form-outline form-white mb-4">
                                        <input type="password" id="password" name="password"
                                               class="form-control form-control-lg"
                                               placeholder="Пароль" required/>
                                        <label class="form-label" for="password">Пароль</label>
                                    </div>

                                    <button class="btn btn-outline-light btn-lg px-5" type="submit">Ввійти</button>

                                </div>

                                <div>
                                    <p class="mb-0"><b>Вперше на сайті?</b>
                                        <a href="/registration" class="text-white-50 fw-bold"><b>Зареєструйтесь</b></a>
                                    </p>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
<jsp:include page="../views/footer.jsp"/>
</body>
</html>
