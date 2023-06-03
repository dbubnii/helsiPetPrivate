<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Головна</title>
    <link href="<c:url value="../../resources/css/cards.css"/>" rel="stylesheet" type="text/css">

    <style>
        /*body {*/
        /*    background: url('../../resources/images/ukraine_flag.png') no-repeat center fixed;*/
        /*    background-size: cover;*/
        /*}*/
        #container1 {
            background-image: url('../../resources/images/ukraine_flag.png');
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center;
        }

        #container2 {
            background-image: url('../../resources/images/ukraine_flag.png');
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center;
        }

        img {
            width: 100%;
            object-fit: contain;
        }
    </style>
</head>
<body style="background-color: #E0FFFF">
<jsp:include page="../views/header.jsp"/>
<div id="container1">
    <img src="../../resources/images/ukraine_flag.png"/>
</div>

<div style="margin: 5rem 5rem 2rem;">
    <hr>
    <h2 style="text-align: center;font-family: Roboto,sans-serif;"><em>Хто ми?</em></h2>
    <h3 style="text-align: center;font-family: Roboto,sans-serif;"><em>На сьогодні ситуація з тваринами у притулках,
        зоопарках та звіринцях на окупованих територіях, а також в зоні бойових дій, критична.
        До того ж безліч безпритульних і покинутих досі залишаються у зруйнованих міста, помираючи від нестачі їжі та
        води.
        <br> <br>
        Вже більше трьох місяців в Україні триває повномасштабна війна з російською федерацією.
        Катастрофічна ситуація складається не лише для людей, а й для тварин – мовчазних жертв війни, які знаходяться у
        притулках,
        зоопарках та звіринцях на окупованих територіях та територіях, де ведуться бойові дії.
        Вони залишаються заручниками без можливості евакуації чи отримання найнеобхіднішої гуманітарної допомоги:
        кормів, води,
        медикаментів тощо.
        <br> <br>
        Наша організація з першого дня війни опікується питаннями допомоги та захисту тварин у цей важкий час.
        TakeCareOfUs створено, щоб цілодобово перебувати на зв’язку з вами для максимально оперативного розв’язання
        проблем та надання допомоги.</em>
    </h3>
</div>
</body>
</html>
<jsp:include page="../views/footer.jsp"/>