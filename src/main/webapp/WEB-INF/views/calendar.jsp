<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Weekly Event Calendar in Spring Boot/Java (Open-Source)</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link type="text/css" rel="stylesheet" href="<c:url value="../../resources/css/main.css"/>">
    <link type="text/css" rel="stylesheet" href="<c:url value="../../resources/icons/style.css"/>">
</head>
<body>
<jsp:include page="../views/header.jsp"/>

<div class="main" style="display: flex;">
    <div style="margin-right: 10px;">
        <div id="nav"></div>
    </div>
    <div style="flex-grow: 1">
        <div id="dp"></div>
    </div>
</div>

<!-- DayPilot library -->
<script src="../../resources/js/daypilot/daypilot-all.min.js"></script>

<script>

    const datePicker = new DayPilot.Navigator("nav", {
        showMonths: 3,
        skipMonths: 3,
        selectMode: "week",
        onTimeRangeSelected: (args) => {
            dp.startDate = args.day;
            dp.update();
            dp.events.load("/api/events");
        }
    });
    datePicker.init();

    const dp = new DayPilot.Calendar("dp", {
        viewType: "Week",
        onTimeRangeSelected: async (args) => {


            const modal = await DayPilot.Modal.prompt("Create a new event:", "Event");
            dp.clearSelection();
            if (modal.canceled) {
                return;
            }
            const params = {
                start: args.start,
                end: args.end,
                text: modal.result
            };
            const {data} = await DayPilot.Http.post("/api/events/create", params);
            dp.events.add(data);
            console.log("Event created");
        },
        onEventMove: async (args) => {
            const params = {
                id: args.e.id(),
                start: args.newStart,
                end: args.newEnd
            };
            const {data} = await DayPilot.Http.post("/api/events/move", params);
            console.log("Event moved");
        },
        onEventResize: async (args) => {
            const params = {
                id: args.e.id(),
                start: args.newStart,
                end: args.newEnd
            };
            const {data} = await DayPilot.Http.post("/api/events/move", params);
            console.log("Event resized");
        },
        onBeforeEventRender: (args) => {
            args.data.barColor = args.data.color;
            args.data.areas = [
                {
                    top: 2,
                    right: 2,
                    icon: "icon-triangle-down",
                    visibility: "Visible",
                    action: "ContextMenu",
                    style: "font-size: 12px; background-color: #f9f9f9; border: 1px solid #ccc; padding: 2px 2px 0px 2px; cursor:pointer;"
                }
            ];
        },
        contextMenu: new DayPilot.Menu({
            items: [
                {
                    text: "Blue",
                    icon: "icon icon-blue",
                    color: "#1066a8",
                    onClick: (args) => app.updateColor(args.source, args.item.color)
                },
                {
                    text: "Green",
                    icon: "icon icon-green",
                    color: "#6aa84f",
                    onClick: (args) => app.updateColor(args.source, args.item.color)
                },
                {
                    text: "Yellow",
                    icon: "icon icon-yellow",
                    color: "#f1c232",
                    onClick: (args) => app.updateColor(args.source, args.item.color)
                },
                {
                    text: "Red",
                    icon: "icon icon-red",
                    color: "#cc0000",
                    onClick: (args) => app.updateColor(args.source, args.item.color)
                },
            ]
        })
    });
    dp.init();


    const app = {
        init: () => {
            dp.events.load("/api/events");
        },
        async updateColor(e, color) {
            const params = {
                id: e.id(),
                color: color
            };
            const {data} = await DayPilot.Http.post("/api/events/setColor", params);
            e.data.color = color;
            dp.events.update(e);
            console.log("Color updated");
        }
    };
    app.init();


</script>

</body>
</html>
<jsp:include page="../views/footer.jsp"/>
