<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang = "ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
<tr>
	<caption>가입 정보</caption>
</tr>
<tr>
	<th>사진</th>
	<td>${userVO.user_photo}
	<img src ="../download?filename=${userVO.user_photo}" width="100"/>
	</td>
</tr>
<tr>
	<th>아이디</th>
	<td>${userVO.user_id}</td>
</tr>
<tr>
	<th>이름</th>
	<td>${userVO.user_name}</td>
</tr>
<tr>
	<th>이메일 주소</th>
	<td>${userVO.user_email}</td>
</tr>

<tr>
	<th>성별</th>
	<td>${userVO.user_gender}</td>
</tr>
<tr>
	<th>생일</th>
	<td>${userVO.user_birth}</td>
</tr>
<tr>
	<th>소개 글</th>
	<td>${userVO.user_aboutme}</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<input type="button" value="완료" onclick="location.href='Login';"  />
	</td>
</tr>
</table>
</body>
</html>