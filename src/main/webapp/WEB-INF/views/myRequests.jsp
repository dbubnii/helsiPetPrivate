<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Мої записи</title>
    <style>
        table {
            counter-reset: tableCount;
        }

        .counterCell:before {
            content: counter(tableCount);
            counter-increment: tableCount;
        }</style>
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js">
    </script>
    <link href="<c:url value="../../resources/css/myRequest.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="../views/header.jsp"/>
<div class="table-wrapper">
    <table class="fl-table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">ПІБ Ветеринара</th>
            <th scope="col">ПІБ Власника</th>
            <th scope="col">Кличка тарини</th>
            <th scope="col">Вибрана дата запису</th>
            <th scope="col">Деталі</th>
            <th scope="col">Статус запису</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <c:forEach items="${appointments}" var="appointment">
            <tbody>
            <tr>
                <th class="counterCell" scope="row"></th>
                <td>${appointment.vet.firstName} ${appointment.vet.lastName}</td>
                <td>${appointment.owner.firstName} ${appointment.owner.lastName}</td>
                <td>${appointment.animal.name}</td>
                <td>${appointment.vetAvailableDate}</td>
                <td>${appointment.details}</td>
                <td>${appointment.status}</td>
                <c:if test="${userType == 'ВЛАСНИК'}">
                    <c:if test="${appointment.receiptId != null}">
                        <td>
                            <div class="row">
                                <form action="${pageContext.request.contextPath}/receipt/get/${appointment.receiptId}">
                                    <button class="btn" style="background-color:#1985a1; color:white;" type="submit">
                                        Переглянути рецепт
                                    </button>
                                </form>
                            </div>
                        </td>
                    </c:if>
                </c:if>
                <c:if test="${userType == 'ВЕТЕРИНАР'}">
                    <c:if test="${appointment.status == 'ОЧІКУЄТЬСЯ'}">
                        <td>
                            <div class="row">
                                <div class="column">
                                    <button type="button" class="btn btn-info" style="margin-right: 10px"
                                            onclick="confirmRequest(${appointment.id}, '${appointment.owner.usersUsername}')">
                                        Підтвердити запис
                                    </button>
                                </div>
                                <div class="column">
                                    <button type="button" class="btn btn-secondary"
                                            onclick="rejectRequest(${appointment.id}, '${appointment.owner.usersUsername}')">
                                        Відмінити запис
                                    </button>
                                </div>
                            </div>
                        </td>

                    </c:if>
                    <c:if test="${appointment.status == 'ПІДТВЕРДЖЕНО'}">
                        <td>
                            <div class="row">
                                <div class="column">
                                    <form action="${pageContext.request.contextPath}/pet/profile/byName/${appointment.animal.name}/${appointment.owner.usersUsername}">
                                        <button class="btn" type="submit"
                                                style="background-color:#1985a1; color:white; margin-right: 10px">
                                            Переглянути профіль тварини
                                        </button>
                                    </form>
                                </div>
                                <br>
                                <div class="column">
                                    <button type="button" class="btn btn-info"
                                            onclick="completeRequest(${appointment.id}, '${appointment.owner.usersUsername}', '${appointment.vet.usersUsername}', '${appointment.animal.name}')">
                                        Позначити як виконане
                                    </button>
                                </div>
                            </div>
                        </td>
                    </c:if>
                    <c:if test="${appointment.status == 'ВИКОНАНО'}">
                        <c:if test="${appointment.receiptId == null}">
                            <td>
                                <div class="row">
                                    <div class="column">
                                        <form action="${pageContext.request.contextPath}/pet/profile/byName/${appointment.animal.name}/${appointment.owner.usersUsername}">
                                            <button class="btn"
                                                    style="margin-right: 10px; background-color:#1985a1; color:white"
                                                    type="submit">
                                                Переглянути профіль тварини
                                            </button>
                                        </form>
                                    </div>
                                    <div class="column">
                                        <form action="${pageContext.request.contextPath}/receipt/${appointment.id}">
                                            <button class="btn btn-info" type="submit">Додати рецепт</button>
                                        </form>
                                    </div>
                                </div>
                            </td>
                        </c:if>
                        <c:if test="${appointment.receiptId != null}">
                            <td>
                                <div class="row">
                                    <div class="column">
                                        <form action="${pageContext.request.contextPath}/pet/profile/byName/${appointment.animal.name}/${appointment.owner.usersUsername}">
                                            <button class="btn" type="submit" style="background-color:#1985a1; color:white; margin-right: 10px">
                                                Переглянути профіль тварини
                                            </button>
                                        </form>
                                    </div>
                                    <div class="column">
                                        <form action="${pageContext.request.contextPath}/receipt/get/${appointment.receiptId}">
                                            <button class="btn btn-info" type="submit">Переглянути рецепт</button>
                                        </form>
                                    </div>
                                </div>
                            </td>
                        </c:if>
                    </c:if>
                </c:if>
            </tr>
            <tr style="border-bottom:1px solid black">
                <td colspan="100%"></td>
            </tr>
            </tbody>
        </c:forEach>
    </table>
</div>
</body>
</html>
<jsp:include page="../views/footer.jsp"/>

<script>
    function confirmRequest(requestId, ownerUsername) {
        $.ajax({
            type: "POST",
            async: false,
            url: "/request/confirm",
            data: {
                ownerUsername: ownerUsername,
                requestId: requestId
            }
        }).done(function () {
            alert("Запит успішно опрацьовано!");
        }).fail(function () {
            alert("Щось пішло не так!");
        });
    }

    function rejectRequest(requestId, ownerUsername) {
        $.ajax({
            type: "POST",
            async: false,
            url: "/request/reject",
            data: {
                ownerUsername: ownerUsername,
                requestId: requestId
            }
        }).done(function () {
            alert("Запит успішно опрацьовано!");
        }).fail(function () {
            alert("Щось пішло не так!");
        });
    }

    function completeRequest(requestId, ownerUsername, vetUsername, petName) {
        $.ajax({
            type: "POST",
            async: false,
            url: "/request/complete",
            data: {
                ownerUsername: ownerUsername,
                vetUsername: vetUsername,
                petName: petName,
                requestId: requestId
            }
        }).done(function () {
            alert("Запит успішно опрацьовано!");
        }).fail(function () {
            alert("Щось пішло не так!");
        });
    }
</script>