<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="../js/xhr.js"></script>
<script type="text/javascript">
var user_id_count = -1;
// -1: 체크하지 않은 경우
// 0 : 아이디를 사용할 수 있는 경우
// 1 : 아이디가 존재하는 경우

function openZipcode() {
	window.open('Zipcode','confirm','toolbar=no,location=no,status=no,'+
		'menubar=no,scrollbar=yes,resizable=no,width=400,height=400');	
}

function validate(){
	var f = document.regist;
	if (f.user_id.value === '') {
		alert('먼저 id 를 입력하세요');
		f.user_id.focus();
		return;
	}
	
	if (user_id_count === -1) {
		alert('아이디 중복확인을 하세요');
		f.btnCheckID.focus();
		return;
	}
	
	if (user_id_count === 1) {
		alert('아이디가 사용중입니다.\n새로운 아이디를 입력하세요');
		f.user_id.focus();
		return;
	}
	
	if (f.user_pw.value !== f.user_pw2.value) {
		alert('비밀번호와 비밀번호 확인이 틀립니다.');
		f.user_pw.focus();
		return;
	}
	//f.method = 'GET';
	//f.action = 'regist_test.jsp';
	f.submit();
}

function check_id() {
	var user_id = document.getElementById('user_id').value;
	
	if (user_id.trim() === '') {
		alert('아이디를 먼저 입력하세요');
		document.getElementById('user_id').focus();
		return;
	}
	new ajax.xhr.Request('CheckID','user_id=' + user_id, callback, 'POST');
	
}

function change_user_id() {
	user_id_count = -1;
	document.getElementById('check_id_result').innerHTML = 
		'아이디 중복확인을 체크하세요.';
}

function callback(xhr) {
	if (xhr.readyState == 4) {
		if (xhr.status == 200) {
			user_id_count = parseInt(xhr.responseText.trim());
// 			alert(user_id_count);
			if (user_id_count === 1) {
				document.getElementById('check_id_result').innerHTML = 
					'이미 사용중인 아이디 입니다.';
				document.getElementById('user_id').focus();
				return;
			} else if (user_id_count === 0){
				document.getElementById('check_id_result').innerHTML = 
					'사용 가능한 아이디 입니다.';
				return;
			} else {
				alert('시스템 오류입니다...');
			}
		}
	}
}
</script>
</head>



<body>
<form name="regist" method="post" action="RegistAction" enctype="multipart/form-data">
<table border="1">
<tr>
	<caption>회원 가입</caption>
</tr>
<tr>
	<th>아이디</th>
	<td><input type="text" name="user_id" id="user_id" onkeyup="change_user_id();" />
		<input type="button" value="id중복검사" id="btnCheckID" 
		onclick="check_id();" />
		<div id="check_id_result"><br/></div>
	</td>
</tr>
<tr>
	<th>이름</th>
	<td><input type="text" name="user_name" id="user_name" >
		<div id="check_name_result"><br/></div>
	</td>
</tr>
<tr>
	<th>비밀번호</th>
	<td><input type="password" name="user_pw" id="user_pw" /></td>
</tr>
<tr>
	<th>비밀번호 확인</th>
	<td><input type="password" name="user_pw2" id="user_pw2" /></td>
</tr>
<tr>
	<th>이메일 주소</th>
	<td><input type="text" name="user_email" id="user_email" /></td>
</tr>
<tr>
	<th>사진</th>
	<td><input type="file" name="user_img" id="user_img" value="업로드" /></td>
</tr>
<tr>
	<th>성별</th>
	<td>남<input type="radio" name="user_gender" value="male" checked="checked">
		여<input type="radio" name="user_gender" value="female"></td>
</tr>
<tr>
	<th>생일</th>
	<td><input type="date" name="user_birth" id="user_birth" /></td>
</tr>
<tr>
	<th>소개 글</th>
	<td><input type="text" name="user_aboutme" id="user_aboutme" /></td>
</tr>
<tr>
	<td colspan="2" align="center">
		<input type="button" value="완료" onclick="validate();" />
	</td>
</tr>
</table>
</form>
</body>
</html>

