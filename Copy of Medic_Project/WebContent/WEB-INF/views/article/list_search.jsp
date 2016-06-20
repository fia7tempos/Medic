<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<div style="margin-top: 100px;"></div>

<div class="container-fluid">
	<div class="row vertical-align">
		<c:forEach items='${list}' var='vo' varStatus="idx">
			<div class="col-xs-12 col-sm-6 col-lg-4 article" id="${vo.no}">
				<c:if test="${vo.image==null}">
					<div class="polaroid-noimg">
				</c:if>
				<c:if test="${vo.image!=null}">
					<div class="polaroid"
						style="background: url(/download?filename=${vo.image }) no-repeat top center; background-size: cover;">
				</c:if>
			</div>
			<div class="well">
				<p>${vo.content }</p>
				<ul>
					<li><a href="#"><span class="glyphicon glyphicon-leaf"></span>
							${vo.no }</a> <a href="#"><span class="glyphicon glyphicon-user"></span>
							${vo.user_id }</a> <a href="#"><span
							class="glyphicon glyphicon-time"></span> ${vo.regdate }</a></li>
					<li><a href="#"><span
							class="glyphicon glyphicon-folder-open"></span> ${vo.category} }</a>
						<a href="#"><span class="glyphicon glyphicon-tags"></span>
							${vo.hashtag } </a> <a href="#"><span
							class="glyphicon glyphicon-heart"></span> ${vo.good} } Likes</a></li>
				</ul>

			</div>
	</div>

	</c:forEach>
</div>



