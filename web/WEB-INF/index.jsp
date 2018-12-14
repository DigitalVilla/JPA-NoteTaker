<%-- 
    Document   : index
    Created on : Oct 25, 2018, 4:36:47 PM
    Author     : 767110
--%>

<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Notes App</title>


<link rel="stylesheet" type="text/css" media="screen"  
href="res/${! empty cookie.style.value?cookie.style.value:'blue'}.css" />
</head>
<body>
	<div class="card">
		<h1>Notes Login</h1>
		<form action='NotesController' method='POST'>
			User name: <br> <input type="text" name="username"><br>
			Password:<br> <input type="password" name="password"><br>
			<button id="loginBtn" type="submit" class="button">Login</button>
			<h4>${requestScope.message}</h4>
		</form>
	</div>
                <!--//optional error page-->
	  <!--<a style="color:#7777ff;"href="noPage.html">404</a>-->

</body>
</html>
