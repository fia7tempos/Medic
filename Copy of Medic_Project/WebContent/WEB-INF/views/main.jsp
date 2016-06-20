<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>



<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<style>
div.polaroid-noimg {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	padding: 50px;
	background: url(../img/noimage.gif) no-repeat top center;
	background-size: cover;
	min-height: 300px;
}

div.polaroid {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	padding: 50px;
	min-height: 300px;
}

div.bg {
	background: url(../img/background1.jpg) no-repeat;
	position: fixed;
	width: 100%;
	height: 300%;
	top: 0;
	left: 0;
	z-index: -1;
	overflow: scroll;
}
.fixed-bg {
    /* The background image */
    background-image: url("../img/background1.jpg");
    /* Set a specified height, or the minimum height for the background image */
    min-height: 500px;
    /* Set background image to fixed (don't scroll along with the page) */
    background-attachment: fixed;
    /* Center the background image */
    background-position: center;
    /* Set the background image to no repeat */
    background-repeat: no-repeat;
    /* Scale the background image to be as large as possible */
    background-size: cover;
}
</style>


<script>
	var pg= 1;
	$(document).ready(function() {
		
		//글쓰기 창 열기
		$("#text_content").focus(function() {
			$("#hidden_write").slideDown("slow");
			$("#text_content").attr('cols','50').attr('rows','5');
			getLocation();
		});
		
		//글쓰기 접기		
		$("#btn_close_insert").click(function(){
			$("#hidden_write").slideUp("slow");
			$("#text_content").attr('cols','20').attr('rows','2');
		});
		//서브밋
		$("#btn_write").click(function(){
			
			$("#write_form").submit();
		});
		
		
		// 페이지 더 보기
		$("#btn_next").click(function(){			
			if(${pagenation.endPage}!=pg){
				pg++;

				$.get("/article/List?pg="+pg, function(data, status){
		     	  $("#div_next").append(data);
		   		 });
				
			}else{
				$("#btn_next").hide();
			}				
		});
		$("#div_list").load('/article/List?pg=1');
		
		//페이지 검색
		$("#btn_search").click(function(){
			pg=1;
			var keyword = document.getElementById("txt_keyword").value;
			console.log(keyword);
			console.log(pg);
		   	$("#div_list").html = '';
		   	$("#div_next").html = '';
		   	$("#btn_next").hide();
			$("#btn_search_next").show();
			
			$.get("/article/SearchList?pg="+pg+"&keyword="+keyword, function(data, status){
		     	  $("#div_list").html(data);
		   	});
		   	
		});
		//검색한 페이지 더 보기
		$("#btn_search_next").click(function(){			
			if(${pagenation.endPage}!=pg){
				pg++;
				var keyword = document.getElementById("txt_keyword").value;
				$.get("/article/SearchList?pg="+pg+"&keyword="+keyword, function(data, status){
		     	  $("#div_next").append(data);
		   		 });
				
			}else{
				$("#btn_search_next").hide();
			}				
		});
		
		
		//글 팝업 외곽 클릭시 사라짐
		$("#btn_article_close").click(function(){
			$("#div_view").fadeOut(1000);
		});
		
		
	});
		function fn_view(article){
		//글 클릭시 팝업
		
			var no = article.id;
			console.log(no+"클릭됨");
			
			$.get("/article/View?no="+no, function(data, status){
					$("#div_view").fadeIn(1000,function(){
		     	  		$("#div_inner_view").html(data);						
					});
		   	});	
			
		}
	
	//위치 체크 함수
	function getLocation() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(showPosition);
		} else {

		}
	}
	function showPosition(position) {
		document.getElementById("latitude").value = position.coords.latitude;
		document.getElementById("longitude").value = position.coords.longitude;
	}
	
	$(window).scroll(function(e){
			parallax();
	});

	function parallax(){
			var scrolled = $(window).scrollTop();
			$('.bg').css('top',-(scrolled*0.2)+'px');
	}
</script>
</head>
<body class="fixed-bg">

	<!-- 상단 메뉴바 -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">의사소통</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/Main">메인</a></li>
					<li><a href="/user/UserMain">마이페이지</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${sessionScope.userInfo==null}">
					<li><a href="/user/FBRegist"><span
							class="glyphicon glyphicon-user"></span> Sign Up with Facebook</a></li>
					<li><a href="/user/Regist"><span
							class="glyphicon glyphicon-user"></span> Sign Up</a></li>
					<li><a href="/user/Login"><span
							class="glyphicon glyphicon-log-in"></span> Login</a></li>
					</c:if>
					<c:if test="${sessionScope.userInfo!=null}">
					<li>${sessionScope.userInfo.user_name}님 환영합니다.</li>
					<li><a href="/user/Logout"><span
							class="glyphicon glyphicon-log-in"></span> LogOut</a></li>
					</c:if>
					
				</ul>
			</div>
		</div>
	</nav>

	<div><br/><br/><br/></div>
	<!-- 글 입력 폼 -->
	<form id="write_form" name="write_form" method="POST"
		action="/article/InsertAction" enctype="multipart/form-data">
		<div id="write_action"
			style="background-color: rgba(200, 200, 200, 0.5);">
			<textarea rows="2" cols="50" class="form-control" id="text_content"
				name="content" placeholder="리뷰를 등록해 주세요."></textarea>
			<div id="hidden_write" hidden="hidden" style="text-align: center;">
				<table>
					<tr>
						<td><span class="glyphicon glyphicon-user"><input
								class="form-control" type="text" name="user_id"
								placeholder="user_id" /></span></td>
						<td><span class="glyphicon glyphicon-picture"><input
								class="form-control" type="file" name="image"
								placeholder="image file" /></span></td>
						<td><span class="glyphicon glyphicon-facetime-video"><input
								class="form-control" type="file" name="video"
								placeholder="video file" /></span></td>
						<td><span class="glyphicon glyphicon-list-alt"></td>
						<td><select class="form-control" type="text" name="category">
								<option>병원</option>
								<option>의사</option>
								<option>질병</option>
						</select> </span></td>
						<td><span class="glyphicon glyphicon-tags"> <input
								class="form-control" type="text" name="hashtag" /></span></td>
					</tr>
				</table>
				<hr />
				<input class="form-control" type="text" id="latitude"
					name="latitude" /> <input class="form-control" type="text"
					id="longitude" name="longitude" /> <input type="button"
					id="btn_write" value="글쓰기" />
				<hr />
				<input type="button" class="btn" id="btn_close_insert" value="접기"
					style="text-align: right;" />
			</div>
		</div>
	</form>

	<!--  검색 폼 -->
	<div id="div_search" class="container">
		<form id="search_form" method="GET">
			<table class="table">
				<tr>
					<td><span class="glyphicon glyphicon-search">검색</span></td>
					<td><input class="form-control" id="txt_keyword"
						name="keyword" placeholder="search a keyword or hashtags" /></td>
					<td><input type="button" class="btn" id="btn_search"
						value="검색" /></td>

				</tr>
			</table>
		</form>
	</div>

		<!-- 게시물 페이지 -->

		<div id="div_list"></div>
		<div id="div_next"></div>
		<div>
			<input type="button" class="btn" id="btn_next" value="더보기" /> <input
				type="button" class="btn" id="btn_search_next" value="더보기"
				hidden="hidden" />
		</div>

	<!-- 글 보기 팝업 -->
	<div id="div_view" class="container"
		style="overflow: scroll; position: fixed; top: 10%; left: 10%; width: 80%; height: 80%; background: linear-gradient(to bottom right, rgba(100, 200, 200, 0.5), rgba(50, 150, 100, 0.5));"
		hidden="hidden">
		<input type="button" id="btn_article_close" class="btn" value="닫기"
			style="background-color: rgba(200, 200, 200, 255)" />
		<div id="div_inner_view" class="container"></div>
	</div>
</body>
</html>