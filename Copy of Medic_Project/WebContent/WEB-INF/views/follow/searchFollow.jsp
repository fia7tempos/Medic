<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html lang ="ko">
<head>
<meta charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
<table>
<c:if test="${searchFollowList != null }">
	<c:forEach items="${searchFollowList}" var="vo" >
	
	
		<tr>
			<td>
				<img src="../download?filename=${vo.user_photo}" width="30" class="img-circle"> &nbsp;
			</td>
			<td align="left">
						<div class="dropdown" >
							<div class="dropdown-toggle" data-toggle="dropdown">
								${vo.user_name}(${vo.user_id})<span class="caret"></span>
							</div>
							<ul class="dropdown-menu dropdown-menu-right">
								<li> <img src="../download?filename=${vo.user_photo}" width="30" class="img-circle"> 
									  &nbsp; ${vo.user_name}(${vo.user_id})
								</li>
								
								<li style="background-color: #cccccc"><a href="#" onclick="addFollwer('${vo.user_id}')">。add Follow</a></li>
								<li style="background-color: #cccccc"><a href="#">。go Home</a></li>
							</ul>
						</div>
			</td> 
		
	</c:forEach>
</c:if>
<c:if test="${searchFollowList == '[]' }">
검색 결과가 없습니다.
</c:if>
</table>


  
</body>
</html>