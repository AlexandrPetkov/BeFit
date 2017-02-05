<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../elements/localization.jspf" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	
	<title>BeFit - выбери личного тренера и будь всегда в форме</title>

	<link rel="shortcut icon" href="assets/images/gt_favicon.png">
	
	<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/font-awesome.min.css">

	<!-- Custom styles for our template -->
	<link rel="stylesheet" href="assets/css/bootstrap-theme.css" media="screen" >
	<link rel="stylesheet" href="assets/css/main.css">

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->
</head>

<body class="home">
	<!-- Fixed navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top headroom" >
		<div class="container">
			<div class="navbar-header">
				<!-- Button for smallest screens -->
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
				<a class="navbar-brand" href="index.jsp"><img src="assets/images/logo.png" alt="Be Fit"></a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right">
					<li><a class="btn" href="ChangeLocale">${changeLocaleButton}</a></li>
					<li class="active"><a href="index.jsp">${home}</a></li>
					<li><a href="Controller?command=ShowAllTrainers">${ourTrainers}</a></li>
					<li><a href="Controller?command=ShowAllPupils">${ourPupils}</a></li>
					<li><a href="contact.jsp">${contacts}</a></li>					
										
					<c:if test="${sessionScope.isLogged eq true}">
						<li><a href="Controller?command=goToUserCard&id=${sessionScope.user.id}&isTrainer=${sessionScope.user.isTrainer}">${cabinet}</a></li>
						<li><a class="btn" href="Controller?command=SignOut">${signOut}</a></li>
					</c:if>
					
					<c:if test="${not (sessionScope.isLogged eq true)}">
						<li><a class="btn" href="Controller?command=GoToSignIn">${signInUp}</a></li>
					</c:if>
					
				</ul>
			</div><!--/.nav-collapse -->
		</div>
	</div> 
	<!-- /.navbar -->

	<!-- Header -->
	<header id="head">
		<div class="container">
			<div class="row">
			<h1 class="lead">${tagline}</h1>
				<c:if test="${sessionScope.isLogged eq true}">
				<p>${hello}<strong>${user.name} </strong>${callToAction}</p>
				</c:if>
			</div>
		</div>
	</header>
	<!-- /Header -->

	<!-- Intro -->
	<div class="container text-center">
		<br> <br>
		<h2 class="thin">Be Fit</h2>
		<p class="text-muted">${siteDescription}</p>
	</div>
	<!-- /Intro-->
		
	<!-- Highlights - jumbotron -->
	<div class="jumbotron top-space">
		<div class="container">
			
			<h2 class="page-title text-center">${reasons}</h2>
			
			<div class="row text-center">
				<div class="col-md-3 col-sm-6 highlight">
					<h4 class="text-center">${reason_1_title}</h4>
					<p  class="text-muted">${reason_1_description}</p>
				</div>
				<div class="col-md-3 col-sm-6 highlight">
					<h4 class=>${reason_2_title}</h4>
					<p class="text-muted">${reason_2_description}</p>
				</div>
				<div class="col-md-3 col-sm-6 highlight">
					<h4>${reason_3_title}</h4>
					<p class="text-muted">${reason_3_description}</p>
				</div>
				<div class="col-md-3 col-sm-6 highlight">
					<h4>${reason_4_title}</h4>
					<p class="text-muted">${reason_4_description}</p>
					
				</div>
			</div> <!-- /row  -->
		
		</div>
	</div>
	<!-- /Highlights -->

	<!-- container -->
	<!-- Social links. @TODO: replace by link/instructions in template -->
	<section id="social">
		<div class="container">
			<div class="wrapper clearfix">
				<!-- AddThis Button BEGIN -->
				<div class="addthis_toolbox addthis_default_style">
				<a class="addthis_button_facebook_like" fb:like:layout="button_count"></a>
				<a class="addthis_button_tweet"></a>
				<a class="addthis_button_linkedin_counter"></a>
				<a class="addthis_button_google_plusone" g:plusone:size="medium"></a>
				</div>
				<!-- AddThis Button END -->
			</div>
		</div>
	</section>
	
		<footer id="footer" class="top-space">

		<div class="footer1">
			<div class="container">
				<div class="row">
					
					<div class="col-md-6 widget">
						<div class="col-md-6 widget">
						<div class="widget-body">
							<p>+375 25 544-82-07<br>
								+375 33 667-64-91<br>
								Skype: petkov.alexandr<br>
							</p>	
						</div>
						</div>
						<div class="col-md-6 widget">
						<div class="widget-body">
							<p>
								<a href="mailto:#">petkov.alexandr@gmail.com</a><br>
								<a href="mailto:#">petkov.alexandr@tut.by</a><br>
								<br>
							</p>	
						</div>
						</div>
					</div>

					<div class="col-md-3 widget">
						<div class="widget-body">
							<p class="follow-me-icons clearfix">
								<a href=""><i class="fa fa-twitter fa-2"></i></a>
								<a href=""><i class="fa fa-dribbble fa-2"></i></a>
								<a href=""><i class="fa fa-github fa-2"></i></a>
								<a href=""><i class="fa fa-facebook fa-2"></i></a>
							</p>	
						</div>
					</div>

					<div class="col-md-3 widget">
						<div class="widget-body">
							<p>Copyright &copy; 2017, Alexandr Petkov</p>
						</div>
					</div>
				</div>
			</div>
		</div>

	</footer>	
	

	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="assets/js/headroom.min.js"></script>
	<script src="assets/js/jQuery.headroom.min.js"></script>
	<script src="assets/js/template.js"></script>
</body>
</html>