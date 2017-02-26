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
	
	<title>All pupils on this site</title>

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
					<li  class="active"><a href="Controller?command=ShowAllPupils">${ourPupils}</a></li>
					
					<c:if test="${sessionScope.isLogged eq true}">
						<li><a href="Controller?command=goToMyCard">${cabinet}</a></li>
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
<header id="head" class="secondary"></header>
	<div class="container">
		<ol class="breadcrumb">
			<li><a href="index.jsp">${home}</a></li>
			<li class="active">${ourPupils}</li>
		</ol>
		<h3 class="thin text-center">${allPupils_header}</h3>
		<p class="text-center text-muted" style="color: red">${requestScope.errorText}</p>
		<hr>
		
	<div class="row">
		<c:forEach var="user" items="${users}">
		<c:if test="${user.id ne sessionScope.user.id}">
			<div class="col-sm-6 col-md-4">
				<div class="thumbnail">
				<c:choose>
					<c:when test="${user.photo ne 'no photo'}">
							<a href="Controller?command=goToUserCard&id=${user.id}&isTrainer=${user.isTrainer}"><img src="${user.photo}" alt="..." style="height: 250px"></a>
					</c:when>
					<c:otherwise>
						<a href="Controller?command=goToUserCard&id=${user.id}&isTrainer=${user.isTrainer}"><img src="http://placehold.it/260x260" alt="..." style="height: 250px"></a>
					</c:otherwise>
				</c:choose>
					<div class="caption">
						<h3 class="text-center">${user.name} ${user.secondName}</h3>
						<p class="text-left text-muted"></p>
						
						
						<div class="row top-margin">
							<div class="col-sm-4">
								<p>${age}: <strong>${user.age}</strong> <small>${years}</small></p>
							</div>
							<div class="col-sm-4">
								<p>${weight}: <strong>${user.weight}</strong><small> ${weightKG}</small></p>
							</div>
							<div class="col-sm-4">
								<p>${height}: <strong>${user.height_sm}</strong><small> ${heightSM}</small></p>
							</div>
								<div class="col-sm-12">
									<p>${goalText}: <strong>${user.goal}</strong></p>
								</div>
							</div>
							
							

							<c:if test="${sessionScope.isLogged eq true}">
								<c:if test="${sessionScope.isTrainer}">
									<hr>
									
									<div class="text-right">
										<form>
											<input type="hidden" name="command" value="MakeOffer">
											<input type="hidden" name="idPupils" value="${user.id}">
											<button class="btn btn-action" type="submit">${makeOffer}</button>
										</form>
									</div>
								</c:if>
							</c:if>
					</div>
				</div>
			</div>
			</c:if>
			</c:forEach>
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