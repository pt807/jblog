<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog/blogheader.jsp"></c:import>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/admin/adminmenu.jsp"></c:import>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>삭제</th>      			
		      		</tr>
					<c:forEach items="${list }" var="vo" varStatus="status">
					<tr>
						<td>${vo.no }</td>
						<td>${vo.name }</td>
						<td>${vo.postCount }</td>
						<c:choose>
							<c:when test="${vo.postCount == 0}">
								<td >
								<a href="${pageContext.request.contextPath}/${authUser.id }/admin/category/delete/${vo.no }"><img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a>
								</td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
					</tr> 
					</c:forEach> 
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<form:form modelAttribute="categoryVo" action="${pageContext.request.contextPath}/${authUser.id }/admin/category" method="post">
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><form:input path="name"/></td>
		      			<td><p style="color:#f00; text-align:left; padding:0">
							<form:errors path="name" />
						</p></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
		      	</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/blog/blogfooter.jsp"></c:import>
	</div>
</body>
</html>