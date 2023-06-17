<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Виникла помилка</title>
    <style>
        .button{
            background: linear-gradient(
                    135deg
                    , #46494c 0%, #1985a1 100%);
            padding: 15px;
            border: none;
            border-radius: 50px;
            color: white;
            font-weight: 400;
            font-size: 1.2rem;
            margin-top: 10px;
            width:100%;
            letter-spacing: .11rem;
            outline:none;
        }

        .button:hover
        {
            transform: scale(1.05) translateY(-3px);
            box-shadow: 3px 3px 6px #38373785;
        }
    </style>
</head>
<body style="background-color: #E0FFFF">
<jsp:include page="../views/header.jsp"/>
    <p>Помилка: ${exception}</p>
    <p>Запитуване URL: ${url}</p>
    <a style="margin-top: 3rem" class="button" role="button" href="${pageContext.request.contextPath}/">Домашня сторінка</a>
</body>
</html>
<jsp:include page="../views/footer.jsp"/>