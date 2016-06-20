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
	padding: 10px;
	background: url(../img/noimage.gif) no-repeat top center;
	background-size: cover;
	min-height: 300px;
}
div.polaroid {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
	padding: 10px;
	
	min-height: 300px;
	
}
</style>

<script type="text/javascript">
$(document).ready(function(){
		
}
</script>

</head>
<body>

	<div class="container-fluid">
		<div class="row">
			<c:forEach items='${list}' var='vo' varStatus="idx">
				<div class="col-xs-12 col-sm-6 col-lg-4" id="item${vo.no}">
					<c:if test="${vo.image==null}">
						<div class="polaroid-noimg">
					</c:if>
					<c:if test="${vo.image!=null}">
						<div class="polaroid" 
								style="background: url(../download?filename=${vo.image }) no-repeat top center; background-size: cover;">
					</c:if>
				</div>
				<div class="well">
					<ul>
						<li><a href="#"><span class="glyphicon glyphicon-user"></span>
								${vo.user_id }</a> <a href="#"><span
								class="glyphicon glyphicon-time"></span> ${vo.regdate }</a> <a
							href="#"><span class="glyphicon glyphicon-tags"></span>
								${vo.hashtag } </a> <a href="#"><span
								class="glyphicon glyphicon-heart"></span> ${vo.good} } Likes</a></li>
					</ul>
					<p>${vo.content }</p>
				</div>
		</div>

		</c:forEach>
	</div>
	</div>

	<div class="container-fluid" name="nextPg"></div>

	<div class="progress" name="progressbar">
		<div class="progress-bar progress-bar-striped active"
			role="progressbar" aria-valuenow="40" aria-valuemin="0"
			aria-valuemax="100" style="width: 40%">40%</div>
	</div>


	<table class="table">
		<caption>게시물 리스트</caption>
		<tr>
			<th colspan="5">전체 게시물 수 : ${pageNation.articleCount }</th>
		</tr>
		<tr>
			<th>번호</th>
			<th>내용</th>
			<th>작성자</th>
			<th>이미지</th>
			<th>비디오</th>
			<th>위도</th>
			<th>경도</th>
			<th>해쉬태그</th>
			<th>카테고리</th>
			<th>좋아요</th>
			<th>작성일</th>
		</tr>
		<c:forEach items='${list}' var='vo' varStatus="idx">
			<tr>
				<td>${pageNation.articleCount - idx.index - ((pageNation.pg - 1) * pageNation.pageSize) }[${vo.no }]
				</th>
				<td><a href="View?no=${vo.no }">${vo.content }</a>
				</th>
				<td>${vo.user_id }
				</th>
				<td>${vo.image }
				</th>
				<td>${vo.video }
				</th>
				<td>${vo.latitude }
				</th>
				<td>${vo.longitude }
				</th>
				<td>${vo.hashtag }
				</th>
				<td>${vo.category }
				</th>
				<td>${vo.good }
				</th>
				<td>${vo.regdate }
				</th>

			</tr>
		</c:forEach>
		<tr>
			<td colspan="11"><c:if test="${pageNation.startPage != 1 }">
			[<a href="List?pg=1">처음으로</a>]
			[<a href="List?pg=${pageNation.startPage - 1 }">이전 블럭</a>]
		</c:if> <c:forEach var="p" begin="${pageNation.startPage}"
					end="${pageNation.endPage}" step="1">
					<c:if test="${p == pageNation.pg }">${p}</c:if>
					<c:if test="${p != pageNation.pg }">
						<a href="List?pg=${p}">${p}</a>
					</c:if>
				</c:forEach> <c:if test="${pageNation.endPage != pageNation.pageCount }">
			[<a href="List?pg=${pageNation.endPage + 1 }">다음 블럭</a>]
			[<a href="List?pg=${pageNation.pageCount}">끝으로</a>]
		</c:if></td>
		</tr>
		<tr>
			<td colspan=="11"><input type="button" class="form-control"
				value="글쓰기" onclick="location.href='Insert'" /></td>
		</tr>
	</table>



</body>
</html>