<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="formmm"
	uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Photopia &mdash;</title>
<meta name="viewport" content="width=device-width, initial-scale=1">



<!-- <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet"> -->
<!-- <link href="https://fonts.googleapis.com/css?family=Playfair+Display:400,400i" rel="stylesheet"> -->

<!-- Animate.css -->
<link rel="stylesheet" href="css/animate.css">
<!-- Icomoon Icon Fonts-->
<link rel="stylesheet" href="css/icomoon.css">
<!-- Bootstrap  -->
<link rel="stylesheet" href="css/bootstrap.css">

<!-- Theme style  -->
<link rel="stylesheet" href="css/styles.css">

<!-- Modernizr JS -->
<script src="js/modernizr-2.6.2.min.js"></script>
<!-- FOR IE9 below -->
<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

<script type="text/javascript">
	function change(index) {
		var elem = document.getElementById("myButton-"+index);
		if (elem.value == "Follow") {
			follow(index);
			elem.value = "Following";
		} else
			unfollow(index);
			elem.value = "Follow";
	}
</script>
<script type="text/javascript">
function unfollow(index) {
	var followingId=$("#userId-"+index).val();
	$.post("http://localhost:8080/Photopia/unfollow?followingId="+followingId);
}</script>

<script type="text/javascript">
function follow(index) {
	var followingId=$("#userId-"+index).val();
	$.post("http://localhost:8080/Photopia/follow?followingId="+followingId);
}
</script>

</head>
<body>

	<div class="fh5co-loader"></div>

	<div id="page">
		<nav class="fh5co-nav" role="navigation">
		<div class="container">
			<div class="fh5co-top-logo">
				<div id="fh5co-logo">
					<a href="wall">Photopia</a>
				</div>
			</div>
			<div class="fh5co-top-menu menu-1 text-center">
				<ul>
					<li class="active"><a>Suggestion</a></li>
					<li><a href="profile">Profile</a></li>
					<li class="has-dropdown"><a href="#">Newsfeed</a>
						<ul class="dropdown">
							<li><a href="#"> Newsfeed </a></li>

						</ul></li>
					<li><a href="search">Search</a></li>

				</ul>
			</div>
			<div class="fh5co-top-social menu-1 text-right">
				<ul class="fh5co-social">
					<li><a href="logout">Logout</a></li>

				</ul>
			</div>
		</div>
		</nav>

		<div id="fh5co-author">
			<div class="container">
				<div class="row top-line animate-box">



					<div class="col-md-12">

						<h3 class="title animate-box">You might want to follow</h3>
					</div>
					<c:forEach var="user" items="${allUserFollowers}" varStatus="loop">
						<div class="col-md-6 col-sm-6">
							<div class="feature-center animate-box"
								data-animate-effect="fadeIn">


								<c:if test="${not empty user.profilePhotoUrl}">
									<img src="img/${user.profilePhotoUrl}" width="150" height="150">
								</c:if>

								<c:if test="${empty user.profilePhotoUrl}">
									<img src="img/emptyPhoto.jpg" width="150" height="150">
								</c:if>
								<h3>
									<c:out value="${user.username}" />
								</h3>
								<p></p>
								<form >
									<input type="button" id="myButton-${loop.index}" onclick="change(${loop.index})"
										value="Follow" />
									<input type="hidden" id="userId-${loop.index}" value="${user.userId}" />

								</form>
							</div>
						</div>

					</c:forEach>

	

					<div class="gototop js-top">
						<a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
					</div>

					<!-- jQuery -->
					<script src="js/jquery.min.js"></script>
					<!-- jQuery Easing -->
					<script src="js/jquery.easing.1.3.js"></script>
					<!-- Bootstrap -->
					<script src="js/bootstrap.min.js"></script>
					<!-- Waypoints -->
					<script src="js/jquery.waypoints.min.js"></script>
					<!-- Main -->
					<script src="js/main.js"></script>
</body>
</html>