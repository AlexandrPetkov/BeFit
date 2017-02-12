<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../elements/localization.jspf" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	
	<title>Регистрация</title>
	
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

<body>
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
					<li><a href="index.jsp">${home}</a></li>
					<li><a href="Controller?command=ShowAllTrainers">${ourTrainers}</a></li>
					<li><a href="Controller?command=ShowAllPupils">${ourPupils}</a></li>
					<li><a href="contact.jsp">${contacts}</a></li>
					
					<c:if test="${sessionScope.isLogged eq true}">
						<li><a href="Controller?command=goToUserCard&id=${sessionScope.user.id}">${cabinet}</a></li>
						<li class="active"><a class="btn" href="Controller?command=SignOut">${signOut}</a></li>
					</c:if>
					
					<c:if test="${not (sessionScope.isLogged eq true)}">
						<li class="active"><a class="btn" href="Controller?command=GoToSignIn">${signInUp}</a></li>
					</c:if>
					
				</ul>
			</div><!--/.nav-collapse -->
		</div>
	</div> 
	<!-- /.navbar -->
	<header id="head" class="secondary"></header>

	<!-- container -->
	<div class="container">

		<ol class="breadcrumb">
			<li><a href="index.jsp">${home}</a></li>
			<li class="active">${registrationWord}</li>
		</ol>

		<div class="row">
			
			<!-- Article main content -->
			<article class="col-xs-12 maincontent">
				<header class="page-header">
					<h1 class="page-title">${registrationWord}</h1>
				</header>
				
				<div class="col-md-8 col-md-offset-2 col-sm-9 col-sm-offset-1">
					<div class="panel panel-default">
						<div class="panel-body">
							<h3 class="thin text-center">${pupil_header}</h3>
							<p class="text-center text-muted">
								<c:if test="${not (sessionScope.isLogged eq true)}">
									${goToSignInText}<a href="Controller?command=GoToSignIn">${registrationSignIn}</a>.<br>
									${goToTrainerRegistrationText}<a href="Controller?command=GoToSignUpTrainer">${trainerAccountLink}</a>.
								</c:if>
								
								<c:if test="${sessionScope.isLogged eq true}">
									${alreadyLoggedIn}<br> 
									<div class="text-center">
									<form action="Controller" method="get">
										<input type="hidden" name="command" value="SignOut">
										<button class="btn" type="submit">${signOut}</button>
									</form>
									</div> 
								</c:if>
							</p>
							<hr>
							<p class="text-center text-muted" style="color: red">${requestScope.errorText}</p>

							<form action="Controller" method="post">
							<input type="hidden" name="command" value="SignUpPupil">
							<div class="row top-margin">
								<div class="col-xs-6">
									<label>${name}</label>
									<input type="text" name="name" class="form-control">
								</div>
								<div class="col-xs-6">
									<label>${lastName}</label>
									<input type="text" name="secondName" class="form-control">
								</div>
							</div>
								<hr>
								<div class="top-margin">
									<label>${email}<span class="text-danger">*</span></label>
									<input type="email" name="login" class="form-control">
								</div>

								<div class="row top-margin">
									<div class="col-sm-6">
										<label>${password}<span class="text-danger">*</span></label>
										<input type="password" name="password" class="form-control">
									</div>
									<div class="col-sm-6">
										<label>${passFonfirm}<span class="text-danger">*</span></label>
										<input type="password" name="confPassword" class="form-control">
									</div>
								</div>
								<hr>
								
								<div class="row top-margin">
								<div class="col-md-4">
									<label>${photo}</label>
									<input type="file" class="btn-default" name="photo" class="form-control">   
								</div>
									<div class="col-md-3 col-md-offset-1">
										<label>${sex}<span class="text-danger">*</span><br>
										<input type="radio" name="isMale" value="true"> 
										${male}<br>
										<input type="radio" name="isMale" value="false"> 
										${female}  
										</label>                   
									</div>
									<div class="col-md-4">
										<label>${birthday}</span></label>
										<input type="date" name="age" class="form-control">
									</div>
								</div>
								
								<div class="row top-margin">									
									<div class="col-md-3">
										<label>${weightText}</label>
										<input type="number" name="weight" class="form-control">
									</div>
								
								
									<div class="col-md-3">
										<label>${heightText}</label>
										<input type="number" name="height_sm" class="form-control">
									</div>
									
									<div class="col-md-6">
										<label>${goal}</label>
										<textarea name="goal" class="form-control"></textarea>
									</div>
									
								</div>
								
								<hr>

								<div class="row">
									<div class="col-lg-6">
										<label class="checkbox">
											<input type="checkbox"> 
											${termsText}<a href="page_terms.jsp" target="_blank">${termsLink}</a>
										</label>                        
									</div>
									<div class="col-lg-6 text-right">
										<button class="btn btn-action" type="submit">${registerBTNtext}</button>
									</div>
								</div>
								
								
							</form>
						</div>
					</div>

				</div>
				
			</article>
		</div>
	</div>	
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