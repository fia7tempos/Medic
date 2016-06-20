<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>



</head>

<script>
	var lati = document.getElementById("latitude");
	var longi = document.getElementById("longitude")

	window.onload = function() {
		getLocation();
	}

	function getLocation() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(showPosition);
		} else {

		}
	}
	function showPosition(position) {
		document.getElementById("latitude").value = position.coords.latitude;
		document.getElementById("longitude").value = position.coords.longitude;
	}
</script>

<body>
	<form action="InsertAction" method="POST" enctype="multipart/form-data">
		<table>
			<tr>
				<th>글내용(content):</th>
				<td><textarea class="form-control" rows="5" name="content"></textarea></td>
			</tr>
			<tr>
				<th>작성자(user_id):</th>
				<td><input class="form-control" type="text" name="user_id" /></td>
			</tr>
			<tr>
				<th>이미지(image):</th>
				<td><input class="form-control" type="file" name="image" /></td>
			</tr>
			<tr>
				<th>동영상(video):</th>
				<td><input class="form-control" type="file" name="video" /></td>
			</tr>
			<tr>
				<th>위도(latitude):</th>
				<td><input class="form-control" type="text" id="latitude"
					name="latitude"></td>
			</tr>
			<tr>
				<th>경도(longitude):</th>
				<td><input class="form-control" type="text" id="longitude"
					name="longitude" /></td>
			</tr>
			<tr>
				<th>해쉬태그(hashtag):</th>
				<td><input class="form-control" type="text" name="hashtag" /></td>
			</tr>
			<tr>
				<th>카테고리(category):</th>
				<td><select class="form-control" type="text" name="category"><option>병원</option>
						<option>의사</option>
						<option>질병</option></select></td>

			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="입력" /></td>
			</tr>

		</table>
	</form>
</body>
</html>