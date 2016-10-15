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
	function reloadPeople() {
		var text = $("#text").val();

		$.get("http://localhost:8080/Photopia/searchUsers?prefix=" + text,
				function(data) {
					$("#users").empty();

					for (index in data) {
						var object = data[index];

						var container = document.createElement("div");
						container.id = object.id;

						var name = document.createElement("h1");
						name.innerHTML = object.username;
						container.appendChild(name);

						var photo = document.createElement("img");
						photo.src = "img/" + object.profilePhotoUrl;
						photo.width = 100;
						photo.height = 100;
						container.appendChild(photo);
						var input = document.createElement("input");
						input.type="hidden";
						input.id="userId-"+index;
						input.value=object.userId;
						container.appendChild(input);
						
						var button=document.createElement("button");
						button.id="button-"+index;
						button.type="button";
						button.style.width="70px";
						button.style.height="30px";
						checkIfFollow(object.userId,button);
						button.onclick=function(){startFollow(index);};
						container.appendChild(button);
						var br = document.createElement("br");
						container.appendChild(br);
						var br1 = document.createElement("br");
						container.appendChild(br1);

						$("#users").append(container);
					}
				});
	}
</script>
<script type="text/javascript">
function checkIfFollow(followerId,button) {
	$.get("http://localhost:8080/Photopia/checkUserFollower?followerId="+followerId,
			function(data){
		var buttonValue=data;
		button.innerHTML =buttonValue;
		
		
	});
	
}
</script>
<script type="text/javascript">
function startFollow(index) {
	var followingId=$("#userId-"+index).val();
	$.post("http://localhost:8080/Photopia/follow?followingId="+followingId);
	var buttonValue = document.getElementById("button-"+index);
	buttonValue.innerHTML='Following';
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
					<li><a href="suggestions">Suggestion</a></li>
					<li><a href="profile">Profile</a></li>
					<li><a>Newsfeed</a></li>
					<li><a class="active" href="search">Search</a></li>

				</ul>
			</div>
			<div class="fh5co-top-social menu-1 text-right">
				<ul class="fh5co-social">
					<li><a href="logout">Logout</a></li>

				</ul>
			</div>
		</div>
		</nav>

		<div id="fh5co-author" class="col-md-6 col-md-offset-3 col-md-push-2 text-left fh5co-heading">
			<font size="6" class="title animate-box" >Search for new
				friends :</font> 
				<br/>
				<input type="input" onkeyup="reloadPeople()" id="text"
				placeholder="Search users..." />
				<br/>
				<br/>

			<div class="container">
				<div class="row top-line animate-box" id="users">

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