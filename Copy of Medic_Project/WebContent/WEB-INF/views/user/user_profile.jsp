<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html lang ="ko">
<head>
<meta charset=UTF-8">
 
<title>Insert title here</title>
<script type="text/javascript">
function updateProfile(){
	document.getElementById('O_profile').value="변경사항 저장";
	document.getElementById('O_profile').onclick=updateProfileAction;
	document.getElementById('C_profile').type="button";
	
	document.getElementById('aboutme_div').hidden=true;
	document.getElementById('aboutme_txt').style='';
	
	
}
function updateProfileAction(){
	
	document.getElementById('O_profile').value="프로필 수정";
	document.getElementById('O_profile').onclick=updateProfile;
	document.getElementById('C_profile').type="hidden";
	
	document.getElementById('aboutme_div').hidden=false;
	document.getElementById('aboutme_txt').style='display:none';
	
	var x = document.getElementById('aboutme_txt').value;
	
	new ajax.xhr.Request('../user/updateUserProfile', 'user_profile=' + x, callback_resultUpdate, 'POST');
	
	
}

function callback_resultUpdate(xhr) {
	if (xhr.readyState == 4) {
		if (xhr.status == 200) {
			var m = eval('(' + xhr.responseText + ')');
			if(m){
				alert('프로필 수정 성공');
				history.go(0);
			} else {
				alert('프로필 수정 실패');
			}
		}
	}
}


</script>
</head>
<body>
<table>
	<tr>
		<td width="200" align="left">
			<font size="5em">${userVO.user_name}</font>(${userVO.user_id})
		</td>
		<td width="100">
			${userVO.user_birth}
		</td>
	</tr>
	<tr>
		<td colspan="2" align="left">
			<c:if test="${userVO.user_gender == 'male'}">
				남자 
			</c:if>
			<c:if test="${userVO.user_gender == 'female'}">
				여자  
			</c:if>
		</td>
	</tr>
</table>
<hr style="color: black"/>
<table>
	<tr>
		<td width="300" align="left">
			<textarea class="form-control" rows="5" id="aboutme_txt" style="display:none">${userVO.user_aboutme}</textarea>
			<div id="aboutme_div"><font size="3em">${userVO.user_aboutme}</font></div>
		</td>
	</tr>
</table>
<hr/>
 <input type="hidden" class="btn btn-default active" value="취소" id="C_profile"/>
 <input type="button" class="btn btn-info active" value="프로필 수정" onclick="updateProfile();" id="O_profile">


</body>
</html>