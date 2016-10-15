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
<script type="text/javascript" src="js/jquery-3.1.1.min"></script>

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
					<li><a href="suggestions">Suggestion</a></li>
					<li><a href="profile">Profile</a></li>
					<li ><a >Newsfeed</a></li>
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

		<div></div>

		<div id="fh5co-author">
			<div class="container">
				<div class="row top-line animate-box">
					<div
						class="col-md-6 col-md-offset-3 col-md-push-2 text-left fh5co-heading">


						<h2>${user.username}</h2>
						<img src="img/${user.profilePhotoUrl}" width="128" height="128">

						<p>${numberOfPosts}&ensp;posts</p>
						<p>${numberOfFollowers}&ensp;followers</p>
						<p>${numberOfFollowings}&ensp;followings</p>


					</div>
				</div>
				<c:forEach items="${allPosts}" var="post">
					<div class="row">
						<div class="col-md-4 text-center animate-box">
							<a class="work">
								<div class="work-grid"
									style="background-image: url(img/${post.url});">

									<div class="inner">
										<div class="desc">
											<h3></h3>
											<span class="cat"></span>
										</div>
									</div>
								</div>
							</a>
						</div>
				</c:forEach>

			</div>
		</div>
	</div>
	</div>
	</div>

	<div class="col-md-6 animate-box skills">
		<h3>Likes</h3>
		<div class="progress">
			<div class="progress-bar" role="progressbar" aria-valuenow="10"
				aria-valuemin="0" aria-valuemax="100" style="width: 10%">Likes</div>
		</div>
	</div>

	<div id="fh5co-started">
		<div class="container">
			<div class="row animate-box">
				<div class="col-md-8 col-md-offset-2 text-center fh5co-heading">
					<p></p>
				</div>
			</div>
		</div>
	</div>



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