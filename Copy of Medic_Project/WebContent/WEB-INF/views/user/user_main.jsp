<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<title>의사소통</title>
<meta charset="utf-8">
<script type="text/javascript" src="../js/xhr.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
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

.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

.row.content {
	height: 840px;
	margin-top: 52px;
	padding: 0;
}

.sidenav {
	padding-top: 20px;
	background-color: #f1f1f1;
	height: 100%;
}

footer {
	background-color: #555;
	color: white;
	padding: 15px;
}

@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: 0px;
	}
}

#panel, #flip {
	padding: 5px;
	text-align: center;
	margin: 0px;
}

#panel {
	padding: 20px;
	margin: 0px;
	display: none;
}
</style>

<script type="text/javascript">
	function fn_view(article) {
		//글 클릭시 팝업

		var no = article.id;
		console.log(no + "클릭됨");

		$.get("/article/View?no=" + no, function(data, status) {
			$("#div_view").fadeIn(1000, function() {
				$("#div_inner_view").html(data);
			});
		});

	}

	$(document).ready(function() {
		$("#div_list").load(
				'/article/UserList?user_id='
						+ '${sessionScope.userInfo.user_id}');
		
		//글 팝업 외곽 클릭시 사라짐
		$("#btn_article_close").click(function() {
			$("#div_view").fadeOut(1000);
		});

	});
	
</script>


</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Logo</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/Main">Home</a></li>
					<li><a href="#">My Page</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="UserLogout"><span
							class="glyphicon glyphicon-log-in"></span> Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-3 sidenav">
				<div class="well" align="center">
					<img src="../download?filename=${userVO.user_photo}"
						class="img-thumbnail" width="250" />
					<hr />
					<table class="table">
						<thead>
							<tr>
								<th>게시글</th>
								<th>Following</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><a href="#"><span class="badge">${getUserArticle}</span></a></td>
								<td><a href="#"><span class="badge">${followCnt}</span></a></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="well">
					<c:import url="user_profile.jsp"></c:import>
				</div>

			</div>

			<div class="col-sm-7 text-left" id="div_list"></div>

			<div class="col-sm-2 sidenav">
				<div class="well">
					<p>
						<c:import url="user_friendList.jsp"></c:import>
					</p>
				</div>
			</div>
		</div>
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
