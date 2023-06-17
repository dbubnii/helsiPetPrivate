<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Профіль</title>
    <link href="<c:url value="../../resources/css/vetProfile.css"/>" rel="stylesheet" type="text/css">

</head>
<body style="background-color: #E0FFFF">
<jsp:include page="../views/header.jsp"/>
<div class="container mt-4">
    <div class="row d-flex justify-content-center">
        <div class="col-md-7">
            <div class="card p-3 py-4" style="font-size: 15px; height: 500px; width: 1000px; margin-top: 50px; margin-left: -20%">
                <div class="text-center">
                    <img src="https://i.imgur.com/bDLhJiP.jpg" width="100" class="rounded-circle" alt="">
                </div>

                <div class="text-center mt-3">
                    <span class="bg-secondary p-1 px-4 rounded text-white">ВЕТЕРИНАР</span>
                    <h5 class="mt-2 mb-0">${vet.firstName} ${vet.lastName}</h5>

                    <div class="px-4 mt-1">
                        <p class="fonts">текст про ветеринара</p>

                    </div>

                    <div class="buttons">
                        <form action="${pageContext.request.contextPath}/appointment/form/${vet.usersUsername}">
                            <button class="btn btn-outline-primary px-4" type="submit">Записатись на прийом</button>
                        </form>
                        <div class="px-4 mt-1">
                            <p class="fonts">Електронна пошта: ${vet.email} <br> Моб.тел.: ${vet.phoneNumber} </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
<jsp:include page="../views/footer.jsp"/>