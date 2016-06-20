<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>

<table>
<c:forEach items="${list}" var="vo">
<tr><td rowspan="2"><img src="../download?filename=${vo.user_photo}"  class="img-thumbnail" width="30"/> </td>
	<th align="left">${vo.user_id}님의 글:</th>
	<td align="right">${vo.regdate}</td>
</tr>
<tr>
	<td colspan="2"  style="width:250px;" >${vo.content}</td>
</tr>
</c:forEach>
</table>

