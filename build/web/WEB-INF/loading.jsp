<%-- 
    Document   : loading
    Created on : Oct 25, 2018, 4:53:38 PM
    Author     : 767110
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Welcome Page</title>
    <c:set var="page" value="${requestScope.source}">
    </c:set>
    <meta http-equiv="Refresh" content="1;url=${page}?loaded='true'">
</head>
<style>
    html {
        background: #e5eff1;
    }

    #loading {
        position: absolute;
        left: 50%;
        transform: translateX(-50%);
        max-width: 80vh;
    }
</style>
<body>
    <img id="loading" src="res/load.gif">
</body>
</html>