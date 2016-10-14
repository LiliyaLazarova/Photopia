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

<script type="text/javascript">
function validateFileSize(){
var input = document.getElementById('file');
  var file = input.files[0].size / 1048576;
  if (file > 0 && file <= 500) {}}
  
  </script>

<script type="text/javascript">
	function Validatebodypanelbumper(theForm) {
		var regexp;
		var extension = new FormData(theForm).get("file").value
				.lastIndexOf('.');
		if ((extension.toLowerCase() != ".gif")
				&& (extension.toLowerCase() != ".jpg") && (extension != "")) {
			alert("The \"FileUpload\" field contains an unapproved filename.");
			theForm.file.focus();
			return false;
		}
		return true;
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
<script type="text/javascript">
function getFollowingsList(){
	$.get("http://localhost:8080/Photopia/followingsList", 
			function(data){
		$("#followings").empty();
		var message=document.createElement("h1");
		message.innerHTML="Followings: ";
		$("#followings").append(message);
		for (index in data) {
			var object = data[index];
			var name = document.createElement("h1");
			name.innerHTML = object.username;
			$("#followings").append(name);
		}
	});
	
}
</script>
<script type="text/javascript">
function getFollowersList(){
	$.get("http://localhost:8080/Photopia/followersList",
			function(data){
		$("#followers").empty();
		var message=document.createElement("h1");
		message.innerHTML="Followers: ";
		$("#followers").append(message);
		for (index in data) {
			var object = data[index];
			var name = document.createElement("h1");
			name.innerHTML = object.username;
			$("#followers").append(name);
		}
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
					<li><a href="suggestions">Suggestion</a></li>
					<li class="active"><a>Profile</a></li>
					<li class="has-dropdown"><a href="#">Newsfeed</a>
						<ul class="dropdown">
							<li><a href="#">Web Design</a></li>

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

		<div></div>

		<div id="fh5co-work">
			<div class="container">
				<div class="row top-line animate-box">
					<div class="row">
						<div class="col-md-4 text-center animate-box">
							<a class="work">
								<div class="work-grid"
									style="background-image: url(img/${user.profilePhotoUrl})"></div>
							</a>
						</div>
						<div class="col-md-4 text-center animate-box">
							<a class="work">
								<div class="work-grid">
									<h2>About Me</h2>
									<h3>${user.username}</h3>

									<p>${numberOfPosts}&ensp;posts</p>
									<p onmouseover="getFollowersList()">${numberOfFollowers}&ensp;followers</p>
									<p onmouseover="getFollowingsList()">${numberOfFollowings}&ensp;followings</p>

									<input class="btn btn-primary" type="submit"
										onclick="window.location='changeProfile'"
										value="Change Profile" />

								</div>
							</a>
						</div>
						<div class="col-md-4 text-center animate-box">
							<a class="work">
								<div id="followings">
									
								</div>
								<div id="followers">
									
								</div>
								
							</a>
						</div>
					</div>
					
					<br/>
					<br/>
					<br/>
					
					<div class="row">
						<c:forEach items="${allPosts}" var="post" varStatus="loop">
							<div class="col-md-4 text-center animate-box">
								<a class="work" href="showPost?postId=${post.postId}">
									<div class="work-grid"
										style="background-image: url(img/${post.url});">
										<input type="hidden" name="postId" id="postId-${loop.index}"
											value="${post.postId}" />


										<div class="inner"
											onmouseover="getNumberOfLikesAndComments(${loop.index})">
											<div class="desc">
												<h3></h3>
												<span class="cat"><font size="5"
													id="likes-${loop.index}"></font></span>
											</div>
										</div>
									</div>
								</a>
							</div>
						</c:forEach>


						<div class="row">
							<div class="col-md-8 col-md-offset-2">
								<div class="author">
									<div class="author-inner animate-box"
										style="background-image: url(images/person3.jpg);"></div>
									<div class="desc animate-box">


										<span> </span>
										<formmm:form commandName="post" enctype="multipart/form-data"
											onsubmit="Validatebodypanelbumper()">

											<formmm:input type="text" path="location"
												placeholder="Add Location"></formmm:input>
											<br />

											<formmm:input type="text" path="description"
												placeholder="Add Description"></formmm:input>
											<br />
											<table>
												<tr>
													<td>File to upload:</td>
													<td><input type="file" id="file" name="file"
														accept="image/*" /></td>
												</tr>
												<tr>
													<td></td>
													<td><input class="btn btn-primary" type="submit"
														value="Upload" /></td>
												</tr>
											</table>
										</formmm:form>


									</div>
								</div>


								<div class="col-md-6 animate-box skills">
									<h3>Likes</h3>
									<div class="progress">
										<div class="progress-bar" role="progressbar"
											aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"
											style="width: 10%">Likes</div>
									</div>
								</div>
							</div>
						</div>
					</div>
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