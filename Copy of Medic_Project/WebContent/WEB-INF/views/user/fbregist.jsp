<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="../js/xhr.js"></script>
<script type="text/javascript">

//FB.getLoginStatus()의 결과로 실행되는 함수
function statusChangeCallback(response) {
  console.log('statusChangeCallback');
  console.log(response);    
  // response 객체로 대상의 현재 로그인 상태 필드를 반환하여 어플리케이션이 알 수 있게 합니다.
  
  if (response.status === 'connected') {
    // 로그인상태라면 testAPI();
    testAPI();
  } else if (response.status === 'not_authorized') {
    // 페이스북은 로그인상태지만 사용자어플에는 로그인되지 않았을때
    
  } else {
    // 페이스북에 로그인하지 않은상태
    
  }
}

// 로그인버튼이 눌렸을때 실행
function checkLoginState() {
  FB.getLoginStatus(function(response) {
    statusChangeCallback(response);
  });
}

// 웹 어플리케이션 설정
window.fbAsyncInit = function() {
FB.init({
  appId      : '965832663524967', //페북에서 받은 Appcode
  cookie     : true,  // enable cookies to allow the server to access 
                      // the session
  xfbml      : true,  // parse social plugins on this page
  version    : 'v2.5' // use graph api version 2.5
});

// Now that we've initialized the JavaScript SDK, we call 
// FB.getLoginStatus().  This function gets the state of the
// person visiting this page and can return one of three states to
// the callback you provide.  They can be:
//
// 1. Logged into your app ('connected')
// 2. Logged into Facebook, but not your app ('not_authorized')
// 3. Not logged into Facebook and can't tell if they are logged into
//    your app or not.
//
// These three cases are handled in the callback function.

FB.getLoginStatus(function(response) {
  statusChangeCallback(response);
});

};

// Load the SDK asynchronously
// SDK 라이브러리 읽어오기
(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/sdk.js";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
var user_id;


// Here we run a very simple test of the Graph API after login is
// successful.  See statusChangeCallback() for when this call is made.  
function testAPI() {
  console.log('Welcome!  Fetching your information.... ');
  FB.api('/me?fields=id,name,email,gender,birthday,about', function(response) {
    user_id = response.id;
    document.getElementById('status').innerHTML =
      'Thanks for logging in,<br/> id:' + response.id + '<br/> name:'+response.name+'<br/> email:'+response.email+'<br/> gender:'+response.gender+'<br/> birthday:'+response.birthday;
    
    document.getElementById('user_id').value = 'f_'+response.id;
    document.getElementById('user_name').value = response.name;
    document.getElementById('user_email').value = response.email;
    document.getElementById('user_gender').value = response.gender;
    
  });
  FB.api('/me/picture?type=large', function(response) {
      document.getElementById('status').innerHTML += '<br/>profile picture:<img src="'+response.data.url+'"">';
    document.getElementById('user_photo').value = response.data.url;    
    document.regist.submit();
  });
  
}
</script>
</head>



<body>
<form id="regist" name="regist" method="post" action="FBRegistAction" >
<fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
</fb:login-button>
<table border="1">
<tr>
	<caption>회원 가입</caption>
</tr>
<tr>
	<th>아이디</th>
	<td><input type="text" name="user_id" id="user_id"  />
	</td>
</tr>
<tr>
	<th>이름</th>
	<td><input type="text" name="user_name" id="user_name" >
		<div id="check_name_result"><br/></div>
	</td>
</tr>
<tr>
	<th>이메일 주소</th>
	<td><input type="text" name="user_email" id="user_email" /></td>
</tr>
<tr>
	<th>사진</th>
	<td><input type="text" name="user_photo" id="user_photo"/></td>
</tr>
<tr>
	<th>성별</th>
	<td><input type="text" name="user_gender" id="user_gender"/></td>
</tr>

<tr>
	<td colspan="2" align="center">
		<input type="button" value="완료" />
	</td>
</tr>
</table>
</form>
<div id="status">
</div>
</body>
</html>

