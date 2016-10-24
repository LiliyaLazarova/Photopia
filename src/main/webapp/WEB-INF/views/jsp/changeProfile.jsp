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
	var _validFileExtensions = [ ".jpg", ".jpeg", ".bmp", ".gif", ".png" ];
	function Validate(oForm) {
		var arrInputs = oForm.getElementsByTagName("input");
		for (var i = 0; i < arrInputs.length; i++) {
			var oInput = arrInputs[i];
			if (oInput.type == "file") {
				var sFileName = oInput.value;
				if (sFileName.length > 0) {
					var blnValid = false;
					for (var j = 0; j < _validFileExtensions.length; j++) {
						var sCurExtension = _validFileExtensions[j];
						if (sFileName.substr(
								sFileName.length - sCurExtension.length,
								sCurExtension.length).toLowerCase() == sCurExtension
								.toLowerCase()) {
							blnValid = true;
							break;
						}
					}

					if (!blnValid) {
						message.innerHTML = "Sorry, allowed extensions are: "
								+ _validFileExtensions.join(", ");
						$("#message").append(message);
						return false;
					}
				}
			}
		}

		return true;
	}
</script>
<script type='text/javascript'>
	function showFileSize() {
		var input = document.getElementById('file');
		var file = input.files[0].size / 1048576;
		var message = document.createElement("h1");
		if (file > 0 && file <= 5) {
			message.innerHTML = "File size is OK!";
			document.getElementById("submit").disabled = false;
		} else {
			message.innerHTML = "The file is over the size limit of 5 MB : "
					+ Math.ceil(file) + " MB.";
			$.get("http://localhost:8080/Photopia/profile");
			document.getElementById("submit").disabled = true;
		}
		$("#message").append(message);
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
					<li><a>Newsfeed</a></li>
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


						<h2>About Me</h2>

						<formmm:form commandName="user" enctype="multipart/form-data"
							onsubmit="return Validate(this);">
							<img src="img/${user.profilePhotoUrl}" width="128" height="128">
							<br />
							<br />
							<p>Change profile photo:</p>
							<input type="file" id="file" name="file" accept="image/*" />
							<br />
							<input type="hidden" name="userId" value="${user.userId}" />
							<input type="hidden" name="username" value="${user.username}" />
							<h3>Username: ${user.username}</h3>
							<input type="hidden" name="email" value="${user.email}" />
							<h3>E-mail: ${user.email}</h3>
							<input class="btn btn-primary" type="button"
								onclick="window.location='changePassword'"
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

							<input type="hidden" name="profilePhotoUrl"
								value="${user.profilePhotoUrl}" />
							<br />
							<br />

							<input class="btn btn-primary" type="submit" id="submit"
								onclick="showFileSize()" value="Apply changes" />

						</formmm:form>
						<div id="message"></div>
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