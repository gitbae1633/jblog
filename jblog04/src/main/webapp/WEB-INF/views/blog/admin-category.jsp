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
					<li><a href="${pageContext.request.contextPath}/${vo.id }/adminbasic">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a href="${pageContext.request.contextPath}/${vo.id }/adminwrite">글작성</a></li>
				</ul>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		<c:set var="count" value="1" />
		      		<c:forEach items="${list }" var="list" varStatus="status" >
		      			<tr>
							<td>${count }</td>
							<td>${list.name }</td>
							<td>${list.count }</td>   
							<td>
								<c:if test='${list.count == 0 && list.name != "기본"}'>
									<a href="${pageContext.request.contextPath}/${vo.id }/categorydelete/${list.no }">
										<img src="${pageContext.request.contextPath}/assets/images/delete.jpg">
									</a>
								</c:if>
							</td>
						</tr>
						<c:set var="count" value="${count+1 }" />	
		      		</c:forEach>
				</table>
      			
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      				<form id="category-form" method="post" action="${pageContext.request.contextPath }/${vo.id }/categoryadd">
				      	<table id="admin-cat-add">
				      		<tr>
				      			<td class="t">카테고리명</td>
				      			<td><input type="text" name="name"></td>
				      		</tr>
				      		<tr>
				      			<td class="s">&nbsp;</td>
				      			<td><input type="submit" value="카테고리 추가"></td>
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