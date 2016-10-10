<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="formmm"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Photopia</title>
<script src="http://s.codepen.io/assets/libs/modernizr.js"
	type="text/javascript"></script>



<link rel="stylesheet" href="css/normalize.css">


<link rel="stylesheet" href="css/style.css">


<style>
body {
	background-image: url("img/background.jpg");
	background-size: auto;
	background-repeat: no-repeat;
}
</style>


</head>

<body>

	<div class="login">
		<header class="header"> <span class="text">REGISTER</span> <span
			class="loader"></span> </header>
		<formmm:form commandName="user" class="form">
		<formmm:errors path="*" cssClass="errorblock" element="div"/>
	
			<c:if test="${not empty errorMessage}">
				<font color="red"> <c:out value="${errorMessage}" />
				</font>
			</c:if>

			<formmm:input path="username" class="input" placeholder="Username" cssErrorClass="error"/>
			<formmm:errors path="username" cssClass="error"> </formmm:errors>
			
			<formmm:input path="password" class="input" placeholder="Password" cssErrorClass="error" type="password" />
			<formmm:errors path="password" cssClass="error"> </formmm:errors>
			
			<formmm:input path="email" class="input" placeholder="E-mail" cssErrorClass="error"/>
<formmm:errors path="email" cssClass="error"> </formmm:errors>

			<button class="btn" type="submit"></button>
		</formmm:form>

	</div>
	<button class="resetbtn" type="reset"
		onclick="window.location.href='index'">Login</button>

</body>
</html>