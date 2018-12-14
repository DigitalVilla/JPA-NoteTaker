<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Preferences</title>

        <!-- //This code loads the style of each session user just as the method getStyleCookie(), it is just to practice JSTL-->
        <c:set var="usr" value="${sessionScope.username}"></c:set>
        <c:forEach items="${cookie}" var="c" varStatus="i">
            <c:if test="${c.key.equals(usr.toString())}">
                <c:set var="name" value="${c.key}"></c:set>
                <c:set var="object" value="${c.value}"></c:set>
                <c:set var="style" value="${c.value.value}"></c:set>
            </c:if>
        </c:forEach>
        <link rel="stylesheet" type="text/css" media="screen"
              href="res/${!empty  style?style:'blue'}.css" />
    </head>
    <body>
        <div class="header">
            <h1 class="header__title">Welcome to Preferences</h1>
            <h1 class="header__subtitle">${usr} your theme is ${cookie.style.value.toUpperCase()} </h1>
        </div>

        <div class="card2">
            <!--program links-->
            <div class="control">
                <a href='NotesController?logout=true' class="button button--link">logout</a>
                <a href='NotesController?notes=true' class="button button--link">Notes</a>
            </div>
            <!--ADD cookie style-->
            <form action='CookieController' method='POST' class="add-option">
                <select class="add-option__input" name="styles">
                    <option value="blue"  ${style.equals("blue")?'selected':""}> 
                        Blue </option>
                    <option value="green"${style.equals("green")?'selected=true':""}>
                        Green </option>
                    <option value="pink"${style.equals("pink")?'selected=true':""}>
                        Pink </option>
                    <option value="dark"${style.equals("dark")?'selected':""}>
                        Dark </option>
                </select><br>
                <button type="submit" class="button">SET</button>
            </form>
            <div style="text-align: center;">
                <h5>${requestScope.message}</h5>
            </div>

        </div>
    </body>
</html>
