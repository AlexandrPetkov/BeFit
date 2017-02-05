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
	
	<title>Личный кабинет</title>

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
						<li class="active"><a href="Controller?command=goToUserCard&id=${sessionScope.user.id}">${cabinet}</a></li>
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
	<header id="head" class="secondary">
	</header>
	
	<div class="container">
			<ol class="breadcrumb">
			<li><a href="index.jsp">${home}</a></li>
			<c:if test="${sessionScope.user.id eq user.id}">
						<li class="active">${cabinet}</li>
					</c:if>
			<c:if test="${not (sessionScope.user.id eq user.id)}">
						<li class="active">${user.name}'s card</li>
					</c:if>
		</ol>
	<div class="row">
			
			<!-- Article main content -->
		<article class="col-xs-12 maincontent">
			<header class="page-header">
				<h1 class="page-title">${user.name} ${user.secondName}</h1>
			</header>
			<div class="col-md-4 col-md-offset-1">
				<div class="panel panel-default">
					<div class="panel-body">
						<c:choose>
							<c:when test="${not (user.photo eq 'no photo')}">
								<a  href="Controller?command=goToUserCard&id=${user.id}&isTrainer=${user.isTrainer}"><img src="${user.photo}" alt="..." style="width: 100%"></a>
							</c:when>
							<c:otherwise>
								<img src="http://placehold.it/260x180" alt="..." style="width: 100%">
							</c:otherwise>
						</c:choose>
					</div>
					<div class="panel-body text-right">
						<button class="btn" type="submit">Изменить</button>
					</div>				
				</div>
			</div>
			
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-body">
						<div>
							<label class="text-center">Личные данные</label>
							<div class="row">
								<div class="col-xs-3">
									<p>${age}:</p>
									<p>${weight}:</p>
									<p>${height}:</p>
								</div>
								<div class="col-xs-3">
									<%-- <input type="number" size="5" name="age"  value="${user.age}" readonly> --%>
									<p><strong>${user.age}</strong> <small>${years}</small></p>
									<p><strong>${user.weight}</strong><small> ${weightKG}</small></p>
									<p><strong>${user.height_sm}</strong><small> ${heightSM}</small></p>	
								</div>
								<div class="col-xs-6">
									<p>Тренер: <strong>Радюк Виталий</strong></p>
									<p>Цель: <strong>${user.goal}</strong></p>	
									
								</div>
							</div>
						</div>
						<div class="text-right">
							<input type='button' value='Form Checker' onclick="changePupilBlock()"/>
							<button class="btn" onclick='changePupilBlock()'>sgsrgvvdrfv</button>
						</div>
					</div>
				</div>
			</div>
		</article>
		<p class="text-center text-muted" style="color: red">${requestScope.errorText}</p>
	</div>
	<div>
		<header class="page-header">
				<h1 class="page-title">Программа тренировок</h1>
			</header>
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