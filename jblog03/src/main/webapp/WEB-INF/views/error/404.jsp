<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Not Found(404) - Oooops</h1>
	<p>
		죄송합니다. 요청하신 페이지를 찾을 수 없습니다.
	</p> <br>
	<a href="${pageContext.request.contextPath }">홈페이지로 돌아가기</a>
</body>
</html>