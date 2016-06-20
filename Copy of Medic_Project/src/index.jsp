<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container-fluid">
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"><img class=""
					src="img/logo.png" style="z-index: 1000000" /></a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Home</a></li>
				<li><a class="dropdown-toggle" data-toggle="dropdown" href="#">메인
						<span class="caret"></span>
				</a></li>
				<li><a href="#">마이페이지</a></li>
				<li><a href="#">친구</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><span class="glyphicon glyphicon-search"></span></a></li>
				<li><input type="text" class="form-control input-sm"
					placeholder="search keyword" /></li>
				<li><a href="#"><span class="glyphicon glyphicon-user"></span>
						Sign Up</a></li>
				<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
						Login</a></li>
			</ul>
		</div>
	</nav>

	<div class="jumbotron" align="center">
		<h1>메인페이지</h1>
	</div>

	<div class="row">
		<div class="col-xs-12 col-sm-6 col-lg-4"><span style="font-size: 2 em;"> <font size="2 em">dsadsads</font>testname</span><br/>@testid<br/> <br/>  <br/></div>
		<div class="col-xs-12 col-sm-6 col-lg-4">testname<br/>@testid<br/> <br/>  <br/></div>
		<div class="col-xs-12 col-sm-6 col-lg-4">testname<br/>@testid<br/> <br/>  <br/></div>	
		<div class="col-xs-12 col-sm-6 col-lg-4">testname<br/>@testid<br/> <br/>  <br/></div>
		<div class="col-xs-12 col-sm-6 col-lg-4">testname<br/>@testid<br/> <br/>  <br/></div>
		<div class="col-xs-12 col-sm-6 col-lg-4">testname<br/>@testid<br/> <br/>  <br/></div>	
		<div class="col-xs-12 col-sm-6 col-lg-4">testname<br/>@testid<br/> <br/>  <br/></div>
		<div class="col-xs-12 col-sm-6 col-lg-4">testname<br/>@testid<br/> <br/>  <br/></div>
		<div class="col-xs-12 col-sm-6 col-lg-4">testname<br/>@testid<br/> <br/>  <br/></div>
	</div>
	<div class="jumbotron" align="center">
		<h1>테스트</h1>
	</div>
	<div class="jumbotron" align="center">
		<h1>테스트</h1>
	</div>
	<div class="jumbotron" align="center">
		<h1>테스트</h1>
	</div>
	<div class="jumbotron" align="center">
		<h1>테스트</h1>
	</div>
	
</div>
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    <li data-target="#myCarousel" data-slide-to="1"></li>
    <li data-target="#myCarousel" data-slide-to="2"></li>
    <li data-target="#myCarousel" data-slide-to="3"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img src="img/img1.jpg" alt="Chania">
    </div>

    <div class="item">
      <img src="img/img2.jpg" alt="Chania">
    </div>

    <div class="item">
      <img src="img/img3.jpg" alt="Flower">
    </div>

    <div class="item">
      <img src="img/img4.jpg" alt="Flower">
    </div>
  </div>

  <!-- Left and right controls -->
  <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
	

</div>


</body>
</html>