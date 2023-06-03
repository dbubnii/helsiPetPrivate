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
    <link href="<c:url value="../../resources/css/cards.css"/>" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="../views/header.jsp"/>
<table class="table table-hover">
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
                                <button class="btn btn-primary" type="submit">Переглянути рецепт</button>
                            </form>
                        </div>
                    </td>
                </c:if>
            </c:if>
            <c:if test="${userType == 'ВЕТЕРИНАР'}">
                <c:if test="${appointment.status == 'ОЧІКУЄТЬСЯ'}">
                    <td>
                        <div class="row">
                            <button type="button" class="btn btn-primary" onclick="confirmRequest(${appointment.id})">
                                Підтвердити запис
                            </button>
                            <button type="button" class="btn btn-primary" onclick="rejectRequest(${appointment.id})">
                                Відмінити запис
                            </button>
                        </div>
                    </td>
                </c:if>
                <c:if test="${appointment.status == 'ПІДТВЕРДЖЕНО'}">
                    <td>
                        <div class="row">
                            <button type="button" class="btn btn-primary" onclick="completeRequest(${appointment.id})">
                                Позначити як виконане
                            </button>
                        </div>
                    </td>
                </c:if>
                <c:if test="${appointment.status == 'ВИКОНАНО'}">
                    <c:if test="${appointment.receiptId == null}">
                        <td>
                            <div class="row">
                                <form action="${pageContext.request.contextPath}/receipt/${appointment.id}">
                                    <button class="btn btn-primary" type="submit">Додати рецепт</button>
                                </form>
                            </div>
                        </td>
                    </c:if>
                    <c:if test="${appointment.receiptId != null}">
                        <td>
                            <div class="row">
                                <form action="${pageContext.request.contextPath}/receipt/get/${appointment.receiptId}">
                                    <button class="btn btn-primary" type="submit">Переглянути рецепт</button>
                                </form>
                            </div>
                        </td>
                    </c:if>
                </c:if>
            </c:if>
        </tr>
        </tbody>
    </c:forEach>
</table>
</body>
</html>
<jsp:include page="../views/footer.jsp"/>

<script>
    function confirmRequest(requestId) {
        $.ajax({
            type: "POST",
            async: false,
            url: "/request/confirm",
            data: {
                requestId: requestId
            }
        }).done(function () {
            alert("Запит успішно опрацьовано!");
        }).fail(function () {
            alert("Щось пішло не так!");
        });
    }

    function rejectRequest(requestId) {
        $.ajax({
            type: "POST",
            async: false,
            url: "/request/reject",
            data: {
                requestId: requestId
            }
        }).done(function () {
            alert("Запит успішно опрацьовано!");
        }).fail(function () {
            alert("Щось пішло не так!");
        });
    }

    function completeRequest(requestId) {
        $.ajax({
            type: "POST",
            async: false,
            url: "/request/complete",
            data: {
                requestId: requestId
            }
        }).done(function () {
            alert("Запит успішно опрацьовано!");
        }).fail(function () {
            alert("Щось пішло не так!");
        });
    }
</script>