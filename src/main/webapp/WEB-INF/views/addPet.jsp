<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Додати тварину</title>
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js">
    </script>
    <link href="<c:url value="../../resources/css/addPet.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="../views/header.jsp"/>
<div class="container">
    <div class="title">Додайте тварину</div>
    <div class="content">
        <form method="POST" action="${pageContext.request.contextPath}/">
            <div class="user-details">
                <div class="input-box">
                    <span class="details">Як звати тварину</span>
                    <input type="text" id="name" name="name" placeholder="Введіть кличку тварини" required>
                </div>
                <div class="input-box">
                    <span class="details">Вкажіть породу</span>
                    <input type="text" id="breed" name="breed" placeholder="Введіть породу тварини" required>
                </div>
                <div class="input-box">
                    <span class="details">Вкажіть вік</span>
                    <input type="text" id="age" name="age" placeholder="Введіть вік тварини" required>
                </div>
                <div class="input-box">
                    <span class="details">Оберіть стать тварини</span>
                    <select name="petSex" id="petSex" style="height: 45px; width: 630px">
                        <option value=""></option>
                        <c:forEach items="${petSex}" var="sex">
                            <option value="${sex}">${sex}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="input-box" id="dateEmployedBox">
                    <span class="details">Виберіть розмір тварини</span>
                    <select name="petSize" id="petSize" style="height: 45px; width: 630px">
                        <option value=""></option>
                        <c:forEach items="${petSize}" var="size">
                            <option value="${size}">${size}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="input-box" id="areaBox">
                    <span class="details">Вкажіть вагу тварини</span>
                    <input type="text" id="weight" name="weight" placeholder="Введіть вагу тварини" required>
                </div>
                <div class="input-box">
                    <span class="details">Вкажіть чи тварина стерилізована</span>
                    <select name="sterilized" id="sterilized" style="height: 45px; width: 630px">
                        <option value=""></option>
                        <option value="ТАК">ТАК</option>
                        <option value="НІ">НІ</option>
                    </select>
                </div>
                <div class="input-box">
                    <span class="details">Оберіть тип тварини</span>
                    <select name="petType" id="petType" style="height: 45px; width: 630px">
                        <option value=""></option>
                        <c:forEach items="${petType}" var="type">
                            <option value="${type}">${type}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="input-box">
                    <span class="details">Вкажіть деталі про які нам варто знати</span>
                    <input type="text" id="details" name="details" placeholder="  Вкажіть важливі деталі" style="height: 45px; width: 630px" required>
                </div>
            </div>

            <c:if test="${empty owner}">
                <div class="input-box">
                    <span class="details">Власник тварини</span>
                    <input type="text" id="owner" name="owner" placeholder="Вкажіть власника тварини" class="form-control form-control-lg"/>
                </div>
            </c:if>

            <c:if test="${empty owner == false}">
                <div class="input-box" style="margin-top: 3px">
                    <span class="details">Власник тварини</span>
                    <input type="text" class="form-control form-control-lg" value="${owner.firstName} ${owner.lastName}"
                           readonly/>
                </div>
                <input type="hidden" id="ownerUsername" name="ownerUsername" value="${owner.usersUsername}"/>
            </c:if>
            <div class="button" style="background-color: #1985a1">
                <input type="submit" onclick="addPet()" value="Додати тварину">
            </div>
        </form>
        <div>
            <p class="mb-0"><b>Профіль тварини вже існує?</b>
                <a href="/pet/addByUniqueID" class="text-black-50 fw-bold"><b>Додати тварину за унікальним кодом</b></a>
            </p>
        </div>
    </div>
</div>

<%--<section class="vh-100">--%>
<%--    <div class="container-fluid h-custom">--%>
<%--        <div class="row d-flex justify-content-center align-items-center h-100">--%>
<%--            <div class="col-md-9 col-lg-6 col-xl-5">--%>
<%--                <a href="/pet/addByUniqueID" class="btn btn-info btn-lg">--%>
<%--                    <span class="glyphicon glyphicon-plus-sign"></span> Додати тварину за унікальним кодом--%>
<%--                </a>--%>
<%--            </div>--%>
<%--            <div style="margin-top: 5rem" class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">--%>
<%--                <h1 class="title">Заповніть дані про тварину</h1>--%>
<%--                <br>--%>
<%--                <form method="POST" action="${pageContext.request.contextPath}/" autocomplete="off">--%>
<%--                    <div class="form-outline mb-4">--%>
<%--                        <label for="name">Як звати тварину--%>
<%--                            <input type="text" id="name" name="name" class="form-control form-control-lg" required/>--%>
<%--                        </label>--%>
<%--                    </div>--%>
<%--                    <div class="form-outline mb-4">--%>
<%--                        <label for="breed">Вкажіть породу--%>
<%--                            <input type="text" id="breed" name="breed" class="form-control form-control-lg" required/>--%>
<%--                        </label>--%>
<%--                    </div>--%>

<%--                    <div class="form-outline mb-4">--%>
<%--                        <label for="age">Вкажіть вік--%>
<%--                            <input type="number" id="age" name="age" class="form-control form-control-lg"/>--%>
<%--                        </label>--%>
<%--                    </div>--%>

<%--                    <div class="form-outline mb-4">--%>
<%--                        <label for="petSex">Оберіть стать тварини--%>
<%--                            <select name="petSex" id="petSex">--%>
<%--                                <option value=""></option>--%>
<%--                                <c:forEach items="${petSex}" var="sex">--%>
<%--                                    <option value="${sex}">${sex}</option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>
<%--                        </label>--%>
<%--                    </div>--%>
<%--                    <div class="form-outline mb-4">--%>
<%--                        <label for="petSize">Виберіть розмір тварини--%>
<%--                            <select name="petSize" id="petSize">--%>
<%--                                <option value=""></option>--%>
<%--                                <c:forEach items="${petSize}" var="size">--%>
<%--                                    <option value="${size}">${size}</option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>
<%--                        </label>--%>
<%--                    </div>--%>
<%--                    <div class="form-outline mb-4">--%>
<%--                        <label for="weight">Вкажіть вагу тварини--%>
<%--                            <input type="text" id="weight" name="weight" class="form-control form-control-lg" required/>--%>
<%--                        </label>--%>
<%--                    </div>--%>

<%--                    <div class="form-outline mb-4">--%>
<%--                        <label for="sterilized">Вкажіть чи тварина стерилізована--%>
<%--                            <select name="sterilized" id="sterilized">--%>
<%--                                <option value=""></option>--%>
<%--                                <option value="ТАК">ТАК</option>--%>
<%--                                <option value="НІ">НІ</option>--%>
<%--                            </select>--%>
<%--                        </label>--%>
<%--                    </div>--%>

<%--                    <div class="form-outline mb-4">--%>
<%--                        <label for="petType">Оберіть до якого типу віднести Вашу тварину--%>
<%--                            <select name="petType" id="petType">--%>
<%--                                <option value=""></option>--%>
<%--                                <c:forEach items="${petType}" var="type">--%>
<%--                                    <option value="${type}">${type}</option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>
<%--                        </label>--%>
<%--                    </div>--%>

<%--                    <div class="form-outline mb-4">--%>
<%--                        <label for="details">Вкажіть деталі про які нам варто знати--%>
<%--                            <input type="text" id="details" name="details" class="form-control form-control-lg"/>--%>
<%--                        </label>--%>
<%--                    </div>--%>

<%--                    <c:if test="${empty owner}">--%>
<%--                        <div class="form-outline mb-4">--%>
<%--                            <label for="owner">Власник тварини--%>
<%--                                <input type="text" id="owner" name="owner" class="form-control form-control-lg"/>--%>
<%--                            </label>--%>
<%--                        </div>--%>
<%--                    </c:if>--%>

<%--                    <c:if test="${empty owner == false}">--%>
<%--                        <div class="form-outline mb-4">--%>
<%--                            <label for="owner">Власник тварини--%>
<%--                                <input type="text" class="form-control form-control-lg"--%>
<%--                                       value="${owner.firstName} ${owner.lastName}" readonly/>--%>
<%--                            </label>--%>
<%--                        </div>--%>
<%--                        <input type="hidden" id="ownerUsername" name="ownerUsername"--%>
<%--                               value="${owner.usersUsername}"/>--%>
<%--                    </c:if>--%>

<%--                    <div class="text-center text-lg-start mt-4 pt-2">--%>
<%--                        <button type="submit" class="button-new" onclick="addPet()">Додати тварину</button>--%>
<%--                    </div>--%>
<%--                </form>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</section>--%>
</body>
</html>
<jsp:include page="../views/footer.jsp"/>

<script>
    function addPet() {
        const name = document.getElementById("name").value;
        const breed = document.getElementById("breed").value;
        const petSex = document.getElementById("petSex").value;
        const age = document.getElementById("age").value;
        const petSize = document.getElementById("petSize").value;
        const weight = document.getElementById("weight").value;
        const sterilized = document.getElementById("sterilized").value;
        const petType = document.getElementById("petType").value;
        const details = document.getElementById("details").value;
        const ownerUsername = document.getElementById("ownerUsername").value;

        $.ajax({
            type: "POST",
            async: false,
            url: "/pet/add",
            data: {
                name: name,
                breed: breed,
                petSex: petSex,
                age: age,
                sterilized: sterilized,
                petSize: petSize,
                weight: weight,
                petType: petType,
                details: details,
                ownerUsername: ownerUsername
            }
        }).done(function () {
            alert("Тварину успішно додано!");
        }).fail(function () {
            alert("Щось пішло не так!");
        });
    }
</script>