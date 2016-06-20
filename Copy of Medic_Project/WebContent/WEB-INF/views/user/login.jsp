<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang = "ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</head>
<body>

<br/><br/><br/><br/><br/><br/><br/><br/>
<div class="container" style="width:30%;">
  <hr/>  
	<form action="LoginAction" method="post" class="form-signin">
		<h1 class="form-signin-heading">의사소통</h1>
        <label for="user_id" class="sr-only">아이디</label>
<!--         id -->
        <input type="text" id="user_id" name="user_id" class="form-control" value="${cookieValue!= null ? cookieValue : ''}" placeholder="아이디" required autofocus>
        <label for="user_pw" class="sr-only">Password</label>
        <input type="password" id="user_pw" name="user_pw" class="form-control" placeholder="비밀번호" required>
        <div class="checkbox">
          <label>
            <input type="checkbox" id="remember_me" name="remember_me" value="remember-me" ${cookieValue != null ? "checked" : ""}> 아이디 저장
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
          <label>
            <h5>계정이 없으세요? <span onclick="location.href='Regist';">  회원가입 >></span> </h5>
          </label>    

   </form>
  <hr/>  
</div>


<!-- 
	<form action="LoginAction" method="post" class="form-signin">

<table class="table" >
<caption>로그인</caption>
<tr>
	<td>아이디</td><td> <input type="text" name ="user_id"/></td>
</tr>
<tr>
	<td>비밀번호</td><td><input type="text" name ="user_pw"/></td>
</tr>
<tr>
	<td colspan="2">
	<input type="submit" value="login"/>
	<input type="button" value="회원가입" onclick="location.href='Regist';"/>
	</td>
</tr>
</table>
</div>
</form> -->

</body>
</html>