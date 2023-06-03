<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Головна</title>
    <style>
        .button-new {
            background: linear-gradient(
                    135deg, #753370 0%, #298096 100%);
            padding: 15px;
            border: none;
            border-radius: 50px;
            color: white;
            font-weight: 400;
            font-size: 1.2rem;
            margin-top: 10px;
            margin-left: 5rem;
            width: 100%;
            letter-spacing: .11rem;
            outline: none;
        }

        .button-new:hover {
            transform: scale(1.05) translateY(-3px);
            box-shadow: 3px 3px 6px #38373785;
        }
    </style>
</head>
<body style="background-color: #E0FFFF">
<jsp:include page="../views/header.jsp"/>
<h2 style="margin-bottom: 5rem; margin-left: 5rem">Вітаємо, Ваш акаунт успішно перевірено!</h2>
<a class="button-new" role="button" href="${pageContext.request.contextPath}/login"><b>Увійти</b></a>
</body>
</html>
<jsp:include page="../views/footer.jsp"/>