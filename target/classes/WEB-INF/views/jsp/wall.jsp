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
function addCommentForPost(index) {
	var text =$("#text-"+index).val();
	var postId=$("#postId-"+index).val();
	$.post("http://localhost:8080/Photopia/wall?text="+text+"&postId="+postId);
	document.getElementById('text-'+index).value='';
}
</script>

<script type="text/javascript">
	function showNewsfeed() {


		$.get("http://localhost:8080/Photopia/NewsfeedController", 
				function(data) {

			$("#newsfeeds").empty();
			var message = document.createElement("h1");
			message.innerHTML="Last Newsfeeds : ";
			$("#newsfeeds").append(message);
			
			for (index in data) {
				var object = data[index];
				var name = document.createElement("h1");
				name.innerHTML = object.userName+" "+object.message;
				$("#newsfeeds").append(name);


			}
		});

	}
</script>
<script type="text/javascript">
	function liked(index) {
		var postId = $("#postId-"+index).val();
		$.post("http://localhost:8080/Photopia/like?currentPostId=" + postId);
		

	}
</script>
<script type="text/javascript">
	function onClickChange(index) {
		var elem = document.getElementById("myButton-"+index);
		if (elem.value == "Like") {
			
			elem.value = "Unlike";
			liked(index);
		} else
			elem.value = "Like";
		unliked(index);
	}
</script>

<script type="text/javascript">
function unliked(index) {
	var postId = $("#postId-"+index).val();
	$.post("http://localhost:8080/Photopia/unlike?currentPostId=" + postId);
}
</script>

<script type="text/javascript">

function getNumberOfLikesAndComments(index) {
	var postId = $("#postId-"+index).val();
	$.get("http://localhost:8080/Photopia/numberOfLikes?currentPostId=" + postId,
			function (data) {
		$("#likes-"+index).empty();

		var likes = data[0];
		var numberLikes = document.createElement("h1");
		numberLikes.innerHTML="Likes: " + likes;
		$("#likes-"+index).append(numberLikes);
		
		var comments = data[1];
		var numberComments = document.createElement("h1");
		numberComments.innerHTML="Comments: " + comments;
		$("#likes-"+index).append(numberComments);
		
	});
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
						href="#">Newsfeed</a></li>

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


					<div
						class="col-md-6 col-md-offset-3 col-md-push-2 text-right fh5co-heading">
						
						<div id="newsfeeds"></div>

					</div>


					<c:forEach var="post" items="${allFollowingsPosts}" varStatus="loop">
						<div class="row">
							<div class="col-md-4 text-center animate-box">
								<a class="work" href="showPost?postId=${post.postId}"> <c:out
										value="${post.ownerName}" />
									<div class="work-grid"
										style="background-image: url(img/${post.url})">

										<div class="inner" onmouseover="getNumberOfLikesAndComments(${loop.index})">

											<div class="desc">
												
												<span class="cat"><font size="5" id="likes-${loop.index}"></font></span>
											</div>
										</div>
									</div>
								</a>
							</div>

						</div>
						<form class="form">
							<input id="text-${loop.index}" type="input"
								placeholder="Add your comment here..." /> <input type="hidden"
								name="postId" id="postId-${loop.index}" value="${post.postId}" />

							<button class="btn" onclick="addCommentForPost(${loop.index})"
								type="button">Add Comment</button>
						</form>

						<input type="button" id="myButton-${loop.index}"
							onclick="onClickChange(${loop.index})" value="Like" />


						<br />
						<br />
						<br />

					</c:forEach>
				</div>
			</div>


			<div id="fh5co-started">
				<div class="container">
					<div class="row animate-box">
						<div class="col-md-8 col-md-offset-2 text-center fh5co-heading">
							<p>
								<a href="#" class="btn btn-primary">Show more results</a>
							</p>
						</div>
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

