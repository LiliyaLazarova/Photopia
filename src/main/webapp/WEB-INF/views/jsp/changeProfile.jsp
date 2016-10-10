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
					<li class="active"><a href="about.html">Profile</a></li>
					<li class="has-dropdown"><a href="#">Newsfeed</a>
						<ul class="dropdown">
							<li><a href="#">Web Design</a></li>

						</ul></li>
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

		<div>
			




		</div>

		<div id="fh5co-author">
			<div class="container">
				<div class="row top-line animate-box">
					<div
						class="col-md-6 col-md-offset-3 col-md-push-2 text-left fh5co-heading">


						<h2>About Me</h2>
						
						<formmm:form commandName="user" enctype="multipart/form-data"
									onsubmit="Validatebodypanelbumper()">
						<img src="img/${user.profilePhotoUrl}" width="128" height="128">
						<br/>
						<br/>
							<p>Change profile photo:</p>
											<input type="file" id="file" name="file"
												accept="image/*" />
												<br/>
						<input type="hidden" name="userId" value="${user.userId}" />
							<input type="hidden" name="username" value="${user.username}" />
							<h3>Username: ${user.username}</h3>
							<input type="hidden" name="email" value="${user.email}" />
							<h3>E-mail: ${user.email}</h3>
								<input class="btn btn-primary" type="button" onclick="window.location='changePassword'"
												value="Change password" />
							<br />
							<h3>Full Name:</h3>
							<formmm:input path="name" type="text" value="${user.name}" />
							<h3>Website:</h3>
							<formmm:input path="website" type="text" value="${user.website}" />
							<h3>Biography:</h3>
							<formmm:input path="biography" type="text"
								value="${user.biography}" />
							<h3>Gender:</h3>
							<formmm:input path="gender" type="text" value="${user.gender}" />
							<br />
							<br />
									
									
										
										
											
											<input class="btn btn-primary" type="submit"
												value="Apply changes" />
									
								</formmm:form>
					</div>
				</div>


				<div class="row">
					<div class="col-md-8 col-md-offset-2">
						<div class="author">
							<div class="author-inner animate-box"
								style="background-image: url(images/person3.jpg);"></div>
							<div class="desc animate-box">


								<span> </span>



							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<h3 class="title animate-box">My Services</h3>
							</div>
							<div class="col-md-6 col-sm-6">
								<div class="feature-center animate-box"
									data-animate-effect="fadeIn">
									<span class="icon"> <i class="icon-brush"></i>
									</span>
									<h3>Web Design</h3>
									<p>Far far away, behind the word mountains, far from the
										countries Vokalia and Consonantia, there live the blind texts.
										Separated they live in Bookmarksgrove</p>
									<p>
										<a href="#" class="btn btn-primary btn-outline">Learn More</a>
									</p>
								</div>
							</div>
							<div class="col-md-6 col-sm-6">
								<div class="feature-center animate-box"
									data-animate-effect="fadeIn">
									<span class="icon"> <i class="icon-cog"></i>
									</span>
									<h3>Web Development</h3>
									<p>Far far away, behind the word mountains, far from the
										countries Vokalia and Consonantia, there live the blind texts.
										Separated they live in Bookmarksgrove</p>
									<p>
										<a href="#" class="btn btn-primary btn-outline">Learn More</a>
									</p>
								</div>
							</div>

							<div class="col-md-6 col-sm-6">
								<div class="feature-center animate-box"
									data-animate-effect="fadeIn">
									<span class="icon"> <i class="icon-briefcase"></i>
									</span>
									<h3>Branding</h3>
									<p>Far far away, behind the word mountains, far from the
										countries Vokalia and Consonantia, there live the blind texts.
										Separated they live in Bookmarksgrove</p>
									<p>
										<a href="#" class="btn btn-primary btn-outline">Learn More</a>
									</p>
								</div>
							</div>
							<div class="col-md-6 col-sm-6">
								<div class="feature-center animate-box"
									data-animate-effect="fadeIn">
									<span class="icon"> <i class="icon-camera"></i>
									</span>
									<h3>Photography</h3>
									<p>Far far away, behind the word mountains, far from the
										countries Vokalia and Consonantia, there live the blind texts.
										Separated they live in Bookmarksgrove</p>
									<p>
										<a href="#" class="btn btn-primary btn-outline">Learn More</a>
									</p>
								</div>
							</div>

							<div class="col-md-12">
								<h3 class="title animate-box">My Skills</h3>
								<div class="row">
									<div class="col-md-6 animate-box skills">
										<h3>Designing</h3>
										<div class="progress">
											<div class="progress-bar" role="progressbar"
												aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
												style="width: 100%">100%</div>
										</div>
									</div>
									<div class="col-md-6 animate-box skills">
										<h3>HTML5/CSS3</h3>
										<div class="progress">
											<div class="progress-bar" role="progressbar"
												aria-valuenow="95" aria-valuemin="0" aria-valuemax="100"
												style="width: 95%">95%</div>
										</div>
									</div>
									<div class="col-md-6 animate-box skills">
										<h3>jQuery</h3>
										<div class="progress">
											<div class="progress-bar" role="progressbar"
												aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"
												style="width: 80%">80%</div>
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