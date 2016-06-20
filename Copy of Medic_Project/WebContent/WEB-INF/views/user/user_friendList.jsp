<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html lang="ko">
<head>
<meta charset=UTF-8">


<title>Insert title here</title>
<script type="text/javascript">
	function findFollow() {
		var x = document.getElementById('txtFollowSearch').value;

		new ajax.xhr.Request('../follow/searchFollw', 'user_id=' + x, callback,
				'POST');
	}

	function callback(xhr) {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				document.getElementById('searchResult').innerHTML = xhr.responseText
			}
		}
	}
	

	function addFollwer(follwerID){
		new ajax.xhr.Request('../follow/addFollwer', 'addFollwer_id=' + follwerID, callback_resultAddFollwer, 'POST');
	}

	function callback_resultAddFollwer(xhr) {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				var m = eval('(' + xhr.responseText + ')');
				if(m){
					alert('Follwer를 추가하였습니다.');
					history.go(0);
				} else {
					alert('이미 등록한 Follwer입니다.');
				}
			}
		}
	}
</script>
</head>
<body>
	<table>
		<caption style="width: 120px"><h4>Following List</h4></caption>
		<c:forEach items="${followList}" var="follow">
			<tr>
				<td height="70" align="left"><img name="friend_photo"
					src="../download?filename=${follow.user_photo}" alt="Follow"
					width="30" class="img-circle"></td>
				<td height="70" align="center" colspan="2" width="250">
					<div class="dropdown">
						<div class="dropdown-toggle" data-toggle="dropdown">
							${follow.user_name}(${follow.user_id})<span class="caret"></span>
						</div>
						<ul class="dropdown-menu dropdown-menu-right">
							<li>
								<img name="friend_photo" src="../download?filename=${follow.user_photo}" width="30" class="img-circle">
								${follow.user_name}(${follow.user_id})
							</li>
							<li style="background-color: #cccccc"><a href="#">。goHome</a></li>
						</ul>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
	<hr />


	<table>
		<tr>
			<td><input type="text" style="width: 100px" id="txtFollowSearch" />
			</td>
			<td>&nbsp;</td>
			<td>
				<button type="button" class="btn btn-info" onclick="findFollow();">
					<span class="glyphicon glyphicon-search"></span>
				</button>
			</td>
		</tr>
	</table>

	<hr />
	<div id="searchResult"></div>
</body>
</html>
