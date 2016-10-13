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
<title>Photopia</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Free HTML5 Website Template by FreeHTML5.co" />
<meta name="keywords"
	content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
<meta name="author" content="FreeHTML5.co" />

<!-- 
	//////////////////////////////////////////////////////

	FREE HTML5 TEMPLATE 
	DESIGNED & DEVELOPED by FreeHTML5.co
		
	Website: 		http://freehtml5.co/
	Email: 			info@freehtml5.co
	Twitter: 		http://twitter.com/fh5co
	Facebook: 		https://www.facebook.com/fh5co

	//////////////////////////////////////////////////////
	 -->

<!-- Facebook and Twitter integration -->
<meta property="og:title" content="" />
<meta property="og:image" content="" />
<meta property="og:url" content="" />
<meta property="og:site_name" content="" />
<meta property="og:description" content="" />
<meta name="twitter:title" content="" />
<meta name="twitter:image" content="" />
<meta name="twitter:url" content="" />
<meta name="twitter:card" content="" />

<!-- <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet"> -->

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
<script type="text/javascript">
	function addCommentForPost() {
		var text = $("#text").val();
		var postId = $("#postId").val();
		$.post("http://localhost:8080/Photopia/addComment?text=" + text
				+ "&postId=" + postId, function(data) {
			$("#iterator").empty();
			document.getElementById('comments').value = '';
			for (index in data) {
				var object = data[index];
				var name = document.createElement("h1");
				name.innerHTML = object.commentOwner + ":" + object.text;
				$("#comments").append(name);
			}

		});
		document.getElementById('text').value = '';

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
					<li><a href="suggestions">Suggestions</a></li>
					<li><a href="profile">Profile</a></li>
					<li class="has-dropdown" onmouseover="showNewsfeed()"><a
						href="#">Newsfeed</a>

						<div class="dropdown" id="newsfeeds">

							<p id="newsfeeds">hello</p>
						</div></li>
					<li><a href="contact.html">Search</a></li>

				</ul>
			</div>
			<div class="fh5co-top-social menu-1 text-right">
				<ul class="fh5co-social">
					<li><a href="logout">Logout</a></li>
				</ul>
			</div>
		</div>
		</nav>
		<div id="fh5co-work">
			<div class="container">
				<div class="row top-line animate-box">

					<div class="row">
						<div class="col-md-4 text-center animate-box">
							<a class="work">
								<div class="work-grid"
									style="background-image: url(img/${post.url})"></div>
							</a>
						</div>
						<div class="col-md-4 text-center animate-box">
							<a class="work">
								<div class="work-grid">
									<p>
										<font size="4">Location: </font>

									</p>
									<h1>${post.location}</h1>
									<p>
										<font size="4">Description: </font>
									</p>
									<h1>${post.description}</h1>
									<p>
										<font size="4">People liked this photo:</font>
									</p>
									<h1>${names}</h1>
									
								</div>
							</a>
						</div>
						<div class="col-md-4 text-center animate-box">
										<a class="work">
											<p id="comments">
										<font size="4">Comments:</font>
									</p>
									<div id="iterator">
										<c:forEach var="comment" items="${comments}" varStatus="loop">

											<h1>${comment.commentOwner}:${comment.text}</h1>

										</c:forEach>
									</div>
										</a>
									</div>

					</div>
					<form class="form">
						<input id="text" type="input"
							placeholder="Add your comment here..." /> <input type="hidden"
							id="postId" value="${post.postId}" />
						<button class="btn" onclick="addCommentForPost()" type="button">Add
							Comment</button>
					</form>

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