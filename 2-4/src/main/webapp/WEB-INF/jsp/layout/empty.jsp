<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>
        <c:choose>
            <c:when test="${view eq 'auth/login'}">
                Login
            </c:when>
            <c:otherwise>
                Unknown page
            </c:otherwise>
        </c:choose>
    </title>

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="/resources/css/site.css" rel="stylesheet"/>
    <link href="/resources/css/common.css" rel="stylesheet"/>
    <link href="/resources/css/indentations.css" rel="stylesheet"/>
    <link href="/resources/css/materialize.min.css" rel="stylesheet"/>
    <link href="/resources/css/materialize-extensions.css" rel="stylesheet"/>
</head>
<body>
<main>
    <jsp:include page="/WEB-INF/jsp/view/${view}/content.jsp"/>
</main>
<script type="text/javascript" src="/resources/js/materialize.min.js"></script>
<script type="text/javascript" src="/resources/js/materialize-extensions.js"></script>
</body>
</html>