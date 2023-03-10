<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>${vo.title }</h1>
			<ul>
				<c:choose>
					<c:when test="${empty authUser }">
						<li><a href="${pageContext.request.contextPath}/user/auth">로그인</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
					</c:otherwise>
				</c:choose>
				<c:if test="${authUser.id == vo.id }">
					<li><a href="${pageContext.request.contextPath}/${vo.id }/adminbasic">블로그 관리</a></li>
				</c:if>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li class="selected">기본설정</li>
					<li><a href="${pageContext.request.contextPath}/${vo.id }/admincategory">카테고리</a></li>
					<li><a href="${pageContext.request.contextPath}/${vo.id }/adminwrite">글작성</a></li>
				</ul>
				<form action="${pageContext.request.contextPath}/${vo.id }/update" method="post" enctype="multipart/form-data">
					<input type="hidden" name="profile" value="${vo.profile }" />
	 		      	<table class="admin-config">
				      		<tr>
				      			<td class="t">블로그 제목</td>
				      			<td><input type="text" size="40" name="title" value="${vo.title }"></td>
				      		</tr>
				      		<tr>
				      			<td class="t">로고이미지</td>
				      			<td><img src="${pageContext.request.contextPath}${vo.profile }"></td>      			
				      		</tr>      		
				      		<tr>
				      			<td class="t">&nbsp;</td>
				      			<td><input type="file" name="file"></td>      			
				      		</tr>           		
				      		<tr>
				      			<td class="t">&nbsp;</td>
				      			<td class="s"><input type="submit" value="기본설정 변경"></td>      			
				      		</tr>		
			      	</table>
				</form>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>${vo.title }</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>