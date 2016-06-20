<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
 <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/xhr.js"></script>
<script type="text/javascript">

function viewStart(){		//온로드 첫 페이지시 실행 
		CommentList();	
		document.getElementById("insertbutton" ).type='button';
		document.getElementById("content" ).style='';
		if('${sessionScope.userInfo.user_id}'===''){
			document.getElementById('content').value=
				'로그인을 하셔야 작성가능합니다.';
			document.getElementById('content').readonly='true';
		}
}
function fn_commentInsert(){
	if('${sessionScope.userInfo.user_id}'===''){
		alert('로그인을 하셔야 작성가능합니다.');
	} else {
		if(document.getElementById('content').value==''){
			return alert("내용을 입력하세요");
		}
 		var article_no = ${articleVO.no};
		var user_id = '${sessionScope.userInfo.user_id}';
		var content = document.getElementById('content').value;
		var c_url ='/comment/CommentInsert'; 
		var c_params = 'article_no='+article_no+'&user_id='+user_id+
					  '&content='+content; 
		document.getElementById('content').value='';
		new ajax.xhr.Request(c_url, c_params, c_callback, 'post');
	
	}
}

function CommentList(){
	var article_no = 'article_no=' + '${articleVO.no}';
	new ajax.xhr.Request("/comment/CommentViewer", article_no, c_callback, "post");
}

function c_callback(xhr){
	if(xhr.readyState == 4){
		if(xhr.status == 200){
			document.getElementById('commentViewer').innerHTML = xhr.responseText;
		} 
	}	
}
</script>
</head>
<body>
	<table class="table">
		<caption>Article</caption>
		<tr>
			<th>번호</th>
			<td>${articleVO.no}</td>
		</tr>
		<tr>
			<th>글내용</th>
			<td>${articleVO.content}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${articleVO.user_id}</td>
		</tr>
		<tr>
			<th>이미지</th>
			<td><c:if test="${articleVO.image!=null}">
			<img src="../download?filename=${articleVO.image}" style="width:320px;"/>
			${articleVO.image}</c:if> 
			</td>
		</tr>
		<tr>
			<th>비디오</th>
			<td><c:if test="${articleVO.video!=null}">
				<video width="320" height="240" controls>
					 <source src="c:\dev64\upload\${articleVO.video}" type="${articleVO.video}">
  					  <source src="mov_bbb.ogg" type="video/ogg">
  					  Your browser does not support the video tag.
				</video>
<!-- 				<embed src="c:\dev64\upload\${articleVO.video}" /> -->
<!-- 				<video src="c:\dev64\upload\${articleVO.video}" width="320" height="240" -->

			${articleVO.video}
			</c:if></td>
		</tr>
		<tr>
			<th>위도</th>
			<td>${articleVO.latitude}</td>
		</tr>
		<tr>
			<th>경도</th>
			<td>${articleVO.longitude}</td>
		</tr>
		<tr>
			<th>해쉬태그</th>
			<td>${articleVO.hashtag}</td>
		</tr>
		<tr>
			<th>카테고리</th>
			<td>${articleVO.hashtag}</td>
		</tr>
		<tr>
			<th>날짜</th>
			<td>${articleVO.regdate}</td>
		</tr>
		<tr>
			<th>좋아요</th>
			<td>${articleVO.good}</td>
		</tr>
		<tr>
	 		<td colspan="2">${commentVO.article_no}
		 	<input type="button" onclick="viewStart();" value="댓글 보기" class="btn btn-default" />	
	 		<table>
	 		<tr>
	 		<td align="right">
				<textarea name="content" id="content" style="display:none" width="100%" rows="2" required="required" ></textarea><br/>
				<input type="hidden" id="insertbutton" class="btn btn-default" value="댓글작성" onclick="fn_commentInsert();"/>
				</td>
				</tr>
	 		</table>
			</td>
		</tr>
		<tr>
		
		
			<td colspan="2">
			<div id="commentViewer">
								</div>
								</td>
		</tr>
	</table>
	



</body>
</html>