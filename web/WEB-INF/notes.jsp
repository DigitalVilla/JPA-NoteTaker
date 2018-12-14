<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notes</title>
            <link rel="stylesheet" type="text/css" media="screen" 
              href="res/${cookie.style.value}.css" />
    </head>
    <body>
        <div class="header">
        <h1 class="header__title">Welcome to Notes!</h1>
        <h1 class="header__subtitle">${sessionScope.username} is logged in</h1>
        </div>
        <div class="card2">
            <!--program links-->
             <div class="control">
                    <a  href='NotesController?logout=true' class="button button--link">logout</a>
                    <a href='NotesController?preferences=true'  class="button button--link">preferences</a>
            </div>
            <!--ADD NOTE-->
            <form action='NotesOperationController' method='POST'  class="add-option">
                <input class="add-option__input" name="note" type="text" placeholder="  Add a New Note">
                <button type="submit" class="button">ADD</button>
            </form>
               <!--notes header-->
            <div class="widget-header">
                <h3 class="widget-header__title">Your Notes</h3>
                <a  href="NotesOperationController?remove=ALL" class="button button--link">Remove All</a>
            </div>
       
            <c:forEach items="${sessionScope.notes}" var="note" varStatus='count'> 
                <div class="option">
                    <p class="option__text">${count.index+1}. ${note} </p>
                    <a class="button button--link"
                       href="NotesOperationController?remove=${count.index}">remove</a>
                </div>
            </c:forEach>
               <h4 class="noteMsg">${requestScope.message}</h4>
        </div>
    </body>
</html>
